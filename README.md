<div id="badges" align="right">
  <a href="www.linkedin.com/in/ajay-vasudevan">
      <img src="https://img.shields.io/badge/LinkedIn-blue?logo=linkedin&logoColor=white&style=plastic" width="100" alt="LinkedIn Badge"/>
  </a>
</div>
<h1 align="left">
  <i>hey there</i>
  <img src="https://media.giphy.com/media/w1OBpBd7kJqHrJnJ13/giphy.gif" width="45"/>
</h1>

Spring Data Reactive Relational Database Connectivity or R2DBC allows developers to work with SQL databases in a fully-reactive, asynchronous non-blocking API, thus moving away from "one-thread-execution" servlet container model. With these SQL drivers from respective vendors based on the new R2DBC specification, the drivers thus provide a completely new set of APIs to communicate effectively with Relational databases in a non-blocking manner. More details can be found here and here

This github repository is to document a working code with the required maven dependencies to get a successful 200 GET Http Response code. Lest assured, the code is supposed to work with the below mentioned steps.

This code base relates to Spring Data R2DBC connectivity with a MySQL relational database. 
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

