package fr.formation.proxi3.metier.entity;

import java.time.LocalDate;

public class CheckBook {

		private Integer id;
		private LocalDate sendDate;
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
