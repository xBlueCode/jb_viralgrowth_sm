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

	private PathProperties path;

	public PaginationProperties getPagination() {
		return pagination;
	}

	public void setPagination(PaginationProperties paginationProperties) {
		this.pagination = paginationProperties;
	}

	public PathProperties getPath() {
		return path;
	}

	public void setPath(PathProperties path) {
		this.path = path;
	}
}
