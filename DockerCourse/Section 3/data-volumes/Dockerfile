FROM node

ARG DEFAULT_PORT=80

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

ENV PORT $DEFAULT_PORT 

EXPOSE $PORT

# VOLUME ["/app/node-nodules"]  # Można też przez polecenie w docer run: -v /app/node-nodules

CMD ["npm", "start"]
