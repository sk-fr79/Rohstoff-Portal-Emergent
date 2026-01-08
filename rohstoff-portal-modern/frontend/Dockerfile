# Frontend Dockerfile
FROM node:20-alpine AS base

WORKDIR /app

# Dependencies installieren
COPY package.json yarn.lock* ./
RUN yarn install --frozen-lockfile

# Source Code kopieren
COPY . .

# Development Stage
FROM base AS development
ENV NODE_ENV=development
EXPOSE 3000
CMD ["yarn", "dev", "--host"]

# Build Stage
FROM base AS build
RUN yarn build

# Production Stage
FROM nginx:alpine AS production
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
