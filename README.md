# Features implemented

* Connect to MySql in reactive mode from Spring boot
* Handle exceptions
* Test classes for above 85 percentile coverage

#### Set up MySql database

* Run `docker run --name my-sql-db -e MYSQL_ROOT_PASSWORD=mysqlpassword -p 3306:3306 -d mysql:8.0.29-debian`. More
  details
  can be
  found [here](https://hub.docker.com/_/mysql)
* Create payroll schema once connected to docker mysql container

#### Maven commands

* Build project : `mvn clean package -T 8`
* Build docker image : `mvn clean compile jib:build`

