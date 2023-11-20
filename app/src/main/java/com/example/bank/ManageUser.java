package com.example.bank;

import java.util.ArrayList;
import java.util.List;
public class ManageUser {
    public static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<User> getUsers() {
        return users;
    }
    public static User authenticateUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        // Zwróć null, jeśli użytkownik o podanym loginie i haśle nie został znaleziony
        return null;
    }
    public static List<String> getUsersLogin() {
        List<String> usersLogins = new ArrayList<>();
        for (User user : users) {
            usersLogins.add(user.getLogin());
        }
        return usersLogins;
    }
}
