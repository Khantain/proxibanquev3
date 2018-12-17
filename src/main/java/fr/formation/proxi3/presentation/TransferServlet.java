package fr.formation.proxi3.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.Account;
import fr.formation.proxi3.metier.entity.Client;
import fr.formation.proxi3.metier.service.AccountService;

public class TransferServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client client = (Client) req.getSession().getAttribute("client");
		List<Account> accounts = client.getAccounts();
		req.setAttribute("accounts", accounts);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/transfer.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client client = (Client) req.getSession().getAttribute("client");
		Double value = Double.parseDouble(req.getParameter("value"));
		Integer compteADebiter = Integer.parseInt(req.getParameter("compteADebiter")); 
		Integer compteACrediter = Integer.parseInt(req.getParameter("compteACrediter")); 
		Account accountDebiteur = AccountService.getInstance().read(compteADebiter);
		Account accountCrediteur = AccountService.getInstance().read(compteACrediter);
		if (accountDebiteur.getBalance()>value && compteACrediter != compteADebiter) {
			accountDebiteur.setBalance(accountDebiteur.getBalance()-value);
			AccountService.getInstance().update(accountDebiteur);
			accountCrediteur.setBalance(accountCrediteur.getBalance()+value);
			AccountService.getInstance().update(accountCrediteur);
			// Renvoyer à board.jsp + boolean afficheer div
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/transfer.jsp").forward(req, resp);
		}
	}
	
		
}
