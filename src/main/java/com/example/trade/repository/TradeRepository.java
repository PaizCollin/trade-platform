package com.example.trade.repository;

import com.example.trade.domain.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, String> {
    Page<Trade> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
