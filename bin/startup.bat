@echo off
cd /d %~dp0
cd ..
start javaw -Dfile.encoding=utf-8 -jar tank-1.0.0.jar
exit 
