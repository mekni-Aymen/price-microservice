# Utiliser une image officielle OpenJDK
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans l'image Docker
COPY target/intervista-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port de l'application
EXPOSE 8080

# Exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]

## run this command in the same location of your file #Dockerfile
## to create image of your application with the name of the image <springboot-mongodb-app>
# docker build -t springboot-mongodb-app .

