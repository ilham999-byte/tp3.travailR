package ma.projet.service;

import java.util.Date;
import ma.projet.beans.Homme;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ma.projet.util.HibernateUtil;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ma.projet.beans.Femme;
import ma.projet.beans.Marriage;

public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme homme) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(homme);
        tx.commit();
        session.close();
        return false;
    }

   

    @Override
    public Homme getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Homme homme = (Homme) session.get(Homme.class, id);
        session.close();
        return homme;
    }

    @Override
    public List<Homme> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Homme> hommes = session.createQuery("from Homme", Homme.class).list();
        session.close();
        return hommes;
    }
    public List<Femme> findEpousesByDate(Homme homme, Date startDate, Date endDate) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Femme> femmes = session.createQuery(
            "select m.femme from Mariage m where m.homme = :homme and m.dateDebut between :start and :end", Femme.class)
            .setParameter("homme", homme)
            .setParameter("start", startDate)
            .setParameter("end", endDate)
            .list();
    session.close();
    return femmes;
}
public long findHommesAvec4FemmesEntreDeuxDates(Date startDate, Date endDate) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<Long> query = builder.createQuery(Long.class);
    Root<Marriage> root = query.from(Marriage.class);

    query.select(builder.countDistinct(root.get("homme")));
    query.where(
        builder.between(root.get("dateDebut"), startDate, endDate),
        builder.greaterThanOrEqualTo(builder.count(root.get("femme")), 4)
    );
    long count = session.createQuery(query).getSingleResult();
    session.close();
    return count;
}

