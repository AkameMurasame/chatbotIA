package com.trabalhoia.chatbot.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoia.chatbot.DTO.ChatbotDTO;
import com.trabalhoia.chatbot.DTO.RetornoComparacaoDTO;
import com.trabalhoia.chatbot.config.ConverterMapper;
import com.trabalhoia.chatbot.models.ChatBot;
import com.trabalhoia.chatbot.models.Condicoes;
import com.trabalhoia.chatbot.models.Fatos;
import com.trabalhoia.chatbot.models.PerguntaResposta;
import com.trabalhoia.chatbot.models.Regras;
import com.trabalhoia.chatbot.repository.ChatbotRepository;
import com.trabalhoia.chatbot.repository.CondicoesRepository;
import com.trabalhoia.chatbot.repository.FatosRepository;
import com.trabalhoia.chatbot.repository.PerguntaRespostaRepository;
import com.trabalhoia.chatbot.repository.RegrasRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatbot")
public class ChatbotController {
	@Autowired
	private FatosRepository fatosRepository;

	@Autowired
	private ConverterMapper mapper;

	@Autowired
	private ChatbotRepository chatbotRepository;

	@Autowired
	private PerguntaRespostaRepository perguntaRespostaRepository;

	@Autowired
	private RegrasRepository regrasRepository;

	@Autowired
	private CondicoesRepository condicoesRepository;

	@PostMapping
	public ChatbotDTO chatBoot(@RequestBody ChatbotDTO objetoDTO) {
		ChatBot chatbot = this.mapper.map(objetoDTO, ChatBot.class);
		List<Fatos> sintomas = fatosRepository.findAll();
		PerguntaResposta ultimaPergunta = perguntaRespostaRepository.getUltimaPergunta();
		List<RetornoComparacaoDTO> retornoComparacao = new ArrayList<RetornoComparacaoDTO>();
		List<Regras> regras = null;
		List<Condicoes> condicoes = null;
		List<PerguntaResposta> perguntasFeitas = perguntaRespostaRepository
				.getPerguntasRespostasFeitas(objetoDTO.getUuid());
		if (objetoDTO.getRegra().equals(0)) {
			for (int x = 0; x < sintomas.size(); x++) {
				if (chatbot.getMsg().toLowerCase().contains(sintomas.get(x).getNome().toLowerCase())) {
					ultimaPergunta.setResposta(objetoDTO.getMsg());
					ultimaPergunta.setBotRaw(sintomas.get(x).getNome());
					ultimaPergunta.setUuid(objetoDTO.getUuid());
					perguntaRespostaRepository.save(ultimaPergunta);
					PerguntaResposta resposta = new PerguntaResposta();
					resposta.setPergunta(sintomas.get(x).getPergunta());
					resposta.setResposta("sim");
					resposta.setBotRaw("sim");
					regras = regrasRepository.getRegrasBySintoma(sintomas.get(x).getNome());
					resposta.setRegra(regras.get(0).getId());
					resposta.setUuid(objetoDTO.getUuid());
					perguntaRespostaRepository.save(resposta);
					condicoes = condicoesRepository.getCondicoesByRegra(regras.get(0).getId(),
							sintomas.get(x).getNome());
					objetoDTO.setRegra(regras.get(0).getId());
				}
			}
			PerguntaResposta novaPergunta = new PerguntaResposta();
			novaPergunta.setPergunta(condicoes.get(0).getPergunta());
			novaPergunta.setUuid(objetoDTO.getUuid());
			novaPergunta.setRegra(objetoDTO.getRegra());
			perguntaRespostaRepository.save(novaPergunta);
			objetoDTO.setMsg(condicoes.get(0).getPergunta());

			ChatBot novoChat = new ChatBot();
			novoChat.setMsg(objetoDTO.getMsg());
			novoChat.setUuid(objetoDTO.getUuid());
			chatbotRepository.save(novoChat);
		} else {
			/*
			 * if (perguntasFeitas.size() > 2) { retornoComparacao =
			 * verificarRegras(objetoDTO); objetoDTO.setRetorno(retornoComparacao); return
			 * objetoDTO; }
			 */
			ultimaPergunta.setResposta(objetoDTO.getMsg());
			ultimaPergunta.setBotRaw(objetoDTO.getMsg());
			ultimaPergunta.setUuid(objetoDTO.getUuid());
			perguntaRespostaRepository.save(ultimaPergunta);

			if (ultimaPergunta.getBotRaw().equals("sim")) {
				List<String> perguntas = new ArrayList<>();
				perguntasFeitas.stream().forEach(pergunta -> perguntas.add(pergunta.getPergunta()));
				condicoes = condicoesRepository.getCondicoesNotIn(objetoDTO.getRegra(), objetoDTO.getUuid());

				PerguntaResposta novaPergunta = new PerguntaResposta();
				novaPergunta.setPergunta(condicoes.get(0).getPergunta());
				novaPergunta.setUuid(objetoDTO.getUuid());
				//novaPergunta.setRegra(objetoDTO.getRegra());
				perguntaRespostaRepository.save(novaPergunta);
				objetoDTO.setMsg(condicoes.get(0).getPergunta());

				ChatBot novoChat = new ChatBot();
				novoChat.setMsg(objetoDTO.getMsg());
				novoChat.setUuid(objetoDTO.getUuid());
				chatbotRepository.save(novoChat);
			} else {
				regras = regrasRepository.getOuterRegraId(objetoDTO.getRegra());
				condicoes = condicoesRepository.getCondicoesNotInRegra(objetoDTO.getUuid());

				PerguntaResposta novaPergunta = new PerguntaResposta();
				novaPergunta.setPergunta(condicoes.get(0).getPergunta());
				novaPergunta.setUuid(objetoDTO.getUuid());
				//novaPergunta.setRegra(objetoDTO.getRegra());
				perguntaRespostaRepository.save(novaPergunta);
				objetoDTO.setMsg(condicoes.get(0).getPergunta());

				ChatBot novoChat = new ChatBot();
				novoChat.setMsg(objetoDTO.getMsg());
				novoChat.setUuid(objetoDTO.getUuid());
				chatbotRepository.save(novoChat);
			}
		}

		if (perguntasFeitas.size() > 2) {
			retornoComparacao = verificarRegras(objetoDTO);
			objetoDTO.setRetorno(retornoComparacao);
			return objetoDTO;
		}

		return objetoDTO;
	}

