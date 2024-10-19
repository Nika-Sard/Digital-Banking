package com.example.demo.model;

import java.util.ArrayList;

public class RequestManager {
    private ArrayList<String> ownersId;
    private Transaction transaction;
    private String message;

    public RequestManager(ArrayList<String> ownersId, Transaction transaction, String message) {
        this.ownersId = ownersId;
        this.transaction = transaction;
        this.message = message;
    }
    public void sendRequests() {
        for(String ownerId : ownersId) {
            User owner = null; ///get owner
            Request request = new Request(this, transaction, message);
            owner.sendRequest(request);
        }
    }
}
