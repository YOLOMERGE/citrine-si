Citrine SI Unit Converter
-------------------------
Java 9 project using jetty, RestEasy, and Antlr

To Build:

    mvn clean verify

To Run:

    java -jar citrine-server/target/citrine-server-1.0-SNAPSHOT-shaded.jar

Maven project with two modules:

*citrine-lib*
* uses Antlr to lex/parse the input strings into a meaningful structure.
  * Unit.g4 for the grammar definition
  * SIUnitVisitor for the logic used to parse the input strings into model objects
* SIUnit is a simple model object with a toJson method - gson and jackson felt like overkill.
* Constants and Functions are self explanatory
* Parser and functions have unit tests

*citrine-server*
* Simple RestEasy server with embedded jetty serving requests on :8080/units/si 
* imports citrine-lib for the business logic