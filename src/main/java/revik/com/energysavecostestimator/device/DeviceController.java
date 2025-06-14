package revik.com.energysavecostestimator.device;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import revik.com.energysavecostestimator.config.security.AppUserPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping
    public List<DeviceDTO> my(@AuthenticationPrincipal AppUserPrincipal p) {
        return service.list(p);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceDTO add(@AuthenticationPrincipal AppUserPrincipal p,
                         @RequestBody DeviceCreateDTO dto) {
        return service.add(p, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@AuthenticationPrincipal AppUserPrincipal p,
                       @PathVariable Long id) {
        service.remove(p, id);
    }
}
