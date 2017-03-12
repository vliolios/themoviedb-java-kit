# TheMovieDb Java Kit [![Build Status](https://travis-ci.org/vliolios/themoviedb-java-kit.svg?branch=master)](https://travis-ci.org/vliolios/themoviedb-java-kit) [![codecov](https://codecov.io/gh/vliolios/themoviedb-java-kit/branch/master/graph/badge.svg)](https://codecov.io/gh/vliolios/themoviedb-java-kit)

This is a Java library for version 3 of the [API](https://www.themoviedb.org/documentation/api) provided by [The Movie Database (TMDb)](https://www.themoviedb.org), a community built movie and TV database. 

*****

### Supported endpoints
#####SEARCH
* Search Companies
* Search Collections
* Search Keywords
* Search Movies
* Multi Search
* Search People
* Search TV Shows 

_For more details regarding the endpoints check the [TMDb API Documentation](https://developers.themoviedb.org/3/)._

*****

### Usage
###### Search Companies
```java
Response<CompanyResult> response = API.search("your-api-key").company().query("lucas").page(1)
                                      .build().submit();
```

###### Search Collections
```java
Response<CollectionResult> response = API.search("your-api-key").collection().query("star wars")
                                         .language("en").page(1).build().submit();
```

###### Search Keywords
```java
Response<KeywordResult> response = API.search("your-api-key").keyword().query("zombie").page(1)
                                      .build().submit();
```

###### Search Movies
```java
Response<MovieResult> response = API.search("your-api-key").movie().query("matrix").language("en")
                                    .page(1).includeAdult(false).primaryReleaseYear(2000)
                                    .region("eu").year(2001).build().submit();
```

###### Multi Search
```java
Response<MultiResult> response = API.search("your-api-key").multi().query("cooper").language("en")
                                    .page(1).region("eu").includeAdult(false).build().submit();
```

###### Search People
```java
Response<PeopleResult> response = API.search("your-api-key").people().query("brad").language("en")
                                     .page(1).region("eu").includeAdult(false).build().submit();
```

###### Search TV Shows
```java
Response<TVResult> response = API.search("your-api-key").tv().query("Westworld").language("en")
                                 .page(1).firstAirDateYear(2016).build().submit();
```

_To obtain an API key you have to register an account with TMDb. More details [here](https://www.themoviedb.org/documentation/api)._

*****