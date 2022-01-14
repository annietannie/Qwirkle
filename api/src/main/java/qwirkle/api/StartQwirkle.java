package qwirkle.api;

import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import qwirkle.api.models.*;
import qwirkle.QwirkleImpl;

@path("/start")
public class StartQwirkle {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
        @Context HttpServletRequest request,
        int numberOfPlayers) {
            var qwirkle = new QwirkleImpl(numberOfPlayers);

        }
}
