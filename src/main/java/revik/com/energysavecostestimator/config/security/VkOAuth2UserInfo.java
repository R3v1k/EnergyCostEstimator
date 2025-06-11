package revik.com.energysavecostestimator.config.security;

import java.util.List;
import java.util.Map;

public class VkOAuth2UserInfo extends OAuth2UserInfo {

    public VkOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        Object emailObj = attributes.get("email");
        if (emailObj instanceof List<?> list && !list.isEmpty()) {
            return list.getFirst().toString();
        }
        return emailObj != null ? emailObj.toString() : null;
    }

    @Override
    public String getName() {
        return attributes.get("first_name") + " " + attributes.get("last_name");
    }
}
