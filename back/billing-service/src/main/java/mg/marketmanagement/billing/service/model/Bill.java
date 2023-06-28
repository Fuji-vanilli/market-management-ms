package mg.marketmanagement.billing.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.billing.service.dto.Product;
import mg.marketmanagement.billing.service.dto.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String methodPayement;
    private Double total;
    private String detailProduct;
    private String emailUser;
    private LocalDateTime date;
    private Double subTotal;
    @Transient
    private Map<String, Integer> productsCode;
    @Transient
    private User user;
    @Transient
    private Map<Product, Integer> products;

}
