FROM node

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

# VOLUME ["/app/node-nodules"]  # Można też przez polecenie w docer run: -v /app/node-nodules

CMD ["node", "server.js"]
