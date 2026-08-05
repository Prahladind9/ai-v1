package edu.prahlad.springaidocker.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RandomDataLoader {

    private final VectorStore vectorStore;

    public RandomDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadSentencesIntoVectorStore() {
        List<String> sentences = List.of(
                "Java is used for building scalable enterprise applications.",
                "Docker packages applications into lightweight containers.",
                "STEM stands for Science, Tech, Eng and Maths",
                "Walking 30mins per day improves health",
                "Stock market is closed on Sunday"
        );

        List<Document> documents = sentences.stream().map(
                Document::new
        ).toList();

        vectorStore.add(documents);
    }
}
