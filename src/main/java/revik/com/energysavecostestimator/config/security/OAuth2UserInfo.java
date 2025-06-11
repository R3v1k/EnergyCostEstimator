package revik.com.energysavecostestimator.config.security;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected final Map<String, Object> attributes;
    protected OAuth2UserInfo(Map<String, Object> attributes) {this.attributes = attributes;}

    public abstract String getId();
    public abstract String getEmail();
    public abstract String getName();

    public String getFirstName() {
        String[] parts = getName().split(" ");
        return parts.length > 0 ? parts[0] : "";
    }

    public String getLastName() {
        String[] parts = getName().split(" ");
        return parts.length > 1 ? parts[1] : "";
    }
}
