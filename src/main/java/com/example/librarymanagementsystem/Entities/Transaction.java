package com.example.librarymanagementsystem.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

}
