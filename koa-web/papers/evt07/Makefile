#
# $Id: Makefile 179 2006-04-03 15:19:00 +0000 (Mon, 03 Apr 2006) jkiniry $
#

paper =		paper

talk = 		talk

texfiles =	$(wildcard *.tex)

dvifiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.dvi,$(texfile)))

psfiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.ps,$(texfile)))

pdffiles =	$(foreach texfile,$(texfiles),\
		$(subst .tex,.pdf,$(texfile)))

.PHONY: all talk view archive html

all:	paper.pdf html

talk: 	talk.ps talk.pdf

view:	paper.pdf
	open -a TeXniscope.app paper.pdf

html:	paper/index.html

paper/index.html:	paper.tex
	latex2html -split 0 -show_section_numbers -local_icons paper.tex

# archive: ime.pdf
# 	tar cjf ENTCS-UITP-04.tbz \
# 		entcsmacro.sty snapshot.sty kt.tex \
# 		Makefile \
# 		ime.tex pvsui.bib ime.pdf ime.bbl \
# 		ECB.pdf function_hierarchy.pdf pvs.pdf

paper.pdf: paper.tex paper.aux paper.bbl
	pdflatex paper && pdflatex paper

paper.bbl: paper.bib
	bibtex paper

paper.aux: paper.tex
	pdflatex paper

cleanup:
	rm -f _region_* *.aux *.log *# *~

clean:
	rm -rf auto _region_* \
		*.aux *.log *~ *.dvi ${paper}.ps ${paper}.pdf *.brf *.txt \
		*.bbl *.blg *.toc *.thm *.out *.cb *.dep \
		*.lof *.lot *.gz *.Z TAGS #*

