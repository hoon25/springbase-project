# spring-base

- version
    - JAVA 17
    - SpringBoot 3.2.3
    - MySQL 8.4.0
- code-style
    - Google Java Style

---

## 개발환경

### URL

- API
    - http://localhost:8080
- Prometheus
    - http://localhost:9090
- Grafana
    - http://localhost:3000

### DB

```
# start
docker volume create mysql-data
docker run --name mysql -e MYSQL_ROOT_PASSWORD=test12 -itd -v mysql-data:/var/lib/mysql -p 3306:3306 mysql:latest
# DDL
어플리케이션 실행 시 Flyway 실행
# DML
resources/db/sql/data.sql 실행
```

### Application

```
./gradlew bootRun
```

### docker

```
./gradlew build
docker build -t springbase:local .
```

### monitoring

start prometheus, grafana

```
docker-compose -f ./infra/docker-compose.yml up -d
```
