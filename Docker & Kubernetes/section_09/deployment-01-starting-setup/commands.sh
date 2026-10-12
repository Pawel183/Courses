docker build -t node-dep-example .

docker run -d --rm --name node-dep -p 80:80 node-dep-example 

docker build -t node-example-1 .
docker tag node-example-1:latest pawel183/example-repo
docker push pawel183/example-repo:latest
docker run -d --rm --name node-dep -p 80:80 pawel183/example-repo:latest