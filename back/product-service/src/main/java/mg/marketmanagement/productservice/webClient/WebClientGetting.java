package mg.marketmanagement.productservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.productservice.dto.Category;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebClientGetting {
    private final WebClient.Builder webClient;

    public Category getCategory(String code) throws JSONException {
        CompletableFuture<String> dataFuture= webClient.build().get()
                .uri("http://localhost:9410/api/category/get/"+code)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        String dataBrute;
        try{
            dataBrute= dataFuture.get();
        }catch (InterruptedException | ExecutionException e){
            log.error("error to try to fetch category on the microservice");
            throw new RuntimeException("error to fetch on the microservice category!!!");
        }
        JSONObject dataJson= new JSONObject(dataBrute);
        JSONObject data= dataJson.getJSONObject("data").getJSONObject("category");

        return Category.builder()
                .code(data.getString("code"))
                .name(data.getString("name"))
                .build();
    }
}
