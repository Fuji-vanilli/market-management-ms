package mg.marketmanagement.articleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.articleservice.dto.ArticleMapper;
import mg.marketmanagement.articleservice.dto.ArticleRequest;
import mg.marketmanagement.articleservice.dto.Category;
import mg.marketmanagement.articleservice.model.Article;
import mg.marketmanagement.articleservice.repository.ArticleRepository;
import mg.marketmanagement.articleservice.utils.Response;
import mg.marketmanagement.articleservice.validator.ArticleValidator;
import mg.marketmanagement.articleservice.webClient.WebClientGetter;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final WebClientGetter webClient;
    @Override
    public Response add(ArticleRequest request) throws JSONException {
        List<String> errors= ArticleValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("request not valid");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "more request not valid. Please try again!"

            );
        }

        if(articleRepository.existsByCodeArticleAndDesignation(request.getCodeArticle(), request.getDesignation())){
            log.error("the article with the code: {} is already exist on the database", request.getCodeArticle());
            return generateResponse(
                    HttpStatus.CONFLICT,
                    null,
                    Map.of(
                            "article", getByCode(request.getCodeArticle())
                    ),
                    "the article with the code: "+request.getCodeArticle()+" is already exist on the database"
            );
        }

        Article article= articleMapper.mapToArticle(request);
        article.setUnitPriceTTC(request.getUnitPriceHT().add(request.getUnitPriceHT().multiply(request.getRateTVA())));
        article.setCreationDate(Instant.now());
        article.setLastModifiedDate(Instant.now());

        Category category= webClient.getCategory(article.getCodeCategory());
        article.setCategory(category);

        articleRepository.save(article);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{codeArticle}")
                .buildAndExpand("api/article/get/"+article.getCodeArticle())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "article", articleMapper.mapToArticleResponse(article)
                ),
                "article added successfully!"
        );
    }

    @Override
    public Response getByCode(String codeArticle) throws JSONException {
        Optional<Article> article= articleRepository.findByCodeArticle(codeArticle);
        if(article.isEmpty()){
            log.error("Sorry! the article with the code: {} doesn't exist on the database", codeArticle);
            return generateResponse(
                    HttpStatus.NOT_FOUND,
                    null,
                    null,
                    "Sorry! the article with the code:"+codeArticle+" doesn't exist on the database"
            );
        }
        Category category= webClient.getCategory(article.get().getCodeCategory());
        article.get().setCategory(category);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "article", articleMapper.mapToArticleResponse(article.get())
                ),
                "article with the code: "+codeArticle+" getted successfully"
        );
    }

    @Override
    public Response all() {

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "articles", articleRepository.findAll().stream()
                                .map(articleMapper::mapToArticleResponse)
                                .toList()
                ),
                "all article getted successfully!"
        );
    }

    @Override
    public Response delete(String codeArticle) {

        if(articleRepository.findByCodeArticle(codeArticle).isEmpty()){
            log.error("the article with the code: "+codeArticle+" doesn't exist on the database!");
            return generateResponse(
                    HttpStatus.NOT_FOUND,
                    null,
                    null,
                    "the article with the code: "+codeArticle+" doesn't exist on the database!"
            );
        }

        articleRepository.deleteByCodeArticle(codeArticle);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "article with the code: "+codeArticle+" deleted successfully!"
        );
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .location(location)
                .data(data)
                .message(message)
                .build();
    }

}
