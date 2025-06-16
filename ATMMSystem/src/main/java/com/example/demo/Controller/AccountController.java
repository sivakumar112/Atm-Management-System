package com.example.demo.Controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Transaction;
import com.example.demo.Services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account Controller", description = "Operations for account management")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Create a new bank account")
    @PostMapping("/create")
    public Account createAccount(@RequestParam String accountNumber, @RequestParam Long userId) {
        return accountService.createAccount(accountNumber, userId);
    }
    @PostMapping("/deposit")
    public Account deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @Operation(summary = "Withdraw amount from account")
    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    @Operation(summary = "Transfer amount between accounts")
    @PostMapping("/transfer")
    public String transfer(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam double amount) {
        accountService.transfer(fromAccount, toAccount, amount);
        return "Transfer successful";
    }

    @Operation(summary = "Check balance of an account")
    @GetMapping("/balance")
    public double checkBalance(@RequestParam String accountNumber) {
        return accountService.checkBalance(accountNumber);
    }

    @Operation(summary = "Get transaction history of an account")
    @GetMapping("/transactions")
    public List<Transaction> getTransactionHistory(@RequestParam String accountNumber) {
        return accountService.getTransactionHistory(accountNumber);
    }
}
