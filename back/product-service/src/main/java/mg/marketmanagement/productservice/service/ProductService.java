package mg.marketmanagement.productservice.service;

import mg.marketmanagement.productservice.dto.ProductRequest;
import mg.marketmanagement.userservice.Utils.Response;
import org.json.JSONException;

public interface ProductService {
    Response add(ProductRequest request) throws JSONException;
    Response getByName(String name) throws JSONException;
    Response getByCode(String code) throws JSONException;
    Response all();
    Response delete(String code);
}
