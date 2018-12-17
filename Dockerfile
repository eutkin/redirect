FROM hirokimatsumoto/alpine-openjdk-11

ENV REDIRECT_HOME /usr/redirect

ENV PORT 80

COPY target/redirect.jar $REDIRECT_HOME/

EXPOSE $PORT

WORKDIR $REDIRECT_HOME

ENTRYPOINT ["sh", "-c"]

CMD ["java -jar $REDIRECT_HOME/redirect.jar --spring.profiles.active=$PROFILE --server.port=$PORT"]