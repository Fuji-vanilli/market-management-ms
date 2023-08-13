package mg.marketmanagement.fournisseurservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.fournisseurservice.dto.CommandFournisseur;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "fournisseur")
public class Fournisseur {
    private String id;
    private Instant creationDate;
    private Instant lastModifiedDate;
    private String code;
    private String firstname;
    private String lastname;
    private String email;
    private String photo;
    private String phoneNumber;
    private Adresse adresse;
    private List<String> codeCommands= new ArrayList<>();
    private List<CommandFournisseur> commandFournisseurs= new ArrayList<>();
}
