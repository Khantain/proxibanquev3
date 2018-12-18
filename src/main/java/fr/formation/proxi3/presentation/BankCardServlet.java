package fr.formation.proxi3.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.CurrentAccount;
import fr.formation.proxi3.metier.service.AccountService;

public class BankCardServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("id", id);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/formCard.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		CurrentAccount currentAccount = (CurrentAccount) AccountService.getInstance().read(id);
		String type = req.getParameter("type");
		boolean newCard = AccountService.getInstance().linkNewCard(id, type);
		req.setAttribute("bool",newCard);
		System.out.println(newCard);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorCard.jsp").forward(req, resp);
	}
}
