# Movie-shop

* How to execute the movie-shop

Using maven to build the jar file
```bash
$ docker build -t movie-shop -f Dockerfile-maven .
```
Or
```
$ mvn clean install && docker build -t movie-shop -f Dockerfile-jar .
```
Running the container
```bash
$ docker run -p 8081:8081 --network <yourNetwork> --name movie-shop movie-shop
```

* See the swagger documentation here `http://localhost:8080/swagger-ui/index.html`
