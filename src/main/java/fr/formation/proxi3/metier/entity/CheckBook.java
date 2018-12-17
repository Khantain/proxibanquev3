package fr.formation.proxi3.metier.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkbook")
public class CheckBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="sendDate")
	private LocalDate sendDate;
	
	@Column(name="receivingDate")
	private LocalDate receivingDate;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}

	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}

	public LocalDate getReceivingDate() {
		return receivingDate;
	}

	public void setReceivingDate(LocalDate receivingDate) {
		this.receivingDate = receivingDate;
	}

}
