package com.trabalhoia.chatbot.DTO;

public class RetornoComparacaoDTO {

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Integer getRegra() {
		return regra;
	}

	public void setRegra(Integer regra) {
		this.regra = regra;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

	public String getStringComparacao() {
		return stringComparacao;
	}

	public void setStringComparacao(String stringComparacao) {
		this.stringComparacao = stringComparacao;
	}

	private String objetivo;
	
	private Integer regra;
	
	private Integer resultado;
	
	private String stringComparacao;
}
