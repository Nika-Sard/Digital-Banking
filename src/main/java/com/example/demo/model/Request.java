package com.example.demo.model;

import org.springframework.web.bind.annotation.RequestMethod;

public class Request{
    private RequestManager manager;
    private Transaction transaction;
    boolean status = false; // true if accept
    private String message;

    public RequestManager getManager() {
        return manager;
    }

    public Request(RequestManager manager, Transaction transaction, String message) {
        this.transaction = transaction;
        this.message = message;
        this.manager = manager;
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
