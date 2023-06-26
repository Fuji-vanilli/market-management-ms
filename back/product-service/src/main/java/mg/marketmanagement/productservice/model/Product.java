package mg.marketmanagement.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.productservice.dto.Category;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String codeCategory;
    private Double price;
    private String description;
    private String photo;
    @Transient
    private Category category;

}
