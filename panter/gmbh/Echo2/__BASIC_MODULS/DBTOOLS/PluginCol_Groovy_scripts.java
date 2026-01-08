package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS.GROOVY_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;


/*
 * verwaltungmodul fuer die verwaltung der trigger zur feldueberwachung
 */
public class PluginCol_Groovy_scripts extends Basic_PluginColumn
{
	
	public PluginCol_Groovy_scripts(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);

		this.add(new MyE2_Label(new MyE2_String("Groovyscripte für Datenbank-gestützt Abfrageausdrücke"), MyE2_Label.STYLE_TITEL_BIG()), E2_INSETS.I_2_2_2_0);
		
		this.add(new GROOVY_LIST_BasicModuleContainer(), E2_INSETS.I_2_2_2_0);
		
		
	}
	
}
