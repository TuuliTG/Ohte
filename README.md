# Mastermind

This project is a part of a course *Ohjelmistotekniikka* in the University of Helsinki. The software is a classic Master Mind game. 

### Documents

* [Requirements analysis](https://github.com/TuuliTG/Ohte/blob/main/Documents/RequirementsAnalysis.md)
* [Work hours](https://github.com/TuuliTG/Ohte/blob/main/Documents/workhours.md)
* [Architecture](https://github.com/TuuliTG/Ohte/blob/main/Documents/Architecture.md)
* [Manual](https://github.com/TuuliTG/Ohte/blob/main/Documents/manual.md)
* [Testing](https://github.com/TuuliTG/Ohte/blob/main/Documents/Testing.md)

### Releases

[Final release](https://github.com/TuuliTG/Ohte/releases/tag/v.2.0)

[Week 6 release](https://github.com/TuuliTG/Ohte/releases/tag/v1.1)

### Command Line commands

#### run tests:

`mvn test`

#### JaCoCo code coverage reports

`mvn jacoco:report`

you can view the report by opening the file `target/site/jacoco/index.html` on your browser. 

#### Generate excecutable jar
Command for creating jar: 
`mvn package`

Jar file will be created in target folder. Jar file will be named  Mastermind-1.0-SNAPSHOT.jar

You can run the jar file on the command line by `java -jar Mastermind.10-SNAPSHOT.jar`

#### Checkstyle
Command for getting the checkstyle report:
`mvn jxr:jxr checkstyle:checkstyle`

The report is found in target/site/checkstyle.html

#### Javadoc
Generate Javadoc:
`mvn javadoc:javadoc`



