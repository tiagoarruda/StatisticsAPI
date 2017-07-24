package com.n26.challenge.ws;

import java.util.logging.Logger;

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
	
	private final static Logger log = Logger.getLogger(StatisticsWS.class.getName());
	
	@Inject
	private TransactionController controller;
	
	@GET
	@Path("/statistics")
	@Produces(MediaType.APPLICATION_JSON)
	public TransactionStatistics getStatistics() {
		log.info("Get Statistics Endpoint");
		return controller.getStatistics();
	}
	
	@POST
	@Path("/transaction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postTransaction(Transaction t) {
		log.info("Post Transaction Endpoint");
		StatisticsResponse message = new StatisticsResponse();
		if (t.getAmount() == null || t.getTimestamp() == null) {
			message.setMessage("Invalid Data");
			return Response.status(400).entity(message).build();
		}
		
		try {
			controller.addTransaction(t);
		} catch(ExpiredTransaction e) {
			log.info("This transaction is older and will not be processed");
			return Response.status(204).build();
		} catch(Exception e) {
			return Response.status(500).entity("Unknow error.").build();
		}
		
		message.setMessage("Transaction succesfully processed");
		return Response.status(201)
				.entity(message)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

	public void setController(TransactionController controller) {
		this.controller = controller;
	}	
}
