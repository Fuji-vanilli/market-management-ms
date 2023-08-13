package mg.marketmanagement.entrepriseservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.entrepriseservice.microservice.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebClientGetter {
    private final WebClient.Builder webClient;

    public List<Client> getClients(List<String> codes){
        CompletableFuture<List<Client>> dataFuture= webClient.build().get()
                .uri("http://localhost:9440/api/client/byCodes",
                        uriBuilder -> uriBuilder.queryParam("codes", codes).build())
                .retrieve()
                .bodyToFlux(Client.class)
                .collectList()
                .toFuture();

        return dataFuture.join();
    }

    public List<Fournisseur> getFournisseurs(List<String> codes){
        CompletableFuture<List<Fournisseur>> dataFuture= webClient.build().get()
                .uri("http://localhost:9490/api/fournisseur/byCodes",
                        uriBuilder -> uriBuilder.queryParam("codes", codes).build())
                .retrieve()
                .bodyToFlux(Fournisseur.class)
                .collectList()
                .toFuture();

        return dataFuture.join();
    }
}
