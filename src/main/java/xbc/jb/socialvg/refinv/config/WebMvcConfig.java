package xbc.jb.socialvg.refinv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xbc.jb.socialvg.refinv.properties.WebappProperties;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private WebappProperties webappProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Register resource handler for CSS and JS
		registry.addResourceHandler("/css/**", "/images/**", "/**")
				.addResourceLocations(
						"classpath:/static/css/",
						"classpath:/images/",
						"file:/"
						//String.format("file:%s", webappProperties.getPath().getImageFolder()))
				).setCachePeriod(0);
	}
}
