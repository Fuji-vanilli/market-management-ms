package mg.marketmanagement.fournisseurservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LineCommandFournisseur {
    private Article article;
    private BigDecimal quantity;
    private BigDecimal price;
}
