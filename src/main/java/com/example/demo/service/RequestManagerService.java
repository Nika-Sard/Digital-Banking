package com.example.demo.service;

import com.example.demo.dao.Dao;
import com.example.demo.model.Request;
import com.example.demo.model.RequestManager;
import com.example.demo.model.Transaction;

import java.util.ArrayList;

public class RequestManagerService {
    Dao dao;
    RequestManager manager;
    Service service;
    public RequestManagerService(RequestManager manager, Service service){
        this.manager = manager;
        this.service = service;
    }
    public void approveRequest(String userId) {
        manager.approveRequest(userId);
        if(manager.hasEveryoneApproved())
            service.makeOrdinaryTransaction(manager.getTransaction());
    }
    public void sendRequests() {
        ArrayList<String> ownersId = manager.getOwnersId();
        for(String id : ownersId) {
            String requestId = dao.addRequest(id, manager.getRequestManagerId(),
                    manager.getTransaction().getTransactionId(), manager.getMessage());
        }
    }
}
