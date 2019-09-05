package com.trabalhoia.chatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalhoia.chatbot.DTO.ResponsavelDTO;
import com.trabalhoia.chatbot.config.ConverterMapper;
import com.trabalhoia.chatbot.models.Responsavel;
import com.trabalhoia.chatbot.repository.ResponsavelRepository;

@Service
public class ResponsavelService {
	@Autowired
	private ConverterMapper mapper;
	
	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	@Transactional(readOnly = true)
	public List<ResponsavelDTO> findAll() {
		List<Responsavel> responsaveis = responsavelRepository.findAll();
		return this.mapper.mapAsList(responsaveis, ResponsavelDTO.class);
	}

	@Transactional(readOnly = true)
	public ResponsavelDTO findById(Integer id) {
		Responsavel responsavel = responsavelRepository.findById(id).orElse(null);
		return this.mapper.map(responsavel, ResponsavelDTO.class);
	}

	@Transactional
	public void deleteById(Integer id) {
		responsavelRepository.deleteById(id);
	}

	@Transactional
	public ResponsavelDTO save(ResponsavelDTO responsavelDTO) {
		Responsavel responsavel = this.mapper.map(responsavelDTO, Responsavel.class);
		responsavel = this.responsavelRepository.save(responsavel);
	    return this.mapper.map(responsavel, ResponsavelDTO.class);
	}
}
