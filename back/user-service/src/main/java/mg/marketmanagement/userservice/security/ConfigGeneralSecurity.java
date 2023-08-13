package mg.marketmanagement.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class ConfigGeneralSecurity {
    @Bean
    public JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter(){
        return new JwtGrantedAuthoritiesConverter();
    }
}
