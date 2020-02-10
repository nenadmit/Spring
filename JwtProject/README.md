# Jwt Authentication


## Built With

* [Spring-Boot](https://www.oracle.com/java/technologies/java-ee-glance.html) - Framework

## What its all about

Made an app as practice project to learn about JSON Web Tokens.
The app consists of an Authentication controller, which is a class that takes input in form of JSON User object as RequestBody via POST 
method, checks to see if user exist and returns a token.

Login controller accepts GET method and username & password as parameters, then it sends POST request to Authentication controller via 
HttpUrlConnection (its made so it simulates two different servers), if the username/password match, it fetches the token.

