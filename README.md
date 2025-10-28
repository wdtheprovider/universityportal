To build and run follow the following commands. make sure you update the parts below to work in your laptop,"wd" is my user directory and the tomcat is located in my Documents Folder (Make sure you update this too)

mvn clean package  

cp target/UniversityPortal.war /Users/wd/Documents/apache-tomcat-10.1.48/webapps/ 

rm -rf /Users/wd/Documents/apache-tomcat-10.1.48/webapps/UniversityPortal    

