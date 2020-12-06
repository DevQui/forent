FROM jboss/keycloak:11.0.3

COPY /keycloak/realm.json /tmp/realm.json

ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin
ENV KEYCLOAK_FRONTEND_URL=http://localhost:8080/auth
ENV PROXY_ADDRESS_FORWARDING=true
ENV KEYCLOAK_IMPORT /tmp/realm.json

EXPOSE 8080:8080