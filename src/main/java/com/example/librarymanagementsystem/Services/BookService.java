package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Author;
import com.example.librarymanagementsystem.Entities.Book;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.RequestDtos.AddBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;
    public String addBook(AddBookRequest addBookRequest){
        //get author from Author entity
        Author author = authorRepository.findById(addBookRequest.getAuthorId()).get();
        //create book entity from addBookRequest
        Book newBook = new Book(addBookRequest.getBookName(), addBookRequest.getBookGenre(), addBookRequest.getNoOfPages(), addBookRequest.getPrice(), addBookRequest.getPublishDate());
        author.setNoOfBookWritten(author.getNoOfBookWritten() + 1);
        //set the foreign key/mapping
            //adding for the book (author Entity)
            newBook.setAuthor(author);//Uni-directional Mapping
            //for author add book in book List
            author.getBookList().add(newBook);//Bi-Directional Mapping
        //save the parent class
        authorRepository.save(author);
        return "Book has been saved to Db";
    }
}
