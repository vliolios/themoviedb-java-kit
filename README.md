# TheMovieDb Java Kit [![Build Status](https://travis-ci.org/vliolios/themoviedb-java-kit.svg?branch=master)](https://travis-ci.org/vliolios/themoviedb-java-kit) [![codecov](https://codecov.io/gh/vliolios/themoviedb-java-kit/branch/master/graph/badge.svg)](https://codecov.io/gh/vliolios/themoviedb-java-kit) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.vliolios/themoviedb-java-kit/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.vliolios/themoviedb-java-kit)

This is a Java library for version 3 of the [API](https://www.themoviedb.org/documentation/api) provided by [The Movie Database (TMDb)](https://www.themoviedb.org), a community built movie and TV database. 

*****

### Supported endpoints
##### SEARCH
* Search Companies
* Search Collections
* Search Keywords
* Search Movies
* Multi Search
* Search People
* Search TV Shows 

_For more details regarding the endpoints check the [TMDb API Documentation](https://developers.themoviedb.org/3/)._

*****

### Getting started

You can find the latest themoviedb-java-kit release [here](https://github.com/vliolios/themoviedb-java-kit/releases) or you can add it as a dependency as follows:
##### Maven
```xml
<dependency>
    <groupId>com.vliolios</groupId>
    <artifactId>themoviedb-java-kit</artifactId>
    <version>0.1.0</version>
</dependency>
```

##### Gradle
```
compile 'com.vliolios:themoviedb-java-kit:0.1.0'
```

*****

### Usage
###### Search Companies
```java
Response<CompanyResult> response = new API("your-api-key").search().company().query("lucas").page(1)
                                      .build().submit();
```

###### Search Collections
```java
Response<CollectionResult> response = new API("your-api-key").search().collection().query("star wars")
                                         .language("en").page(1).build().submit();
```

###### Search Keywords
```java
Response<KeywordResult> response = new API("your-api-key").search().keyword().query("zombie").page(1)
                                      .build().submit();
```

###### Search Movies
```java
Response<MovieResult> response = new API("your-api-key").search().movie().query("matrix").language("en")
                                    .page(1).includeAdult(false).primaryReleaseYear(2000)
                                    .region("GB").year(2001).build().submit();
```

###### Multi Search
```java
Response<MultiResult> response = new API("your-api-key").search().multi().query("cooper").language("en")
                                    .page(1).region("GB").includeAdult(false).build().submit();
```

###### Search People
```java
Response<PeopleResult> response = new API("your-api-key").search().people().query("brad").language("en")
                                     .page(1).region("GB").includeAdult(false).build().submit();
```

###### Search TV Shows
```java
Response<TVResult> response = new API("your-api-key").search().tv().query("Westworld").language("en")
                                 .page(1).firstAirDateYear(2016).build().submit();
```

_To obtain an API key you have to register an account with TMDb. More details [here](https://www.themoviedb.org/documentation/api)._

*****