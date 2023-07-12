package mg.marketmanagement.linecommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.marketmanagement.linecommandservice.dto.Article;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "line-command")
public class LineCommand {
    private String id;
    private String code;
    private String codeArticle;
    private BigDecimal quantity;
    private BigDecimal price;
    @Transient
    private Article article;
}
