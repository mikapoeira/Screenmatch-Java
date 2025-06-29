package com.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.screenmatch.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConverteDados implements IConverteDados {
    
    private static final Logger logger = LoggerFactory.getLogger(ConverteDados.class);
    private final ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            logger.debug("Convertendo JSON para classe: {}", classe.getSimpleName());
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao converter JSON para {}: {}", classe.getSimpleName(), e.getMessage());
            throw new ApiException("Erro ao processar dados da API", e);
        }
    }
}
