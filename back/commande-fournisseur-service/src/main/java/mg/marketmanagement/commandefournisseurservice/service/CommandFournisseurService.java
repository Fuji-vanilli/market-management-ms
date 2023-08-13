package mg.marketmanagement.commandefournisseurservice.service;

import mg.marketmanagement.commandefournisseurservice.dto.CommandFournisseurRequest;
import mg.marketmanagement.commandefournisseurservice.model.CommandFournisseur;
import mg.marketmanagement.commandefournisseurservice.utils.Response;

import java.util.List;

public interface CommandFournisseurService {
    Response add(CommandFournisseurRequest request);
    Response get(String code);
    List<CommandFournisseur> getCommands(List<String> codes);
    Response all();
    Response delete(String code);
}
