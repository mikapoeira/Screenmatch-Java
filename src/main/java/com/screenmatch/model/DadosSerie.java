package com.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(
    @JsonAlias("Title") String titulo,
    @JsonAlias("Year") String ano,
    @JsonAlias("imdbID") String imdbId,
    @JsonAlias("Type") String tipo,
    @JsonAlias("Poster") String poster,
    @JsonAlias("Plot") String sinopse,
    @JsonAlias("Genre") String genero,
    @JsonAlias("Actors") String atores,
    @JsonAlias("Director") String diretor,
    @JsonAlias("Writer") String escritor,
    @JsonAlias("Language") String idioma,
    @JsonAlias("Country") String pais,
    @JsonAlias("Awards") String premios,
    @JsonAlias("imdbRating") String avaliacaoImdb,
    @JsonAlias("imdbVotes") String votosImdb,
    @JsonAlias("Runtime") String duracao,
    @JsonAlias("Released") String dataLancamento,
    @JsonAlias("totalSeasons") String totalTemporadas
) {
    
    public boolean isValid() {
        return titulo != null && !titulo.trim().isEmpty() && 
               imdbId != null && !imdbId.trim().isEmpty();
    }
    
    public double getAvaliacaoImdbAsDouble() {
        try {
            return avaliacaoImdb != null && !avaliacaoImdb.equals("N/A") 
                ? Double.parseDouble(avaliacaoImdb) 
                : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    public int getTotalTemporadasAsInt() {
        try {
            return totalTemporadas != null && !totalTemporadas.equals("N/A") 
                ? Integer.parseInt(totalTemporadas) 
                : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
