package mg.marketmanagement.commandefournisseurservice.dto;

import mg.marketmanagement.commandefournisseurservice.model.CommandFournisseur;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandFournisseurImpl implements CommandFournisseurMapper{
    @Override
    public CommandFournisseur mapToCommandFournisseur(CommandFournisseurRequest request) {
        return CommandFournisseur.builder()
                .code(request.getCode())
                .codeLineCommands(request.getCodeLineCommands())
                .build();
    }

    @Override
    public CommandFournisseurResponse mapToCommandFournisseurResponse(CommandFournisseur commandFournisseur) {
        return CommandFournisseurResponse.builder()
                .id(commandFournisseur.getId())
                .code(commandFournisseur.getCode())
                .date(commandFournisseur.getDate())
                .totalPrice(commandFournisseur.getTotalPrice())
                .lineCommands(commandFournisseur.getLineCommands())
                .codeLineCommands(commandFournisseur.getCodeLineCommands())
                .build();
    }
}
