package com.example.demo.Services;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Account createAccount(String accountNumber, Long userId) {
        if (accountRepository.findByAccountNumber(accountNumber).isPresent()) {
            throw new IllegalArgumentException("Account number already exists");
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Account acc = new Account();
        acc.setAccountNumber(accountNumber);
        acc.setUser(user);
        acc.setBalance(0.0);

        return accountRepository.save(acc);
    }

    public Account deposit(String accountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");

        Account acc = getAccountByNumber(accountNumber);
        acc.setBalance(acc.getBalance() + amount);

        Transaction tx = new Transaction();
        tx.setAccount(acc);
        tx.setType("DEPOSIT");
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());

        transactionRepository.save(tx);
        return accountRepository.save(acc);
    }

    public Account withdraw(String accountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");

        Account acc = getAccountByNumber(accountNumber);
        if (acc.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);

        Transaction tx = new Transaction();
        tx.setAccount(acc);
        tx.setType("WITHDRAW");
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());

        transactionRepository.save(tx);
        return accountRepository.save(acc);
    }

    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Transfer amount must be positive");

        Account from = getAccountByNumber(fromAccountNumber);
        Account to = getAccountByNumber(toAccountNumber);

        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance for transfer");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        Transaction txFrom = new Transaction();
        txFrom.setAccount(from);
        txFrom.setType("TRANSFER_OUT");
        txFrom.setAmount(-amount);
        txFrom.setTimestamp(LocalDateTime.now());

        Transaction txTo = new Transaction();
        txTo.setAccount(to);
        txTo.setType("TRANSFER_IN");
        txTo.setAmount(amount);
        txTo.setTimestamp(LocalDateTime.now());

        transactionRepository.save(txFrom);
        transactionRepository.save(txTo);

        accountRepository.save(from);
        accountRepository.save(to);
    }

    public double checkBalance(String accountNumber) {
        return getAccountByNumber(accountNumber).getBalance();
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        Account acc = getAccountByNumber(accountNumber);
        return transactionRepository.findByAccount(acc);
    }

    public List<Account> getAccountsByUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return accountRepository.findByUser(user);
    }

    public void deleteAccount(String accountNumber) {
        Account acc = getAccountByNumber(accountNumber);
        accountRepository.delete(acc);
    }

    private Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
