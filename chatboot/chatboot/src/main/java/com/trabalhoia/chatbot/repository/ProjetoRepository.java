package com.trabalhoia.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalhoia.chatbot.models.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>{

}
