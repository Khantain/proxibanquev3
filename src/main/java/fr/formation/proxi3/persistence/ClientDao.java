package fr.formation.proxi3.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import fr.formation.proxi3.persistence.JpqlQueries;
import fr.formation.proxi3.metier.entity.Client;

/**
 * Classe regroupant les traitements a effectuer sur les clients. Respecte le
 * design pattern singleton.
 * 
 * @author Adminl
 *
 */
public class ClientDao extends AbstractDao<Client> {

	private static final ClientDao INSTANCE = new ClientDao();
	
	public static ClientDao getInstance() {
		return ClientDao.INSTANCE;
	}
	
	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Permet de recuperer les informations d'un client a partir de son id.
	 */
	@Override
	public Client read(Integer id) {
		return this.read(id, new Client());
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Permet de recuperer les infos d'un client à partir de son nom et de son prénom.
	 */ 
	public Client read(String firstname, String lastname){
		TypedQuery<Client> query = this.em.createQuery(JpqlQueries.SELECT_ONE_CLIENT, Client.class);
		query.setParameter("firstname", firstname);
		query.setParameter("lastname", lastname);
		Client client = null;
		try {
			client = query.getSingleResult();
			
		} catch (NoResultException | NonUniqueResultException e) {
			System.out.println("impossible de récupérer un seul client dans la BDD avec ces parametres.");
		}
		return client;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Permet de recuperer l'ensemble des clients.
	 */
	@Override
	public List<Client> readAll() {
		List<Client> clients = new ArrayList<>();
		TypedQuery<Client> query = this.em
				.createQuery(JpqlQueries.SELECT_ALL_CLIENT, Client.class);
		clients.addAll(query.getResultList());
		return clients;
	}

}


