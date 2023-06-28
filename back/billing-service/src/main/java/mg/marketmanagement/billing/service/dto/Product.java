package mg.marketmanagement.billing.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Product {
    private String name;
    private Double price;
    private String category;
    private int quantity;
    private double subTotal;
}
