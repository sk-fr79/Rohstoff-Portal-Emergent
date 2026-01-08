package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class __FU_ButtonOpenFuhre extends E2_Button  {

	private Rec21 recFuhre = null; 
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return null;
	}
	
	
	public __FU_ButtonOpenFuhre(Rec21 fuhre) {
		super();
		
		this.recFuhre = fuhre;
				
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"BEARBEITE_FUHRE"));

		this.add_GlobalValidator(new ownValidator());
		this.add_oActionAgent(new ownAction());
		
		this.setToolTipText(new MyE2_String("Fuhre direkt bearbeiten ...").CTrans());
		
	}
	
	
	private class ownValidator extends XX_ActionValidator_NG {
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException {
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (recFuhre.is_yes_db_val(VPOS_TPA_FUHRE.deleted)) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde bereits gelöscht (ID: "+recFuhre.getId()+")")));
			} else {
				if (recFuhre.is_yes_db_val(VPOS_TPA_FUHRE.ist_storniert)) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde storniert (ID: "+recFuhre.getId()+")")));
				}
			}
			return oMV;
		}
	}
	
	
	
	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new __FUHREN_MASKEN_OEFFNER(new FU_MASK_ModulContainer(null, null, false, false), null, recFuhre.getIdLong().toString(), null, null,false);
		}
	}
	
	
	
}
