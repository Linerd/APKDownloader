#!/usr/bin/python
import os
import time

with open('app.url', 'r') as r:
	for line in r:
		os.system("wget %s" %line.strip('\n'))
		time.sleep(5)

with open('game.url', 'r') as r:
	for line in r:
		os.system("wget %s" %line.strip('\n'))
		time.sleep(5)
		
