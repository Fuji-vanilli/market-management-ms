package mg.marketmanagement.linecommandservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.linecommandservice.dto.Article;
import mg.marketmanagement.linecommandservice.dto.Category;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetter {
    private final WebClient.Builder webClient;

    public Article getArticle(String code) throws JSONException {
        CompletableFuture<String> dataFuture= webClient.build().get()
                .uri("http://localhost:9450/api/article/get/"+code)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        String dataBrute;
        try{
            dataBrute= dataFuture.get();
        }catch (InterruptedException | ExecutionException e){
            log.error("error to fetch the microservice article");
            throw new RuntimeException("error to fetch the microservice article");
        }

        JSONObject dataJson= new JSONObject(dataBrute);
        JSONObject data= dataJson.getJSONObject("data").getJSONObject("article");
        JSONObject cat= data.getJSONObject("category");

        Category category= Category.builder()
                .code(cat.getString("code"))
                .name(cat.getString("designation"))
                .build();

        return Article.builder()
                .codeArticle(data.getString("codeArticle"))
                .category(category)
                .designation(data.getString("designation"))
                .unitPriceHT(BigDecimal.valueOf(data.getDouble("unitPriceHT")))
                .rateTVA(BigDecimal.valueOf(data.getDouble("rateTVA")))
                .unitPriceTTC(BigDecimal.valueOf(data.getDouble("unitPriceTTC")))
                .build();
    }
}
