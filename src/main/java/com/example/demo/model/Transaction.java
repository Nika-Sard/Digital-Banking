package com.example.demo.model;

public class Transaction {
    private String transactionId;
    private String senderId;
    private String receiverId;
    private String message;
    private double amount;
    boolean isPending = false;

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

    void setStatus(boolean isPending) {
        this.isPending = isPending;
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
