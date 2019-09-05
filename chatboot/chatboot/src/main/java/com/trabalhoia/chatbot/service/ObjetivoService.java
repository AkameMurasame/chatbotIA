package com.trabalhoia.chatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalhoia.chatbot.DTO.ObjetivoDTO;
import com.trabalhoia.chatbot.config.ConverterMapper;
import com.trabalhoia.chatbot.models.Objetivos;
import com.trabalhoia.chatbot.repository.ObjetivoRepository;

@Service
public class ObjetivoService {

	@Autowired
	private ObjetivoRepository objetivoRepository;
	@Autowired
	private ConverterMapper mapper;

	@Transactional(readOnly = true)
	public List<ObjetivoDTO> findAll() {
		List<Objetivos> objetivos = objetivoRepository.findAll();
		return this.mapper.mapAsList(objetivos, ObjetivoDTO.class);
	}

	@Transactional(readOnly = true)
	public ObjetivoDTO findById(Long id) {
		Objetivos objetivo = objetivoRepository.findById(id).orElse(null);
		return this.mapper.map(objetivo, ObjetivoDTO.class);
	}

	@Transactional
	public void deleteById(Long id) {
		objetivoRepository.deleteById(id);
	}

	@Transactional
	public ObjetivoDTO save(ObjetivoDTO objetivoDTO) {
		Objetivos objetivo = this.mapper.map(objetivoDTO, Objetivos.class);
		objetivo = this.objetivoRepository.save(objetivo);
	    return this.mapper.map(objetivo, ObjetivoDTO.class);
	}
}
