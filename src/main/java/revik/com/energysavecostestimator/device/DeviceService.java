package revik.com.energysavecostestimator.device;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import revik.com.energysavecostestimator.config.security.AppUserPrincipal;

import java.util.List;

@Service
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceDTO> list(AppUserPrincipal p) {
        return deviceRepository.findByUserId(p.getUser().getId())
                .stream()
                .map(this::toDto)
                .toList();
    }

    public DeviceDTO add(AppUserPrincipal p, DeviceCreateDTO dto) {
        if (deviceRepository.existsByUserIdAndVendorAndDeviceModel(
                p.getUser().getId(), dto.vendor(), dto.deviceModel()))
            throw new IllegalStateException("device already exists");

        Device entity = new Device(
                null,
                p.getUser(),
                dto.vendor(),
                dto.deviceModel(),
                dto.nominalPowerW()
        );

        return toDto(deviceRepository.save(entity));
    }

    public void remove(AppUserPrincipal p, Long id) {
        Device d = deviceRepository.findById(id)
                .filter(dev -> dev.getUser().getId().equals(p.getUser().getId()))
                .orElseThrow();
        deviceRepository.delete(d);
    }

    private DeviceDTO toDto(Device d) {
        return new DeviceDTO(
                d.getId(),
                d.getVendor(),
                d.getDeviceModel(),
                d.getNominalPowerW()
        );
    }


}
