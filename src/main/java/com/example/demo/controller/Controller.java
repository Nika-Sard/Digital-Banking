package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    @Autowired
    private MyService service;

    @GetMapping("/getAccount/{userId}/{accountId}")
    public Account getAccount(@PathVariable String userId, @PathVariable String accountId) {
        return service.getAccount(userId, accountId);
    }

    @PostMapping("/doTransaction/{senderId}/{receiverId}/{amount}")
    public void doTransaction(@PathVariable String senderId, @PathVariable String receiverId,
                              @PathVariable double amount) {
        service.doTransaction(senderId, receiverId, amount);
    }

    @GetMapping("/getAccounts/{userId}")
    public ArrayList<Account> getAccounts(@PathVariable String userId) {
        return service.getAccounts(userId);
    }

    @PostMapping("/requestTransaction/{requesterId}/{senderId}/{receiverId}/{amount}/{message}")
    public void requestTransaction(@PathVariable String requesterId, @PathVariable String senderId,
                                   @PathVariable String receiverId, @PathVariable double amount,
                                   @PathVariable String message) {
        service.requestTransaction(requesterId, senderId, receiverId, amount, message);
    }

    @GetMapping("/getTransactions/{accountId}")
    public ArrayList<Transaction> getTransactions(@PathVariable String accountId) {
        return service.getTransactions(accountId);
    }
}
