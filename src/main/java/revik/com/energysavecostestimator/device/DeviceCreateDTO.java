package revik.com.energysavecostestimator.device;

public record DeviceCreateDTO(
        String vendor,
        String deviceModel,
        int nominalPowerW
) {}
