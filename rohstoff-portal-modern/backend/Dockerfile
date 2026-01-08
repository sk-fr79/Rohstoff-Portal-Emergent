# Rohstoff Portal Backend - Dockerfile
FROM node:20-alpine AS base

# Installiere notwendige Pakete für native Module (argon2)
RUN apk add --no-cache python3 make g++ libc6-compat

WORKDIR /app

# Kopiere package files
COPY package.json yarn.lock* ./

# Installiere Dependencies
RUN yarn install --frozen-lockfile

# Kopiere Prisma Schema
COPY prisma ./prisma/

# Generiere Prisma Client
RUN yarn db:generate

# Kopiere Source Code
COPY . .

# Build TypeScript
RUN yarn build

# Production Stage
FROM node:20-alpine AS production

WORKDIR /app

# Kopiere nur notwendige Dateien
COPY --from=base /app/dist ./dist
COPY --from=base /app/node_modules ./node_modules
COPY --from=base /app/package.json ./
COPY --from=base /app/prisma ./prisma

# Setze Environment
ENV NODE_ENV=production
ENV PORT=8001

# Exponiere Port
EXPOSE 8001

# Healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8001/api/health || exit 1

# Starte Application
CMD ["node", "dist/app.js"]
