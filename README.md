###Market Processor


##Front end 

![frontend](https://github.com/eldemcan/markettsp/blob/master/img/frontend.png)

##Endpoints

-localhost:8080/api/insert insert messages

-localhost:8080/consumer/poolrecent get last message

-localhost:8080/consumer/poolall gives all messages

-localhost:8080/consumer/"/pool/{country} gives country specific messages


Post tool:Postman

##Approach 

"Don't make war make jar" (pivotal seminar,https://youtu.be/sbPSjI4tt10).

![design](https://github.com/eldemcan/markettsp/blob/master/img/design.png)

Ideally, I should have two separate spring boot application, communicating using spring integration however, I placed all of them into one project for the sake of simplicity. (producer and consumer packages)

There will be two different application server (producer and consumer) both end of the ecosystem. Producer will get post message, validate it and try to send it to RabbitMq, if it can not send it, message will be saved to the mongodb and a scheduling job will try to send failed messages to RabbitMq (scheduling part have not implemented)

Consumer will listen RabbitMq save incoming message and expose data via controllers.

Front end is an angular application which uses RestAngular library to make restful request to the controller.


##Code structure and clarity
-Multilayered software architecture is used in the project.

![multilayer](https://github.com/eldemcan/markettsp/blob/master/img/multilayer.png)

-In addition, applying clean code practices. (i.e. naming conventions, packaging etc.)
-Converter s convert DTO <==> DAO reason is we would like to separete between DTO and DAO. For instance, there are some db related fields which should not be exposed to front end due to security reasons conveters will stripe those information before sending to front end.

##Security
-Input validation using annotations

-Simple authentication configuration (username:user,password:password)

-Seperation between DAO s and DTO s using converters


##Testing 
-Unit tests and Integration tests are created

##Running
-Project is a spring boot application. It can be imported into inteliji idea IDE


##Things to improve
-Implement Websocket instead of pooling for UI

-Implementing Spring integration

-Create a small tool to make random post requests and measure the load


