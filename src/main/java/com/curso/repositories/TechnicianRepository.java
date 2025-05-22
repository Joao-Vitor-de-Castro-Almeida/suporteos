package com.curso.repositories;

import com.curso.domains.Technician;
import com.curso.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    Optional<Technician> findByCpf(String cpf);
    Optional<Technician> findByEmail(String email);
}
