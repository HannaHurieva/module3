import entity.Groups;
import entity.Subjects;
import entity.Teachers;
import services.GroupsService;
import services.SubjectsService;
import services.TeachersService;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        SubjectsService subjectsService = new SubjectsService();
        GroupsService groupsService = new GroupsService();
        TeachersService teachersService = new TeachersService();

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
        subjects1.add(sub2);
        subjects1.add(sub3);
        coach1.setSubjects(subjects1);

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
        subjects3.add(sub1);
        subjects3.add(sub6);
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
        teachersService.add(coach1);
        teachersService.add(coach2);
        teachersService.add(coach3);

        groupsService.add(gr);
        groupsService.add(gr1);
        groupsService.add(gr2);


        System.out.println("\n\nЗАПРОС: Вывести список всех групп.");
        List<Groups> groupsList = groupsService.getAll();
        for (Groups group : groupsList) {
            System.out.println(group.toString());
        }

        System.out.println("\n\nЗАПРОС: Вывести группу по id.");
        System.out.println("Введите значение id :");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        Groups MyGroup = groupsService.getById(id);
        System.out.println(MyGroup.toString());


        System.out.println("\n\nЗАПРОС: Вывести список названий групп с указанием количества студентов.");
        System.out.println("        Отсортировать по названию группы.");
        groupsService.getSortingByGroupsTitles_WithNumberOfStudents();


        System.out.println("\n\nЗАПРОС: Вывести преподавателя по id.");
        System.out.println("Введите значение id :");
        int teacherId = in.nextInt();
        Teachers coach = teachersService.getById(teacherId);
        System.out.println(coach.toString());


        System.out.println("\n\nЗАПРОС: Вывести ФИО всех преподавателей, стаж которых больше 'exp' лет.");
        System.out.println("Введите значение стажа - exp :");
        int experience = in.nextInt();
        List<Teachers> teachersExp = teachersService.getByExperience(experience);
        for (Teachers teacher : teachersExp) {
            System.out.println(teacher.toString());
        }


        System.out.println("\n\nЗАПРОС: Вывести ФИО всех преподавателей ");
        System.out.println("        и номера групп, в которых они преподают.");
        teachersService.getTeachersAndGroupstitles();


        System.out.println("\n\nЗАПРОС: Вывести ФИО всех преподавателей, стаж которых больше 'exp' лет.");
        System.out.println("        и которые могут вести предметы 'subj1' или 'subj2'.");
        System.out.println("Введите значение стажа - exp :");
        int exp = in.nextInt();
        String split = in.nextLine();
        System.out.println("и название первого предмета - subj1 :");
        String subj1 = in.nextLine();
        System.out.println("Введите название второго предмета - subj2 :");
        String subj2 = in.nextLine();
        teachersService.getByExperienceAndSubjects(exp, subj1, subj2);


        System.out.println("\n\nЗАПРОС: Вывести предмет по id.");
        System.out.println("Введите значение id :");
        int subjectId = in.nextInt();
        Subjects subjId = subjectsService.getById(subjectId);
        System.out.println(subjId.toString());
        in.close();


        HibernateUtil.shutdown();
    }
}