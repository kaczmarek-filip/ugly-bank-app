package com.example.bank;

import static com.example.bank.Cards.generateCardNumber;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String login;
    private final String password;
    private double balance;
    private double deposit;

    private String cardNumber;

    public User(String login, String password){
        this.login = login;
        this.password = password;

        cardNumber = generateCardNumber();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }
    public void minusBalance(double balance) {
        this.balance -= balance;
    }

    public String getBalance() {
        return String.valueOf(balance);
    }

    public String getDeposit() {
        return String.valueOf(deposit);
    }

    public void setDeposit(double deposit) {
        this.deposit += deposit;
    }
    public void minusDeposit(double deposit) {
        this.deposit -= deposit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
