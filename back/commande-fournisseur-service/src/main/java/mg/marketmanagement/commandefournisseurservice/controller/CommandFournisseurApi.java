package mg.marketmanagement.commandefournisseurservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.commandefournisseurservice.dto.CommandFournisseurRequest;
import mg.marketmanagement.commandefournisseurservice.model.CommandFournisseur;
import mg.marketmanagement.commandefournisseurservice.service.CommandFournisseurService;
import mg.marketmanagement.commandefournisseurservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static mg.marketmanagement.commandefournisseurservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class CommandFournisseurApi implements CommandFournisseurController{
    private final CommandFournisseurService commandFournisseurService;
    @Override
    public ResponseEntity<Response> add(CommandFournisseurRequest request) {
        return ResponseEntity.ok(commandFournisseurService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(commandFournisseurService.get(code));
    }

    @Override
    public List<CommandFournisseur> getCommands(List<String> codes) {
        return commandFournisseurService.getCommands(codes);
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(commandFournisseurService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(commandFournisseurService.delete(code));
    }
}
