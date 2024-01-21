package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Author;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import com.example.librarymanagementsystem.RequestDtos.AddAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    public String addAuthor(AddAuthorRequest addAuthorRequest){
        Author authorEntity = new Author(addAuthorRequest.getAuthorName(), addAuthorRequest.getAuthorAge(), addAuthorRequest.getEmailId());
        Author newAuthor = authorRepository.save(authorEntity);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Hi "+newAuthor.getAuthorName()+" !");
        message.setFrom("yonkai28@gmail.com");
        message.setTo(newAuthor.getEmailId());
        message.setText("Your have been Successfully registered on our portal !" +" Looking forward for you to use our services");
        javaMailSender.send(message);
        return "Author with authorId "+newAuthor.getAuthorId()+" has been added to Db";
    }
}
