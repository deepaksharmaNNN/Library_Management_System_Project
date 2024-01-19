package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Book;
import com.example.librarymanagementsystem.Entities.LibraryCard;
import com.example.librarymanagementsystem.Entities.Transaction;
import com.example.librarymanagementsystem.Enums.TransactionStatus;
import com.example.librarymanagementsystem.Enums.TransactionType;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;


    public String addTransaction(Integer bookId, Integer cardId) throws Exception{
        //get book and card Entity from the Db
        Book book = bookRepository.findById(bookId).get();
        LibraryCard libraryCard = cardRepository.findById(cardId).get();
        //check for availability (book and maxAllowedBooks)
        if(book.getIsAvailable() == Boolean.FALSE){
            throw new Exception("Book with the given bookId is not available");
        }
        if(libraryCard.getNoOfBooksIssued() >= LibraryCard.MAX_NO_OF_ALLOWED_BOOKS){
            throw new Exception("You have reached the max no of allowed books"+"\n"+"Please return previous books to get new one");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setTransactionType(TransactionType.ISSUED);

        //update book and card status
        book.setIsAvailable(Boolean.FALSE);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued() + 1);

    }
}
