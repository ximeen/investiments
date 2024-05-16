package org.ximenes.investiments.domain.stock;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "tb_stocks") @Data
public class Stock {

    @Id
    private String stockId;
    private String description;
}
