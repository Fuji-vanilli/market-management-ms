package mg.marketmanagement.articleservice.dto;

import mg.marketmanagement.articleservice.model.Article;

public interface ArticleMapper {
    Article mapToArticle(ArticleRequest request);
    ArticleResponse mapToArticleResponse(Article article);
}
