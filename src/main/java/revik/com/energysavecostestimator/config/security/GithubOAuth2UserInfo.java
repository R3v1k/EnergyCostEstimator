package revik.com.energysavecostestimator.config.security;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo{

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        String name = (String) attributes.get("name");
        if (name != null && !name.isBlank()) {
            return name;
        }
        return (String) attributes.get("login");
    }
}
