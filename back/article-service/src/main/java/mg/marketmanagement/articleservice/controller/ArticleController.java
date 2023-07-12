package mg.marketmanagement.articleservice.controller;

import mg.marketmanagement.articleservice.dto.ArticleRequest;
import mg.marketmanagement.articleservice.utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ArticleController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody ArticleRequest request) throws JSONException;
    @GetMapping("get/{codeArticle}")
    ResponseEntity<Response> getByCode(@PathVariable String codeArticle) throws JSONException;
    @GetMapping("all")
    ResponseEntity<Response> getAll();
    @DeleteMapping("delete/{codeArticle}")
    ResponseEntity<Response> delete(@PathVariable String codeArticle);
}
