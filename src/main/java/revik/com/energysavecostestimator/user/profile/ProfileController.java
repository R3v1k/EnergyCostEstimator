package revik.com.energysavecostestimator.user.profile;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import revik.com.energysavecostestimator.config.security.AppUserPrincipal;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @GetMapping("/profile")
    public UserProfileDto getProfile(@AuthenticationPrincipal AppUserPrincipal principal) {
        return UserProfileDto.from(principal.getUser());
    }
}