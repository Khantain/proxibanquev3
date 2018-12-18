package fr.formation.proxi3.persistence;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.formation.proxi3.metier.entity.BankCard;

public class BankCardDao extends AbstractDao<BankCard>{
	
	private static final BankCardDao INSTANCE = new BankCardDao();
	
	public static BankCardDao getInstance() {
		return BankCardDao.INSTANCE;
	}

	@Override
	public BankCard read(Integer id) {
		return this.read(id, new BankCard());
	}

	@Override
	public List<BankCard> readAll() {
		List<BankCard> bankCards = new ArrayList<>();
		TypedQuery<BankCard> query = this.em
				.createQuery(JpqlQueries.SELECT_ALL_BANKCARD, BankCard.class);
		bankCards.addAll(query.getResultList());
		return bankCards;
	}

}
