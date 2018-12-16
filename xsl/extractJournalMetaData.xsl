<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="no"/>

    <xsl:template match="/issue-xml/journal-meta">
        <journal-meta>
            <xsl:copy-of select="journal-id"/>
            <xsl:copy-of select="node()[journal-title]"/>
            <xsl:copy-of select="issn[@pub-type='ppub']"/>
            <xsl:copy-of select="issn[@pub-type='epub']"/>
            <xsl:copy-of select="node()[publisher-name]"/>
        </journal-meta>
    </xsl:template>

    <xsl:template match="text()"/>


</xsl:stylesheet>