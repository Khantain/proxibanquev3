package fr.formation.proxi3.persistence;

public class JpqlQueries {
	
	public static final String SELECT_ALL_CHECKBOOKS = "";
	public static final String SELECT_ALL_CLIENT = "SELECT c FROM Client c";
	public static final String SELECT_ONE_CLIENT = "SELECT c FROM Client c WHERE c.firstname = :firstname AND c.lastname = :lastname";

}
