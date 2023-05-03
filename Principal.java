package tareaAvanzadaDOM;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import crearXML.Libro;

public class Principal {
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException {
		LectorDOM lector= new LectorDOM();
		
		TreeMap<String,Equipo> equipos = lector.getEquipos();
		//Para poder tener nombre de equipo
		Set<String> nombresEquipos = equipos.keySet();
		for(String n: nombresEquipos) {
			System.out.println(n); //coge el nombre de equipo
			System.out.println(equipos.get(n)); //coge los valores del objeto
		}
		/*imprime valores sin el nombre del equipo
		Collection<Equipo> valores = equipos.values();
		for(Equipo e: valores) {
			System.out.println(e);
		}
		*/
		generarXMLClasificacion(equipos);
	}
	
	public static void generarXMLClasificacion(TreeMap<String, Equipo> equipos) throws ParserConfigurationException, IOException, TransformerException {
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document xml = builder.newDocument();
		
		
		//Ceamos el nodo root
		Element clasificacion = xml.createElement("clasificacion");
		//AÑADIMOS EL ATRIBUTO
		clasificacion.setAttribute("año", "19/20");
		
		//tenemos que recorrer los equipos e insertar la informacion
		Set<String> nombresEquipos = equipos.keySet();
		
		for(String nombreEquipo:nombresEquipos) {
			
			Equipo equipo= equipos.get(nombreEquipo);//saco el elemento tipo cn todos los datos
			
			//Creamos los elementos-ELEMENT-
			Element elementoEquipo=xml.createElement("equipo");
			Element elementoNombre=xml.createElement("nombre");
			Element elementoPuntos=xml.createElement("puntos");
			Element elementoPartidosGanados=xml.createElement("partidosGanados");
			Element elementoPartidosEmpatados=xml.createElement("partidosEmpatados");
			Element elementoPartidosPerdidos=xml.createElement("partidosPerdidos");
			Element elementoGolesFavor=xml.createElement("golesFavor");
			Element elementoGolesContra=xml.createElement("golesContra");
			
			//sacamos los nodos Texto
			Text textoNombre = xml.createTextNode(nombreEquipo);
			Text textoPuntos = xml.createTextNode(equipo.calcularPuntos()+"");
			Text textoPartidosGanados = xml.createTextNode(equipo.getPartidosGanados()+"");
			Text textoPartidosEmpatados = xml.createTextNode(equipo.getPartidosEmpatados()+"");
			Text textoPartidosPerdidos = xml.createTextNode(equipo.getPartidosPerdidos()+"");
			Text textoGolesFavor = xml.createTextNode(equipo.getGolesFavor()+"");
			Text textoGolesContra = xml.createTextNode(equipo.getGolesContra()+"");
			
			//Ahora juntamos los elementos texto a los elementos
			elementoNombre.appendChild(textoNombre);
			elementoPuntos.appendChild(textoPuntos);
			elementoPartidosGanados.appendChild(textoPartidosGanados);
			elementoPartidosEmpatados.appendChild(textoPartidosEmpatados);
			elementoPartidosPerdidos.appendChild(textoPartidosPerdidos);
			elementoGolesFavor.appendChild(textoGolesFavor);
			elementoGolesContra.appendChild(textoGolesContra);

			//ahora el elemento Equipo le añado los 7 elementos, son sus hijos
			elementoEquipo.appendChild(elementoNombre);
			elementoEquipo.appendChild(elementoPuntos);
			elementoEquipo.appendChild(elementoPartidosGanados);
			elementoEquipo.appendChild(elementoPartidosEmpatados);
			elementoEquipo.appendChild(elementoPartidosPerdidos);
			elementoEquipo.appendChild(elementoGolesFavor);
			elementoEquipo.appendChild(elementoGolesContra);

			
			//Ahora al elemento clasificacion le añadimos el elemento Equipo
			clasificacion.appendChild(elementoEquipo);
		}
			//Una vez metido el elemento equipo al elemento clasifiacion, añadimos clasificacion al documento XML
			xml.appendChild(clasificacion);
			//lamamos
			saveXMLtoFile(xml,"clasificacion1920.xml");
		
		
	}
	
	
	/*TOCHO PARA GUARDARLO COMO XML*/
	public static void saveXMLtoFile(Document document,String fileName) throws IOException,TransformerException {
		
		DOMSource domSource=new DOMSource(document);
		FileWriter writer=new FileWriter("C:\\Users\\Alumno\\Desktop\\DAM22-23\\Lenguajes de marca\\XSL\\tareaAvanzadaDOM\\"+fileName);
		StreamResult result=new StreamResult(writer);
		TransformerFactory transformerFactory=TransformerFactory.newInstance();
		Transformer transformer=transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
		transformer.transform(domSource, result);		
		writer.close();	
	}
	
}
