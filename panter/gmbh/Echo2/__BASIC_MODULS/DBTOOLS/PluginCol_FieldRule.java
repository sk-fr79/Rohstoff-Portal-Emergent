package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES.RUL_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;


/*
 * verwaltungmodul fuer die verwaltung der trigger zur feldueberwachung
 */
public class PluginCol_FieldRule extends Basic_PluginColumn
{

	
	
	public PluginCol_FieldRule(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);

		this.add(new MyE2_Label(new MyE2_String("Verwaltung der Feldregeln/Zugriffsregeln"), MyE2_Label.STYLE_TITEL_BIG()), E2_INSETS.I_2_2_2_0);
		
		this.add(new RUL_LIST_BasicModuleContainer(), E2_INSETS.I_2_2_2_0);
		
		
	}
	
}
