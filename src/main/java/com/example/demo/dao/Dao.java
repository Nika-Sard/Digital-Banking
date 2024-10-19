package com.example.demo.dao;

import com.example.demo.model.*;

import java.util.ArrayList;


public class Dao {
    private final ArrayList<Account> accounts;
    private final ArrayList<User> users;
    private final ArrayList<Transaction> transactions;
    private final ArrayList<Request> requests;
    private final ArrayList<RequestManager> requestManagers;
    public Dao() {
        this.requests = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.users = new ArrayList<>();
        this.requestManagers = new ArrayList<>();
    }

    public String addAccount(boolean isUserAccount) {
        Account account = null;
        if(isUserAccount)
            account = new Account(String.valueOf(accounts.size()), true);
        else
            account = new Obshiaki(String.valueOf(accounts.size()), false);
        accounts.add(account);
        return account.getId();
    }

    public Account getAccount(String id) {
        return new Account(accounts.get(Integer.parseInt(id)));
    }

    public void deposit(String id, int amount) {
        Account account = accounts.get(Integer.parseInt(id));
        account.deposit(amount);
    }

    public void withdraw(String id, int amount) {
        Account account = accounts.get(Integer.parseInt(id));
        account.withdraw(amount);
    }

    public String addUser(String name) {
        User user = new User(String.valueOf(users.size()), name);
        users.add(user);
        return user.getId();
    }

    public User getUser(String id) {
        return new User(users.get(Integer.parseInt(id)));
    }

    public void addUserAccount(String userId, String accountId) {
        User user = users.get(Integer.parseInt(userId));
        Account account = accounts.get(Integer.parseInt(accountId));
        user.addAccount(account);
    }

    public String addTransaction(String senderId, String receiverId, String message, int amount) {
        Transaction transaction = new Transaction(String.valueOf(transactions.size()), senderId, receiverId, message, amount);
        transactions.add(transaction);
        return transaction.getId();
    }

    public Transaction getTransaction(String id) {
        return new Transaction(transactions.get(Integer.parseInt(id)));
    }

    public String addRequestManager(String transactionId, String message, String[] ownersId) {
        Transaction transaction = transactions.get(Integer.parseInt(transactionId));
        RequestManager requestManager = new RequestManager(String.valueOf(requestManagers.size()), transaction, message, ownersId);
        requestManagers.add(requestManager);
        return requestManager.getId();
    }

    public RequestManager getRequestManager(String requestManagerId) {
        return new RequestManager(requestManagers.get(Integer.parseInt(requestManagerId)));
    }

    public String addRequest(String transactionId, String message, String requestReceiverId, String requestManagerId) {
        Transaction transaction = transactions.get(Integer.parseInt(transactionId));
        Request request = new Request(String.valueOf(requests.size()), transaction, message, requestReceiverId, requestManagerId);
        requests.add(request);
        return request.getId();
    }

    public Request getRequest(String id) {
        return new Request(requests.get(Integer.parseInt(id)));
    }

    public ArrayList<Request> getRequestsByUser(String userId) {
        ArrayList<Request> result = new ArrayList<>();
        for(Request request : requests) {
            if (request.getRequestReceiverId().equals(userId)) {
                result.add(new Request(request));
            }
        }
        return result;
    }

    public void deleteRequest(String id) {
        requests.set(Integer.parseInt(id), null);
    }

    public void deleteRequestManager(String id) {
        requestManagers.set(Integer.parseInt(id), null);
    }

    public void addApprovedRequestReceiver(String requestManagerId, String receiverId) {
        RequestManager requestManager = requestManagers.get(Integer.parseInt(requestManagerId));
        requestManager.addApprovedRequestReceiver(receiverId);
    }

}
