package revik.com.energysavecostestimator.config.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import revik.com.energysavecostestimator.user.User;
import revik.com.energysavecostestimator.user.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) {
        OAuth2User oAuth2User = super.loadUser(request);
        String regId = request.getClientRegistration().getRegistrationId();
        OAuth2UserInfo info = OAuth2UserInfoFactory.get(regId, oAuth2User.getAttributes());

        User user = userRepository.findByProviderAndProviderId(regId, info.getId())
                .orElseGet(() -> userRepository.save(User.fromOAuth(info, regId)));

        user.updateFromOAuth(info);
        userRepository.save(user);

        return new AppUserPrincipal(user, oAuth2User.getAttributes());
    }


}
