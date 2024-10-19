package com.example.demo.model;

import org.springframework.web.bind.annotation.RequestMethod;

public class Request{
    private RequestManager manager;
    private Transaction transaction;
    private String message;

    public RequestManager getManager() {
        return manager;
    }

    public Request(Request request) {
        this.manager = new RequestManager(request.manager);
        this.transaction = new Transaction(request.transaction);
        this.message = request.message;
    }

    public Request(RequestManager manager, Transaction transaction, String message) {
        this.transaction = transaction;
        this.message = message;
        this.manager = manager;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getMessage() {
        return message;
    }
}
