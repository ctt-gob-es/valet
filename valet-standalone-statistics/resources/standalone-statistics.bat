echo off

set JDK_LIB=%JAVA_HOME%

@echo off
set CLASSPATH=.\configuration
set CLASSPATH=%CLASSPATH%;.\lib\*

echo PATH: %CLASSPATH%

"%JDK_LIB%"\bin\java -d64 -Xms128m -Xmx2048m -classpath %CLASSPATH% es.gob.valet.statistics.ValetStandaloneStatistics %1