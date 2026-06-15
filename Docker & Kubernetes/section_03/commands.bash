### 3 volumes (name, anonymous, bind mount)
docker run -d -p 3000:80 --rm --name feedback-app \
    -v feedback:/app/feedback \
    -v /app/node_modules \
    -v "$(pwd):/app" \
    feedback-app:volumes

### Read only
docker run -d -p 3000:80 --rm --name feedback-app \
    -v feedback:/app/feedback \
    -v "$(pwd):/app:ro" \
    -v /app/node_modules \
    -v /app/temp \
    feedback-app:env

### Env
docker run -d -p 4000:8000 --rm --name feedback-app \
    --env PORT=8000 \
    -e OTHER_ENV="other" \
    -v feedback:/app/feedback \
    -v "$(pwd):/app" \
    -v /app/node_modules \
    -v /app/temp \
    feedback-app:env

### Env file
docker run -d -p 5000:8888 --rm --name feedback-app \
    --env-file ./.env \
    feedback-app:env

### Build with different arg value
docker build -t feedback-app:dev --build-arg DEFAULT_PORT=88 .