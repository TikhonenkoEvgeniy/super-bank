package com.example.superbank.service;

import com.example.superbank.model.dto.TransferBalance;
import com.example.superbank.repository.BalanceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        BigDecimal currentBalance = balanceRepository.getBalanceForId(toId);
        if (currentBalance == null) {
            balanceRepository.save(toId, amount);
        } else {
            BigDecimal updatedBalance = currentBalance.add(amount);
            balanceRepository.save(toId, updatedBalance);
        }
     }

    public void transferBalance(TransferBalance transferBalance) {
        BigDecimal fromBalance = balanceRepository.getBalanceForId(transferBalance.getFromId());
        BigDecimal toBalance = balanceRepository.getBalanceForId(transferBalance.getToId());
        if (fromBalance == null || toBalance == null) throw new IllegalArgumentException();
        if (transferBalance.getAmount().compareTo(fromBalance) > 0) throw new IllegalArgumentException("No money!");

        BigDecimal updatedFromBalance = fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updatedToBalance = toBalance.add(transferBalance.getAmount());

        balanceRepository.save(transferBalance.getFromId(), updatedFromBalance);
        balanceRepository.save(transferBalance.getToId(), updatedToBalance);
    }
}
