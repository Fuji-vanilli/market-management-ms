package mg.marketmanagement.entrepriseservice.controller;

import jakarta.ws.rs.Path;
import mg.marketmanagement.entrepriseservice.dto.EntrepriseRequest;
import mg.marketmanagement.entrepriseservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface EntrepriseController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody EntrepriseRequest request);
    @PutMapping("addClient")
    ResponseEntity<Response> addClient(@RequestBody Map<String, String> data);
    @PutMapping("addFournisseur")
    ResponseEntity<Response> addFournisseur(@RequestBody Map<String, String> data);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{id}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
