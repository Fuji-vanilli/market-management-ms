package mg.marketmanagement.entrepriseservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.entrepriseservice.microservice.Client;
import mg.marketmanagement.entrepriseservice.microservice.Fournisseur;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "entreprise")
public class Entreprise {
    private String id;
    private Instant creationDate;
    private Instant lastModifiedDate;
    private String code;
    private String name;
    private String status;
    private Adresse adresse;
    private String phoneNumber;
    private List<String> codeClients= new ArrayList<>();
    private List<String> codeFournisseurs= new ArrayList<>();
    private List<Client> clients= new ArrayList<>();
    private List<Fournisseur> fournisseurs= new ArrayList<>();
}
