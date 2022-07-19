package com.example.superbank.controller;

import com.example.superbank.model.dto.TransferBalance;
import com.example.superbank.service.BalanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController("/balance")
@AllArgsConstructor
@Slf4j
public class BalanceController {
    private final BalanceService balanceService;

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId) {
        return balanceService.getBalance(accountId);
    }

    @PostMapping("/increase")
    public void increaseBalance(@RequestBody TransferBalance transferBalance) {
        balanceService.increaseBalance(transferBalance.getToId(), transferBalance.getAmount());
    }

    @PostMapping("/transfer")
    public boolean transferBalance(@RequestBody TransferBalance transferBalance) {
        balanceService.transferBalance(transferBalance);
        return true;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException exception) {
        log.error(exception.getMessage());
        return "Error: invalid account id";
    }
}