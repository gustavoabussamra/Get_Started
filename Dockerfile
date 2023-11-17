FROM ubuntu:18.04 as maven_builder
RUN apt update && apt install -y maven openjdk-11-jdk npm git bash
WORKDIR /app
ADD pom.xml /app/pom.xml
RUN git config --global url."https://".insteadOf git://
ARG TIER
ARG CONTEXT_USE
ARG MOBILE_APP

RUN mvn dependency:go-offline -B
ADD . /app
RUN cd /app && mvn package -X -Dcronapp.profile=${TIER} -Dcronapp.useContext=${CONTEXT_USE}

FROM tomcat:9.0.17-jre11
RUN rm -rf /usr/local/tomcat/webapps/* && groupadd tomcat && useradd -s /bin/false -M -d /usr/local/tomcat -g tomcat tomcat
COPY --from=maven_builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
RUN chown tomcat:tomcat -R /usr/local/tomcat
USER tomcat
