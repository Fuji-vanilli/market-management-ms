package mg.marketmanagement.productservice.utils.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.productservice.dto.ProductRequest;
import mg.marketmanagement.productservice.service.ProductService;
import mg.marketmanagement.userservice.Utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static mg.marketmanagement.productservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class ProductApi implements ProductController{
    private final ProductService productService;

    @Override
    public ResponseEntity<Response> add(ProductRequest request) throws JSONException {
        return ResponseEntity.ok(productService.add(request));
    }

    @Override
    public ResponseEntity<Response> getByName(String name) throws JSONException {
        return ResponseEntity.ok(productService.getByName(name));
    }

    @Override
    public ResponseEntity<Response> getByCode(String code) throws JSONException {
        return ResponseEntity.ok(productService.getByCode(code));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(productService.all());
    }

    @Override
    public ResponseEntity<Response> deleteByCode(String code) {
        return ResponseEntity.ok(productService.delete(code));
    }
}
