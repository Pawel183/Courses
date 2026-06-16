#!/bin/bash

set -e

## Remove containers, images and network
echo "1."
docker stop frontend-container backend-container mongo-db || true
docker rm -f frontend-container backend-container mongo-db || true
docker rmi frontend:v1 backend:v1 mongo || true
docker network rm multi-app || true

## Create network
echo "2."
docker network create multi-app

## Create mongo-db Container
echo "3."
docker run --name mongo-db \
    --rm -d -p 27017:27017 \
    --network multi-app \
    -v data2:/data/db \
    -e MONGO_INITDB_ROOT_USERNAME=test \
    -e MONGO_INITDB_ROOT_PASSWORD=secret \
    mongo

## Create backend image and container
echo "4."
cd multi-app/backend

docker build -t backend:v1 .

docker run --name backend-container \
    -d --rm -p 80:80 \
    -v /app/node_modules \
    -v "$(pwd):/app" \
    -v logs:/app/logs \
    --network multi-app \
    backend:v1

## Create frontend image and container
echo "5."
cd ../frontend

docker build -t frontend:v1 .

docker run --name frontend-container \
    -it -d --rm -p 3000:3000 \
    -v /app/node_modules \
    -v "$(pwd)/src:/app/src" \
    --network multi-app \
    frontend:v1

echo "6. End"