package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Extent;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_EAK_CODE;
import panter.gmbh.indep.exceptions.myException;

/**
 * button, um avv-code-listen zu exportieren (auf der haupt- und der Lieferadressen-Liste 
 * @author martin
 *
 */
public class __FS_BT_EXPORT_EAK_LIST extends MyE2_Button {
	private __FS_Component_MASK_DAUGHTER_EAK_CODES  oAVV_CODE_Daughter = null;

	public __FS_BT_EXPORT_EAK_LIST(__FS_Component_MASK_DAUGHTER_EAK_CODES oAVV_CODE_Daughter) {
		super(new MyE2_String("Export"));
		this.oAVV_CODE_Daughter = oAVV_CODE_Daughter;
		this.setToolTipText(new MyE2_String("Exportieren der IDs der untenstehenden Liste, um diese auf anderen Lagern zu importieren").CTrans());
		this.add_oActionAgent(new ownAction());
	}
	
	
	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			__FS_BT_EXPORT_EAK_LIST oThis = __FS_BT_EXPORT_EAK_LIST.this;
			
			Vector<String> vIDs = new Vector<String>();
			
			vIDs.addAll(oThis.oAVV_CODE_Daughter.get_oNavigationList().get_vectorSegmentation());
			
			new ownContainer(vIDs);
			
		}
		
	}
	
	
	private class ownContainer extends E2_BasicModuleContainer
	{
		private Vector<String> 		vIDs = null;
		private MyE2_TextArea  		oTA = new MyE2_TextArea("",100,10000,30);
		

		public ownContainer(Vector<String> vIDs) throws myException {
			super();
			this.vIDs = vIDs;
			
			this.add(new MyE2_Label(new MyE2_String("Folgende AVV-Codes wurden gefunden:"),true));
			this.add(oTA);
			StringBuffer cTextHelp = new StringBuffer("");
			for (String cID: this.vIDs) {
				RECORD_ADRESSE_EAK_CODE recAdrEAK = new RECORD_ADRESSE_EAK_CODE(cID);
				cTextHelp.append(recAdrEAK.get_ID_EAK_CODE_cUF_NN(""));
				cTextHelp.append("\n");
			}
			this.oTA.setText(cTextHelp.toString());
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(200), new Extent(600), new MyE2_String("Export AVV-Codes"));
		}
		
	}
	
	
}
