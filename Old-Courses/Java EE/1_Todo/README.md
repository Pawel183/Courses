# Build
mvn clean package && docker build -t com.course/1_Todo .

# RUN

docker rm -f 1_Todo || true && docker run -d -p 8080:8080 -p 4848:4848 --name 1_Todo com.course/1_Todo 