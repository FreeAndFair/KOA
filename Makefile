# This makefile builds a release file; it does not build the software
# Building the software is described in ReadMe.txt
# Copyright (c) 2007-2009, Dermot Cochran <http://voting.ucd.ie:8080>

.PHONY: clean release

VERSION=2.0.0
RELEASE_DIR=./koa$(VERSION)

default: build

build: infrastructure plugins

infrastructure:

plugins: votail

votail:
	make -C TallySystems/Irish

clean:
	rm -rf $(RELEASE_DIR)
	rm -rf koa$(VERSION).*

release: clean
	mkdir -p $(RELEASE_DIR)
	cp -R infrastructure $(RELEASE_DIR)/infrastructure
	cp -R plugins $(RELEASE_DIR)/plugins
	cp -R docs $(RELEASE_DIR)/docs
	cp -R papers $(RELEASE_DIR)/papers
	cp -R specs $(RELEASE_DIR)/specs
	cp -R servletspecs $(RELEASE_DIR)/servletspecs
	cp license.txt $(RELEASE_DIR)
	cp ReadMe.txt $(RELEASE_DIR)
	cp ChangeLog.txt $(RELEASE_DIR)
	cp .project $(RELEASE_DIR)
	cp .classpath $(RELEASE_DIR)
	cp -R .externalToolBuilders $(RELEASE_DIR)/.externalToolBuilders
	-find $(RELEASE_DIR) -name ".svn*" -exec rm -rf {} \;
	zip -r koa$(VERSION).zip $(RELEASE_DIR)
	tar cvf koa$(VERSION).tar $(RELEASE_DIR)
	gzip koa$(VERSION).tar 
