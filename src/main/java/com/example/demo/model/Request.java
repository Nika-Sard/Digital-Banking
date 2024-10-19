package com.example.demo.model;

import org.springframework.web.bind.annotation.RequestMethod;

public class Request{
    private RequestManager manager;
    private Transaction transaction;
    private String message;
    private final String requestId;
    private final String requestReceiverId;

    public RequestManager getManager() {
        return manager;
    }

    public Request(Request request) {
        this.manager = new RequestManager(request.manager);
        this.transaction = new Transaction(request.transaction);
        this.message = request.message;
        this.requestId = request.requestId;
        this.requestReceiverId = request.requestReceiverId;
    }

    public Request(String requestId, String requestReceiverId, RequestManager manager, Transaction transaction, String message) {
        this.transaction = transaction;
        this.message = message;
        this.manager = manager;
        this.requestId = requestId;
        this.requestReceiverId = requestReceiverId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getRequestReceiverId() {
        return requestReceiverId;
    }
}
