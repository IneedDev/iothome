FROM amazoncorretto:8u302-alpine

ENV TZ=Europe/Warsaw
ENV JVM_XMX="1G"
ENV JVM_XMS="248M"

ADD temp/iothome.jar /opt/iothome.jar

ADD docker-entrypoint.sh /docker-entrypoint.sh
RUN ["chmod", "+x", "docker-entrypoint.sh"]

ENTRYPOINT ["/docker-entrypoint.sh"]