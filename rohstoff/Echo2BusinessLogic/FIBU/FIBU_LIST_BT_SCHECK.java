package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;
import panter.gmbh.indep.myVectors.VectorSingle;


public class FIBU_LIST_BT_SCHECK extends MyE2_Button
{

	private E2_NavigationList  oNaviList = null;

	public FIBU_LIST_BT_SCHECK(E2_NavigationList onavigationList)
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
		protected MyE2_MessageVector isValid(String cID_FIBU)	throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			RECORD_FIBU  recFibu = new RECORD_FIBU(cID_FIBU);
			
			if (recFibu.is_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Stornierte Buchung: Keine weitere Eingabe möglich !"));
				return oMV; 
			}
			
			RECORD_ADRESSE  recAdresse = recFibu.get_UP_RECORD_ADRESSE_id_adresse();
			
			if (recAdresse.is_BARKUNDE_NO() && recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_SCHECKDRUCK_JN_NO())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Scheckdruck nur möglich bei Adressen, die entweder als BAR oder für SCHECK  !!")));
			}
			
			return oMV;
		}
		
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FIBU_LIST_BT_SCHECK oThis = FIBU_LIST_BT_SCHECK.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			String cID_Fibu = null;
			
			if (vIDs.size() > 1)
			{
				//dann nachsehen, ob der fibu-block einheitlich ist
				VectorSingle vTest = new VectorSingle();
				for (String cID_FIBU: vIDs)
				{
					RECORD_FIBU  recFibu = new RECORD_FIBU(cID_FIBU);
					if (recFibu.is_STORNIERT_YES())
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte nur NICHT stornierte Buchungen auswählen !!"));
						return;
					}
					
					vTest.add(recFibu.get_BUCHUNGSBLOCK_NR_cUF());
				}
				
				if (vTest.size()>1)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte höchstens einen BUCHUNGSBLOCK auswählen !!"));
					return;
				}
			}
			
			
			
			if (vIDs.size()>=1)
			{
				cID_Fibu = vIDs.get(0);
			}
			else
			{
				cID_Fibu = null;
			}
			
			if (cID_Fibu != null)
			{
				bibMSG.add_MESSAGE(execInfo.make_ID_Validation(vIDs));
			}
			
			if (bibMSG.get_bIsOK())
			{
				new FIBU__BUCHUNGS_CONTAINER(cID_Fibu, oThis.oNaviList,true);
			}
			
		}
	}
	
	
	
}
