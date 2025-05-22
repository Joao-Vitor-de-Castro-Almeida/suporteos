package com.curso.repositories;

import com.curso.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Pessoa, Long> {
}
