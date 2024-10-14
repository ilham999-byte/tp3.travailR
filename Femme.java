package ma.projet.beans;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;


@Entity

@NamedQuery(
    name = "NombreEnfantsParFemmeEtDates",
    query = "SELECT SUM(m.nbrEnfant) FROM Mariage m WHERE m.femme = :femme AND m.dateDebut BETWEEN :startDate AND :endDate"
)
public class Femme extends Personne {
    // Spécifique à la classe Femme, sinon hérite de Personne
}
