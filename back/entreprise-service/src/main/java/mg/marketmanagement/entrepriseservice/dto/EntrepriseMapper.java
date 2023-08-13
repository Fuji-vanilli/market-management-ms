package mg.marketmanagement.entrepriseservice.dto;

import mg.marketmanagement.entrepriseservice.model.Entreprise;

public interface EntrepriseMapper {
    Entreprise mapToEntreprise(EntrepriseRequest request);
    EntrepriseResponse mapToEntrepriseResponse(Entreprise entreprise);
}
