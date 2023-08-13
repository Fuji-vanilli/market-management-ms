package mg.marketmanagement.commandefournisseurservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.commandefournisseurservice.dto.LineCommand;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetter {
    private final WebClient.Builder webClient;

    public List<LineCommand> getLineCommand(List<String> codes){
        CompletableFuture<List<LineCommand>> dataFuture= webClient.build().get()
                .uri("http://localhost:9470/api/line-command/byCodes",
                        uriBuilder -> uriBuilder.queryParam("codes", codes).build())
                .retrieve()
                .bodyToFlux(LineCommand.class)
                .collectList()
                .toFuture();

        return dataFuture.join();
    }

}
