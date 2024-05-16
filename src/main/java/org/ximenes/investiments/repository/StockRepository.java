package org.ximenes.investiments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ximenes.investiments.domain.accountStock.AccountStock;
import org.ximenes.investiments.domain.stock.Stock;
import org.ximenes.investiments.domain.user.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
