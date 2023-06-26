package mg.marketmanagement.categoryservice.controller;

import jakarta.ws.rs.Path;
import mg.marketmanagement.categoryservice.dto.CategoryRequest;
import mg.marketmanagement.userservice.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CategoryController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody CategoryRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
