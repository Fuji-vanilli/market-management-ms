package mg.marketmanagement.commandeclientservice.service;

import mg.marketmanagement.commandeclientservice.dto.CommandClientRequest;
import mg.marketmanagement.commandeclientservice.model.CommandClient;
import mg.marketmanagement.commandeclientservice.utils.Response;

import java.util.List;

public interface CommandClientService {
    Response add(CommandClientRequest request);
    Response get(String code);
    List<CommandClient> getByCodes(List<String> codes);
    Response all();
    Response delete(String code);
}
