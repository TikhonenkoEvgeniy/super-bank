package com.example.superbank.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferBalance {
    private Long fromId;
    private Long toId;
    private BigDecimal amount;
}