# Maven build container
  
FROM maven:3.6.3-openjdk-11-slim AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM eclipse-temurin:11.0.18_10-jre-jammy

COPY hooks /tmp/hooks/
COPY wait-for /tmp/

#RUN apk --no-cache --update add git

# resolving CVE-2019-14697
#RUN apk upgrade musl

#maintainer
MAINTAINER ttran@pingidentity.com

EXPOSE 6879

CMD cd /tmp/ && ./hooks/start-service.sh

COPY --from=maven_build /tmp/target/cdr-au-mock-dh-apis-*.jar /tmp/app.war
