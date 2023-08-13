package mg.marketmanagement.fournisseurservice.dto;

import mg.marketmanagement.fournisseurservice.model.Fournisseur;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FournisseurMapImpl implements FournisseurMapper{
    @Override
    public Fournisseur mapToFournisseur(FournisseurRequest request) {
        return Fournisseur.builder()
                .code(request.getCode())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .photo(request.getPhoto())
                .phoneNumber(request.getPhoneNumber())
                .adresse(request.getAdresse())
                .codeCommands(request.getCodeCommands())
                .build();
    }

    @Override
    public FournisseurResponse mapToFournisseurResponse(Fournisseur fournisseur) {
        return FournisseurResponse.builder()
                .id(fournisseur.getId())
                .code(fournisseur.getCode())
                .firstname(fournisseur.getFirstname())
                .lastname(fournisseur.getLastname())
                .adresse(fournisseur.getAdresse())
                .email(fournisseur.getEmail())
                .photo(fournisseur.getPhoto())
                .creationDate(fournisseur.getCreationDate())
                .lastModifiedDate(fournisseur.getLastModifiedDate())
                .commandFournisseurs(fournisseur.getCommandFournisseurs())
                .phoneNumber(fournisseur.getPhoneNumber())
                .codeCommands(fournisseur.getCodeCommands())
                .build();
    }
}
