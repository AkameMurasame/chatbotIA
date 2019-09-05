package com.trabalhoia.chatbot.DTO;

import java.util.List;

import com.trabalhoia.chatbot.models.Condicoes;

public class RegrasDTO {
	private Integer id;
	private String objetivo;
	private String projeto;
	private List<Condicoes> condicoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getProjeto() {
		return projeto;
	}

	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	public List<Condicoes> getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(List<Condicoes> condicoes) {
		this.condicoes = condicoes;
	}
}
