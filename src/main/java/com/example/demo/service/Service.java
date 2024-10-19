package com.example.demo.service;

import com.example.demo.dao.Dao;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Dao dao;
    public Account getAccount(String id) {
        return dao.getAccount(id);
    }


    public void makeObshiakTransaction(String senderId, String receiverId, double amount, String transactionMessage, String requestMessage) {
        String transactionId = dao.addTransaction(senderId, receiverId, transactionMessage, amount);
        Transaction transaction = dao.getTransaction(transactionId);
        String id = transaction.getSenderId();
        Obshiaki sender = (Obshiaki)(dao.getAccount(id));
        String requestManagerId = dao.addRequestManager(sender.getOwnerIds(), transaction.getTransactionId(), requestMessage);
        RequestManager manager = dao.getRequestManager(requestManagerId);
        sendRequests(manager);
    }

    public void makeTransaction(String senderId, String receiverId, double amount,
                                String message) {
        String transactionId = dao.addTransaction(senderId, receiverId, message, amount);

        Transaction transaction = dao.getTransaction(transactionId);
        makeOrdinaryTransaction(transaction);
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
            makeOrdinaryTransaction(manager.getTransaction());
            dao.deleteRequestManager(manager.getRequestManagerId());
            Transaction transaction = manager.getTransaction();
            makeOrdinaryTransaction(transaction);
        }
    }

    public void rejectRequest(String requestId) {
        Request request = dao.getRequest(requestId);
        RequestManager manager = request.getManager();
        dao.deleteRequestManager(manager.getRequestManagerId());
    }

    public void makeObshiaki(ArrayList <String> userIds) {
        String accountId = dao.addAccount(false);
        for(String userId : userIds){
            User user = dao.getUser(userId);
            dao.addAccountUser(userId, accountId);
            dao.addUserAccount(userId, accountId);
        }
    }

    public void addUserAccount(String userId) {
        String accountId = dao.addAccount(true);
        dao.addUserAccount(userId, accountId);
    }

    public void makeOrdinaryTransaction(Transaction transaction) {
        Account sender = dao.getAccount(transaction.getSenderId());
        Account receiver = dao.getAccount(transaction.getReceiverId());
        if(sender.getBalance() >= transaction.getAmount()) {
            dao.setStatus(transaction.getTransactionId());
            dao.withdraw(sender.getAccountId(), transaction.getAmount());
            dao.deposit(receiver.getAccountId(), transaction.getAmount());
        }
    }

    public ArrayList<Transaction> getTransactions(String accountId) {
        return dao.getTransactions(accountId);
    }

    public ArrayList<Request> getRequests(String userId) {
        return dao.getRequestsByUser(userId);
    }
}
