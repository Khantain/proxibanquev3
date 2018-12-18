package fr.formation.proxi3.persistence;

/**
 * Classe rassemblant les requetes JPQL utilisees par le coucher persistence de
 * l'application.
 * 
 * @author Adminl
 *
 */
public class JpqlQueries {

	public static final String SELECT_ALL_CHECKBOOKS = "";
	public static final String SELECT_ALL_CLIENT = "SELECT c FROM Client c";
	public static final String SELECT_ONE_CLIENT = "SELECT c FROM Client c WHERE c.firstname = :firstname AND c.lastname = :lastname";
	public static final String SELECT_ALL_BANKCARD = "SELECT bc FROM BankCard bc";

}
