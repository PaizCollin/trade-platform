package com.example.trade.kafka;

import com.example.trade.domain.Trade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TradeProducer {

    private final KafkaTemplate<String, TradeEvent> kafka;
    private static final String TOPIC = "trades";

    public void publish(Trade trade) {
        TradeEvent event = new TradeEvent(
                trade.getId(), trade.getSymbol(), trade.getQuantity(),
                trade.getPrice(), trade.getType().name(), trade.getCreatedAt()
        );

        kafka.send(TOPIC, trade.getId(), event)
                .whenComplete((result, ex) -> {
                    if(ex != null) log.error("Failed to publish trade {}", trade.getId());
                    else log.info("Published trade {} to partition {}",
                            trade.getId(), result.getRecordMetadata().partition());
                });
    }
}
