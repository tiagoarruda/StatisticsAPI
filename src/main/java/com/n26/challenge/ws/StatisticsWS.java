package com.n26.challenge.ws;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.n26.challenge.controller.TransactionController;
import com.n26.challenge.exception.ExpiredTransaction;
import com.n26.challenge.transaction.Transaction;
import com.n26.challenge.transaction.TransactionStatistics;

@Path("/ws")
public class StatisticsWS {
	
	@Inject
	private TransactionController controller;
	
	private TransactionStatistics statistics;
	
	@GET
	@Path("/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistics() {
		
		return Response.status(200).entity(statistics).build();
	}
	
	@POST
	@Path("/transaction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTransaction(Transaction t) {
		if (t.getAmount() == null || t.getTimestamp() == null) {
			return Response.status(400).entity("Invalid data.").build();
		}
		
		try {
			controller.addTransaction(t);
		} catch(ExpiredTransaction e) {
			return Response.status(204).entity("The transaction is expired and was not computed.").build();
		} catch(Exception e) {
			return Response.status(500).entity("Unknow error.").build();
		}
		return Response.status(201).entity("Transaction succesfully processed").build();
	}
}
