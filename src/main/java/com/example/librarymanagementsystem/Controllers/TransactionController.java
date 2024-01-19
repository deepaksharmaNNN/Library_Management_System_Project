package com.example.librarymanagementsystem.Controllers;

import com.example.librarymanagementsystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("issueBook")
    public String issueBook(@RequestParam("bookId") Integer bookId, @RequestParam("cardId") Integer cardId){

    }
}
