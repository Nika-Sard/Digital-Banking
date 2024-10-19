package com.example.demo.service;

import com.example.demo.dao.Dao;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class MyService {
    @Autowired
    private Dao dao;

    public void doTransaction(String senderId, String receiverId, double amount) {

    }

    public ArrayList<Account> getAccounts(String userId) {
        return null;
    }

    public void requestTransaction(String requesterId, String senderId, String receiverId, double amount, String message) {

    }

    public ArrayList<Transaction> getTransactions(String accountId) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1", "1", "2", "hello", 100));
        return transactions;
    }

    public Account getAccount(String userId, String accountId) {
        Account account = new Account("1");
        account.deposit(1000);
        return account;
    }
}
