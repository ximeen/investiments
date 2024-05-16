package org.ximenes.investiments.controllers;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ximenes.investiments.dto.stock.CreateStockDTO;
import org.ximenes.investiments.dto.user.CreateUserDTO;
import org.ximenes.investiments.dto.user.UserResponseDTO;
import org.ximenes.investiments.service.StockService;

import java.util.UUID;

@RestController @RequestMapping("/stocks") @Data
public class StockController {
    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO body){
        this.stockService.createStocks(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
