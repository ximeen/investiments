package org.ximenes.investiments.domain.user;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.ximenes.investiments.domain.account.Account;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @CreationTimestamp
    private Instant creationTimestamp;
    @UpdateTimestamp
    private Instant updateTimestamp;
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

}
