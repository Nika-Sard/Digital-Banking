package com.example.demo.model;

import java.util.ArrayList;

public class User {
    private ArrayList<String> accounts;
    private String name;
    private String userId;
    private ArrayList<String>pendingRequests;
    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<String> getAccounts() {
        return accounts;
    }
    public void addAccount(String accountId) {
        accounts.add(accountId);
    }
    public void removeAccount(String accountId) {
        accounts.remove(accountId);
    }
    public User(String id, String name) {
        this.name = name;
        this.userId = id;
        this.accounts = new ArrayList<>();
    }

    public User(User user) {
        this.accounts = user.getAccounts();
        this.userId = user.getUserId();
        this.name = user.name;
    }

    public void addPendingRequest(String requestId) {
        pendingRequests.add(requestId);
    }
    public void removePendingRequest(String requestId) {
        pendingRequests.remove(requestId);
    }
}
