package mg.marketmanagement.articleservice.validator;

import mg.marketmanagement.articleservice.dto.ArticleRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleValidator {
    public static List<String> validate(ArticleRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCodeArticle())){
            errors.add("code article required!!!");
        }
        if(Objects.isNull(request.getDesignation())){
            errors.add("designation required!!!");
        }
        if(Objects.isNull(request.getUnitPriceHT())){
            errors.add("unit price required!!!");
        }
        if(Objects.isNull(request.getRateTVA())){
            errors.add("rate TVA required!!!");
        }
        if(Objects.isNull(request.getPhoto())){
            errors.add("photo required!!!");
        }
        if(Objects.isNull(request.getCodeCategory())){
            errors.add("id category required!!!");
        }
        return  errors;
    }
}
