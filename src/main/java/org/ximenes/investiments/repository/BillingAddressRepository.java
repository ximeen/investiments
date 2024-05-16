package org.ximenes.investiments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ximenes.investiments.domain.account.Account;
import org.ximenes.investiments.domain.billingAddress.BillingAddress;

import java.util.UUID;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
