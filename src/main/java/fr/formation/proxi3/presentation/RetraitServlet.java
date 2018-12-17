package fr.formation.proxi3.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.Account;
import fr.formation.proxi3.metier.service.AccountService;

public class RetraitServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Account account = AccountService.getInstance().read(id);
		req.setAttribute("account", account);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/retrait.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Double value = Double.parseDouble(req.getParameter("value"));
		System.out.println("id : " + id);
		System.out.println("montant : " + value);
		Account account = AccountService.getInstance().read(id);
		account.setBalance(account.getBalance()-value);
		AccountService.getInstance().update(account);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/retrait.jsp").forward(req, resp);
	}

}
