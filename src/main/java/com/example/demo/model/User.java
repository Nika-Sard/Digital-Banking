package com.example.demo.model;

import java.util.ArrayList;

public class User {
    private ArrayList<String> accounts;
    private String firstName;
    private String lastName;
    private String userId;

    private ArrayList<Request> requests;

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
    public User(String firstName, String lastName, String userId, ArrayList<Request> requests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.requests = requests;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void sendRequest(Request request) {
        requests.add(request);
    }

    public void answerRequest(Request request, boolean accepts) {
        request.setStatus(accepts);
        request.getManager().response(request, userId);
    }

    public User(User user) {
        this.accounts = user.getAccounts();
        this.userId = user.getUserId();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.requests = user.getRequests();
    }

    public void removeRequest(Request request) {
        requests.remove(request);
    }
}
