package com.hospital.tallerReactiva.services;

import com.hospital.tallerReactiva.model.Patient;
import com.hospital.tallerReactiva.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

public Flux<Patient> getAllPatient(){
    return  patientRepository.findAll().filter(patient -> patient.getState() ==(1)).log().delayElements(Duration.ofSeconds(1));
}

public Mono<ResponseEntity<Patient>> findAll(String id){
        return  patientRepository.findById(id).filter(patient -> patient.getState() ==(1))
                .map(updatedPatient -> new ResponseEntity<>(updatedPatient, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

public Mono<Patient> postPatient(Patient patient) {
        return  patientRepository.save(patient).log();
    }

public Mono<ResponseEntity<Patient>>putPatient(String id, Patient patient) {
        return patientRepository.findById(id)
                .filter(putPatient -> putPatient.getState() == (1))
                .flatMap(oldPatient ->{
                    oldPatient.setPatient(patient.getPatient());
                    oldPatient.setDocumentType(patient.getDocumentType());
                    oldPatient.setDocument(patient.getDocument());
                    oldPatient.setEmail(patient.getEmail());
                    oldPatient.setAddress(patient.getAddress());
                    oldPatient.setDentist(patient.getDentist());
                    oldPatient.setBloodType(patient.getBloodType());
                    oldPatient.setTreatment(patient.getTreatment());
                    oldPatient.setPrice(patient.getPrice());
                    oldPatient.setState(patient.getState());
                    return patientRepository.save(oldPatient);
                })
            .map(updatedPatient -> new ResponseEntity<>(updatedPatient, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));

}


    public Mono<ResponseEntity<Patient>>deletepatient(String id){
        return patientRepository.findById(id)
                .filter(delectePatient -> delectePatient.getState() == (1))
                .flatMap(oldPatient ->{
                    oldPatient.setState(0);
                    return patientRepository.save(oldPatient);
                })
                .map(updatedPatient -> new ResponseEntity<>(updatedPatient, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    public Mono<ResponseEntity<Patient>>UpdateTreatment(String id, String treatment){
        return patientRepository.findById(id)
                .filter(patchPatient -> patchPatient.getState() == (1))
                .flatMap(oldPatient ->{
                    oldPatient.setTreatment(treatment);

                    return patientRepository.save(oldPatient);
                })
                .map(updatedPatient -> new ResponseEntity<>(updatedPatient, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    public Mono<ResponseEntity<Patient>>delete(String id){
       return patientRepository.findById(id)
               .filter(Patient -> Patient.getState() == (1))
                .flatMap(deletedBook -> patientRepository.delete(deletedBook)
                        .then(Mono.just(deletedBook)))
                .map(deletePet -> new ResponseEntity<>(deletePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
