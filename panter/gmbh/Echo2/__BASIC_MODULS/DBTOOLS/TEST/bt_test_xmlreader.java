package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.TEST;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.BasicTools.TextFileLoader;
import panter.gmbh.basics4project.BasicTools.readXML;
import panter.gmbh.indep.exceptions.myException;

public class bt_test_xmlreader extends E2_Button {

	/**
	 * 
	 */
	public bt_test_xmlreader() {
		super();
		
		this._t("TEST XML")._aaa(new ownAction());
		
		
		
	}

	
	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			TextFileLoader load = new TextFileLoader(readXML.class, "text.xml");
			
			try {
				new readXML()._set_xmlText(load.get_loadedText()).interpret();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
