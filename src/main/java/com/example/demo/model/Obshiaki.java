package com.example.demo.model;

import java.util.ArrayList;

public class Obshiaki extends Account{
    private ArrayList<String> ownersIds;
    public Obshiaki(ArrayList <String> ownerIds) {
        this.ownersIds = ownerIds;
    }
    public void requestTransactions() {

    }
}
