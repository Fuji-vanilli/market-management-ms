package mg.marketmanagement.clientservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.clientservice.dto.CommandClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.concurrent.CompletableFuture;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetter {
    private final WebClient.Builder webClient;

    public List<CommandClient> getCommandClient(List<String> codes){
        CompletableFuture<List<CommandClient>> dataFuture= webClient.build().get()
                .uri("http://localhost:9460/api/command-client/byCodes",
                        uriBuilder -> uriBuilder.queryParam("codes", codes).build())
                .retrieve()
                .bodyToFlux(CommandClient.class)
                .collectList()
                .toFuture();

        return dataFuture.join();
    }
}
