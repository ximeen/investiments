package org.ximenes.investiments.domain.accountStock;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Embeddable @Data
public class AccountStockId {

    private UUID accountId;
    private String stockId;
}
