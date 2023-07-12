package mg.marketmanagement.clientservice.validator;

import mg.marketmanagement.clientservice.dto.ClientRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientValidator {
    public static List<String> validate(ClientRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getFirstname())){
            errors.add("firstname required!");
        }
        if(Objects.isNull(request.getEmail())){
            errors.add("email required!");
        }
        if(Objects.isNull(request.getPhoneNumber())){
            errors.add("phone number required!");
        }
        if(Objects.isNull(request.getAdresse())){
            errors.add("adress required!");
        }
        return errors;
    }
}
