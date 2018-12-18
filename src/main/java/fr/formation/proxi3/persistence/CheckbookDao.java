package fr.formation.proxi3.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.formation.proxi3.persistence.JpqlQueries;
import fr.formation.proxi3.metier.entity.CheckBook;

/**
 * Classe regroupant les traitements a effectuer sur les chequiers. Respecte le
 * design pattern singleton.
 * 
 * @author Adminl
 *
 */
public class CheckbookDao extends AbstractDao<CheckBook> {

	private static final CheckbookDao INSTANCE = new CheckbookDao();

	public CheckbookDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * renvoie le singleton de la classe
	 * 
	 * @return CheckBookDao Le singleton.
	 */
	public static CheckbookDao getInstance() {
		return CheckbookDao.INSTANCE;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Permet de recuperer les informations d'un chequier a partir de son id.
	 */
	@Override
	public CheckBook read(Integer id) {
		return this.read(id, new CheckBook());
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Permet de recuperer l'ensemble des chequiers.
	 */
	@Override
	public List<CheckBook> readAll() {
		List<CheckBook> checkbooks = new ArrayList<>();
		TypedQuery<CheckBook> query = this.em.createQuery(JpqlQueries.SELECT_ALL_CHECKBOOKS, CheckBook.class);
		checkbooks.addAll(query.getResultList());
		return checkbooks;
	}

}
