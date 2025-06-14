package revik.com.energysavecostestimator.user.profile;

import revik.com.energysavecostestimator.user.User;

public record UserProfileDto(
        Long id,
        String provider,
        String providerId,
        String firstName,
        String lastName,
        String email,
        String gender,
        String region
) {
    public static UserProfileDto from(User user) {
        return new UserProfileDto(
                user.getId(),
                user.getProvider(),
                user.getProviderId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGender(),
                user.getRegion()
        );
    }
}
