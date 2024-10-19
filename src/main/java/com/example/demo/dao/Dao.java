package com.example.demo.dao;

import com.example.demo.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
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
            account = new Account(String.valueOf(accounts.size()));
        else{
            ArrayList<String> ownerIds = new ArrayList<>();
            account = new Obshiaki(String.valueOf(accounts.size()));
        }
        accounts.add(account);
        return account.getAccountId();
    }

    public Account getAccount(String id) {
        Account acc = new Account(accounts.get(Integer.parseInt(id)));
        if(!acc.getIsObshiak()) {
            return acc;
        }
        Obshiaki obsh = new Obshiaki((Obshiaki) accounts.get(Integer.parseInt(id)));
        return obsh;
    }

    public void deposit(String id, double amount) {
        Account account = accounts.get(Integer.parseInt(id));
        account.deposit(amount);
    }

    public void withdraw(String id, double amount) {
        Account account = accounts.get(Integer.parseInt(id));
        account.withdraw(amount);
    }

    public String addUser(String name) {
        User user = new User(String.valueOf(users.size()), name);
        users.add(user);
        return user.getUserId();
    }

    public User getUser(String id) {
        return new User(users.get(Integer.parseInt(id)));
    }

    public void addUserAccount(String userId, String accountId) {
        User user = users.get(Integer.parseInt(userId));
        user.addAccount(accountId);
    }

    public void addAccountUser(String userId, String accountId) {
        Obshiaki obshiaki = (Obshiaki) accounts.get(Integer.parseInt(accountId));
        obshiaki.addOwnerId(userId);
    }

    public String addTransaction(String senderId, String receiverId, String message, double amount) {
        Transaction transaction = new Transaction(String.valueOf(transactions.size()), senderId, receiverId, message, amount);
        transactions.add(transaction);
        return transaction.getTransactionId();
    }

    public Transaction getTransaction(String id) {
        return new Transaction(transactions.get(Integer.parseInt(id)));
    }

    public String addRequestManager(ArrayList<String> ownersId, String transactionId, String message) {
        Transaction transaction = transactions.get(Integer.parseInt(transactionId));
        RequestManager requestManager = new RequestManager(String.valueOf(requestManagers.size()), ownersId, transaction, message);
        requestManagers.add(requestManager);
        return requestManager.getRequestManagerId();
    }

    public RequestManager getRequestManager(String requestManagerId) {
        return new RequestManager(requestManagers.get(Integer.parseInt(requestManagerId)));
    }

    public String addRequest(String requestReceiverId, String requestManagerId, String transactionId, String message) {
        Transaction transaction = transactions.get(Integer.parseInt(transactionId));
        RequestManager requestManager = requestManagers.get(Integer.parseInt(requestManagerId));
        Request request = new Request(String.valueOf(requests.size()), requestReceiverId, requestManager, transaction, message);
        requests.add(request);
        return request.getRequestId();
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
        requestManager.approveRequest(receiverId);
    }

}
