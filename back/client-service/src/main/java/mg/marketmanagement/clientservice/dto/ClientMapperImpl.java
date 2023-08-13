package mg.marketmanagement.clientservice.dto;

import mg.marketmanagement.clientservice.model.Client;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientMapperImpl implements ClientMapper{

    @Override
    public Client mapToClient(ClientRequest request) {
        return Client.builder()
                .code(request.getCode())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .adresse(request.getAdresse())
                .photo(request.getPhoto())
                .phoneNumber(request.getPhoneNumber())
                .codeCommands(request.getCodeCommands())
                .build();
    }

    @Override
    public ClientResponse mapToClientResponse(Client client) {
        return ClientResponse.builder()
                .code(client.getCode())
                .creationDate(client.getCreationDate())
                .lastModifiedUpdate(client.getLastModifiedDate())
                .firstname(client.getFirstname())
                .lastname(client.getLastname())
                .email(client.getEmail())
                .adresse(client.getAdresse())
                .photo(client.getPhoto())
                .phoneNumber(client.getPhoneNumber())
                .codeCommands(client.getCodeCommands())
                .commandClients(client.getCommandClients())
                .build();
    }
}
