package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ButtonMessageBoxYesNo;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorMyString;
import panter.gmbh.indep.myVectors.VectorString;

public class BS__ButtonResetPrintCounter extends E2_ButtonMessageBoxYesNo 
{
	private E2_NavigationList 	oNaviList = null;
	private String    		  	cTableName = null;
	private MyE2_String 		cBezeichnerFuerBeleg = null;
	

	public BS__ButtonResetPrintCounter(E2_NavigationList o_NaviList, String c_TableName, MyE2_String c_BezeichnerFuerBeleg)  throws myException 
	{
		super(new MyE2_String("Druckzähler auf 0 setzen"),new MyE2_String("Druckzähler auf 0 setzen"), new MyE2_String("Ja"), new MyE2_String("Nein/Abbruch"), new VectorMyString("Zurücksetzen des Druckzählers:","bewirkt, dass kein Duplikat-Vermerk mehr auf","das Formular gedruckt wird !"));
		this.oNaviList = o_NaviList;
		this.cTableName = c_TableName;
		this.cBezeichnerFuerBeleg = c_BezeichnerFuerBeleg;
		
		this.add_GlobalValidator(new validatorListSel());
		this.add_GlobalAUTHValidator_AUTO("RESET_DRUCKZAEHLER");
	}

	
	
	private class validatorListSel extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException 
		{
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			BS__ButtonResetPrintCounter oThis = BS__ButtonResetPrintCounter.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size()!=1)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte genau einen Vorgang vom Typ ",true,oThis.cBezeichnerFuerBeleg.CTrans(),false,"in der Liste auswählen !!",true)));
			}
			else
			{
				String cAnzahl= bibDB.EinzelAbfrage("SELECT NVL(DRUCKZAEHLER,0) FROM "+bibE2.cTO()+"."+oThis.cTableName+" WHERE ID_"+oThis.cTableName.substring(3)+"="+vIDs.get(0));

				if (cAnzahl.trim().equals("0"))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Beim ausgewählten Beleg ist der Druckzähler bereits (oder noch) 0 !!",true)));
				}
			}
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)		throws myException 
		{
			return null;
		}
		
		
	}
	
	
	@Override
	public Vector<XX_ActionAgent> create_actionAgents4yesButton() 
	{
		
		Vector<XX_ActionAgent> vRueck =new Vector<XX_ActionAgent>();
		
		vRueck.add(new ownActionAgent());
		
		return vRueck;
	}

	


	@Override
	public E2_MessageBoxYesNo create_E2_MessageBoxYesNo(	MyE2_String TextTitelZeile, 
															MyE2_String TextYes,
															MyE2_String TextNo, 
															Vector<MyString> Infos,
															XX_ActionAgent ActionAgentStart) throws myException 
	{
		return new ownMessageBox(TextTitelZeile, TextYes, TextNo, Infos, ActionAgentStart);
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BS__ButtonResetPrintCounter oThis = BS__ButtonResetPrintCounter.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte genau einen Vorgang vom Typ ",true,oThis.cBezeichnerFuerBeleg.CTrans(),false,"in der Liste auswählen !!",true)));
				return;
			}
			
			String cSQL = "UPDATE "+oThis.cTableName+" SET DRUCKZAEHLER=0 WHERE ID_"+cTableName.substring(3)+"="+vIDs.get(0);
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(new VectorString(cSQL), true));
			
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Zähler wurde zurückgesetzt ..."));
			}
		}
		
	}
	
	private class ownMessageBox extends E2_MessageBoxYesNo
	{

		public ownMessageBox(MyE2_String TextTitelZeile, MyE2_String TextYes,
				MyE2_String TextNo, Vector<MyString> Infos,
				XX_ActionAgent ActionAgentStart) throws myException {
			super(TextTitelZeile, TextYes, TextNo, Infos, ActionAgentStart);
		}
		
	}
	
}
