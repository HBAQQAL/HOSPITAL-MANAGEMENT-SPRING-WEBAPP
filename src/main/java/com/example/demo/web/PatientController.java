package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Patient;
import com.example.demo.repository.patientRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class PatientController {
  @Autowired
  private patientRepository patientRepository;

  @GetMapping(path = "/index")

  public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "4") int size) {
    Page<Patient> pagePatient = patientRepository.findAll(PageRequest.of(page, size));
    model.addAttribute("patients", pagePatient);
    int totalOfPages = pagePatient.getTotalPages();
    int tab[] = new int[totalOfPages];
    model.addAttribute("pages", tab);
    return "patients";

  }

}
