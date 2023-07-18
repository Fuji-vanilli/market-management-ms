package mg.marketmanagement.commandeclientservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.commandeclientservice.dto.CommandClientMapper;
import mg.marketmanagement.commandeclientservice.dto.CommandClientRequest;
import mg.marketmanagement.commandeclientservice.dto.LineCommand;
import mg.marketmanagement.commandeclientservice.model.CommandClient;
import mg.marketmanagement.commandeclientservice.repository.CommandClientRepository;
import mg.marketmanagement.commandeclientservice.utils.Response;
import mg.marketmanagement.commandeclientservice.validator.CommandClientValidator;
import mg.marketmanagement.commandeclientservice.webClient.WebClientGetter;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommandClientImpl implements CommandClientService{
    private final CommandClientRepository commandClientRepository;
    private final CommandClientMapper commandClientMapper;
    private final WebClientGetter webClient;

    @Override
    public Response add(CommandClientRequest request) {
        List<String> errors= CommandClientValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("some field not valid!!!!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "some field not valid!!!"
            );
        }

        if(commandClientRepository.existsByCode(request.getCode())){
            log.error("command client already exist wiht the code: "+request.getCode());
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    Map.of(
                            "commandClient", commandClientRepository.findByCode(request.getCode())
                    ),
                    "command client already exist with the code: "+request.getCode()
            );
        }

        CommandClient commandClient= commandClientMapper.mapToCommandClient(request);
        List<LineCommand> lineCommands= new ArrayList<>();
        try {
            lineCommands= webClient.getLineCommand(request.getCodeLineCommands());
        } catch (JSONException e) {
            log.error("error to get line command microservice");
            throw new RuntimeException(e);
        }

        BigDecimal totalPrice= lineCommands.stream()
                .map(LineCommand::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        commandClient.setLineCommands(lineCommands);
        commandClient.setTotalPrice(totalPrice);
        commandClient.setDate(Instant.now());

        commandClientRepository.save(commandClient);
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/command-client/"+commandClient.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "commandLine", commandClientMapper.mapToCommandClientResponse(commandClient)
                ),
                "new command client added successfully!!!"
        );
    }

    @Override
    public Response get(String code) {
        if(!commandClientRepository.existsByCode(code)){
            log.error("the command client doesn't exist on the database");
            generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the command client doesn't exist on the database"
            );
        }
        Optional<CommandClient> commandClient= commandClientRepository.findByCode(code);
        List<LineCommand> lineCommands= null;
        try {
            lineCommands = webClient.getLineCommand(commandClient.get().getCodeLineCommands());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        commandClient.get().setLineCommands(lineCommands);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "commandClient", commandClientMapper.mapToCommandClientResponse(commandClient.get())
                ),
                "command client getted successfully!"
        );
    }

    @Override
    public List<CommandClient> getByCodes(List<String> codes) {
        return commandClientRepository.findByCodeIn(codes).stream()
                .peek(commandClient -> {
                    List<LineCommand> lineCommands;
                    BigDecimal totalPrice;
                    try {
                        lineCommands= webClient.getLineCommand(commandClient.getCodeLineCommands());
                        totalPrice= lineCommands.stream()
                                .map(LineCommand::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                    } catch (JSONException e) {
                        log.error("error to fetch a linecommand microservice");
                        throw new RuntimeException(e);
                    }
                    commandClient.setLineCommands(lineCommands);
                    commandClient.setTotalPrice(totalPrice);
                })
                .toList();
    }
    @Override
    public Response all() {

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "allCommandClient", commandClientRepository.findAll().stream()
                                .peek(commandClient -> {
                                    List<LineCommand> lineCommands= null;
                                    try {
                                        lineCommands = webClient.getLineCommand(commandClient.getCodeLineCommands());
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                    commandClient.setLineCommands(lineCommands);
                                })
                                .map(commandClientMapper::mapToCommandClientResponse)
                                .toList()
                ),
                "all command client getted successfully!"
        );
    }

    @Override
    public Response delete(String code) {

        if(!commandClientRepository.existsByCode(code)){
            log.error("sorry the command with the code: "+code+" doesn't exist on the database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry the command with the code: "+code+" doesn't exist on the database"
            );
        }

        commandClientRepository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "command client with the code: "+code +" deleted successfully"
        );
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message) {
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .data(data)
                .location(location)
                .message(message)
                .build();
    }
}
