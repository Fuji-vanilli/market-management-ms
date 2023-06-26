package mg.marketmanagement.billing.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.billing.service.dto.User;

import java.time.LocalDateTime;
import java.util.Date;

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
    @Transient
    private User user;

}
