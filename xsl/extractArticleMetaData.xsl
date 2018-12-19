<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>


    <xsl:template match="/article/front/article-meta">
        <article-meta>

            <xsl:copy-of select="article-id[@pub-id-type='doi']"/>

            <xsl:copy-of select="title-group/article-title"/>
            <authors>
                <xsl:for-each select="contrib-group/contrib">
                    <xsl:element name="author-name">
                        <xsl:value-of select="string-name/given-names"/><xsl:value-of
                            select="string-name/surname"/>
                    </xsl:element>
                </xsl:for-each>
            </authors>

            <xsl:copy-of select="self-uri"/>

            <xsl:choose>
                <xsl:when test="abstract">
                <xsl:copy-of select="abstract"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:element name="abstract">No abstract</xsl:element>
                </xsl:otherwise>
            </xsl:choose>
        </article-meta>

    </xsl:template>

    <xsl:template match="text()"/>


</xsl:stylesheet>