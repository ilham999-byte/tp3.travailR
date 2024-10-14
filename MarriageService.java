package ma.projet.service;

import ma.projet.beans.Marriage;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ma.projet.util.HibernateUtil;

import java.util.List;

public class MarriageService implements IDao<Marriage> {

    @Override
    public boolean create(Marriage mariage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(mariage);
        tx.commit();
        session.close();
        return false;
    }

    
    @Override
    public Marriage getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Marriage mariage = (Marriage) session.get(Marriage.class, id);
        session.close();
        return mariage;
    }

    @Override
    public List<Marriage> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Marriage> mariages = session.createQuery("from Marriage", Marriage.class).list();
        session.close();
        return mariages;
    }
}
