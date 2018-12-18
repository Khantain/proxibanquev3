package fr.formation.proxi3.metier.service;


import fr.formation.proxi3.persistence.AccountDao;
import fr.formation.proxi3.persistence.BankCardDao;
import fr.formation.proxi3.persistence.ClientDao;

import java.time.LocalDate;

import fr.formation.proxi3.metier.entity.Account;
import fr.formation.proxi3.metier.entity.BankCard;
import fr.formation.proxi3.metier.entity.CheckBook;
import fr.formation.proxi3.metier.entity.CheckbookStatus;
import fr.formation.proxi3.metier.entity.CurrentAccount;

/**
 * Classe rassemblant les traitements effectués sur les comptes.
 * 
 * @author Adminl
 *
 */
public class AccountService {

	private static final AccountService INSTANCE = new AccountService(AccountDao.getInstance(), BankCardDao.getInstance());

	private AccountDao accountDao;
	private BankCardDao bankCardDao;

	public AccountService(AccountDao accountDao2, BankCardDao bankCardDao) {
		this.accountDao = accountDao2;
		this.bankCardDao = bankCardDao;
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
	
	public boolean linkNewCard(Integer accountId, String type) {
		boolean resultOk = true;
		CurrentAccount currentAccount = (CurrentAccount) this.accountDao.read(accountId);
		// Si le compte avait déjà une carte et qu'elle a expirée.
		if (currentAccount.getBankCard() != null) {
			// On vérifie que la date d'expiration est bien dépassée.
			if (currentAccount.getBankCard().getExpirationDate().isBefore(LocalDate.now())) {
				// Retirer le lien entre l'ancienne carte et le compte.
				currentAccount.setBankCard(null);
				// Mettre à jour le compte pour que le lien n'existe plus en BDD.
				this.accountDao.update(currentAccount);				
			} else {
				// Sinon on indique qu'il ne faut pas créer de carte.
				resultOk = false;
			}
		}
		// Si il est possible d'ajouter une carte.
		if (resultOk) {
			// On prepare la nouvelle carte.
			BankCard newCard = new BankCard();
			newCard.setExpirationDate(LocalDate.now().plusMonths(3));
			newCard.setType(type);
			// On créé la carte en BDD pour avoir un id généré.
			newCard = this.bankCardDao.create(newCard);
			// On lie la nouvelle carte au compte.
			currentAccount.setBankCard(newCard);
			// On met à jour le compte avec le lien vers la nouvelle carte.
			this.accountDao.update(currentAccount);
		}
		return resultOk;
	}
	
	public CheckbookStatus linkCheckBook(Integer accountId) {
		CheckbookStatus message = new CheckbookStatus();
		Account account = new Account();
		// Recupere id compte et stock ds new
		account = this.read(accountId);
		if (account.getCheckbook() != null) {
			if (account.getCheckbook().getReceivingDate().isBefore(LocalDate.now().minusMonths(3))) {
				account.setCheckbook(new CheckBook(LocalDate.now(), LocalDate.now().plusDays(15)));
				this.accountDao.update(account);
				message.setOK(true);
				message.setMessage("“Nouveau chéquier valable jusqu’au "  + LocalDate.now().plusDays(15).plusMonths(3) + " en cours de distribution...");
				return message;
			} else {
				message.setMessage("Impossible d’effectuer le retrait d’un nouveau chéquier pour ce compte avant le " + account.getCheckbook().getReceivingDate().plusMonths(3) + ".");
				message.setOK(false);
				return message;
			}
			
		} else {
			account.setCheckbook(new CheckBook(LocalDate.now(), LocalDate.now().plusDays(15)));
			this.accountDao.update(account);
			message.setOK(true);
			message.setMessage("Premier chéquier pour ce compte en cours de distribution...");
			return message;
		}
		
	}

}
