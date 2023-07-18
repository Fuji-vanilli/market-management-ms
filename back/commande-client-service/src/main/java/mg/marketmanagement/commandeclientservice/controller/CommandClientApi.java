package mg.marketmanagement.commandeclientservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.commandeclientservice.dto.CommandClientRequest;
import mg.marketmanagement.commandeclientservice.model.CommandClient;
import mg.marketmanagement.commandeclientservice.service.CommandClientService;
import mg.marketmanagement.commandeclientservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static mg.marketmanagement.commandeclientservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class CommandClientApi implements CommandClientController{
    private final CommandClientService commandClientService;

    @Override
    public ResponseEntity<Response> add(CommandClientRequest request) {
        return ResponseEntity.ok(commandClientService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(commandClientService.get(code));
    }

    @Override
    public List<CommandClient> getByCodes(List<String> codes) {
        return commandClientService.getByCodes(codes);
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(commandClientService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(commandClientService.delete(code));
    }
}
