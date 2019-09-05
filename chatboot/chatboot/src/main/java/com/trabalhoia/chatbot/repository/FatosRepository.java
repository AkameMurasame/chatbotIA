package com.trabalhoia.chatbot.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trabalhoia.chatbot.models.Fatos;

public interface FatosRepository extends JpaRepository<Fatos, Integer>{

	@Query("Select u from Fatos as u where nome like '%:string%'")
	public List<Fatos> getFatoByString(@Param("string") String fato);
}	
