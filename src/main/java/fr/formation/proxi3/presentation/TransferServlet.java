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

/**
 * Classe servlet gérant les requetes arrivant sur /transfer.html.
 * 
 * @author Adminl
 *
 */
public class TransferServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	/**
	 * Methode gérant les requetes GET. Elle récupère la liste des comptes du client
	 * et transfere la requete vers le formulaire de virement.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client client = (Client) req.getSession().getAttribute("client");
		List<Account> accounts = client.getAccounts();
		req.setAttribute("accounts", accounts);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/transfer.jsp").forward(req, resp);
	}

	/**
	 * Methode gérant les requetes POST. Elle récupèreles id des comptes a crediter
	 * et debiter ainsi que le montant du virement qui ne peut pas être superieur à
	 * 900 euro. et transfere la requete vers le formulaire de virement. Appel de la
	 * méthode makeTransfer pour effectuer le virement
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Double value = Double.parseDouble(req.getParameter("value"));
		Integer compteADebiter = Integer.parseInt(req.getParameter("compteADebiter"));
		Integer compteACrediter = Integer.parseInt(req.getParameter("compteACrediter"));
		Integer id = Integer.parseInt(req.getParameter("id"));
		boolean ok = AccountService.getInstance().makeTransfer(compteADebiter, compteACrediter, value);
		req.setAttribute("bool",ok);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorTransfer.jsp").forward(req, resp);
	//	resp.sendRedirect(this.getServletContext().getContextPath() + "/board.html");
	}
}