	private List<RetornoComparacaoDTO> verificarRegras(ChatbotDTO objetoDTO) {
		List<Regras> regras = regrasRepository.findAll();
		List<RetornoComparacaoDTO> retorno = new ArrayList<RetornoComparacaoDTO>();
		for (Regras regra : regras) {
			RetornoComparacaoDTO retornoComparacao = new RetornoComparacaoDTO();
			List<Condicoes> condicoes = condicoesRepository.getCondicoesByRegraFull(regra.getId());
			StringBuilder stringComparacao = new StringBuilder();
			Integer contador = 0;
			for (Condicoes condicao : condicoes) {
				if (condicao.getRegra().equals(regra.getId())) {
					List<PerguntaResposta> respostas = perguntaRespostaRepository
							.getPerguntasRespostasFeitasByRegra(objetoDTO.getUuid(), regra.getId());
					if (condicao.getTipo().equals("")) {
						for (PerguntaResposta resposta : respostas) {
							if (condicao.getPergunta().equals(resposta.getPergunta())) {
								if (condicao.getValor().equals(resposta.getBotRaw())) {
									stringComparacao.append("true/");
								} else {
									stringComparacao.append("false/");
								}
							}
						}
					} else {
						for (PerguntaResposta resposta : respostas) {
							if (condicao.getPergunta().equals(resposta.getPergunta())) {
								if ((contador + 1) < condicoes.size()) {
									stringComparacao.append(condicao.getTipo() + "/");
								}
								if (condicao.getValor().equals(resposta.getBotRaw())) {
									stringComparacao.append("true/");
								} else {
									stringComparacao.append("false/");
								}
							}
						}
					}					
				}
			}
			String resultado = stringComparacao.toString();
			retornoComparacao.setObjetivo(regra.getObjetivo());
			retornoComparacao.setRegra(regra.getId());
			retornoComparacao.setStringComparacao(resultado);
			retorno.add(retornoComparacao);
		}		

		for(RetornoComparacaoDTO retorDto : retorno) {
			String stringComparacao = retorDto.getStringComparacao();
			if (!stringComparacao.contains("OU")) {
				String[] testa = stringComparacao.split("E/");
				Integer contadorTrue = 0;
				for (Integer z = 0; z < testa.length; z++) {
					if (testa[z].equals("true/")) {
						contadorTrue++;
					}
				}
				List<Condicoes> condicoesretorno = condicoesRepository.getCondicoesByRegraFull(retorDto.getRegra());
				Integer porcentagem = (contadorTrue * 100) / condicoesretorno.size();
				retorDto.setResultado(porcentagem);
			}
		}
		
		return retorno;
	}

	@PostMapping("/create")
	public ChatbotDTO createBot(@RequestBody ChatbotDTO objetoDTO) {
		ChatBot chatbot = this.mapper.map(objetoDTO, ChatBot.class);
		chatbot = chatbotRepository.save(chatbot);
		PerguntaResposta perguntaResposta = new PerguntaResposta();
		perguntaResposta.setPergunta("0");
		perguntaResposta.setUuid(objetoDTO.getUuid());
		perguntaResposta.setRegra(0);
		perguntaRespostaRepository.save(perguntaResposta);
		return objetoDTO;
	}
}
