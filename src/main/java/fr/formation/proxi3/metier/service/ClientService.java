package fr.formation.proxi3.metier.service;

import java.util.List;

import fr.formation.proxi3.metier.entity.Client;
import fr.formation.proxi3.metier.service.ClientService;
import fr.formation.proxi3.persistence.AccountDao;
import fr.formation.proxi3.persistence.BankCardDao;
import fr.formation.proxi3.persistence.ClientDao;

public class ClientService {

	private static final ClientService INSTANCE = new ClientService(AccountDao.getInstance() , ClientDao.getInstance(), BankCardDao.getInstance());
	private ClientDao daoClient;
	private AccountDao daoAccount;
	private BankCardDao daoCard;

	/**
	 * Retourne le singleton de la classe.
	 * 
	 * @return Le singleton.
	 */
	public static ClientService getInstance() {
		return ClientService.INSTANCE;
	}

	public ClientService() {
	}

	public ClientService(AccountDao daoAccount , ClientDao daoClient, BankCardDao daoCard) {
		this.daoAccount = daoAccount;
		this.daoClient = daoClient;
		this.daoCard = daoCard;
	}

	
	/**
	 * Recup�re la liste de tous les clients suivis par le conseiller.
	 * 
	 * @return La liste des clients du conseiller.
	 */
	public List<Client> getAll() {
		return this.daoClient.readAll();
	}
}
