package com.example.trade.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeEvent {
    private String tradeId;
    private String symbol;
    private Integer quantity;
    private BigDecimal price;
    private String type;
    private LocalDateTime createdAt;
}
