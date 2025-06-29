# 🎬 ScreenMatch 2.0

> **Aplicação Java/Spring Boot para busca de informações sobre séries e filmes usando a API OMDB**

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Sobre o Projeto

O **ScreenMatch** é uma aplicação console desenvolvida em Java com Spring Boot que permite buscar informações detalhadas sobre séries e filmes através da API do OMDB (Open Movie Database). A aplicação oferece uma interface interativa no terminal para realizar diferentes tipos de buscas.

### ✨ Funcionalidades

- 🔍 **Busca por nome**: Encontre múltiplas séries/filmes por termo de busca
- 📺 **Busca detalhada**: Obtenha informações completas de uma série específica
- 🆔 **Busca por ID IMDB**: Consulte diretamente pelo identificador IMDB
- ⭐ **Avaliações**: Visualize ratings e número de votos do IMDB
- 🎭 **Informações completas**: Elenco, diretor, gênero, sinopse e muito mais
- 🏆 **Prêmios**: Veja os prêmios conquistados pela produção

## 🚀 Tecnologias Utilizadas

- **Java 17+** - Linguagem de programação
- **Spring Boot 3.2.1** - Framework principal
- **Spring Boot Starter Web** - Para funcionalidades web
- **Spring Boot Configuration Processor** - Processamento de configurações
- **Jackson** - Serialização/deserialização JSON
- **SLF4J + Logback** - Sistema de logging
- **Maven** - Gerenciamento de dependências
- **HTTP Client** - Cliente HTTP nativo do Java 11+

## 📁 Estrutura do Projeto

