package fr.formation.proxi3.metier.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/** Classe représentant un compte epargne. Herite de {@link Account}.
 * @author Adminl
 *
 */
@Entity
@DiscriminatorValue(value="savings")
public class SavingsAccount extends Account {

}
