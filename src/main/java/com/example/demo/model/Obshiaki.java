package com.example.demo.model;

import java.util.ArrayList;

public class Obshiaki extends Account{
    private ArrayList<String> ownerIds;
    public Obshiaki(ArrayList <String>ownerIds) {
        super.isObshiak = true;
        this.ownerIds = ownerIds;
    }
    public void requestTransaction(Transaction transaction, String message) {
        RequestManager manager = new RequestManager(ownerIds, transaction, message);
        manager.sendRequests();
    }
}
