package xbc.jb.socialvg.refinv.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

/**
 *
 */

@Component
@Data // lombok
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Must not be empty")
    @Column(unique = true)
    @Length(min = 4, max = 26, message = "Length must be between 4 and 26 !")
    private String username;

    @Column(unique = true)
    @NotEmpty(message = "Must not be empty")
    private String email;

    @NotEmpty(message = "Must not be empty")
    @Length(min = 4, message = "Length must be bigger than 4 !")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "IMAGE_ID")
    )
    private Set<Image> images;

/*    @JoinColumn(name = "image_id")
    private Image image;*/

//    @Length(min=4, max=12, message = "Length must be between 4 and 12 !")
    private String iCode;

    private Long iId;

    private String rCode;

    private Double score;

    private Long invitees;

    private Long direct;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public String getrCode() {
        return rCode;
    }

    public void setrCode(String rCode) {
        this.rCode = rCode;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Long getDirect() {
        return direct;
    }

    public void setDirect(Long direct) {
        this.direct = direct;
    }

    public void addScore(Double value)
    {
        this.score += value;
    }

    public Long getInvitees() {
        return invitees;
    }

    public void setInvitees(Long invitees) {
        this.invitees = invitees;
    }

    public void addInvitee(Long val)
    {
        this.invitees += val;
    }

    public void addDirect(Long n) {
        this.direct += n;
    }

    public Long getiId() {
        return iId;
    }

    public void setiId(Long iId) {
        this.iId = iId;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

/*    public Image getImage() {
        return image;
    }

    public void setId(Image image) {
        this.image = image;
    }*/
}
