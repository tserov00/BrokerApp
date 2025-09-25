package org.example.brokerapplication.controller;

import jakarta.validation.Valid;
import org.example.brokerapplication.dto.request.CustomerRegistrationDto;
import org.example.brokerapplication.dto.response.CustomerRegistrationResponseDto;
import org.example.brokerapplication.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerRegistrationDto customer) {
        CustomerRegistrationResponseDto responseDto = registrationService.registerCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
