package mg.marketmanagement.categoryservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.categoryservice.dto.CategoryMapper;
import mg.marketmanagement.categoryservice.dto.CategoryRequest;
import mg.marketmanagement.categoryservice.model.Category;
import mg.marketmanagement.categoryservice.repository.CategoryRepository;
import mg.marketmanagement.categoryservice.utils.Response;
import mg.marketmanagement.categoryservice.validator.CategoryValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public Response add(CategoryRequest request) {
        List<String> errors= CategoryValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("same request not valid");
            generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "same field not valid"
            );
        }
        if(categoryRepository.existsByCode(request.getCode())){
            log.error("category already exist on the database!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "category already exist on the database please try again!"
            );
        }
        Category category= categoryMapper.mapToCategory(request);
        categoryRepository.save(category);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{code}")
                .buildAndExpand("api/category/get/"+category.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "category", categoryMapper.mapToCategoryResponse(category)
                ),
                "new category added succesfully!"
        );
    }

    @Override
    public Response getByCode(String code) {
        Optional<Category> category= categoryRepository.findByCode(code);
        if(category.isEmpty()){
            log.error("category don't exist on the database!");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "category with the code: "+code+" doesn't exist on the database!"
            );
        }
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "category", categoryMapper.mapToCategoryResponse(category.get())
                ),
                "category getted successfully!"
        );
    }

    @Override
    public Response all() {
        return null;
    }

    @Override
    public Response delete(String code) {
        if(categoryRepository.findByCode(code).isEmpty()){
            log.error("category with the code {} doesn't exist on the database ",code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "category with the code: "+code+" doesn't exist on the database"
            );
        }
        categoryRepository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "category with the code: "+code+" deleted successfully!"
        );
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .data(data)
                .location(location)
                .message(message)
                .build();
    }
}
