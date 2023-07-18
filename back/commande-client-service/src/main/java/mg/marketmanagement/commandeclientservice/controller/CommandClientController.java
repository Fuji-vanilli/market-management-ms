package mg.marketmanagement.commandeclientservice.controller;


import mg.marketmanagement.commandeclientservice.dto.CommandClientRequest;
import mg.marketmanagement.commandeclientservice.model.CommandClient;
import mg.marketmanagement.commandeclientservice.utils.Response;
import org.apache.hc.core5.reactor.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommandClientController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody CommandClientRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("byCodes")
    List<CommandClient> getByCodes(@RequestParam("codes") List<String> codes);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);

}
