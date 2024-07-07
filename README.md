# spring-base
- JAVA 17
- SpringBoot 3.2.3
- MySQL 8.4.0

---
## DEV
### DB
```
# start
docker run --name mysql -e MYSQL_ROOT_PASSWORD=test12 -d -p 3306:3306 mysql:latest
# DDL
어플리케이션 실행 시 Flyway 실행
# DML
resources/sql/data.sql 실행
```

### Application
```
# start
./gradlew bootRun
```
### docker
```
./gradlew build
docker build -t springbase:local .
```
### docker-compose
start with prometheus, grafana
```
docker-compose -f ./infra/docker-compose.yml up -d
```




## Code-Style
Google Java Style

