package fr.formation.proxi3.metier.service;

import fr.formation.proxi3.persistence.AccountDao;
import fr.formation.proxi3.persistence.BankCardDao;
import fr.formation.proxi3.persistence.CheckbookDao;

import java.time.LocalDate;

import org.apache.log4j.Logger;

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

	private Logger logger = Logger.getLogger(AccountService.class.getName());

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

	/**
	 * Methode permettant de récupere un compte à partir de son ID.
	 * 
	 * @param id l'id du compte.
	 * @return Account Le compte.
	 */
	public Account read(Integer id) {
		return this.accountDao.read(id);
	}

	/**
	 * Methode permettant de mettre à jour les informations d'un compte
	 * 
	 * @param account Le compte à mettre à jour.
	 */
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
		logger.debug("Entree dans linkNewCard");
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
				logger.info("Suppression de la carte avec l'id : " + bankCardId);
			} else {
				logger.info("Pas de nouvelle carte créée car la carte la actuelle expire le "
						+ currentAccount.getBankCard().getExpirationDate());
				// Sinon on indique qu'il ne faut pas créer de carte.
				resultOk = false;
			}
		}
		// Si il est possible d'ajouter une carte.
		if (resultOk) {
			// On prepare la nouvelle carte qui expirera 3 mois plus tard.
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
		logger.debug("sortie de la methode linkNewCard");
		return resultOk;
	}

	/**
	 * Methode qui genere une chaine de 16 chiffres aleatoirement.
	 * 
	 * @return String La chaine générée.
	 */
	private String generateNumber() {
		String chaine = "";
		for (int i = 0; i < 16; i++) {
			String num = Long.toString(Math.round(Math.random() * 10));
			chaine += num;
		}
		logger.info("génération du numéro de carte : " + chaine);
		return chaine;
	}

	/**
	 * Methode gérant l'ajout d'un nouveau chequier à un compte.
	 * 
	 * @param accountId L'id du compte.
	 * @return CheckbookStatus Objet contenant un booleen (qui vaut True si un
	 *         nouveau chequier a effectivement été ajouté au compte, false sinon)
	 *         et un message précisant le résultat de l'opération.
	 */
	public CheckbookStatus linkCheckBook(Integer accountId) {
		logger.debug("entree dans linkCheckBook");
		CheckbookStatus message = new CheckbookStatus();
		Account account = this.read(accountId);
		// test si le compte a déjà un chequier enregistré.
		if (account.getCheckbook() != null) {
			// test si le chequier en cours a été reçu il y a plus de 3 mois.
			if (account.getCheckbook().getReceivingDate().isBefore(LocalDate.now().minusMonths(3))) {
				// on récupère l'id du chequier expiré pour pouvoir le supprimer de la BDD à la
				// fin.
				Integer checkBookId = account.getCheckbook().getId();

				// on supprime le chequiere actuel expiré.
				account.setCheckbook(null);

				// et on en crée un nouveau qu'on va lier au compte.
				CheckBook chk = new CheckBook(LocalDate.now(), LocalDate.now().plusDays(15));
				CheckbookDao.getInstance().create(chk);
				account.setCheckbook(chk);
				this.accountDao.update(account);

				// l'ancien chequier est supprimé définitivement.
				CheckbookDao.getInstance().delete(checkBookId);
				message.setOK(true);
				message.setMessage("Nouveau chéquier valable jusqu’au " + LocalDate.now().plusDays(15).plusMonths(3)
						+ " en cours de distribution...");
				logger.info("ajout d'un nouveau chequier en remplacement de l'ancien");
				return message;
			} else {
				// le chequier actuel n'est pas périmé.
				message.setMessage("Impossible d’effectuer le retrait d’un nouveau chéquier pour ce compte avant le "
						+ account.getCheckbook().getReceivingDate().plusMonths(3) + ".");
				message.setOK(false);
				logger.info("pas de modification sur le chequier actuel encore valide.");
				return message;
			}

		} else {
			// ici on sait que le compte n'a pas de chéquier. Donc on en crée un et on
			// l'ajoute au compte.
			CheckBook chk = new CheckBook(LocalDate.now(), LocalDate.now().plusDays(15));
			CheckbookDao.getInstance().create(chk);
			account.setCheckbook(chk);
			this.accountDao.update(account);
			message.setOK(true);
			message.setMessage("Premier chéquier pour ce compte en cours de distribution...");
			logger.info("ajout d'un premier chequier au compte");
			return message;
		}

	}

	/**
	 * Methode permettant de faire un retrait d'argent liquide depuis un compte. Le
	 * retrait ne peut pas être supérieur au solde acutel du compte.
	 * 
	 * @param id    L'id du compte sur lequel faire le retrait.
	 * @param value Le montant du retrait à effectuer.
	 * @return
	 */
	public boolean makeWithdrawal(Integer id, Double value) {
		boolean notOk = false;
		Account account = AccountService.getInstance().read(id);
		// on teste si le compte à débiter n'aura pas de solde négatif suite au retrait
		// et si le montant du retrait est bien ifnérieur à 300 euro.
		if (account.getBalance() > value && value <= 300) {
			account.setBalance(account.getBalance() - value);
			AccountService.getInstance().update(account);
			notOk = true;
			logger.info("retrait d'argent liquide effectué");
		}
		return notOk;
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
	public boolean makeTransfer(Integer idCompteADebiter, Integer idCompteACrediter, Double value) {
		boolean notOk = false;
		Account accountDebiteur = AccountService.getInstance().read(idCompteADebiter);
		Account accountCrediteur = AccountService.getInstance().read(idCompteACrediter);

		// on teste si le compte a débiter n'aura pas de solde négatif, si les deux
		// comptes sont différents et si le montant du transfert est bien inférieur à
		// 900 euro.
		if (accountDebiteur.getBalance() > value && idCompteACrediter != idCompteADebiter && value <= 900) {
			accountDebiteur.setBalance(accountDebiteur.getBalance() - value);
			AccountService.getInstance().update(accountDebiteur);
			accountCrediteur.setBalance(accountCrediteur.getBalance() + value);
			AccountService.getInstance().update(accountCrediteur);
			logger.info("transfert effectué.");
			notOk = true;
		}
		return notOk;
	}

}
