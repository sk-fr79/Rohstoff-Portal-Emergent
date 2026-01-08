package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.RB_MODULES.CG__ModuleContainer_RB;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class CG__BasicModuleContainer_CodeGen extends Project_BasicModuleContainer
{

	public CG__BasicModuleContainer_CodeGen() throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_MODUL_CODEGENERATOR);
		
		MyE2_TabbedPane  oTabbedPane = new MyE2_TabbedPane(new Integer(600));
		
		oTabbedPane.add_Tabb(new MyE2_String("Records/Recordlists")	, new CG__Column_RECORDS_and_NAMESPACE());
		oTabbedPane.add_Tabb(new MyE2_String("Module")				, new CG__Column_MODULE());
		oTabbedPane.add_Tabb(new MyE2_String("XML-Definition")		, new CG__Column_XML_Editor_Definition());
		oTabbedPane.add_Tabb(new MyE2_String("RB-Modul")			, new CG__ModuleContainer_RB());
		oTabbedPane.add_Tabb(new MyE2_String("RB-Registrierung")	, new CG__Column_RBRegistierung_assistant_tool());
		
		this.add(oTabbedPane, E2_INSETS.I_10_10_10_10);
	}

}
