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

import com.trabalhoia.chatbot.DTO.ProjetoDTO;
import com.trabalhoia.chatbot.service.ProjetoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;
	
	@GetMapping
    public List<ProjetoDTO> findAll() {
        return this.projetoService.findAll();
    }

    @GetMapping("/{id}")
    public ProjetoDTO findById(@PathVariable("id") Integer id) {
        return this.projetoService.findById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Integer id) {
        this.projetoService.deleteById(id);
    }

    @PostMapping
    public ProjetoDTO save(@RequestBody ProjetoDTO projetoDTO) {
        return this.projetoService.save(projetoDTO);
    }
}
