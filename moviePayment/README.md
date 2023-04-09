# Movie-payment

* How to execute the movie-payment

Using maven to build the jar file
```bash
$ docker build -t movie-payment -f Dockerfile-maven .
```
Or
```
$ mvn clean install && docker build -t movie-payment -f Dockerfile-jar .
```
Running the container
```bash
$ docker run -p 8082:8082 --network <yourNetwork> --name movie-payment movie-payment
```

* See the swagger documentation here `http://localhost:8082/swagger-ui/index.html`