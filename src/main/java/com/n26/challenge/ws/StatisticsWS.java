package com.n26.challenge.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ws")
public class StatisticsWS {
	
	@GET
	@Path("/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistics() {
		return Response.status(200).build();
	}
	
	@POST
	@Path("/transaction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTransaction() {
		return Response.status(200).build();
	}
}
