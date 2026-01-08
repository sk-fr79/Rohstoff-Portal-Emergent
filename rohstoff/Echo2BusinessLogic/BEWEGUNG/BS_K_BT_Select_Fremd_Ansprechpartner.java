package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MITARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MITARBEITER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;



public class BS_K_BT_Select_Fremd_Ansprechpartner extends MyE2_Button
{

	private long 					l_ID_ADRESSE = -1;
	private Vector<MyE2_CheckBox>	vCB_Mitarbeiter = new Vector<MyE2_CheckBox>();
	private Vector<MyE2_CheckBox>	vCB_MailAdressFirma = new Vector<MyE2_CheckBox>();
	private Vector<MyE2_CheckBox>	vCB_MailAdressMitarbeiter = new Vector<MyE2_CheckBox>();
	
	private ActionAgent_RadioFunction_CheckBoxList oRadioMitarbeiter = new ActionAgent_RadioFunction_CheckBoxList(true);
	private ActionAgent_RadioFunction_CheckBoxList oRadioMail = new ActionAgent_RadioFunction_CheckBoxList(true);


	public BS_K_BT_Select_Fremd_Ansprechpartner()
	{
		super(E2_ResourceIcon.get_RI("person_popup.png"), true);
		
		this.add_oActionAgent(new ownActionAgent());
		
	}


	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_ComponentMAP  oMap = BS_K_BT_Select_Fremd_Ansprechpartner.this.EXT().get_oComponentMAP();
			long lID_ADRESS_FIRMA = oMap.get_LActualDBValue("ID_ADRESSE", true, true, new Long(-1)).longValue();
			
			if (lID_ADRESS_FIRMA == -1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Firmenadresse laden !!"));
				return;
			}
			
			BS_K_BT_Select_Fremd_Ansprechpartner.this.l_ID_ADRESSE = lID_ADRESS_FIRMA;
		
