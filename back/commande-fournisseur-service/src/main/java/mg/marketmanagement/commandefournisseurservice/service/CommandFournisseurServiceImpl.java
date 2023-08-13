package mg.marketmanagement.commandefournisseurservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.commandefournisseurservice.dto.CommandFournisseurMapper;
import mg.marketmanagement.commandefournisseurservice.dto.CommandFournisseurRequest;
import mg.marketmanagement.commandefournisseurservice.dto.LineCommand;
import mg.marketmanagement.commandefournisseurservice.model.CommandFournisseur;
import mg.marketmanagement.commandefournisseurservice.repository.CommandFournisseurRepository;
import mg.marketmanagement.commandefournisseurservice.utils.Response;
import mg.marketmanagement.commandefournisseurservice.validator.CommandFournisseurValidator;
import mg.marketmanagement.commandefournisseurservice.webClient.WebClientGetter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommandFournisseurServiceImpl implements CommandFournisseurService{
    private final CommandFournisseurRepository commandFournisseurRepository;
    private final WebClientGetter webClient;
    private final CommandFournisseurMapper commandFournisseurMapper;
    @Override
    public Response add(CommandFournisseurRequest request) {
        List<String> errors= CommandFournisseurValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("some field not valid...");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "some field not valid...Please try again!"
            );
        }

        if(commandFournisseurRepository.existsByCode(request.getCode())){
            log.error("command fournisseur already passed");
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    Map.of(
                            "commandFournisseur", commandFournisseurMapper.mapToCommandFournisseurResponse(commandFournisseurRepository.findByCode(request.getCode()).get())
                    ),
                    "command fournisseur already passed...Please try again!"
            );
        }

        CommandFournisseur commandFournisseur= commandFournisseurMapper.mapToCommandFournisseur(request);
        List<LineCommand> lineCommands= webClient.getLineCommand(request.getCodeLineCommands());

        BigDecimal totalPrice= lineCommands.stream()
                .map(LineCommand::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        commandFournisseur.setDate(Instant.now());
        commandFournisseur.setLineCommands(lineCommands);
        commandFournisseur.setTotalPrice(totalPrice);

        commandFournisseurRepository.save(commandFournisseur);
        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/command-fournisseur/"+commandFournisseur.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "commandFournisseur", commandFournisseurMapper.mapToCommandFournisseurResponse(commandFournisseur)
                ),
                "new command fournisseur passed!"
        );
    }

    @Override
    public Response get(String code) {
        if(!commandFournisseurRepository.existsByCode(code)){
            log.error(
                    "the command fournisseur with the code {} doesn't exist ", code
            );
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    null,
                    "the command fournisseur with the code: "+code+" doesn't exist"
            );
        }

        CommandFournisseur commandFournisseur= commandFournisseurRepository.findByCode(code).orElse(null);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "commandFournisseur", commandFournisseurMapper.mapToCommandFournisseurResponse(commandFournisseur)
                ),
                "command fournisseur getted successfully!"
        );
    }

    @Override
    public List<CommandFournisseur> getCommands(List<String> codes) {

        return commandFournisseurRepository.findByCodeIn(codes).stream()
                .peek(commandFournisseur -> {
                    List<LineCommand> lineCommands= webClient.getLineCommand(commandFournisseur.getCodeLineCommands());
                    commandFournisseur.setLineCommands(lineCommands);
                })
                .toList();
    }

    @Override
    public Response all() {
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "commandFournisseurs", commandFournisseurRepository.findAll().stream()
                                .map(commandFournisseurMapper::mapToCommandFournisseurResponse)
                                .toList()
                ),
                "all command fournisseur getted successfully"
        );
    }

    @Override
    public Response delete(String code) {
        if(!commandFournisseurRepository.existsByCode(code)){
            log.error("the command fournisseur with the code !"+code+" doesn't exist on the database!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the command fournisseur with the code !"+code+" doesn't exist on the database!"
            );
        }
        commandFournisseurRepository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "command fournisseur deleted succesfully!"
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
