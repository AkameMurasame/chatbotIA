package com.trabalhoia.chatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalhoia.chatbot.DTO.ProjetoDTO;
import com.trabalhoia.chatbot.config.ConverterMapper;
import com.trabalhoia.chatbot.models.Projeto;
import com.trabalhoia.chatbot.repository.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private ConverterMapper mapper;
	
	@Transactional(readOnly = true)
	public List<ProjetoDTO> findAll() {
		List<Projeto> responsaveis = projetoRepository.findAll();
		return this.mapper.mapAsList(responsaveis, ProjetoDTO.class);
	}

	@Transactional(readOnly = true)
	public ProjetoDTO findById(Integer id) {
		Projeto projeto = projetoRepository.findById(id).orElse(null);
		return this.mapper.map(projeto, ProjetoDTO.class);
	}

	@Transactional
	public void deleteById(Integer id) {
		projetoRepository.deleteById(id);
	}

	@Transactional
	public ProjetoDTO save(ProjetoDTO projetoDTO) {
		Projeto projeto = this.mapper.map(projetoDTO, Projeto.class);
		projeto = this.projetoRepository.save(projeto);
	    return this.mapper.map(projeto, ProjetoDTO.class);
	}
}
