#!/bin/bash
mkdir bin
javac -d bin/ -sourcepath src src/showme/framework/ShowMe.java
jar cvfe showme.jar showme.framework.ShowMe -C bin showme
