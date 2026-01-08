package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_ComponentMap;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.myDataRecordCopySQLString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.valid_KopiereNurBelegeMitAktiveAdressen;
import echopointng.Separator;

public class FU_LIST_BT_FUHRE_COPY_FUHRE extends MyE2_Button
{

	private E2_NavigationList 			 	oNaviList = null;
	private E2_BasicModuleContainer_MASK 	oMaskContainer = null;
	
	private Vector<String>     				vIDsTo_Copy = new Vector<String>();
	
	//2011-03-27: fuhrenkopie erweitert auf einstellungen in der zielkopie
	private ownGridWithSetting          	gridWithSettings = null;
	
	
	public FU_LIST_BT_FUHRE_COPY_FUHRE(E2_NavigationList onavigationList, E2_BasicModuleContainer_MASK omaskContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("copy.png") , true);
		
		this.oNaviList = onavigationList;
		this.oMaskContainer = omaskContainer;
		
		this.add_oActionAgent(new ownActionAgentStartKopieDialog());
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER,"KOPIERE_FUHRE"));
		this.add_IDValidator(new FU__Validator_Fuhre_ist_gloescht_ODER_ist_storniert());
		
		this.setToolTipText(new MyE2_String("Die gewählte Fuhre kopieren (n-fach)").CTrans());
		
		//aenderung 2010-12-22: keine kopie von inaktiven adressen
		this.add_IDValidator(new ownValidatior());
	}
	
	
	
	//aenderung 2010-12-22: keine kopie von inaktiven adressen
	private class ownValidatior extends valid_KopiereNurBelegeMitAktiveAdressen
	{

		@Override
		public VectorSingle SammleAdressIDs(String cID_BelegToCopy)		throws myException 
		{
			VectorSingle vRueck = new VectorSingle();
			
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_BelegToCopy);
					
			vRueck.add(recFuhre.get_ID_ADRESSE_FREMDAUFTRAG_cUF_NN("0"));
			vRueck.add(recFuhre.get_ID_ADRESSE_SPEDITION_cUF_NN("0"));
			vRueck.add(recFuhre.get_ID_ADRESSE_START_cUF_NN("0"));
			vRueck.add(recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("0"));

			RECLIST_VPOS_TPA_FUHRE_ORT  reclistOrte = 
				recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
			
			if (reclistOrte != null)
			{
				Iterator<RECORD_VPOS_TPA_FUHRE_ORT> iterOrte = reclistOrte.values().iterator();
				
				while (iterOrte.hasNext())
				{
					RECORD_VPOS_TPA_FUHRE_ORT recOrt = iterOrte.next();
					
					vRueck.add(recOrt.get_ID_ADRESSE_cUF_NN("0"));
				}
			}
			return vRueck;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated)		throws myException 
		{
			return null;
		}
		
	}

	
	
	
	private class ownGridWithSetting extends MyE2_Grid
	{
		private MyE2_SelectField   				oSelFieldAnzahl = 			new MyE2_SelectField();
		private MyE2_CheckBox      				cbErsteOeffnen = 			new MyE2_CheckBox(new MyE2_String("Erste Kopie gleich öffnen"));
		
		private MyE2_CheckBox      				cbDatumSetzen = 			new MyE2_CheckBox(new MyE2_String("Datum setzen (oder leeren)"));
		private MyE2_TextField_DatePOPUP_OWN    oTfSetDatumZumEintragen = 	null;

		private MyE2_CheckBox                   cbPlanMengeKopieren =  		new MyE2_CheckBox(new MyE2_String("Plan-Mengen kopieren"));
		
		private MyE2_CheckBox                   cbSORTEKopieren =  			new MyE2_CheckBox(new MyE2_String("Sorte kopieren"));
		private MyE2_CheckBox                   cb_EK_KONTRAKT_Kopieren = 	new MyE2_CheckBox(new MyE2_String("EK-Kontrakt kopieren"));
		private MyE2_CheckBox                   cb_VK_KONTRAKT_Kopieren = 	new MyE2_CheckBox(new MyE2_String("VK-Kontrakt kopieren"));
		
		private MyE2_CheckBox                   cb_LKW_Kennzkopieren = 	new MyE2_CheckBox(new MyE2_String("LKW/Anhänger-Kennzeichen kopieren"));

		private MyE2_Button  					btEinstellungenSpeichern = new MyE2_Button(new MyE2_String("Einstellungen speichern"));
		
		
		private E2_UserSetting_ComponentMap     oUserSettings = new E2_UserSetting_ComponentMap(FU___CONST.HASH_4_SAVE_KOPIE_SETTINGS_FUHRE);
		
		public ownGridWithSetting() throws myException
		{
			super(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

			String[] cCopy = {"1","2","3","4","5","6","7","8","9","10"};
			this.oSelFieldAnzahl.set_ListenInhalt(cCopy, false);
			this.oSelFieldAnzahl.set_ActiveInhalt_or_FirstInhalt("1");
			
			this.oTfSetDatumZumEintragen = new MyE2_TextField_DatePOPUP_OWN("", 100,true);

			oUserSettings.put("ANZAHL_KOPIE", 				oSelFieldAnzahl);
			oUserSettings.put("ERSTE_KOPIE_OEFFNEN", 		cbErsteOeffnen);
			oUserSettings.put("DATUM_SETZEN", 				cbDatumSetzen);
			oUserSettings.put("DATUM", 						oTfSetDatumZumEintragen.get_oTextField());
			oUserSettings.put("MENGEN_KOPIEREN", 			cbPlanMengeKopieren);
			oUserSettings.put("SORTE_KOPIEREN", 			cbSORTEKopieren);
			oUserSettings.put("EK_KONTRAKT_KOPIEREN", 		cb_EK_KONTRAKT_Kopieren);
			oUserSettings.put("VK_KONTRAKT_KOPIEREN", 		cb_VK_KONTRAKT_Kopieren);
			oUserSettings.put("LKW_KENNZEICHEN_KOPIEREN", 	cb_LKW_Kennzkopieren);
			
			oUserSettings.RESTORE_WERTE();
			
			this.btEinstellungenSpeichern.setFont(new E2_FontBoldItalic(-2));
			this.btEinstellungenSpeichern.setToolTipText(new MyE2_String("Die Einstellungen der Kopiermaske in diesem Zustand speichern ...").CTrans());
			this.btEinstellungenSpeichern.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					ownGridWithSetting.this.saveSettings();
				}
			});
			
			
			Insets  iHelp = new Insets(2,2,5,6);

			E2_ComponentGroupHorizontal_NG  oGroup = new E2_ComponentGroupHorizontal_NG(
							new MyE2_Label(new MyE2_String("Anzahl:"),MyE2_Label.STYLE_TITEL_BIG()),
							this.oSelFieldAnzahl,LayoutDataFactory.get_GridLayoutGridLeftMID(E2_INSETS.I_0_0_10_0));

			String cInfoOldSorte = ""; 
			String cInfoOldKontraktEK = "";
			String cInfoOldKontraktVK = "";
			String cInfoOldPlanmengen = "";
			String cInfoLKW = "";
			
			if (FU_LIST_BT_FUHRE_COPY_FUHRE.this.vIDsTo_Copy.size()==1)
			{
				RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU_LIST_BT_FUHRE_COPY_FUHRE.this.vIDsTo_Copy.get(0));
				if (recFuhre.get_UP_RECORD_ARTIKEL_id_artikel()!=null)
				{
					cInfoOldSorte = recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get___KETTE(bibALL.get_Vector("ANR1","ARTBEZ1"),"","< "," >",",");
				}
				if (recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek()!=null)
				{
					cInfoOldKontraktEK = recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get___KETTE(bibALL.get_Vector(RECORD_VPOS_KON.FIELD__ANR1,RECORD_VPOS_KON.FIELD__ARTBEZ1),"","< "," >",",");
					cInfoOldKontraktEK += recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(bibALL.get_Vector(RECORD_ADRESSE.FIELD__NAME1),"","< "," >"," ");
				}
				if (recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk()!=null)
				{
					cInfoOldKontraktVK = recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get___KETTE(bibALL.get_Vector(RECORD_VPOS_KON.FIELD__ANR1,RECORD_VPOS_KON.FIELD__ARTBEZ1),"","< "," >"," ");
					cInfoOldKontraktVK += recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get_UP_RECORD_VKOPF_KON_id_vkopf_kon().get_UP_RECORD_ADRESSE_id_adresse().get___KETTE(bibALL.get_Vector(RECORD_ADRESSE.FIELD__NAME1),"","< "," >"," ");
				}
				cInfoOldPlanmengen = new MyE2_String("Planmenge Ladeseite: ").CTrans()+"< "+recFuhre.get_ANTEIL_PLANMENGE_LIEF_cF_NN(" - ")+" >"+" ---  "+new MyE2_String("Abladeseite: ").CTrans()+"< "+recFuhre.get_ANTEIL_PLANMENGE_ABN_cF_NN(" - ")+" >";
				cInfoLKW = recFuhre.get___KETTE(bibALL.get_Vector(RECORD_VPOS_TPA_FUHRE.FIELD__TRANSPORTKENNZEICHEN,RECORD_VPOS_TPA_FUHRE.FIELD__ANHAENGERKENNZEICHEN),"","< "," >"," ");
			}
			
