package org.ximenes.investiments.domain.account;


import jakarta.persistence.*;
import lombok.Data;
import org.ximenes.investiments.domain.accountStock.AccountStock;
import org.ximenes.investiments.domain.billingAddress.BillingAddress;
import org.ximenes.investiments.domain.user.User;

import java.util.List;
import java.util.UUID;

@Entity @Table(name = "tb_accounts") @Data
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;
    private String description;
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account") @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;
    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStockList;

}
