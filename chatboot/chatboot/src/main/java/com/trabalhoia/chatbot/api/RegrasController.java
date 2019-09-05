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

import com.trabalhoia.chatbot.DTO.RegrasDTO;
import com.trabalhoia.chatbot.service.RegrasService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/regras")
public class RegrasController {
	@Autowired
	private RegrasService regrasService;
	
	@GetMapping
    public List<RegrasDTO> findAll() {
        return this.regrasService.findAll();
    }

    @GetMapping("/{id}")
    public RegrasDTO findById(@PathVariable("id") Integer id) {
        return this.regrasService.findById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Integer id) {
        this.regrasService.deleteById(id);
    }

    @PostMapping
    public RegrasDTO save(@RequestBody RegrasDTO objetoDTO) {
        return this.regrasService.save(objetoDTO);
    }
}
