Parental Control Service Test
=============================

This archive is the Solution for the Coding challenge from Sky.

The coding challenge is Parental Control Service. The details of the
challenge can be found in the file Coding-Test-Sky.pdf. This file is in
the root folder of the archive.

What is contained in this archive ?
-----------------------------------

The name of the archive is  pcs-sky.zip. This is a single archive that
contains the coding challenge document, source code and the executable jar.

The structure of the zip is
```
\pcs-sky.zip\rajesh-somasundaram
    .idea
    META-INF
    out
    movieservice
    parentalcontrolservice
    pom.xml
    README.md
```
The name of the root folder in the archive is the full name of the candidate

The .idea file helps if the code is viewed in intelliJ

The META-INF has details required for the executable jar

The out folder contains the executable jar file pcs_jar.jar in
 \pcs-sky\rajesh-somasundaram\out\artifacts\pcs_jar . The jar file
 can be used to execute the program.

There are two modules in the archive - movieservice and parentalcontrolservice.

The movieservice is a module that contains the code of the Movie Meta Data.
 There is a DefaultMovieServiceImpl that a TestClient may use.

The parentalcontrolservice is a module that contains the code for the
 Parental Control Service API and its implementation. A Command line based
 TestClient is provided to execute this program.

The pom.xml contains the maven details

The README.md is this file.

How to execute this program ?
-----------------------------

1. Unzip the pcs-sky.zip in a location. Lets call the location as <PCS_HOME>
2. Open a command prompt and move to this location
<PCS_HOME>\pcs-sky\rajesh-somasundaram\out\artifacts\pcs_jar
3. You will find a pcs.jar in this location. This is the executable jar.
4. Ensure you have JRE 8 available in this location.
5. The usage is java -jar pcs.jar <Movie ID> <user PC level>
  The Accepted values for user PC levels are U, PG, 12, 15, 18
6. For example, the following is a sample execution using the jar file
```
C:\Temp\pcs-sky-master\rajesh-somasundaram\out\artifacts\pcs_jar>java -jar pcs.jar
The usage is java -jar pcs.jar <Movie ID> <user PC level>

C:\Temp\pcs-sky-master\rajesh-somasundaram\out\artifacts\pcs_jar>java -jar pcs.jar 1 U
You can watch the movie 1

C:\Temp\pcs-sky-master\rajesh-somasundaram\out\artifacts\pcs_jar>java -jar pcs.jar 2 U
You cannot watch the movie 2

C:\Temp\pcs-sky-master\rajesh-somasundaram\out\artifacts\pcs_jar>java -jar pcs.jar 16 U
Sorry, we could not find the movie 16 you are looking for.
```

What is the available test data ?
---------------------------------

The developer who would use the Parental Control Service can provide any implementaion of the
Movie Service. However, the TestClient attached in this solution provides a
DefaultMovieServiceImpl, that has a limited sample data.

The sample data available with this TestClient is
```
            Movie ID "1": Parental Control Level  "U";
            Movie ID "2": Parental Control Level  "PG";
            Movie ID "3": Parental Control Level  "12";
            Movie ID "4": Parental Control Level  "15";
            Movie ID "5": Parental Control Level  "18";
            Movie ID "6": Parental Control Level  "U";
            Movie ID "7": Parental Control Level  "18";
```

How the code is organized ?
---------------------------

The Code is a set of simple java programs.

The Project has two modules.

The partnering team that provides Movie Meta Data is assumed to have its
own independent module called Movie Service. This module provides the
 Movie Service and a default test implementation.

The Video-On-Demand's team has its Parental Control code in the module
 ParentalControlService. It is dependent on MovieService to provide results.
 The focus of this project is this module.

The ParentalControlService module has an enum for the supported PG levels
 - U, PG, 12, 15, 18. The enum pattern also provides friendly methods to
 tell if the given level is higher than a particular PG level.

The ParentalControlService API provides a method
```
boolean isMovieAllowedByParentalControlLevel(String movieId, String userLevel)
```
This method returns true if the given movieID and a user level is permitted to be watched.

