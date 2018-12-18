package fr.formation.proxi3.metier.entity;

/**
 * Classe représentant la réponse envoyée par le WebService pour la demande d'un
 * chéquier.
 * 
 * @author Adminl
 *
 */
public class CheckbookStatus {

	private boolean isOK;
	private String message;

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
