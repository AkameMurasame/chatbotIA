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

import com.trabalhoia.chatbot.DTO.FatosDTO;
import com.trabalhoia.chatbot.service.FatosService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fatos")
public class FatosController {
	@Autowired
	private FatosService fatosService;
	
	@GetMapping
    public List<FatosDTO> findAll() {
        return this.fatosService.findAll();
    }

    @GetMapping("/{id}")
    public FatosDTO findById(@PathVariable("id") Integer id) {
        return this.fatosService.findById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Integer id) {
        this.fatosService.deleteById(id);
    }

    @PostMapping
    public FatosDTO save(@RequestBody FatosDTO objetoDTO) {
        return this.fatosService.save(objetoDTO);
    }
}
