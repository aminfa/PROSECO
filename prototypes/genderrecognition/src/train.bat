@echo off
cd /d %~dp0
java -cp ".;libs/weka.jar;libs/util.jar;libs/Catalano.Image.jar" GenderPredictor -t contTrainingInstances.serialized
