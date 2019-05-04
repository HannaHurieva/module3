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

            //String sql = "SELECT * FROM groups_st";
            String hql = "FROM Groups";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Groups.class);
            //Query query = session.createQuery(sql);
            Query query = session.createQuery(hql);
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

            //String sql = "SELECT * FROM groups_st WHERE group_id = :id";
            String hql = "FROM Groups WHERE group_id = :id";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Groups.class);
            //Query query = session.createQuery(sql);
            Query query = session.createQuery(hql);
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

    // Вывести список названий групп с указанием количества студентов.
    // Отсортировать по названию группы.
    public void getSortingByGroupsTitles_WithNumberOfStudents() {
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT title, number_of_students FROM groups_st ORDER BY title";
            String hql = "SELECT title, numberOfStudents FROM Groups ORDER BY title";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Groups.class);
            Query query = session.createQuery(hql);

            //Retrieving values ​​in multiple columns
            List<Object[]> groupsTitles = (List<Object[]>) query.getResultList();
            //Accessing each object array to retrieve title og group and number of students in it
            for (Object[] groupTitle : groupsTitles) {
                System.out.println("Title of group = " + (String) groupTitle[0]);
                System.out.println("The number of students = " + (Integer) groupTitle[1]);
                System.out.println();
            }
            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
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

