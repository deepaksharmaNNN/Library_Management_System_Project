package com.example.librarymanagementsystem.Controllers;

import com.example.librarymanagementsystem.Entities.Author;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @PostMapping("/addAuthor")
    public String addAuthor(@RequestBody Author author){

    }
}
