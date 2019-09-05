package com.trabalhoia.chatbot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PerguntaResposta")
public class PerguntaResposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	private String pergunta;
	
	private String botRaw;
	
	private Integer regra;
	
	private String uuid;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getBotRaw() {
		return botRaw;
	}

	public void setBotRaw(String botRaw) {
		this.botRaw = botRaw;
	}

	public Integer getRegra() {
		return regra;
	}

	public void setRegra(Integer regra) {
		this.regra = regra;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private String resposta;
}
