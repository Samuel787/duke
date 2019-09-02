@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.txt

REM navigate to java folder to compile
cd ..\src\main\java
javac *.java

REM delete all class files in bin
del ..\..\..\bin\*.class

REM move all class files to bin
move *.class ..\..\..\bin

REM navigate to the bin folder now
cd ..\..\..\bin

REM move the data folder to the bin folder
move ..\data .

java Duke < ..\text-ui-test\input.txt > ..\text-ui-test\ACTUAL.txt
FC ..\text-ui-test\ACTUAL.txt ..\text-ui-test\EXPECTED.txt

REM move the data file back out
move data ..\

REM return back to testing directory
cd ..\text-ui-test
