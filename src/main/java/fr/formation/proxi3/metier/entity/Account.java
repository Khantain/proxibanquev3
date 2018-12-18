package fr.formation.proxi3.metier.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Classe abstraite représentant un compte en banque générique.
 * 
 * @author Adminl
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "balance")
	private Double balance;

	@Column(name = "number")
	private String number;

	@Column(name = "label")
	private String label;

	@Column(name = "openDate")
	private String openDate;

	@OneToOne
	@JoinColumn(name = "checkbook")
	private CheckBook checkbook;

	public Account() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public CheckBook getCheckbook() {
		return checkbook;
	}

	public void setCheckbook(CheckBook checkbook) {
		this.checkbook = checkbook;
	}

}
