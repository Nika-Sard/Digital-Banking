package com.example.demo.model;

import java.util.ArrayList;

public class Obshiaki extends Account{
    private ArrayList<String> ownerIds;

    public Obshiaki(Obshiaki obshiaki) {
        this.ownerIds = (ArrayList<String>) obshiaki.ownerIds.clone();
        super.isObshiak = true;
    }

    public Obshiaki(ArrayList <String>ownerIds) {
        super.isObshiak = true;
        this.ownerIds = ownerIds;
    }
}
