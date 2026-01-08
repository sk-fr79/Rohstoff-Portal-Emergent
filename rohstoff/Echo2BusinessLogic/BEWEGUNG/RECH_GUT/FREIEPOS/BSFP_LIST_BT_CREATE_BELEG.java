package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.text.SimpleDateFormat;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO_OnlyCode;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonCancelMask;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox_WithUserSetting_AUTOSAVE;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_LIST_BT_Mail_and_Print;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.__SORTER_POSITIONEN_DATUM_SORTE;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_IdKvPosUndFristTage;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_Lib;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_idAdresseUndRecVposRg;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VposRg;
import rohstoff.utils.MyAdress;


public class BSFP_LIST_BT_CREATE_BELEG extends MyE2_Button
{
	
	public static final String  SESSION_HASH_4_CREATE_RG__CHECKBOX_OPEN_RG = 	"SESSION_HASH_4_CREATE_RG__CHECKBOX_OPEN_RG";
	public static final String  SESSION_HASH_4_BELEG_DIREKT_SORTIEREN = 		"SESSION_HASH_4_BELEG_DIREKT_SORTIEREN";
	
	
	private String	 						cBELEGTYP = null;
	private E2_NavigationList  				oNaviList = null;
	
	private Vector<String>                  vIDs_statt_Navilist = null;
 	
	private String[][] 						cInfoArray = null;
	private E2_ConfirmBasicModuleContainer 	oConfirm = null;
	
	private MyE2_CheckBox_WithUserSetting_AUTOSAVE 	cbOpenRechungsGs_formular_nach_Erstellen = null;
	
	//2011-09-07: Beleg nach datum/Sorte sortieren (direkt)
	private MyE2_CheckBox_WithUserSetting_AUTOSAVE 	cbBelegDirektSortieren = null;
	
	
	
	private boolean bSicherheitsabfrageUeberspringen = false;
	
