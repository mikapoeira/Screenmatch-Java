package com.screenmatch.service;

import com.screenmatch.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class ConsumoApi {
    
    private static final Logger logger = LoggerFactory.getLogger(ConsumoApi.class);
    private final HttpClient client;
    
    public ConsumoApi() {
        this.client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .build();
    }
    
    public String obterDados(String endereco) {
        logger.info("Fazendo requisição para: {}", endereco);
        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .timeout(Duration.ofSeconds(30))
            .header("User-Agent", "ScreenMatch/2.0")
            .GET()
            .build();
        
        try {
            HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
            
            logger.debug("Status da resposta: {}", response.statusCode());
            
            if (response.statusCode() != 200) {
                throw new ApiException("Erro na API: Status " + response.statusCode());
            }
            
            return response.body();
            
        } catch (IOException | InterruptedException e) {
            logger.error("Erro ao fazer requisição para {}: {}", endereco, e.getMessage());
            throw new ApiException("Erro ao conectar com a API", e);
        }
    }
}
