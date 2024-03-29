package mg.marketmanagement.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserRequest {
    private String username;
   // private String lastname;
    private String email;
    private String password;
    private String contact;
    private String photo;
    private String role;
}
