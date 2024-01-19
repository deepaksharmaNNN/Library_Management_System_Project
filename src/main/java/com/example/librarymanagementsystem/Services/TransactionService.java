package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    public String addTransaction(Integer bookId, Integer cardId){

    }
}
