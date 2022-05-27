package com.arthur.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthur.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
