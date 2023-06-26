package mg.marketmanagement.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.productservice.dto.Category;
import mg.marketmanagement.productservice.dto.ProductMapper;
import mg.marketmanagement.productservice.dto.ProductRequest;
import mg.marketmanagement.productservice.model.Product;
import mg.marketmanagement.productservice.repository.ProductRepository;
import mg.marketmanagement.productservice.validator.ProductValidator;
import mg.marketmanagement.productservice.webClient.WebClientGetting;
import mg.marketmanagement.userservice.Utils.Response;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final WebClientGetting webClientCategory;
    @Override
    public Response add(ProductRequest request) throws JSONException {
        List<String> errors= ProductValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("same field required!");
            generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "same field required!!!"
            );
        }
        if(productRepository.existsByCodeAndName(request.getCode(), request.getName())){
            log.error("product already exist on the database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "product exist", productRepository.findByName(request.getName())
                    ),
                    "product with the code: "+request.getCode()+" and name: "+request.getName()+" already exist on database!"
            );
        }
        Product product= productMapper.mapToProduct(request);
        Category category= webClientCategory.getCategory(product.getCodeCategory());
        product.setCategory(category);

        productRepository.save(product);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{name}")
                .buildAndExpand("api/product/getByCode/"+product.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "product", productMapper.mapToProductResponse(product)
                ),
                "new product added successfully!"

        );
    }

    @Override
    public Response getByName(String name) throws JSONException {
        if(productRepository.findByName(name).isEmpty()){
            log.error("the product with the name {} doesn't exist on the database!", name);
            return generateResponse(
                    HttpStatus.OK,
                    null,
                    null,
                    "the product with the name: "+name+" doesn't exist on the database!"
            );
        }
        Product product= productRepository.findByName(name).orElse(null);
        assert product != null;
        product.setCategory(webClientCategory.getCategory(product.getCodeCategory()));
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "product", productMapper.mapToProductResponse(product)
                ),
                "product with the name: "+name+" getting succesfully!"
        );
    }

    @Override
    public Response getByCode(String code) throws JSONException {
        if(productRepository.findByCode(code).isEmpty()){
            log.error("the product with the name {} doesn't exist on the database!", code);
            return generateResponse(
                    HttpStatus.OK,
                    null,
                    null,
                    "the product with the name: "+code+" doesn't exist on the database!"
            );
        }
        Product product= productRepository.findByCode(code).orElse(null);
        assert product != null;
        product.setCategory(webClientCategory.getCategory(product.getCodeCategory()));
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "product", productMapper.mapToProductResponse(product)
                ),
                "product with the name: "+code+" getting succesfully!"
        );
    }

    @Override
    public Response all() {
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "products", productRepository.findAll().stream()
                                .map(productMapper::mapToProductResponse)
                                .toList()
                ),
                "all product getting succesfully!"
        );
    }

    @Override
    public Response delete(String code) {
        if(productRepository.findByCode(code).isEmpty()){
            log.error("the product with the code {} doesn't exist on the database!", code);
            return generateResponse(
                    HttpStatus.OK,
                    null,
                    null,
                    "the product with the code: "+code+" doesn't exist on the database!"
            );
        }
        productRepository.deleteByCode(code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "product deleted succesfully!"
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
