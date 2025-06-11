package revik.com.energysavecostestimator;

import org.springframework.boot.SpringApplication;

public class TestEnergySaveCostEstimatorApplication {

    public static void main(String[] args) {
        SpringApplication.from(EnergySaveCostEstimatorApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
