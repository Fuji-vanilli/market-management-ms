package mg.marketmanagement.entrepriseservice.microservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private Category category;
}
