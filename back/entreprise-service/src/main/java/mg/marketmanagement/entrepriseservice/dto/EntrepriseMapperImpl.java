package mg.marketmanagement.entrepriseservice.dto;

import mg.marketmanagement.entrepriseservice.model.Entreprise;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntrepriseMapperImpl implements EntrepriseMapper{
    @Override
    public Entreprise mapToEntreprise(EntrepriseRequest request) {
        return Entreprise.builder()
                .code(request.getCode())
                .name(request.getName())
                .adresse(request.getAdresse())
                .phoneNumber(request.getPhoneNumber())
                .status(request.getStatus())
                .codeClients(request.getCodeClients())
                .codeFournisseurs(request.getCodeFournisseurs())
                .build();
    }

    @Override
    public EntrepriseResponse mapToEntrepriseResponse(Entreprise entreprise) {
        return EntrepriseResponse.builder()
                .code(entreprise.getCode())
                .creationDate(entreprise.getCreationDate())
                .lastModifiedDate(entreprise.getLastModifiedDate())
                .adresse(entreprise.getAdresse())
                .phoneNumber(entreprise.getPhoneNumber())
                .name(entreprise.getName())
                .status(entreprise.getStatus())
                .codeClients(entreprise.getCodeClients())
                .codeFournisseurs(entreprise.getCodeFournisseurs())
                .clients(entreprise.getClients())
                .fournisseurs(entreprise.getFournisseurs())
                .build();
    }
}
