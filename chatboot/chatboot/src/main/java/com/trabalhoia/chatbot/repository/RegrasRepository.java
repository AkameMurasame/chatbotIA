package com.trabalhoia.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trabalhoia.chatbot.models.Regras;

public interface RegrasRepository extends JpaRepository<Regras, Integer>{

	@Query(value="select * from regras as re inner join condicoes as co on co.regra = re.id where co.fato = ?", nativeQuery = true)
	public List<Regras> getRegrasBySintoma(String sintoma);
	
	@Query(value="select * from regras where id != ?", nativeQuery = true)
	public List<Regras> getOuterRegraId(Integer id);
}
