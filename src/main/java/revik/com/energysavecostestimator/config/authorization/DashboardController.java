package revik.com.energysavecostestimator.config.authorization;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import revik.com.energysavecostestimator.config.security.AppUserPrincipal;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal AppUserPrincipal principal, Model model) {
        model.addAttribute("name", principal.getUser().getFirstName());
        return "dashboard";
    }
}
