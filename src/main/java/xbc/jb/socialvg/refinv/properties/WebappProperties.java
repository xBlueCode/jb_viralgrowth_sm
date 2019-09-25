package xbc.jb.socialvg.refinv.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "webapp")
@Data
public class WebappProperties {

	private PaginationProperties pagination;

	public PaginationProperties getPaginationProperties() {
		return pagination;
	}

	public void setPaginationProperties(PaginationProperties paginationProperties) {
		this.pagination = paginationProperties;
	}
}
