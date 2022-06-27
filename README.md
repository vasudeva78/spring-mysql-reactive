# Features implemented

* Connect to MySql in reactive mode from Spring boot
* Handle exceptions
* Request bean validation
* Test classes for above 85 percentile coverage
* Dockerize the app

1. #### Set up MySql database

* Run `docker run --name my-sql-db -e MYSQL_ROOT_PASSWORD=mysqlpassword -p 3306:3306 -d mysql:8.0.29-debian`. More
  details
  can be
  found [here](https://hub.docker.com/_/mysql)
* Create payroll schema once connected to docker mysql container

2. #### Set up Gitlab for container registry

* Create new project
* Get the container registry image url

3. #### Maven commands

* Build project : `mvn clean package -T 8`
* Build & push docker image : `mvn clean package jib:build`

4. **Run the container image pulled from Gitlab**

* docker login registry.gitlab.com -u _your-gitlab-username_ -p _your-gitlab-password_
* 

