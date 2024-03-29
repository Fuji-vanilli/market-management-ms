package mg.marketmanagement.billing.service.dto;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BillResponse {
    private Long id;
    private String uuid;
    private String methodPayement;
    private Double total;
    private String detailProduct;
    private LocalDateTime date;
    private Double subTotal;
    private User user;
    private Map<Product, Integer> products;
}
