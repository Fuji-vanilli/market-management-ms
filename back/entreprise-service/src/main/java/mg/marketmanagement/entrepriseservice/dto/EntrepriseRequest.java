package mg.marketmanagement.entrepriseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.entrepriseservice.model.Adresse;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class EntrepriseRequest {
    private String code;
    private String name;
    private String status;
    private Adresse adresse;
    private String phoneNumber;
    private List<String> codeClients= new ArrayList<>();
    private List<String> codeFournisseurs= new ArrayList<>();
}
