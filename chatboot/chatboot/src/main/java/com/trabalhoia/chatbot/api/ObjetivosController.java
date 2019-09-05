package com.trabalhoia.chatbot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalhoia.chatbot.DTO.ObjetivoDTO;
import com.trabalhoia.chatbot.service.ObjetivoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/objetivo")
public class ObjetivosController {

	@Autowired
	private ObjetivoService objetivoService;
	
	@GetMapping
    public List<ObjetivoDTO> findAll() {
        return this.objetivoService.findAll();
    }

    @GetMapping("/{id}")
    public ObjetivoDTO findById(@PathVariable("id") Long id) {
        return this.objetivoService.findById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Long id) {
        this.objetivoService.deleteById(id);
    }

    @PostMapping
    public ObjetivoDTO save(@RequestBody ObjetivoDTO objetoDTO) {
        return this.objetivoService.save(objetoDTO);
    }
}
