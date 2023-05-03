# db-api

The db api are a software component that encapsulate all the logic for accessing and managing the EPOS metadata catalog.\
Their aim is to simplify the access to the database using java. They are designed to be easily added and removed from a 
component in order to be used with a plug and play approch.

## How to use

There are three main step required to be able to work with the db-api

### 1. Set up maven project

The db-api are available on the maven central repository so you can get the artifact
[here](https://mvnrepository.com/artifact/org.epos-eu.ics-c/db-api/no-versioning-approval-javadoc). 

Within a Maven project it is sufficient to copy to the file *pom.xml*, inside the dependencies tag, the following code snippet:
```xml
<!-- https://mvnrepository.com/artifact/org.epos-eu.ics-c/db-api -->
<dependency>
    <groupId>org.epos-eu.ics-c</groupId>
    <artifactId>db-api</artifactId>
    <version>no-versioning-approval</version>
</dependency>
```
Then you will have to reload the maven project in order to allow it to download the new dependency.

### 2. Environment Variables

In order to work properly the db-api need to have defined the following environment variables:
- `PERSISTENCE_NAME`: Name of the persistance unit that you want to use, usually `EPOSDataModel`.
- `CONNECTION_POOL_MAX_SIZE`: The maximum number of connections that can be opened with the database.
- `CONNECTION_POOL_MIN_SIZE`: The minimum number of connections that must remain open with the database.
- `CONNECTION_POOL_INIT_SIZE`: The number of connections to the database to open as soon as it starts.

There are also the necessary environment variables to be able to connect correctly to the database. You can either pass
the whole connection url directly, or enter the individual parameters for the connection.\
So can be use this:
- `POSTGRESQL_CONNECTION_STRING`

or these:

- `POSTGRESQL_HOST`: URL where the DB is reachable.
- `POSTGRESQL_DBNAME`: Name of the DB, usually `cerif`.
- `POSTGRESQL_USERNAME`: Username of the user used to connect.
- `POSTGRESQL_PASSWORD`: Password of the user used to connect.

### 3. Code
To interact with the database is required to create the specific implementation based on which EPOS Data Model Bean 
is needed.\
Distribution is used for this example. First you create the corresponding object:
```java
    EPOSDataModel<Distribution> distributionDB = new DistributionDBAPI();
```
The created object contains all the methods to be able to interact with the database as regards to all the Distributions.
Let's see some examples to create a new Distribution in the database:
```java
        Distribution distribution = new Distribution();
        distribution.setUid("uid");
        distribution.setTitle(List.of("title1", "title2"));
        distributionDB.save(distribution);
```
The behavior is similar for all other methods described in the EPOSDataModel interface.