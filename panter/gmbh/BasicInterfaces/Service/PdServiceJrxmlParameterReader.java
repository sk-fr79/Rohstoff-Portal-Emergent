package panter.gmbh.BasicInterfaces.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class PdServiceJrxmlParameterReader {

	private String jrxmlPath ="";

	/**
	 * 
	 * @author sebastien
	 * @date 28.01.2020
	 *
	 * @param pJrxmlPath -> path from JRXML file
	 * @throws myException
	 */
	public PdServiceJrxmlParameterReader _init(String pJrxmlPath) throws myException{
		this.jrxmlPath = pJrxmlPath;
		return this;
	}

	/**
	 * 
	 * @author sebastien
	 * @date 28.01.2020
	 *
	 * @return LinkedHashMap with key = <i>PARAMETER</i> and value = <i>DATA TYPE</i>
	 * @throws myException
	 */
	public LinkedHashMap<String, String> readParameter() throws myException{
		if(S.isFull(jrxmlPath)) {
			try {

				LinkedHashMap<String, String> lhmRueck = new LinkedHashMap<>();

				File jrxmlFile = new File(jrxmlPath);
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder;

				docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(jrxmlFile);

				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("parameter");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						String parameterName 	= eElement.getAttribute("name");
						String parameterClass 	= eElement.getAttribute("class");
//						if(parameterClass.lastIndexOf(".")!=-1) {
//							parameterClass = parameterClass.substring(parameterClass.lastIndexOf(".")+1);
//						}
						lhmRueck.put(parameterName,parameterClass);
					}

				}

				return lhmRueck;
				
			} catch (ParserConfigurationException e) {
				throw new myException("FEHLER : 865ea930-4097-46c9-b0a4-26cc288a6b84 : PdServiceReadJrxmlParameter : ParserConfigurationException\n :"+e.getMessage());
			} catch (SAXException e) {
				throw new myException("FEHLER : e9ee6958-9680-44e5-a6f6-74bf83380d67 : PdServiceReadJrxmlParameter : SAXException\n :"+e.getMessage());
			} catch (IOException e) {
				throw new myException("FEHLER : 3d667a4f-c51a-4c64-bcbf-20307eef469f : PdServiceReadJrxmlParameter : IOException : \n"+e.getMessage());
			} catch (Exception e) {
				throw new myException("FEHLER : 3d667a4f-c51a-4c64-bcbf-20307reef469f : PdServiceReadJrxmlParameter : Allgemeiner Fehler : \n"+e.getMessage());

			}

		}

		return null;

	}

}
