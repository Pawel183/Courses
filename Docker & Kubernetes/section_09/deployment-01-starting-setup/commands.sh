docker build -t node-dep-example .

docker run -d --rm --name node-dep -p 80:80 node-dep-example 


### First Deploy to E2C instance

docker build -t node-example-1 .
docker tag node-example-1:latest pawel183/example-repo
docker push pawel183/example-repo:latest

# Run in E2C instance console
docker run -d --rm --name node-dep -p 80:80 pawel183/example-repo:latest

### Update source code and redeploy
docker build -t node-example-1 .
docker tag node-example-1:latest pawel183/example-repo
docker push pawel183/example-repo:latest

# Run in E2C instance console
docker pull pawel183/example-repo:latest 
docker stop node-dep
docker run -d --rm --name node-dep -p 80:80 pawel183/example-repo:latest

### Deploy changes to ECS cluster
docker build -t node-example-1 .
docker tag node-example-1:latest pawel183/example-repo
docker push pawel183/example-repo:latest