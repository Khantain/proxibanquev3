package fr.formation.proxi3.persistence;

import java.util.List;

import fr.formation.proxi3.metier.entity.BankCard;

public class BankCardDao extends AbstractDao<BankCard>{
	
	private static final BankCardDao INSTANCE = new BankCardDao();
	
	public static BankCardDao getInstance() {
		return BankCardDao.INSTANCE;
	}

	@Override
	public BankCard read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankCard> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BankCard create(BankCard bankcard) {
		return null;
	}

}
