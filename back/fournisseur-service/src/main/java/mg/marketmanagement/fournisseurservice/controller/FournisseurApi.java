package mg.marketmanagement.fournisseurservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.fournisseurservice.dto.FournisseurRequest;
import mg.marketmanagement.fournisseurservice.model.Fournisseur;
import mg.marketmanagement.fournisseurservice.service.FournisseurService;
import mg.marketmanagement.fournisseurservice.utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static mg.marketmanagement.fournisseurservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class FournisseurApi implements FournisseurController{
    private final FournisseurService fournisseurService;
    @Override
    public ResponseEntity<Response> add(FournisseurRequest request) {
        return ResponseEntity.ok(fournisseurService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) throws JSONException {
        return ResponseEntity.ok(fournisseurService.get(code));
    }

    @Override
    public List<Fournisseur> getFournisseurs(List<String> codes) {
        return fournisseurService.getFournisseurs(codes);
    }

    @Override
    public ResponseEntity<Response> addCommand(Map<String, String> data) throws JSONException {
        return ResponseEntity.ok(fournisseurService.addCommand(data));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(fournisseurService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(fournisseurService.delete(code));
    }
}
