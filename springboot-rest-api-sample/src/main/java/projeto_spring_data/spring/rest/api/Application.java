package projeto_spring_data.spring.rest.api;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(Application.class, args);
	 * }
	 * 
	 * @Override
	 * protected SpringApplicationBuilder configure(SpringApplicationBuilder
	 * builder) {
	 * return builder.sources(Application.class);
	 * }
	 */

	public static void main(String[] args) {
		configureApplication(new SpringApplicationBuilder()).run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
	}

}
