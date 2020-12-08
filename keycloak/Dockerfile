FROM jboss/keycloak:11.0.3

COPY /keycloak/forent_realm.json forent_realm.json

ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin
ENV KEYCLOAK_FRONTEND_URL=http://localhost:8080/auth
ENV PROXY_ADDRESS_FORWARDING=true
ENV KEYCLOAK_IMPORT forent_realm.json
