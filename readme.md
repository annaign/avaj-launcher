# Avaj Launcher

This project is the introduction to the Java world at [42](http://www.42.fr).

## Goals

Frankfurt airport recently discovered that due to frequent weather changes they have a bottleneck on some of the landing tracks. In order to find a solution, they first need to know which scenarios create the worst bottlenecks. So they decided to use a simulator where they configure and analyze multiple scenarios and hope that this will highlight them were the real problem is.

## Build and run the project

```
find * -name "*.java" > source.txt
javac -d bin @source.txt
java -classpath bin Simulation scenario.txt
```