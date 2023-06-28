package mg.marketmanagement.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "user-market")
public class User {
    @Id
    private String id;
    private String username;
    //private String lastname;
    private String email;
    private String password;
    private String contact;
    private String photo;
    private String role= "USER";
}
