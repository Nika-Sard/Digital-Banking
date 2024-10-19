package com.example.demo.model;

public class Account {
    private String accountId;
    private double balance;
    protected boolean isObshiak;
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
        isObshiak = false;
    }
    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0;
        isObshiak = false;
    }

    public Account(Account account) {
        this.accountId = account.accountId;
        this.balance = account.balance;
        this.isObshiak = account.isObshiak;
    }
}
