package org.ximenes.investiments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ximenes.investiments.domain.accountStock.AccountStock;
import org.ximenes.investiments.domain.accountStock.AccountStockId;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {

    @Query("SELECT quantity FROM AccountStock WHERE stock.stockId = :stockId")
     Integer findQuantityByStockId(String stockId);
}
