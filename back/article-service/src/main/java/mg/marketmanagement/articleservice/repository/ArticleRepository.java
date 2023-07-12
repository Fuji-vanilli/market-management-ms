package mg.marketmanagement.articleservice.repository;

import mg.marketmanagement.articleservice.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ArticleRepository extends MongoRepository<Article, String> {
    Optional<Article> findByCodeArticle(String codeArticle);
    boolean existsByCodeArticleAndDesignation(String codeArticle, String designation);
    void deleteByCodeArticle(String codeArticle);

}
