package dao;

import entity.Groups;

import java.util.List;

public interface GroupsDAO {
    //create
    void add(Groups group);

    //read
    List<Groups> getAll();

    Groups getById(int id);

    //update
    void update(Groups group);

    //delete
    void remove(Groups group);

}

