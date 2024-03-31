package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping(path = "/user/index")
  public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "4") int size,
      @RequestParam(value = "keyword", defaultValue = "") String keyword) {
    Page<Patient> pagePatient = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
    model.addAttribute("patients", pagePatient);
    int totalOfPages = pagePatient.getTotalPages();
    int tab[] = new int[totalOfPages];
    model.addAttribute("pages", tab);
    model.addAttribute("keyword", keyword);
    return "patients";
  }

  @GetMapping(path = "/admin/delete")
  public String deletePatient(Long id) {
    patientRepository.deleteById(id);
    return "redirect:/index";
  }

  @GetMapping(path = "/admin/formPatient")
  public String formPatient(Model model) {
    model.addAttribute("patient", new Patient());
    return "formPatient";
  }

  @PostMapping(path = "/admin/savePatient")
  public String savePatient(Patient patient) {
    System.out.println(patient);
    patientRepository.save(patient);
    return "redirect:/index";
  }

  @GetMapping(path = "/")
  public String home() {
    return "redirect:/user/index";
  }
}
