package hibernate.dao;

import hibernate.entity.Teacher;

import java.util.List;

public interface TeachersDAO {
    //create
    void add(Teacher teacher);

    //read
    List<Teacher> getAll();

    Teacher getById(int id);

    //update
    void update(Teacher teacher);

    //delete
    void remove(Teacher teacher);

   }
