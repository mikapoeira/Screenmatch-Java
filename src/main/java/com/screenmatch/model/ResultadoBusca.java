package com.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoBusca(
    @JsonAlias("Search") List<DadosSerie> resultados,
    @JsonAlias("totalResults") String totalResultados,
    @JsonAlias("Response") String resposta,
    @JsonAlias("Error") String erro
) {
    
    public boolean isSuccessful() {
        return "True".equalsIgnoreCase(resposta) && erro == null;
    }
    
    public int getTotalResultadosAsInt() {
        try {
            return totalResultados != null ? Integer.parseInt(totalResultados) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
