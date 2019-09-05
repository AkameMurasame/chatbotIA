package com.trabalhoia.chatbot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "condicoes")
public class Condicoes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "fato", nullable = false)
	private String fato;
	@Column(name = "pergunta", nullable = false)
	private String pergunta;
	@Column(name = "tipo", nullable = false)
	private String tipo;
	@Column(name = "valor", nullable = false)
	private String valor;
	@Column(name = "regra")
	private Integer regra;
	
	public Integer getRegra() {
		return regra;
	}
	public void setRegra(Integer regra) {
		this.regra = regra;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFato() {
		return fato;
	}
	public void setFato(String fato) {
		this.fato = fato;
	}
	public String getPergunta() {
		return pergunta;
	}
	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
