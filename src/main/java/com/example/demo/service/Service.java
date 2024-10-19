package com.example.demo.service;

import com.example.demo.dao.Dao;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Dao dao;
    Service() {
    }

    public Account getAccount(String id) {
        return dao.getAccount(id);
    }

    public void makeObshiakTransaction(String senderId, String receiverId, double amount, String transactionMessage, String requestMessage) {
        String transactionId = dao.addTransaction(senderId, receiverId, transactionMessage, amount);
        Transaction transaction = dao.getTransaction(transactionId);
        String id = transaction.getSenderId();
        Obshiaki sender = (Obshiaki)(dao.getAccount(id));
        String requestManagerId = dao.addRequestManager(sender.getOwnerIds(), transaction.getTransactionId(),
                                                        requestMessage);
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
        if(manager.hasEveryoneApproved()){
            Transaction transaction = manager.getTransaction();
            makeOrdinaryTransaction(transaction.getSenderId(), transaction.getReceiverId(), transaction.getAmount());
        }
    }

    public void rejectRequest(String requestId) {
        Request request = dao.getRequest(requestId);
        RequestManager manager = request.getManager();
        for(String userId : manager.getOwnersId()) {

        }
    }

//    public void makeObshiaki(ArrayList<String> ownersId) {
//        User user = dao.getUser(userId);
//        String accountId = dao.addAccount(false);
//        dao.addAccountUser(userId, accountId);
//        dao.addUserAccount(userId, accountId);
//    }

    public void joinObshiaki(String userId, String accountId) {
        dao.addAccountUser(userId, accountId);
        dao.addUserAccount(userId, accountId);
    }

    public void addUserAccount(String userId) {
        String accountId = dao.addAccount(true);
        dao.addUserAccount(userId, accountId);
    }

    public void makeOrdinaryTransaction(String senderId, String receiverId, double amount) {
        Account sender = dao.getAccount(senderId);
        Account receiver = dao.getAccount(receiverId);
        if(sender.getBalance() >= amount) {
            dao.withdraw(sender.getAccountId(), amount);
            dao.deposit(receiver.getAccountId(), amount);
        }
    }

    public ArrayList<Transaction> getTransactions(String accountId) {
        return dao.getTransactions(accountId);
    }
}
