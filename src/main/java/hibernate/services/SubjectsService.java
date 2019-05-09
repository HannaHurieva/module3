package hibernate.services;


import hibernate.dao.SubjectsDAO;
import hibernate.entity.Subject;
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class SubjectsService extends SessionUtil implements SubjectsDAO {
    //create
    public void add(Subject subject) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.save(subject);

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //read
    public List<Subject> getAll() {
        List<Subject> subjectsList = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM subjects";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Subject.class);
            subjectsList = query.list();

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return subjectsList;
    }


    public Subject getById(int id) {
        Subject subject = new Subject();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM subjects WHERE subject_id = :id";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Subject.class);
            query.setParameter("id", id);

            subject = (Subject) query.getSingleResult();

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return subject;
    }


    //update
    public void update(Subject subject) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.update(subject);

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //delete
    public void remove(Subject subject) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.remove(subject);

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
