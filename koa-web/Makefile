# This makefile builds a release file; it does not build the software
# Building the software is described in ReadMe.txt
# Copyright (c) 2007, Systems Research Group, University College Dublin

.PHONY: clean release

VERSION=2.0.0
RELEASE_DIR=./koa$(VERSION)

default: build

build: infrastructure plugins

infrastructure:

plugins: votail

votail:
	make -C plugins/votail

clean:
	rm -rf $(RELEASE_DIR)
	rm -rf koa$(VERSION).*

release: clean
	mkdir -p $(RELEASE_DIR)
	svn export infrastructure $(RELEASE_DIR)/infrastructure
	svn export plugins $(RELEASE_DIR)/plugins
	svn export docs $(RELEASE_DIR)/docs
	svn export papers $(RELEASE_DIR)/papers
	svn export specs $(RELEASE_DIR)/specs
	svn export servletspecs $(RELEASE_DIR)/servletspecs
	cp license.txt $(RELEASE_DIR)
	cp ReadMe.txt $(RELEASE_DIR)
	cp ChangeLog.txt $(RELEASE_DIR)
	cp .project $(RELEASE_DIR)
	cp .classpath $(RELEASE_DIR)
	mkdir $(RELEASE_DIR)/.externalToolBuilders
	zip -r koa$(VERSION).zip $(RELEASE_DIR)
	tar cvf koa$(VERSION).tar $(RELEASE_DIR)
	gzip koa$(VERSION).tar 
