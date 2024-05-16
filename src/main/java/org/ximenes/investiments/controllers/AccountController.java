package org.ximenes.investiments.controllers;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ximenes.investiments.dto.account.AccountStockResponstoDTO;
import org.ximenes.investiments.dto.account.AssociateAccountStockDTO;
import org.ximenes.investiments.service.AccountService;

import java.util.List;

@RequestMapping("/accounts") @RestController @Data
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/{id}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable String id, @RequestBody AssociateAccountStockDTO body){
        this.accountService.associateStock(id, body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{id}/stocks")
    public ResponseEntity<List<AccountStockResponstoDTO>> listStocks(@PathVariable String id){
        var result = this.accountService.listStocks(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
