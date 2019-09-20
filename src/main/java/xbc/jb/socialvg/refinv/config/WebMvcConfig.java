package xbc.jb.socialvg.refinv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Register resource handler for CSS and JS
		registry.addResourceHandler("/css/**")
				.addResourceLocations("classpath:/static/css/");
	}
}
