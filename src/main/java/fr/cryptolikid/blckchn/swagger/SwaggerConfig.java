package fr.cryptolikid.blckchn.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	/** API_TITLE */
	private static final String API_TITLE = "Cryptolikid - BLCKCHN";

	/** API_VERSION */
	private static final String API_VERSION = SwaggerConfig.class.getPackage().getImplementationVersion() != null ?
			SwaggerConfig.class.getPackage().getImplementationVersion() : "1.0";

	/** CONTACT_NAME */
	private static final String CONTACT_NAME = "DSIGD";

	/** CONTACT_URL */
	private static final String CONTACT_URL = "https://damntools.fr";

	/** CONTACT_EMAIL */
	private static final String CONTACT_EMAIL = "contact.blckchn@cryptolikid.com";


	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/.*"))
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title(API_TITLE)
				.description("\"Spring Boot REST API for Online Store\"")
				.version(API_VERSION)
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.contact(new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}