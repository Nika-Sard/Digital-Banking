package com.example.demo.model;

import java.lang.invoke.LambdaMetafactory;

public class Transaction {
    private String transactionId;
    private String senderId;
    private String receiverId;
    private String message;
    private double amount;
    boolean isPending = true;

    public Transaction(String transactionId, String senderId, String receiverId, String message, double amount) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.amount = amount;
    }

    public Transaction(Transaction transaction) {
        this.isPending = transaction.getStatus();
        this.amount = transaction.getAmount();
        this.message = transaction.getMessage();
        this.senderId = transaction.getSenderId();
        this.receiverId = transaction.getReceiverId();
        this.transactionId = transaction.getTransactionId();
    }

    void setStatus() throws Exception {
        if(isPending) {
            throw new Exception("already set to true");
        }
        this.isPending = false;
        ///TODO : get from the database
        Account senderAccount = null;
        Account recieverAccount = null;
        senderAccount.withdraw(amount);
        recieverAccount.deposit(amount);
    }

    public Transaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }

    public boolean getStatus() {
        return isPending;
    }
}
