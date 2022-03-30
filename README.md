# rss-tracker_v0_feeds
[![Build & Report](https://github.com/Flashky/rss-tracker_v0_feeds/actions/workflows/build-report.yml/badge.svg)](https://github.com/Flashky/rss-tracker_v0_feeds/actions/workflows/build-report.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8c1b7b60abea416883adfb28f3697880)](https://www.codacy.com/gh/Flashky/rss-tracker_v0_feeds/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Flashky/rss-tracker_v0_feeds&amp;utm_campaign=Badge_Grade) 
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/8c1b7b60abea416883adfb28f3697880)](https://www.codacy.com/gh/Flashky/rss-tracker_v0_feeds/dashboard?utm_source=github.com&utm_medium=referral&utm_content=Flashky/rss-tracker_v0_feeds&utm_campaign=Badge_Coverage)


## Configuration properties

You must set these configuration properties by overriding either ``application.yml`` or as environment variables before running the service:

Property | Description | Default value
--|--|--
``MONGODB_HOST`` | The MongoDB instance hostname or ip | ``localhost``
``MONGODB_PORT`` | The MongoDB port | ``27017``
``MONGODB_DATABASE`` | The MongoDB database | ``rss_tracker_db``
``MONGODB_USERNAME`` | The MongoDB username | ``admin`` **(*)**
``MONGODB_PASSWORD`` | The MongoDB password | ``admin`` **(*)**

***:** *It is highly encouraged to change both default username and password.*

## Running the service

There are several ways to run the service.

- [Java Standalone]
- Docker Standalone
- Docker Compose

### Java Standalone

#### Requirements

- JDK 8.
- Maven
- MongoDB instance running with user and password.

#### Build

Open a terminal, clone the repo and build with maven:

```ssh
git clone https://github.com/Flashky/rss-tracker_v0_feeds.git
cd rss-tracker_v0_feeds
mvn clean package
```

#### Configure environment variables

Check **Configuration properties** section.

#### Run

```ssh
java -jar target/rss-tracker-v0-feeds-{version}.jar
```

**Note:** *Must be* ``version`` *the compiled version suffix.*