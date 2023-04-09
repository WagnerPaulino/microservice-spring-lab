# Movie-schedule

* How to execute the movie-schedule

Using maven to build the jar file
```bash
$ docker build -t movie-schedule -f Dockerfile-maven .
```
Or
```
$ mvn clean install && docker build -t movie-schedule -f Dockerfile-jar .
```
Running the container
```bash
$ docker run -p 8081:8081 --network <yourNetwork> --name movie-schedule movie-schedule
```

* See the swagger documentation here `http://localhost:8081/swagger-ui/index.html`