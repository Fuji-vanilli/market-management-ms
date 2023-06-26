package mg.marketmanagement.billing.service.controller;

import mg.marketmanagement.billing.service.dto.BillRequest;
import mg.marketmanagement.userservice.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BillController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody BillRequest request);
    @GetMapping("get/{uuid}")
    ResponseEntity<Response> get(@PathVariable String uuid);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{uuid}")
    ResponseEntity<Response> delete(@PathVariable String uuid);
}
