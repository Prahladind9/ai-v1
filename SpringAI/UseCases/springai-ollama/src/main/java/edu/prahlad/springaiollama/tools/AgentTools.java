package edu.prahlad.springaiollama.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import java.util.function.Function;

@Configuration
public class AgentTools {

    // Define a record for the input parameters (Spring AI converts this to JSON schema automatically)
    public record CodeRequest(String directoryPath) {}
    public record CodeResponse(String status) {}

    @Bean
    @Description("Scans a local directory workspace for compilation errors and code formatting.")
    public Function<CodeRequest, CodeResponse> scanWorkspaceCode() {
        return request -> {
            // Your logic to execute a local task
            System.out.println("Agent triggered skill: Scanning " + request.directoryPath());
            return new CodeResponse("No compilation errors found in " + request.directoryPath());
        };
    }
}
