package com.trabalhoia.chatbot.DTO;

import java.util.List;

import com.trabalhoia.chatbot.models.RespostasFato;

public class FatosDTO {
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getProjeto() {
		return projeto;
	}
	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}
	public List<RespostasFato> getRespostasFato() {
		return respostasFato;
	}
	public void setRespostasFato(List<RespostasFato> respostasFato) {
		this.respostasFato = respostasFato;
	}
	private String nome;
	private String pergunta;
	private String projeto;
	private List<RespostasFato> respostasFato;
}
