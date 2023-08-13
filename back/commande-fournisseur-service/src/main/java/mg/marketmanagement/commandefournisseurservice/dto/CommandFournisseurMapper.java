package mg.marketmanagement.commandefournisseurservice.dto;

import mg.marketmanagement.commandefournisseurservice.model.CommandFournisseur;

public interface CommandFournisseurMapper {
    CommandFournisseur mapToCommandFournisseur(CommandFournisseurRequest request);
    CommandFournisseurResponse mapToCommandFournisseurResponse(CommandFournisseur commandFournisseur);
}
