import entity.Groups;
import entity.Subjects;
import entity.Teachers;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            Subjects sub1 = new Subjects("Информатика", "Лекция", 50, 200);
            Subjects sub2 = new Subjects("Информатика", "Практика", 25, 300);
            Subjects sub3 = new Subjects("Математика", "Лекция", 90, 150);
            Subjects sub4 = new Subjects("Математика", "Практика", 60, 250);
            Subjects sub5 = new Subjects("Базы данных", "Лекция", 40, 250);
            Subjects sub6 = new Subjects("Базы данных", "Практика", 60, 200);

            Groups gr = new Groups("J2", "comp", "on weekdays", 12);
            Groups gr1 = new Groups("J4", "math", "evenings daily", 15);
            Groups gr2 = new Groups("J7", "web", "on weekends", 10);

            Teachers coach1 = new Teachers("Pensov", "Serg", "First", "999-999", 8);
            Teachers coach2 = new Teachers("Slyusar ", "Peter", "Second", "777-777", 5);
            Teachers coach3 = new Teachers("Shmorgun", "Eugene", "Third", "888-888", 10);

            // schedule of subject for coach1
            List<Subjects> subjects1 = new ArrayList<>();
            subjects1.add(sub1);
            subjects1.add(sub2);
            subjects1.add(sub3);
            coach1.setSubjects(subjects1);

            sub1.setTeacher(coach1);
            sub2.setTeacher(coach1);
            sub3.setTeacher(coach1);

            // schedule of subject for coach2
            List<Subjects> subjects2 = new ArrayList<>();
            subjects2.add(sub4);
            subjects2.add(sub5);
            coach2.setSubjects(subjects2);

            sub4.setTeacher(coach2);
            sub5.setTeacher(coach2);

            // schedule of subject for coach3
            List<Subjects> subjects3 = new ArrayList<>();
            subjects2.add(sub1);
            subjects2.add(sub6);
            coach3.setSubjects(subjects3);

            sub1.setTeacher(coach3);
            sub6.setTeacher(coach3);

            // schedule for groups
            gr.addSubject(sub1);
            gr.addSubject(sub2);
            gr.addSubject(sub3);
            gr.addSubject(sub4);
            gr.addSubject(sub5);

            gr1.addSubject(sub3);
            gr1.addSubject(sub4);
            gr1.addSubject(sub6);

            gr2.addSubject(sub1);
            gr2.addSubject(sub2);
            gr2.addSubject(sub5);


            // save the objects
            session.save(sub1);
            session.save(sub2);
            session.save(sub3);
            session.save(sub4);
            session.save(sub5);
            session.save(sub6);

            session.save(gr);
            session.save(gr1);
            session.save(gr2);

            session.save(coach1);
            session.save(coach2);
            session.save(coach3);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}