#!/usr/bin/env bash
wait-for-it.sh db:3306 -t 30
java -Djava.security.egd=file:/dev/./urandom  -jar solvro-0.0.1-SNAPSHOT.jar