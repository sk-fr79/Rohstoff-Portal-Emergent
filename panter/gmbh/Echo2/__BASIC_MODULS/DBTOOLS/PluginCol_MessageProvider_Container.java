/**
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider.MES_LIST_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class PluginCol_MessageProvider_Container extends Basic_PluginColumn {

	/**
	 * @param oMothercontainer
	 * @throws myException 
	 */
	public PluginCol_MessageProvider_Container(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException {
		super(oMothercontainer);
		
		this.add(new MyE2_Label(new MyE2_String("Definition der Meldungsverteilung von Systemmeldungen"), MyE2_Label.STYLE_TITEL_BIG()), E2_INSETS.I_2_2_2_0);
		
		this.add(new MES_LIST_BasicModuleContainer(), E2_INSETS.I_2_2_2_0);

		
		
	}

}
