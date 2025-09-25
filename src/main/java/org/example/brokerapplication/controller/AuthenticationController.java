package org.example.brokerapplication.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.brokerapplication.dto.request.CustomerAuthenticationDto;
import org.example.brokerapplication.repository.CustomerAccountRepository;
import org.example.brokerapplication.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final CustomerAccountRepository accountRepository;

    public AuthenticationController(AuthenticationService authenticationService, CustomerAccountRepository accountRepository) {
        this.authenticationService = authenticationService;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody CustomerAuthenticationDto authenticationDto, HttpServletRequest request) {
        authenticationService.authenticateCustomer(authenticationDto);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return ResponseEntity.ok().build();
    }
}
