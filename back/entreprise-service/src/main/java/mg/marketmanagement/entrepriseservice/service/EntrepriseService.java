package mg.marketmanagement.entrepriseservice.service;

import mg.marketmanagement.entrepriseservice.dto.EntrepriseRequest;
import mg.marketmanagement.entrepriseservice.utils.Response;

import java.util.Map;

public interface EntrepriseService {
    Response add(EntrepriseRequest request);
    Response addClient(Map<String, String> data);
    Response addFournisseur(Map<String, String> data);
    Response get(String code);
    Response all();
    Response delete(String code);
}
