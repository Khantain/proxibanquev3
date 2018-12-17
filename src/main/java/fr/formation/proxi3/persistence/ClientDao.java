package fr.formation.proxi3.persistence;

import java.util.List;

import fr.formation.proxi3.metier.entity.Client;
/**
 * Classe regroupant les traitements � effectuer sur les clients. Respecte le
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
		return null;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Permet de recuperer les infos d'un client à partir de son nom et de son prénom.
	 */ 
	public Client read(String firsname, String lastname) {
		return null;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Permet de recuperer l'ensemble des clients.
	 */
	@Override
	public List<Client> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}


