#!/bin/bash

# Environment variables
source .env

# Variables
JAR_PATH=target
JAR=rss-tracker-v0-feeds-0.0.1-SNAPSHOT.jar

# Service execution
java -jar "${JAR_PATH}/${JAR}"