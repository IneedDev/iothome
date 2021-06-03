FROM docker.easypack24.net:5000/inpost/corretto-jdk11:11.0.3.7.1.GA

ENV TZ=Europe/Warsaw
ENV JVM_XMX="1G"
ENV JVM_XMS="248M"

ADD temp/iothome.jar /opt/iothome.jar

ADD docker-entrypoint.sh /docker-entrypoint.sh
RUN ["chmod", "+x", "docker-entrypoint.sh"]

ENTRYPOINT ["/docker-entrypoint.sh"]