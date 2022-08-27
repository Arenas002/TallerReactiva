package com.hospital.tallerReactiva.repositories;

import com.hospital.tallerReactiva.model.Patient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PatientRepository extends ReactiveMongoRepository<Patient, String> {
}
