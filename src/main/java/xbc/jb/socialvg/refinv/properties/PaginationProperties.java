package xbc.jb.socialvg.refinv.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotNull;

//@Configuration
//@PropertySource("classpath:application.yml")
//@ConfigurationProperties(prefix = "pagination")
//@Data
public class PaginationProperties {

	private int pageSize;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
