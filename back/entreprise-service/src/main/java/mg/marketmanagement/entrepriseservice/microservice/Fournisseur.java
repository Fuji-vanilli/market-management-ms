package mg.marketmanagement.entrepriseservice.microservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Fournisseur {
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
