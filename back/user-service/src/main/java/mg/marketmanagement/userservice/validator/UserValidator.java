package mg.marketmanagement.userservice.validator;

import mg.marketmanagement.userservice.dto.UserRequest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserValidator {

    public static List<String> validate(UserRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getUsername()) && Objects.isNull(request.getUsername())){
            errors.add("firstname and lastname required!!!");
        }
        if(Objects.isNull(request.getEmail())){
            errors.add("email required!");
        }
        if(Objects.isNull(request.getPassword())){
            errors.add("password required!");
        }
        return errors;
    }
}
