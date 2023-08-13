package mg.marketmanagement.fournisseurservice.service;

import mg.marketmanagement.fournisseurservice.dto.FournisseurRequest;
import mg.marketmanagement.fournisseurservice.model.Fournisseur;
import mg.marketmanagement.fournisseurservice.utils.Response;
import org.json.JSONException;

import java.util.List;
import java.util.Map;

public interface FournisseurService {
    Response add(FournisseurRequest request);
    Response get(String code) throws JSONException;
    List<Fournisseur> getFournisseurs(List<String> codes);
    Response addCommand(Map<String, String> data) throws JSONException;
    Response all();
    Response delete(String code);
}
