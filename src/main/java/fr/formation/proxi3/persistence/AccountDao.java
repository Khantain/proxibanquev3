package fr.formation.proxi3.persistence;

import java.util.List;

import fr.formation.proxi3.metier.entity.Account;
import fr.formation.proxi3.metier.entity.Client;

/**
 * Classe regroupant les traitements � effectuer sur les comptes. Respecte le
 * design pattern singleton.
 * 
 * @author Adminl
 *
 */
public class AccountDao extends AbstractDao<Account>{
	
private static final AccountDao INSTANCE = new AccountDao();
	
	public static AccountDao getInstance() {
		return AccountDao.INSTANCE;
	}
	
	/**
	 * {@inheritDoc} 
	 * 
	 * Permet de recuperer les informations d'un compte à partir de l'id du client
	 */
	@Override
	public Account read(Integer id) {
		return null;
	}


	/**
	 * Permet de recuperer l'ensemble des comptes par client.
	 */
	public List<Account> readAllAccountByClient(Client clients) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * {@inheritDoc} 
	 * 
	 * Permet de recuperer l'ensemble des comptes
	 */
	@Override
	public List<Account> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Permet de recuperer l'ensemble des comptes par client.
	 */
	public Account update(Account account) {
		return account;
		
	}

}



