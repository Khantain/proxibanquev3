package fr.formation.proxi3.presentation.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.formation.proxi3.metier.entity.Account;
import fr.formation.proxi3.metier.entity.CheckbookStatus;
import fr.formation.proxi3.metier.service.AccountService;

@Path("/checkbook")
public class JsonWebService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CheckbookStatus read(Account account) {
		System.out.println(account);
		return AccountService.getInstance().linkCheckBook(account.getId());
	}
}
