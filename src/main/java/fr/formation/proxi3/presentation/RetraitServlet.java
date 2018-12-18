package fr.formation.proxi3.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.Account;
import fr.formation.proxi3.metier.service.AccountService;

/**
 * Classe servlet gérant les requetes arrivant sur /retrait.html.
 * 
 * @author Adminl
 *
 */
public class RetraitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Methode gérant les requetes GET. Elle récupère le compte visé pour le retrait
	 * d'argent et transfère la requete.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Account account = AccountService.getInstance().read(id);
		req.setAttribute("account", account);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/retrait.jsp").forward(req, resp);
	}

	/**
	 * Methode gérant les requetes POST. Elle récupère le compte et le montant pour
	 * l'opération de retrait.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recupération de l'id du compte et du montant du retrait
		Integer id = Integer.parseInt(req.getParameter("id"));
		Double value = Double.parseDouble(req.getParameter("value"));
		// appel de la méthode de retrait de AccountService.
		AccountService.getInstance().makeWithdrawal(id, value);
		// redirection vers la page principale des comptes.
		resp.sendRedirect(this.getServletContext().getContextPath() + "/board.html");
	}

}
