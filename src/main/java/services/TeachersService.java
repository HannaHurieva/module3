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

            //String sql = "SELECT * FROM teachers";
            String hql = "FROM Teachers";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Teachers.class);
            Query query = session.createQuery(hql);
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

            //String sql = "SELECT * FROM teachers WHERE id = :id";
            String hql = "from Teachers where id = :id";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Teachers.class);
            Query query = session.createQuery(hql);
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

    //Вывести ФИО всех преподавателей, стаж которых больше 'exp' лет.
    public List<Teachers> getByExperience(int exp) {
        List<Teachers> teachersExp = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT * FROM teachers WHERE id = :id";
            String hql = "from Teachers where experience > :exp";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Teachers.class);
            Query query = session.createQuery(hql);
            query.setParameter("exp", exp);
            teachersExp = query.list();

            //close session with a transaction
            closeTransactionSesstion();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return teachersExp;
    }


    // Вывести список преподавателей
    // и номера групп, в которых они преподают.
    public void getTeachersAndGroupstitles() {
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT DISTINCT teachers.first_name, teachers.last_name, teachers.second_name, groups_st.title FROM teachers " +
                    "JOIN subjects ON teachers.id = subjects.teacher_id " +
                    "JOIN subject_group ON subjects.subject_id = subject_group.subjects_id " +
                    "JOIN groups_st ON subject_group.groups_st_specialty = groups_st.group_id " +
                    "ORDER BY teachers.second_name ";

            Session session = getSession();
            Query query = session.createSQLQuery(sql);

            //Retrieving values ​​in multiple columns
            List<Object[]> teachersGroupsList = (List<Object[]>) query.getResultList();
            //Accessing each object array to retrieve title og group and number of students in it
            for (Object[] teacherGroups : teachersGroupsList) {
                System.out.println("First name = " + (String) teacherGroups[0]);
                System.out.println("Last name = " + (String) teacherGroups[1]);
                System.out.println("Second name = " + (String) teacherGroups[2]);
                System.out.println("Groups title = " + (String) teacherGroups[3]);
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


    // Вывести список преподавателей, стаж которых больше 5 лет
    // и которые могут вести предметы «математика» и «информатика».
    public void getByExperienceAndSubjects(int exp, String subj1, String subj2) {
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT DISTINCT teachers.first_name, teachers.last_name, teachers.second_name FROM teachers" +
                    " LEFT JOIN subjects ON teachers.id = subjects.teacher_id WHERE teachers.EXP > :exp" +
                    " AND (subjects.title = :subj1 OR subjects.title = :subj2)";

            Session session = getSession();
            Query query = session.createSQLQuery(sql);
            query.setParameter("exp", exp);
            query.setParameter("subj1", subj1);
            query.setParameter("subj2", subj2);

            //Retrieving values ​​in multiple columns
            List<Object[]> teachersNames = (List<Object[]>) query.getResultList();
            //Accessing each object array to retrieve title og group and number of students in it
            for (Object[] teacherName : teachersNames) {
                System.out.println("First name = " + (String) teacherName[0]);
                System.out.println("Last name = " + (String) teacherName[1]);
                System.out.println("Second name = " + (String) teacherName[2]);
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
