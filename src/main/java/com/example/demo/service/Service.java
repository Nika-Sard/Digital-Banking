package com.example.demo.service;

import com.example.demo.dao.Dao;
import com.example.demo.model.*;

import java.util.ArrayList;

public class Service {
    Dao dao;
    Service() {
        dao = new Dao();
    }

    public Account getAccount(String id) {
        return dao.getAccount(id);
    }

    public void makeObshiakTransaction(String transactionId, String message) {
        Transaction transaction = dao.getTransaction(transactionId);
        String id = transaction.getSenderId();
        Obshiaki sender = (Obshiaki)(dao.getAccount(id));
        String requestManagerId = dao.addRequestManager(sender.getOwnerIds(), transaction.getTransactionId(),
                                                        message);
        RequestManager manager = dao.getRequestManager(requestManagerId);
        sendRequests(manager);
        //RequestManagerService managerService = new RequestManagerService(manager, this);
    }

    private void sendRequests(RequestManager manager) {
        ArrayList<String> ownersId = manager.getOwnersId();
        for(String id : ownersId) {
            String requestId = dao.addRequest(id, manager.getRequestManagerId(),
                    manager.getTransaction().getTransactionId(), manager.getMessage());
        }
    }

    public void approveRequest(String requestId) {
        Request request = dao.getRequest(requestId);
        RequestManager manager = request.getManager();
        dao.addApprovedRequestReceiver(manager.getRequestManagerId(), request.getRequestReceiverId());
        if(manager.hasEveryoneApproved())
            makeOrdinaryTransaction(manager.getTransaction());
    }

    public void rejectRequest(String requestId) {
        Request request = dao.getRequest(requestId);
        RequestManager manager = request.getManager();
        for(String userId : manager.getOwnersId()) {
            dao.removeRequest(userId, requestId);
        }
        dao.removeRequestManager(manager.getRequestManagerId());
    }

    public void makeObshiaki(String userId) {
        User user = dao.getUser(userId);
        String accountId = dao.addAccount(false);
        dao.addAccountUser(userId, accountId);
        dao.addUserAccount(userId, accountId);
    }

    public void joinObshiaki(String userId, String accountId) {
        dao.addAccountUser(userId, accountId);
        dao.addUserAccount(userId, accountId);
    }

    public void addUserAccount(String userId) {
        String accountId = dao.addAccount(true);
        dao.addUserAccount(userId, accountId);
    }

    public void makeOrdinaryTransaction(Transaction transaction) {
        Account sender = dao.getAccount(transaction.getSenderId());
        Account receiver = dao.getAccount(transaction.getReceiverId());
        if(sender.getBalance() >= transaction.getAmount()) {
            dao.withdraw(sender.getAccountId(), transaction.getAmount());
            dao.deposit(receiver.getAccountId(), transaction.getAmount());
        }
    }

}
