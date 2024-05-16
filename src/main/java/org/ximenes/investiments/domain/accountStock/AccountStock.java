package org.ximenes.investiments.domain.accountStock;

import jakarta.persistence.*;
import lombok.Data;
import org.ximenes.investiments.domain.account.Account;
import org.ximenes.investiments.domain.stock.Stock;

@Entity @Table(name = "tb_accounts_stock") @Data
public class AccountStock {

    @EmbeddedId
    private AccountStockId id;
    @ManyToOne @MapsId("accountId") @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne @MapsId("stockId") @JoinColumn(name = "stock_id")
    private Stock stock;
    private Integer quantity;
}