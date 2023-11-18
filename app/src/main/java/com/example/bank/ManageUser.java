package com.example.bank;

import java.util.ArrayList;
import java.util.List;

public class ManageUser {
    private static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }
}
