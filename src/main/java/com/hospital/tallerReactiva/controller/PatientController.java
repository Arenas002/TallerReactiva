package com.hospital.tallerReactiva.controller;


import com.hospital.tallerReactiva.model.Patient;
import com.hospital.tallerReactiva.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/api/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Patient> getPatient() {
        return patientService.getAllPatient();

    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<Patient>> findById(@PathVariable String id) {

        return patientService.findAll(id);
    }

    @PostMapping("/")
    public Mono<Patient> postBook(@RequestBody Patient patient) {
        return patientService.postPatient(patient).log();
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Patient>> putPatient(@PathVariable (value = "id") String id, @RequestBody Patient patient) {
        return patientService.putPatient(id,patient).log();
    }

    @PutMapping("/delete/{id}")
    public Mono<ResponseEntity<Patient>> delete(@PathVariable (value = "id") String id) {
        return patientService.deletepatient(id);
    }

    @PatchMapping("/treatment/{id}/{update}")
    public Mono<ResponseEntity<Patient>> patch(@PathVariable (value = "id") String id,@PathVariable (value = "update")String treatment){
        return patientService.UpdateTreatment(id, treatment);
    }

    @DeleteMapping("/borrar/{id}")
        public Mono<ResponseEntity<Patient>> deletePatch(@PathVariable (value ="id") String id){
        return patientService.delete(id);
    }

}
