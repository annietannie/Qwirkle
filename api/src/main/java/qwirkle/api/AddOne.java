package qwirkle.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import qwirkle.api.models.*;

@Path("/addone")
public class AddOne {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response add(
        @Context HttpServletRequest request,
        Count count) {
            Count response = new Count();
            response.setCount(count.getCount() + 1);
            return Response.status(200).entity(response).build();

        }
}
