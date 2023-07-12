package mg.marketmanagement.articleservice.service;

import mg.marketmanagement.articleservice.dto.ArticleRequest;
import mg.marketmanagement.articleservice.utils.Response;
import org.json.JSONException;

public interface ArticleService {
    Response add(ArticleRequest request) throws JSONException;
    Response getByCode(String codeArticle) throws JSONException;
    Response all();
    Response delete(String codeArticle);
}
