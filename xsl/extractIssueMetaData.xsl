<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="no"/>

    <xsl:template match="/issue-xml/issue-meta">
        <issue_number-meta>

            <xsl:element name="issue-type">
                <xsl:value-of select="@issue-type"/>
            </xsl:element>

            <xsl:copy-of select="pub-date/node()"/>
            <xsl:copy-of select="volume"/>
            <xsl:copy-of select="issue"/>
            <xsl:copy-of select="issue-id"/>
        </issue_number-meta>
    </xsl:template>

    <xsl:template match="text()"/>


</xsl:stylesheet>