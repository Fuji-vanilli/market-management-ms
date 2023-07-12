package mg.marketmanagement.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Article {
    private String codeArticle;
    private String designation;
    private BigDecimal unitPriceHT;
    private BigDecimal rateTVA;
    private BigDecimal unitPriceTTC;
    private String photo;
    private String codeCategory;
}
