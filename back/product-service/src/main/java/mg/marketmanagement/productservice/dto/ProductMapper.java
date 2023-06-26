package mg.marketmanagement.productservice.dto;

import mg.marketmanagement.productservice.model.Product;

public interface ProductMapper {
    Product mapToProduct(ProductRequest request);
    ProductResponse mapToProductResponse(Product product);
}
