package mg.marketmanagement.entrepriseservice.microservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LineCommand {
    private String code;
    private Article article;
    private BigDecimal quantity;
    private BigDecimal price;
}
