#!/usr/bin/python
import os
import time
import sys

filename = sys.argv[1]

with open(filename+'.txt', 'r') as r:
	for line in r:
		os.system("wget -P /z/apks/"+filename+"/ %s" %line.strip('\n'))
		time.sleep(5)
		
