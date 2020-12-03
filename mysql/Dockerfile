FROM mysql:latest
COPY /mysql/*.sql /docker-entrypoint-initdb.d

ENV MYSQL_DATABASE=rentals
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_PASSWORD=password
ENV MYSQL_USER=root

#ADD mysql.sh /mysql.sh
#CMD ["/mysql.sh"]
#EXPOSE 3306
