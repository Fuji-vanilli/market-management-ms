package mg.marketmanagement.linecommandservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LineCommandRequest {
    private String code;
    private String codeArticle;
    private BigDecimal quantity;
}
