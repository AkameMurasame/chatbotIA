package com.trabalhoia.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalhoia.chatbot.models.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer>{

}
