# Build da aplicação com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho.
WORKDIR /app

# Copia o arquivo de configuração do Maven (pom.xml).
COPY pom.xml .

# Copia o resto do código.
COPY src ./src

# Roda o comando do Maven para baixar as dependências e empacotar a aplicação em um .jar.
# O -DskipTests pula a execução dos testes para acelerar o build.
RUN mvn clean install -DskipTests

# Imagem base de Java (JRE), é mais leve para rodar a aplicação.
FROM openjdk:21-jre-slim

# Define o diretório de trabalho.
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio de build para o nosso contêiner final.
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 1000. O Render vai mapear isso internamente.
EXPOSE 1000
# Comando que será executado quando o contêiner iniciar.
ENTRYPOINT ["java", "-jar", "app.jar"]