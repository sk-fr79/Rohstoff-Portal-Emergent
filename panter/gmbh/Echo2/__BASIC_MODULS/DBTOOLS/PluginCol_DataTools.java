/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS
 * @author manfred
 * @date 28.07.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer.IMPORT_Zolltarifnummer_Temp_BasicModuleContainer;

/**
 * @author manfred
 * @date 28.07.2016
 *
 */
public class PluginCol_DataTools extends Basic_PluginColumn {

	/**
	 * @author manfred
	 * @date 28.07.2016
	 *
	 * @param oMothercontainer
	 */
	public PluginCol_DataTools(ContainerForVerwaltungsTOOLS oMothercontainer) {
		super(oMothercontainer);
		
		
		MyE2_Button oButtonImportWarennummern = new MyE2_Button(new MyE2_String("Zolltarifnummern importieren "));
		oButtonImportWarennummern.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new IMPORT_Zolltarifnummer_Temp_BasicModuleContainer().show();
			}
		});
		
		// column fuer das tab-pane aufbauen
		this.add(new E2_ComponentGroupHorizontal(0,oButtonImportWarennummern,new Insets(1)),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
//		this.add(this.get_TextArea4Output(),ContainerForVerwaltungsTOOLS.INSETS_LIST);

	}
	
	

}
