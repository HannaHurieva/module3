package dao;

import entity.Teachers;

import java.util.List;

public interface TeachersDAO {
    //create
    void add(Teachers teacher);

    //read
    List<Teachers> getAll();

    Teachers getById(int id);

    //update
    void update(Teachers teacher);

    //delete
    void remove(Teachers teacher);

   }
