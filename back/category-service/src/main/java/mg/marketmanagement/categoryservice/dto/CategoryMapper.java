package mg.marketmanagement.categoryservice.dto;

import mg.marketmanagement.categoryservice.model.Category;

public interface CategoryMapper {
    Category mapToCategory(CategoryRequest request);
    CategoryResponse mapToCategoryResponse(Category category);
}
