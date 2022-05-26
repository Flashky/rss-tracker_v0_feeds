# rss-tracker_v0_feeds
[![Build & Report](https://github.com/Flashky/rss-tracker_v0_feeds/actions/workflows/build-report.yml/badge.svg)](https://github.com/Flashky/rss-tracker_v0_feeds/actions/workflows/build-report.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8c1b7b60abea416883adfb28f3697880)](https://www.codacy.com/gh/Flashky/rss-tracker_v0_feeds/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Flashky/rss-tracker_v0_feeds&amp;utm_campaign=Badge_Grade) 
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/8c1b7b60abea416883adfb28f3697880)](https://www.codacy.com/gh/Flashky/rss-tracker_v0_feeds/dashboard?utm_source=github.com&utm_medium=referral&utm_content=Flashky/rss-tracker_v0_feeds&utm_campaign=Badge_Coverage)


## Configuration properties

Before running the service, you will need to customize several properties:

Property | Description | Default value
--|--|--
``MONGODB_HOST`` | The MongoDB instance hostname or ip | ``localhost``
``MONGODB_PORT`` | The MongoDB port | ``27017``
``MONGODB_USERNAME`` | The MongoDB username | ``admin`` **(*)**
``MONGODB_PASSWORD`` | The MongoDB password | ``admin`` **(*)**
``MONGODB_DATABASE`` | The MongoDB database | ``rss_tracker_db``
``OAUTH2_ISSUER_URI`` | The OAuth Issuer URI | ``https:\\your-oauth-issuer-uri``

***:** *It is highly recommended to change both default username and password.*

These properties can be found inside the provided ``.env`` file. 
You can also create any additional copies of this file in order to setup different execution environments that point to different databases.

## Running the service

There are several ways to run the service.

- Java Standalone
- Docker Standalone
- Docker Compose

Before running by either of these options, please first setup the needed configuration properties at ``.env`` file.

### Java Standalone

#### Requirements

- JDK 8.
- Maven.
- MongoDB instance running with user and password.

#### Build

Open a terminal, clone the repo and build with maven:

```ssh
git clone https://github.com/Flashky/rss-tracker_v0_feeds.git
cd rss-tracker_v0_feeds
mvn clean package
```

#### Run

Give execution permissions to the following script:

```ssh
chmod +x run_standalone.sh
```

Run:

```ssh
./run_standalone.sh
```

This will execute the service using the configuration defined at the ``.env`` file.

### Docker Standalone

#### Requirements

- Docker.
- MongoDB instance running with user and password.

#### Image pull

Download the latest docker image:

```shell
docker pull flashk/rss-tracker_v0_feeds:latest
```

#### Run

```shell
docker run --name rss-tracker_v0_feeds --env-file .env -dp 8080:8080 flashk/rss-tracker_v0_feeds:latest
```

### Docker Compose

#### Requirements

- Docker.

#### Run

```shell
docker compose up
```

The previous command will setup a Docker container with MongoDB and another Docker container with the service. 
It will use ``.env`` file by default.

You can also customize a different ``.env`` file. 
For example, the following will use an env file named as ``.env.dev``:

```shell
docker compose --env-file .env.dev up 
```


