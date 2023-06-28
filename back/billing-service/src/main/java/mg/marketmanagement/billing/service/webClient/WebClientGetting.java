package mg.marketmanagement.billing.service.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.billing.service.dto.Product;
import mg.marketmanagement.billing.service.dto.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetting {
    private final WebClient.Builder webClient;

    public User getUser(String email) throws JSONException {
        CompletableFuture<String> dataFuture= webClient.build().get()
                .uri("http://localhost:9400/api/user/get/"+email)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        String dataBrute= "";
        try{
            dataBrute= dataFuture.get();
        }catch (InterruptedException | ExecutionException e){
            log.error("error to fetch data on the microservice user");
            throw new RuntimeException("error to fetch data on the microservice user");
        }
        JSONObject dataJson= new JSONObject(dataBrute);
        JSONObject data= dataJson.getJSONObject("data").getJSONObject("user");

        return User.builder()
                .firstname(data.getString("firstname"))
                .lastname(data.getString("lastname"))
                .contact(data.getString("contact"))
                .build();
    }
    public Product getProduct(String code){
        CompletableFuture<String> dataFuture= webClient.build().get()
                .uri("http://localhost:9420/api/product/getByCode/"+code)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();
        String dataBrute;
        try{
            dataBrute= dataFuture.get();
        }catch (InterruptedException | ExecutionException e){
            log.error("error to fetch to the microservice product");
            throw new RuntimeException("error to fetch to the microservice product");
        }

        JSONObject data= new JSONObject();
        Product product= new Product();
        try {
            JSONObject dataJson= new JSONObject(dataBrute);
            data= dataJson.getJSONObject("data").getJSONObject("product");

            product.setName(data.getString("name"));
            product.setPrice(data.getDouble("price"));
            product.setCategory(data.getJSONObject("category").getString("name"));
        } catch (JSONException e) {
            log.error("error to convert json object");
            throw new RuntimeException("error to convert json object");
        }
        return product;
    }
}
