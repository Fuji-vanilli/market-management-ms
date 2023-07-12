package mg.marketmanagement.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.clientservice.model.Adresse;
import org.springframework.data.annotation.Transient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ClientResponse {
    private String code;
    private Instant creationDate;
    private Instant lastModifiedUpdate;
    private String firstname;
    private String lastname;
    private String email;
    private String photo;
    private String phoneNumber;
    private Adresse adresse;
    @Transient
    private List<CommandClient> commandClients= new ArrayList<>();
}
