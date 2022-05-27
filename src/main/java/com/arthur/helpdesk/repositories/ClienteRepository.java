package com.arthur.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthur.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}