FROM eclipse-temurin:21-jre

WORKDIR /app

COPY financialIndicatorsApi.jar app.jar

EXPOSE 8082

# Variáveis de ambiente padrão
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
