package com.example.trade.service;

import com.example.trade.domain.Trade;
import com.example.trade.kafka.TradeProducer;
import com.example.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "trades")
public class TradeService {

    private final TradeRepository repo;
    private final TradeProducer producer;

    @CacheEvict(key = "'all'")
    public Trade create(Trade trade) {
        Trade saved = repo.save(trade);
        producer.publish(saved);
        return saved;
    }

    @Cacheable(key = "#id")
    public Optional<Trade> findById(String id) {
        return repo.findById(id);
    }

    public Page<Trade> findAll(Pageable pageable) {
        return repo.findAllByOrderByCreatedAtDesc(pageable);
    }
}
