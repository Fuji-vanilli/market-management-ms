package mg.marketmanagement.commandeclientservice.validator;


import mg.marketmanagement.commandeclientservice.dto.CommandClientRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandClientValidator {
    public static List<String> validate(CommandClientRequest request){
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
