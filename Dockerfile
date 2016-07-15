FROM java:8
COPY target/docker /alioraty
CMD cd /alioraty && java $JAVA_OPTS -jar alioraty.jar --spring.config.location=/alioraty/conf/
EXPOSE 8080
