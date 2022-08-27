package com.hospital.tallerReactiva.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value= "patient")
public class Patient {
    @Id
    private String id;

    private String patient;
    private String documentType;
    private String document;
    private String email;
    private String address;
    private String dentist;
    private String bloodType;
    private String treatment;
    private Integer price;
    private Integer state;

    public Patient(String id, String patient, String documentType, String document, String email, String address, String dentist, String bloodType, String treatment, Integer price) {
        this.id = id;
        this.patient = patient;
        this.documentType = documentType;
        this.document = document;
        this.email = email;
        this.address = address;
        this.dentist = dentist;
        this.bloodType = bloodType;
        this.treatment = treatment;
        this.price = price;

    }
}
