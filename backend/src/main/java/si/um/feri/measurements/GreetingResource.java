package si.um.feri.measurements;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    /**
     * Returns a greeting message.
     *
     * This method generates a simple greeting string that can be used in various contexts
     * where a friendly message is required.
     *
     * @return a String containing the greeting message "Hello from RESTEasy Reactive".
     *
     * @throws NullPointerException if the greeting message cannot be generated (not applicable in this case).
     */
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}
