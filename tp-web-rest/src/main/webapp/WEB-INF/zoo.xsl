<?xml version="1.0" encoding="ISO-8859-1" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
    xmlns:zoo="urn:org.inria.ns.tp:tp-web-rest:zoo"
    xmlns:doc="http://org.inria.ns.tp/tp-web-rest/doc"
    xmlns:date="urn:org.inria.ns.tp:tp-web-rest:dates"
    exclude-result-prefixes="zoo doc date">

    <xsl:import href="dates.xsl"/>
    <xsl:output method="html" doctype-public=" "/>
    <xsl:param name="liste-par-nom" select="true()" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Zoo</title>
                <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>
                <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-theme.min.css"/>
                <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"/>
                <style>
body {
    padding-top: 60px;
}
h2:first-letter {
    text-transform: uppercase;
}
                </style>
            </head>
            <body>
                <header class="navbar navbar-default navbar-inverse navbar-fixed-top" role="banner">
                    <div class="navbar-header navbar-brand">Bienvenue au Zoo</div>
                </header>
                <div class="container">
                    <div class="well sidebar-nav">
                        <ul class="nav nav-pills nav-stacked">
                          <xsl:choose>
                            <xsl:when test="$liste-par-nom">
                                <xsl:apply-templates mode="toc" select="//zoo:nom">
                                    <xsl:sort select="."/>
                                </xsl:apply-templates>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:apply-templates mode="toc" select="//zoo:nom">
                                    <xsl:sort select="../@date-naissance"/>
                                </xsl:apply-templates>
                            </xsl:otherwise>
                          </xsl:choose>
                        </ul>
                    </div>
                    <xsl:apply-templates/>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="zoo:nom" mode="toc">
        <li><a href="#{generate-id()}"><xsl:value-of select="."/></a></li>
    </xsl:template>

    <xsl:template match="zoo:aquarium/*/*/doc:info | zoo:aquarium/*/*/doc:attention | zoo:aquarium/*/*/doc:danger">
        <tr>
            <td colspan="6"><xsl:apply-templates select="." mode="apply"/></td>
        </tr>
    </xsl:template>
    <xsl:template match="doc:info | doc:attention | doc:danger">
        <xsl:apply-templates select="." mode="apply"/>
    </xsl:template>

    <xsl:template match="doc:info" mode="apply">
        <div class="alert alert-info">
            <p class="lead">
                <span class="glyphicon glyphicon-info-sign"/>
                <xsl:text> </xsl:text>
                <xsl:apply-templates />
            </p>
        </div>
    </xsl:template>

    <xsl:template match="doc:attention" mode="apply">
        <div class="alert alert-warning">
            <p class="lead">
                <span class="glyphicon glyphicon-warning-sign"/>
                <xsl:text> </xsl:text>
                <xsl:apply-templates />
            </p>
        </div>
    </xsl:template>

    <xsl:template match="doc:danger" mode="apply">
        <div class="alert alert-danger">
            <p class="lead">
                <span class="glyphicon glyphicon-cutlery"/>
                <xsl:text> </xsl:text>
                <xsl:apply-templates />
            </p>
        </div>
    </xsl:template>

    <xsl:template match="doc:b"><b><xsl:apply-templates /></b></xsl:template>
    <xsl:template match="doc:i"><i><xsl:apply-templates /></i></xsl:template>
    <xsl:template match="doc:em"><em><xsl:apply-templates /></em></xsl:template>
    <xsl:template match="doc:strong"><strong><xsl:apply-templates /></strong></xsl:template>

    <xsl:template match="zoo:dauphins|zoo:sélaciens">
        <h2><xsl:value-of select="local-name()"/></h2>
        <table class="table table-bordered">
            <tr>
                <th>Nom</th>
                <th>Date naissance</th>
                <th>Type</th>
                <th>Taille</th>
                <th>Poids</th>
                <th>Sexe</th>
                <xsl:if test="*/@photo">
                    <th>Photo</th>
                </xsl:if>
            </tr>
            <xsl:apply-templates/>
        </table>
    </xsl:template>

    <xsl:template match="zoo:dauphin|zoo:requin">
	   <tr>
            <td><a name="{generate-id(zoo:nom)}"><xsl:value-of select="zoo:nom" /></a></td>
            <td>
                <xsl:call-template name="date:format">
                    <xsl:with-param name="date" select="@date-naissance"/>
                </xsl:call-template>
            </td>
            <td><xsl:value-of select="@espèce" /><br /><i><xsl:value-of select="@nom-savant" /></i></td>
            <td><xsl:value-of select="zoo:taille" />&#160;<xsl:value-of select="zoo:taille/@unité" /></td>
            <td><xsl:value-of select="zoo:poids" />&#160;<xsl:value-of select="zoo:poids/@unité" /></td>
            <td><xsl:value-of select="zoo:sexe" /></td>
            <xsl:if test="@photo">
                <td><img src="../img/{@photo}" alt="{zoo:nom}" class="img-circle"/></td>
            </xsl:if>
	   </tr>
    </xsl:template>

</xsl:stylesheet>