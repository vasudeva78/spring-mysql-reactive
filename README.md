<div id="badges" align="right">
  <a href="www.linkedin.com/in/ajay-vasudevan">
      <img src="https://img.shields.io/badge/LinkedIn-blue?logo=linkedin&logoColor=white&style=plastic" width="100" alt="LinkedIn Badge"/>
  </a>
</div>
<h1 align="left">
  <i>hey there</i>
  <img src="https://media.giphy.com/media/w1OBpBd7kJqHrJnJ13/giphy.gif" width="45"/>
</h1>

Spring Data _Reactive Relational Database Connectivity_ or R2DBC allows developers to work with SQL databases in a
fully-reactive and asynchronous manner, thus moving away from the "**one-thread-execution**" servlet container model.
With the SQL drivers based on the new R2DBC specification from respective vendors, the drivers thus provide a
completely new set of APIs to communicate effectively with Relational databases in a non-blocking manner. More details
can be found [here](https://spring.io/projects/spring-data-r2dbc#overview) and [here](https://r2dbc.io).

This github repository is to document a working code with the **minimum** required maven dependencies to get a
successful 200 GET
HTTP Response code. Lest assured, the code is supposed to work with the below mentioned steps.

This code base is based on Spring Data R2DBC with MySQL relational database.

# Features implemented

* Connect to MySQL in asynchronous mode from Spring boot
* Validate request and throw custom validation exception
* Handle any exceptions and throws meaningful error messages
* Maintain test classes execution coverage of 85 percentile and above
* Dockerize the app with docker engine installed during image build

1. #### Set up MySQL database

* Run `docker run --name my-sql-db -e MYSQL_ROOT_PASSWORD=mysqlpassword -p 3306:3306 -d mysql:8.0.29-debian`.
* Connect to database using MySQL Workbench(u: root | p: mysqlpassword) -- or -- run mysql command inside the running
  docker container
* Create **payroll** schema once connected to docker mysql container
* Use **payroll** schema
* Create **customer** table.`CREATE TABLE IF NOT EXISTS customers (
  cust_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );`

2. #### Set up Gitlab for registering the docker image

* Create new project. `E.g.spring-mysql-reactive`

3. #### Maven commands

* Run unit tests & build project : `mvn clean package -T 8`
* Build & push docker image to
  GitLab : `mvn clean install -T 4 -Dgitlab-image=<<your-image-name>> -Dgitlab-username=<<my-gitlab-username>> -Dgitlab-password=<<my-gitlab-password>>`
  _your-image-name would be_ **registry.gitlab.com/<<my-gitlab-username>>/spring-mysql-reactive/reactive-mysql:1.0.1**


4. #### Run the container image pulled from Gitlab

* `docker login registry.gitlab.com -u <<my-gitlab-username>> -p <<my-gitlab-password>>`
* `docker-compose up`

