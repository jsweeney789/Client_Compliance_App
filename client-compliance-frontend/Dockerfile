# BUILD
FROM node:20-alpine AS build

WORKDIR /app

# Install dependencies first (better caching)
COPY package*.json ./
RUN npm ci

# Copy source code
COPY . .

# Build Angular app (production)
RUN npm run build

###############
# RUN
FROM nginx:1.27-alpine

# Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

# Copy built Angular files from build stage
COPY --from=build /app/dist/client-compliance-frontend/browser /usr/share/nginx/html

# Optional: custom nginx config for Angular routing (recommended)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]