#!/bin/sh
#
JDK_LIB=$JAVA_HOME

oldCP=$CLASSPATH

lib1=./lib

#unset CLASSPATH
CLASSPATH=""
for i in $lib1/* ; do
  if [ "x${CLASSPATH}" != "x" ]; then
    CLASSPATH=${CLASSPATH}:$i
  else
   CLASSPATH=.:./configuration:$i
  fi
done

if [ "x${oldCP}" != "x" ]; then
    CLASSPATH=${CLASSPATH}:${oldCP}
fi

echo Usando path ${CLASSPATH}

$JDK_LIB/bin/java.exe -d64 -Xms128m -Xmx2048m -classpath ${CLASSPATH} es.gob.valet.statistics.ValetStandaloneStatistics %1