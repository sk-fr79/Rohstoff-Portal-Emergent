package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FSZ_LIST_BT_AdoHocMeldung extends MyE2_Button
{

	public FSZ_LIST_BT_AdoHocMeldung()
	{
		super(new MyE2_String("Direkt-Nachricht"));
		this.setFont(new E2_FontPlain(-2));
		this.add_GlobalValidator(new ownValidator());
		this.add_oActionAgent(new ownActionAgent());
		
	}

	
	//hier umdrehen: im status bearbeiten inaktiv und umgekehrt
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(!enabled);
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FSZ_LIST_BT_AdoHocMeldung oButton = new FSZ_LIST_BT_AdoHocMeldung();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}
	
	
	private class ownValidator extends XX_ActionValidator
	{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)	throws myException
		{
			E2_ComponentMAP  oMap = FSZ_LIST_BT_AdoHocMeldung.this.EXT().get_oComponentMAP();
			
			boolean bKannNachrichtSenden = false;
			
			if (oMap.get_bdActualDBValue("ID_USER", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				bKannNachrichtSenden = true;
			}
			if (oMap.get_bdActualDBValue("ID_USER_ERSATZ", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				bKannNachrichtSenden = true;
			}
			if (oMap.get_bdActualDBValue("ID_USER_SACHBEARBEITER", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				bKannNachrichtSenden = true;
			}
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			if (!bKannNachrichtSenden)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt keinen Adressaten fuer die Nachricht !")));
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException
		{
			return null;
		}
		
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			//bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("<Funktion ist noch nicht implementiert !>")));
			new ownContainer4Message();
			
		}
	}
	
	
	private class ownContainer4Message extends E2_BasicModuleContainer
	{

		private MyE2_TextField_DatePOPUP_OWN  oTF_Date = 			new MyE2_TextField_DatePOPUP_OWN(bibALL.get_cDateNOW(), 100,true);
		private MyE2_CheckBox                 cbSofortNachricht = 	new MyE2_CheckBox(new MyE2_String("Sofort anzeigen"));
		
		private Vector<MyE2_CheckBox> 		  vUsersToSend = 		new Vector<MyE2_CheckBox>();
		
		private MyE2_Button 			      btSend = new MyE2_Button(new MyE2_String("Nachrichten senden"));
		private MyE2_Button 			      btClose = new MyE2_Button(new MyE2_String("Fenster schliessen"));
		
		private MyE2_TextField                tfBetreff = new MyE2_TextField("", 600,100);
		private MyE2_TextArea                 tfNachricht = new MyE2_TextArea("", 600, 1000, 10);
		
		
		
		public ownContainer4Message() throws myException
		{
			super();
			E2_ComponentMAP  oMap = FSZ_LIST_BT_AdoHocMeldung.this.EXT().get_oComponentMAP();
			
//			this.cbSofortNachricht.add_oActionAgent(new XX_ActionAgent()
//			{
//				@Override
//				public void executeAgentCode(ExecINFO oExecInfo) throws myException
//				{
//					if (ownContainer4Message.this.cbSofortNachricht.isSelected())
//					{
//						ownContainer4Message.this.oTF_Date.set_bEnabled_For_Edit(false);
//					}
//					else
//					{
//						ownContainer4Message.this.oTF_Date.set_bEnabled_For_Edit(true);
//					}
//				}
//			});
			
			
			this.btClose.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					ownContainer4Message.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
			
			
			this.btSend.add_oActionAgent(new ownActionSendNachricht());
			
			
			RECORD_ADRESSE_INFO  	recInfo =		new RECORD_ADRESSE_INFO(oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			RECORD_ADRESSE  		recAdresse = 	recInfo.get_UP_RECORD_ADRESSE_id_adresse();

			boolean  bIchBinAuchDabei = false;
			
			if (oMap.get_bdActualDBValue("ID_USER", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				RECORD_USER  recUser = new RECORD_USER(oMap.get_bdActualDBValue("ID_USER", BigDecimal.ZERO,  BigDecimal.ZERO).longValue());
				MyE2_CheckBox oCB = new MyE2_CheckBox(recUser.get_VORNAME_cUF_NN("<vorname>")+" "+recUser.get_NAME1_cUF_NN("<name1>"));
				oCB.EXT().set_O_PLACE_FOR_EVERYTHING(recUser);
				oCB.EXT().set_C_MERKMAL(recUser.get_ID_USER_cUF());
				oCB.EXT().set_C_MERKMAL2(recAdresse.get_ID_ADRESSE_cUF());
				oCB.setSelected(true);
				this.vUsersToSend.add(oCB);
				
				if (recUser.get_ID_USER_cUF().equals(bibALL.get_RECORD_USER().get_ID_USER_cUF()))
				{
					bIchBinAuchDabei =true;
				}
			}
			if (oMap.get_bdActualDBValue("ID_USER_ERSATZ", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				RECORD_USER  recUser = new RECORD_USER(oMap.get_bdActualDBValue("ID_USER_ERSATZ", BigDecimal.ZERO,  BigDecimal.ZERO).longValue());
				MyE2_CheckBox oCB = new MyE2_CheckBox(recUser.get_VORNAME_cUF_NN("<vorname>")+" "+recUser.get_NAME1_cUF_NN("<name1>"));
				oCB.EXT().set_O_PLACE_FOR_EVERYTHING(recUser);
				oCB.EXT().set_C_MERKMAL(recUser.get_ID_USER_cUF());
				oCB.EXT().set_C_MERKMAL2(recAdresse.get_ID_ADRESSE_cUF());
				oCB.setSelected(true);
				this.vUsersToSend.add(oCB);

				if (recUser.get_ID_USER_cUF().equals(bibALL.get_RECORD_USER().get_ID_USER_cUF()))
				{
					bIchBinAuchDabei =true;
				}
			}
			if (oMap.get_bdActualDBValue("ID_USER_SACHBEARBEITER", BigDecimal.ZERO,  BigDecimal.ZERO).intValue()!=0)
			{
				RECORD_USER  recUser = new RECORD_USER(oMap.get_bdActualDBValue("ID_USER_SACHBEARBEITER", BigDecimal.ZERO,  BigDecimal.ZERO).longValue());
				MyE2_CheckBox oCB = new MyE2_CheckBox(recUser.get_VORNAME_cUF_NN("<vorname>")+" "+recUser.get_NAME1_cUF_NN("<name1>"));
				oCB.EXT().set_O_PLACE_FOR_EVERYTHING(recUser);
				oCB.EXT().set_C_MERKMAL(recUser.get_ID_USER_cUF());
				oCB.EXT().set_C_MERKMAL2(recAdresse.get_ID_ADRESSE_cUF());
				oCB.setSelected(true);
				this.vUsersToSend.add(oCB);

				if (recUser.get_ID_USER_cUF().equals(bibALL.get_RECORD_USER().get_ID_USER_cUF()))
				{
					bIchBinAuchDabei =true;
				}

			}


			if (!bIchBinAuchDabei)
			{
				//der bediener bekommt auch eine checkbox
				RECORD_USER  recUser = new RECORD_USER(bibALL.get_RECORD_USER().get_ID_USER_cUF());
				MyE2_CheckBox oCB = new MyE2_CheckBox(recUser.get_VORNAME_cUF_NN("<vorname>")+" "+recUser.get_NAME1_cUF_NN("<name1>"));
				oCB.EXT().set_O_PLACE_FOR_EVERYTHING(recUser);
				oCB.EXT().set_C_MERKMAL(recUser.get_ID_USER_cUF());
				oCB.EXT().set_C_MERKMAL2(recAdresse.get_ID_ADRESSE_cUF());
				oCB.setSelected(true);
				this.vUsersToSend.add(oCB);

				if (recUser.get_ID_USER_cUF().equals(bibALL.get_RECORD_USER().get_ID_USER_cUF()))
				{
					bIchBinAuchDabei =true;
				}
			}
			
			
			
			
			
			this.tfBetreff.setText("Infotext zu Adresse:  "+recAdresse.get_NAME1_cUF_NN("")+" "+recAdresse.get_ORT_cF_NN(""));
			this.tfNachricht.setText(recInfo.get_TEXT_cUF_NN("<Textinhalt>"));
			
			
			MyE2_Grid oGridInhalt = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			oGridInhalt.add_raw(new MyE2_Label(new MyE2_String("Nachricht versenden ..."), MyE2_Label.STYLE_TITEL_BIG()), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_10_2_10,oGridInhalt.getSize()));
			
			oGridInhalt.add_raw(new MyE2_Label(new MyE2_String("Betreff:"),MyE2_Label.STYLE_SMALL_ITALIC()), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_0,2));
			oGridInhalt.add_raw(this.tfBetreff, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_2,2));
			oGridInhalt.add_raw(new MyE2_Label(new MyE2_String("Nachricht:"),MyE2_Label.STYLE_SMALL_ITALIC()), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_0,2));
			oGridInhalt.add_raw(this.tfNachricht, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_2,2));
			
			
			oGridInhalt.add_raw(this.cbSofortNachricht, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_10_2_10,1));
			oGridInhalt.add_raw(new E2_ComponentGroupHorizontal_NG(new MyE2_Label(new MyE2_String("Anzeigen ab ")),this.oTF_Date,E2_INSETS.I_2_10_2_10), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_2,1));

			
			
			oGridInhalt.add_raw(new MyE2_Label(new MyE2_String("Verteiler: "),MyE2_Label.STYLE_SMALL_ITALIC()), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_10_2_0,oGridInhalt.getSize()));
			
			MyE2_Grid oGridVerteiler = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			for (int i=0;i<this.vUsersToSend.size();i++)
			{
				oGridVerteiler.add_raw(this.vUsersToSend.get(i), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_2,1));
			}
			oGridInhalt.add_raw(oGridVerteiler, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_2_2_10,oGridInhalt.getSize()));
			
			oGridInhalt.add_raw(new E2_ComponentGroupHorizontal_NG(this.btSend, this.btClose, E2_INSETS.I_0_0_10_0), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_2_10_2_10,oGridInhalt.getSize()));
			
			
			this.add(oGridInhalt, E2_INSETS.I_5_5_5_5);

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(550), new MyE2_String("Nachricht versenden"));
			
			
		}
		
		
		
		private class ownActionSendNachricht extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownContainer4Message oThis = ownContainer4Message.this;
				
				String 	cDateAnzeigen = oThis.oTF_Date.get_oDBFormatedDateFromTextField();
				boolean bSofort = 		oThis.cbSofortNachricht.isSelected();
				
				for (int i=0;i<oThis.vUsersToSend.size();i++)
				{
					if (oThis.vUsersToSend.get(i).isSelected())
					{
						RECORD_USER recUSER = (RECORD_USER)oThis.vUsersToSend.get(i).EXT().get_O_PLACE_FOR_EVERYTHING();
//						MESSAGE_Handler hMessage = new MESSAGE_Handler();
//						
//						
//						hMessage.saveMessage(	S.NN(oThis.tfBetreff.getText()), 
//															S.NN(oThis.tfNachricht.getText()), 
//															oThis.vUsersToSend.get(i).EXT().get_C_MERKMAL(), 
//															bSofort, 
//															cDateAnzeigen, 
//															"", 
//															oThis.vUsersToSend.get(i).EXT().get_C_MERKMAL2(), 
//															E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,
//															E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST);
						
						MESSAGE_Entry msgEntry = new MESSAGE_Entry()
							.set_Titel(S.NN(oThis.tfBetreff.getText()))
							.set_Nachricht(S.NN(oThis.tfNachricht.getText()))
							.add_idEmpfaenger(oThis.vUsersToSend.get(i).EXT().get_C_MERKMAL())
							.set_Sofortanzeige(bSofort)
							.set_DtAnzeigeAb(cDateAnzeigen)
							.add_Target(new MESSAGE_Entry_Target(oThis.vUsersToSend.get(i).EXT().get_C_MERKMAL2(),E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,null,null))
							.set_Kategorie(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST);
					
						new MESSAGE_Handler().saveMessage(msgEntry);
					
						
						
						
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Nachricht gesendet an: ",true,recUSER.get_VORNAME_cUF_NN("")+" "+recUSER.get_NAME1_cUF_NN(""),false)));
						
					}
				}
				
				
			}
		}
		
		
		
		
	}
	
}
