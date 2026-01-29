# =========================
# Stage 1: Build Angular
# =========================
FROM node:20 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers de dépendances
COPY package*.json ./

# Installer les dépendances
RUN npm install

# Copier le reste du projet
COPY . .

# Build production Angular
RUN npm run build -- --configuration=production

# =========================
# Stage 2: Serve with Nginx
# =========================
FROM nginx:alpine

# Copier les fichiers build Angular vers le répertoire Nginx
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Exposer le port
EXPOSE 80

# Démarrer Nginx
CMD ["nginx", "-g", "daemon off;"]
