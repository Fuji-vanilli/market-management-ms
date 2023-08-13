package mg.marketmanagement.fournisseurservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.fournisseurservice.model.Adresse;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FournisseurRequest {
    private String code;
    private String firstname;
    private String lastname;
    private String email;
    private String photo;
    private String phoneNumber;
    private Adresse adresse;
    private List<String> codeCommands= new ArrayList<>();
}