	/**
	 * Konstruktor fuer nutzung aus Freien positionen, haendisch zusammenstellen der RG-Belege
	 * @param VorgangsArt
	 * @param NaviList
	 * @throws myException
	 */
	public BSFP_LIST_BT_CREATE_BELEG(String VorgangsArt, E2_NavigationList  NaviList) throws myException 
	{
		super("");
		
		this.oNaviList = NaviList;
		this.vIDs_statt_Navilist = null;

		String cMODULE_KENNER = E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN;
		
		this.cbOpenRechungsGs_formular_nach_Erstellen = new MyE2_CheckBox_WithUserSetting_AUTOSAVE(
							new MyE2_String("Beleg nach Erstellung gleich öffnen ?"),
							false,
							false,
							BSFP_LIST_BT_CREATE_BELEG.SESSION_HASH_4_CREATE_RG__CHECKBOX_OPEN_RG,
							cMODULE_KENNER);

		this.cbOpenRechungsGs_formular_nach_Erstellen.setFont(new E2_FontBold(2));

		this.cbBelegDirektSortieren = new MyE2_CheckBox_WithUserSetting_AUTOSAVE(
							new MyE2_String("Beleg nach Leistungsdatum/Sorte sortieren ?"),
							true,
							false,
							BSFP_LIST_BT_CREATE_BELEG.SESSION_HASH_4_BELEG_DIREKT_SORTIEREN,
							cMODULE_KENNER);

		this.cbBelegDirektSortieren.setFont(new E2_FontBold(0));
	
		
		
		
		
		if (!(VorgangsArt.equals(myCONST.VORGANGSART_RECHNUNG) || VorgangsArt.equals(myCONST.VORGANGSART_GUTSCHRIFT)))
		{
			throw new myException("BSFP_LIST_BT_CREATE_BELEG:Constructor: Error: only allowed:"+myCONST.VORGANGSART_RECHNUNG+" .. "+myCONST.VORGANGSART_GUTSCHRIFT);
		}
				
		this.cBELEGTYP = VorgangsArt;
		
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG))
			this.set_Text(new MyE2_String("Rechnung"));
		else
			this.set_Text(new MyE2_String("Gutschrift"));
		
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN,"ERZEUGE_RECH_GUT"));
		this.add_IDValidator(new BSFP_LIST_BT_CREATE_BELEG__ID_Validator());
		this.add_oActionAgent(new ownActionAgentStartSicherheitsAbfrage());
		
		//20190701: neue validatoren
		this.add_GlobalValidator(new validateAllPositionsInSameMonth());
		this.add_GlobalValidator(new validateAllPositionsHaveSameKVFakturierungsFrist());
		this.add_GlobalValidator(new validateAllFromSameAdress());
	}
	

	
	
	/**
	 * Konstruktor fuer nutzung aus dem Speichern und -mehr -Dialog der Fuhre
	 * @param VorgangsArt
	 * @param v_IDs_statt_Navilist
	 * @throws myException
	 */
	public BSFP_LIST_BT_CREATE_BELEG(String VorgangsArt, Vector<String>  v_IDs_statt_Navilist) throws myException 
	{
		super("");
		
		this.bSicherheitsabfrageUeberspringen = true;
		
		this.oNaviList = null;
		this.vIDs_statt_Navilist = v_IDs_statt_Navilist;

		String cMODULE_KENNER = E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN;
		
		this.cbOpenRechungsGs_formular_nach_Erstellen = new MyE2_CheckBox_WithUserSetting_AUTOSAVE(
							new MyE2_String("Beleg nach Erstellung gleich öffnen ?"),
							false,
							false,
							BSFP_LIST_BT_CREATE_BELEG.SESSION_HASH_4_CREATE_RG__CHECKBOX_OPEN_RG,
							cMODULE_KENNER);

		this.cbOpenRechungsGs_formular_nach_Erstellen.setFont(new E2_FontBold(2));
		
		
		this.cbBelegDirektSortieren = new MyE2_CheckBox_WithUserSetting_AUTOSAVE(
				new MyE2_String("Beleg nach Leistungsdatum/Sorte sortieren ?"),
				true,
				false,
				BSFP_LIST_BT_CREATE_BELEG.SESSION_HASH_4_BELEG_DIREKT_SORTIEREN,
				cMODULE_KENNER);

		this.cbBelegDirektSortieren.setFont(new E2_FontBold(0));

		
		if (!(VorgangsArt.equals(myCONST.VORGANGSART_RECHNUNG) || VorgangsArt.equals(myCONST.VORGANGSART_GUTSCHRIFT)))
		{
			throw new myException("BSFP_LIST_BT_CREATE_BELEG:Constructor: Error: only allowed:"+myCONST.VORGANGSART_RECHNUNG+" .. "+myCONST.VORGANGSART_GUTSCHRIFT);
		}
				
		this.cBELEGTYP = VorgangsArt;
		
		if (this.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG))
			this.set_Text(new MyE2_String("Rechnung erstellen"));
		else
			this.set_Text(new MyE2_String("Gutschrift erstellen"));
		
		
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FREIEPOSITIONEN,"ERZEUGE_RECH_GUT"));
		this.add_IDValidator(new BSFP_LIST_BT_CREATE_BELEG__ID_Validator());
		this.add_oActionAgent(new ownActionAgentStartSicherheitsAbfrage());
		
		//20190701: neue validatoren
		this.add_GlobalValidator(new validateAllPositionsInSameMonth());
		this.add_GlobalValidator(new validateAllPositionsHaveSameKVFakturierungsFrist());
		this.add_GlobalValidator(new validateAllFromSameAdress());

	}

	
	
	
	

	
	
	
	
	
	
	
	private class ownActionAgentStartSicherheitsAbfrage extends XX_ActionAgent
	{

		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			BSFP_LIST_BT_CREATE_BELEG oThis = BSFP_LIST_BT_CREATE_BELEG.this;
			
			
			
			
			Vector<String> vIDs = new Vector<String>();
			if (oThis.oNaviList != null)
			{
				vIDs.addAll(oThis.oNaviList.get_vSelectedIDs_Unformated());
			}
			else if (oThis.vIDs_statt_Navilist!=null)
			{
				vIDs.addAll(oThis.vIDs_statt_Navilist);
			}
			
			if (vIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine Belegposition auswählen !"));
				return;
			}
			
			bibMSG.add_MESSAGE(oThis.valid_IDValidation(vIDs));

			if (bibMSG.get_bIsOK())
			{
				try
				{
					
					
					// jetzt pruefen, ob alle positionen zu einer adresse gehoeren/gleiche bedinungen erfuellen
					String cQuery = "SELECT DISTINCT      NVL(ID_ADRESSE,0)," +
														" NVL(OHNE_STEUER,'N')," +
														" NVL(ID_ZAHLUNGSBEDINGUNGEN,0)," +
														" NVL(ID_WAEHRUNG_FREMD,0)," +
														" NVL(ZAHLUNGSBEDINGUNGEN,'-')," +
														" ZAHLTAGE, " +
														" FIXMONAT, " +
														" FIXTAG, " +
														" SKONTO_PROZENT, " +
														" NVL(EU_STEUER_VERMERK,'--') " +
														" FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE " +
							" ID_VPOS_RG IN ("+bibALL.Concatenate(vIDs,",","0")+")";
					
					
				   oThis.cInfoArray = bibDB.EinzelAbfrageInArray(cQuery,"");
				    
				   if (oThis.cInfoArray == null || oThis.cInfoArray.length == 0) 
				   {
				   	bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim ermitteln der beteiligten Adressen !!"));
				   	return;
				   }
				   if (oThis.cInfoArray.length != 1)
				   {
					   bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es muss übereinstimmen:  ADRESSE, ZAHLUNGSBEDINGUNGEN, WAEHRUNG, EU-Steuervermerk und der Zustand <Steuer oder nicht> !"));
					   return;
				   }
				    
				   String cQueryGesamtNettoSumme = "SELECT SUM(JT_VPOS_RG.GESAMTPREIS_FW*JT_VPOS_RG.LAGER_VORZEICHEN-NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG_FW,0)*JT_VPOS_RG.LAGER_VORZEICHEN) FROM " +
				   									bibE2.cTO()+".JT_VPOS_RG WHERE " +
				   									" ID_VPOS_RG IN ("+bibALL.Concatenate(vIDs,",","0")+")";
				   
				   
				   String cQueryCheckeAnzahlFalscheBelege = null;
				   if (oThis.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG))
				   {
					   cQueryCheckeAnzahlFalscheBelege = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE LAGER_VORZEICHEN=1 AND ID_VPOS_RG IN ("+bibALL.Concatenate(vIDs,",","0")+")";
				   }
				   else
				   {
					   cQueryCheckeAnzahlFalscheBelege = "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE LAGER_VORZEICHEN=-1 AND ID_VPOS_RG IN ("+bibALL.Concatenate(vIDs,",","0")+")";
				   }
				   
				   String cAnzahlFalsche = bibDB.EinzelAbfrage(cQueryCheckeAnzahlFalscheBelege);
				   
				   
				   
				   
				   String cGesamtSumme = bibDB.EinzelAbfrage(cQueryGesamtNettoSumme);
				   Double  oDouble = new MyDouble(cGesamtSumme,false).get_oDouble();
				   
				   if (cAnzahlFalsche==null)
				   {
					   bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Richtigkeit der Buchung konnte nicht ermittelt werden !!"));
				   }
				   else
				   {
					   double dSumme = oDouble.doubleValue();
					   if (oThis.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG))
					   {
						   dSumme = -1*dSumme;
					   }
					   
					   //wenn im Wesentlichen Wareneingaenge in gutschriften und warenausgaenge in rechnungen einlaufen, dann
					   //sollt der wert jetzt positiv sein, sonst dickes Warenfeld anzeigen
					   String cHauptBeleg = BSFP_LIST_BT_CREATE_BELEG.this.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG)?"Rechnung":"Gutschrift";
					   String cGegenBeleg = BSFP_LIST_BT_CREATE_BELEG.this.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG)?"Gutschrift":"Rechnung";
					   
					   
					   MyE2_Column  oCol = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
					   oThis.oConfirm = new ownConfirmContainer(oCol,cHauptBeleg);
					   
					   if (cAnzahlFalsche.equals("0"))   //alles ok
					   {
						   oCol.add(new MyE2_Label(new MyE2_String("Gesamtnetto-Betrag :"),MyE2_Label.STYLE_NORMAL_BOLD()),E2_INSETS.I_0_10_0_10);
						   oCol.add(new MyE2_Label(MyNumberFormater.formatDez(dSumme, 2, true),MyE2_Label.STYLE_TITEL_BIG()),E2_INSETS.I_0_10_0_10);
					   }
					   else
					   {
						   oCol.add(new MyE2_Label(new MyE2_String("Es befinden sich Positionen vom Typ: "+cGegenBeleg),MyE2_Label.STYLE_ERROR_BIGBIG()),E2_INSETS.I_0_10_0_0);
						   oCol.add(new MyE2_Label(new MyE2_String(" in den ausgewählten Positionen"),MyE2_Label.STYLE_ERROR_BIGBIG()),E2_INSETS.I_0_0_0_10);
						   oCol.add(new MyE2_Label(new MyE2_String("Gesamtnetto-Betrag :"),MyE2_Label.STYLE_ERROR_BIGBIG()),E2_INSETS.I_0_10_0_10);
						   oCol.add(new MyE2_Label(MyNumberFormater.formatDez(dSumme, 2, true),MyE2_Label.STYLE_ERROR_BIGBIG()),E2_INSETS.I_0_10_0_10);
						   oCol.add(new MyE2_Label(new MyE2_String("Bitte prüfen Sie den Belegtyp !"),MyE2_Label.STYLE_ERROR_BIGBIG()),E2_INSETS.I_0_10_0_10);

						   //eigene buchungsbutton mit der gegenteiligen Positionstype
						   MyE2_Button  oButtonGegenBeleg = new MyE2_Button(new MyE2_String(" OK "+cGegenBeleg+" erzeugen"));
						   oButtonGegenBeleg.add_oActionAgent(new ownActionMacheVorgang(
								   BSFP_LIST_BT_CREATE_BELEG.this.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG)?myCONST.VORGANGSART_GUTSCHRIFT:myCONST.VORGANGSART_RECHNUNG));
						   
						   oButtonGegenBeleg.add_oActionAgent(new XX_ActionAgent()
						   {
								@Override
								public void executeAgentCode(ExecINFO execInfo)	throws myException
								{
									BSFP_LIST_BT_CREATE_BELEG.this.oConfirm.CLOSE_AND_DESTROY_POPUPWINDOW(true);
								}
						   });
						   
						   oCol.add(oButtonGegenBeleg,E2_INSETS.I_0_10_0_10);
						   
					   }
					   
					   oCol.add(new Separator(),E2_INSETS.I_0_10_0_10);
					   oCol.add(BSFP_LIST_BT_CREATE_BELEG.this.cbOpenRechungsGs_formular_nach_Erstellen,E2_INSETS.I_0_10_0_10);
					   BSFP_LIST_BT_CREATE_BELEG.this.cbOpenRechungsGs_formular_nach_Erstellen.get_StoredStatus();
					   
					   oCol.add(new Separator(),E2_INSETS.I_0_10_0_10);
					   oCol.add(BSFP_LIST_BT_CREATE_BELEG.this.cbBelegDirektSortieren,E2_INSETS.I_0_10_0_10);
					   BSFP_LIST_BT_CREATE_BELEG.this.cbBelegDirektSortieren.get_StoredStatus();
					   
					   
						//bei der erzeugung von Einzelpositionsbelegen aus der Fuhre
					   if (BSFP_LIST_BT_CREATE_BELEG.this.bSicherheitsabfrageUeberspringen)
					   {
						   new ownActionMacheVorgang(BSFP_LIST_BT_CREATE_BELEG.this.cBELEGTYP).executeAgentCode(new ExecINFO_OnlyCode());
					   }
					   else
					   {
						   oConfirm.show_POPUP_BOX();
					   }
				   }
				 
				}
				catch (myException ex)
				{
			    	bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BSFP_LIST_BT_CREATE_BELEG:ownActionAgent:Error creating Beleg!"));
			    	bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}
	}
	
	
	private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
	{

		public ownConfirmContainer(MyE2_Column oColInfo, String cWunschbelegt) throws myException
		{
			super(	new MyE2_String("Bestätigung"), 
					BSFP_LIST_BT_CREATE_BELEG.this.cBELEGTYP.equals(myCONST.VORGANGSART_RECHNUNG)?new MyE2_String("Erzeugung einer RECHNUNG"):new MyE2_String("Erzeugung einer GUTSCHRIFT"), 
					null,oColInfo,null, new MyE2_String("JA - "+cWunschbelegt+" erzeugen"),  new MyE2_String("NEIN - Abbruch"),new Extent(500),new Extent(550));
			
			this.set_ActionAgentForOK(new ownActionMacheVorgang(BSFP_LIST_BT_CREATE_BELEG.this.cBELEGTYP));
		}
		
	}
	
	
	private class ownActionMacheVorgang extends XX_ActionAgent
	{
		private String cVorgangTyp = null;
		
		public ownActionMacheVorgang(String vorgangTyp)
		{
			super();
			cVorgangTyp = vorgangTyp;
		}


		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			BSFP_LIST_BT_CREATE_BELEG oThis = BSFP_LIST_BT_CREATE_BELEG.this;
			
			Vector<String> vIDs = new Vector<String>();
			if (oThis.oNaviList != null)
			{
				vIDs.addAll(oThis.oNaviList.get_vSelectedIDs_Unformated());
			}
			else if (oThis.vIDs_statt_Navilist!=null)
			{
				vIDs.addAll(oThis.vIDs_statt_Navilist);
			}

		    // jetzt gehts los
		    BSFP_SQLZUORDNUNGEN_VKOPF oSQL_ZU = new BSFP_SQLZUORDNUNGEN_VKOPF(this.cVorgangTyp,oThis.cInfoArray,vIDs);
		    
		    Vector<String> vSQL = new Vector<String>();
		    
		    vSQL.add(oSQL_ZU.get_CompleteInsertString("JT_VKOPF_RG",bibE2.cTO()));
		    for (int i=0;i<vIDs.size();i++)
		    {
		    	vSQL.add("UPDATE JT_VPOS_RG SET ID_VKOPF_RG=SEQ_VKOPF_RG.CURRVAL WHERE ID_VPOS_RG ="+(String)vIDs.get(i));
		    }
		    
		    
		    
		    bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
		    if (bibMSG.get_bIsOK())
		    {

		    	//2016-12-14: meldungen hier einblenden (falls der kunde meldungen besitzt zur rechnungen / gutschriften
				String id_adresse  = cInfoArray[0][0];
		    	new __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(id_adresse, this.cVorgangTyp).ACTIVATE_MESSAGES();
		    	//
		    	
		    	
		    	//jetzt die ID des neuen kopfsatzes beschaffen, um (falls gewuenscht) den neuen beleg gleich oeffnen zu koennen
		    	String cID_VKOPF_RG = bibDB.EinzelAbfrage("SELECT SEQ_VKOPF_RG.CURRVAL FROM DUAL");
		    	if (!bibALL.isLong(cID_VKOPF_RG))
		    	{
		    		cID_VKOPF_RG = null;
		    	}

		    	
		    	if (this.cVorgangTyp.equals(myCONST.VORGANGSART_RECHNUNG))
		    		bibMSG.add_MESSAGE(new MyE2_Info_Message("Rechnung geschrieben ... "+vIDs.size()+" Positionen !"));
		    	else
		    		bibMSG.add_MESSAGE(new MyE2_Info_Message("Gutschrift geschrieben ... "+vIDs.size()+" Positionen !"));
		    	
		    	
		    	
		    	//2011-09-07: jetzt (falls gewuenscht) die positionen direkt sortieren
		    	if (BSFP_LIST_BT_CREATE_BELEG.this.cbBelegDirektSortieren.isSelected())
		    	{
		    		if (S.isEmpty(cID_VKOPF_RG))
		    		{
		    			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die ID des neuen Belegs konnte nicht ermittelt werden / KEINE DIREKTE SORTIERUNG MÖGLICH"));
		    		}
		    		else
		    		{
		    			new __SORTER_POSITIONEN_DATUM_SORTE(new RECORD_VKOPF_RG(cID_VKOPF_RG),"AUSFUEHRUNGSDATUM,ARTBEZ1","Leistungsdatum/Artikelbezeichnung");
		    		}
		    	}
		    	
		    	
		    	
		    	//2011-02-01: nur die aktuelle auswahl refreshen, nicht die ganze liste (damit spruenge in Rechnung/Gutschrift moeglich sind)
		    	//oThis.oNaviList._REBUILD_COMPLETE_LIST("");
		    	
		    	if (oThis.oNaviList!=null)
		    	{
		    		oThis.oNaviList._REBUILD_ACTUAL_SITE("");
		    	}
		    	
		    	
		    	if (BSFP_LIST_BT_CREATE_BELEG.this.cbOpenRechungsGs_formular_nach_Erstellen.isSelected() || 
		    		BSFP_LIST_BT_CREATE_BELEG.this.bSicherheitsabfrageUeberspringen	)
		    	{
		    		if (S.isEmpty(cID_VKOPF_RG))
		    		{
		    			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die ID des neuen Belegs konnte nicht ermittelt werden !"));
		    		}
		    		else
		    		{
		    			new ownMaskContainer_VKOPF_RG(new RECORD_VKOPF_RG(cID_VKOPF_RG));
		    		}
		    	}
		    	
		    	
		    	
		    	
		    }
		    else
		    {
		    	bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Schreiben des Belegs !!"));
		    	bibMSG.add_ALARMMESSAGE_Vector_Untranslated(vSQL);
		    }
		}
		
		
		
		
		private class ownMaskContainer_VKOPF_RG extends BSRG_K_MASK__ModulContainer
		{

			public ownMaskContainer_VKOPF_RG(RECORD_VKOPF_RG  recVKOPF_RG ) throws myException 
			{
				super(new BS__SETTING(recVKOPF_RG.get_VORGANG_TYP_cUF()), null);
				
				
				BS__SETTING  oSetting = this.get_SETTING();
				
				this.set_SETTING(oSetting);
				
				boolean bIstRech = recVKOPF_RG.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG);

				
				//jetzt die maske fuellen
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = this.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,recVKOPF_RG.get_ID_VKOPF_RG_cUF());
			
				
				//jetzt die buttons SAVE, ABBRUCH und VORSCHAUDRUCK einbauen
				
				BSRG_K_LIST_BT_Mail_and_Print oPrintAusMaske = new BSRG_K_LIST_BT_Mail_and_Print(
																				new MyE2_String("Druck/Mail Rechnung/Gutschrift"), 
																				null, 
																				oSetting, 
																				this.get_MODUL_IDENTIFIER(), 
																				true, 
																				this);

				maskButtonSaveMask   	oSave = new maskButtonSaveMask(this, new E2_SaveMaskStandard(this, null), null, null);
				maskButtonCancelMask  	oCancel = new maskButtonCancelMask(this) 
				{
					@Override
					public boolean doActionAfterCancelMask() 
					{
						return true;
					}
				};
				
				this.get_oRowForButtons().removeAll();
				this.get_oRowForButtons().add(oSave,E2_INSETS.I_5_2_5_2);
				this.get_oRowForButtons().add(oCancel,E2_INSETS.I_5_2_5_2);
				this.get_oRowForButtons().add(oPrintAusMaske,E2_INSETS.I_5_2_5_2);

				
				if (bibMSG.get_bIsOK())
				{
					this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000),new Extent(800),new MyE2_String((bIstRech?"Rechnung ":"Gutschrift ")+" bearbeiten !"));
				}
				
			}
			
		}
		
		
	}
	
	
	
	
	
	
	private class BSFP_SQLZUORDNUNGEN_VKOPF extends MySqlStatementBuilder
	{
		private String	 			c_BELEGTYP = null;
		private String   			cID_ADRESSE = null;

		public BSFP_SQLZUORDNUNGEN_VKOPF(String BELEGTYP, String[][] cInfoArray, Vector<String> vID_VPOS_RG) throws myException
		{
			super();
			this.c_BELEGTYP = 	BELEGTYP;
			this.cID_ADRESSE = 	cInfoArray[0][0];
			
			MyAdress     oAdress = new MyAdress(this.cID_ADRESSE,false);
			BS__SETTING  oBS__Setting = new BS__SETTING(BELEGTYP);
			
			
			String[] cGleicheFelder = {"VORNAME","NAME1","NAME2","NAME3","STRASSE","HAUSNUMMER","PLZ","ORT","ORTZUSATZ"};
			
			this.addSQL_Paar("ID_VKOPF_RG",		"SEQ_VKOPF_RG.NEXTVAL",	false);
			this.addSQL_Paar("ID_ADRESSE",		this.cID_ADRESSE,		false);
			this.addSQL_Paar("VORGANG_TYP",		this.c_BELEGTYP,		true);
			this.addSQL_Paar("ABGESCHLOSSEN",	"N",					true);
			this.addSQL_Paar("DELETED",			"N",					true);
			this.addSQL_Paar("ERSTELLUNGSDATUM","SYSDATE",				false);
			
			/*
			 * geaendert am: 16.03.2010 von: martin
			 */
			this.addSQL_Paar("LAENDERCODE", 	S.isEmpty(oAdress.get_cLAENDERCODE_KLARTEXT())?"NULL":bibALL.MakeSql(oAdress.get_cLAENDERCODE_KLARTEXT()),false);

			this.addSQL_Paar("SPRACHE",						oAdress.get_cSPRACHE_KLARTEXT(),	true);
			
			/*
			 * aenderung am 2011-01-14: Waehrung-Fremd kommt aus der position
			 */
			//this.addSQL_Paar("ID_WAEHRUNG_FREMD",			oAdress.get_cID_WAEHRUNG_UNFORMATED(),false);
			
			this.addSQL_Paar("NAME_ANSPRECHPARTNER",		oAdress.get_StandardAnsprechpartner(true,true,true,true,false,false),true);
			this.addSQL_Paar("TELEFON_AUF_FORMULAR",		oAdress.get_StandardTelefonNumber(),true);
			this.addSQL_Paar("TELEFAX_AUF_FORMULAR",		oAdress.get_StandardFaxNumber(),true);
			this.addSQL_Paar("EMAIL_AUF_FORMULAR",			oAdress.get_EmailForPaper(oBS__Setting.get_cBELEGTYP()),true);
		
			this.addSQL_Paar("ID_USER",						bibALL.get_RECORD_USER().get_ID_USER_cUF(),false);
			this.addSQL_Paar("NAME_BEARBEITER_INTERN",		bibALL.get_RECORD_USER().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "),true);
			this.addSQL_Paar("TEL_BEARBEITER_INTERN",		bibALL.get_RECORD_USER().get_TELEFON_VALUE_FOR_SQLSTATEMENT(),false);
			this.addSQL_Paar("FAX_BEARBEITER_INTERN",  		bibALL.get_RECORD_USER().get_TELEFAX_VALUE_FOR_SQLSTATEMENT(),false);
			
			//aenderung 2010-11-03: es werden zusaetlich noch der sachbearbeiter und der ansprechpartner eingetragen
			RECORD_ADRESSE  recAdresse = new RECORD_ADRESSE(this.cID_ADRESSE);
			if (recAdresse.get_UP_RECORD_USER_id_user()!=null)
			{
				this.addSQL_Paar("ID_USER_ANSPRECH_INTERN", recAdresse.get_UP_RECORD_USER_id_user().get_ID_USER_cUF(),false);
				this.addSQL_Paar("NAME_ANSPRECH_INTERN",   	recAdresse.get_UP_RECORD_USER_id_user().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "),true);
				this.addSQL_Paar("TEL_ANSPRECH_INTERN" ,	recAdresse.get_UP_RECORD_USER_id_user().get_TELEFON_cF_NN(""),true);
				this.addSQL_Paar("FAX_ANSPRECH_INTERN",		recAdresse.get_UP_RECORD_USER_id_user().get_TELEFAX_cF_NN(""),true);
			}
			
			if (recAdresse.get_UP_RECORD_USER_id_user_sachbearbeiter()!=null)
			{
				this.addSQL_Paar("ID_USER_SACHBEARB_INTERN", 	recAdresse.get_UP_RECORD_USER_id_user_sachbearbeiter().get_ID_USER_cUF(),false);
				this.addSQL_Paar("NAME_SACHBEARB_INTERN", 		recAdresse.get_UP_RECORD_USER_id_user_sachbearbeiter().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "),true);
				this.addSQL_Paar("TEL_SACHBEARB_INTERN",		recAdresse.get_UP_RECORD_USER_id_user_sachbearbeiter().get_TELEFON_cF_NN(""),true);
				this.addSQL_Paar("FAX_SACHBEARB_INTERN",		recAdresse.get_UP_RECORD_USER_id_user_sachbearbeiter().get_TELEFAX_cF_NN(""),true);
			}

			
			//2011-01-18: aenderung: 
			//wird in einer position ein zugehoeriger kontrakt gefunden, dann wird der ansprechpartner des kontrakts auch der des rechnungsbelegs
			// feststellen, ob es eine position mit einer kontraktzuordnung gibt und die erste 
			// kontrakt-position nehmen, um den Haendler und Betreuer auf den Rechnungskopf zu uebernehmen
			RECORD_VPOS_RG   	recFirstPosRG = null;
			RECORD_VPOS_KON 	recKON = 		null; 
			for (int i=0;i<vID_VPOS_RG.size();i++)
			{
				RECORD_VPOS_RG recRG = new RECORD_VPOS_RG(vID_VPOS_RG.get(i));
				recKON = recRG.get_UP_RECORD_VPOS_KON_id_vpos_kon_zugeord();

				if (recFirstPosRG==null)
				{
					recFirstPosRG=recRG;
				}
				
				if (recKON != null)
				{
					break;
				}
			}

			if (recKON!=null)
			{
				RECORD_VKOPF_KON recVKOPF_KON = recKON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon();
				this.addSQL_Paar("ID_USER_ANSPRECH_INTERN", 	recVKOPF_KON.get_ID_USER_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
				this.addSQL_Paar("NAME_ANSPRECH_INTERN",   		recVKOPF_KON.get_NAME_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
				this.addSQL_Paar("TEL_ANSPRECH_INTERN" ,		recVKOPF_KON.get_TEL_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
				this.addSQL_Paar("FAX_ANSPRECH_INTERN",			recVKOPF_KON.get_FAX_ANSPRECH_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
				
				this.addSQL_Paar("ID_USER_SACHBEARB_INTERN", 	recVKOPF_KON.get_ID_USER_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
				this.addSQL_Paar("NAME_SACHBEARB_INTERN", 		recVKOPF_KON.get_NAME_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
				this.addSQL_Paar("TEL_SACHBEARB_INTERN",		recVKOPF_KON.get_TEL_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
				this.addSQL_Paar("FAX_SACHBEARB_INTERN",		recVKOPF_KON.get_FAX_SACHBEARB_INTERN_VALUE_FOR_SQLSTATEMENT(),false);
			}
			//-----------------------------------------------
			
			
			
			
			// --------------------------------------------------
			//aenderung 2010-11-04: es wird der schalter BELEG_NUR_INTERN gesetzt
			if (this.c_BELEGTYP.equals(myCONST.VORGANGSART_GUTSCHRIFT))
			{
				this.addSQL_Paar("BELEG_IST_INTERN",		recAdresse.get_GUTSCHRIFTEN_SPERREN_cUF_NN("N"),true);
			}
			else
			{
				this.addSQL_Paar("BELEG_IST_INTERN",		recAdresse.get_RECHNUNGEN_SPERREN_cUF_NN("N"),true);
			}
			
			
			//aenderung 2010-11-22: ust-id-felder im RG-KOPF
//			FirmenSearch_USTID oUST_Search = new FirmenSearch_USTID(recAdresse);
			
			
//			//2011-01-14: nur die haupt-str-nummer automatisch nehmen
			//this.addSQL_Paar("UMSATZSTEUERID", bibALL.MakeSql(S.NN(oUST_Search.get_cUST_ID())));
			//this.addSQL_Paar("UMSATZSTEUERLKZ", bibALL.MakeSql(S.NN(oUST_Search.get_cUST_LKZ())));

			this.addSQL_Paar("UMSATZSTEUERID",  recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERID_VALUE_FOR_SQLSTATEMENT());
			this.addSQL_Paar("UMSATZSTEUERLKZ", recAdresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERLKZ_VALUE_FOR_SQLSTATEMENT());
			
			if (S.isFull(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF()))
			{
				RECORD_ADRESSE recAdresseMandant = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());
			
				this.addSQL_Paar("UMSATZSTEUERID_MANDANT",recAdresseMandant.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERID_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("UMSATZSTEUERLKZ_MANDANT",recAdresseMandant.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERLKZ_VALUE_FOR_SQLSTATEMENT());
			}
			
			
			//aenderung: 2010-12-14: Druckzaehler
			this.addSQL_Paar("DRUCKZAEHLER", "0");

			
			
			// jetzt die gleichen felder
			for (int i=0;i<cGleicheFelder.length;i++)
			{
				this.addSQL_Paar(cGleicheFelder[i],				oAdress.get_ValueStringForSQLStatement(cGleicheFelder[i]),false);
			}
			
			//jetzt den scheck-text in die gutschrift reinfummeln
			if (this.c_BELEGTYP.equals(myCONST.VORGANGSART_GUTSCHRIFT))
			{
				if (oAdress.get_cFI_SCHECKDRUCK_JN().equals("Y"))
				{
					this.addSQL_Paar("FORMULARTEXT_ENDE", bibALL.get_RECORD_MANDANT().get_SCHECKVERMERK_AUF_GUTSCHRIFT_cF_NN(""),true);
				}
			}
			
			
			//aenderungen: 2010-11-26: vorher:
			this.addSQL_Paar("ID_ZAHLUNGSBEDINGUNGEN",		cInfoArray[0][2],	false, true);
			//vorher:aenderung am 2011-01-14: Waehrung-Fremd kommt aus der position
			this.addSQL_Paar("ID_WAEHRUNG_FREMD",			cInfoArray[0][3],   false, false);
			this.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",			cInfoArray[0][4],	true,  true);
			this.addSQL_Paar("ZAHLTAGE",					cInfoArray[0][5],	false, true);
			this.addSQL_Paar("FIXMONAT",					cInfoArray[0][6],	false, true);
			this.addSQL_Paar("FIXTAG",						cInfoArray[0][7],	false, true);
			this.addSQL_Paar("SKONTO_PROZENT",				cInfoArray[0][8],	false, true);
			
			//2011-01-18:  lieferbedingungen aus dem ersten positionssatz
			//old: this.addSQL_Paar("LIEFERBEDINGUNGEN",			oAdress.get_cLIEFERBEDINGUNGEN_KLARTEXT(),	true);
			this.addSQL_Paar("LIEFERBEDINGUNGEN",			oAdress.get_cLIEFERBEDINGUNGEN_KLARTEXT(),	true);
			if (S.isFull(recFirstPosRG.get_LIEFERBEDINGUNGEN_cUF_NN("")))
			{
				this.addSQL_Paar("LIEFERBEDINGUNGEN",			recFirstPosRG.get_LIEFERBEDINGUNGEN_cUF_NN(""),	true);
			}
			
		}
		
	}
	
	
	
	
	//erweiterung mit weiterer validierung der positionen, die zu einem beleg werden sollen
	private VEK<Long>  getIdsToValidate() throws Exception {
		VEK<Long> v = new VEK<>();
		
		if (BSFP_LIST_BT_CREATE_BELEG.this.oNaviList!=null) {
			for (String id: BSFP_LIST_BT_CREATE_BELEG.this.oNaviList.get_vSelectedIDs_Unformated()) {
				v._a(Long.parseLong(id));
			}
		} else if (BSFP_LIST_BT_CREATE_BELEG.this.vIDs_statt_Navilist!=null) {
			for (String id: BSFP_LIST_BT_CREATE_BELEG.this.vIDs_statt_Navilist) {
				v._a(Long.parseLong(id));
			}
		}
		return v;
	}
	
	
	
	/**
	 * validierung sorgt fuer monatsgleiche Fakturierung
	 * @author martin
	 * @date 01.07.2019
	 *
	 */
	private class validateAllPositionsInSameMonth extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			if (ENUM_MANDANT_DECISION.RECHNUNG_GUTSCHRIFT_POSITIONEN_MONATSGLEICH.is_YES()) {
			
				try {
					VEK<Long>  	ids = 			getIdsToValidate();
					VEK<String> testeDatums = 	new VEK<>();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
					
					for (Long id: ids) {
						Rec21  r = new Rec21(_TAB.vpos_rg)._fill_id(id);
						testeDatums._addIfNotIn(df.format(r.getRawVal(VPOS_RG.ausfuehrungsdatum)));
					}

					if (testeDatums.size()>1) {
						mv._addAlarm(S.ms("Es dürfen nur monatsgleiche Rechnungspositionen zu einem Beleg zusammengefasst werden !"));
					}
					
					
				} catch (Exception e) {
					
					mv._addAlarm("Unknown Error Code: <ec4d2c52-9c15-11e9-a2a3-2a2ae2dbcce4>: "+e.getLocalizedMessage());
					e.printStackTrace();
				} 
			
			
			}
			
			return mv;
		}
		
	}
	
	
	
	/**
	 * validierung sorgt fuer monatsgleiche Fakturierung
	 * @author martin
	 * @date 01.07.2019
	 *
	 */
	private class validateAllPositionsHaveSameKVFakturierungsFrist extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			
			try {
				VEK<Long>  							ids = 			getIdsToValidate();
				VEK<KV_idAdresseUndRecVposRg> 		idAdressLeistungsDatum = new VEK<KV_idAdresseUndRecVposRg>();
				
				for (Long id: ids) {
					Rec21  r = new Rec21(_TAB.vpos_rg)._fill_id(id);
					idAdressLeistungsDatum._a(new KV_idAdresseUndRecVposRg(r.getLongDbValue(VPOS_RG.id_adresse),new Rec21_VposRg(r)));
				}

				 VEK<KV_IdKvPosUndFristTage> ergebnis = KV_Lib.getAllFaktFristIdsAndVals(idAdressLeistungsDatum);
				
				if (ergebnis.size()!=1) {
					mv._addAlarm(S.ms("Innerhalb der Positionen, die zum Beleg zusammengefasst werden sollen, "
							+ "sind unterschiedliche verlängerte Fakturierungsfristen vorhanden. Das ist nicht möglich! "));
				}
				
				
			} catch (Exception e) {
				
				mv._addAlarm("Unknown Error Code: <ec4d2c52-9c15-11e9-a2a3-2a2ae2dbcce4>: "+e.getLocalizedMessage());
				e.printStackTrace();
			} 
		
			
			return mv;
		}
		
	}
	

	/**
	 * validierung sorgt fuer monatsgleiche Fakturierung
	 * @author martin
	 * @date 02.07.2019
	 *
	 */
	private class validateAllFromSameAdress extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			
			try {
				VEK<Long>  	ids = 			getIdsToValidate();
				VEK<Long> 	kontrolle = 	new VEK<Long>();
				
				for (Long id: ids) {
					Rec21  r = new Rec21(_TAB.vpos_rg)._fill_id(id);
					kontrolle._addIfNotIn(r.getLongDbValue(VPOS_RG.id_adresse));
				}

				if (kontrolle.size()!=1) {
					mv._addAlarm(S.ms("Achtung! Sie haben Positionen unterschiedlicher Kunden ausgewählt! "));
				}
				
				
			} catch (Exception e) {
				
				mv._addAlarm("Unknown Error Code: <aef431a8-9caa-11e9-a2a3-2a2ae2dbcce4>: "+e.getLocalizedMessage());
				e.printStackTrace();
			} 
		
			
			return mv;
		}
		
	}
	
}
