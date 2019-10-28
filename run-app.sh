#!/usr/bin/env bash

status="$(docker-machine status)"

if [ "$status" == "Stopped" ] ; then
    docker-machine start
fi
./mvnw clean install -DskipTests
cd src/main/docker
docker-compose rm -v
docker-compose down -v
docker-compose up --build
cd ../../..