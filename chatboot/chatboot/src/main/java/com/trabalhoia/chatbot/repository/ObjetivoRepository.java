package com.trabalhoia.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalhoia.chatbot.models.Objetivos;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivos, Long>{

}
