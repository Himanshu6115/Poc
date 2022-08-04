From java:8
Run mkdir /opt/tomcat
WORKDIR /opt/tomcat
ADD tomcat_URL . 
RUN tar -xvzf tomcar_URL 
RUN mv apache-tomcate-version/* /opt/tomcat 
EXPOSE 8080 
CMD ["/opt/tomcat/bin/cataline.sh"]
ADD target/PocProject.jar PocProject.jar

ENTRYPOINT ["java","-jar","PocProject.jar"]