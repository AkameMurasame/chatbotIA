package com.trabalhoia.chatbot.DTO;

import java.util.List;

public class ChatbotDTO {

	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private String msg;
	
	private String uuid;
	
	private Integer regra;

	private List<RetornoComparacaoDTO> retorno;
	
	public Integer getRegra() {
		return regra;
	}

	public void setRegra(Integer regra) {
		this.regra = regra;
	}

	public List<RetornoComparacaoDTO> getRetorno() {
		return retorno;
	}

	public void setRetorno(List<RetornoComparacaoDTO> retorno) {
		this.retorno = retorno;
	}
	
}
