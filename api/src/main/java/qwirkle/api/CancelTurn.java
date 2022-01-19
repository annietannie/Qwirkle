package qwirkle.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import qwirkle.api.models.*;
import qwirkle.QwirkleImpl;

@Path("/cancelturn")
public class CancelTurn {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
        @Context HttpServletRequest request,
        Integer player) {

        HttpSession session = request.getSession(true);
        var qwirkle = (qwirkle.QwirkleImpl) session.getAttribute("qwirkle");
        var numberOfPlayers = (Integer) session.getAttribute("numberOfPlayers");

        try {
            qwirkle.cancelTurn(player);
            var output = new Qwirkle(qwirkle, numberOfPlayers);
            return Response.status(200).entity(output).build();
        } catch (Exception error) {
            var output = error;
            System.out.println(error);
            return Response.status(200).entity(output).build();
        }
    }
    
}
