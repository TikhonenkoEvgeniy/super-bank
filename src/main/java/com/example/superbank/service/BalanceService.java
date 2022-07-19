package com.example.superbank.service;

import com.example.superbank.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BalanceService {
    final BalanceRepository balanceRepository;

    public BigDecimal getBalance(Long accountId) {
        BigDecimal balance = balanceRepository.getBalanceForId(accountId);
        if (balance == null) throw new IllegalArgumentException();
        return balance;
    }

    public void increaseBalance(Long toId, BigDecimal amount) {

    }
}
