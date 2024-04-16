package ${package}.health;

import com.edriving.restboot.health.HealthCheckException;
import com.edriving.restboot.health.HealthChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppHealthChecker implements HealthChecker {

    @Override
    public void check() throws HealthCheckException {
        LOGGER.info("NewHealthChecker is checking health");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AppHealthChecker.class);
}
