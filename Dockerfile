FROM openjdk:11-jre-slim

# Copia el archivo jar generado al contenedor
COPY build/libs/batch-example-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto por el cual se comunicará tu aplicación (si es necesario)
EXPOSE 8080

# Ejecuta la aplicación Java en el contenedor
ENTRYPOINT ["java", "-jar", "/app.jar"]