package fr.formation.proxi3.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.formation.proxi3.persistence.JpqlQueries;
import fr.formation.proxi3.metier.entity.CheckBook;

public class CheckbookDao extends AbstractDao<CheckBook>{
	
	private static final CheckbookDao INSTANCE = new CheckbookDao();

	public CheckbookDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static CheckbookDao getInstance() {
		return CheckbookDao.INSTANCE;
	}
	
	@Override
	public CheckBook read(Integer id) {
		return this.read(id, new CheckBook());
	}

	@Override
	public List<CheckBook> readAll() {
		List<CheckBook> checkbooks = new ArrayList<>();
		TypedQuery<CheckBook> query = this.em
				.createQuery(JpqlQueries.SELECT_ALL_CHECKBOOKS, CheckBook.class);
		checkbooks.addAll(query.getResultList());
		return checkbooks;
	}

}
