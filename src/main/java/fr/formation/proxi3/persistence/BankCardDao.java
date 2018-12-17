package fr.formation.proxi3.persistence;

public class BankCardDao {
	
private static final BankCardDao INSTANCE = new BankCardDao();
	
	public static BankCardDao getInstance() {
		return BankCardDao.INSTANCE;
	}

}
