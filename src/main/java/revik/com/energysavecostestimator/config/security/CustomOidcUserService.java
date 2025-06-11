package revik.com.energysavecostestimator.config.security;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import revik.com.energysavecostestimator.user.User;
import revik.com.energysavecostestimator.user.UserRepository;

@Service
public class CustomOidcUserService extends OidcUserService {
    private final UserRepository userRepo;

    public CustomOidcUserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest request) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(request);
        String regId = request.getClientRegistration().getRegistrationId();
        OAuth2UserInfo info = OAuth2UserInfoFactory.get(regId, oidcUser.getAttributes());

        // Создаём/обновляем пользователя в БД
        User user = userRepo.findByProviderAndProviderId(regId, info.getId())
                .orElseGet(() -> userRepo.save(User.fromOAuth(info, regId)));
        user.updateFromOAuth(info);
        userRepo.save(user);

        // Оборачиваем в AppUserPrincipal
        return new AppUserPrincipal(
                user,
                oidcUser.getAttributes(),
                oidcUser.getIdToken(),
                oidcUser.getUserInfo()
        );

    }
}
