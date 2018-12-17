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

public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Client> clients = ClientService.getInstance().getAll();

		this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// si tout va bien
		String person = req.getParameter("idClient");
		System.out.println(person);
		String firstname = "";
		String lastname = "";

		if (person.contains(" ")) {
			try {
				String[] personSplit = person.split(" ");
				System.out.println(personSplit.length);
				lastname = personSplit[0];
				firstname = personSplit[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorPerson.jsp").forward(req, resp);
			}

			Client client = ClientService.getInstance().read(firstname, lastname);
			System.out.println(client);
			// recherche du client en inversant prenom et nom.
			if (client == null) {
				client = ClientService.getInstance().read(lastname, firstname);
				System.out.println(client);
			}
			// on entre dans le if si les deux recherches ont échoué.
			if (client == null) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorPerson.jsp").forward(req, resp);
			}

			req.getSession().setAttribute("client", client);
			// redirect vers board.html
			resp.sendRedirect(this.getServletContext().getContextPath() + "/board.html");

		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/errorPerson.jsp").forward(req, resp);
		}

	}
}
