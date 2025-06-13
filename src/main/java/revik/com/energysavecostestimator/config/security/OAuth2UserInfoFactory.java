package revik.com.energysavecostestimator.config.security;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo get(String regId, Map<String, Object> attributes) {
        return switch (regId) {
            case "github" -> new GithubOAuth2UserInfo(attributes);
            case "google" -> new GoogleOAuth2UserInfo(attributes);
            case "yandex" -> new YandexOAuth2UserInfo(attributes);
            default -> throw new IllegalArgumentException("Unsupported provider: " + regId);
        };
    }
}
