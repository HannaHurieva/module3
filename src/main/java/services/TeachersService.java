package services;

import dao.TeachersDAO;
import entity.Teachers;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class TeachersService extends SessionUtil implements TeachersDAO {
    //create
    public void add(Teachers teacher) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.save(teacher);

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
    public List<Teachers> getAll() {
        List<Teachers> teachersList = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM teachers";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Teachers.class);
            teachersList = query.list();

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return teachersList;
    }


    public Teachers getById(int id) {
        Teachers teacher = new Teachers();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM teachers WHERE id = :id";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Teachers.class);
            query.setParameter("id", id);

            teacher = (Teachers) query.getSingleResult();

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return teacher;
    }

    public List<Teachers> getByExperience() {
        List<Teachers> teachersExp = new ArrayList<>();
        return teachersExp;
    }

    //update
    public void update(Teachers teacher) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.update(teacher);

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
    public void remove(Teachers teacher) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.remove(teacher);

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
