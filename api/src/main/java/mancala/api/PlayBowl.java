package mancala.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import mancala.api.models.*;
import mancala.domain.MancalaImpl;

@Path("/play")
public class PlayBowl {
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
			@Context HttpServletRequest request, 
			PlayInput index) {
		
        HttpSession session = request.getSession(true);
        var mancala = (mancala.domain.MancalaImpl) session.getAttribute("mancala");
        var namePlayer1 = (String) session.getAttribute("player1");
        var namePlayer2 = (String) session.getAttribute("player2");
        

        try {
            mancala.playPit(index.getIndex());
            var output = new Mancala(mancala, namePlayer1, namePlayer2);
            return Response.status(200).entity(output).build();
        } catch (Exception error) {
            var output = error;
            System.out.println(error);
            return Response.status(200).entity(output).build();
        }

		
		
	}
}
