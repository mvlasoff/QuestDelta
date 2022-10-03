FROM tomcat

WORKDIR /usr/local/tomcat/webapps/

# COPY ./target/quest-delta-1.0-SNAPSHOT.war .

ENV PORT=8080

EXPOSE $PORT

CMD ["catalina.sh", "run"]



