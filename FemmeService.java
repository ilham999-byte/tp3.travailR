package ma.projet.service;

import java.util.Date;
import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class FemmeService implements IDao<Femme> {

    @Override
    public boolean create(Femme femme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(femme);
        tx.commit();
        session.close();
        return true;
    }

    

    @Override
    public Femme getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Femme femme = (Femme) session.get(Femme.class, id);
        session.close();
        return femme;
    }

    @Override
    public List<Femme> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Femme> femmes = session.createQuery("from Femme").list();
        session.close();
        return femmes;
    }

    public int countEnfantsByFemmeAndDates(Femme femme, Date startDate, Date endDate) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Long> result = session.createNamedQuery("NombreEnfantsParFemmeEtDates", Long.class)
                .setParameter("femme", femme)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    session.close();
    
    Long nbrEnfants = result.isEmpty() ? 0L : result.get(0);
    return nbrEnfants.intValue();
}
    
    // Méthode pour afficher les femmes mariées deux fois ou plus
    public List<Femme> femmesMarieesDeuxFoisOuPlus() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Femme> femmes = session.createNamedQuery("FemmesMarieesDeuxFoisOuPlus", Femme.class).list();
        session.close();
        return femmes;
    }

    public List<Femme> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
