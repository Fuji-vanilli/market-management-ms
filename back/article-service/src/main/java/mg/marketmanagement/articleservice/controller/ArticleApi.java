package mg.marketmanagement.articleservice.controller;

import lombok.RequiredArgsConstructor;
import mg.marketmanagement.articleservice.dto.ArticleRequest;
import mg.marketmanagement.articleservice.service.ArticleService;
import mg.marketmanagement.articleservice.utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static mg.marketmanagement.articleservice.utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class ArticleApi implements ArticleController{
    private final ArticleService articleService;
    @Override
    public ResponseEntity<Response> add(ArticleRequest request) throws JSONException {
        return ResponseEntity.ok(articleService.add(request));
    }

    @Override
    public ResponseEntity<Response> getByCode(String codeArticle) throws JSONException {
        return ResponseEntity.ok(articleService.getByCode(codeArticle));
    }

    @Override
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(articleService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String codeArticle) {
        return ResponseEntity.ok(articleService.delete(codeArticle));
    }
}
