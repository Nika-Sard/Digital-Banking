package com.example.demo.dao;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
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
        initDao();
    }

    public void initDao() {
        System.out.println("------------------------------ created dao -----------------------");
        ///adding users
        for(int i = 0; i < 3; i++) {
            String id = Integer.toString(i);
            users.add(new User(id, "user + id"));
        }

        accounts.add(new Account("0"));
        accounts.add(new Account("1"));
        accounts.add(new Account("2"));

        accounts.add((Account) new Obshiaki("3"));
        accounts.getFirst().deposit(10000);
        accounts.get(1).deposit(100);
        accounts.get(2).deposit(500000);
        accounts.get(3).deposit(78);

        Obshiaki ob1 = (Obshiaki)accounts.get(3);

        addUserAccount("0", "0");
        addUserAccount("1", "1");
        addUserAccount("2", "2");

        ob1.addOwnerId("0");
        ob1.addOwnerId("1");
        ob1.addOwnerId("2");
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
        User user = users.get(Integer.parseInt(requestReceiverId));
        user.addPendingRequest(request.getRequestId());
        requests.add(request);
        return request.getRequestId();
    }


    public Request getRequest(String id) {
        return new Request(requests.get(Integer.parseInt(id)));
    }

    public ArrayList<Request> getRequestsByUser(String userId) {
        ArrayList<Request> result = new ArrayList<>();
        for(Request request : requests) {
            if (request == null) continue;
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
        for(Request request : requests) {
            if(request == null) continue;
            if(request.getManager().getRequestManagerId().equals(id)) {
                deleteRequest(request.getRequestId());
            }
        }
        requestManagers.set(Integer.parseInt(id), null);
    }

    public void addApprovedRequestReceiver(String requestManagerId, String receiverId) {
        RequestManager requestManager = requestManagers.get(Integer.parseInt(requestManagerId));
        requestManager.approveRequest(receiverId);
    }
    public void setStatus(String transactionId) {
        transactions.get(Integer.parseInt(transactionId)).setStatus();
    }

    public ArrayList<Transaction> getTransactions(String accountId) {
        ArrayList<Transaction> result = new ArrayList<>();
        for(Transaction transaction : transactions) {
            if((transaction.getSenderId().equals(accountId) || transaction.getReceiverId().equals(accountId)) && !transaction.getStatus()) {
                result.add(new Transaction(transaction));
            }
        }
        return result;
    }
}
