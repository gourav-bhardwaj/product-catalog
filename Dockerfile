FROM openjdk:21-jdk

ENV APP_HOME=/app

WORKDIR ${APP_HOME}

ARG JAR_FILE=product-catalog-*-SNAPSHOT.jar

COPY target/${JAR_FILE} ${APP_HOME}/product-catalog.jar

RUN useradd --uid 98871 appuser && \
    groupadd --uid 98871 --gid appgroup --create-home --home-dir ${APP_HOME} appuser && \
    chown -R appuser:appgroup ${APP_HOME}

USER appuser

EXPOSE 8092

ENTRYPOINT ["java", "-jar", "/app/product-catalog.jar"]


