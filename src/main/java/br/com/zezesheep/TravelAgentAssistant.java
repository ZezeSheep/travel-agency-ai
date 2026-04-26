package br.com.zezesheep;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface TravelAgentAssistant {

    String chat(String userMessage);
}
