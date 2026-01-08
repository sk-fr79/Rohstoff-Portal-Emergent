package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MyMailHelper;
import echopointng.Separator;

public class WF_BT_RETURN_MAIL extends MyE2_Button
{

	private RECORD_LAUFZETTEL 			recLaufzettel = 		null;
	private RECORD_LAUFZETTEL_EINTRAG 	recLaufzettelEintrag = 	null;
	
	private Vector<LaufzettelUser>  	vBeteiligtePersonen = new Vector<LaufzettelUser>();
	
	private String        				cID_LaufZettel = 		null;
	private String        				cID_LaufzettelEintrag = 	null;
	
	private Vector<MyE2_CheckBox>       vCB_Zieladressen = new Vector<MyE2_CheckBox>();
	
	private MyE2_TextArea			    oTF_MailInfos = new MyE2_TextArea("",300,1000,10);
	
	private static String    			cMAIL_BETREFF = new MyE2_String("Antwortmail / Laufzettel").CTrans();
	private static String    			cMAIL_TEXTANFANG = new MyE2_String("Rueck-Information Laufzettel").CTrans();
	
	
	
	public WF_BT_RETURN_MAIL(String IDLaufzettel, String IDLaufzettelEintrag) throws myException
	{
		super(E2_ResourceIcon.get_RI("email_return_small.png"),true);
		this.setToolTipText(new MyString("Antwort-Mail an den Besitzer der Aufgabe schicken").CTrans());
		this.cID_LaufZettel = 			IDLaufzettel;
		this.cID_LaufzettelEintrag =	IDLaufzettelEintrag;		
		
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			WF_BT_RETURN_MAIL oThis = WF_BT_RETURN_MAIL.this;
			
			oThis.vBeteiligtePersonen.removeAllElements();
			oThis.vCB_Zieladressen.removeAllElements();
			oThis.oTF_MailInfos.setText(WF_BT_RETURN_MAIL.cMAIL_TEXTANFANG);
			
			oThis.recLaufzettel = 		new RECORD_LAUFZETTEL(oThis.cID_LaufZettel);
			oThis.recLaufzettelEintrag = new RECORD_LAUFZETTEL_EINTRAG(oThis.cID_LaufzettelEintrag);
			
			new LaufzettelUser(oThis.recLaufzettel.get_ID_USER_SUPERVISOR_cUF_NN(""),				new MyE2_String("Laufzettel <Supervisor>: "));
			new LaufzettelUser(oThis.recLaufzettel.get_ID_USER_BESITZER_cUF_NN(""),					new MyE2_String("Laufzettel <Besitzer>: "));
			new LaufzettelUser(oThis.recLaufzettel.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN(""),		new MyE2_String("Laufzettel <Abgeschlossen von>: "));
			
			new LaufzettelUser(oThis.recLaufzettelEintrag.get_ID_USER_BESITZER_cUF_NN(""),			new MyE2_String("Laufzetteleintrag <Eintrag von>: "));
			new LaufzettelUser(oThis.recLaufzettelEintrag.get_ID_USER_BEARBEITER_cUF_NN(""),		new MyE2_String("Laufzetteleintrag <Zu erledigen von>: "));
			new LaufzettelUser(oThis.recLaufzettelEintrag.get_ID_USER_ABGESCHLOSSEN_VON_cUF_NN(""),	new MyE2_String("Laufzetteleintrag <Abgeschlossen von>: "));

			//hier die validierung, wer darf, implizit
			String 	cID_USER_ICH = bibALL.get_RECORD_USER().get_ID_USER_cUF();
			boolean bIchDarf = false;
			
			if (bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES() || 
				bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES())
			{
				bIchDarf = true;				
			}
			else
			{
				for (int i=0;i<oThis.vBeteiligtePersonen.size();i++)
				{
					if (oThis.vBeteiligtePersonen.get(i).get_USER().get_ID_USER_cUF().equals(cID_USER_ICH))
					{
						bIchDarf = true;
						break;
					}
				}
			}
			
			if (bIchDarf)
			{
				new ownPopupWindow();
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Rückantwort-eMail darf nur von beteiligen Personen gesendet werden !")));
			}
		}
		
	}
	
	
	private class ownPopupWindow extends E2_BasicModuleContainer
	{

		public ownPopupWindow() throws myException
		{
			super();
			
			WF_BT_RETURN_MAIL oThis = WF_BT_RETURN_MAIL.this;
			MyE2_Grid         oGrid = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			Vector<String> vNoDouble = new Vector<String>();
			
			oGrid.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie die Mail-Empfänger aus:")),  MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2,null,3,null));
			oGrid.add(new Separator(), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2,null,3,null));
			
			for (int i=0;i<oThis.vBeteiligtePersonen.size();i++)
			{
				RECORD_USER recUser = oThis.vBeteiligtePersonen.get(i).get_USER();
				
				if (!vNoDouble.contains(recUser.get_ID_USER_cUF()))
				{
					vNoDouble.add(recUser.get_ID_USER_cUF());
					
					MyE2_CheckBox oCB = new MyE2_CheckBox(true,false);
					oCB.EXT().set_O_PLACE_FOR_EVERYTHING(recUser);
					oThis.vCB_Zieladressen.add(oCB);
					
					oGrid.add(oCB, MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
					oGrid.add(new MyE2_Label(oThis.vBeteiligtePersonen.get(i).get_Beschreibung()), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
					oGrid.add(new MyE2_Label(recUser.get_EMAIL_cUF_NN("<eMail>")), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
				}
			}
			
			oGrid.add(new Separator(), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_10,null,3,null));
			oGrid.add(new MyE2_Label(new MyE2_String("Bemerkungen für die Mail:")),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2,null,3,null));
			oGrid.add(oThis.oTF_MailInfos,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_10,null,3,null));
			oGrid.add(new E2_ComponentGroupHorizontal(0,
					new  MyE2_Button(new MyE2_String("Senden"),new MyE2_String("Mails senden"), new ownActionSendMails()),
					new  MyE2_Button(new MyE2_String("Abbruch"),new MyE2_String("Abbruch und Fenster schliessen"), new ownActionClose()),
							E2_INSETS.I_0_0_5_0), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2,null,3,null));
			
			this.add(oGrid, E2_INSETS.I_2_2_2_2);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(450), new MyE2_String("Antwortmail aus Laufzettel ..."));
		}

		
		private class ownActionSendMails extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				WF_BT_RETURN_MAIL oThis = WF_BT_RETURN_MAIL.this;
				
				String cMailText1 = S.NN(oThis.oTF_MailInfos.getText())+"\n";
				
				WF_MessageHelper  oMSG_HLP = new WF_MessageHelper(oThis.recLaufzettel, oThis.recLaufzettelEintrag);
				
				cMailText1 += oMSG_HLP.getMessageText();
				
				String cAbsender = bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("");
				
				if (S.isEmpty(cAbsender))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der angemeldete Benutzer hat keine eMail-Adresse !!!")));
					return;
				}
				
				
				int iZaehler = 0;
				
				for (int i=0; i<oThis.vCB_Zieladressen.size();i++ )
				{
					if (oThis.vCB_Zieladressen.get(i).isSelected())
					{
						RECORD_USER  recUserToSend=  (RECORD_USER)oThis.vCB_Zieladressen.get(i).EXT().get_O_PLACE_FOR_EVERYTHING();
						
						if (S.isFull(recUserToSend.get_EMAIL_cUF_NN("")))
						{
							MyMailHelper  oMailer = new MyMailHelper(cAbsender, recUserToSend.get_EMAIL_cUF_NN(""), WF_BT_RETURN_MAIL.cMAIL_BETREFF, cMailText1, null);
							if (oMailer.doSendWith_REAL_Adress())
							{
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("eMail erfolgreich: ",true,recUserToSend.get_EMAIL_cUF_NN(""),false)));
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("eMail FEHLER: ",true,recUserToSend.get_EMAIL_cUF_NN(""),false)));	
							}
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Benutzer hat keine Mail-Adresse: ",true,recUserToSend.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")),false)));
						}
						iZaehler++;
					}
				}
				
				if (iZaehler == 0)
				{
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Sie haben keine Empfänger ausgewählt !")));
				}
				else
				{
					ownPopupWindow.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			}
		}
		
		
		private class ownActionClose extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownPopupWindow.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}
		
		
	}
	
	
	
	private class LaufzettelUser 
	{
		private RECORD_USER  	recUser = 		null;
		private MyString 		cBeschreibung = null;
		
		public LaufzettelUser(String cID_USER, MyString Beschreibung) throws myException
		{
			super();
			if (S.isFull(cID_USER))
			{
				this.recUser = new RECORD_USER(cID_USER);
				this.cBeschreibung = Beschreibung;
				WF_BT_RETURN_MAIL.this.vBeteiligtePersonen.add(this);
			}
		}
		
		public RECORD_USER get_USER()
		{
			return recUser;
		}

		public MyString get_Beschreibung()
		{
			return cBeschreibung;
		}
		
	}

}