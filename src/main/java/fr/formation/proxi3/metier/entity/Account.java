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

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="balance")
	private Double balance;
	
	@Column(name="number")
	private String number;
	
	@Column(name="label")
	private String label;
	
	@Column(name="openDate")
	private String openDate;

	@OneToOne
	@JoinColumn(name="checkbook")
	private CheckBook checkbook;

}
