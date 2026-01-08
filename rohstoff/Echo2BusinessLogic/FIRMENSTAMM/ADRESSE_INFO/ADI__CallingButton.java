package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Button4Valid;
import panter.gmbh.basics4project.VALIDATOR_KEY;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * button, der aus der liste die Adress-info-Bearbeitung aufruft
 * @author martin
 *
 */
public class ADI__CallingButton extends E2_Button4Valid {

	private E2_NavigationList  naviList = null;
	
	public ADI__CallingButton(E2_NavigationList p_naviList) throws myException {
		super(VALIDATOR_KEY.EDIT_INFOS);
		this._image(E2_ResourceIcon.get_RI("info_list.png"))
			._s_Image()
			._ttt(new MyE2_String("Erfassen von Adresseinformationen"))
			._v(new ownValidatorExactOneAdress())
			._aaa(new ownActionAgentShowListContainer());
		
		this.naviList = p_naviList;
	}

	
	private class ownValidatorExactOneAdress extends  XX_ActionValidator_NG {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();

			if (naviList.get_vSelectedIDs_Unformated().size()!=1) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte genau eine Adresse auswählen !!")));
			}
			return mv;
		}
	}
	

	
	private class ownActionAgentShowListContainer extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RECORD_ADRESSE  ra = new RECORD_ADRESSE(ADI__CallingButton.this.naviList.get_vSelectedIDs_Unformated().get(0));
			
			RECORD_ADRESSE_extend rae = new RECORD_ADRESSE_extend(ra);
			
			new ADI_LIST_BasicModuleContainer(ra).CREATE_AND_SHOW_POPUPWINDOW(new Extent(820), new Extent(600), new MyE2_String("Informationen zur Adresse: ",true,rae.get_block_haupt_namen()+", "+rae.get_block_plz_ort(),false));
		}
		
	}
	
	
}
