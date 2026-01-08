package panter.gmbh.Echo2.__BASIC_MODULS.ADMINTOOLS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class ADM_BasicModulContainer extends Project_BasicModuleContainer
{
	private MyE2_TabbedPane				oTabbedPane = new MyE2_TabbedPane(new Integer(600));

	
	public ADM_BasicModulContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ADMINTOOLS);
		
		this.add(this.oTabbedPane);
		this.oTabbedPane.set_bAutoHeight(true);
		
		this.oTabbedPane.add_Tabb(new MyE2_String("Adressen umdefinieren"),new Tool_ChangeAdressIDs(this.get_MODUL_IDENTIFIER()));
		this.oTabbedPane.add_Tabb(new MyE2_String("AVV-Codes zuordnen (via Branche)"),new Tool_Fill_EAK_Codes(this,"JT_BRANCHE","ID_BRANCHE","KURZBEZEICHNUNG"));
		this.oTabbedPane.add_Tabb(new MyE2_String("AVV-Codes zuordnen (via AVV-Branche)"),new Tool_Fill_EAK_Codes(this,"JT_EAK_BRANCHE","ID_EAK_BRANCHE","BRANCHE"));
		this.oTabbedPane.add_Tabb(new MyE2_String("EU-Formular-Felder Füllen"),new Tool_Fill_EU_BEIBLATT_INFOFELDER());

	}
	
	
	
}
