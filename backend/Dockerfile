# --- build stage
FROM maven:3.6.3-openjdk-11 AS java-builder

ENV MAVEN_OPTS -XX:+TieredCompilation -XX:TieredStopAtLevel=1

WORKDIR /source

COPY pom.xml ./
COPY ergo-bom/pom.xml ./ergo-bom/
COPY ergo-dev-tool/pom.xml ./ergo-dev-tool/
COPY ergo-core/pom.xml ./ergo-core/
COPY ergo-service/pom.xml ./ergo-service/
RUN mvn --threads 1C --errors --batch-mode dependency:resolve-plugins dependency:go-offline

COPY ergo-dev-tool/src ./ergo-dev-tool/src
COPY ergo-core/src ./ergo-core/src
COPY ergo-service/src ./ergo-service/src
RUN mvn --threads 1C --errors --batch-mode package -DskipTests

# --- package stage
FROM openjdk:11.0.9-jre

LABEL com.sibedge.vendor="sibedge-ergo"
LABEL com.sibedge.maintainer="dev-ergo@sibedge.com"
LABEL com.sibedge.description="ERGO demo backend"

ENV APP_ROOT /opt/ergo-service

RUN groupadd --gid 999 --system ergo \
    && useradd --uid 999 --system --gid ergo ergo \
    && mkdir --parents ${APP_ROOT} \
    && chown --recursive ergo:ergo ${APP_ROOT}

COPY --chown=ergo:ergo distribution/boot-ready.sh /usr/local/bin
COPY --chown=ergo:ergo distribution/launch.sh /usr/local/bin

RUN chmod +x /usr/local/bin/boot-ready.sh /usr/local/bin/launch.sh

WORKDIR ${APP_ROOT}

COPY --chown=ergo:ergo --from=java-builder /source/ergo-service/target/*.jar ./app.jar

EXPOSE 8080/tcp

HEALTHCHECK CMD [ "boot-ready.sh" ]

ENV JAVA_OPTS -server -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintFlagsFinal -XX:+PrintCommandLineFlags -Xss2m -Xmx2G -Xms2G

USER ergo

ENTRYPOINT [ "launch.sh" ]
