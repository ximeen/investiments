package org.ximenes.investiments.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.ximenes.investiments.domain.account.Account;
import org.ximenes.investiments.domain.billingAddress.BillingAddress;
import org.ximenes.investiments.domain.user.User;
import org.ximenes.investiments.domain.user.exception.UserAlreadyExistsException;
import org.ximenes.investiments.domain.user.exception.UserNotFoundException;
import org.ximenes.investiments.dto.user.AccountResponseDTO;
import org.ximenes.investiments.dto.user.CreateAccountDTO;
import org.ximenes.investiments.dto.user.CreateUserDTO;
import org.ximenes.investiments.dto.user.UpdateUserDTO;
import org.ximenes.investiments.repository.AccountRepository;
import org.ximenes.investiments.repository.BillingAddressRepository;
import org.ximenes.investiments.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class UserService {

    private final UserRepository userReposity;
    private final AccountRepository accountRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UUID create(CreateUserDTO body){
        Optional<User> user = this.userReposity.findByEmail(body.email());
        if (user.isPresent()) throw new UserAlreadyExistsException("User already exists.");

        User newUser = new User();
            newUser.setUserId(UUID.randomUUID());
            newUser.setUsername(body.username());
            newUser.setEmail(body.email());
            newUser.setPassword(body.password());
            this.userReposity.save(newUser);

        return newUser.getUserId();
    }

    public User getUserById(String id){
        return this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));
    }

    public List<User> listUsers(){
        return this.userReposity.findAll();
    }

    public void updateUserById(String id, UpdateUserDTO body) {
        User user = this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));

        if (body.username() != null) user.setUsername(body.username());
        if (body.password() != null) user.setPassword(body.password());

        this.userReposity.save(user);
        }


    public void deleteUserById(String id){
        this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));
        this.userReposity.deleteById(UUID.fromString(id));
    }

    public void createAccount(String id, CreateAccountDTO body) {
        User user = this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));

        Account account = new Account();
            account.setAccountId(UUID.randomUUID());
            account.setUser(user);
            account.setDescription(body.description());
            account.setAccountStockList(new ArrayList<>());
            this.accountRepository.save(account);

        BillingAddress billingAddress = new BillingAddress();
            billingAddress.setId(account.getAccountId());
            billingAddress.setAccount(account);
            billingAddress.setStreet(body.street());
            billingAddress.setNumber(body.number());
            this.billingAddressRepository.save(billingAddress);
    }


    public List<AccountResponseDTO> listAccount(String id) {
        User user = this.userReposity.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not Found."));
        return user.getAccounts()
                .stream()
                .map(account -> new AccountResponseDTO(account.getAccountId().toString(), account.getDescription()))
                .toList();
    }
}
