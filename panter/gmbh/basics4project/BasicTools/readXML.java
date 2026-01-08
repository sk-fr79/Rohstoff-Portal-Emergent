package panter.gmbh.basics4project.BasicTools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;

/**
 * readXML sammelt node-eintraege, die in der Form: <tag>test</tag> definiert sind. Es gibt ein einleitendes element,
 * darunter 
 * 
 * Beispiel fuer ein xml-file einer message-strucktur, die 2 meldungen erzeugt
 
 <block>
	<message>
		<titel>Test Titel Zeile</titel>
		<text>Das ist ein Test-Text
		für eine Meldung
		</text>
		<user>4000</user>
		<user>4001</user>
		<user>4003</user>
		<subnote>
			<user2>4002</user2>
			<user2>4005</user2>
			<user2>4008</user2>
		</subnote>
	
	</message>



	<message>
		<titel>Test Titel Zeile</titel>
		<text>Das ist ein Test-Text
		für eine Meldung
		</text>
		<user>4000</user>
		<user>4001</user>
		<user>4003</user>
		<subnote>
			<user2>4002</user2>
			<user2>4005</user2>
			<user2>4008</user2>
		</subnote>
	
	</message>

</block> 


 * 
 * 
 * @author martin
 *
 */
public class readXML {


	private String xmlText = 			null;  					//zu interpretierender Text
	private String nameOfStartingNode = "block";				//name des getDocumentElement()
	private String nameOfBlockToRead = 	"message";		 		//name der nodes, die beim jeweiligen aufruf untersucht werden

	private Vector<LinkedHashMap<String, Vector<String>>> v_ret = new Vector<>();

	
	public readXML() {
		super();
	}

	public readXML _set_xmlText(String xmlText) {
		this.xmlText = xmlText;
		return this;
	}

	public String get_xmlText() {
		return xmlText;
	}
	
	/**
	 * definiert den block, der alles umhuellt
	 * @param nameOfStartingNode
	 * @return
	 */
	public readXML _set_nameOfStartingNode(String nameOfStartingNode) {
		this.nameOfStartingNode = nameOfStartingNode;
		return this;
	}

	public String get_nameOfStartingNode() {
		return nameOfStartingNode;
	}

	//diese bloecke beinhalten die eigentlichen definitionen (ergibt dann je eine linkedHashMap<String, ArrayList<String>> fuer jedes objekt)
	public readXML _set_nameOfBlockToRead(String firstFloorName) {
		this.nameOfBlockToRead = firstFloorName;
		return this;
	}
	

	public String get_firstFloorName() {
		return nameOfBlockToRead;
	}

	

	
	/**
	 * 
	 * @param noteFilter
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Vector<LinkedHashMap<String, Vector<String>>> interpret() throws ParserConfigurationException, SAXException, IOException {
		
		this.v_ret.clear();
		
		InputSource source = new InputSource(new InputStreamReader(IOUtils.toInputStream(this.xmlText)));
		DocumentBuilderFactory mxlFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = mxlFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(source);
		
        Element element = doc.getDocumentElement();
		if (element.getNodeName().equals(this.nameOfStartingNode)) {
	        DEBUG.System_println(element.getNodeName());
	        NodeList nodes = element.getChildNodes();
	        this.interPretNote(nodes);
		}
		return v_ret;
	}
	
	
	/**
	 * sucht ab der uebergenen notelist recursiv alle notes durch
	 * @param nodes
	 */
	private void interPretNote(NodeList nodes) {
		
        for (int i = 0; i < nodes.getLength(); i++) {
        	Node node = nodes.item(i);  
        	if (!(node.getNodeType()==Node.ELEMENT_NODE && node.getChildNodes().getLength()==1)) {
        		if (node.getNodeName().equals(this.nameOfBlockToRead)) {
        			DEBUG.System_println("Ein neuer Block ...");
        			LinkedHashMap<String, Vector<String>> hashMap = new LinkedHashMap<>();
        			this.v_ret.add(hashMap);
        			this.interPretNote(nodes.item(i).getChildNodes(), hashMap);
        		} else {
        			this.interPretNote(nodes.item(i).getChildNodes());
        		}
        	} else {
        		DEBUG.System_println("uebergangen..." +node.getNodeName()+" -> "+node.getTextContent());
        	}
        }
	}

	/**
	 * sucht ab der uebergebenen notelist recursiv alle notes durch und sammelt
	 * @param nodes
	 */
	private void interPretNote(NodeList nodes,LinkedHashMap<String, Vector<String>> hashMap ) {
		
        for (int i = 0; i < nodes.getLength(); i++) {
        	Node node = nodes.item(i);  
        	if (node.getNodeType()==Node.ELEMENT_NODE && node.getChildNodes().getLength()==1) {
        		Vector<String> v_values = hashMap.get(node.getNodeName());
        		if (v_values==null) {
        			v_values = new Vector<>();
        			hashMap.put(node.getNodeName(), v_values);
        		}
        		v_values.add(S.NN(node.getTextContent()));
        		DEBUG.System_println("aktiv..." +node.getNodeName()+" -> "+node.getTextContent());
        		
        	} else {
        		this.interPretNote(nodes.item(i).getChildNodes(), hashMap);
        	}
        }
	}
	
	
	
	
	
	
}
