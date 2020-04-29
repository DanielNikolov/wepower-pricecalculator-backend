# WePower Energy Price Calculator Demo Project
The project is implemented using Spring Boot 2.2.6 and Java 1.8.
Two RESTful services are exposed:
- /api/wepower/calculateprice?start=<Time in ms>&end=<Time in ms>&customer=<mining|industrial|commercial>&product=<energy|lgc|energy,lgc> -> Performs energy price calculation based on specified criterias

# Prerequisites
Make sure you have Docker installed and it is using Linux containers

1. In the project's root directory run the following command:
"mvn install dockerfile:build" .
It will build the project and will install an image in Docker

2. Run the following command in PowerShell/Command Console:
"docker run -p 8081:8080 -t primenumber/primenumber" . 
It will start the image and you can open it in browser: http://localhost:8081
