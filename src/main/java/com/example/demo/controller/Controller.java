package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    @Autowired
    private Service service;
    @GetMapping("/getAccount{accountId}")
    public Account getAccount(@PathVariable String accountId) {
        return service.getAccount(accountId);
    }

    @PostMapping("/doTransaction/{senderId}/{receiverId}/{amount}")
    public void doTransaction(@PathVariable String senderId, @PathVariable String receiverId,
                              @PathVariable double amount) {
        service.makeOrdinaryTransaction(senderId, receiverId, amount);
    }

    @PostMapping("/approveRequest/{requestId}")
    public void approveRequest(@PathVariable String requestId) {
        service.approveRequest(requestId);
    }
    @PostMapping("/requestTransaction/{senderId}/{receiverId}/{amount}/{transactionMessage}/{requestMessage}")
    public void requestTransaction(@PathVariable String senderId, @PathVariable String receiverId,
                                   @PathVariable double amount, @PathVariable String transactionMessage,
                                   @PathVariable String requestMessage) {
        service.makeObshiakTransaction(senderId, receiverId, amount, transactionMessage, requestMessage);
    }

    @PostMapping("/rejectRequest/{requestId}")
    public void rejectRequest(@PathVariable String requestId) {
        service.rejectRequest(requestId);
    }
    @GetMapping("/getTransactions/{accountId}")
    public ArrayList<Transaction> getTransactions(@PathVariable String accountId) {
        return service.getTransactions(accountId);
    }

//    @PostMapping("/makeObshiaki/{ownersIds}")
//    public void makeObshiaki(@PathVariable ArrayList<String> ownersIds) {
//        service.makeObshiaki(ownersIds);
//    }
}
