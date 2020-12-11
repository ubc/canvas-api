package edu.ksu.canvas.model.audit;

import edu.ksu.canvas.model.User;

import java.util.ArrayList;
import java.util.List;

public class GradeChangeLinked {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }
}
