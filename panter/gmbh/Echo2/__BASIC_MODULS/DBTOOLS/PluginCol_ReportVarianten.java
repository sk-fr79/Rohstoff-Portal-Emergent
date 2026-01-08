package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten.RV_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Label;

public class PluginCol_ReportVarianten extends Basic_PluginColumn
{

	public PluginCol_ReportVarianten(ContainerForVerwaltungsTOOLS oMothercontainer)
	{
		super(oMothercontainer);
		
		try {
			this.add(new MyE2_Label(new MyE2_String("Reports mit bestimmten Parametersätzen durch anderer (z.b. Sprache) ersetzen"), MyE2_Label.STYLE_TITEL_BIG()), E2_INSETS.I_2_2_2_10);
			this.add(new RV_LIST_BasicModuleContainer());
		} catch (Exception e) {
			e.printStackTrace();
			this.add(new Label("FEHLER !!"));
		}	
		
	}

}
