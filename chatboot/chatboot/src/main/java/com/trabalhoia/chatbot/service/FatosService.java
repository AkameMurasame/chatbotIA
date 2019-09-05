package com.trabalhoia.chatbot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalhoia.chatbot.DTO.FatosDTO;
import com.trabalhoia.chatbot.config.ConverterMapper;
import com.trabalhoia.chatbot.models.Fatos;
import com.trabalhoia.chatbot.models.RespostasFato;
import com.trabalhoia.chatbot.repository.FatosRepository;
import com.trabalhoia.chatbot.repository.RespostaFatoRepository;

@Service
public class FatosService {
	@Autowired
	private FatosRepository fatosRepository;
	
	@Autowired
	private RespostaFatoRepository respostaFatoRepository; 
	
	@Autowired
	private ConverterMapper mapper;

	@Transactional(readOnly = true)
	public List<FatosDTO> findAll() {
		List<Fatos> fatos = fatosRepository.findAll();
		List<FatosDTO> fatosDTO =  this.mapper.mapAsList(fatos, FatosDTO.class);
		fatosDTO.stream().forEach(fato -> fato.setRespostasFato(respostaFatoRepository.getRespostasFato(fato.getId())));
		return fatosDTO;
	}

	@Transactional(readOnly = true)
	public FatosDTO findById(Integer id) {
		Fatos fato = fatosRepository.findById(id).orElse(null);
		return this.mapper.map(fato, FatosDTO.class);
	}

	@Transactional
	public void deleteById(Integer id) {
		fatosRepository.deleteById(id);
	}

	@Transactional
	public FatosDTO save(FatosDTO fatoDTO) {
		Fatos fato = this.mapper.map(fatoDTO, Fatos.class);
		fato = this.fatosRepository.save(fato);
		Integer idPergunta = fato.getId();
		List<RespostasFato> respostasFato = fatoDTO.getRespostasFato();
		respostasFato.stream().forEach(respostaFato -> respostaFato.setPergunta(idPergunta));
		respostasFato.stream().forEach(resposta -> respostaFatoRepository.save(resposta));
	    return this.mapper.map(fato, FatosDTO.class);
	}

}
