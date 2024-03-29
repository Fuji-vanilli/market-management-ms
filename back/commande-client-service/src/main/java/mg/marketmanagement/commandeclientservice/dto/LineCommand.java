package mg.marketmanagement.commandeclientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.commandeclientservice.dto.Article;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LineCommand {
    private Article article;
    private BigDecimal quantity;
    private BigDecimal price;

}
