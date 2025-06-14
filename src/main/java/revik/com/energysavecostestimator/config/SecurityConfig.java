package revik.com.energysavecostestimator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import revik.com.energysavecostestimator.config.security.CustomOAuth2UserService;
import revik.com.energysavecostestimator.config.security.OAuth2AuthSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CustomOAuth2UserService userService,
                                           OAuth2AuthSuccessHandler successHandler) throws Exception {
        http.authorizeHttpRequests(a -> a
                        .requestMatchers("/", "/login**", "/css/**",
                                "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/profile").authenticated()
                        .anyRequest().authenticated())
                .oauth2Login(o -> o
                        .loginPage("/login")
                        .userInfoEndpoint(u -> u.userService(userService))
                        .successHandler(successHandler));
        return http.build();
    }
}
