package mg.marketmanagement.userservice.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "jwt.auth.converter")
@Component
public class JwtAuthConverterProperties {
    private String resourceId;
    private String principalAttribute;
}
