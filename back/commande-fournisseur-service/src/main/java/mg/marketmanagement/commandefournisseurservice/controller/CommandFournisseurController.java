package mg.marketmanagement.commandefournisseurservice.controller;

import mg.marketmanagement.commandefournisseurservice.dto.CommandFournisseurRequest;
import mg.marketmanagement.commandefournisseurservice.model.CommandFournisseur;
import mg.marketmanagement.commandefournisseurservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommandFournisseurController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody CommandFournisseurRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("byCodes")
    List<CommandFournisseur> getCommands(@RequestParam("codes") List<String> codes);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
