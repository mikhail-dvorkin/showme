mkdir bin
call javac -d bin -sourcepath src src\showme\framework\ShowMe.java
call jar cvfe showme.jar showme.framework.ShowMe -C bin showme
