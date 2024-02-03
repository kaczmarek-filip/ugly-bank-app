package com.example.bank;

public class UserParser {
    public String createSaveQuery(User user){

        String query = "INSERT INTO `users`(`login`, `password`, `balanace`, `deposit`, `cardNumber`) VALUES ('" + user.getLogin() +"','"+ user.getPassword() +"','"+ user.getBalance() +"','"+ user.getDeposit() +"','"+ user.getCardNumber() +"')";

        return query;
    }
}
