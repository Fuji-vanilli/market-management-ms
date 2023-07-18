package mg.marketmanagement.clientservice.controller;

import mg.marketmanagement.clientservice.dto.ClientRequest;
import mg.marketmanagement.clientservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface ClientController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody ClientRequest request);
    @PutMapping("update")
    ResponseEntity<Response> addCommand(@RequestBody Map<String, String> command);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("getWithCommand/{code}")
    ResponseEntity<Response> getWithCommand(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @GetMapping("allWithCommand")
    ResponseEntity<Response> allWithCommand();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
