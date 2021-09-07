# Microservice-spring-lab

* How to run postgres container

```bash
docker run -p 5432:5432 --network host -e POSTGRES_USER="admin" -e POSTGRES_PASSWORD="1" -e POSTGRES_DB="test" --name postgres postgres:9.6.23
```