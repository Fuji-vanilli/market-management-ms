package mg.marketmanagement.commandeclientservice.dto;

import mg.marketmanagement.commandeclientservice.model.CommandClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandClientMapperImpl implements CommandClientMapper{
    @Override
    public CommandClient mapToCommandClient(CommandClientRequest request) {
        return CommandClient.builder()
                .code(request.getCode())
                .codeLineCommands(request.getCodeLineCommands())
                .build();
    }

    @Override
    public CommandClientResponse mapToCommandClientResponse(CommandClient commandClient) {
        return CommandClientResponse.builder()
                .code(commandClient.getCode())
                .date(commandClient.getDate())
                .lineCommands(commandClient.getLineCommands())
                .totalPrice(commandClient.getTotalPrice())
                .codeLineCommands(commandClient.getCodeLineCommands())
                .build();
    }
}
