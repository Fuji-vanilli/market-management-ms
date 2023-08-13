package mg.marketmanagement.fournisseurservice.dto;

import mg.marketmanagement.fournisseurservice.model.Fournisseur;

public interface FournisseurMapper {
    Fournisseur mapToFournisseur(FournisseurRequest request);
    FournisseurResponse mapToFournisseurResponse(Fournisseur fournisseur);
}
