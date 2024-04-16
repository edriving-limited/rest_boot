package ${package}.api;

import ${package}.service.HelloWorldService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldResource {

    private final HelloWorldService helloWorldService;

    @Inject
    public HelloWorldResource(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return helloWorldService.getHelloMessage();
    }
}
