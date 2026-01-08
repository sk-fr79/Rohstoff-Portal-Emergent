package panter.gmbh.Echo2.Container.xmlDefTools;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG.FieldDef;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG.ListColumn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class XStreamWriterListDefs {

	private ListDef_NG oList = null;
	
	public XStreamWriterListDefs(String cName) {
		
		XStream oXS = new XStream(new Dom4JDriver());
		oXS.alias("listendefinition",ListDef_NG.class);
		oXS.alias("felddefinition",ListDef_NG.FieldDef.class);
		oXS.alias("spaltendefinition",ListDef_NG.ListColumn.class);
		oXS.alias("suchdefinition",ListDef_NG.SearchDef.class);
		oXS.alias("selektorcheckboxdefinition",ListDef_NG.SelectorDefCheckBox.class);
		oXS.alias("selektorcheckboxanausdefinition",ListDef_NG.SelectorDefCheckBox.class);
		oXS.alias("selektordropdowndefinition",ListDef_NG.SelectorDefDropDown.class);

		
		
		oList = new ListDef_NG();
		oList.TABLENAME = "test";
		oList.AUTOMATICFIELDS = "automaticfields";
		oList.DELETEALLOWED = true;
		oList.EDITALLOWED = true;
		oList.NEWALLOWED = true;
		oList.HEADLINE = "HEADLINE";
		oList.HELPTEXT = "Helptext";
		oList.MENUEGROUP = "menugroup";
		oList.MENUELINE = "menuline";
		oList.MODULKENNER ="MODULKENNER";
		oList.NUMBERCOLUMNSINSELECTORGRID = 3;
		oList.NUMBERROWSINLIST = 10;
		oList.PRIMARYKEYFIELD = "ID_PRIMARY";
		oList.SEQUENCEQUERY = "SELECT * FROM SEQ...";
		
		ListColumn oCol = oList.new ListColumn("headline");
		
		oCol.VISIBLEATSTART = true;
		FieldDef oFiel = oList.new FieldDef("fieldname", "titel", "type", true, true, 100, 1, "defselectfield");
		oCol.add_Field(oFiel);
		oFiel = oList.new FieldDef("fieldname2", "titel2", "type", true, true, 100, 1, "defselectfield");
		oCol.add_Field(oFiel);
		
		oList.VECTORCOLUMNS.add(oCol);
		
		String sXML = oXS.toXML(oList);
		File oFile = new File("/daten/temp/xmltest.xml");
		try {
			FileUtils.writeStringToFile (oFile, sXML, "UTF8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
		
		
	}

}
