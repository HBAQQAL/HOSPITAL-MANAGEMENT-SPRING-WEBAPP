package com.example.demo.repository;

import com.example.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepository extends JpaRepository<Patient, Long> {

}
