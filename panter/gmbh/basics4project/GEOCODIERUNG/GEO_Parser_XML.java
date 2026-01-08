package panter.gmbh.basics4project.GEOCODIERUNG;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class GEO_Parser_XML{

	private StringBuffer 				input_stream;

	private VEK<GEO_Location> 			vLocation;
	private String 						id_adresse; 

	public GEO_Parser_XML(String p_id_adresse, StringBuffer buff) {
		super();
		this.vLocation 		= new VEK<GEO_Location>();
		this.input_stream 	= buff;
		this.id_adresse 	= p_id_adresse;
	}

	public GEO_Parser_XML parse(GEO_ErrorMap error_hm) throws myException {
		
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(input_stream.toString()));

			Document doc = builder.parse(is);

			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("place");
			if(nodeList.getLength()>0) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;

						if (S.isAllFull(element.getAttribute("lat"),element.getAttribute("lon"))) {
							GEO_Location location = new GEO_Location()
									._set_osm_id(		element.getAttribute("osm_id")		)
									._set_place_id(		element.getAttribute("place_id")	)
									._set_osm_type(		element.getAttribute("way")			)
									._set_latitude(		element.getAttribute("lat")			)
									._set_longitude(	element.getAttribute("lon")			)
									._set_strasse(		get_value(element,"road")			)
									._set_hausnummer(	get_value(element, "house_number")	)
									._set_city(			get_value(element, "city")			)	
									._set_dorf(			get_value(element, "village")		)
									._set_stadt(		get_value(element, "town")			)
									._set_plz(			get_value(element,"postcode")		)
									._set_land(			get_value(element,"country")		)
									._set_land_code(	get_value(element,"country_code")	)
									;
	
							this.vLocation._a(location);
						}
					}
				}
			}
//			else {
//				error_hm._add(ENUM_GEO_Error.NO_COORDINATE, this.id_adresse);
//			}

		} catch (ParserConfigurationException e) {
			error_hm._add(ENUM_GEO_Error.UNDEFINED_ERROR, this.id_adresse);
		} catch (SAXException e) {
			error_hm._add(ENUM_GEO_Error.UNDEFINED_ERROR, this.id_adresse);
		} catch (IOException e) {
			error_hm._add(ENUM_GEO_Error.UNDEFINED_ERROR, this.id_adresse);
		}
		return this;		
	}


	private String get_value(Element e, String tag) throws myException{
		String rueck_str = "";
		if(e.getElementsByTagName(tag).item(0) == null) {
			rueck_str ="";
		}else {
			rueck_str = e.getElementsByTagName(tag).item(0).getTextContent();
		}
		return rueck_str;
	}

	public VEK<GEO_Location> get_locations() {
		return vLocation;
	}
	
}
