package mg.marketmanagement.fournisseurservice.controller;

import mg.marketmanagement.fournisseurservice.dto.FournisseurRequest;
import mg.marketmanagement.fournisseurservice.model.Fournisseur;
import mg.marketmanagement.fournisseurservice.utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface FournisseurController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody FournisseurRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code) throws JSONException;
    @GetMapping("byCodes")
    List<Fournisseur> getFournisseurs(@RequestParam("codes") List<String> codes);
    @PutMapping("addCommand")
    ResponseEntity<Response> addCommand(@RequestBody Map<String, String> data) throws JSONException;
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
