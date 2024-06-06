mysql:
	docker run --name mysql8 -e MYSQL_ROOT_PASSWORD=secret -d -p 3306:3306 mysql:latest
server:
	mvn -f ./backend spring-boot:run

.PHONY: mysql server