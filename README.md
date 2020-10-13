# User management service

This is an application to handle basic operations in regards to users:
- add users
- update users
- delete users
- import users
- retrieve user details.

## Running this application in local 
1.- Compile your project using:
```
mvn clean package
``` 

2.- Run the below command:
```
mvn spring-boot:run
```

3.- Test health check endpoint:
```
http://localhost:8080/service/actuator/health
```


