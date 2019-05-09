package hibernate.dao;

import hibernate.entity.GroupSt;

import java.util.List;

public interface GroupsDAO {
    //create
    void add(GroupSt group);

    //read
    List<GroupSt> getAll();

    GroupSt getById(int id);

    //update
    void update(GroupSt group);

    //delete
    void remove(GroupSt group);

}

