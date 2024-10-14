
package ma.projet.test;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;

public class Test {
    

public static void main(String[] args) {
    HommeService hommeService = new HommeService();
    FemmeService femmeService = new FemmeService();
    
    // Créer 10 femmes et 5 hommes
    for (int i = 1; i <= 10; i++) {
        Femme femme = new Femme();
        femme.setNom("Femme" + i);
        femme.setDateNaissance(new Date());
        femmeService.create(femme);
    }
    
    for (int i = 1; i <= 5; i++) {
        Homme homme = new Homme();
        homme.setNom("Homme" + i);
        homme.setDateNaissance(new Date());
        hommeService.create(homme);
    }
    
    // Afficher la liste des femmes
    List<Femme> femmes = femmeService.getAll();
    femmes.forEach(f -> System.out.println(f.getNom()));
    
    // Afficher la femme la plus âgée
    Femme femmeLaPlusAgee = femmes.stream().min(Comparator.comparing(Femme::getDateNaissance)).orElse(null);
    if (femmeLaPlusAgee != null) {
        System.out.println("Femme la plus âgée : " + femmeLaPlusAgee.getNom());
    }
    
    
}
}

