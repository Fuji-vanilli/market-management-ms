package mg.marketmanagement.linecommandservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LineCommandResponse {
    private String id;
    private String code;
    private BigDecimal quantity;
    private BigDecimal price;
    private Article article;
}
