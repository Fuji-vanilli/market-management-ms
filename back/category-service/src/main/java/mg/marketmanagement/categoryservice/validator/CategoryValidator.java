package mg.marketmanagement.categoryservice.validator;

import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import mg.marketmanagement.categoryservice.dto.CategoryRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryValidator {
    public static List<String> validate(CategoryRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCode())){
            errors.add("code required!");
        }
        if(Objects.isNull(request.getName())){
            errors.add("name required!");
        }
        return errors;
    }
}
