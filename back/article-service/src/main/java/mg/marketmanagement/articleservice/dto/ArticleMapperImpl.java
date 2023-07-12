package mg.marketmanagement.articleservice.dto;

import mg.marketmanagement.articleservice.model.Article;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleMapperImpl implements ArticleMapper{
    @Override
    public Article mapToArticle(ArticleRequest request) {
        return Article.builder()
                .codeArticle(request.getCodeArticle())
                .designation(request.getDesignation())
                .unitPriceHT(request.getUnitPriceHT())
                .unitPriceTTC(request.getUnitPriceTTC())
                .rateTVA(request.getRateTVA())
                .photo(request.getPhoto())
                .codeCategory(request.getCodeCategory())
                .build();
    }

    @Override
    public ArticleResponse mapToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .creationDate(article.getCreationDate())
                .lastModifiedDate(article.getLastModifiedDate())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .unitPriceHT(article.getUnitPriceHT())
                .unitPriceTTC(article.getUnitPriceTTC())
                .rateTVA(article.getRateTVA())
                .photo(article.getPhoto())
                .category(article.getCategory())
                .build();
    }
}
