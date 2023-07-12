package mg.marketmanagement.clientservice.dto;

import mg.marketmanagement.clientservice.model.Client;

public interface ClientMapper {
    Client mapToClient(ClientRequest request);
    ClientResponse mapToClientResponse(Client client);
}
