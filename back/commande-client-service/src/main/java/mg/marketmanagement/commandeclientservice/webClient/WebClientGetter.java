package mg.marketmanagement.commandeclientservice.webClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.commandeclientservice.dto.Article;
import mg.marketmanagement.commandeclientservice.dto.Category;
import mg.marketmanagement.commandeclientservice.dto.LineCommand;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetter {

    private final WebClient.Builder webClient;

    public List<LineCommand> getLineCommand(List<String> codes) throws JSONException {
        CompletableFuture<List<LineCommand>> dataFuture= webClient.build().get()
                .uri("http://localhost:9470/api/line-command/byIds",
                        uriBuilder -> uriBuilder.queryParam("codes", codes).build())
                .retrieve()
                .bodyToFlux(LineCommand.class)
                .collectList()
                .toFuture();

        return dataFuture.join();
    }
}
