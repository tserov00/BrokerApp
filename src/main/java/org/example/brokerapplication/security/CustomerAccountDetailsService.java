package org.example.brokerapplication.security;

import org.example.brokerapplication.entity.CustomerAccount;
import org.example.brokerapplication.repository.CustomerAccountRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountDetailsService implements UserDetailsService {
    private final CustomerAccountRepository accountRepository;

    public CustomerAccountDetailsService(CustomerAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CustomerAccountDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        CustomerAccount account = accountRepository.findByLogin(login);
        if (account == null) {throw new BadCredentialsException("Неверный логин или пароль");}
        return new CustomerAccountDetails(account);
    }
}
