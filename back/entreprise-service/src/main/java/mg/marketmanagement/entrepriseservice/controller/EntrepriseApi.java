package mg.marketmanagement.entrepriseservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.entrepriseservice.dto.EntrepriseRequest;
import mg.marketmanagement.entrepriseservice.service.EntrepriseService;
import mg.marketmanagement.entrepriseservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static mg.marketmanagement.entrepriseservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class EntrepriseApi implements EntrepriseController {
    private final EntrepriseService entrepriseService;
    @Override
    public ResponseEntity<Response> add(EntrepriseRequest request) {
        return ResponseEntity.ok(entrepriseService.add(request));
    }

    @Override
    public ResponseEntity<Response> addClient(Map<String, String> data) {
        return ResponseEntity.ok(entrepriseService.addClient(data));
    }

    @Override
    public ResponseEntity<Response> addFournisseur(Map<String, String> data) {
        return ResponseEntity.ok(entrepriseService.addFournisseur(data));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(entrepriseService.get(code));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(entrepriseService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(entrepriseService.delete(code));
    }
}
