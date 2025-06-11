package revik.com.energysavecostestimator.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth)
            throws IOException {
        AppUserPrincipal principal = (AppUserPrincipal) auth.getPrincipal();
        if (principal.getUser().isProfileComplete()) {
            response.sendRedirect("/dashboard");
        } else {
            response.sendRedirect("/proceed");
        }
    }
}
