package com.example.chatgptbasedcookingingredients.controller;


import com.example.chatgptbasedcookingingredients.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final ChatGptService chatGptService;

    @PostMapping
    public String categorizeIngredient(@RequestBody String ingredient) {
        return chatGptService.categorizeIngredient(ingredient);
    }

    @PostMapping("/ask")
    public String askQuestion(@RequestBody String question) {
        return chatGptService.askQuestion(question);
    }

}
