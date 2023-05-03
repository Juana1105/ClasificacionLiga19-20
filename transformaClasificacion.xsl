<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes"/><!--SE ABRE Y SE CIERRA con la / al final-->
<xsl:template match="/">
<html>

    <head>
        <style>

            th{
                background-color: #6F5097;
                color:white;
                padding:0.8em;

            }
            td{
                 padding: 0.5em;
            }
            tr:nth-child(even){
                padding:0.8em;
                text-align:center;
                background-color: #4BB4A0;
                font-family:'Calibri';
            }
            tr:nth-child(odd){
                padding:0.8em;
                text-align:center;
                background-color: #C5FAF6;
                font-family:'Calibri';
            }
            table{
                margin:0 auto;
                text-align:center;
                border-collapse:collapse;
            }
            #titulo{
                text-align:center;
                color: #000000;
                margin-top:25px;
                margin-bottom:25px;
            }

        </style>
    </head>
    <body>

    <h1 id="titulo">Clasificacion liga 19/20</h1>
        <table border="1">
            <thead>
                <th>Nombre</th>
                <th>Puntos</th>
                <th>Partidos Ganados</th>
                <th>Partidos Empatados</th>
                <th>Partidos Perdidos</th>
                <th>Goles Favor</th>
                <th>Goles Contra</th>
            </thead>
            <tbody>
                <xsl:for-each select="clasificacion/equipo">
                <xsl:sort select="puntos" data-type="number" order="descending"></xsl:sort>
                    <tr>
                        <td><xsl:value-of select="nombre"></xsl:value-of></td>
                        <td><xsl:value-of select="puntos"></xsl:value-of></td>
                        <td><xsl:value-of select="partidosGanados"></xsl:value-of></td>
                        <td><xsl:value-of select="partidosEmpatados"></xsl:value-of></td>
                        <td><xsl:value-of select="partidosPerdidos"></xsl:value-of></td>
                        <td><xsl:value-of select="golesFavor"></xsl:value-of></td>
                        <td><xsl:value-of select="golesContra"></xsl:value-of></td>
                    </tr>
                </xsl:for-each>
            </tbody>
        </table>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>