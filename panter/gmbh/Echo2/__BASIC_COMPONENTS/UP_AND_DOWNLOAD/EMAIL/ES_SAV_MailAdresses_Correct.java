package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.COMP.RB_SimpleDaughter;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailAdressChecker;

public class ES_SAV_MailAdresses_Correct extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid( 	RB_ComponentMap 			rbMASK, 
																VALID_TYPE 			ActionType, 
																ExecINFO 			oExecInfo) throws myException {
		
		MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
		
		Vector<MyE2IF__DB_Component>  vMailadressenAlle = this.sammleMailadressen(rbMASK,true);
		Vector<MyE2IF__DB_Component>  vMailadressenFalsch = this.sammleMailadressen(rbMASK,false);

		
		//alle farbmarkierungen resetten
		for (MyE2IF__DB_Component  tf: vMailadressenAlle) {
			if (tf instanceof MyE2_TextField) {
				if (!((MyE2_TextField)tf).isEnabled()) {
					((MyE2_TextField)tf).setBackground(new E2_ColorGray(230));
				} else {
					((MyE2_TextField)tf).setBackground(new E2_ColorEditBackground());
				}
				
			}
		}


		//nur beim laden in eine Edit-maske
		if (ActionType==RB__CONST.VALID_TYPE.USE_IN_MASK_LOAD_ACTION && rbMASK.getRbDataObjectActual().rb_relevant_record_to_fill() instanceof MyRECORD_IF_RECORDS) {
			
			if (vMailadressenFalsch.size()>0) {
				for (MyE2IF__DB_Component eMail:vMailadressenFalsch) {
					oMVRueck.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Mailadresse: ",true,eMail.get_cActualMaskValue(),false," scheint nicht korrekt! ",true)));
					((MyE2_DB_TextField)eMail).setBackground(new E2_ColorHelpBackground());
				}
			}
			
			
		} else if (ActionType==RB__CONST.VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
			if (vMailadressenFalsch.size()>0) {
				for (MyE2IF__DB_Component eMail:vMailadressenFalsch) {
					oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Mailadresse: ",true,eMail.get_cActualMaskValue(),false," scheint nicht korrekt! ",true)));
					((MyE2_DB_TextField)eMail).setBackground(new E2_ColorHelpBackground());
				}
			}
		}
		
		
		
		
		
		return oMVRueck;
	}
	
	

	
	private Vector<MyE2IF__DB_Component> sammleMailadressen(RB_ComponentMap rbMASK, boolean bAll) throws myException {
		
		Vector<MyE2IF__DB_Component>  vMailadressenFalsch = new Vector<MyE2IF__DB_Component>();
		Vector<MyE2IF__DB_Component>  vMailadressenAll = new Vector<MyE2IF__DB_Component>();
		RB_TextField_old tf_Email= (RB_TextField_old)rbMASK.get__Comp(_DB.EMAIL_SEND$SENDER_ADRESS);
		
		vMailadressenAll.add(tf_Email);
		
		if (S.isFull(tf_Email.get_cActualMaskValue())) {
			if (!(new MailAdressChecker(tf_Email.get_cActualMaskValue()).isOK())) {
				vMailadressenFalsch.add(tf_Email);
			}
		}

		RB_SimpleDaughter  sd_Verteiler = (RB_SimpleDaughter)rbMASK.get__Comp(ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS);
		
		Vector<E2_ComponentMAP> vE2_ComponentMAPS = sd_Verteiler.get_vE2_ComponentMAPs_NewAndEdit_WithoutDeleteMarker();
		
		for (E2_ComponentMAP oMAP: vE2_ComponentMAPS) {
			vMailadressenAll.add(oMAP.get__DBComp(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS));
			String cMailAdress = oMAP.get_cActualDBValueFormated(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS);
			if (S.isFull(cMailAdress)) {
				if (!(new MailAdressChecker(cMailAdress).isOK())) {
					vMailadressenFalsch.add(oMAP.get__DBComp(_DB.EMAIL_SEND_TARGETS$TARGET_ADRESS));
				}
			}
		}
		
		return bAll?vMailadressenAll:vMailadressenFalsch;
		
	}



	
}


	

