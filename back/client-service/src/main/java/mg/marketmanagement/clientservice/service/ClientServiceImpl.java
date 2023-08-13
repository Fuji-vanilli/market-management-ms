package mg.marketmanagement.clientservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.clientservice.dto.ClientMapper;
import mg.marketmanagement.clientservice.dto.ClientRequest;
import mg.marketmanagement.clientservice.dto.CommandClient;
import mg.marketmanagement.clientservice.model.Client;
import mg.marketmanagement.clientservice.repository.ClientRepository;
import mg.marketmanagement.clientservice.utils.Response;
import mg.marketmanagement.clientservice.validator.ClientValidator;
import mg.marketmanagement.clientservice.webClient.WebClientGetter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final WebClientGetter webClient;
    @Override
    public Response add(ClientRequest request) {
        List<String> errors= ClientValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("error of more request");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "more field not valid!!! please try again"
            );
        }

        if(clientRepository.existsByCode(request.getCode())){
            log.error("article already exist on the database!");
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    null,
                    "article already exist on the database"
            );
        }

        Client client= clientMapper.mapToClient(request);

        List<CommandClient> commandClients= webClient.getCommandClient(client.getCodeCommands());

        client.setCreationDate(Instant.now());
        client.setLastModifiedDate(Instant.now());
        client.setCommandClients(commandClients);

        clientRepository.save(client);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/client/get/"+client.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "client", clientMapper.mapToClientResponse(client)
                ),
                "new client added successfully!!!"
        );
    }

    @Override
    public Response addCommand(Map<String, String> commands) {
        Optional<Client> client= clientRepository.findByCode(commands.get("codeClient"));
        if(client.isEmpty()){
            log.error("sorry the client doesn't exist on the database!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "sorry the client doesn't exist on the database!"
            );
        }
        if(client.get().getCodeCommands().contains(commands.get("codeCommand"))){
            log.error("sorry the command is already passed for this client");
            return generateResponse(
                    HttpStatus.OK,
                    null,
                    Map.of(
                            "codeCommands", client.get().getCodeCommands()
                    ),
                    "sorry the command is already passed for this client"
            );
        }

        List<String> codeCommands= client.get().getCodeCommands();
        codeCommands.add(commands.get("codeCommand"));

        List<CommandClient> commandClients= webClient.getCommandClient(codeCommands);

        client.get().setCodeCommands(codeCommands);
        client.get().setCommandClients(commandClients);
        clientRepository.save(client.get());

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "client", clientMapper.mapToClientResponse(client.get())
                ),
                "client updated successfully!"
        );
    }

    @Override
    public Response get(String code) {
        Optional<Client> client= clientRepository.findByCode(code);
        if(client.isEmpty()){
            log.error("client doesn't exist on the database!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "client doesn't exist on the databse!"
            );
        }
        List<CommandClient> commandClients= webClient.getCommandClient(client.get().getCodeCommands());
        client.get().setCommandClients(commandClients);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "client", clientMapper.mapToClientResponse(client.get())
                ),
                "client with the code: "+code+" getted successfully!"
        );
    }

    @Override
    public List<Client> getClients(List<String> codes) {
        List<Client> clients= clientRepository.findByCodeIn(codes);

        if(clients.isEmpty()){
            log.error("sorry no client with the list of codes, try again!!!!");
        }

        return clients.stream()
                .peek(client -> {
                    List<CommandClient> commandClients= webClient.getCommandClient(client.getCodeCommands());
                    client.setCommandClients(commandClients);
                })
                .toList();
    }

    @Override
    public Response getWithCommand(String code) {

        return null;
    }

    @Override
    public Response all() {

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "clients", clientRepository.findAll().stream()
                                .peek(client -> {
                                    List<CommandClient> commandClients= webClient.getCommandClient(client.getCodeCommands());
                                    client.setCommandClients(commandClients);
                                })
                                .map(clientMapper::mapToClientResponse)
                                .toList()
                ),
                "all client getted successfully!"
        );
    }

    @Override
    public Response allWithCommand() {
        return null;
    }

    @Override
    public Response delete(String code) {
        if(!clientRepository.existsByCode(code)){
            log.error("the client with the code :"+code+" doesn't exist on the database!");
            return generateResponse(
                    HttpStatus.OK,
                    null,
                    null,
                    "the client with the code !"+code+" doesn't exist on the database!"
            );
        }

        clientRepository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "client deleted successfully!"
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
