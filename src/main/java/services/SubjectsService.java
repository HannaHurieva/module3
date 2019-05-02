package services;


import dao.SubjectsDAO;
import entity.Subjects;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectsService extends SessionUtil implements SubjectsDAO {
    //create
    public void add(Subjects subject) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.save(subject);

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //read
    public List<Subjects> getAll() {
        List<Subjects> subjectsList = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM subjects";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Subjects.class);
            subjectsList = query.list();

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return subjectsList;
    }


    public Subjects getById(int id) {
        Subjects subject = new Subjects();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM subjects WHERE subject_id = :id";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Subjects.class);
            query.setParameter("id", id);

            subject = (Subjects) query.getSingleResult();

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return subject;
    }


    //update
    public void update(Subjects subject) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.update(subject);

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //delete
    public void remove(Subjects subject) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.remove(subject);

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
