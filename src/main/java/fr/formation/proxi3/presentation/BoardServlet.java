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

public class BoardServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				// Récupération de la liste de comptes du client
				List<Account> currentAccount = new ArrayList<>();
				List<Account> savingsAccount = new ArrayList<>();
				Client client = (Client) req.getSession().getAttribute("client");
				List<Account> accounts = client.getAccounts();
				req.setAttribute("accounts", accounts);
				for (Account account : accounts) {
					if (account instanceof CurrentAccount) {
						currentAccount.add(account);
					} else 
						savingsAccount.add(account);
					}
				req.setAttribute("savingAccounts", savingsAccount);
				req.setAttribute("currentAccounts", currentAccount);
				this.getServletContext().getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req,
						resp);
				}
						}
	
