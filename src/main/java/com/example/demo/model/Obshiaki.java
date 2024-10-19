package com.example.demo.model;

import java.util.ArrayList;

public class Obshiaki extends Account{
    private ArrayList<String> ownerIds;

    public Obshiaki(Obshiaki obshiaki) {
        this.ownerIds = (ArrayList<String>) obshiaki.ownerIds.clone();
        super.isObshiak = true;
        super.accountId = obshiaki.accountId;
        super.balance = obshiaki.balance;
    }

    public Obshiaki(String accountId) {
        super.isObshiak = true;
        super.accountId = accountId;
        this.ownerIds = new ArrayList<String>();
        super.balance = 0;
    }

    public ArrayList<String> getOwnerIds() {
        return ownerIds;
    }

    public void setOwnerIds(ArrayList<String> ownerIds) {
        this.ownerIds = ownerIds;
    }

    public void addOwnerId(String ownerId) {
        this.ownerIds.add(ownerId);
    }
}
