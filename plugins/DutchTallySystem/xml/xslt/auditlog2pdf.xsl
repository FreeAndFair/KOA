<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
  <!-- ========================= -->
  <!-- root element: log      -->
  <!-- ========================= -->
  <xsl:template match="log">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body margin-top="2cm" margin-bottom="1cm"/>
          <fo:region-before extent="2cm"/>
          <fo:region-after extent="1cm"/>
        </fo:simple-page-master>
      </fo:layout-master-set>

      <fo:page-sequence master-reference="simpleA4" initial-page-number="1">
        <fo:static-content flow-name="xsl-region-before">
          <fo:block font-size="10pt" text-align="center"
            border-style="solid" border-color="black" border-width="0.5pt">
            <fo:page-number/>
          </fo:block>
        </fo:static-content>
        <fo:flow flow-name="xsl-region-body">
          <fo:block font-size="14pt"
            font-family="sans-serif"
            line-height="20pt"
            space-after.optimum="15pt"
            background-color="yellow"
            color="black"
            text-align="center"
            padding-top="2pt">
            <xsl:value-of select="'Verwerkingsverslag'"/>
          </fo:block>
          <fo:block>
            <xsl:value-of select="'Verslag opgemaakt op: '"/>
            <xsl:value-of select="@timestamp"/>
          </fo:block>
          <xsl:apply-templates select="importcandidates"/>
          <xsl:apply-templates select="importvotes"/>
          <xsl:apply-templates select="importprivkey"/>
          <xsl:apply-templates select="importpubkey"/>
          <xsl:apply-templates select="keypair"/>
          <xsl:apply-templates select="decrypt"/>
          <xsl:apply-templates select="count"/>
          <xsl:call-template name="line"/>
          <fo:block>
            <xsl:value-of select="'Einde verwerkingsverslag'"/>
          </fo:block>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>

  
  <xsl:template match="importcandidates">
    <xsl:call-template name="line"/>
    <fo:block>
      <xsl:value-of select="'Kandidatenbestand '"/>
      <xsl:if test="@success!='yes'">
        <xsl:value-of select="'niet '"/>
      </xsl:if>
      <xsl:value-of select="'succesvol geimporteerd. '"/>
    </fo:block>
    <xsl:apply-templates select="error"/>
    <fo:block>
      <xsl:value-of select="'Locatie en naam kandidatenbestand: '"/>
      <xsl:value-of select="file/@location"/>
      <xsl:value-of select="' '"/>
      <xsl:value-of select="file/@timestamp"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Referentienummer: '"/>
      <xsl:value-of select="candidatecontents/@refnr"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Aantal aangetroffen lijsten (excl. blanco): '"/>
      <xsl:value-of select="candidatecontents/@nroflists"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Aantal aangetroffen kandidaten (excl. blanco): '"/>
      <xsl:value-of select="candidatecontents/@nrofcandidates"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Aantal blanco kandidaten: '"/>
      <xsl:value-of select="candidatecontents/@nrofblanco"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="importvotes">
    <xsl:call-template name="line"/>
    <fo:block>
      <xsl:value-of select="'Stembusbestand '"/>
      <xsl:if test="@success!='yes'">
        <xsl:value-of select="'niet '"/>
      </xsl:if>
      <xsl:value-of select="'succesvol geimporteerd. '"/>
    </fo:block>
    <xsl:apply-templates select="error"/>
    <fo:block>
      <xsl:value-of select="'Locatie en naam stembusbestand: '"/>
      <xsl:value-of select="file/@location"/>
      <xsl:value-of select="' '"/>
      <xsl:value-of select="file/@timestamp"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Aantal aangetroffen kieskringen: '"/>
      <xsl:value-of select="votecontents/@nrofkieskringen"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Aantal aangetroffen versleutelde stemmen: '"/>
      <xsl:value-of select="votecontents/@nrofvotes"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="importprivkey">
    <xsl:call-template name="line"/>
    <fo:block>
      <xsl:value-of select="'Private sleutel '"/>
      <xsl:if test="@success!='yes'">
        <xsl:value-of select="'niet '"/>
      </xsl:if>
      <xsl:value-of select="'succesvol geimporteerd. '"/>
    </fo:block>
    <xsl:apply-templates select="error"/>
    <fo:block>
      <xsl:value-of select="'Locatie en naam sleutelbestand: '"/>
      <xsl:value-of select="file/@location"/>
      <xsl:value-of select="' '"/>
      <xsl:value-of select="file/@timestamp"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="importpubkey">
    <fo:block>
      <xsl:value-of select="'Publieke sleutel '"/>
      <xsl:if test="@success!='yes'">
        <xsl:value-of select="'niet '"/>
      </xsl:if>
      <xsl:value-of select="'succesvol geimporteerd. '"/>
    </fo:block>
    <xsl:apply-templates select="error"/>
    <fo:block>
      <xsl:value-of select="'Locatie en naam sleutelbestand: '"/>
      <xsl:value-of select="file/@location"/>
      <xsl:value-of select="' '"/>
      <xsl:value-of select="file/@timestamp"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="keypair">
    <fo:block>
      <xsl:value-of select="'De private sleutel en publieke sleutel behoren '"/>
      <xsl:choose>
        <xsl:when test="@success='yes'">
          <xsl:value-of select="'wel '"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="'niet '"/>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:value-of select="'tot hetzelfde sleutelpaar.'"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="decrypt">
    <xsl:call-template name="line"/>
    <fo:block>
      <xsl:value-of select="'Ontsleutelen gestart op '"/>
      <xsl:value-of select="runtime/@start"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Ontsleutelen is '"/>
      <xsl:if test="@success!='yes'">
        <xsl:value-of select="'niet geheel '"/>
      </xsl:if>
      <xsl:value-of select="'succesvol verlopen. '"/>
    </fo:block>
    <xsl:apply-templates select="error"/>
    <fo:block>
      <xsl:value-of select="'Ontsleutelen gestopt op '"/>
      <xsl:value-of select="runtime/@end"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Er zijn '"/>
      <xsl:value-of select="result/@nrofvotes"/>
      <xsl:value-of select="' stemmen ontsleuteld.'"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="count">
    <xsl:call-template name="line"/>
    <fo:block>
      <xsl:value-of select="'Tellen gestart op '"/>
      <xsl:value-of select="runtime/@start"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Tellen is '"/>
      <xsl:if test="@success!='yes'">
        <xsl:value-of select="'niet geheel '"/>
      </xsl:if>
      <xsl:value-of select="'succesvol verlopen. '"/>
    </fo:block>
    <xsl:apply-templates select="error"/>
    <fo:block>
      <xsl:value-of select="'Tellen gestopt op '"/>
      <xsl:value-of select="runtime/@end"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Er zijn '"/>
      <xsl:value-of select="result/@nrofvotes"/>
      <xsl:value-of select="' stemmen geteld.'"/>
    </fo:block>
  </xsl:template>

  <xsl:template match="kieskring">
    <fo:block text-align="center">
      <fo:marker marker-class-name="kieskringmarker">
        <fo:block>
          <xsl:value-of select="'Kieskring: '"/>
          <xsl:value-of select="@nummer"/>
          <xsl:value-of select="' - '"/>
          <xsl:value-of select="@naam"/>
          <xsl:value-of select="' p.'"/>
          <fo:page-number/>
        </fo:block>
      </fo:marker>
    </fo:block>
  </xsl:template>

  <xsl:template name="line">
    <fo:block border-style="solid" border-color="black" border-width="0.5pt" space-after.optimum="10pt" space-before.optimum="5pt"/>
  </xsl:template>

  <xsl:template match="error">
      <fo:block>
        <xsl:value-of select="'Foutmelding: '"/>
        <xsl:apply-templates/>
      </fo:block>
  </xsl:template>
</xsl:stylesheet>
