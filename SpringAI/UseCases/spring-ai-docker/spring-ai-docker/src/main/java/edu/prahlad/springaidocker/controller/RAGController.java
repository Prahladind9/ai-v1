package edu.prahlad.springaidocker.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RAGController(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("random/chat")
    public ResponseEntity<String> randomChat(@RequestParam("message") String message) {
        SearchRequest searchRequest = SearchRequest.builder().query(message)
                .topK(2)
                .similarityThreshold(0.5)
                .build();

        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        String similarContext = similarDocs.stream()
                .map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));

        String answer = chatClient.prompt().system(similarContext)
                .call().content();
        return ResponseEntity.ok(answer);
    }
}
