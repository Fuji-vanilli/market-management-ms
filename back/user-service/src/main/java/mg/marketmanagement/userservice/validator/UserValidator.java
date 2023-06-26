package mg.marketmanagement.userservice.validator;

import mg.marketmanagement.userservice.dto.UserRequest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserValidator {

    public static List<String> validate(UserRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getFirstname()) && Objects.isNull(request.getLastname())){
            errors.add("firstname and lastname required!!!");
        }
        if(Objects.isNull(request.getEmail())){
            errors.add("email required!");
        }
        if(Objects.isNull(request.getPassword())){
            errors.add("password required!");
        }
        if(Objects.isNull(request.getRole())){
            errors.add("role required!");
        }
        return errors;
    }
}
