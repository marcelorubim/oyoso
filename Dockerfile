FROM payara/micro

COPY /target/runsol-api-webapp.war $DEPLOY_DIR
COPY /libs/mysql-connector-java-8.0.11.jar /opt/payara/libs/

EXPOSE 8181