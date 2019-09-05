package com.trabalhoia.chatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalhoia.chatbot.DTO.RegrasDTO;
import com.trabalhoia.chatbot.config.ConverterMapper;
import com.trabalhoia.chatbot.models.Condicoes;
import com.trabalhoia.chatbot.models.Regras;
import com.trabalhoia.chatbot.models.RespostasFato;
import com.trabalhoia.chatbot.repository.CondicoesRepository;
import com.trabalhoia.chatbot.repository.RegrasRepository;

@Service
public class RegrasService {
	@Autowired
	private RegrasRepository regrasRepository;
	
	@Autowired
	private CondicoesRepository condicoesRepository;
	
	@Autowired
	private ConverterMapper mapper;
	
	@Transactional(readOnly = true)
	public List<RegrasDTO> findAll() {
		List<Regras> regras = regrasRepository.findAll();
		return this.mapper.mapAsList(regras, RegrasDTO.class);
	}

	@Transactional(readOnly = true)
	public RegrasDTO findById(Integer id) {
		Regras regras = regrasRepository.findById(id).orElse(null);
		return this.mapper.map(regras, RegrasDTO.class);
	}

	@Transactional
	public void deleteById(Integer id) {
		regrasRepository.deleteById(id);
	}

	@Transactional
	public RegrasDTO save(RegrasDTO objDTO) {
		Regras regras = this.mapper.map(objDTO, Regras.class);
		regras = this.regrasRepository.save(regras);
		Integer idRegra = regras.getId();
		List<Condicoes> condicoes = objDTO.getCondicoes();
		condicoes.stream().forEach(condicao -> condicao.setRegra(idRegra));
		condicoes.stream().forEach(condicaosave -> this.condicoesRepository.save(condicaosave));
	    return this.mapper.map(regras, RegrasDTO.class);
	}
}
