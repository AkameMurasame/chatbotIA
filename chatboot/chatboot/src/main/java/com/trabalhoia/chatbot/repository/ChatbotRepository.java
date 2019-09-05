package com.trabalhoia.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trabalhoia.chatbot.models.ChatBot;

public interface ChatbotRepository extends JpaRepository<ChatBot, Integer>{
	
	@Query(value = "select * from chat where uuid = ?", nativeQuery = true)
	public ChatBot getChat(String id);
}
