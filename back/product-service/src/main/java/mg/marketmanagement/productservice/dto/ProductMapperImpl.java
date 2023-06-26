package mg.marketmanagement.productservice.dto;

import mg.marketmanagement.productservice.model.Product;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapperImpl implements ProductMapper{
    @Override
    public Product mapToProduct(ProductRequest request) {
        return Product.builder()
                .code(request.getCode())
                .name(request.getName())
                .price(request.getPrice())
                .codeCategory(request.getCodeCategory())
                .photo(request.getPhoto())
                .description(request.getDescription())
                .build();
    }
    @Override
    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .photo(product.getPhoto())
                .description(product.getDescription())
                .code(product.getCode())
                .category(product.getCategory())
                .build();
    }
}
