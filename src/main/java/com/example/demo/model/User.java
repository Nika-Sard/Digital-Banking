package com.example.demo.model;

import java.util.ArrayList;

public class User {
    private ArrayList<String> accounts;
    private String firstName;
    private String lastName;
    private String userId;
    public String getFirstName() {
        return firstName;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<String> getAccounts() {
        return accounts;
    }

    public String getLastName() {
        return lastName;
    }
    public void addAccount(String accountId) {
        accounts.add(accountId);
    }
    public void removeAccount(String accountId) {
        accounts.remove(accountId);
    }
    public User(String firstName, String lastName, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public User(User user) {
        this.accounts = user.getAccounts();
        this.userId = user.getUserId();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
    }

}
