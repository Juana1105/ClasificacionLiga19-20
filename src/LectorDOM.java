import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class LectorDOM {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Partido[] partidos= new Partido[0];
		Equipo[] teams= new Equipo[0];
		Map<String,Equipo> teamsMap= new HashMap<String,Equipo>();
		HashSet<String> equipos= new HashSet<String>();
		ArrayList<String> equiposLista= new ArrayList<String>();
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		File archivo=new File("C:\\Users\\Alumno\\Downloads\\partidos (1).xml");
		Document xml=builder.parse(archivo);
		Element root=xml.getDocumentElement(); 
		NodeList matchList=root.getChildNodes();
		for(int i=0;i<matchList.getLength();i++) {
			Partido partido= new Partido();
			Node match= matchList.item(i);
			NodeList matchElements= match.getChildNodes();
			for(int j=0; j<matchElements.getLength();j++) {
				Node matchElement= matchElements.item(j);
				if(matchElement.getNodeName().equals("EquipoLocal")) {
					partido.setTeam1(matchElement.getTextContent());
					equipos.add(matchElement.getTextContent());
				}
				if(matchElement.getNodeName().equals("Resultado")){
					String[] resultado=matchElement.getTextContent().split("-");
					partido.setGoles1(Integer.parseInt(resultado[0]));
					partido.setGoles2(Integer.parseInt(resultado[1]));
				}
				if(matchElement.getNodeName().equals("EquipoVisitante")) {
					partido.setTeam2(matchElement.getTextContent());
				}
			}
			partidos=Arrays.copyOf(partidos, partidos.length+1);
			partidos[partidos.length-1]=partido;
		}
		equiposLista.addAll(equipos);
		for(int i=0;i<equiposLista.size();i++) {
			Equipo a= new Equipo(equiposLista.get(i));
			teams= Arrays.copyOf(teams, teams.length+1);
			teams[teams.length-1]= a;
		}
		for(int i=0;i<equiposLista.size();i++) {
			for(int j=0; j<partidos.length;j++) {
				if(equiposLista.get(i).equals(partidos[j].getTeam1())) {
					if(partidos[j].getGoles1()>partidos[j].getGoles2()) {
						for(int k=0;k<teams.length;k++) {
							if(teams[k].getNombre().equals(partidos[j].getTeam1())) {
								teams[k].setPartidosGanados(teams[k].getPartidosGanados()+1);
							}
						}
						
					}
					else if(partidos[j].getGoles1()<partidos[j].getGoles2()) {
						for(int k=0;k<teams.length;k++) {
							if(teams[k].getNombre().equals(partidos[j].getTeam1())) {
								teams[k].setPartidosPerdidos(teams[k].getPartidosPerdidos()+1);
							}
						}
				}
					else {
						for(int k=0;k<teams.length;k++) {
							if(teams[k].getNombre().equals(partidos[j].getTeam1())) {
								teams[k].setPartidosEmpatados(teams[k].getPartidosEmpatados()+1);
							}
						}
				}
			}
				if(equiposLista.get(i).equals(partidos[j].getTeam2())) {
					if(partidos[j].getGoles1()<partidos[j].getGoles2()) {
						for(int k=0;k<teams.length;k++) {
							if(teams[k].getNombre().equals(partidos[j].getTeam2())) {
								teams[k].setPartidosGanados(teams[k].getPartidosGanados()+1);
							}
						}
						
					}
					else if(partidos[j].getGoles1()>partidos[j].getGoles2()) {
						for(int k=0;k<teams.length;k++) {
							if(teams[k].getNombre().equals(partidos[j].getTeam2())) {
								teams[k].setPartidosPerdidos(teams[k].getPartidosPerdidos()+1);
							}
						}
				}
					else {
						for(int k=0;k<teams.length;k++) {
							if(teams[k].getNombre().equals(partidos[j].getTeam2())) {
								teams[k].setPartidosEmpatados(teams[k].getPartidosEmpatados()+1);
							}
						}
				}
			}
		}
	}
		for(int i=0;i<equiposLista.size();i++) {
			for(int j=0; j<partidos.length;j++) {
				if(equiposLista.get(i).equals(partidos[j].getTeam1())) {
					for(int k=0;k<teams.length;k++) {
						if(teams[k].getNombre().equals(partidos[j].getTeam1())) {
							teams[k].setGolesFavor(partidos[j].getGoles1()+teams[k].getGolesFavor());
							teams[k].setGolesContra(partidos[j].getGoles2()+teams[k].getGolesContra());
						}
					}
				}
				if(equiposLista.get(i).equals(partidos[j].getTeam2())) {
					for(int k=0;k<teams.length;k++) {
						if(teams[k].getNombre().equals(partidos[j].getTeam2())) {
							teams[k].setGolesFavor(partidos[j].getGoles2()+teams[k].getGolesFavor());
							teams[k].setGolesContra(partidos[j].getGoles1()+teams[k].getGolesContra());
						}
					}
				}
			}
		}
		for(int i=0;i<teams.length;i++) {
			teamsMap.put(teams[i].getNombre(), teams[i]);
		}
		Arrays.sort(teams);
		DocumentBuilderFactory factoryXML=DocumentBuilderFactory.newInstance();
		DocumentBuilder builderXML=factoryXML.newDocumentBuilder();
		Document document=builderXML.newDocument();
		
		Element elementPartidos= document.createElement("clasificacion");
		for(int i=0;i<teamsMap.size();i++) {
			Element elementPuesto= document.createElement("puesto");
			Element elementPartido= document.createElement("equipo");
			Element elementNombre= document.createElement("nombre");
			Element elementPuntos= document.createElement("puntos");
			Element elementPG= document.createElement("partidosGanados");
			Element elementPE= document.createElement("partidosEmpatados");
			Element elementPP= document.createElement("partidosPerdidos");
			Element elementGF= document.createElement("golesFavor");
			Element elementGC= document.createElement("golesContra");
			
			Text textPuesto= document.createTextNode((i+1)+"");
			Text textNombre= document.createTextNode(teamsMap.get(teams[i].getNombre()).getNombre());
			Text textPuntos= document.createTextNode(teamsMap.get(teams[i].getNombre()).calcularPuntos()+"");
			Text textPG= document.createTextNode(teamsMap.get(teams[i].getNombre()).getPartidosGanados()+"");
			Text textPE= document.createTextNode(teamsMap.get(teams[i].getNombre()).getPartidosEmpatados()+"");
			Text textPP= document.createTextNode(teamsMap.get(teams[i].getNombre()).getPartidosPerdidos()+"");
			Text textGF= document.createTextNode(teamsMap.get(teams[i].getNombre()).getGolesFavor()+"");
			Text textGC= document.createTextNode(teamsMap.get(teams[i].getNombre()).getGolesContra()+"");
			
			elementPuesto.appendChild(textPuesto);
			elementNombre.appendChild(textNombre);
			elementPuntos.appendChild(textPuntos);
			elementPG.appendChild(textPG);
			elementPE.appendChild(textPE);
			elementPP.appendChild(textPP);
			elementGF.appendChild(textGF);
			elementGC.appendChild(textGC);
			
			elementPartido.appendChild(elementPuesto);
			elementPartido.appendChild(elementNombre);
			elementPartido.appendChild(elementPuntos);
			elementPartido.appendChild(elementPG);
			elementPartido.appendChild(elementPE);
			elementPartido.appendChild(elementPP);
			elementPartido.appendChild(elementGF);
			elementPartido.appendChild(elementGC);
			
			elementPartidos.appendChild(elementPartido);
		}
		elementPartidos.setAttribute("anyo", "19/20");
		document.appendChild(elementPartidos);
		
		saveXMLtoFile(document,"clasificacionLiga.xml");
		String srcXmlPath = "C:\\Users\\Alumno\\Desktop\\clasificacionLiga.xml";
		String targetHTMLPath = "C:\\Users\\Alumno\\Desktop\\clasificacionLiga.html";
		String xsltPath = "C:\\Users\\Alumno\\Desktop\\transformaLiga.xsl";
		transformXmlByXslt(srcXmlPath,targetHTMLPath,xsltPath);
	}
		public static void saveXMLtoFile(Document document,String fileName) throws IOException,TransformerException {
			
			DOMSource domSource=new DOMSource(document);
			FileWriter writer=new FileWriter("C:\\Users\\Alumno\\Desktop\\"+fileName);
			StreamResult result=new StreamResult(writer);
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			Transformer transformer=transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
			transformer.transform(domSource, result);		
			writer.close();	
}
		

	
	public static void transformXmlByXslt(String srcXmlPath,String targetXmlPath,String xsltPath) {

		// Obtener la fábrica de convertidores
		TransformerFactory tff = TransformerFactory.newInstance();

		try {
		// Obtener las instancias del objeto convertidor s
		Transformer tf = tff.newTransformer(new StreamSource(xsltPath));
		//Convertir
		tf.transform(new StreamSource(srcXmlPath), new StreamResult(
		new FileOutputStream(targetXmlPath)));

		System.out.println ("Conversión exitosa");
		} catch (TransformerConfigurationException e) {
		e.printStackTrace();
		System.out.println ("No se pudo obtener la instancia del objeto de conversión");
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		System.out.println ("No se encontró archivo fuente");
		} catch (TransformerException e) {
		e.printStackTrace();
		System.out.println ("No se pudo convertir al archivo de destino");
		}


		}
}

