package fr.formation.proxi3.metier.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="savings")
public class SavingsAccount extends Account {

}
