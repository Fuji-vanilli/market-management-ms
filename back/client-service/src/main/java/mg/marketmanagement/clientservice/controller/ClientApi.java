package mg.marketmanagement.clientservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.clientservice.dto.ClientRequest;
import mg.marketmanagement.clientservice.service.ClientService;
import mg.marketmanagement.clientservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static mg.marketmanagement.clientservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class ClientApi implements ClientController{

    private final ClientService clientService;
    @Override
    public ResponseEntity<Response> add(ClientRequest request) {
        return ResponseEntity.ok(clientService.add(request));
    }

    @Override
    public ResponseEntity<Response> addCommand(Map<String, String> command) {
        return ResponseEntity.ok(clientService.addCommand(command));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(clientService.get(code));
    }

    @Override
    public ResponseEntity<Response> getWithCommand(String code) {
        return ResponseEntity.ok(clientService.getWithCommand((code)));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(clientService.all());
    }

    @Override
    public ResponseEntity<Response> allWithCommand() {
        return ResponseEntity.ok(clientService.allWithCommand());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(clientService.delete(code));
    }


}
