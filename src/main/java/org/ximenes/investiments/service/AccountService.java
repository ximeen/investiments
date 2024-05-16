package org.ximenes.investiments.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.ximenes.investiments.domain.account.Account;
import org.ximenes.investiments.domain.accountStock.AccountStock;
import org.ximenes.investiments.domain.accountStock.AccountStockId;
import org.ximenes.investiments.domain.stock.Stock;
import org.ximenes.investiments.domain.stock.exception.StockNotFoundException;
import org.ximenes.investiments.dto.account.AccountStockResponstoDTO;
import org.ximenes.investiments.dto.account.AssociateAccountStockDTO;
import org.ximenes.investiments.dto.account.exception.AccountNotFoundException;
import org.ximenes.investiments.dto.user.AccountResponseDTO;
import org.ximenes.investiments.repository.AccountRepository;
import org.ximenes.investiments.repository.AccountStockRepository;
import org.ximenes.investiments.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service @Data
public class AccountService {
    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;
    private final AccountStockRepository accountStockRepository;

    public void associateStock(String id, AssociateAccountStockDTO body) {
        Account account = this.accountRepository.findById(UUID.fromString(id)).orElseThrow(() -> new AccountNotFoundException("Account not found."));
        Stock stock = this.stockRepository.findById(body.stockId()).orElseThrow(() -> new StockNotFoundException("Stock not found."));
        Integer quantityAlreadyObtained = this.accountStockRepository.findQuantityByStockId(body.stockId());

        AccountStockId accountStockId = new AccountStockId();
        accountStockId.setAccountId(account.getAccountId());
        accountStockId.setStockId(stock.getStockId());

        AccountStock associateAccountStock = new AccountStock();
        associateAccountStock.setAccount(account);
        associateAccountStock.setStock(stock);
        associateAccountStock.setId(accountStockId);
        associateAccountStock.setQuantity(quantityAlreadyObtained + body.quantity());
        this.accountStockRepository.save(associateAccountStock);
    }

    public List<AccountStockResponstoDTO> listStocks(String id) {
        Account account = this.accountRepository.findById(UUID.fromString(id)).orElseThrow(() -> new AccountNotFoundException("Account not found."));
        return account.getAccountStockList()
                .stream()
                .map(as -> new AccountStockResponstoDTO(as.getStock().getStockId(), as.getQuantity(), 0.0)).toList();
    }
}
