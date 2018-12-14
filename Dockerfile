FROM hirokimatsumoto/alpine-openjdk-11

ENV REDIRECT_HOME /usr/redirect

COPY target/redirect.jar $REDIRECT_HOME/

WORKDIR $REDIRECT_HOME

ENTRYPOINT ["sh", "-c"]

CMD ["java -jar $REDIRECT_HOME/redirect.jar --spring.profiles.active=$PROFILE"]