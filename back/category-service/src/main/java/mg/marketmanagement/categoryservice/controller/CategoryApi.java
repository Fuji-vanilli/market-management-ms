package mg.marketmanagement.categoryservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.categoryservice.dto.CategoryRequest;
import mg.marketmanagement.categoryservice.service.CategoryService;
import mg.marketmanagement.userservice.Utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static mg.marketmanagement.categoryservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class CategoryApi implements CategoryController{
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<Response> add(CategoryRequest request) {
        return ResponseEntity.ok(categoryService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(categoryService.getByCode(code));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(categoryService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(categoryService.delete(code));
    }
}
