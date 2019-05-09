package hibernate.services;

import hibernate.dao.GroupsDAO;
import hibernate.entity.GroupSt;
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class GroupsService extends SessionUtil implements GroupsDAO {
    //create
    public void add(GroupSt group) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.save(group);

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
    public List<GroupSt> getAll() {
        List<GroupSt> groupsList = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT * FROM groups_st";
            String hql = "FROM GroupSt";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(GroupSt.class);
            //Query query = session.createQuery(sql);
            Query query = session.createQuery(hql);
            groupsList = query.list();

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return groupsList;
    }


    public GroupSt getById(int id) {
        GroupSt group = new GroupSt();
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT * FROM groups_st WHERE group_id = :id";
            String hql = "FROM GroupSt WHERE group_id = :id";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(GroupSt.class);
            //Query query = session.createQuery(sql);
            Query query = session.createQuery(hql);
            query.setParameter("id", id);

            group = (GroupSt) query.getSingleResult();

            //close session with a transaction
            closeTransactionSession();
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
    public void getSortingByGroupsTitlesWithNumberOfStudents() {
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT title, number_of_students FROM groups_st ORDER BY title";
            String hql = "SELECT title, numberOfStudents FROM GroupSt ORDER BY title";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(GroupSt.class);
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
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    // Вывести список предметов для каждой специальности
    // с указанием количества отведенных на них часов.
    public void getSubjectsHoursListBySpeciality() {
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT DISTINCT groups_st.specialty, subjects.title, subjects.`type`, subjects.hours FROM groups_st " +
                    "JOIN subject_group ON groups_st.group_id = subject_group.groups_st_specialty " +
                    "JOIN subjects ON subject_group.subjects_id = subjects.subject_id ";

            Session session = getSession();
            Query query = session.createSQLQuery(sql);

            //Retrieving values ​​in multiple columns
            List<Object[]> subjectsSpecialityList = (List<Object[]>) query.getResultList();
            //Accessing each object array to retrieve title og group and number of students in it
            for (Object[] subjectsSpeciality : subjectsSpecialityList) {
                System.out.println("Speciality = " + (String) subjectsSpeciality[0]);
                System.out.println("Subject Title = " + (String) subjectsSpeciality[1]);
                System.out.println("Type = " + (String) subjectsSpeciality[2]);
                System.out.println("Hours total = " + (Integer) subjectsSpeciality[3]);
                System.out.println();
            }

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    //update
    public void update(GroupSt group) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.update(group);

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
    public void remove(GroupSt group) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.remove(group);

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

