package hibernate;

import hibernate.entity.GroupSt;
import hibernate.entity.Subject;
import hibernate.entity.Teacher;
import hibernate.services.GroupsService;
import hibernate.services.SubjectsService;
import hibernate.services.TeachersService;
import hibernate.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        SubjectsService subjectsService = new SubjectsService();
        GroupsService groupsService = new GroupsService();
        TeachersService teachersService = new TeachersService();

        Subject sub1 = new Subject("Информатика", "Лекция", 50, 200);
        Subject sub2 = new Subject("Информатика", "Практика", 25, 300);
        Subject sub3 = new Subject("Математика", "Лекция", 90, 150);
        Subject sub4 = new Subject("Математика", "Практика", 60, 250);
        Subject sub5 = new Subject("Базы данных", "Лекция", 40, 250);
        Subject sub6 = new Subject("Базы данных", "Практика", 60, 200);

        GroupSt gr = new GroupSt("J2", "comp", "on weekdays", 12);
        GroupSt gr1 = new GroupSt("J4", "math", "evenings daily", 15);
        GroupSt gr2 = new GroupSt("J7", "web", "on weekends", 10);

        Teacher coach1 = new Teacher("Pensov", "Serg", "First", "999-999", 8);
        Teacher coach2 = new Teacher("Slyusar ", "Peter", "Second", "777-777", 5);
        Teacher coach3 = new Teacher("Shmorgun", "Eugene", "Third", "888-888", 10);

        // schedule of subject for coach1
        List<Subject> subjects1 = new ArrayList<>();
        subjects1.add(sub2);
        subjects1.add(sub3);
        coach1.setSubjects(subjects1);

        sub2.setTeacher(coach1);
        sub3.setTeacher(coach1);

        // schedule of subject for coach2
        List<Subject> subjects2 = new ArrayList<>();
        subjects2.add(sub4);
        subjects2.add(sub5);
        coach2.setSubjects(subjects2);

        sub4.setTeacher(coach2);
        sub5.setTeacher(coach2);

        // schedule of subject for coach3
        List<Subject> subjects3 = new ArrayList<>();
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
        List<GroupSt> groupsList = groupsService.getAll();
        for (GroupSt group : groupsList) {
            System.out.println(group.toString());
        }

        System.out.println("\n\nЗАПРОС: Вывести группу по id.");
        System.out.println("Введите значение id :");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        GroupSt MyGroup = groupsService.getById(id);
        System.out.println(MyGroup.toString());


        System.out.println("\n\nЗАПРОС: Вывести список названий групп с указанием количества студентов.");
        System.out.println("        Отсортировать по названию группы.");
        groupsService.getSortingByGroupsTitlesWithNumberOfStudents();


        System.out.println("\n\nЗАПРОС: Вывести преподавателя по id.");
        System.out.println("Введите значение id :");
        int teacherId = in.nextInt();
        Teacher coach = teachersService.getById(teacherId);
        System.out.println(coach.toString());


        System.out.println("\n\nЗАПРОС: Вывести ФИО всех преподавателей, стаж которых больше 'exp' лет.");
        System.out.println("Введите значение стажа - exp :");
        int experience = in.nextInt();
        List<Teacher> teachersExp = teachersService.getByExperience(experience);
        for (Teacher teacher : teachersExp) {
            System.out.println(teacher.getLastName() + "   " + teacher.getFirstName() + "   " + teacher.getSecondName());
        }


        System.out.println("\n\nЗАПРОС: Вывести ФИО всех преподавателей ");
        System.out.println("        и номера групп, в которых они преподают.");
        teachersService.getTeachersAndGroupsTitles();


        System.out.println("\n\nЗАПРОС: Посчитать нагрузку для преподавателя 'last_name'");
        String split = in.nextLine(); // service for '/n'
        System.out.println("Введите фамилию преподавателя - last_name :");
        String lastName = in.nextLine();
        int workingHours = teachersService.getCostHoursByTeacher(lastName);
        System.out.println("Итого количество рабочих часов для преподавателя " + lastName + " = " + workingHours);


        System.out.println("\n\nЗАПРОС: Вывести ФИО всех преподавателей, стаж которых больше 'exp' лет.");
        System.out.println("        и которые могут вести предметы 'subj1' или 'subj2'.");
        System.out.println("Введите значение стажа - exp :");
        int exp = in.nextInt();
        split = in.nextLine(); // service for '/n'
        System.out.println("и название первого предмета - subj1 :");
        String subj1 = in.nextLine();
        System.out.println("Введите название второго предмета - subj2 :");
        String subj2 = in.nextLine();
        teachersService.getByExperienceAndSubjects(exp, subj1, subj2);


        System.out.println("\n\nЗАПРОС: Вывести список предметов для каждой специальности");
        System.out.println("        с указанием количества отведенных на них часов.");
        groupsService.getSubjectsHoursListBySpeciality();


        System.out.println("\n\nЗАПРОС: Вывести предмет по id.");
        System.out.println("Введите значение id :");
        int subjectId = in.nextInt();
        Subject subjId = subjectsService.getById(subjectId);
        System.out.println(subjId.toString());
        in.close();


        HibernateUtil.shutdown();
    }
}