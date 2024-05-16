package org.ximenes.investiments.domain.billingAddress;

import jakarta.persistence.*;
import lombok.Data;
import org.ximenes.investiments.domain.account.Account;

import java.util.UUID;

@Entity @Table(name = "tb_billing_address") @Data
public class BillingAddress {

    @Id @JoinColumn(name = "account_id")
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "account_id") @MapsId
    private Account account;
    private String street;
    private Integer number;
}
