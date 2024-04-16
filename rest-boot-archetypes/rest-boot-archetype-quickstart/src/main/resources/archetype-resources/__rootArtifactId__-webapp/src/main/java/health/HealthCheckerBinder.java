package ${package}.health;

import com.edriving.restboot.health.HealthChecker;
import com.edriving.restboot.inject.AbstractBinder;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;

@Provider
public class HealthCheckerBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(AppHealthChecker.class).to(HealthChecker.class).in(Singleton.class);
    }
}
