#!/bin/bash
mkdir bin
find src -name '*.java' > sources.list
javac -d bin/ -sourcepath src @sources.list
jar cvfe showme.jar showme.framework.ShowMe -C bin showme
