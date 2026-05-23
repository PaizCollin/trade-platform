package com.example.trade.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TradeConsumer {

    @KafkaListener(topics = "trades", groupId = "trade-consumer-group")
    public void consume(TradeEvent event) {
        log.info("Consumed trade event {} {} {} @ {}",
                event.getType(), event.getQuantity(),
                event.getSymbol(), event.getPrice());
    }
}
