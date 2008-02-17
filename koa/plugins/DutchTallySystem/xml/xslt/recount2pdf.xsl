<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
  <!-- ========================= -->
  <!-- root element: result      -->
  <!-- ========================= -->
  <xsl:template match="result">
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
            border-style="solid" border-color="grey" border-width="0.5pt">
            <fo:retrieve-marker retrieve-class-name="kieskringmarker">
            </fo:retrieve-marker>
          </fo:block>
        </fo:static-content>
        <fo:flow flow-name="xsl-region-body">
          <xsl:apply-templates select="globaal"/>
          <xsl:apply-templates select="kieskring"/>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>

  
  <xsl:template match="globaal">
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="grey"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Resultaat van de stemming'"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Stembureau: '"/>
      <xsl:value-of select="@stembureau"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Stemperiode: '"/>
      <xsl:value-of select="@periode"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Verkiezing: '"/>
      <xsl:value-of select="@verkiezing"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Starttijd stemming: '"/>
      <xsl:value-of select="@stemming_start"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Eindtijd stemming: '"/>
      <xsl:value-of select="@stemming_eind"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Aantal kiesgerechtigden: '"/>
      <xsl:value-of select="@kiesgerechtigden"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Aantal kiesgerechtigden dat heeft gestemd: '"/>
      <xsl:value-of select="@kiesgerechtigden_gestemd"/>
    </fo:block>
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="yellow"
      color="black"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'Aantal kiesgerechtigden dat niet heeft gestemd: '"/>
      <xsl:value-of select="@kiesgerechtigden_niet_gestemd"/>
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
    <fo:block font-size="16pt"
      font-family="sans-serif"
      line-height="22pt"
      space-before.optimum="15pt"
      space-after.optimum="15pt"
      background-color="orange"
      color="black"
      text-align="left"
      padding-top="3pt">
      <xsl:value-of select="'Kieskring: '"/>
      <xsl:value-of select="@nummer"/>
      <xsl:value-of select="' - '"/>
      <xsl:value-of select="@naam"/>
    </fo:block>
    <xsl:apply-templates select="district"/>
    <xsl:apply-templates select="kieslijst"/>
  </xsl:template>

  <!--
  <xsl:template match="district">
    <fo:block font-size="14pt"
      font-family="sans-serif"
      line-height="20pt"
      space-after.optimum="15pt"
      background-color="green"
      color="white"
      text-align="left"
      padding-top="2pt">
      <xsl:value-of select="'District: '"/>
      <xsl:value-of select="@nummer"/>
      <xsl:value-of select="'. '"/>
      <xsl:value-of select="@naam"/>
    </fo:block>
  </xsl:template>
  -->

  <xsl:template match="kieslijst">
    <fo:block font-size="16pt"
      font-family="sans-serif"
      line-height="22pt"
      space-before.optimum="20pt"
      space-after.optimum="15pt"
      background-color="red"
      color="white"
      text-align="left"
      padding-top="3pt">
      <xsl:value-of select="'Lijstnummer: '"/>
      <xsl:value-of select="@nummer"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Lijstnaam: '"/>
      <xsl:value-of select="@groepering"/>
    </fo:block>
    <fo:block>
      <xsl:value-of select="'Aantal stemmen: '"/>
      <xsl:value-of select="@stemmen"/>
    </fo:block>
    <fo:block font-size="10pt">
      <fo:table table-layout="fixed" width="100%">
        <fo:table-column column-width="1.0cm"/>
        <fo:table-column />
        <fo:table-column column-width="2.0cm"/>
        <fo:table-body>
          <xsl:apply-templates select="positie"/>
        </fo:table-body>
      </fo:table>
    </fo:block>
  </xsl:template>

  <xsl:template match="positie">
    <fo:table-row>
      <fo:table-cell border-width="0.2mm" border-style="solid" text-align="right" padding-right="0.1cm" line-height="16pt" padding-top="2pt">
        <fo:block>
          <xsl:value-of select="@nummer"/>
          <xsl:value-of select="'.'"/>
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-width="0.2mm" border-style="solid" text-align="left" padding-left="0.2cm" line-height="16pt" padding-top="2pt">
        <fo:block>
          <xsl:value-of select="@voorletters"/>
          <xsl:value-of select="' '"/>
          <xsl:value-of select="@achternaam"/>
        </fo:block>
      </fo:table-cell>
      <fo:table-cell border-width="0.2mm" border-style="solid" text-align="right" padding-right="0.1cm" line-height="16pt" padding-top="2pt">
        <fo:block>
          <xsl:value-of select="@stemmen"/>
        </fo:block>
      </fo:table-cell>
    </fo:table-row>
  </xsl:template>
</xsl:stylesheet>
