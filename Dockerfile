## Consulte a documentação do Docker em https://docs.cronapp.io para informações sobre o conteúdo desse arquivo.
 
FROM ubuntu:18.04 as maven_builder
RUN apt update && apt install -y maven openjdk-11-jdk npm git bash
WORKDIR /app
ADD pom.xml /app/pom.xml
RUN git config --global url."https://".insteadOf git://
ARG TIER
ARG CONTEXT_USE
ARG MOBILE_APP
 
## Para repositório EXTERNO não é necessário alterações.
## Para repositório INTERNO comente as linhas 16, 17 e 18 e retire os comentários nas linhas 21, 22, 23 e 24.
 
## Usando repositórios externo - JAR e NPM
RUN mvn dependency:go-offline -B
ADD . /app
RUN cd /app && mvn package -X -Dcronapp.profile=DEV -Dcronapp.useContext=TRUE -Dcronapp.mobileapp=FALSE
 
## Usando repositórios interno - JAR e NPM
#ADD settings.xml  $HOME/.m2/settings.xml
#RUN npm config set registry https://my.registry.com/your-repository/name && mvn -s /app/settings.xml dependency:go-offline -B
#ADD . /app
#RUN cd /app && mvn -s /app/settings.xml package -X -Dcronapp.profile=PRD -Dcronapp.useContext=true -Dcronapp.mobileapp=false
 
FROM tomcat:9.0.17-jre11
RUN rm -rf /usr/local/tomcat/webapps/* && groupadd tomcat && useradd -s /bin/false -M -d /usr/local/tomcat -g tomcat tomcat
COPY --from=maven_builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
RUN chown tomcat:tomcat -R /usr/local/tomcat
USER tomcat
