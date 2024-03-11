package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.entities.Patient;
import com.example.demo.repository.patientRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private patientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		patientRepository.save(new Patient(null, "Hassan", new Date(), false, 4));
		patientRepository.save(new Patient(null, "Rachid", new Date(), false, 5));
		patientRepository.save(new Patient(null, "Salah", new Date(), true, 6));
		patientRepository.save(new Patient(null, "Karim", new Date(), false, 7));

	}

}
