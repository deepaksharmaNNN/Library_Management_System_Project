package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.LibraryCard;
import com.example.librarymanagementsystem.Entities.Student;
import com.example.librarymanagementsystem.Enums.CardStatus;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;
    public String getFreshCard(){
        LibraryCard newCard = new LibraryCard();
        newCard.setCardStatus(CardStatus.NEW);
        newCard.setNoOfBooksIssued(0);

        LibraryCard savedCard = cardRepository.save(newCard);
        return "New card with card no "+savedCard.getCardId()+" has been generated";
    }
    public String associateCardAndStudent(Integer studentId, Integer cardId) throws Exception{
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);
        if(optionalLibraryCard.isEmpty()){
            throw new Exception("Invalid Card Id Entered !");
        }
        LibraryCard libraryCard = optionalLibraryCard.get();

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            throw new Exception("No student with the given id exits !");
        }
        Student student = optionalStudent.get();

        //changes
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);
        libraryCard.setNoOfBooksIssued(0);
        cardRepository.save(libraryCard);
        return "Done";

    }
}
