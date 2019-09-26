package xbc.jb.socialvg.refinv.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

//@Component
//@Data // lombok
@Entity
//@Table(name = "images")
//@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String path;

	private boolean isProfile;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isProfile() {
		return isProfile;
	}

	public void setProfile(boolean profile) {
		isProfile = profile;
	}
}
