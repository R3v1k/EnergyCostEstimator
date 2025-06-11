package revik.com.energysavecostestimator.config.authorization;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import revik.com.energysavecostestimator.config.security.AppUserPrincipal;
import revik.com.energysavecostestimator.user.User;
import revik.com.energysavecostestimator.user.UserRepository;

@Controller
public class ProceedRegistrationController {
    private final UserRepository userRepository;

    public ProceedRegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/proceed")
    public String showProceedForm(Model model, @AuthenticationPrincipal AppUserPrincipal principal) {
        User user = principal.getUser();
        model.addAttribute("user", user);
        return "proceed";
    }
    
    @PostMapping("/proceed")
    public String processProceed(@ModelAttribute("user") @Valid User userForm, BindingResult bindingResult,
                                 @AuthenticationPrincipal AppUserPrincipal principal) {
        if (bindingResult.hasErrors()) return "proceed";
        User user = principal.getUser();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        user.setGender(userForm.getGender());
        user.setRegion(userForm.getRegion());
        userRepository.save(user);
        return "redirect:/dashboard";
    }
}
