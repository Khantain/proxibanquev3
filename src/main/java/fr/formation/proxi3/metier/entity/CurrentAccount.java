package fr.formation.proxi3.metier.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/** Classe représentant un compte courant. Herite de la classe {@link Account}.
 * @author Adminl
 *
 */
@Entity
@DiscriminatorValue(value="current")
public class CurrentAccount extends Account {
	
	@OneToOne
	@JoinColumn(name="bankcard")
	private BankCard bankCard;

	public BankCard getBankCard() {
		return bankCard;
	}

	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}

	
	
}
