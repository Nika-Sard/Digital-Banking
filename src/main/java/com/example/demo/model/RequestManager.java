package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestManager {
    private ArrayList<String> ownersId;
    private Transaction transaction;
    private String message;
    ArrayList<String> approvedRequestIds;

    public RequestManager(RequestManager requestManager) {
        this.message = requestManager.message;
        this.transaction = new Transaction(requestManager.transaction);
        this.approvedRequestIds = (ArrayList<String>) requestManager.approvedRequestIds.clone();
        this.ownersId = (ArrayList<String>) requestManager.ownersId.clone();
    }

    public RequestManager(ArrayList<String> ownersId, Transaction transaction, String message) {
        this.ownersId = ownersId;
        this.transaction = transaction;
        this.message = message;
        this.approvedRequestIds = new ArrayList<>();
    }

    public void approveRequest(String requestReceiverId) {
        approvedRequestIds.add(requestReceiverId);
    }

    public boolean hasEveryoneApproved(String requestReceivedId) {
       return approvedRequestIds.size() == ownersId.size();
    }

    public ArrayList<String> getOwnersId() {
        return ownersId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getMessage() {
        return message;
    }
}
