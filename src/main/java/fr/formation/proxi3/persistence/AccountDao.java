package fr.formation.proxi3.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.formation.proxi3.metier.entity.Account;

/**
 * Classe regroupant les traitements a effectuer sur les comptes. Respecte le
 * design pattern singleton.
 * 
 * @author Adminl
 *
 */
public class AccountDao extends AbstractDao<Account>{
	
private static final AccountDao INSTANCE = new AccountDao();
	
	/** renvoie le singleton de la classe.
	 * @return AccountDao Le singleton.
	 */
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
		return this.read(id, new Account()); 
		
	}


	
	/**
	 * {@inheritDoc} 
	 * 
	 * Permet de recuperer l'ensemble des comptes
	 */
	@Override
	public List<Account> readAll() {
		List<Account> accounts = new ArrayList<>();
		TypedQuery<Account> query = this.em
				.createQuery(JpqlQueries.SELECT_ALL_CLIENT, Account.class);
		accounts.addAll(query.getResultList());
		return accounts;
	}
	


}



