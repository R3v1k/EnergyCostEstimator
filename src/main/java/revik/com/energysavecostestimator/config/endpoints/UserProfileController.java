package revik.com.energysavecostestimator.config.endpoints;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import revik.com.energysavecostestimator.config.security.AppUserPrincipal;
import revik.com.energysavecostestimator.user.ProfileDTO;

@RestController
public class UserProfileController {

    @GetMapping(
            value    = "/profile",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfileDTO> getProfile(@AuthenticationPrincipal AppUserPrincipal principal) {
        ProfileDTO dto = ProfileDTO.from(principal.getUser());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);
    }
}
