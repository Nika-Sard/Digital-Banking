package com.example.demo.model;

public class Request{
    Transaction transaction;
    boolean status = false;
    String message;

    public Request(Transaction transaction, String message) {
        this.transaction = transaction;
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
