package fr.formation.proxi3.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.Account;
import fr.formation.proxi3.metier.entity.Client;
import fr.formation.proxi3.metier.entity.CurrentAccount;

/**
 * Classe servlet gérant les requetes arrivant sur /board.html qui sera la page
 * principale de l'application.
 * 
 * @author Adminl
 *
 */
public class BoardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Methode gerant les requetes GET. Elle récupère les comptes courant et epargne
	 * du client qui vient de se connecter et les transfère à board.jsp.
	 * 
	 * @author Adminl
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Récupération de la liste de comptes du client
		List<Account> currentAccount = new ArrayList<>();
		List<Account> savingsAccount = new ArrayList<>();
		Client client = (Client) req.getSession().getAttribute("client");
		List<Account> accounts = client.getAccounts();
		// A partir de la liste complète des comptes, on crée les deux listes : comptes
		// epargne / comptes courant.
		for (Account account : accounts) {
			if (account instanceof CurrentAccount) {
				currentAccount.add(account);
			} else
				savingsAccount.add(account);
		}
		// passage des listes en paramètres de la requete et transfert à la jsp.
		req.setAttribute("savingAccounts", savingsAccount);
		req.setAttribute("currentAccounts", currentAccount);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req, resp);
	}
}
