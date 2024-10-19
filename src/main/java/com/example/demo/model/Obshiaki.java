package com.example.demo.model;

import java.util.ArrayList;

public class Obshiaki extends Account{
    private ArrayList<String> ownerIds;
    public Obshiaki(ArrayList <String>ownerIds) {
        this.ownerIds = ownerIds;
    }
    @Override
    public void requestTransaction(Transaction transaction) {
        RequestManager manager = new RequestManager(ownerIds, transaction);
        manager.sendRequests();
    }
}
