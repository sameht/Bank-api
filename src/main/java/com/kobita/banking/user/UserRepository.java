package com.kobita.banking.user;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Boolean existsByEmail(String email);
}
