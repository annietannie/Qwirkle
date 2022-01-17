package qwirkle.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import qwirkle.api.models.*;
import qwirkle.QwirkleImpl;

@Path("/start")
public class StartQwirkle {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
        @Context HttpServletRequest request,
        Integer numberOfPlayers) {
           
            var qwirkle = new QwirkleImpl(numberOfPlayers);

            HttpSession session = request.getSession(true);
            session.setAttribute("qwirkle", qwirkle);
            session.setAttribute("numberOfPlayers", numberOfPlayers);
            
            var output = new Qwirkle(qwirkle, numberOfPlayers);
            return Response.status(200).entity(output).build();
        }
}
