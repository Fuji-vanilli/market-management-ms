package mg.marketmanagement.billing.service.webClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientBilling {
    @Bean
    public WebClient.Builder webClientBill(){
        return WebClient.builder();
    }
}
