/**
 * rohstoff.Echo2BusinessLogic.INTRASTAT.XML
 * @author manfred
 * @date 20.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.INTRASTAT.XML;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.dom4j.Element;
import org.dom4j.dom.DOMElement;

import panter.gmbh.indep.S;


/**
 * @author manfred
 * @date 20.10.2020
 *
 */
public  class xml_element  {

	// optionale elemente müssen nicht generiert werden, wenn leer...
	boolean _IsOptional = false;
	
	// Name und Kenner des Knoten
	protected String _node ;
	
	// Hashmap der Attribute des Knoten
	protected LinkedHashMap<String, String> _hmAttributes = new LinkedHashMap<String, String>(); 
	
	// Text (falls vorhanden) des Knoten
	protected String _text;

	// Text als CDATA setzen
	protected boolean _isCDATA = false;
	
	// untergeordnete Elemente
	public ArrayList<xml_element> _alElements = new ArrayList<xml_element>();

	
	/**
	 * @author manfred
	 * @date 20.10.2020
	 *
	 */
	public xml_element(String node) {
		_node = node;
	}
	
	public xml_element setAttribut (String key, String value) {
		_hmAttributes.put(key, value);
		return this;		
	}
	
	
	public xml_element addElement(xml_element element) {
		_alElements.add(element);
		return this;
	}
	
	
//	public xml_element setElementText(String node, String text) {
//		if (_hmElements.containsKey(node)) {
//			xml_element o =  _hmElements.get(node);
//			o.setText(text);
//		}
//		return this;
//	}
	
	
	public xml_element setText (String text) {
		_text = text;
		return this;
	};
	
	public xml_element isCDATA( boolean isCDATA) {
		_isCDATA = isCDATA;
		return this;
	}
	
	public xml_element isOptional(boolean isOptional) {
		_IsOptional = isOptional;
		return this;
	}
	
//	/**
//	 * gibt das Child mit dem Node-Namen zurück;
//	 * @author manfred
//	 * @date 20.10.2020
//	 *
//	 * @param node
//	 * @return
//	 */
//	public xml_element get_xml_element(String node) {
//		
//		if (_hmElements.containsKey(node)) {
//			xml_element o =  _hmElements.get(node);
//			return o;
//		} else {
//			return null;
//		}
//
//	}
	
	
	
	protected void init() {
		
	};
	
	
	/**
	 * generate xml-element recursively
	 * @author manfred
	 * @date 20.10.2020
	 *
	 * @return
	 */
	public Element getElement() {
		Element element = new DOMElement(_node);
		
		init();
		

		
		// Attribute erzeugen
		// nur, wenn auch ein Wert angegeben ist.
		Iterator<Map.Entry<String, String>> it =_hmAttributes.entrySet().iterator(); 
		while(it.hasNext()) {
			Map.Entry<String, String> pair = it.next();		
			if (S.isAllFull(pair.getValue())){
				element.addAttribute(pair.getKey(), pair.getValue());
			}
		}
		
		
		// element aufbauen  Text oder weitere Strukturen
		if (_text != null) {
			if(_isCDATA ) {
				element.addCDATA(_text);
			} else {
				element.setText(_text) ;
			}
		} else {

			// Untergeordnete Elemente erzeugen / REKURSION
			for(xml_element xml_elem: _alElements) {
				if (xml_elem._IsOptional == false || S.isFull(xml_elem._text) || xml_elem._hmAttributes.size() > 0 || xml_elem._alElements.size() > 0 ) {
					element.add(xml_elem.getElement());
				} 
			}
		}
		
		
		return element;
	};
}
