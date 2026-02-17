# Dockerfile for the nodejs application
FROM node:20

# set working directory
WORKDIR /app

# copy package.json into the container at /app
COPY package.json /app

# run the command in the container
RUN npm install

# copy the current directory contents into the container at /app
COPY . /app

# expose the port 80
EXPOSE 80

# run the command in the container
CMD ["node", "server.js"]
