package mx.clicktwocell.simulador.configuration;


import mx.clicktwocell.simulador.constants.ApiValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private ApiValues apiValues;

	/**
	 * Bean para escanear las APIs existentes y generar swagger-ui.
	 * 
	 * @return product api
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(Boolean.FALSE).select()
				.apis(RequestHandlerSelectors.basePackage(apiValues.getBasePackage())).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}

	/**
	 * Builder de the ApiInfo.
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(apiValues.getSwaggerTitle()).description(apiValues.getSwaggerDescription())
				.version(apiValues.getSwaggerVersion()).contact(new Contact(apiValues.getSwaggerDeveloperName(),
						apiValues.getSwaggerContact(), apiValues.getSwaggerDeveloperEmail()))
				.build();
	}
}