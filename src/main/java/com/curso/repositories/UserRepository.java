package com.curso.repositories;

import com.curso.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
}
