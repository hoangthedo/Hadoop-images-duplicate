#!/bin/bash
#mkdir bin
#collectpath and put img to HDFS
#hadoop fs -rm /input/*
#hadoop fs -rmdir /input
hadoop fs -copyFromLocal input /
hadoop fs -ls /input
#hadoop fs -rm /pathinput.txt
hadoop jar AllClass.jar CollectPathFile input pathinput.txt
hadoop fs -copyFromLocal pathinput.txt /
#converx img to seqfile
#hadoop fs -rm /seq_input/*
#hadoop fs -rmdir /seq_input
hadoop jar AllClass.jar ImageFileToHadoopSequenceFile /pathinput.txt /seq_input
#hash seq file and compare to each other
#hadoop fs -rm /output/*
#hadoop fs -rmdir /output
hadoop jar AllClass.jar ImgDriver /seq_input/part-r-00000 /output
#get file output.txt
hadoop fs -cat /output/part-r-00000
hadoop fs -get /output/part-r-00000 output.txt


