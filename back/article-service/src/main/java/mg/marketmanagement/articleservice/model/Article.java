package mg.marketmanagement.articleservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.articleservice.dto.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "article")
public class Article {
    @Id
    private String id;
    private Instant creationDate;
    private Instant lastModifiedDate;
    private String codeArticle;
    private String designation;
    private BigDecimal unitPriceHT;
    private BigDecimal rateTVA;
    private BigDecimal unitPriceTTC;
    private String photo;
    private String codeCategory;
    @Transient
    private Category category;

}
