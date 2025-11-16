@echo off
cmd /k "mvn clean package && echo Copiando archivos a Tomcat... && xcopy /Y /E .\target\notasApp "C:\Program Files\Apache Tomcat\webapps\notasApp" && copy /Y .\target\notasApp.war "C:\Program Files\Apache Tomcat\webapps\" && echo Listo"
pause

