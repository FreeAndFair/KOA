jdksrc = /usr/local/Java/src/jdk-1.4/
jmlsrc = /home/sos/kiniry/JML2/specs/:/home/sos/kiniry/JML2/:
escjava2-tc = escjava2 \
	-source 1.4 -typecheck \
	-specs ${jmlsrc}
escjava2 = escjava2 \
	-source 1.4 \
	-specs ${jmlsrc}
jml = /home/sos/kiniry/JML2/bin-for-developers/jml2-unix --source 1.4

export CLASSPATH=".:${HOME}/MJ:${HOME}/JML2:/usr/local/Java/antlr/antlr.jar:/usr/local/Java/junit/junit.jar:/usr/local/Java:${HOME}/JML2/specs:/usr/java/jdk-1.4/lib/tools.jar:/usr/local/Java/jakarta-ant/lib/ant.jar:/usr/local/Java/jakarta-ant/lib/xml-apis.jar"

.PHONY: all \
	check check-security check-crypto \
	typecheck typecheck-security typecheck-crypto \
	jml jml-security jml-crypto

all:	typecheck jml check

check:	check-security check-crypto

check-security:
	${escjava2} \
		`find java/security -name "*.refines-java" -or \
			-name "*.spec" -or \
			-name "*.jml"`

check-crypto:
	${escjava2} \
		`find javax/crypto -name "*.refines-java" -or \
			-name "*.spec" -or \
			-name "*.jml"`

typecheck:	typecheck-security typecheck-crypto

typecheck-security:
	${escjava2-tc} \
		`find java/security -name "*.refines-java" -or \
			-name "*.spec" -or \
			-name "*.jml"`

typecheck-crypto:
	${escjava2-tc} \
		`find javax/crypto -name "*.refines-java" -or \
			-name "*.spec" -or \
			-name "*.jml"`

jml:	jml-security jml-crypto

jml-security:
	${jml} `find java/security -name "*.refines-java" -or \
			-name "*.spec" -or \
			-name "*.jml"`

jml-crypto:
	${jml} `find javax/crypto -name "*.refines-java" -or \
			-name "*.spec" -or \
			-name "*.jml"`
