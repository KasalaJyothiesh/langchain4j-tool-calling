package com.jyothiesh;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey="YOUR_API_KEY";

        DefinedTools tools = new DefinedTools();

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("YOUR_MODEL_NAME")
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .tools(tools)
                .build();

        Scanner sc = new Scanner(System.in);
        System.out.println("Hey, I'm here to help you! ask your query here.");
        while (true)
        {
            System.out.println("You : ");
            String userQuestion = sc.nextLine();
            if (userQuestion.equalsIgnoreCase("exit"))
            {
                System.out.println("Hope i have helped with your queries, I'd be happy to assist you again!");
                break;
            }
            String modelResponse = assistant.Chat(userQuestion);
            System.out.println("Assistant : "+modelResponse);
            System.out.println();
            System.out.println("Type 'exit' if you have no questions");
        }

    }
}