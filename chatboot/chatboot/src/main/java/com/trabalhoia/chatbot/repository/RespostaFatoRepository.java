package com.trabalhoia.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trabalhoia.chatbot.models.RespostasFato;

public interface RespostaFatoRepository extends JpaRepository<RespostasFato, Integer>{

	@Query("select p from RespostasFato as p where pergunta = :idPergunta")
	public List<RespostasFato> getRespostasFato(@Param("idPergunta") Integer id);
	
	//,nativeQuery = true)
}	
