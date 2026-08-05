package edu.prahlad.springaidocker.rag;


import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JavaCodingPolicyLoader {
    private final VectorStore vectorStore;

    public JavaCodingPolicyLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Value("classpath:JavaCodingPolicy.pdf")
    Resource javaCodingPolicyFile;

    @PostConstruct
    public void loadPDF(){
        TikaDocumentReader reader = new TikaDocumentReader(javaCodingPolicyFile);
        List<Document> docs = reader.get();
        vectorStore.add(docs);
    }
}
