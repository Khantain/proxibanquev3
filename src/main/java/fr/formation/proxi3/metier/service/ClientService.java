package fr.formation.proxi3.metier.service;

import java.util.List;

import org.apache.log4j.Logger;

import fr.formation.proxi3.metier.entity.Client;
import fr.formation.proxi3.metier.service.ClientService;
import fr.formation.proxi3.persistence.AccountDao;
import fr.formation.proxi3.persistence.BankCardDao;
import fr.formation.proxi3.persistence.ClientDao;

/**
 * Classe rassemblant les traitements effectués sur les clients de
 * l'application.
 * 
 * @author Adminl
 *
 */
public class ClientService {

	private static final ClientService INSTANCE = new ClientService(ClientDao.getInstance());
	private ClientDao daoClient;
	private Logger logger = Logger.getLogger(ClientService.class.getName());

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

	public ClientService(ClientDao daoClient) {
		this.daoClient = daoClient;
	}

	/**
	 * Recupere la liste de tous les clients de la base de donnees.
	 * 
	 * @return La liste des clients.
	 */
	public List<Client> getAll() {
		return this.daoClient.readAll();
	}

	/**
	 * Methode permettant de récupérer un client à partir de son nom et de son
	 * prenom. S'il n'y pas de client ou au moins deux clients associes à ces
	 * valeurs, l'objet retourne vaut null.
	 * 
	 * @param firstname Le prénom du client recherche
	 * @param lastname  Le nom du client recherche
	 * @return Client le client de la base de données. Null s'il n'y a pas de client
	 *         ou plus d'un client ayant ce nom et ce prénom.
	 */
	public Client read(String firstname, String lastname) {
		logger.info("recherche de " + firstname + " " + lastname);
		return this.daoClient.read(firstname, lastname);
	}
}
