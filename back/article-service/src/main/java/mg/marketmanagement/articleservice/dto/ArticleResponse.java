package mg.marketmanagement.articleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ArticleResponse {
    private String id;
    private Instant creationDate;
    private Instant lastModifiedDate;
    private String codeArticle;
    private String designation;
    private BigDecimal unitPriceHT;
    private BigDecimal rateTVA;
    private BigDecimal unitPriceTTC;
    private String photo;
    private Category category;
}
