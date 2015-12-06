#!/usr/bin/python
import os
import time

with open('app.url', 'r') as r:
	for line in r:
		os.system("cd /z/apks/Downloads/app/ && wget --recursive --referer=https://apkpure.com/app?sort=download %s" %line.strip('\n'))
with open('game.url', 'r') as r:
	for line in r:
		os.system("cd /z/apks/Downloads/game/ && wget --recursive --referer=https://apkpure.com/app?sort=download %s" %line.strip('\n'))
		
