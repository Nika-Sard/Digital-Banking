package com.example.demo.service;

import com.example.demo.dao.Dao;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;

public class TransactionService {
    Dao dao;

    private boolean makeOrdinaryTransaction(Transaction transaction) {
        Account sender = dao.getAccount(transaction.getSenderId());
        Account receiver = dao.getAccount(transaction.getReceiverId());
        if(sender.getBalance() >= transaction.getAmount()) {
            dao.withdraw(sender.getAccountId(), transaction.getAmount());
            dao.deposit(receiver.getAccountId(), transaction.getAmount());
            return true;
        }
        return false;
    }

}
