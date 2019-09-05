package com.trabalhoia.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trabalhoia.chatbot.models.PerguntaResposta;

public interface PerguntaRespostaRepository extends JpaRepository<PerguntaResposta, Integer>{

	@Query(value = "select * from pergunta_resposta where id = (SELECT max(id) from pergunta_resposta);", nativeQuery = true)
	public PerguntaResposta getUltimaPergunta();
	
	@Query(value = "select * from pergunta_resposta where uuid = ?", nativeQuery = true)
	public List<PerguntaResposta> getPerguntasRespostasFeitas(String uid);
	
	@Query(value = "select * from pergunta_resposta where uuid = ? and regra = ? and resposta != ''", nativeQuery = true)
	public List<PerguntaResposta> getPerguntasRespostasFeitasByRegra(String uid, Integer regra);
}
