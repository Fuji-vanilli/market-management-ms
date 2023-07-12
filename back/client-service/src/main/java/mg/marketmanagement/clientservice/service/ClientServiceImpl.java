package mg.marketmanagement.clientservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.clientservice.dto.ClientMapper;
import mg.marketmanagement.clientservice.dto.ClientRequest;
import mg.marketmanagement.clientservice.model.Client;
import mg.marketmanagement.clientservice.repository.ClientRepository;
import mg.marketmanagement.clientservice.utils.Response;
import mg.marketmanagement.clientservice.validator.ClientValidator;
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

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
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

        if(clientRepository.existsByEmail(request.getEmail())){
            log.error("article already exist on the database!");
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    null,
                    "article already exist on the database"
            );
        }

        Client client= clientMapper.mapToClient(request);
        client.setCreationDate(Instant.now());
        client.setLastModifiedDate(Instant.now());

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
    public Response get(String code) {
        return null;
    }

    @Override
    public Response getWithCommand(String code) {
        return null;
    }

    @Override
    public Response all() {
        return null;
    }

    @Override
    public Response allWithCommand() {
        return null;
    }

    @Override
    public Response delete(String code) {
        return null;
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
