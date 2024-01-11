package com.example.librarymanagementsystem.Entities;

import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(unique = true)
    private String bookName;

    @Enumerated(value = EnumType.STRING)
    private Genre bookGenre;

    private int noOfPages;

    private int price;

    private Date publishDate;

    @JoinColumn //(referencedColumnName = "emailId")
    @ManyToOne
    private Author author;


}
