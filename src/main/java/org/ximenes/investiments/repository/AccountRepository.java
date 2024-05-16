package org.ximenes.investiments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ximenes.investiments.domain.account.Account;
import org.ximenes.investiments.domain.user.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
