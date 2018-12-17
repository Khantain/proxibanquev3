package fr.formation.proxi3.metier.service;


import fr.formation.proxi3.persistence.AccountDao;
import fr.formation.proxi3.persistence.ClientDao;
import fr.formation.proxi3.metier.entity.Account;

/**
 * Classe rassemblant les traitements effectués sur les comptes.
 * 
 * @author Adminl
 *
 */
public class AccountService {

	private static final AccountService INSTANCE = new AccountService(new AccountDao());

	private AccountDao accountDao;

	public AccountService(AccountDao accountDao2) {
		this.accountDao = AccountDao.getInstance();
	}

	/**
	 * Retourne le singleton de la classe.
	 * 
	 * @return Le singleton.
	 */
	public static AccountService getInstance() {
		return AccountService.INSTANCE;
	}

	public Account read(Integer id) {
		return this.accountDao.read(id);
	}

	public void update(Account account) {
		this.accountDao.update(account);
		
	}

}
