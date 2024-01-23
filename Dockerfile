# Primeira etapa: Construir a aplicação
FROM ubuntu:latest AS build

# Atualizar o sistema e instalar o JDK e o Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar os arquivos necessários para o diretório de trabalho
COPY src src
COPY pom.xml .

# Baixar as dependências do Maven e construir o projeto
RUN mvn clean package -DskipTests

# Segunda etapa: Criar uma imagem mais leve com apenas o JAR
FROM openjdk:17-jdk-slim

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar o JAR da primeira etapa para a segunda
COPY --from=build /app/target/gerenciadordetarefa-1.0.0.jar app.jar

# Expor a porta 8080 (se necessário)
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for executado
ENTRYPOINT ["java", "-jar", "app.jar"]