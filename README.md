# HTTP Server

[![Build Status](https://travis-ci.org/RabeaGleissner/http-server.svg?branch=master)](https://travis-ci.org/RabeaGleissner/http-server)

This server is following the specifications of the [8th Light cob spec test suite](https://github.com/8thlight/cob_spec).

## Running instructions

Clone this repo, navigate into the folder and run:

`mvn package`

`java -jar target/http-server-1.0-SNAPSHOT.jar`

You can also specify a port and a directory if you don't want to use the default port 5000 and directory PUBLIC_DIR.

`java -jar target/http-server-1.0-SNAPSHOT.jar -p 1234 -d SOME_OTHER_DIR`

You will get feedback in the console to see which port number and directory are used.


## Running the unit tests

You can run the unit tests with

`mvn test`

## Running the cob spec test suite

Navigate into the folder `vendor/cob_spec`.

Then type

`mvn package`

`java -jar fitnesse.jar -p 9090`

The test suite will run on [http://localhost:9090/HttpTestSuite](http://localhost:9090/HttpTestSuite).

