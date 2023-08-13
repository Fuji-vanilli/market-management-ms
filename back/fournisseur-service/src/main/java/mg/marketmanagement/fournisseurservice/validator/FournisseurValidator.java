package mg.marketmanagement.fournisseurservice.validator;

import mg.marketmanagement.fournisseurservice.dto.FournisseurRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FournisseurValidator {
    public static List<String> validate(FournisseurRequest request){
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
        if(Objects.isNull(request.getCode())){
            errors.add("code client required!");
        }
        return errors;
    }
}
