FROM mysql:8
COPY /mysql/*.sql /docker-entrypoint-initdb.d

ENV MYSQL_DATABASE=rentals
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_USER=forent_user
ENV MYSQL_PASSWORD=password
ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes

#ADD mysql.sh /mysql.sh
#CMD ["/mysql.sh"]
#EXPOSE 6033:3306
