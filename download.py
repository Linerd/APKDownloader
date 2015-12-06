#!/usr/bin/python
import os

with open('app.url', 'r') as r:
	for line in r:
		os.system("wget --directory-prefix=%s %s" %('/z/apks/Downloads/app/',line.strip('\n')))
with open('game.url', 'r') as r:
	for line in r:
		os.system("wget --directory-prefix=%s %s" %('/z/apks/Downloads/game/', line.strip('\n')))
		
