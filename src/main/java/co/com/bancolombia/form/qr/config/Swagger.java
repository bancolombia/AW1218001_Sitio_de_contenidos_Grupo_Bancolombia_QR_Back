package co.com.bancolombia.form.qr.config;

import co.com.bancolombia.form.qr.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Swagger {


    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title(Constant.PROJECT_NAME)
                .version(Constant.PROJECT_VERSION)
                .description(Constant.PROJECT_DESCRIPTION)
                .termsOfService("https://www.bancolombia.com/personas")
                .contact(new Contact().name("Bancolombia").url("https://www.bancolombia.com/personas"))
        );
    }

}