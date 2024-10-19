package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestManager {
    private ArrayList<String> ownersId;
    private Transaction transaction;
    private String message;

    HashMap<String, Request> requestHashMap;

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
            requestHashMap.put(ownerId, request);
        }
    }

    public void response(Request request, String userId) {
        if(request.getStatus()) {
            requestHashMap.remove(userId);
            if(requestHashMap.isEmpty()) {
                transaction.setStatus();
            }
        } else {
            for(String curId: requestHashMap.keySet()) {
                User curUser = null; /// base
                curUser.removeRequest(requestHashMap.get(curUser));
            }
        }
    }
}
