package com.example.demo.model;

public class Account {
    private String accountId;
    private double balance;

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        balance += amount;
    }
    public void withdraw(double amount) {
        balance -= amount;
    }

    public Account(){

    }

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0;
    }
}
