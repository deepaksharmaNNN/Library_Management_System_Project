package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Book;
import com.example.librarymanagementsystem.Entities.LibraryCard;
import com.example.librarymanagementsystem.Entities.Transaction;
import com.example.librarymanagementsystem.Enums.TransactionStatus;
import com.example.librarymanagementsystem.Enums.TransactionType;
import com.example.librarymanagementsystem.Exceptions.BookNotAvailableException;
import com.example.librarymanagementsystem.Exceptions.BookNotFoundException;
import com.example.librarymanagementsystem.Exceptions.CardNotFoundException;
import com.example.librarymanagementsystem.Exceptions.MaxLimitReachedException;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;


    public String issueBook(Integer bookId, Integer cardId) throws Exception{
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.ISSUED);
        transaction.setTransactionStatus(TransactionStatus.ONGOING);

        //get book and card Entity from the Db
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Book id is invalid");
        }
        Optional<LibraryCard> optionalLibraryCard= cardRepository.findById(cardId);
        if(optionalLibraryCard.isEmpty()){
            throw new CardNotFoundException("Card id is invalid");
        }
        Book book = bookOptional.get();
        LibraryCard libraryCard = optionalLibraryCard.get();


        //check for availability (book and maxAllowedBooks)
        if(book.getIsAvailable() == Boolean.FALSE){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new BookNotAvailableException("Book with the given bookId is not available, transaction id "+transaction.getTransactionId());
        }
        if(libraryCard.getNoOfBooksIssued() >= LibraryCard.MAX_NO_OF_ALLOWED_BOOKS){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transaction = transactionRepository.save(transaction);
            throw new MaxLimitReachedException("You have reached the max no of allowed books"+
                    "\n"+"Please return previous books to get new one "
                    +"Transaction id "+transaction.getTransactionId());
        }
        //being here means all the thing are valid
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //update book and card status
        book.setIsAvailable(Boolean.FALSE);
        libraryCard.setNoOfBooksIssued(libraryCard.getNoOfBooksIssued() + 1);

        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        //save the child as it will save the parent(cascade)
        transaction = transactionRepository.save(transaction);
        return "The transaction with the TransactionId "+transaction.getTransactionId()+" has been saved to Db";

    }
}
