package mg.marketmanagement.clientservice.service;

import mg.marketmanagement.clientservice.dto.ClientRequest;
import mg.marketmanagement.clientservice.utils.Response;

import java.util.Map;

public interface ClientService {
    Response add(ClientRequest request);
    Response addCommand(Map<String, String> commands);
    Response get(String code);
    Response getWithCommand(String code);
    Response all();
    Response allWithCommand();
    Response delete(String code);
}
