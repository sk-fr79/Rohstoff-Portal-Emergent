package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FU__ListButton_Edit_IN_LIST_FUHRE extends MyE2_ButtonInLIST 
{

	private RECORD_VPOS_TPA_FUHRE recFuhre = null; 
	
	
	public FU__ListButton_Edit_IN_LIST_FUHRE() 
	{
		super(E2_ResourceIcon.get_RI("edit_mini.png"), true);
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"BEARBEITE_FUHRE"));

		this.add_GlobalValidator(new ownValidator());
		this.add_oActionAgent(new ownAction());
		
		this.setToolTipText(new MyE2_String("Fuhre direkt bearbeiten ...").CTrans());
		
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FU__ListButton_Edit_IN_LIST_FUHRE oCopy = new FU__ListButton_Edit_IN_LIST_FUHRE();
		
		oCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCopy));
		
		return oCopy;
	}
	
	
	
	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException 
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			FU__ListButton_Edit_IN_LIST_FUHRE oThis = FU__ListButton_Edit_IN_LIST_FUHRE.this;
			String cID_Fuhre = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			if (oThis.recFuhre==null)
			{
				oThis.recFuhre=new RECORD_VPOS_TPA_FUHRE(cID_Fuhre);
			}

			if (oThis.recFuhre.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde bereits gelöscht !")));
			}
			
			if (oThis.recFuhre.is_IST_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde storniert !")));
			}
			
			return oMV;
		}

		
		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException 
		{
			return null;
		}
		
	}
	
	
	
	
	private class ownAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FU__ListButton_Edit_IN_LIST_FUHRE 	oThis = FU__ListButton_Edit_IN_LIST_FUHRE.this;
			
			E2_ComponentMAP  					oMap = 	oThis.EXT().get_oComponentMAP();
			
			
			String 								cID_Fuhre = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			if (oThis.recFuhre==null)
			{
				oThis.recFuhre=new RECORD_VPOS_TPA_FUHRE(cID_Fuhre);
			}
			
			E2_NavigationList 					oNaviList =  oMap.get_oNavigationList_This_Belongs_to();

			FU_LIST_ModulContainer  			oModuleContainer = (FU_LIST_ModulContainer)oNaviList.get_oContainer_NaviList_BelongsTo();
			
			new __FUHREN_MASKEN_OEFFNER(oModuleContainer.get_oMaskFuhre(), oNaviList, cID_Fuhre, null, null,true);
			
			oThis.recFuhre = null;   //zwingt beim naechsten klick wieder zum neubau
		}
	}
	
	
	
}
