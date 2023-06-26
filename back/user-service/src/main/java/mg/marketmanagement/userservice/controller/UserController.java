package mg.marketmanagement.userservice.controller;

import jakarta.ws.rs.Path;
import mg.marketmanagement.userservice.Utils.Response;
import mg.marketmanagement.userservice.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody UserRequest request);
    @GetMapping("get/{email}")
    ResponseEntity<Response> get(@PathVariable String email);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{id}")
    ResponseEntity<Response> delete(@PathVariable String id);
}
