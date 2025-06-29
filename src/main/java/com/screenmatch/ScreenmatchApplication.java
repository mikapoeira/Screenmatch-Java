package com.screenmatch;

import com.screenmatch.service.ScreenMatchService;
import com.screenmatch.model.DadosSerie;
import com.screenmatch.exception.ApiException;
import com.screenmatch.exception.SerieNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(ScreenmatchApplication.class);
    
    @Autowired
    private ScreenMatchService screenMatchService;
    
    private final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }
    
    @Override
    public void run(String... args) {
        logger.info("=== ScreenMatch 2.0 - Iniciado ===");
        exibirMenu();
    }
    
    private void exibirMenu() {
        int opcao = -1;
        
        while (opcao != 0) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("🎬 SCREENMATCH 2.0 - Menu Principal");
            System.out.println("=".repeat(50));
            System.out.println("1 - Buscar séries");
            System.out.println("2 - Buscar série por nome (detalhada)");
            System.out.println("3 - Buscar série por ID IMDB");
            System.out.println("0 - Sair");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1 -> buscarSeries();
                    case 2 -> buscarSerieDetalhada();
                    case 3 -> buscarSeriePorId();
                    case 0 -> {
                        System.out.println("👋 Obrigado por usar o ScreenMatch!");
                        logger.info("Aplicação finalizada pelo usuário");
                    }
                    default -> System.out.println("❌ Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, digite um número válido!");
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado: " + e.getMessage());
                logger.error("Erro inesperado no menu", e);
            }
        }
    }
    
    private void buscarSeries() {
        System.out.print("\n🔍 Digite o nome da série para buscar: ");
        String nomeSerie = scanner.nextLine();
        
        try {
            List<DadosSerie> series = screenMatchService.buscarSeries(nomeSerie);
            
            System.out.println("\n📺 Resultados encontrados:");
            System.out.println("-".repeat(80));
            
            for (int i = 0; i < series.size(); i++) {
                DadosSerie serie = series.get(i);
                System.out.printf("%d. %s (%s) - %s - ⭐ %.1f%n", 
                    i + 1, 
                    serie.titulo(), 
                    serie.ano(), 
                    serie.tipo(),
                    serie.getAvaliacaoImdbAsDouble()
                );
            }
            
        } catch (SerieNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("❌ Erro na API: " + e.getMessage());
            logger.error("Erro na API ao buscar séries", e);
        }
    }
    
    private void buscarSerieDetalhada() {
        System.out.print("\n🔍 Digite o nome exato da série: ");
        String nomeSerie = scanner.nextLine();
        
        try {
            DadosSerie serie = screenMatchService.buscarSerieDetalhada(nomeSerie);
            exibirDetalhes(serie);
            
        } catch (SerieNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("❌ Erro na API: " + e.getMessage());
            logger.error("Erro na API ao buscar série detalhada", e);
        }
    }
    
    private void buscarSeriePorId() {
        System.out.print("\n🔍 Digite o ID IMDB da série (ex: tt0944947): ");
        String imdbId = scanner.nextLine();
        
        try {
            DadosSerie serie = screenMatchService.buscarSeriePorId(imdbId);
            exibirDetalhes(serie);
            
        } catch (SerieNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (ApiException e) {
            System.out.println("❌ Erro na API: " + e.getMessage());
            logger.error("Erro na API ao buscar série por ID", e);
        }
    }
    
    private void exibirDetalhes(DadosSerie serie) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("📺 DETALHES DA SÉRIE");
        System.out.println("=".repeat(80));
        System.out.println("🎬 Título: " + serie.titulo());
        System.out.println("📅 Ano: " + serie.ano());
        System.out.println("🎭 Gênero: " + serie.genero());
        System.out.println("👥 Atores: " + serie.atores());
        System.out.println("🎬 Diretor: " + serie.diretor());
        System.out.println("✍️ Escritor: " + serie.escritor());
        System.out.println("🌍 País: " + serie.pais());
        System.out.println("🗣️ Idioma: " + serie.idioma());
        System.out.println("⏱️ Duração: " + serie.duracao());
        System.out.println("📺 Temporadas: " + serie.getTotalTemporadasAsInt());
        System.out.println("⭐ Avaliação IMDB: " + serie.getAvaliacaoImdbAsDouble());
        System.out.println("🗳️ Votos IMDB: " + serie.votosImdb());
        System.out.println("🏆 Prêmios: " + serie.premios());
        System.out.println("📖 Sinopse: " + serie.sinopse());
        System.out.println("🆔 IMDB ID: " + serie.imdbId());
        System.out.println("=".repeat(80));
    }
}
