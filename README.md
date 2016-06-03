# HTTP Server

[![Build Status](https://travis-ci.org/RabeaGleissner/http-server.svg?branch=master)](https://travis-ci.org/RabeaGleissner/http-server)

This server is following the specifications of 8th Light cob spec.

## Running instructions

Clone this repo, navigate into the folder and run:

`mvn package`

`java -jar target/http-server-1.0-SNAPSHOT.jar`

You can also specify a port and a directory if you don't want to use the default port 5000 and directory PUBLIC_DIR.

`java -jar target/http-server-1.0-SNAPSHOT.jar -p 1234 -d SOME_OTHER_DIR`

You will get feedback in the console to see which port number and directory are used.


## Running the tests

You can run the unit tests with

`mvn test`

You can also run the [8th Light cob spec test suite](https://github.com/8thlight/cob_spec) against it. 

Instructions for running it are in the [repo's README](https://github.com/8thlight/cob_spec).