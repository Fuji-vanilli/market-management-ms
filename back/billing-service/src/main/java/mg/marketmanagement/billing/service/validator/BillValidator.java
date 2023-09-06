package mg.marketmanagement.billing.service.validator;

import mg.marketmanagement.billing.service.dto.BillRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BillValidator {
    public static List<String> validate(BillRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getDetailProduct())){
            errors.add("detail of product required!!!");
        }/*
        if(!Objects.isNull(request.getEmailUser())){
            errors.add("email of user required required!!!");
        }*/
        if(Objects.isNull(request.getMethodPayement())){
            errors.add("method of paiement required!!!");
        }
        return  errors;
    }
}
