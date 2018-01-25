# Usage

run application with
```
mvn tomcat7:run
```

* connect to `http://localhost:8080`
* log-in with admin/admin
* go to `http://localhost:8080/actors`
* The first loading should be slow, 
* Immediated reloading should be fast.
* The console shows the following:
```
2018-01-25 16:19:14.907 [http-bio-8080-exec-8] DEBUG o.h.d.m.actors.ActorsController - Entering getActors()
2018-01-25 16:19:14.911 [http-bio-8080-exec-8] DEBUG o.h.d.movies.actors.ActorService - Nothing found in Cache
2018-01-25 16:19:25.694 [http-bio-8080-exec-7] DEBUG o.h.d.m.actors.ActorsController - Entering getActors()
2018-01-25 16:19:25.696 [http-bio-8080-exec-7] DEBUG o.h.d.movies.actors.ActorService - Cache hit
```