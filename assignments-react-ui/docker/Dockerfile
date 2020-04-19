FROM node:alpine as build

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package.json .
COPY yarn.lock .
COPY src src

RUN npm install --registry=https://registry.npm.taobao.org
RUN npm install react-scripts -g  --registry=https://registry.npm.taobao.org

COPY . ./
RUN npm run build

FROM nginx:stable-alpine
COPY --from=build /app/build /usr/shar/nginx/html
ENTRYPOINT ["nginx", "-g", "daemon off;"]
