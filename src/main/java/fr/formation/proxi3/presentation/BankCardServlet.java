package fr.formation.proxi3.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.CurrentAccount;
import fr.formation.proxi3.metier.service.AccountService;

/**
 * Classe servlet gérant les requetes arrivant sur /card.html.
 * 
 * @author Adminl
 *
 */
public class BankCardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Methode gérant les requetes GET. Elle renvoie le client vers le formulaire
	 * pour choisir le type de carte bleue à ajouter au compte visé.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("id", id);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/formCard.jsp").forward(req, resp);
	}

	/**
	 * Methode gérant les requetes POST. Elle récupère le compte visé par
	 * l'opération (forcément un compte courant), ainsi que le type de carte
	 * souhiaté par le client. Transfère le client vers la jsp affichant un message
	 * de confirmation au client.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Récupération du compte qui est forcément un compte courant, les comptes
		// epargne n'ont pas le bouton pour ajouter une carte bleue.
		Integer id = Integer.parseInt(req.getParameter("id"));
		CurrentAccount currentAccount = (CurrentAccount) AccountService.getInstance().read(id);

		String type = req.getParameter("type");

		// Appel de la methode de AccountService qui gere l'ajout de la carte bleue et
		// transfert du booleen à la jsp pour adapter le message de confirmation.
		boolean newCard = AccountService.getInstance().linkNewCard(id, type);
		req.setAttribute("boolean", newCard);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorCard.jsp").forward(req, resp);
	}
}
