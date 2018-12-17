package fr.formation.proxi3.metier.service;


import fr.formation.proxi3.persistence.ClientDao;

/**
 * Classe rassemblant les traitements effectués sur les comptes.
 * 
 * @author Adminl
 *
 */
public class AccountService {

	private static final AccountService INSTANCE = new AccountService();

	private ClientDao clientDao;

	/**
	 * Retourne le singleton de la classe.
	 * 
	 * @return Le singleton.
	 */
	public static AccountService getInstance() {
		return AccountService.INSTANCE;
	}

}
