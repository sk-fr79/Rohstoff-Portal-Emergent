package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.FIBU__BUCHUNGS_CONTAINER;


public class BSRG_K_LIST_BT_SCHECK extends MyE2_Button
{

	private E2_NavigationList  oNaviList = null;

	public BSRG_K_LIST_BT_SCHECK(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("buchung_scheck.png") , E2_ResourceIcon.get_RI("leer.png"));
		
		this.oNaviList = onavigationList;
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EINGABE_SCHECK"));
		this.setToolTipText(new MyE2_String("Scheck-Druck").CTrans());
		this.add_IDValidator(new ownValidator());
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component componentWhichIsValidated)	throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VKOPF_RG)	throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			RECORD_VKOPF_RG  recVKOPF_RG = new RECORD_VKOPF_RG(cID_VKOPF_RG);
			
			if (recVKOPF_RG.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Auf eine gelöschte Gutschrift kann kein Scheck gedruckt werden !"));
				return oMV; 
			}

			if (recVKOPF_RG.is_ABGESCHLOSSEN_NO())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Nur abgeschlossenen Gutschriften kann ein Scheck zugeordnet werden !"));
				return oMV; 
			}


			// sicherheitshalber
			if (!recVKOPF_RG.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_GUTSCHRIFT))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Rechnungen kann kein Scheck zugeordnet werden !"));
				return oMV; 
			}

			
			return oMV;
		}
		
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			BSRG_K_LIST_BT_SCHECK oThis = BSRG_K_LIST_BT_SCHECK.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();

			String cID_Fibu = null;
			
			if (vIDs.size() > 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte höchstens eine Gutschrift (oder keine) auswählen !!"));
				return;
			}
			else if (vIDs.size()==1)
			{
				bibMSG.add_MESSAGE(execInfo.make_ID_Validation(vIDs));
				
				if (bibMSG.get_bHasAlarms())
				{
					return;
				}

				// jetzt ueber die RG-ID die fibu-id suchen
				RECLIST_FIBU recListFibu = new RECLIST_FIBU("SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE ID_VKOPF_RG="+vIDs.get(0));
				if (recListFibu.get_vKeyValues().size()!=1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Ich kann den Buchungssatz für diese Gutschrift nicht finden ?"));
					return;
				}
				cID_Fibu = recListFibu.get(0).get_ID_FIBU_cUF();
			}
			else
			{
				cID_Fibu = null;
			}
			
			if (bibMSG.get_bIsOK())
			{
				new FIBU__BUCHUNGS_CONTAINER(cID_Fibu, oThis.oNaviList,true);
			}
			
		}
	}
	
	
	
}
