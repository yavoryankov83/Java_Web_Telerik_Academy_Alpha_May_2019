package com.telerikacademy.springdemo.controllers;

import com.telerikacademy.springdemo.models.Employee;
import com.telerikacademy.springdemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllEmployeesPage(Model model) {
        model.addAttribute("employeesList", service.getAll());
        return "allEmployees";
    }

    @GetMapping("/new")
    public String getNewEmployeeView(Model model) {
        model.addAttribute("employee", new Employee());
        return "newEmployee";
    }

    @PostMapping("/new")
    public String newEmployee(@Valid @ModelAttribute Employee employee,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "newEmployee";
        }

        //setting the id manually for test purposes
        employee.setId(service.getAll().size() + 1);
        service.addNewEmployee(employee);

        return "redirect:/employees";
    }


}
