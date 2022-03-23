FROM openjdk:8-jre-alpine

# copy application JAR
COPY target/rss-tracker-v0-feeds-*.jar /app/rss-tracker-v0-feeds.jar

# Application entrypoint
CMD ["java", "-jar", "/app/rss-tracker-v0-feeds.jar"]