FROM node

WORKDIR /app

COPY package.json .

RUN npm install

COPY . .

EXPOSE 80

RUN mkdir -p /app/logs

ENV USERNAME=test
ENV PASSWORD=password

CMD ["npm", "start"]