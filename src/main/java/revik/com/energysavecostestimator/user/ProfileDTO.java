package revik.com.energysavecostestimator.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProfileDTO {
    private String provider;
    private String providerId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String region;

    public ProfileDTO() {}

    public ProfileDTO(String provider,
                      String providerId,
                      String firstName,
                      String lastName,
                      String email,
                      String gender,
                      String region) {
        this.provider = provider;
        this.providerId = providerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.region = region;
    }

    public static ProfileDTO from(User u) {
        return new ProfileDTO(
                u.getProvider(),
                u.getProviderId(),
                u.getFirstName(),
                u.getLastName(),
                u.getEmail(),
                u.getGender(),
                u.getRegion()
        );
    }
}
