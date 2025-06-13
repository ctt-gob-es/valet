echo off

set JDK_LIB=%JAVA_HOME%

@echo off
set CLASSPATH=.\configuration
set CLASSPATH=%CLASSPATH%;.\lib\*

echo PATH: %CLASSPATH%

"%JDK_LIB%"\bin\java -d64 -Xms128m -Xmx2048m -Dlog4j2.configurationFile=file:.\configuration\log4j2.xml -classpath %CLASSPATH% es.gob.valet.statistics.ValetStandaloneStatistics %1