package com.example.librarymanagementsystem.RequestDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssociationCardStudentRequest {
    private Integer studentId;
    private Integer cardId;
}
