package fr.formation.proxi3.metier.service;

import fr.formation.proxi3.persistence.AccountDao;
import fr.formation.proxi3.persistence.BankCardDao;
import fr.formation.proxi3.persistence.CheckbookDao;
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

	private static final AccountService INSTANCE = new AccountService(AccountDao.getInstance(),
			BankCardDao.getInstance());

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

	/**
	 * Methode permettant d'ajouter une nouvelle carte Bleue à un compte. Dans le
	 * cas où une carte est déjà liée au compte, la méthode ne fait rien si cette
	 * carte n'est pas expirée. Le numero de la carte est généré alétoirement par la
	 * méthode generateNumber.
	 * 
	 * @param accountId Le compte pour lequel il faut ajouter une nouvelle carte.
	 * @param type      Le type de la carte à ajouter.
	 * @return boolean True si une nouvelle carte a été ajoutée au compte. False si
	 *         la carte actuelle du compte n'a pas expiré.
	 */
	public boolean linkNewCard(Integer accountId, String type) {
		boolean resultOk = true;
		// génération d'un nombre de longueur 16 aleatoirement.
		String number = this.generateNumber();

		CurrentAccount currentAccount = (CurrentAccount) this.accountDao.read(accountId);
		// Si le compte avait déjà une carte et qu'elle a expirée.
		if (currentAccount.getBankCard() != null) {
			Integer bankCardId = currentAccount.getBankCard().getId();
			// On vérifie que la date d'expiration est bien dépassée.
			if (currentAccount.getBankCard().getExpirationDate().isBefore(LocalDate.now())) {
				// Retirer le lien entre l'ancienne carte et le compte.
				currentAccount.setBankCard(null);
				// Mettre à jour le compte pour que le lien n'existe plus en BDD.
				this.accountDao.update(currentAccount);
				// Suppression de la carte.
				this.bankCardDao.delete(bankCardId);
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
			newCard.setNumber(number);
			// On créé la carte en BDD pour avoir un id généré.
			newCard = this.bankCardDao.create(newCard);
			// On lie la nouvelle carte au compte.
			currentAccount.setBankCard(newCard);
			// On met à jour le compte avec le lien vers la nouvelle carte.
			this.accountDao.update(currentAccount);
		}
		return resultOk;
	}

	/**
	 * genere une chaine de 16 chiffres aleatoirement.
	 * 
	 * @return La chaine générée.
	 */
	private String generateNumber() {
		String chaine = "";
		for (int i = 0; i < 16; i++) {
			String num = Long.toString(Math.round(Math.random() * 10));
			chaine += num;
		}
		return chaine;
	}

	/**
	 * Methode gérant l'ajout d'un nouveau chequier à un compte.
	 * 
	 * @param accountId L'id du compte.
	 * @return CheckbookStatus Objet contenant un booleen (qui vaut True si un
	 *         nouveau chequier a effectivement été ajouté, false sinon) et un
	 *         message précisant le résultat de l'opération.
	 */
	public CheckbookStatus linkCheckBook(Integer accountId) {
		CheckbookStatus message = new CheckbookStatus();
		Account account = new Account();
		// Recupere id compte et stock ds new
		account = this.read(accountId);
		if (account.getCheckbook() != null) {
			if (account.getCheckbook().getReceivingDate().isBefore(LocalDate.now().minusMonths(3))) {
				Integer checkBookId = account.getCheckbook().getId();
				account.setCheckbook(null);
				CheckBook chk = new CheckBook(LocalDate.now(), LocalDate.now().plusDays(15));
				CheckbookDao.getInstance().create(chk);
				account.setCheckbook(chk);
				this.accountDao.update(account);
				CheckbookDao.getInstance().delete(checkBookId);
				message.setOK(true);
				message.setMessage("“Nouveau chéquier valable jusqu’au " + LocalDate.now().plusDays(15).plusMonths(3)
						+ " en cours de distribution...");
				return message;
			} else {
				message.setMessage("Impossible d’effectuer le retrait d’un nouveau chéquier pour ce compte avant le "
						+ account.getCheckbook().getReceivingDate().plusMonths(3) + ".");
				message.setOK(false);
				return message;
			}

		} else {
			CheckBook chk = new CheckBook(LocalDate.now(), LocalDate.now().plusDays(15));
			CheckbookDao.getInstance().create(chk);
			account.setCheckbook(chk);
			this.accountDao.update(account);
			message.setOK(true);
			message.setMessage("Premier chéquier pour ce compte en cours de distribution...");
			return message;
		}

	}

	/**
	 * Methode permettant de faire un retrait d'argent liquide depuis un compte. Le
	 * retrait ne peut pas être supérieur au solde acutel du compte.
	 * 
	 * @param id    L'id du compte sur lequel faire le retrait.
	 * @param value Le montant du retrait à effectuer.
	 */
	public void makeWithdrawal(Integer id, Double value) {
		Account account = AccountService.getInstance().read(id);
		if (account.getBalance() > value) {
			account.setBalance(account.getBalance() - value);
			AccountService.getInstance().update(account);
		}
	}

	/**
	 * Methode permettant de faire un virement entre deux comptes. Le virement ne se
	 * fera que si le compte debiteur a un solde superieur au montant et si les id
	 * des deux comptes osnt differents.
	 * 
	 * @param idCompteADebiter  L'id du compte a debiter.
	 * @param idCompteACrediter L'id du compte a crediter.
	 * @param value             Le montant du transfert.
	 */
	public void makeTransfer(Integer idCompteADebiter, Integer idCompteACrediter, Double value) {
		Account accountDebiteur = AccountService.getInstance().read(idCompteADebiter);
		Account accountCrediteur = AccountService.getInstance().read(idCompteACrediter);
		if (accountDebiteur.getBalance() > value && idCompteACrediter != idCompteADebiter) {
			accountDebiteur.setBalance(accountDebiteur.getBalance() - value);
			AccountService.getInstance().update(accountDebiteur);
			accountCrediteur.setBalance(accountCrediteur.getBalance() + value);
			AccountService.getInstance().update(accountCrediteur);
		}
	}

}
