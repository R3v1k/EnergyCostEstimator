package revik.com.energysavecostestimator.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import revik.com.energysavecostestimator.user.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AppUserPrincipal implements OidcUser, OAuth2User {

    private final User user;
    private final Map<String, Object> attributes;

    public AppUserPrincipal(User user, Map<String,Object> attributes) {
        this(user, attributes, null, null);
    }

    public AppUserPrincipal(User user,
                            Map<String, Object> attributes,
                            OidcIdToken idToken,
                            OidcUserInfo userInfo) {
        this.user       = user;
        this.attributes = attributes;
    }


    public User getUser() {
        return user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getName() {
        return user.getId().toString();
    }

    @Override
    public Map<String, Object> getClaims() {
        return Map.of();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }
}