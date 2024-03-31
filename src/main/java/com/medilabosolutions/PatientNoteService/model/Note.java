package com.medilabosolutions.PatientNoteService.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "note")
@Data
public class Note {

    @Id
    private String id;

    @NotBlank(message = "patID cannot be blank")
    private Integer patId;

    @NotBlank(message = "patient cannot be blank")
    private String patient;

    @NotBlank(message = "note cannot be blank")
    private String note;

}

