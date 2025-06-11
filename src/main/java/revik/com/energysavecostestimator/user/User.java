package revik.com.energysavecostestimator.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import revik.com.energysavecostestimator.config.security.OAuth2UserInfo;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private String providerId;

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String region;

    public static User fromOAuth(OAuth2UserInfo info, String provider) {
        User u = new User();
        u.setProvider(provider);
        u.setProviderId(info.getId());
        u.setFirstName(info.getFirstName());
        u.setLastName(info.getLastName());
        u.setEmail(info.getEmail());
        return u;
    }

    public void updateFromOAuth(OAuth2UserInfo info) {
        String newEmail = info.getEmail();
        if (newEmail != null && !newEmail.equals(this.email)) {
            this.email = newEmail;
        }

        String newFirst = info.getFirstName();
        if (newFirst != null && !newFirst.equals(this.firstName)) {
            this.firstName = newFirst;
        }

        String newLast = info.getLastName();
        if (newLast != null && !newLast.equals(this.lastName)) {
            this.lastName = newLast;
        }
    }

    public boolean isProfileComplete() {
        return firstName != null
                && lastName  != null
                && email     != null
                && gender    != null
                && region    != null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}