package org.ximenes.investiments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ximenes.investiments.domain.User.User;

import java.util.UUID;

public interface UserReposity extends JpaRepository<User, UUID> { }
