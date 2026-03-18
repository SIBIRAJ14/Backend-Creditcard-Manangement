package com.banking.credit.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.credit.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndIsActiveTrue(String email);
}
