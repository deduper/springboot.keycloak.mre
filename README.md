# springboot.keycloak.mre
A minimal reproducible example of a Keycloak-secured Spring Boot Application — Demonstrates how to configure publicly available pages.

## Steps to build and run

1. Download or clone this project
2. *`cd`* to your download/clone location
3. Open *`{%your.path.to%}/springboot.keycloak.mre/src/main/resources/application.properties`* in a text editor
4. Change the value of *`keycloak.auth-server-url`* to the ***IP address***<sup>1</sup> of your Docker host machine (*i.e. the machine on which you're building/running this MRE*)
5. Build the Spring Boot executable by running the following command (*after replacing „*`{%your.path.to%}`*“ appropriately*)…

```bash
./gradlew clean bootJar && \
cp {%your.path.to%}/springboot.keycloak.mre/build/libs/product-app-0.0.0.jar \
{%your.path.to%}/springboot.keycloak.mre/docker/app/
```

6. Compose the application service and the Keycloak service by running the following docker-compose command…

```bash
docker-compose -f {%your.path.to%}/springboot.keycloak.mre/docker/docker-compose.yml build
```
7. A successful docker-compose build will yield output similar to the following…

```bash
Building app
Step 1/3 : FROM mcr.microsoft.com/java/jre:11-zulu-alpine
 ---> 7dfd226f29ee
Step 2/3 : COPY product-app-0.0.0.jar app.jar
 ---> 2cba5d4d1c7d
Step 3/3 : CMD ["java", "-jar", "/app.jar"]
 ---> Running in 43ca6eab6341
Removing intermediate container 43ca6eab6341
 ---> 855ff85fa1e2

Successfully built 855ff85fa1e2
Successfully tagged docker_app:latest
Building keycloak
Step 1/3 : FROM quay.io/keycloak/keycloak:11.0.2
 ---> b0d05a1a3f54
Step 2/3 : COPY springdemo-realm.json /springdemo-realm.json
 ---> Using cache
 ---> 538e184b468f
Step 3/3 : EXPOSE 8080
 ---> Using cache
 ---> 5b0596cb1653

Successfully built 5b0596cb1653
Successfully tagged docker_keycloak:latest
```
8. Launch the application container and the Keycloak contaier by running the following docker-compose command…
```bash
docker-compose -f {%your.path.to%}/springboot.keycloak.mre/docker/docker-compose.yml up -d
```
9. A successful docker-compose launch will yield output similar to the following…
```bash
Creating network "docker_default" with the default driver
Creating docker_keycloak_1 ... done
Creating docker_app_1      ... done
```
The Spring Boot app server should start up within seconds. The Keycloak server may take longer. You can examine the logs to check on their progress. Once both servers are completely up and running…

10. Launch *`http://localhost:8081/index.html`* in a browser. Click on „*View Products*“ link on the „*Login page*“. Enter the user credentials: „*testuser/password*“ when prompted by Keycloak to login. A successful authentication should then redirect you to the *`http://localhost:8081/dashboard.html`* page.

The Keycloak Admin Console can be launched at *`http://localhost:8585/auth`*. Enter the adminstrator credentials: „*admin/admin*“ when prompted by Keycloak to login.

Please report ***any and all*** failures of any of the above steps in this project's [*Issue's*](https://github.com/deduper/springboot.keycloak.mre/issues) section.

<br />
<br />
<br />
<br />

----


<sup><sup><em>1</em></sup></sup>&nbsp;<sup>Because&nbsp;this&nbsp;MRE&nbsp;is&nbsp;composed&nbsp;of&nbsp;two&nbsp;services&nbsp;running&nbsp;in&nbsp;different&nbsp;containers,&nbsp;neither&nbsp;localhost&nbsp;nor&nbsp;127.0.0.1&nbsp;will&nbsp;work&nbsp;in&nbsp;this&nbsp;basic&nbsp;configuration.&nbsp;The&nbsp;value&nbsp;for&nbsp;*`keycloak.auth-server-url`*&nbsp;***MUST***,&nbsp;therefore,&nbsp;be&nbsp;an&nbsp;IP address!</sup>