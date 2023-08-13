package mg.marketmanagement.categoryservice.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static mg.marketmanagement.categoryservice.utils.Root.APP_ROOT;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("gestion de stock")
                        .title("gestion de stock")
                        .build())
                .groupName("REST Api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("mg.marketmanagement.categoryservice"))
                .paths(PathSelectors.ant(APP_ROOT+"/**"))
                .build();
    }
}
