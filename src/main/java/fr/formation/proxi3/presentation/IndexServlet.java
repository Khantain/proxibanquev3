package fr.formation.proxi3.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.formation.proxi3.metier.entity.Client;
import fr.formation.proxi3.metier.service.ClientService;

public class IndexServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Client> clients = ClientService.getInstance().getAll();
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	

}
