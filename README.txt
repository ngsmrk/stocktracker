1. System requirements

- Maven (built on version 2.2.0)
- Java (built on version 1.6.0_20)

2. Configuring Maven to use a proxy

NOTE: I have assumed that the proxy requires a username + password for basic authentication

- Add the proxy element to your Maven settings file e.g.: ${user.home}/.m2/settings.xml

  <proxies>
   <proxy>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy.somewhere.com</host>
      <port>8080</port>
      <username>proxyuser</username>
      <password>somepassword</password>
    </proxy>
  </proxies>

See http://maven.apache.org/guides/mini/guide-proxies.html for further details

3. To configure the app to use a proxy
Open src/main/webapp/WEB-INF/application.properties
Set the values for the following properties:
proxy.protocol=
proxy.host=
proxy.port=
proxy.username=
proxy.password=

4. To run the app
- Open a command line prompt (I am assuming that Maven is already configured in the path) and go to the root directory of the project
- type "mvn install tomcat:run" and hit return
- open a browser and go to http://localhost:8080/stocktracker/
