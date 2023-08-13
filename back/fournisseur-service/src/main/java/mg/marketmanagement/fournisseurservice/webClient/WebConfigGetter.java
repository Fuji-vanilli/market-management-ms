package mg.marketmanagement.fournisseurservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.fournisseurservice.dto.CommandFournisseur;
import mg.marketmanagement.fournisseurservice.dto.LineCommandFournisseur;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebConfigGetter {
    private final WebClient.Builder webClient;

    public List<CommandFournisseur> getCommandFournisseurs(List<String> codes) throws JSONException {
        CompletableFuture<List<CommandFournisseur>> dataFuture= webClient.build().get()
                .uri("http://localhost:9480/api/command-fournisseur/byCodes",
                        uriBuilder -> uriBuilder.queryParam("codes", codes).build())
                .retrieve()
                .bodyToFlux(CommandFournisseur.class)
                .collectList()
                .toFuture();

        return dataFuture.join();
    }
}
