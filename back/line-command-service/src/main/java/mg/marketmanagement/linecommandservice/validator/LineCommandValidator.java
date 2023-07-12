package mg.marketmanagement.linecommandservice.validator;


import mg.marketmanagement.linecommandservice.dto.LineCommandRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LineCommandValidator {
    public static List<String> validate(LineCommandRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCode())){
            errors.add("code required!");
        }
        if(Objects.isNull(request.getQuantity())){
            errors.add("quantity required!");
        }
        if(Objects.isNull(request.getCodeArticle())){
            errors.add("code article required!");
        }
        return errors;
    }
}
