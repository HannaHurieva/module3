package services;

import dao.GroupsDAO;
import entity.Groups;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class GroupsService extends SessionUtil implements GroupsDAO {
    //create
    public void add(Groups group) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.save(group);

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
    public List<Groups> getAll() {
        List<Groups> groupsList = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM groups_st";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Groups.class);
            groupsList = query.list();

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return groupsList;
    }


    public Groups getById(int id) {
        Groups group = new Groups();
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT * FROM groups_st WHERE group_id = :id";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Groups.class);
            query.setParameter("id", id);

            group = (Groups) query.getSingleResult();

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return group;
    }


    //update
    public void update(Groups group) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.update(group);

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
    public void remove(Groups group) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.remove(group);

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

