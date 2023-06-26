package mg.marketmanagement.productservice.controller;

import mg.marketmanagement.productservice.dto.ProductRequest;
import mg.marketmanagement.userservice.Utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ProductController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody ProductRequest request) throws JSONException;
    @GetMapping("getByName/{name}")
    ResponseEntity<Response> getByName(@PathVariable String name) throws JSONException;
    @GetMapping("getByCode/{code}")
    ResponseEntity<Response> getByCode(@PathVariable String code) throws JSONException;
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> deleteByCode(@PathVariable String code);
}
