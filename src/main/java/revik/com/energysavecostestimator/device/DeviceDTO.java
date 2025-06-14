package revik.com.energysavecostestimator.device;

public record DeviceDTO(
        Long id,
        String vendor,
        String deviceModel,
        int nominalPowerW
) {}