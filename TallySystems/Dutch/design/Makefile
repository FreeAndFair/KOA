BASE	= design
BIBTEXOPT = 
BIBWARN = 'LaTeX Warning: Citation'
REFWARN = 'Rerun to get cross-references'
LATEXMAX = 6
FIGSCALE = 0.5

%.ps: %.gif
	giftopnm $< | pnmtops -noturn > $@
%.ps: %.fig
	fig2dev -L ps $< > $@
%.eps: %.fig
	fig2dev -L eps $< > $@
%.pdf: %.fig
	fig2dev -L pdf $< > $@
%.pdf: %.eps
	epstopdf $< > $@
%.pdf_t: %.pstex_t
	sed 's/\.pstex/\.pdf/g' $< > $@
%.pdftex: %.tex
	sed 's/\.pstex_t/\.pdf_t/g' $< > $@
%.pstex: %.fig
	fig2dev -L pstex -m $(FIGSCALE) $< > $@
%.pstex_t: %.fig
	fig2dev -L pstex_t -m $(FIGSCALE) -p `basename $< .fig`.pstex $< > $@
%.ps: %.dvi
	dvips -D600 -Ppdf $< -o $@

%.aux: %.tex
	latex $*

%.dvi: %.tex
	latex $<
	if grep $(BIBWARN) $*.log >/dev/null; \
	then bibtex $(BIBTEXOPT) $*; latex $<; latex $<; fi
	RUNS=$(LATEXMAX); \
	while [ $$RUNS -gt 0 ] ; do \
		if grep $(REFWARN) $*.log > /dev/null; \
		then latex $< ; else break; fi; \
		RUNS=`expr $$RUNS - 1`; \
	done


%.pdf: %.ps
	ps2pdf $<

all: classes

ps:	$(BASE).ps

$(BASE).dvi:	$(BASE).tex\
		$(BASE).bbl

$(BASE).bbl:	$(BASE).aux\
		$(BASE).bib
		bibtex $(BIBTEXOPT) $(BASE)

$(BASE).ps:		$(BASE).dvi

$(BASE).pdf:	$(BASE).ps
		ps2pdf13 $(BASE).ps

pdf:		$(BASE).pdf

ps:		$(BASE).ps

all:		$(BASE).pdf\
		$(BASE).ps
spellcheck:
		aspell --lang=american --master=american -t -c $(BASE).tex
		#aspell --lang=british --master=british -t -c $(BASE).tex

classes:
	mkdir -p build
	javac\
	   -d build\
	   -classpath src\
	   src/sos/koa/*.java

clean:
	rm -f $(BASE).dvi $(BASE).ps $(BASE).pdf
	rm -f *.log *.bbl *.blg *.aux
	rm -f *.pstex *.pstex_t *.pdf *.pdf_t *.pdftex
	rm -f *.bak

