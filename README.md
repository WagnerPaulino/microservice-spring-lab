# Microservice-spring-lab

Project to manage movie purchases based on microservices architecture using the following technologies

- Spring boot
- Spring cloud netflix eureka server
- Testcontainers
- Rabbitmq
- Postgres database
- Swagger

* How to run postgres container

```bash
docker run -p 5432:5432 --network host -e POSTGRES_USER="admin" -e POSTGRES_PASSWORD="1" -e POSTGRES_DB="test" --name postgres postgres:9.6.23
```

* How to run rabbitmq container

```bash
$ docker build -t rabbit rabbit-image/
$ docker run -p 5672:5672 -p 15672:15672 --name rabbit rabbit
```