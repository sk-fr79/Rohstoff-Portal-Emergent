/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_LIST_BT_STORNO extends MyE2_Button
{
	private E2_NavigationList 	oNavigationList = 	null;
	private Vector<String>		vSQL_Stack = 		new Vector<String>();
	private Vector<String>		vID_Selected = 		new Vector<String>();
	
	private MyE2_TextArea oTextStornoBeguendung = new MyE2_TextArea("",300,500,5);
	
	
	public FIBU_LIST_BT_STORNO(E2_NavigationList onavigationlist)
	{
		super(E2_ResourceIcon.get_RI("storno.png"),true);
		this.oNavigationList = onavigationlist;
		this.add_oActionAgent(new ownActionAgent());

		this.setToolTipText((new MyE2_String("Stornieren der angewählten Buchung ...")).CTrans());
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("STORNO_BUCHUNG"));

		this.add_IDValidator(new ownValidator());
		
	}

	

	private class ownValidator extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_UnFormated) 	throws myException
		{
			RECORD_FIBU recFIBU = new RECORD_FIBU(cID_UnFormated);
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();

			if (recFIBU.is_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Position ist bereits storniert ..."));
				return oMV;
			}
			
			if (recFIBU.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_GUTSCHRIFT) || 
				recFIBU.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_RECHNUNG))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Positionen vom Typ Rechnung/Gutschrift können NICHT storniert werden !"));
				return oMV;
			}
			
			return oMV;
		}
		
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public ownActionAgent()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FIBU_LIST_BT_STORNO oThis = FIBU_LIST_BT_STORNO.this;
			
			FIBU_LIST_BT_STORNO.this.vSQL_Stack.removeAllElements();
			FIBU_LIST_BT_STORNO.this.vID_Selected.removeAllElements();
			FIBU_LIST_BT_STORNO.this.vID_Selected.addAll(FIBU_LIST_BT_STORNO.this.oNavigationList.get_vSelectedIDs_Unformated());

			if (FIBU_LIST_BT_STORNO.this.vID_Selected.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte markieren Sie GENAU EINE Buchung zum Stornieren !"));
				return;
			}

			bibMSG.add_MESSAGE(oThis.valid_IDValidation(oThis.vID_Selected));
			
			
			if (bibMSG.get_bIsOK())
			{
				
				oThis.oTextStornoBeguendung.setText("");
						
				ownConfirmContainer oConfirm = new ownConfirmContainer(
						new MyE2_String("Sicherheitsabfrage:"),
						new MyE2_String("Beleg stornieren:"),
						bibALL.get_Vector(new MyE2_String("Bitte geben sie den Storno-Grund an !")),
						oThis.oTextStornoBeguendung,
						new ownValidatorStornoBegruendung(),
						new MyE2_String("JA"), 
						new MyE2_String("NEIN"),
						new Extent(250), 
						new Extent(200)	);
					
				oConfirm.set_ActionAgentForOK( 
						new XX_ActionAgent()
							{
								public void executeAgentCode(ExecINFO ooExecInfo) throws myException
								{
									FIBU_LIST_BT_STORNO ooThis = FIBU_LIST_BT_STORNO.this;
									
									RECORD_FIBU recFibu = new RECORD_FIBU(FIBU_LIST_BT_STORNO.this.vID_Selected.get(0));

									ooThis.vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_FIBU SET" +
											" STORNOGRUND="+bibALL.MakeSql(ooThis.oTextStornoBeguendung.getText())+"," +
											" STORNOKUERZEL="+bibALL.MakeSql(bibALL.get_KUERZEL())+"," +
											" STORNIERT='Y',"+
											" BUCHUNGSBLOCK_NR=0,"+
											" STORNIERT_AM="+bibALL.MakeSql(bibALL.get_cDateNOWInverse("-"))+
											" WHERE ID_FIBU IN ("+bibALL.Concatenate(ooThis.vID_Selected, ",", "-1")+")");
									
									//2015-02-13: KORREKTUR-FIBU-BUG: mandant eingefuegt
									ooThis.vSQL_Stack.add("UPDATE "+bibE2.cTO()+".JT_FIBU SET BUCHUNG_GESCHLOSSEN='N' WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND BUCHUNGSBLOCK_NR="+recFibu.get_BUCHUNGSBLOCK_NR_cUF());
									
									
									bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(FIBU_LIST_BT_STORNO.this.vSQL_Stack,true));
									if (bibMSG.get_bHasAlarms())
									{
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Ausführen der Storno-Anweisung !"));
									}
									else
									{
										bibMSG.add_MESSAGE(new MyE2_Info_Message("Stornierung durchgeführt !"));
									}
									/*
									 * auf jeden fall die liste refreshen
									 */
									try
									{
										for (int i=0;i<vID_Selected.size();i++)
										{
											FIBU_LIST_BT_STORNO.this.oNavigationList._REBUILD_ACTUAL_SITE("");
										}
									}
									catch (myException ex)
									{
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Error refreshing List after update!"));
										bibMSG.add_MESSAGE(ex.get_ErrorMessage());
									}
									
								}
							});
				
				oConfirm.show_POPUP_BOX();
						
			}
				


			/*
			 * auf jeden fall die liste refreshen
			 */
			try
			{
				FIBU_LIST_BT_STORNO.this.oNavigationList.Refresh_ComponentMAP(vID_Selected.get(0), E2_ComponentMAP.STATUS_VIEW);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			
		}
	}


	private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
	{

		public ownConfirmContainer(MyE2_String windowtitle,
				MyE2_String texttitle, Vector<MyString> innerTextblock,
				Component innerComponent,
				XX_ActionValidator validatorForOK_Button,
				MyE2_String labelOKButton, MyE2_String labelCancelButton,
				Extent width, Extent height)  throws myException
		{
			super(windowtitle, texttitle, innerTextblock, innerComponent,
					validatorForOK_Button, labelOKButton, labelCancelButton, width, height);
			// TODO Auto-generated constructor stub
		}

		
	}
	
	
	private class ownValidatorStornoBegruendung extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException	
		{
			FIBU_LIST_BT_STORNO ooThis = FIBU_LIST_BT_STORNO.this;
			MyE2_MessageVector ovRueck = new MyE2_MessageVector();
			if (S.isEmpty(ooThis.oTextStornoBeguendung.getText()))
			{
				ovRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte eine Begründung für die Stornierung eingeben !")));
			}
			else if (ooThis.oTextStornoBeguendung.getText().length()>299)
			{
				ovRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Begründung bitte kürzer halten !")));
			}
			return ovRueck;
		}
		
		@Override
		protected MyE2_MessageVector isValid(String unformated) 	throws myException
		{
			return null;
		}
		
	}
}
	

	
	
