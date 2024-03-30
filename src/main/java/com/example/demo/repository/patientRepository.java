package com.example.demo.repository;

import com.example.demo.entities.Patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface patientRepository extends JpaRepository<Patient, Long> {

  Page<Patient> findByNomContains(String keyword, Pageable pageable);

}
