mysql:
	docker run --name mysql8 -e MYSQL_ROOT_PASSWORD=secret -d -p 3306:3306 mysql:latest
server:
	mvn -f ./backend spring-boot:run
test:
	mvn -f ./backend test -Dtest=*MockTest

.PHONY: mysql server