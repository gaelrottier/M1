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



<div id="count"><xsl:value-of select="count(xsi:entries/xsi:entry)"></xsl:value-of> hôtels trouvés</div>
<xsl:for-each select="xsi:entries/xsi:entry">
      <div id = "hotel"><table>
      <tr><th><xsl:value-of select="xsi:name_fr"/></th> <td><a href="{xsi:website}">Site web</a></td></tr>
			<tr>
			<td  rowspan='1'><img src="{xsi:images/xsi:image}" height="250px" width = "250px"/></td>

			<td><xsl:value-of select="xsi:address/xsi:address_line1"/> - <xsl:value-of select="xsi:address/xsi:zip"/></td>
			<td><xsl:value-of select="xsi:standings_levels/xsi:standings_level"/></td>
			<td><select>
				<xsl:for-each select="xsi:categories/xsi:category">
					<option><xsl:value-of select="."/> </option>
				</xsl:for-each>

			</select></td>
			<td>Prix min : <xsl:value-of select="xsi:tariffs/xsi:tariff/xsi:min"/> €</td>
			<td>Prix max : <xsl:value-of select="xsi:tariffs/xsi:tariff/xsi:max"/> €</td>	
			<td><a href = "DataServlet?value={xsi:ID}">Détails</a></td>
			</tr>

			
		</table>	
		</div>

</xsl:for-each>

</xsl:template>
</xsl:stylesheet>