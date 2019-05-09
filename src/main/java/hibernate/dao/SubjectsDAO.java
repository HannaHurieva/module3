package hibernate.dao;

import hibernate.entity.Subject;

import java.util.List;

public interface SubjectsDAO {
    //create
    void add(Subject subject);

    //read
    List<Subject> getAll();

    Subject getById(int id);

    //update
    void update(Subject subject);

    //delete
    void remove(Subject subject);

}

