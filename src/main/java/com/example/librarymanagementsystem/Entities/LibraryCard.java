package com.example.librarymanagementsystem.Entities;

import com.example.librarymanagementsystem.Enums.CardStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)

    private CardStatus cardStatus;

    private int noOfBooksIssued;

    @JoinColumn
    @OneToOne
    private Student student;

}
