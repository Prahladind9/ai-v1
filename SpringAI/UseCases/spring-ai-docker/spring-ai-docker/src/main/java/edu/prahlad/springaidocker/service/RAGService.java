package edu.prahlad.springaidocker.service;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RAGService {

    private static final Integer TOP_K = 3;
    private static final Double SIMILARITY_THRESHOLD = 0.5d;

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RAGService(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    public String randomChat(String message) {
        SearchRequest searchRequest = getSearchRequest(message);
        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        String similarContext = getSimilarSearch(similarDocs);

        String answer = chatClient.prompt().system(similarContext)
                .call().content();
        return answer;
    }

    public String randomChatWithSystemPrompt(String message, Resource promptTemplate) {
        return getString(message, promptTemplate);
    }

    public String pdfChat(String message, Resource promptTemplate) {
        return getString(message, promptTemplate);
    }

    @Nullable
    private String getString(String message, Resource promptTemplate) {
        SearchRequest searchRequest = getSearchRequest(message);

        List<Document> similarDocs = vectorStore.similaritySearch(searchRequest);
        String similarContext = getSimilarSearch(similarDocs);

        String answer = chatClient.prompt()
                .system(promptSystemSpec ->
                        promptSystemSpec.text(promptTemplate)
                                .param("documents", similarContext))
                .call().content();
        return answer;
    }

    private static @NonNull String getSimilarSearch(List<Document> similarDocs) {
        String similarContext = similarDocs.stream()
                .map(Document::getText)
                .collect(Collectors.joining(System.lineSeparator()));
        return similarContext;
    }

    private static @NonNull SearchRequest getSearchRequest(String message) {
        SearchRequest searchRequest = SearchRequest.builder().query(message)
                .topK(TOP_K)
                .similarityThreshold(SIMILARITY_THRESHOLD)
                .build();
        return searchRequest;
    }

}
