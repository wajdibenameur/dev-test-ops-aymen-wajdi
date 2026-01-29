# Build Angular
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --configuration=production

# Serve with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/frontend-angular /usr/share/nginx/html
EXPOSE 80
