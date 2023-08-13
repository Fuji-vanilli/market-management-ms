package mg.marketmanagement.entrepriseservice.validator;


import mg.marketmanagement.entrepriseservice.dto.EntrepriseRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCode())){
            errors.add("code required!");
        }
        if(Objects.isNull(request.getAdresse())){
            errors.add("adresse required!");
        }
        if(Objects.isNull(request.getCodeClients())){
            errors.add("code clients required!");
        }
        if(Objects.isNull(request.getCodeFournisseurs())){
            errors.add("code fournisseurs required!");
        }
        if(Objects.isNull(request.getName())){
            errors.add("name required!");
        }
        return errors;
    }
}
