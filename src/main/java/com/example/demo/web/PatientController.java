package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    model.addAttribute("patients", patientRepository.findAll(PageRequest.of(page, size)));
    // add number of pages to the model
    model.addAttribute("numberOfPages", patientRepository.count() / size);
    return "patients";
  }

}
