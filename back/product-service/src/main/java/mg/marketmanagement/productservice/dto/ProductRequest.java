package mg.marketmanagement.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductRequest {
    private String code;
    private String name;
    private String codeCategory;
    private Double price;
    private String description;
    private String photo;
}

