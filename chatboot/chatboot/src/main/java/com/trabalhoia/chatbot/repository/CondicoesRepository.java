package com.trabalhoia.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trabalhoia.chatbot.models.Condicoes;

public interface CondicoesRepository extends JpaRepository<Condicoes, Integer>{

	@Query(value = "select * from condicoes where condicoes.regra = ? and condicoes.fato != ? order by tipo asc", nativeQuery = true)
	public List<Condicoes> getCondicoesByRegra(Integer id, String fato);
	
	@Query(value = "select * from condicoes where condicoes.regra = :id and condicoes.pergunta"
			+ " not in (select pergunta from pergunta_resposta "
			+ "where uuid = :uid and regra = :id)", nativeQuery = true)
	public List<Condicoes> getCondicoesNotIn(Integer id, String uid);
	
	@Query(value = "select * from condicoes where condicoes.pergunta"
			+ " not in (select pergunta from pergunta_resposta "
			+ "where uuid = :uid)", nativeQuery = true)
	public List<Condicoes> getCondicoesNotInRegra(String uid);
	
	@Query(value = "select * from condicoes where condicoes.regra = ? order by tipo asc;", nativeQuery = true)
	public List<Condicoes> getCondicoesByRegraFull(Integer id);
}
