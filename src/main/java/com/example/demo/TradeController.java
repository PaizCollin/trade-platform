package com.example.demo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades")
@RequiredArgsConstructor
@Validated
public class TradeController {

    private final TradeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trade create(@RequestBody @Valid Trade trade) {
        return service.create(trade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<Trade> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return service.findAll(PageRequest.of(page, size));
    }
}