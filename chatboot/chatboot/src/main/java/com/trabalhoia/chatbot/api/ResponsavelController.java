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

import com.trabalhoia.chatbot.DTO.ResponsavelDTO;
import com.trabalhoia.chatbot.service.ResponsavelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {

	@Autowired
	private ResponsavelService responsavelService;
	
	@GetMapping
    public List<ResponsavelDTO> findAll() {
        return this.responsavelService.findAll();
    }

    @GetMapping("/{id}")
    public ResponsavelDTO findById(@PathVariable("id") Integer id) {
        return this.responsavelService.findById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestBody Integer id) {
        this.responsavelService.deleteById(id);
    }

    @PostMapping
    public ResponsavelDTO save(@RequestBody ResponsavelDTO responsavelDTO) {
    	return this.responsavelService.save(responsavelDTO);
    }
}
