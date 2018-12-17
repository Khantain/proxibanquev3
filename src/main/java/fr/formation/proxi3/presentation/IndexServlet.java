package fr.formation.proxi3.presentation;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.Client;
import fr.formation.proxi3.metier.service.ClientService;

/**
 * Classe permettant de gerer l'affichage de l'ecran d'accueil de connexion et
 * la redirection vers la page principale de l'application.
 * 
 * @author Adminl
 *
 */
public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Methode prenant en charge une requete GET sur l'adresse "/index.html". Elle
	 * redirigie vers la page d'accueil qui demande le nom et le prenom du client.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Client> clients = ClientService.getInstance().getAll();

		this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	/**
	 * Methode prenant en charge une requete POST sur l'adresse "/index.html". A
	 * partir du nom et du prenom renseignés, elle envoie une requete à la base de
	 * données. S'il n'y a aucun client ou plus d'un client correspondant à ces nom
	 * et prenom, la méthode dirige le client vers une page d'erreur.
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recupération du nom et du prenom à partir du champ unique de saisie.
		String person = req.getParameter("idClient");
		System.out.println(person);
		String firstname = "";
		String lastname = "";

		if (person.contains(" ")) {
			try {
				// stockage du nom et du prénom dans deux variables séparées.
				String[] personSplit = person.split(" ");
				System.out.println(personSplit.length);
				lastname = personSplit[0];
				firstname = personSplit[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				// si le split ne marche pas, le client est envoyé vers la page d'erreur.
				this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorPerson.jsp").forward(req, resp);
			}

			// Premiere requete pour récupérer le client.
			Client client = ClientService.getInstance().read(firstname, lastname);
			System.out.println(client);
			// recherche du client en inversant prenom et nom.

			if (client == null) {
				// recherche du client en inversant prenom et nom si la première requete a
				// échoué.
				client = ClientService.getInstance().read(lastname, firstname);
				System.out.println(client);
			}
			// on entre dans le if si les deux recherches ont échoué.
			if (client == null) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorPerson.jsp").forward(req, resp);
			}
			// Le client récupéré est stocké dans la session.
			req.getSession().setAttribute("client", client);
			// redirection vers board.html
			resp.sendRedirect(this.getServletContext().getContextPath() + "/board.html");

		} else {
			// s'il n'y a pas d'espace dans la chaine récupérée, c'est que le client a mal
			// renseigné le champ => renvoi vers la page d'erreur.
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorPerson.jsp").forward(req, resp);
		}

	}
}
