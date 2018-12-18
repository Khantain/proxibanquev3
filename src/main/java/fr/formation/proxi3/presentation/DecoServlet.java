package fr.formation.proxi3.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe servlet gérant les requetes arrivant sur /disconnect.html.
 * 
 * @author Adminl
 *
 */
public class DecoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Methode gérant les requetes GET. Elle invalide la session de l'utilisateur et
	 * redirige vers index.htlm en vue d'une nouvelle connexion.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect(this.getServletContext().getContextPath() + "/index.html");
	}

}
