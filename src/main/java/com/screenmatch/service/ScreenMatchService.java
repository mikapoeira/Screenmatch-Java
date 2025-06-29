package com.screenmatch.service;

import com.screenmatch.config.OmdbApiConfig;
import com.screenmatch.exception.SerieNotFoundException;
import com.screenmatch.model.DadosSerie;
import com.screenmatch.model.ResultadoBusca;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ScreenMatchService {
    
    private static final Logger logger = LoggerFactory.getLogger(ScreenMatchService.class);
    
    private final ConsumoApi consumoApi;
    private final IConverteDados conversor;
    private final OmdbApiConfig config;
    
    @Autowired
    public ScreenMatchService(ConsumoApi consumoApi, IConverteDados conversor, OmdbApiConfig config) {
        this.consumoApi = consumoApi;
        this.conversor = conversor;
        this.config = config;
    }
    
    public List<DadosSerie> buscarSeries(String nomeSerie) {
        logger.info("Buscando séries com o termo: {}", nomeSerie);
        
        String nomeEncoded = URLEncoder.encode(nomeSerie, StandardCharsets.UTF_8);
        String endereco = String.format("%s?s=%s&apikey=%s", 
            config.getBaseUrl(), nomeEncoded, config.getKey());
        
        String json = consumoApi.obterDados(endereco);
        ResultadoBusca resultado = conversor.obterDados(json, ResultadoBusca.class);
        
        if (!resultado.isSuccessful()) {
            logger.warn("Busca não retornou resultados para: {}", nomeSerie);
            throw new SerieNotFoundException("Nenhuma série encontrada para: " + nomeSerie);
        }
        
        logger.info("Encontradas {} séries para o termo: {}", 
            resultado.getTotalResultadosAsInt(), nomeSerie);
        
        return resultado.resultados().stream()
            .filter(DadosSerie::isValid)
            .toList();
    }
    
    public DadosSerie buscarSeriePorId(String imdbId) {
        logger.info("Buscando série por ID: {}", imdbId);
        
        String endereco = String.format("%s?i=%s&apikey=%s", 
            config.getBaseUrl(), imdbId, config.getKey());
        
        String json = consumoApi.obterDados(endereco);
        DadosSerie serie = conversor.obterDados(json, DadosSerie.class);
        
        if (!serie.isValid()) {
            throw new SerieNotFoundException("Série não encontrada para ID: " + imdbId);
        }
        
        logger.info("Série encontrada: {}", serie.titulo());
        return serie;
    }
    
    public DadosSerie buscarSerieDetalhada(String nomeSerie) {
        logger.info("Buscando detalhes da série: {}", nomeSerie);
        
        String nomeEncoded = URLEncoder.encode(nomeSerie, StandardCharsets.UTF_8);
        String endereco = String.format("%s?t=%s&apikey=%s", 
            config.getBaseUrl(), nomeEncoded, config.getKey());
        
        String json = consumoApi.obterDados(endereco);
        DadosSerie serie = conversor.obterDados(json, DadosSerie.class);
        
        if (!serie.isValid()) {
            throw new SerieNotFoundException("Série não encontrada: " + nomeSerie);
        }
        
        logger.info("Detalhes obtidos para: {}", serie.titulo());
        return serie;
    }
}
