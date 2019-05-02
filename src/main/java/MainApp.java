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

            Groups gr1 = new Groups("J5", "math","budget",15);
            Groups gr2 = new Groups("J7", "web","contract",20);

            Teachers coach1 = new Teachers("Pensov","Serg","First","999-999",10);
            Teachers coach2 = new Teachers("Shmorgun","Eugene","First","888-888",15);

            List<Subjects> subjects1 = new ArrayList<>();
            subjects1.add(sub1);
            subjects1.add(sub3);
            coach1.setSubjects(subjects1);

            List<Subjects> subjects2 = new ArrayList<>();
            subjects2.add(sub2);
            subjects2.add(sub4);
            coach2.setSubjects(subjects2);


            // save the objects
            session.save(sub1);
            session.save(sub2);
            session.save(sub3);
            session.save(sub4);

            session.save(gr1);
            session.save(gr2);

            session.save(coach1);
            session.save(coach2);


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