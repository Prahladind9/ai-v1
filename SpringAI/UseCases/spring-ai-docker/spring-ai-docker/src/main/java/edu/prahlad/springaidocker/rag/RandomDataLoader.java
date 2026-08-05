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
                "Python is commonly used for machine learning and automation tasks.",
                "Docker packages applications into lightweight containers.",
                "Kubernetes automates container orchestration at scale.",
                "Redis is an in-memory data store used for caching.",
                "REST APIs allow stateless client-server communication.",
                "GraphQL enables clients to fetch exactly the data they need.",
                "Blockchain is a distributed, immutable ledger of transactions.",
                "WebAssembly allows code written in other languages to run in the browser.",
                "CI/CD automates the process of testing and deploying software.",
                "Compound interest increases investment returns over time.",
                "Mutual funds pool money from investors to buy securities.",
                "Bitcoin operates on a decentralized peer-to-peer network.",
                "Credit scores influence the interest rates on loans.",
                "Diversifying investments reduces overall financial risk.",
                "The stock market opens at 9:30 a.m. EST on weekdays.",
                "Inflation is the rate at which the general price level rises.",
                "Derivatives are financial contracts whose value is derived from an underlying asset.",
                "A hedge fund pools capital to invest in various assets.",
                "The Federal Reserve sets monetary policy to manage the economy.",
                "Photosynthesis is how plants convert sunlight into chemical energy.",
                "DNA is the molecule that carries genetic instructions in living organisms.",
                "Earth revolves around the Sun in an elliptical orbit.",
                "Volcanoes form when magma rises through Earth's crust.",
                "Earthquakes are caused by sudden tectonic shifts.",
                "Lightning is a discharge of electricity caused by charged clouds.",
                "The ozone layer protects Earth from harmful ultraviolet rays.",
                "Gravity is the fundamental force that attracts objects toward each other.",
                "Plate tectonics explains the movement and interaction of Earth's crustal plates.",
                "The nucleus is the dense center of an atom.",
                "Drinking water improves digestion and skin health.",
                "A balanced diet includes proteins, carbohydrates, fats, and vitamins.",
                "Walking 30 minutes a day improves cardiovascular health.",
                "Meditation can reduce stress and improve focus.",
                "Too much sugar increases the risk of diabetes.",
                "Sleep deprivation negatively impacts cognitive function.",
                "Vitamin D is essential for bone health and immune function.",
                "Omega-3 fatty acids are essential fats for brain health.",
                "Resistance training helps build muscle mass and strength.",
                "Gut health is strongly linked to overall immune system function.",
                "The Sahara is the largest hot desert in the world.",
                "Mount Kilimanjaro is the tallest mountain in Africa.",
                "The Amazon River is the second longest river in the world.",
                "The Great Wall of China is a massive fortification system.",
                "Niagara Falls is located on the border between the U.S. and Canada.",
                "The Ring of Fire is a zone of intense seismic and volcanic activity.",
                "The Suez Canal is a vital waterway connecting the Mediterranean Sea and the Red Sea.",
                "The Arctic is the northernmost region of the Earth.",
                "The Himalayas are the world's highest mountain range.",
                "Japan is known for its advanced technology and unique culture."
        );

        List<Document> documents = sentences.stream().map(
                Document::new
        ).toList();

        vectorStore.add(documents);
    }
}
