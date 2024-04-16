package ${package}.service;

import com.edriving.restboot.inject.AbstractBinder;
import jakarta.inject.Singleton;
import jakarta.ws.rs.ext.Provider;

@Provider
public class HelloWorldBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(HelloWorldServiceImpl.class).to(HelloWorldService.class).in(Singleton.class);
    }
}
