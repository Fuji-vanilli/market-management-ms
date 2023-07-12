package mg.marketmanagement.articleservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.articleservice.dto.Category;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetter {

    private final WebClient.Builder webClient;

    public Category getCategory(String code) throws JSONException {
        CompletableFuture<String> dataFuture= webClient.build().get()
                .uri("http://localhost:9410/api/category/get/"+code)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        String dataExtract= "";
        try{
            dataExtract= dataFuture.get();
            log.info("ms category getting successfully!!!");
        }catch (InterruptedException | ExecutionException e){
            log.error("error to try to fetch microservice category");
            throw new RuntimeException("error to fetch to the microservice category!");
        }

        JSONObject dataJson= new JSONObject(dataExtract);
        JSONObject data= dataJson.getJSONObject("data").getJSONObject("category");

        return Category.builder()
                .code(data.getString("code"))
                .designation(data.getString("name"))
                .build();
    }
}
