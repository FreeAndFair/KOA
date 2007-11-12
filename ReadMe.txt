KOA/2 Alpha Release
===================

This is first alpha release of the KOA/2 remote voting system.  This system is intended for use a platform for research 
in electronic voting and applied formal methods.  It is not intended for use in governmental elections.  For further 
information please visit http://kind.ucd.ie/products/opensource/KOA.

License
=======
This distribution is released under the GNU public license.  It does however depend on several third-party JAR files which are listed in
infrastructure/jars, some of which have their own licenses, including the Apache license.

Version
=======
This is version 2.0.0 of KOA.

Release Date
============
August 2007

Building in Eclipse
===================
KOA/2 is configured as an Eclipse project with three builders; one for the infrastructure, one for the Dutch vote
counting system and one for the Irish vote counting system.

Prerequisites
=============
You will need the following tools:
  Ant
  ESCJava/2
  JBoss
  JML
  Make
  MySQL
  Tomcat

Building from the Command Line
==============================

To build the infrastructure:

  cd infrastructure/source/WebVotingSystem
  ant
  
To build the Dutch tally system:

  cd plugins/DutchTallySystem
  make clean build source_docs release
  
To build the Irish tally system:

  cd plugins/votail
  make clean build source_docs distr
