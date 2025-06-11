package revik.com.energysavecostestimator.config.security;

import java.util.Map;

public class YandexOAuth2UserInfo extends OAuth2UserInfo {

    public YandexOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("default_email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("real_name");
    }
}
