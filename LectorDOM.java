package tareaAvanzadaDOM;


import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class LectorDOM {

	private TreeMap<String,Equipo> equipos;
	
	public LectorDOM() {
		
		equipos = new TreeMap<String,Equipo>();
		
		//ahora vamos a leer el DOM de arriba a abajo sin Xpath
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			//la variable XML alberga el documento q vamos a parsear
			File archivo=new File("C:\\Users\\Alumno\\Desktop\\DAM22-23\\Lenguajes de marca\\XSL\\tareaAvanzadaDOM\\partidos.xml");
			Document xml=builder.parse(archivo);
		
			Element root = xml.getDocumentElement();
			//cogemos todos los hijos q tenga
			NodeList nodosPartido = root.getChildNodes();
			//cada elemento de NodosPartido es un elemento de tipo partido
			//Vamos a recorrerlos
			for (int i = 0; i<nodosPartido.getLength(); i++ ) {
				//sacamos un partido para analizar sus datos__cada uno de los partidos es un NODO
				Node nodoPartido = nodosPartido.item(i);
				//hay q comprobar q el nodo que leemos es de tipo 'Elemento' 
				if( nodoPartido.getNodeType() != Node.ELEMENT_NODE) { //si el nodoPartido no es de tipo 'ELEMENTO' seguimos con el siguiente (podria ser un nodo de tipo texto)
					continue;
				}
				//vamos a sacar los 5 nodos de tipo elemento de PARTIDO(ronda,fecha...)
				NodeList nodosElementosPartido = nodoPartido.getChildNodes();
				//creamos aqui las variables
				String equipoLocal= "",equipoVisitante="";
				int golesLocal=0,golesVisitante=0;
				
				//BUCLE FOR--> Lee/itera LOS 5 ELEMENTOS DE PARTIDO
				for ( int j=0; j<nodosElementosPartido.getLength(); j++) {
					//vamos a sacar el nodo
					Node elementosPartido= nodosElementosPartido.item(j); //elementos de un partido
					
					if( elementosPartido.getNodeName().equals("EquipoLocal")) {//si el nodo se llama EQUIPO LOCAL hacemos una cosa
						equipoLocal = elementosPartido.getTextContent();//SI EL DICCIONARIO DE EQUIPOS NO CONTIENE LA CLAVE DEFINIDA AQUI, getTextContent te devuelve 
						//vamos a analizar Equipo Local__
						if ( !equipos.containsKey(equipoLocal )) { //comprobamos si el diccionario NO contiene al equipo, asi que lo agrega
							equipos.put(equipoLocal, new Equipo()); //añado una entrada en el diccionario con los datos
						}
					//	System.out.println(nombreEquipoLocal); //Ejecutamos para probar
						
					}else if( elementosPartido.getNodeName().equals("EquipoVisitante")) {//Los nodos fecha y ronda por ejemplo no queremos hacer nada con ellos por eso ni los nombramos
						equipoVisitante = elementosPartido.getTextContent();
						if ( !equipos.containsKey(equipoVisitante)) { 
							equipos.put(equipoVisitante, new Equipo()); 
						}
						
					}else if( elementosPartido.getNodeName().equals("Resultado")) {
						String cadena= elementosPartido.getTextContent();
						String cadGolesLocal=cadena.split("-")[0];
						String cadGolesVisitante=cadena.split("-")[1];
						golesLocal=Integer.parseInt(cadGolesLocal);
						golesVisitante=Integer.parseInt(cadGolesVisitante);

					}
				}
				
				/*Fuera del for*/
				//Una vez hemos leido EquipoLocal,EquipoVisitante y Resultado, vamos a hacer los calculos
				//Vamos a extrar del diccionario LOS OBJETOS EquipoLocal y EquipoVisitante para Rellenarlos-ESTAN VACIOS,iniciados a 0-
				Equipo local = equipos.get(equipoLocal); //es el objeto
				Equipo visitante = equipos.get(equipoVisitante);
				
				local.setGolesFavor(local.getGolesFavor() + golesLocal);
				local.setGolesContra(local.getGolesContra() + golesVisitante);
				visitante.setGolesFavor(visitante.getGolesFavor() + golesVisitante);
				visitante.setGolesContra(visitante.getGolesContra() + golesLocal);
				
				if(golesLocal>golesVisitante) {
					//victorio del local
					local.setPartidosGanados(local.getPartidosGanados() + 1);
					visitante.setPartidosPerdidos(visitante.getPartidosPerdidos() + 1);

				}else if( golesLocal == golesVisitante) {
					//empate
					local.setPartidosEmpatados(local.getPartidosEmpatados() + 1);
					visitante.setPartidosEmpatados(visitante.getPartidosEmpatados() + 1);

				}else {
					//caso de victoria del visitante
					local.setPartidosPerdidos(local.getPartidosPerdidos() + 1);
					visitante.setPartidosGanados(visitante.getPartidosGanados() + 1);

				}
				
			}	
			
		}catch(Exception ex) {
			System.out.println(ex.toString());
		}
	}

	//Metodo para coger los equipos
	public TreeMap<String, Equipo> getEquipos() {
		return equipos;
	}
	
	
}