//			this.add_raw(new MyE2_Label(new MyE2_String("Anzahl:"),MyE2_Label.STYLE_TITEL_BIG()), 	LayoutDataFactory.get_GridLayoutGridLeftTOP(iHelp,1));
//			this.add_raw(this.oSelFieldAnzahl, 							LayoutDataFactory.get_GridLayoutGridLeftTOP(iHelp,2));
			
			this.add_raw(oGroup, 										LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,2));
			this.add_raw(this.btEinstellungenSpeichern, 				LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));
			this.add_raw(new MyE2_Label(""), 							LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));
			
			this.add_raw(this.cbErsteOeffnen, 							LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,3));
			this.add_raw(new MyE2_Label(""), 							LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));
			
			this.add_raw(new Separator(), 							    LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,4));
			
			this.add_raw(this.cbDatumSetzen, 							LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,2));
			this.add_raw(this.oTfSetDatumZumEintragen, 					LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));
			this.add_raw(new MyE2_Label(""), 							LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));

			this.add_raw(this.cbSORTEKopieren, 							LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,3));
			this.add_raw(new MyE2_LabelWrap(cInfoOldSorte,MyE2_Label.STYLE_SMALL_PLAIN()), 	LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));

			this.add_raw(this.cb_EK_KONTRAKT_Kopieren, 					LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,3));
			this.add_raw(new MyE2_LabelWrap(cInfoOldKontraktEK,MyE2_Label.STYLE_SMALL_PLAIN()), 	LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));

			this.add_raw(this.cb_VK_KONTRAKT_Kopieren, 					LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,3));
			this.add_raw(new MyE2_LabelWrap(cInfoOldKontraktVK,MyE2_Label.STYLE_SMALL_PLAIN()), 	LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));
			
			this.add_raw(this.cbPlanMengeKopieren, 						LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,3));
			this.add_raw(new MyE2_LabelWrap(cInfoOldPlanmengen,MyE2_Label.STYLE_SMALL_PLAIN()), 	LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));
			
			this.add_raw(this.cb_LKW_Kennzkopieren, 					LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,3));
			this.add_raw(new MyE2_LabelWrap(cInfoLKW,MyE2_Label.STYLE_SMALL_PLAIN()), 	LayoutDataFactory.get_GridLayoutGridLeftMID(iHelp,1));
			
			
			//in der letzten zeile den einstellungen-speichern button anzeigen
			
			
			//ein paar zusammenhaenge
			this.cbSORTEKopieren.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					if (!ownGridWithSetting.this.cbSORTEKopieren.isSelected())
					{
						ownGridWithSetting.this.cb_EK_KONTRAKT_Kopieren.setSelected(false);
						ownGridWithSetting.this.cb_VK_KONTRAKT_Kopieren.setSelected(false);
					}
				}
			});

			//ein paar zusammenhaenge
			this.cb_EK_KONTRAKT_Kopieren.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					if (ownGridWithSetting.this.cb_EK_KONTRAKT_Kopieren.isSelected())
					{
						ownGridWithSetting.this.cbSORTEKopieren.setSelected(true);
					}
				}
			});

			//ein paar zusammenhaenge
			this.cb_VK_KONTRAKT_Kopieren.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					if (ownGridWithSetting.this.cb_VK_KONTRAKT_Kopieren.isSelected())
					{
						ownGridWithSetting.this.cbSORTEKopieren.setSelected(true);
					}
				}
			});

			
			
		}

		
		public void saveSettings() throws myException
		{
			this.oUserSettings.SAVE_WERTE();
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Einstellungen der Kopiermaske wurden gespeichert !"));
		}
		
	}
	
	
	
	private class ownActionAgentStartKopieDialog extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU_LIST_BT_FUHRE_COPY_FUHRE oThis = FU_LIST_BT_FUHRE_COPY_FUHRE.this;
			
			
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDs.size() == 0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine Fuhre auswählen !"));
				return;
			}

			bibMSG.add_MESSAGE(execInfo.make_ID_Validation(vIDs));
			
			if (bibMSG.get_bIsOK())
			{
				oThis.vIDsTo_Copy.removeAllElements();
				oThis.vIDsTo_Copy.addAll(vIDs);
				
				FU_LIST_BT_FUHRE_COPY_FUHRE.this.gridWithSettings = new ownGridWithSetting();
				
				new ownConfirmContainer(FU_LIST_BT_FUHRE_COPY_FUHRE.this.gridWithSettings).show_POPUP_BOX();
			}
		}
		
	}
	
	
	
	
	private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
	{

		public ownConfirmContainer(ownGridWithSetting oGridMitEinstellungen)  throws myException
		{
			super(	new MyE2_String("Fuhre(n) kopieren ..."), 
					new MyE2_String("Fuhre(n) kopieren "), 
					null, 
					oGridMitEinstellungen,
					null, 
					new MyE2_String("Kopien erzeugen"), 
					new MyE2_String("Abbrechen"), 
					new Extent(720), 
					new Extent(500));
			
			this.set_ActionAgentForOK(new ownActionMakeCopy());
			
			this.get_oButtonOK().add_GlobalValidator(new XX_ActionValidator() 
			{
				@Override
				protected MyE2_MessageVector isValid(String cID_Unformated) 	throws myException 
				{
					return null;
				}
				
				@Override
				public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException 
				{
					String cDatum = FU_LIST_BT_FUHRE_COPY_FUHRE.this.gridWithSettings.oTfSetDatumZumEintragen.get_oTextField().getText();
					if (S.isEmpty(cDatum))
					{
						return null;
					}
					
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					
					TestingDate  oTD = new TestingDate(cDatum);
					if (oTD.testing())
					{
						FU_LIST_BT_FUHRE_COPY_FUHRE.this.gridWithSettings.oTfSetDatumZumEintragen.get_oTextField().setText(oTD.get_FormatedDateString("dd.mm.yyyy"));
					}
					else
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte korrektes Datum eintragen!"));
					}
					
					return oMV;
				}
			});			
		}
		
	}
	
	
	
	private class ownActionMakeCopy extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU_LIST_BT_FUHRE_COPY_FUHRE oThis = FU_LIST_BT_FUHRE_COPY_FUHRE.this;

			Vector<String> vSQL = new Vector<String>();
			
			int iAnzahlKopien = new Integer(oThis.gridWithSettings.oSelFieldAnzahl.get_ActualWert());
			
			Vector<String> vNewFuhrenIDs =  bibALL.get_SEQUENCE_VECT_VALUES("SEQ_VPOS_TPA_FUHRE",  (oThis.vIDsTo_Copy.size()*iAnzahlKopien));

			int iCount = 0;
			for (String cID_Copy: oThis.vIDsTo_Copy)
			{
				for (int i=0;i<iAnzahlKopien;i++)
				{
					ownFuhrenKopierer Kopiere = new ownFuhrenKopierer(cID_Copy,vNewFuhrenIDs.get(iCount));
					String cSQLCopy = Kopiere.get_cINSERT_String();
					vSQL.add(cSQLCopy);
					
					iCount++;
				}
			}
			
			MyE2_MessageVector  oMV = bibDB.ExecMultiSQLVector(vSQL, true);
			
			if (oMV.get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Neue Fuhren erzeugt: "+(oThis.vIDsTo_Copy.size()*iAnzahlKopien)));
				
				oThis.oNaviList.ADD_NEW_ID_TO_ALL_VECTORS(vNewFuhrenIDs);
				oThis.oNaviList._REBUILD_ACTUAL_SITE(null);
				
				//markieren: original mit marker, kopien anhaken
				Vector<String> vAlteUndNeue = new Vector<String>();
				vAlteUndNeue.addAll(FU_LIST_BT_FUHRE_COPY_FUHRE.this.vIDsTo_Copy);
				vAlteUndNeue.addAll(vNewFuhrenIDs);
				
				oThis.oNaviList.Check_ID_IF_IN_Page(vAlteUndNeue);
				
				//JETZT die erste kopierte gleich oeffnen, wenn der schalter gesetzt ist
				if (FU_LIST_BT_FUHRE_COPY_FUHRE.this.gridWithSettings.cbErsteOeffnen.isSelected())
				{
					FU_MASK_ModulContainer  oFuMaskContainer = (FU_MASK_ModulContainer)FU_LIST_BT_FUHRE_COPY_FUHRE.this.oMaskContainer;
					
					new __FUHREN_MASKEN_OEFFNER(oFuMaskContainer, FU_LIST_BT_FUHRE_COPY_FUHRE.this.oNaviList, vNewFuhrenIDs.get(0), "Maskenansicht zum Bearbeiten:  ID=", "Fuhrenkopie bearbeiten ...  ID:",true);
				}
			}
			else
			{
				bibMSG.add_MESSAGE(oMV);
			}
			
		}
		
	}
	


	
	private class ownFuhrenKopierer extends myDataRecordCopySQLString
	{

		public ownFuhrenKopierer(String cID_VPOS_TPA_FUHRE_QUELLE, String cNewID) throws myException
		{
			super("JT_VPOS_TPA_FUHRE", "ID_VPOS_TPA_FUHRE", cID_VPOS_TPA_FUHRE_QUELLE, null,null, null,null, false);
			
			HashMap<String,String> hmZusatz = new HashMap<String, String>();

			this._WRITE_ERSATZFIELDS_IN_POS_TPA_FUHRE(hmZusatz,new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE_QUELLE));
			hmZusatz.put("ID_VPOS_TPA_FUHRE",cNewID);
			
			this.add_ErsatzFields(hmZusatz);
		}
		
		
		/*
		 * wird als Kopie der ganze Transportauftrag kopiert, dann werden auch 
		 * die Felder zu den Kontrakten und die Planmenge geloescht
		 */
		public void _WRITE_ERSATZFIELDS_IN_POS_TPA_FUHRE(HashMap<String,String> hmErsatz,RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
		{
			
			ownGridWithSetting    GridWithSettings = FU_LIST_BT_FUHRE_COPY_FUHRE.this.gridWithSettings;

			
			hmErsatz.put("ID_VPOS_TPA_FUHRE","NULL");
			hmErsatz.put("ID_VPOS_TPA","NULL");            //muss in der rufenden einheit gesetzt werden

			
			hmErsatz.put("ID_MANDANT",bibALL.get_ID_MANDANT());
			hmErsatz.put("GEAENDERT_VON",bibALL.MakeSql(bibALL.get_KUERZEL()));
			hmErsatz.put("LETZTE_AENDERUNG",DB_META.get_tStampString(bibE2.get_DB_KENNUNG()));
			hmErsatz.put("ABGESCHLOSSEN","'N'");
			
			hmErsatz.put("MENGE_VORGABE_KO","NULL");
			hmErsatz.put("MENGE_AUFLADEN_KO","NULL");
			hmErsatz.put("MENGE_ABLADEN_KO","NULL");

			hmErsatz.put("ANTEIL_LADEMENGE_LIEF","NULL");
			hmErsatz.put("ANTEIL_ABLADEMENGE_LIEF","NULL");
			hmErsatz.put("ANTEIL_LADEMENGE_ABN","NULL");
			hmErsatz.put("ANTEIL_ABLADEMENGE_ABN","NULL");

			hmErsatz.put("DATUM_ABLADEN","NULL");
			hmErsatz.put("DATUM_AUFLADEN","NULL");

			
			//2011-05-31: bei der standard-kopie wird der Schalter "FUHRE_AUS_FAHRPLAN" immer auf nein gesetzt
			hmErsatz.put("FUHRE_AUS_FAHRPLAN","NULL");
			

			
			hmErsatz.put("BUCHUNGSNR_FUHRE","NULL");
			
			
			hmErsatz.put("AUFLADEN_BRUTTO","NULL");
			hmErsatz.put("AUFLADEN_TARA","NULL");
			hmErsatz.put("ABLADEN_BRUTTO","NULL");
			hmErsatz.put("ABLADEN_TARA","NULL");
			hmErsatz.put("FUHRENGRUPPE","NULL");
			hmErsatz.put("WIEGEKARTENKENNER_LADEN","NULL");
			hmErsatz.put("WIEGEKARTENKENNER_ABLADEN","NULL");
			hmErsatz.put("ID_WIEGEKARTE_LIEF","NULL");
			hmErsatz.put("ID_WIEGEKARTE_ABN","NULL");
			
			
			/*
			 * geaendert am: 24.03.2010 von: martin
			 */
			hmErsatz.put("AVV_AUSSTELLUNG_DATUM","NULL");
			
			
			/*
			 * geaendert am: 24.03.2010 von: martin
			 */
			//preisfelder muessen bei kopien auch leer werden
			hmErsatz.put("ABZUG_LADEMENGE_LIEF","NULL");
			hmErsatz.put("ABZUG_ABLADEMENGE_ABN","NULL");
			hmErsatz.put("ID_VPOS_STD_EK","NULL");
			hmErsatz.put("ID_VPOS_STD_VK","NULL");
			hmErsatz.put("MANUELL_PREIS_EK","NULL");
			hmErsatz.put("EINZELPREIS_EK","NULL");
			hmErsatz.put("STEUERSATZ_EK","NULL");
			hmErsatz.put("MANUELL_PREIS_VK","NULL");
			hmErsatz.put("EINZELPREIS_VK","NULL");
			hmErsatz.put("STEUERSATZ_VK","NULL");
			hmErsatz.put("EU_STEUER_VERMERK_EK","NULL");
			hmErsatz.put("EU_STEUER_VERMERK_VK","NULL");
			hmErsatz.put("SPEICHERN_TROTZ_INKONSISTENZ","NULL");
			hmErsatz.put("PRUEFUNG_LADEMENGE","NULL");
			hmErsatz.put("PRUEFUNG_LADEMENGE_VON","NULL");
			hmErsatz.put("PRUEFUNG_LADEMENGE_AM","NULL");
			hmErsatz.put("PRUEFUNG_ABLADEMENGE","NULL");
			hmErsatz.put("PRUEFUNG_ABLADEMENGE_VON","NULL");
			hmErsatz.put("PRUEFUNG_ABLADEMENGE_AM","NULL");
			
			//2013-03-21: die weiteren prueffelder auch leermachen
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_EK_PREIS,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_EK_PREIS_VON,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_EK_PREIS_AM,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_VK_PREIS,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_VK_PREIS_VON,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$PRUEFUNG_VK_PREIS_AM,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$SCHLIESSE_FUHRE,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$SCHLIESSE_FUHRE_VON,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$SCHLIESSE_FUHRE_AM,"NULL");

			hmErsatz.put(_DB.VPOS_TPA_FUHRE$ID_TAX_EK,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$ID_TAX_VK,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_IN,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$INTRASTAT_MELD_OUT,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$TRANSIT_EK,"NULL");
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$TRANSIT_VK,"NULL");
			
			//2013-10-01: die id der handelsdefinition darf auch nicht kopiert werden
			hmErsatz.put(_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF,"NULL");
			
			//aenderung 2010-12-22: FUHRE_KOMPLETT ist bei kopierten Fuhren immer N sein,
			//weitere Felder resettet beim kopieren einer fuhre
			//damit eine kopie einer gefaehrlichen sorte durch den validierer geht (noti ist null)
			hmErsatz.put("NOTIFIKATION_NR","NULL");
			
			hmErsatz.put("NOTIFIKATION_NR_EK","NULL");    //2012-10-18: ek-noti muss ebenfalls auf leer gesetzt werden
			
			hmErsatz.put("STATUS_BUCHUNG","NULL");
			hmErsatz.put("IST_STORNIERT","'N'");
			hmErsatz.put("IST_GEPLANT_FP","'N'");
			hmErsatz.put("STORNO_GRUND","NULL");
			hmErsatz.put("STORNO_KUERZEL","NULL");
			hmErsatz.put("WIEGEKARTENKENNER_LADEN","NULL");
			hmErsatz.put("WIEGEKARTENKENNER_ABLADEN","NULL");
			hmErsatz.put("KENNER_ALTE_SAETZE_FP","NULL");
			
			//falls in der kopierten fuhre eine noti vorhanden ist, muss der bediener nochmals ueber die Fuhrenmaske gehen
			//2012-10-18: auch die notifikation-ek in diese pruefung miteinbeziehen
			if (S.isFull(recFuhre.get_NOTIFIKATION_NR_cUF_NN("")) || S.isFull(recFuhre.get_NOTIFIKATION_NR_EK_cUF_NN("")))
			{
				hmErsatz.put("FUHRE_KOMPLETT","'N'");
			}

			
			if (!GridWithSettings.cbPlanMengeKopieren.isSelected())    //dann auch die planmengen loeschen
			{
				hmErsatz.put("ANTEIL_PLANMENGE_LIEF","NULL");
				hmErsatz.put("ANTEIL_PLANMENGE_ABN","NULL");
			}
			
			if (!GridWithSettings.cb_EK_KONTRAKT_Kopieren.isSelected())    //dann auch die planmengen loeschen
			{
				hmErsatz.put("ID_VPOS_KON_EK","NULL");
			}			

			if (!GridWithSettings.cb_VK_KONTRAKT_Kopieren.isSelected())    //dann auch die planmengen loeschen
			{
				hmErsatz.put("ID_VPOS_KON_VK","NULL");
			}			
			
			if (!GridWithSettings.cbSORTEKopieren.isSelected())    //dann auch die planmengen loeschen
			{
				hmErsatz.put("ID_ARTIKEL","NULL");
				hmErsatz.put("ID_ARTIKEL_BEZ_EK","NULL");
				hmErsatz.put("ID_ARTIKEL_BEZ_VK","NULL");
				hmErsatz.put("ID_VPOS_KON_VK","NULL");
				hmErsatz.put("ID_VPOS_KON_EK","NULL");
				
				hmErsatz.put("ARTBEZ1_EK","NULL");
				hmErsatz.put("ARTBEZ1_VK","NULL");
				hmErsatz.put("ARTBEZ2_EK","NULL");
				hmErsatz.put("ARTBEZ2_VK","NULL");
				
				hmErsatz.put("ANR1_EK","NULL");
				hmErsatz.put("ANR2_EK","NULL");

				hmErsatz.put("ANR1_VK","NULL");
				hmErsatz.put("ANR2_VK","NULL");

				
				//die fuhre als unvollstaendig kennzeichnen, damit druckauftraege nicht funktionieren
				hmErsatz.put("FUHRE_KOMPLETT","'N'");
				
			}			

			
			if (GridWithSettings.cbDatumSetzen.isSelected())    //dann auch die planmengen loeschen
			{

				hmErsatz.put("DATUM_ABHOLUNG",			"NULL");
				hmErsatz.put("DATUM_ABHOLUNG_ENDE",		"NULL");
				hmErsatz.put("DATUM_ABLADEN",			"NULL");
				hmErsatz.put("DATUM_ANLIEFERUNG",		"NULL");
				hmErsatz.put("DATUM_ANLIEFERUNG_ENDE",	"NULL");
				hmErsatz.put("DATUM_AUFLADEN",			"NULL");
				if (S.isFull(GridWithSettings.oTfSetDatumZumEintragen.get_oTextField().getText()))
				{
					TestingDate TD = new TestingDate(GridWithSettings.oTfSetDatumZumEintragen.get_oTextField().getText());
					if (TD.testing())
					{
						String cDatum = TD.get_ISO_DateFormat(true);
						hmErsatz.put("DATUM_ABHOLUNG",			cDatum);
						hmErsatz.put("DATUM_ABHOLUNG_ENDE",		cDatum);
						hmErsatz.put("DATUM_ABLADEN",			cDatum);
						hmErsatz.put("DATUM_ANLIEFERUNG",		cDatum);
						hmErsatz.put("DATUM_ANLIEFERUNG_ENDE",	cDatum);
						hmErsatz.put("DATUM_AUFLADEN",			cDatum);
					}
				}
			}			

			if (!GridWithSettings.cb_LKW_Kennzkopieren.isSelected())    //dann auch die planmengen loeschen
			{
				hmErsatz.put("TRANSPORTKENNZEICHEN",	"NULL");
				hmErsatz.put("ANHAENGERKENNZEICHEN",	"NULL");
			}		
			
			
			//2012-01-04: UM-Kontrakt aus der kopie raushalten
			hmErsatz.put("ID_UMA_KONTRAKT","NULL");

			
			//2012-01-23: postennummer aus kopie raushalten
			hmErsatz.put(RECORD_VPOS_TPA_FUHRE.FIELD__POSTENNUMMER_EK,"NULL");
			hmErsatz.put(RECORD_VPOS_TPA_FUHRE.FIELD__POSTENNUMMER_VK,"NULL");
			
			//2015-08-19: Bestellnummer-EK aus der kopie loeschen
			hmErsatz.put(VPOS_TPA_FUHRE.bestellnummer_ek.fn(),"NULL");
			//hmErsatz.put(VPOS_TPA_FUHRE.bestellnummer_vk.fn(),"NULL");
			
			
			
//			//2013-07-16: proforma-rechnung-buchungsnummer loeschen
//			hmErsatz.put(RECORD_VPOS_TPA_FUHRE.FIELD__BELEGNR_PROFORMA,"NULL");
			
			
			//2019-10-14: schalter gelangensbestaetigung raus
			hmErsatz.put(VPOS_TPA_FUHRE.gelangensbestaetigung_erhalten.fn(),"'N'");

			
			
		}

		
	}
	


}
