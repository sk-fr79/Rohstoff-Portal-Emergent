package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_MaskInfo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_FormaterForFoundButtons;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;
import panter.gmbh.indep.maggie.TestingDate;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Sel_Zahlungsbedingungen;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;
import rohstoff.utils.META_MAP_ArtikelBez;
import rohstoff.utils.My_MWST;
import rohstoff.utils.My_MWSTSaetze;
import rohstoff.utils.ECHO2.ARTBEZ_Selector.XX_Tell_me_the_actual_adress_id;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class BSK_P_MASK_SEARCH_ArtikelBEZ extends DB_SEARCH_ArtikelBez
{
	// in allen positionsvarianten werden die Kopf-ids ueber das SQLFieldForRestrictTableRange
	// in die maske uebernommen
	private SQLFieldMAP oSQLFieldMAP_Position = null;
	private BS__SETTING o_Setting = null;
	
	public BSK_P_MASK_SEARCH_ArtikelBEZ(SQLFieldMAP osqlFieldGroup, BS__SETTING oSetting) throws myException
	{
		super(osqlFieldGroup.get_("ID_ARTIKEL_BEZ"), null, null, null, null, null, true,null);
		
		this.oSQLFieldMAP_Position = osqlFieldGroup;
		this.o_Setting = oSetting;
		
		this.set_FormatterForButtons(new FormatErgebnisButtons());
		
		this.set_bTextForAnzeigeVisible(false);
		
		/*
		 * sicherstellen, dass auf der EK-Seite nur gelistete sorten kommen
		 */
		this.add_ValidatorStartSearch(new VALID_ADRESS_INPUT(oSetting));
		
		// da auf der EK-seite nur die gelisteten sortenbezeichnungen kommen, ist ein suchtext nicht noetig
		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT))
		{
			this.get_oSeachBlock().set_bAllowEmptySearchField(true);
			
			//und beschraenken der schnellzugriffs-selektion auf der EK-Seite
			this.set_oTell_Adress_ID(new ownTell_Me_The_adresse_id());
			
		}
		
		
		// jetzt noch ein ActionAgentAfter Found
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		
	}

	
	private class SearchAdressID 
	{
		private String cID_ADRESS = null;

		public SearchAdressID() throws myException
		{
			
			BSK_P_MASK_SEARCH_ArtikelBEZ oThis = BSK_P_MASK_SEARCH_ArtikelBEZ.this;
			String cID_TEST = ((SQLFieldForRestrictTableRange) oThis.oSQLFieldMAP_Position.get_("ID_VKOPF_KON")).get_cRestictionValue_IN_DB_FORMAT();
			
			String cID__Test = bibALL.ReplaceTeilString(cID_TEST,".","");
			if (bibALL.isInteger(cID__Test))
			{
				// dann die kunden-spezifischen artikelbezeichungnen
				String cSQL = "SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_VKOPF_KON="+cID__Test;
				
				String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cSQL);
				if (cErgebnis != null && cErgebnis.length==1)
				{
					this.cID_ADRESS= cErgebnis[0][0];
				}
			}
		}
		public String get_cID_ADRESS() 
		{
			return cID_ADRESS;
		}
	}
	
	
	private class ownTell_Me_The_adresse_id extends XX_Tell_me_the_actual_adress_id
	{

		@Override
		public String get_unformated_Adress_ID_or_null() throws myException 
		{
			return new SearchAdressID().get_cID_ADRESS();
		}
		
	}
	
	
	
	private class FormatErgebnisButtons extends XX_FormaterForFoundButtons
	{
		private Vector<String> vIDArtikelBEZ = null;
		
		
		
		public void DO_Format(XX_Button4SearchResultList oButton)
		{
			BSK_P_MASK_SEARCH_ArtikelBEZ oThis = BSK_P_MASK_SEARCH_ArtikelBEZ.this;
			
			try
			{
				
				//beim ersten aufruf nach dem reset muss die liste mit artikelbez eingelesen werden
				if (this.vIDArtikelBEZ==null)
				{
					this.vIDArtikelBEZ = new Vector<String>();
					
					// zuerst nachsehen, ob die ID-Adresse eine id enthaelt
					String cID_TEST = ((SQLFieldForRestrictTableRange) oThis.oSQLFieldMAP_Position.get_("ID_VKOPF_KON")).get_cRestictionValue_IN_DB_FORMAT();
					
					String cID__Test = bibALL.ReplaceTeilString(cID_TEST,".","");
					if (bibALL.isInteger(cID__Test))
					{
						// dann die kunden-spezifischen artikelbezeichungnen
						String cSQL = "SELECT ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ADRESSE=(" +
											"SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VKOPF_KON WHERE ID_VKOPF_KON="+cID__Test+")";
						
						MyDBToolBox oDB = bibALL.get_myDBToolBox();
						String[][] cErgebnis = oDB.EinzelAbfrageInArray(cSQL);
						bibALL.destroy_myDBToolBox(oDB);
						if (cErgebnis != null && cErgebnis.length>0)
						{
							for (int i=0;i<cErgebnis.length;i++)
							{
								this.vIDArtikelBEZ.add(cErgebnis[i][0]);
							}
						}
					}
				}
				
				
				// jetzt nachsehen, ob der button im EXT().CMerkmal eine ID aus dem vector enthaelt,
				// wenn ja, Schrift fett / kursiv
				if (!bibALL.isEmpty(oButton.EXT().get_C_MERKMAL()))
					if (this.vIDArtikelBEZ.contains(oButton.EXT().get_C_MERKMAL()))
						oButton.setFont(new E2_FontBoldItalic());
				
			}
			catch (myException ex)
			{
				
			}
			
		}
		
	

		public void RESET()
		{
			this.vIDArtikelBEZ=null;;
		}



//		@Override
//		public Component DO_Transform(MyE2_Button oButton) throws myException {
//			return null;
//		}



		@Override
		public void RESET_ROW(XX_Button4SearchResultList[] buttonsInRow) throws myException {
			
		}
	
	}
	
	
	/**
	 * @author martin
	 * Validator hat folgende aufgabe:
	 * 1. muss sicherstellen, dass eine korrekte adress eingetragen ist
	 * 2. Wenn EK-Kontrakt, dann fuegt zusatz-bedingung bei der suche ein, die nur noch sorten zulaesst, die in der kundenspezifischen
	 *    artikelbezeichnung vorhanden sind.
	 */
	private class VALID_ADRESS_INPUT extends XX_ActionValidator
	{
		private MyE2_String cError = new MyE2_String("");
	
		private BS__SETTING oSetting = null;
		
		public VALID_ADRESS_INPUT(BS__SETTING Setting)
		{
			super();
			this.oSetting=Setting;
		}


		public MyE2_Message get_ErrorMessage()
		{
			return new MyE2_Message(cError,MyE2_Message.TYP_ALARM,false);
		}

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			boolean bRueck = false;
			
			BSK_P_MASK_SEARCH_ArtikelBEZ oThis = BSK_P_MASK_SEARCH_ArtikelBEZ.this;
			
			try
			{

				boolean bIST_EK = (this.oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT));
				
				// zuerst nachsehen, ob die ID-Adresse eine id enthaelt
				String cID_ADRESSE = new SearchAdressID().get_cID_ADRESS();
				if (!bibALL.isInteger(cID_ADRESSE))
				{
					if (bIST_EK)
						this.cError = new MyE2_String("Bitte geben Sie zuerst die Lieferantenadresse korrekt ein!!");
					else
						this.cError = new MyE2_String("Bitte geben Sie zuerst die Abnehmeradresse korrekt ein!!");
				}
				else
				{
					bRueck = true;

					// wenn ek-kontrakt, dann sorten auf die gelisteten beschraenken
					if (bIST_EK)
					{
						String cWhereZusatz = 
								"JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ IN (SELECT DISTINCT JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ FROM "+
								bibE2.cTO()+".JT_ARTIKELBEZ_LIEF " +
								" WHERE JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP='EK' AND "+
								" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+cID_ADRESSE+")";
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().add(cWhereZusatz);
					}
					else
					{
						oThis.get_oSeachBlock().get_vZusatzWhereBedingungen().removeAllElements();
					}
				}
					
			}
			catch (myException ex)
			{
				this.cError = new MyE2_String("Fehler beim Pruefen der Adresse !!");
			}
			
			if (!bRueck)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(this.cError));
			}
			
			return oMV;
		}

		public MyE2_MessageVector isValid(String cID_Unformated)
		{
			return new MyE2_MessageVector();
		}
		
	}

	
	
	
	
	
	/*
	 * mask-setting-agent fuer das laden der artikel in die maske
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cID_ArtikelBez, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) 
						throws myException 
		{
			if (bAfterAction)
			{
				
			
				META_MAP_ArtikelBez          	oArtBez = 	new META_MAP_ArtikelBez(cID_ArtikelBez);
				E2_RecursiveSearch_MaskInfo 	oMaskInfo = new E2_RecursiveSearch_MaskInfo(BSK_P_MASK_SEARCH_ArtikelBEZ.this);
				String cID_ADRESSE = 			new SearchAdressID().get_cID_ADRESS();

				if (!(new DotFormatterGermanFixed(cID_ArtikelBez).doFormat()))
				{
					return;
				}
				
				String ID_ArtikelBez = new MyInteger(cID_ArtikelBez).get_cUF_IntegerString(); 
					
				// wenn  nur ein gueltiger MWST-Satz fuer diese Art-Bez existiert, dann 
				// wird dieser 
				My_MWSTSaetze oBSMW = new My_MWSTSaetze(null,ID_ArtikelBez);
				
				oMaskInfo.get_DBComponent("STEUERSATZ", "JT_VPOS_KON").set_cActualMaskValue("");
				if (oBSMW.get_vMWSTArtBez().size()==1)
				{
					My_MWST oBSMWST = (My_MWST)oBSMW.get_vMWSTArtBez().get(0);
					oMaskInfo.get_DBComponent("STEUERSATZ", "JT_VPOS_KON").set_cActualMaskValue(oBSMWST.get_cMWST_Formated());
				}
				
				oMaskInfo.get_DBComponent("ANR1", 				"JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_FormatedValue("ANR1"));
				oMaskInfo.get_DBComponent("ANR2", 				"JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_FormatedValue("ANR2"));
				oMaskInfo.get_DBComponent("ARTBEZ1", 			"JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_FormatedValue("ARTBEZ1"));
				oMaskInfo.get_DBComponent("ARTBEZ2", 			"JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_FormatedValue("ARTBEZ2"));
				oMaskInfo.get_DBComponent("ID_ARTIKEL", 		"JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_FormatedValue("ID_ARTIKEL"));
				oMaskInfo.get_DBComponent("EINHEITKURZ", 		"JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_cEINHEIT_KURZ());
				oMaskInfo.get_DBComponent("EINHEIT_PREIS_KURZ", "JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_cEINHEIT_PREIS_KURZ());
				oMaskInfo.get_DBComponent("MENGENDIVISOR", 		"JT_VPOS_KON").set_cActualMaskValue(oArtBez.get_FormatedValue("MENGENDIVISOR"));
				
		
				
				//zuerst die zahlungs- und lieferbedingungen von der kopfmaske lesen
				String cID_Kopf = ((SQLFieldForRestrictTableRange) BSK_P_MASK_SEARCH_ArtikelBEZ.this.oSQLFieldMAP_Position.get_("ID_VKOPF_KON")).get_cRestictionValue_IN_DB_FORMAT();
				MyDataRecordHashMap hmKopfsatz = new MyDataRecordHashMap("JT_VKOPF_KON","ID_VKOPF_KON",cID_Kopf);

				//2012-05-22: zahlungsbedingung wird bisher immer nur von der EK-seite gelesen
				RECORD_VKOPF_KON  recKOPF_KON = new RECORD_VKOPF_KON(cID_Kopf);
				String cEK_VK = recKOPF_KON.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_EK_KONTRAKT)?"EK":"VK";

				
				//2012-05-22:unsinnig, da schon vorhanden   oMaskInfo.get_DBComponent("ZAHLUNGSBEDINGUNGEN","JT_VPOS_KON").set_cActualMaskValue(hmKopfsatz.get_FormatedValue("ZAHLUNGSBEDINGUNGEN"));
				oMaskInfo.get_DBComponent("LIEFERBEDINGUNGEN","JT_VPOS_KON").set_cActualMaskValue(hmKopfsatz.get_FormatedValue("LIEFERBEDINGUNGEN"));

				try    //kann sein, dass es keine artikelbezeichnung-lief gibt
				{
					//jetzt nachsehen, ob es eine ARTIKEL_BEZ_LIEF - eintragung dazu gibt
					RECLIST_ARTIKELBEZ_LIEF  rlArtbezLief = new RECLIST_ARTIKELBEZ_LIEF(
							"SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ARTIKEL_BEZ="+ID_ArtikelBez+
																			" AND ID_ADRESSE="+cID_ADRESSE+
																			" AND ARTBEZ_TYP="+bibALL.MakeSql(cEK_VK));
					
					//falls einer oder mehrere gefunden, dann den ersten
					RECORD_ARTIKELBEZ_LIEF recArtbez_lief = null;
					if (rlArtbezLief.get_vKeyValues().size()>0) 	recArtbez_lief = rlArtbezLief.get(0);
	
					//2012-05-16: die zahlungsbedinung wird nur noch aus der id_zahlungsbedinung, nicht mehr aus dem textfeld gelesen
					// falls es in den JT_ARTIKELBEZ_LIEF eigene gibt, dann diese nehmen
					//ALT//  if (recArtbez_lief!=null && S.isFull(recArtbez_lief.get_ZAHLUNGSBEDINGUNGEN_cUF_NN("")))
					//ALT//  oMaskInfo.get_DBComponent("ZAHLUNGSBEDINGUNGEN","JT_VPOS_KON").set_cActualMaskValue(recArtbez_lief.get_ZAHLUNGSBEDINGUNGEN_cUF_NN(""));
					if (recArtbez_lief!=null)
					{
						if (recArtbez_lief.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen()!=null)
						{
							E2_ComponentMAP 					oMap = 		BSK_P_MASK_SEARCH_ArtikelBEZ.this.EXT().get_oComponentMAP();
							
							//den drop-down-wert setzen 
							((MyE2IF__DB_Component)oMap.get("ID_ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(recArtbez_lief.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(""));
							//und die werte nachladen
							BS_Sel_Zahlungsbedingungen.LeseID_Zahlungsbedingung_und_setze_korrellierendeFelder(oMap);
						}
					}
					// -----
					
					
					
					if (recArtbez_lief!=null && S.isFull(recArtbez_lief.get_LIEFERBEDINGUNGEN_cUF_NN("")))
						oMaskInfo.get_DBComponent("LIEFERBEDINGUNGEN","JT_VPOS_KON").set_cActualMaskValue(recArtbez_lief.get_LIEFERBEDINGUNGEN_cUF_NN(""));
					
					if (recArtbez_lief!=null)
						oMaskInfo.get_DBComponent("ARTBEZ2","JT_VPOS_KON").set_cActualMaskValue(new RECORD_ARTBEZ_LIEF_extend(recArtbez_lief).get_ARTBEZ_2_Incl_Specials());
					
					
					//jetzt das popup-fenster fuer die infos, dass angebote existieren, aufrufen
					if (bIS_PrimaryCall)
					{
						new ownPopupToShowAngebote();
					}
					
				}
				catch (myException ex) {}
			}
		}
		
	}

	
	private class ownPopupToShowAngebote extends E2_BasicModuleContainer
	{

		private E2_ComponentMAP  oMapVPOS_KON = 		null;
		private E2_ComponentMAP  oMapVPOS_KON_TRAKT = 	null;
		
		public ownPopupToShowAngebote() throws myException
		{
			super();

			BSK_P_MASK_SEARCH_ArtikelBEZ oThis = BSK_P_MASK_SEARCH_ArtikelBEZ.this;
			
			//infos beschaffen
			this.oMapVPOS_KON = 		oThis.EXT().get_oComponentMAP();
			this.oMapVPOS_KON_TRAKT =	oMapVPOS_KON.get_E2_vCombinedComponentMAPs().get(1);
			
			TestingDate oDateStart = oMapVPOS_KON_TRAKT.get_DateActualDBValue("GUELTIG_VON",true, true);
			TestingDate oDateEnde = oMapVPOS_KON_TRAKT.get_DateActualDBValue("GUELTIG_BIS",true, true);
			
			Long lID_ARTIKEL_BEZ = oMapVPOS_KON.get_LActualDBValue("ID_ARTIKEL_BEZ", new Long(-1), new Long(-1));
			
			String cID_VKOPF_KON_UF = ((SQLFieldForRestrictTableRange)oMapVPOS_KON.get_oSQLFieldMAP().get("ID_VKOPF_KON")).get_cRestictionValue_IN_DB_FORMAT();
			
			RECORD_VKOPF_KON 		recVKOPF_KON = new RECORD_VKOPF_KON(cID_VKOPF_KON_UF) ;
			RECORD_ARTIKEL_BEZ 		recArtikelBEZ = new RECORD_ARTIKEL_BEZ(lID_ARTIKEL_BEZ);
			
			
			//ueerpruefen, ob alles ok ist
			if (oDateStart==null || oDateEnde==null)
			{
				//dann hier abbrechen, fehlermeldung und Sortenbezeichnung loeschen
				oMapVPOS_KON.get__DBComp("ID_ARTIKEL_BEZ").prepare_ContentForNew(false);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Gültigkeit festlegen !"));
				return;
			}
			
			
			String cTyp = bibALL.MakeSql(myCONST.VORGANGSART_ABNAHMEANGEBOT);
			String cAdressbezeichner = "Lieferanten";
			if (oThis.o_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
			{
				cTyp = bibALL.MakeSql(myCONST.VORGANGSART_ANGEBOT);
				cAdressbezeichner = "Kunden";
			}
			
			
			RECLIST_VPOS_STD  oRecListStd = new RECLIST_VPOS_STD(
								" SELECT "+ 
								"    JT_VPOS_STD.* "+ 
								" FROM "+ 
								bibE2.cTO()+".JT_VPOS_STD "+ 
								" LEFT OUTER JOIN "+ 
								bibE2.cTO()+".JT_VKOPF_STD "+ 
								"    ON"+ 
								"    ("+
								"        JT_VPOS_STD.ID_VKOPF_STD = JT_VKOPF_STD.ID_VKOPF_STD "+
								"    )"+ 
								" LEFT OUTER JOIN "+ 
								bibE2.cTO()+".JT_VPOS_STD_ANGEBOT "+ 
								"    ON"+ 
								"    ("+
								"        JT_VPOS_STD.ID_VPOS_STD = JT_VPOS_STD_ANGEBOT.ID_VPOS_STD "+
								"    )"+
								" WHERE JT_VKOPF_STD.ID_ADRESSE="+recVKOPF_KON.get_ID_ADRESSE_cUF()+
								" AND   JT_VKOPF_STD.VORGANG_TYP="+cTyp+
								" AND   JT_VPOS_STD.ID_ARTIKEL="+recArtikelBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_ID_ARTIKEL_cUF()+
								" AND   NVL(JT_VPOS_STD.DELETED,'N')='N' "+
								" AND   JT_VPOS_STD.POSITION_TYP="+bibALL.MakeSql(myCONST.VG_POSITION_TYP_ARTIKEL)+
								" AND   ((TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY-MM-DD')<="+oDateStart.get_ISO_DateFormat(true)+
									"   AND   TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'YYYY-MM-DD')>="+oDateStart.get_ISO_DateFormat(true)+")"+
									"   OR    (TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY-MM-DD')>="+oDateStart.get_ISO_DateFormat(true)+
									"   AND   TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'YYYY-MM-DD')<="+oDateEnde.get_ISO_DateFormat(true)+")) " +
								" ORDER BY JT_VPOS_STD.ANR1"
								);
			
			
			if (oRecListStd.get_vKeyValues().size()>0)
			{
				MyE2_Column oColBase = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
				oColBase.add(new MyE2_Label(new MyE2_String("Es existieren Angebote der gleichen Sorte für diesen "+cAdressbezeichner), MyE2_Label.STYLE_TITEL_NORMAL()), E2_INSETS.I_5_5_5_10);
				oColBase.add(new MyE2_Label(new MyE2_String("Falls Sie die Angaben eines Angebotes auf den Kontrakt übernehmen möchten"), MyE2_Label.STYLE_SMALL_PLAIN()), E2_INSETS.I_5_2_5_2);
				oColBase.add(new MyE2_Label(new MyE2_String("wählen Sie das Angebot unten aus ..."), MyE2_Label.STYLE_SMALL_PLAIN()), E2_INSETS.I_5_2_5_2);
				
				MyE2_Grid oGridAngebot = new MyE2_Grid(4,0);
				
				for (int i=0;i<oRecListStd.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_STD recPOS = oRecListStd.get(i);
					
					oGridAngebot.add(new ownButtonSelectAngebot(recPOS.get___KETTE(bibALL.get_Vector("ANR1", "ANR2")),recPOS));
					oGridAngebot.add(new ownButtonSelectAngebot(recPOS.get_ARTBEZ1_cUF_NN("--"),recPOS));
					oGridAngebot.add(new ownButtonSelectAngebot(recPOS.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_VON_cF()+new MyE2_String(" bis ").CTrans()+recPOS.get_DOWN_RECORD_LIST_VPOS_STD_ANGEBOT_id_vpos_std().get(0).get_GUELTIG_BIS_cF(),recPOS));
					oGridAngebot.add(new ownButtonSelectAngebot(recPOS.get_EINZELPREIS_cF_NN(" -?- "),recPOS));
				}

				oColBase.add(oGridAngebot, E2_INSETS.I_5_5_5_5);
				
				this.add(oColBase, E2_INSETS.I_2_2_2_2);
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(700), new MyE2_String("Bitte wählen Sie ein vorhandenes Angebot:"));
			}
			
			
		}
		
		
		
		
		
		private class ownButtonSelectAngebot extends MyE2_Button
		{

			public ownButtonSelectAngebot(String Beschriftung, RECORD_VPOS_STD recID_VPOS_STD)
			{
				super(Beschriftung);
				this.EXT().set_O_PLACE_FOR_EVERYTHING(recID_VPOS_STD);
				this.add_oActionAgent(new ownActionAgent());
			}
			
		}
		
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ownPopupToShowAngebote oThis = ownPopupToShowAngebote.this;
				RECORD_VPOS_STD recID_VPOS_STD = (RECORD_VPOS_STD)((MyE2_Button)execInfo.get_MyActionEvent().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING();
				
				// Bei EK-Kontrakten erst nachsehen, ob der Artikel in den Artikelbezeichnungen gelistet ist
				if (BSK_P_MASK_SEARCH_ArtikelBEZ.this.o_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_EK_KONTRAKT))
				{
					RECORD_ADRESSE recAdresse = recID_VPOS_STD.get_UP_RECORD_VKOPF_STD_id_vkopf_std().get_UP_RECORD_ADRESSE_id_adresse();
					
					HashMap<String, String> hmArtBezLief = recAdresse.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse("JT_ARTIKELBEZ_LIEF.ARTBEZ_TYP='EK'",null,false).get_ID_ARTIKEL_BEZ_hmString_UnFormated("");
					
					if (!hmArtBezLief.containsValue(recID_VPOS_STD.get_ID_ARTIKEL_BEZ_cUF()))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Sorte ",true,recID_VPOS_STD.get_ANR1_cF_NN("")+"-"+recID_VPOS_STD.get_ANR2_cF_NN(""),false," ist bei dem Lieferanten nicht gelistet !",true)));
						return;
					}
				}
				
				((MyE2_DB_MaskSearchField) oThis.oMapVPOS_KON.get__Comp("ID_ARTIKEL_BEZ")).set_cActualMaskValue(recID_VPOS_STD.get_ID_ARTIKEL_BEZ_cUF(), true, true, false);
				oThis.oMapVPOS_KON.get__DBComp("EINZELPREIS").set_cActualMaskValue(recID_VPOS_STD.get_EINZELPREIS_cF());
				oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
		
		
	}
	
	
}
