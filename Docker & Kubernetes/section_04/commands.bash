### build image
docker build -t favorites-node .

### create network
docker network create favorites-net

### connect mongodb container to network
docker run -d --name mongodb --network favorites-net mongo

### connect node app to network
docker run --name favorites -d --rm -p 3000:3000 --network favorites-net favorites-node