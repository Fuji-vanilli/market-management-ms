package mg.marketmanagement.commandefournisseurservice.validator;


import mg.marketmanagement.commandefournisseurservice.dto.CommandFournisseurRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandFournisseurValidator {
    public static List<String> validate(CommandFournisseurRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCode())){
            errors.add("code required!");
        }
        if(Objects.isNull(request.getCodeLineCommands())){
            errors.add("code line commands required!");
        }
        return errors;
    }
}
