package mg.marketmanagement.commandeclientservice.dto;

import mg.marketmanagement.commandeclientservice.model.CommandClient;

public interface CommandClientMapper {
    CommandClient mapToCommandClient(CommandClientRequest request);
    CommandClientResponse mapToCommandClientResponse(CommandClient commandClient);
}
