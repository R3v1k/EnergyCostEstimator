package revik.com.energysavecostestimator.device;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUserId(Long userId);
    boolean existsByUserIdAndVendorAndDeviceModel(Long userId, String vendor, String deviceModel);
}
