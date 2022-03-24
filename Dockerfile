FROM openjdk:8-jre-alpine

# copy application JAR
COPY target/rss-tracker-v0-feeds-*.jar /app/rss-tracker-v0-feeds.jar

# Default environment variables
ENV MONGODB_HOST "localhost"
ENV MONGODB_PORT "27017"
ENV MONGODB_DATABASE "rss_tracker_db"
ENV MONGODB_USERNAME "YOUR_MONGODB_USERNAME"
ENV MONGODB_PASSWORD "YOUR_MONGODB_PASSWORD"

# Application entrypoint
CMD ["java", "-jar", "/app/rss-tracker-v0-feeds.jar"]