			new ownBasicContainer4PopUP();
			
		}
	}
	

	
	private class ownBasicContainer4PopUP extends E2_BasicModuleContainer
	{
		private RECLIST_ADRESSE  	recListMitarbeiter = new RECLIST_ADRESSE();
		private MyE2_Row    		oRow4Content = new MyE2_Row();

		public ownBasicContainer4PopUP() throws myException
		{
			super();
			BS_K_BT_Select_Fremd_Ansprechpartner oThis = BS_K_BT_Select_Fremd_Ansprechpartner.this;
			
			/*
			 * 2 bereiche aufbauen: 1. Bereich: Alle Mitarbeiter der Firma
			 * 						2. Bereich: Alle Mailadresse der Firma/des zuletzt gewaehlten Mitarbeiters
			 */
			
			RECORD_ADRESSE  recAdressFirma = new RECORD_ADRESSE(oThis.l_ID_ADRESSE);
			
			recAdressFirma.get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_basis().DoAnyThing(new RECLIST_MITARBEITER.DoAnyThingWithAll()
			{
				@Override
				public void doAnyThingWith(String hashKey,RECORD_MITARBEITER oRecMitarbeiter) throws myException
				{
					RECORD_ADRESSE recMitarbeiter = oRecMitarbeiter.get_UP_RECORD_ADRESSE_id_adresse_mitarbeiter();
					if (recMitarbeiter.is_AKTIV_YES())
					{
						ownBasicContainer4PopUP.this.recListMitarbeiter.ADD(recMitarbeiter,true);
					}
				}
			});
			
			oThis.vCB_Mitarbeiter.removeAllElements();
			oThis.vCB_MailAdressFirma.removeAllElements();
			oThis.vCB_MailAdressMitarbeiter.removeAllElements();
			
			for (int i=0;i<this.recListMitarbeiter.get_vKeyValues().size();i++)
			{
				oThis.vCB_Mitarbeiter.add(new ownCheckBoxMitarbeiter(this.recListMitarbeiter.get(i),this));
			}
			
			for (int i=0;i<recAdressFirma.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();i++)
			{
				MyE2_CheckBox oCB_MA = new MyE2_CheckBox(recAdressFirma.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(i).get_EMAIL_cUF_NN("-")+"  (FA)",MyE2_CheckBox.STYLE_SMALL_BOLD());
				oCB_MA.EXT().set_C_MERKMAL(recAdressFirma.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(i).get_EMAIL_cUF_NN("-"));
				oThis.vCB_MailAdressFirma.add(oCB_MA);
			}
			
			this.add(this.oRow4Content, E2_INSETS.I_5_5_5_5);
			this.build_Content();
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(400), new MyE2_String("Mitarbeiter aussuchen"));
		}

		
		
		public void build_Content() throws myException
		{
			this.oRow4Content.removeAll();
			
			MyE2_Column  oColMitarbeiter = 	new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
			MyE2_Column  oColMailAdress = 	new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
			
			this.oRow4Content.add(oColMitarbeiter, E2_INSETS.I_5_5_5_5);
			this.oRow4Content.add(oColMailAdress, E2_INSETS.I_5_5_5_5);
			
			BS_K_BT_Select_Fremd_Ansprechpartner oThis = BS_K_BT_Select_Fremd_Ansprechpartner.this;

			for (int i=0;i<oThis.vCB_Mitarbeiter.size();i++)
			{
				oColMitarbeiter.add(oThis.vCB_Mitarbeiter.get(i));
			}

			for (int i=0;i<oThis.vCB_MailAdressMitarbeiter.size();i++)
			{
				oColMailAdress.add(oThis.vCB_MailAdressMitarbeiter.get(i));
			}

			for (int i=0;i<oThis.vCB_MailAdressFirma.size();i++)
			{
				oColMailAdress.add(oThis.vCB_MailAdressFirma.get(i));
			}
			
			oThis.oRadioMail.get_vSammler().removeAllElements();
			oThis.oRadioMitarbeiter.get_vSammler().removeAllElements();
			
			oThis.oRadioMail.add_CheckBox(oThis.vCB_MailAdressFirma);
			oThis.oRadioMail.add_CheckBox(oThis.vCB_MailAdressMitarbeiter);
			oThis.oRadioMitarbeiter.add_CheckBox(oThis.vCB_Mitarbeiter);

			MyE2_Button btOK = new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"),true);
			
			btOK.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					//die angkreuzten Checkboxen raussuchen
					BS_K_BT_Select_Fremd_Ansprechpartner oThis = BS_K_BT_Select_Fremd_Ansprechpartner.this;
					
					String cMailAdress = null;
					String cNameAnsprech = null;
					
					for (MyE2_CheckBox oCB:oThis.vCB_MailAdressFirma)
					{
						if (oCB.isSelected())
						{
							cMailAdress = oCB.EXT().get_C_MERKMAL();
						}
					}
					for (MyE2_CheckBox oCB:oThis.vCB_MailAdressMitarbeiter)
					{
						if (oCB.isSelected())
						{
							cMailAdress = oCB.EXT().get_C_MERKMAL();
						}
					}
					
					for (MyE2_CheckBox oCB:oThis.vCB_Mitarbeiter)
					{
						if (oCB.isSelected())
						{
							RECORD_ADRESSE recMitarbeiter = (RECORD_ADRESSE)oCB.EXT().get_O_PLACE_FOR_EVERYTHING();
							
							cNameAnsprech = "";
							if (recMitarbeiter.get_UP_RECORD_ANREDE_id_anrede()!=null)
							{
								cNameAnsprech += recMitarbeiter.get_UP_RECORD_ANREDE_id_anrede().get_ANREDE_cF_NN("");
							}
							cNameAnsprech += (" "+recMitarbeiter.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")));
							
							cNameAnsprech = cNameAnsprech.trim();
						}
					}
					
					
					if (S.isFull(cNameAnsprech))
					{
						oThis.EXT().get_oComponentMAP().get__DBComp("NAME_ANSPRECHPARTNER").set_cActualMaskValue(cNameAnsprech);
					}
					if (S.isFull(cMailAdress))
					{
						oThis.EXT().get_oComponentMAP().get__DBComp("EMAIL_AUF_FORMULAR").set_cActualMaskValue(cMailAdress);
					}
					
					ownBasicContainer4PopUP.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
			
			this.oRow4Content.add(btOK, E2_INSETS.I_5_5_5_5);
   
			
		}
		
	}
	
	
	private class ownCheckBoxMitarbeiter extends MyE2_CheckBox
	{
				
		public ownCheckBoxMitarbeiter(RECORD_ADRESSE recAdresseMitarbeiter, ownBasicContainer4PopUP oContainer) throws myException
		{
			super(recAdresseMitarbeiter.get___KETTE(bibALL.get_Vector("VORNAME","NAME1","NAME2")), MyE2_CheckBox.STYLE_SMALL_ITALIC());
			this.EXT().set_O_PLACE_FOR_EVERYTHING(recAdresseMitarbeiter);
			this.EXT().set_O_PLACE_FOR_EVERYTHING2(oContainer);              //wird benoetigt fuers refreshen nach klick auf mitarbeiter 
			
			
			/*
			 * actionAgent laedt die eMailAdressen der Mitarbeiter
			 */
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					BS_K_BT_Select_Fremd_Ansprechpartner 	oThis = 			BS_K_BT_Select_Fremd_Ansprechpartner.this;
					ownCheckBoxMitarbeiter 					oCB_Mitarbeiter = 	(ownCheckBoxMitarbeiter)execInfo.get_MyActionEvent().getSource();
					
					oThis.vCB_MailAdressMitarbeiter.removeAllElements();
					
					if (oCB_Mitarbeiter.isSelected())   //wenn selected, dann die mitarbeiter-eMails reinholen
					{
						RECORD_ADRESSE  recMitarbeiter = (RECORD_ADRESSE)oCB_Mitarbeiter.EXT().get_O_PLACE_FOR_EVERYTHING();
						
						for (int i=0;i<recMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get_vKeyValues().size();i++)
						{
							MyE2_CheckBox oCB_MA = new MyE2_CheckBox(recMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(i).get_EMAIL_cUF_NN("-")+" (MA)",MyE2_CheckBox.STYLE_SMALL_ITALIC());
							oCB_MA.EXT().set_C_MERKMAL(recMitarbeiter.get_DOWN_RECORD_LIST_EMAIL_id_adresse().get(i).get_EMAIL_cUF_NN("-"));
							oThis.vCB_MailAdressMitarbeiter.add(oCB_MA);
						}
					}
					
					((ownBasicContainer4PopUP)oCB_Mitarbeiter.EXT().get_O_PLACE_FOR_EVERYTHING2()).build_Content();
				}
			});
		}
	}
	
	
	
}
