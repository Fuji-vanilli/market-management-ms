package mg.marketmanagement.categoryservice.dto;

import mg.marketmanagement.categoryservice.model.Category;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryMapperImpl implements CategoryMapper{
    @Override
    public Category mapToCategory(CategoryRequest request) {
        return Category.builder()
                .code(request.getCode())
                .name(request.getName())
                .build();
    }

    @Override
    public CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .code(category.getCode())
                .name(category.getName())
                .build();
    }
}
