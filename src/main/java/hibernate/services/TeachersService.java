package hibernate.services;

import hibernate.dao.TeachersDAO;
import hibernate.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class TeachersService extends SessionUtil implements TeachersDAO {
    //create
    public void add(Teacher teacher) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.save(teacher);

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
    public List<Teacher> getAll() {
        List<Teacher> teachersList = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT * FROM teachers";
            String hql = "FROM Teacher";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Teacher.class);
            Query query = session.createQuery(hql);
            teachersList = query.list();

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return teachersList;
    }


    public Teacher getById(int id) {
        Teacher teacher = new Teacher();
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT * FROM teachers WHERE id = :id";
            String hql = "from Teacher where id = :id";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Teacher.class);
            Query query = session.createQuery(hql);
            query.setParameter("id", id);

            teacher = (Teacher) query.getSingleResult();

            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return teacher;
    }

    //Вывести ФИО всех преподавателей, стаж которых больше 'exp' лет.
    public List<Teacher> getByExperience(int exp) {
        List<Teacher> teachersExp = new ArrayList<>();
        try {
            //open session with a transaction
            openTransactionSession();

            //String sql = "SELECT teachers.last_name, teachers.first_name, teachers.second_name FROM teachers WHERE teachers.experience > :exp";
            String hql = "from Teacher where experience > :exp";

            Session session = getSession();
            //Query query = session.createNativeQuery(sql).addEntity(Teacher.Class);
            Query query = session.createQuery(hql);
            query.setParameter("exp", exp);
            teachersExp = query.list();

            //close session with a transaction
            closeTransactionSession();
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
    public void getTeachersAndGroupsTitles() {
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT DISTINCT teachers.last_name, teachers.first_name, teachers.second_name, groups_st.title FROM teachers " +
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
                System.out.println("Last name = " + (String) teacherGroups[0]);
                System.out.println("First name = " + (String) teacherGroups[1]);
                System.out.println("Second name = " + (String) teacherGroups[2]);
                System.out.println("GroupSt title = " + (String) teacherGroups[3]);
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

    // Посчитать нагрузку для преподавателя 'last_name'.
    public int getCostHoursByTeacher(String lastName) {
        int workingHours = 0;
        int costHoursTotal = 0;
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT DISTINCT teachers.last_name, subjects.`type`, subjects.hours, subjects.cost_per_hour FROM teachers " +
                    "JOIN subjects ON teachers.id = subjects.teacher_id WHERE teachers.last_name= :lastName";

            Session session = getSession();
            Query query = session.createSQLQuery(sql);
            query.setParameter("lastName", lastName);

            //Retrieving values ​​in multiple columns
            List<Object[]> costHours = (List<Object[]>) query.getResultList();
            //Accessing each object array to retrieve title og group and number of students in it
            for (Object[] costPerHour : costHours) {
                System.out.println("Teacher's Last name = " + (String) costPerHour[0]);
                System.out.println("Subject's type = " + (String) costPerHour[1]);
                System.out.println("Total hours = " + (Integer) costPerHour[2]);
                System.out.println("Cost per hour = " + (Integer) costPerHour[3]);
                System.out.println();

                workingHours += (Integer) costPerHour[2];
                costHoursTotal += (Integer) costPerHour[2] * (Integer) costPerHour[3];
            }
            System.out.println("Total working hours for teacher " + lastName + " = " + workingHours);
            System.out.println("Total payment for teacher " + lastName + " = " + costHoursTotal);
            //close session with a transaction
            closeTransactionSession();
        } catch (Exception e) {
            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return workingHours;
    }


    // Вывести список преподавателей, стаж которых больше 5 лет
    // и которые могут вести предметы «математика» и «информатика».
    public void getByExperienceAndSubjects(int exp, String subj1, String subj2) {
        try {
            //open session with a transaction
            openTransactionSession();

            String sql = "SELECT DISTINCT teachers.last_name, teachers.first_name, teachers.second_name FROM teachers" +
                    " JOIN subjects ON teachers.id = subjects.teacher_id WHERE teachers.experience > :exp" +
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
                System.out.println("Last name = " + (String) teacherName[0]);
                System.out.println("First name = " + (String) teacherName[1]);
                System.out.println("Second name = " + (String) teacherName[2]);
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
    public void update(Teacher teacher) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.update(teacher);

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
    public void remove(Teacher teacher) {
        try {
            //open session with a transaction
            openTransactionSession();

            Session session = getSession();
            session.remove(teacher);

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
