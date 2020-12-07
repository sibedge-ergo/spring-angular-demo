# --- build stage
FROM node:12.16.3 AS builder

COPY . /usr/src/app
WORKDIR /usr/src/app

RUN npm install && npm run build

# --- package stage
FROM nginx:1.17-alpine

COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /usr/src/app/dist/ergo/ /usr/share/nginx/html


CMD exec nginx -g "daemon off;"
