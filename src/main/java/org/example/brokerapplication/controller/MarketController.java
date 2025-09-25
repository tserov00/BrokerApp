package org.example.brokerapplication.controller;

import org.example.brokerapplication.dto.response.MarketSecuritiesResponseDto;
import org.example.brokerapplication.service.MarketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/securities")
    public ResponseEntity<List<MarketSecuritiesResponseDto>> getSecurities() {
        return ResponseEntity.status(HttpStatus.OK).body(marketService.getSecurities());
    }
}
