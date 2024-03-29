package com.example.chatgptbasedcookingingredients.service;

import com.example.chatgptbasedcookingingredients.exception.CustomExceptionToShutUpSonarLint;
import com.example.chatgptbasedcookingingredients.model.ChatGptRequest;
import com.example.chatgptbasedcookingingredients.model.ChatGptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ChatGptService
{
    private final RestClient client;

    public ChatGptService(@Value("${open.ai.key") String apikey,
                          @Value("${open.ai.url") String baseurl){
        client = RestClient.builder()
                .baseUrl(baseurl)
                .defaultHeader("Authorization", "Bearer " + apikey)
                .build();
    }

    public String askQuestion(String q){
        ChatGptRequest request = new ChatGptRequest(q);
        return postPrompt(request);
    }

    public String categorizeIngredient(String ingredient){
        ChatGptRequest request = new ChatGptRequest("Is \"" + ingredient + "\" vegan, vegetarian, regular, or no food? Provide one-word answer prioritizing from vegan to no food.");
        return postPrompt(request);
    }

    private String postPrompt(ChatGptRequest request) {
        ChatGptResponse response = client.post()
                .body(request)
                .retrieve()
                .body(ChatGptResponse.class);
        if(response == null){throw new CustomExceptionToShutUpSonarLint("Error: No response given.");}
        if(response.getAnswer() == null){throw new CustomExceptionToShutUpSonarLint("Error: No answer given.");}
        return response.getAnswer();
    }
}
