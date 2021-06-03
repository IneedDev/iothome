#!/bin/bash

exec java -Xlog:gc*:file=/tmp/gc.log::filecount=4,filesize=50M \
  -jar -XX:MetaspaceSize=64m \
  -XX:MaxMetaspaceSize=128m \
  -XX:+UseG1GC \
  -Xmx${JVM_XMX} \
  -Xms${JVM_XMS} \
  -XX:+UseStringDeduplication \
  -Dspring.profiles.active=non-test \
  /opt/iothome.jar
