package mg.marketmanagement.categoryservice.service;

import mg.marketmanagement.categoryservice.dto.CategoryRequest;
import mg.marketmanagement.userservice.Utils.Response;

public interface CategoryService {
    Response add(CategoryRequest request);
    Response getByCode(String code);
    Response all();
    Response delete(String code);
}
