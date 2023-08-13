package mg.marketmanagement.fournisseurservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Adresse {
    private String adresse1;
    private String adresse2;
    private String city;
    private String codePostale;
    private String country;
}
