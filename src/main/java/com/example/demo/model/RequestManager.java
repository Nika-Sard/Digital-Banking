package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestManager {
    private ArrayList<String> ownersId;
    private Transaction transaction;
    private String message;

    private HashMap<String, Request> requests;

    public RequestManager(ArrayList<String> ownersId, Transaction transaction, String message) {
        this.ownersId = ownersId;
        this.transaction = transaction;
        this.message = message;
        this.requests = new HashMap<>();
    }

    public void response(Request request, String ownerId) {
        if(request.getStatus()) {
            requests.remove(ownerId);
            if(requests.isEmpty()) {
                transaction.setStatus();
            }
        } else {
            for(String curId : requests.keySet()) {
                User curUser = null; ///
                curUser.removeRequest(requests.get(curId));
            }
        }
    }

    public void sendRequests() {
        for(String ownerId : ownersId) {
            User owner = null; ///get owner
            Request request = new Request(this, transaction, message);
            owner.sendRequest(request);
            requests.put(ownerId, request);
        }
    }
}
