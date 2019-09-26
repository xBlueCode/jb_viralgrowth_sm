package xbc.jb.socialvg.refinv.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Component
@Data // lombok
@Entity
//@Table(name = "photo")
@NoArgsConstructor
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String path;

	private boolean profile;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean getProfile() {
		return profile;
	}

	public void setProfile(boolean profile) {
		profile = profile;
	}
}
