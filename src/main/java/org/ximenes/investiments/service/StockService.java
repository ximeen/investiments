package org.ximenes.investiments.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.ximenes.investiments.domain.stock.Stock;
import org.ximenes.investiments.dto.stock.CreateStockDTO;
import org.ximenes.investiments.repository.StockRepository;

import java.util.UUID;

@Service
@Data
public class StockService {
    private final StockRepository stockRepository;

    public void createStocks(CreateStockDTO body){

        Stock stock = new Stock();
        stock.setStockId(body.stockId());
        stock.setDescription(body.description());

        this.stockRepository.save(stock);
    }

}
