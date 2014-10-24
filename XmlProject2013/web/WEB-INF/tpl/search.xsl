<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE xsl:stylesheet  [
	<!ENTITY nbsp   "&#160;">
	<!ENTITY copy   "&#169;">
	<!ENTITY reg    "&#174;">
	<!ENTITY trade  "&#8482;">
	<!ENTITY mdash  "&#8212;">
	<!ENTITY ldquo  "&#8220;">
	<!ENTITY rdquo  "&#8221;"> 
	<!ENTITY pound  "&#163;">
	<!ENTITY yen    "&#165;">
	<!ENTITY egrave "&#232;">
	<!ENTITY eacute "&#233;">
	<!ENTITY euro   "&#8364;">
]>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://ref.otcnice.com/webservice/">
<xsl:output method="html" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>
<xsl:template match="/">
		<div id = "recherche">
			<form action="DataServlet" method ="POST">
				Quartier hôtel
				<select name="qHotel" id ="qHotel">
					<xsl:for-each select="xsi:entries/xsi:location">
						<option>
							<xsl:value-of select="."/>
						</option>
					</xsl:for-each>
				</select>
				<br/>Minimum <select name = "sLevel" id = "sLevel">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select> étoiles
				<input type="hidden" name = "hotel" value="search"/>
				<br/><input type="submit" value = "rechercher"/>
			</form>
			<form method = "POST" action = "DataServlet">
				<input type='hidden' name='hotel' value = 'all'/>
				<input type ='submit' value="Tous les hôtels" />	
			</form>
			<input type="button" value="Afficher les hôtels sur la carte" onclick="showMap()"/>
		</div>
	</xsl:template>
</xsl:stylesheet>