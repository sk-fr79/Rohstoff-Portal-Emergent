package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class FS_BT_DruckMail_EU_VERTRAG extends MyE2_Button {

	private E2_NavigationList naviList = null;

	public FS_BT_DruckMail_EU_VERTRAG(String p_buttonText, E2_NavigationList p_naviList) {
		super(p_buttonText);

		this.naviList = p_naviList;

		this.add_GlobalValidator(new ownValidatorValidNoEmptySelection());
		this.add_GlobalValidator(new ownValidatorValidOnlyOneSelection());
		this.add_GlobalValidator(new ownValidatorValidEveryAdressHasReport());
		
		this.add_oActionAgent(new XX_ActionAgent() {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new FS_POPUP_PrintMail_EU_VERTRAG(naviList);
			}
		});

	}


	public E2_NavigationList getNavilist() {
		return naviList;
	}


	public void setNavilist(E2_NavigationList navilist) {
		this.naviList = navilist;
	}

	private class ownValidatorValidEveryAdressHasReport extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {

			MyE2_MessageVector oMV = new MyE2_MessageVector();

			FS_BT_DruckMail_EU_VERTRAG oThis = FS_BT_DruckMail_EU_VERTRAG.this;

			Vector<String> vID_Adress = oThis.naviList.get_vSelectedIDs_Unformated();

			for (String cID_ADRESS: vID_Adress) {
				RECORD_ADRESSE  recADRESS = new RECORD_ADRESSE(cID_ADRESS);

				if (S.isEmpty(recADRESS.get_ID_ADRESSE_EU_VERTR_FORM_cUF_NN(""))) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Adresse ohne definiertes Vertragsformular: ",true,
							recADRESS.get___KETTE(bibVECTOR.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$ORT)),false)));
				}
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}

	}

	private class ownValidatorValidNoEmptySelection extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {

			MyE2_MessageVector oMV = new MyE2_MessageVector();

			if (FS_BT_DruckMail_EU_VERTRAG.this.naviList.get_vSelectedIDs_Unformated().size()==0) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte markieren Sie mindestens eine Adresse ")));
			}

			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}

	}

	private class ownValidatorValidOnlyOneSelection extends XX_ActionValidator_NG{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector omv = new MyE2_MessageVector();
			if(FS_BT_DruckMail_EU_VERTRAG.this.getNavilist().get_vSelectedIDs_Unformated().size()>1) {
				omv._addAlarm("Bitte markieren Sie eine Adresse");
			}
			return omv;
		}

	}
}
