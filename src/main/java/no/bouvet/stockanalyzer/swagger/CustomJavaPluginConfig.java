package no.bouvet.stockanalyzer.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by extrna on 04.11.2014.
 */
@Configuration
@EnableWebMvc
@EnableSwagger
public class CustomJavaPluginConfig {
    
    @Value("${version}")
    private String apiVersion;
    
    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean //Don't forget the @Bean annotation
    public SwaggerSpringMvcPlugin customImplementation(){
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .apiVersion(apiVersion)
                .includePatterns(".*");
    }

    // TODO vurder å bytte til properties tilsvarende som for version
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "CHARGEN REST API", // tittel
                "Beskrivelse", // beskrivelse
                "", // Terms of service
                "", // epost
                "", // Tittel for neste punkt Eks Foo -> <a href="http://localhost:8080/bar">Foo</a>
                "" // uri eks /bar Eks <a href="http://localhost:8080/bar">Foo</a>
        );
        return apiInfo;
    }
}