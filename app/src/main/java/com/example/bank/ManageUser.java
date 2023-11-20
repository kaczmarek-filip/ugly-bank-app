package com.example.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa służy do wykonywania operacji na użytkowniku
 * Tworzy listę użytkowników `users`
 */
public class ManageUser {
    public static List<User> users = new ArrayList<>();

    /**
     * @param user przyjmuje użytkownika i dodaje go do listy
     * @see User
     */
    public static void addUser(User user) {
        users.add(user);
    }

    /**
     * @return listę użytkowników
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * @param login przyjmuje login użytkownika
     * @return zwraca obiekt użytkownika o zadanym loginie
     */
    public static User authenticateUser(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     * @return Zwraca listę loginów użytkowników
     */
    public static List<String> getUsersLogin() {
        List<String> usersLogins = new ArrayList<>();
        for (User user : users) {
            usersLogins.add(user.getLogin());
        }
        return usersLogins;
    }
}