\`\`\`
src/
├── main/
│   ├── java/com/screenmatch/
│   │   ├── config/
│   │   │   └── OmdbApiConfig.java          # Configurações da API
│   │   ├── exception/
│   │   │   ├── ApiException.java           # Exceção para erros de API
│   │   │   └── SerieNotFoundException.java # Exceção para série não encontrada
│   │   ├── model/
│   │   │   ├── DadosSerie.java            # Record para dados da série
│   │   │   └── ResultadoBusca.java        # Record para resultado da busca
│   │   ├── service/
│   │   │   ├── ConsumoApi.java            # Serviço de consumo da API
│   │   │   ├── ConverteDados.java         # Implementação de conversão JSON
│   │   │   ├── IConverteDados.java        # Interface de conversão
│   │   │   └── ScreenMatchService.java    # Serviço principal da aplicação
│   │   └── ScreenmatchApplication.java    # Classe principal
│   └── resources/
│       └── application.yml                # Configurações da aplicação
├── test/                                  # Testes unitários
├── logs/                                  # Arquivos de log
├── pom.xml                               # Configurações Maven
└── README.md                             # Este arquivo
\`\`\`

## ⚙️ Configuração e Instalação

### Pré-requisitos

- ☕ **Java 17 ou superior**
- 📦 **Maven 3.6 ou superior**
- 🔑 **Chave da API OMDB** (gratuita em [omdbapi.com](http://www.omdbapi.com/))

### 🔧 Instalação

1. **Clone o repositório**
   \`\`\`bash
   git clone https://github.com/mikapoeira/Spring-sem-web.git
   cd Spring-sem-web
   \`\`\`

2. **Configure a chave da API**
   
   Edite o arquivo \`src/main/resources/application.yml\`:
   \`\`\`yaml
   omdb:
     api:
       key: "SUA_CHAVE_API_AQUI"
   \`\`\`
   
   Ou defina como variável de ambiente:
   \`\`\`bash
   export OMDB_API_KEY=sua_chave_api_aqui
   \`\`\`

3. **Compile o projeto**
   \`\`\`bash
   mvn clean compile
   \`\`\`

4. **Execute a aplicação**
   \`\`\`bash
   mvn spring-boot:run
   \`\`\`

## 🎯 Como Usar

Ao executar a aplicação, você verá o menu principal:

\`\`\`
==================================================
🎬 SCREENMATCH 2.0 - Menu Principal
==================================================
1 - Buscar séries
2 - Buscar série por nome (detalhada)
3 - Buscar série por ID IMDB
0 - Sair
==================================================
\`\`\`

### Exemplos de Uso

**1. Busca por séries:**
- Digite: \`Game of Thrones\`
- Resultado: Lista com todas as séries relacionadas

**2. Busca detalhada:**
- Digite: \`Breaking Bad\`
- Resultado: Informações completas da série

**3. Busca por ID IMDB:**
- Digite: \`tt0944947\`
- Resultado: Detalhes de Game of Thrones

## 📊 Exemplo de Saída

\`\`\`
================================================================================
📺 DETALHES DA SÉRIE
================================================================================
🎬 Título: Breaking Bad
📅 Ano: 2008–2013
🎭 Gênero: Crime, Drama, Thriller
👥 Atores: Bryan Cranston, Aaron Paul, Anna Gunn
🎬 Diretor: N/A
✍️ Escritor: Vince Gilligan
🌍 País: United States
🗣️ Idioma: English, Spanish
⏱️ Duração: 49 min
📺 Temporadas: 5
⭐ Avaliação IMDB: 9.5
🗳️ Votos IMDB: 1,673,637
🏆 Prêmios: Won 16 Primetime Emmys. 58 wins & 165 nominations total
📖 Sinopse: A chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine with a former student in order to secure his family's future.
🆔 IMDB ID: tt0903747
================================================================================
\`\`\`

## 🔧 Configurações Avançadas

### Profiles de Ambiente

A aplicação suporta diferentes profiles:

- **dev**: Para desenvolvimento local
- **prod**: Para produção

### Configurações de Timeout

Ajuste o timeout das requisições no \`application.yml\`:

\`\`\`yaml
omdb:
  api:
    timeout: 30s  # Timeout personalizado
\`\`\`

### Configurações de Log

Os logs são salvos em \`logs/screenmatch.log\` e podem ser configurados:

\`\`\`yaml
logging:
  level:
    com.screenmatch: DEBUG  # Nível de log para a aplicação
  file:
    name: logs/screenmatch.log
\`\`\`

## 🧪 Testes

Execute os testes unitários:

\`\`\`bash
mvn test
\`\`\`

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (\`git checkout -b feature/AmazingFeature\`)
3. Commit suas mudanças (\`git commit -m 'Add some AmazingFeature'\`)
4. Push para a branch (\`git push origin feature/AmazingFeature\`)
5. Abra um Pull Request

## 📝 Changelog

### Versão 2.0.0 (Atual)
- ✅ Migração para Spring Boot 3.2.1
- ✅ Uso de Java Records para DTOs
- ✅ Sistema de logging estruturado
- ✅ Tratamento robusto de exceções
- ✅ Configurações externalizadas
- ✅ Interface de usuário melhorada
- ✅ Validações de dados
- ✅ Suporte a profiles de ambiente

### Versão 1.0.0 (Anterior)
- ✅ Funcionalidade básica de busca
- ✅ Integração com API OMDB
- ✅ Conversão JSON básica

## 🐛 Problemas Conhecidos

- A API OMDB tem limite de 1000 requisições por dia na versão gratuita
- Algumas séries podem não ter todas as informações disponíveis

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões:

1. Verifique se já existe uma [issue](https://github.com/mikapoeira/Spring-sem-web/issues) similar
2. Crie uma nova issue com detalhes do problema
3. Ou entre em contato através do GitHub

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🙏 Agradecimentos

- [OMDB API](http://www.omdbapi.com/) - Pela API gratuita de filmes e séries
- [Spring Boot](https://spring.io/projects/spring-boot) - Framework incrível
- Comunidade Java - Pelo suporte e recursos

---

**Desenvolvido com ❤️ por [mikapoeira](https://github.com/mikapoeira)**

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório!
\`\`\`

## 🔗 Links Úteis

- [Documentação Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [OMDB API Documentation](http://www.omdbapi.com/)
- [Java 17 Documentation](https://docs.oracle.com/en/java/javase/17/)
- [Maven Documentation](https://maven.apache.org/guides/)
\`\`\`
