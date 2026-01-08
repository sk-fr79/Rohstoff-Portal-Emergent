package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * klasse zum suchen von tannsportpreisen, die real als preise fuer fuhren erfasst wurden
 * in der statisitik werden nur reine fuhren ohne fuhrenorte beruecksichtigt.
 * Die ermittlung erfolgt normiert auf die preiseinheit der gefahrenen sorte
 *  
 * @author martin
 *
 */
public abstract class TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis
{

	private static 	String SAVEKEY_CB_UNDEF_AS_REAL = 	"CB_UNDEF_AS_REAL"; 
	private static 	String SAVEKEY_DATE_MINIMUM = 			"DATE_MINIMUM";
	private static 	String SAVEKEY_BASISWERT_FUER_NULL =		"SAVEKEY_BASISWERT_FUER_NULL";
	
	private 		String cDATE_OLDEST = null;                	//das aelteste zu beruecksichtigende datum (benutzt wird das belegdatum, wenn null, das erstellungsdatum des datensatzes)

	//sollen undefinierte Kosten den NICHT-Neutralen kosten zugeteilt werden (siehe tabelle JT_FUHREN_KOSTEN_TYP)
	private 		boolean	 			bSetzeUndefinierteAlsNichtNeutral= true;
	private 		String 				cDATE_OLDEST_ISO_Format = null;
	
	private 		BigDecimal 		 	bdValueWhenNoPrice = BigDecimal.ZERO;
	private 		RECORD_EINHEIT  	recEINHEIT_USED_MODE = null;

	private   		String  			cID_ADRESSE_BASE = null;
	private   		String  			cID_KOSTEN_LIEFERBED_ADR = null;
	
//	private         boolean   			bUseTempTable = true;
	
	/**
	 * 
	 * @param date_OLDEST (uebergeben als realer datumswert z.B. 31.12.2014)
	 * @param setzeUndefinierteAlsNichtNeutral
	 * @param cID_ADRESSE_BASE  (wenn <> NULL, dann werden alle kosten einer firma berechnet
	 * @param cID_KOSTEN_LIEFERBED_ADR (wenn <> NULL, dann wird nur ein kostenzeile berechnet
	 * @throws myException
	 */
	public TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis( String date_OLDEST, boolean setzeUndefinierteAlsNichtNeutral, String cid_ADRESSE_BASE, String cid_KOSTEN_LIEFERBED_ADR) throws myException
	{
		super();
		this.bSetzeUndefinierteAlsNichtNeutral = setzeUndefinierteAlsNichtNeutral;
		this.cID_ADRESSE_BASE = cid_ADRESSE_BASE;
		this.cID_KOSTEN_LIEFERBED_ADR = cid_KOSTEN_LIEFERBED_ADR;
		
		if (S.isEmpty(this.cID_ADRESSE_BASE) && S.isEmpty(this.cID_KOSTEN_LIEFERBED_ADR)) {
			throw new myException(this,"Error: ID_ADRESS or ID_KOSTEN_LIEFERBED_ADR  MUSTNOT BE NULL");
		}
		
		this.pruefe_und_setzeDatum(date_OLDEST);
		
		this.recEINHEIT_USED_MODE = new TP_Helper_StelleFest_HaefigsteAbrechnungseinheit().get_recEinheitHaeufigst();
		
	}
	
	/**
	 * 
	 * @param date_OLDEST (uebergeben als realer datumswert z.B. 31.12.2014)
	 * @param setzeUndefinierteAlsNichtNeutral
	 * @throws myException
	 */
	public TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis( String date_OLDEST, boolean setzeUndefinierteAlsNichtNeutral) throws myException
	{
		super();
		this.bSetzeUndefinierteAlsNichtNeutral = setzeUndefinierteAlsNichtNeutral;
		
		this.pruefe_und_setzeDatum(date_OLDEST);
		
		this.recEINHEIT_USED_MODE = new TP_Helper_StelleFest_HaefigsteAbrechnungseinheit().get_recEinheitHaeufigst();
		
	}

	
	
	public abstract MyE2_MessageVector  do_After_UpdateStatements() throws myException;
	
	
	private boolean pruefe_und_setzeDatum(String cDATE_FORMATED) {
		boolean bRueck = true;
		
		if (S.isFull(cDATE_FORMATED)) {
			MyDate oDateTest = new MyDate(cDATE_FORMATED);
			if (oDateTest.get_bOK()) {
				this.cDATE_OLDEST = oDateTest.get_cDateStandardFormat();
				this.cDATE_OLDEST_ISO_Format = oDateTest.get_cDBFormatErgebnis();
			} else {
				this.cDATE_OLDEST = null;
				this.cDATE_OLDEST_ISO_Format = null;
				bRueck = false;
			}
		} else {
			this.cDATE_OLDEST = null;
			this.cDATE_OLDEST_ISO_Format = null;
			bRueck = false;
		}
		
		return bRueck;
	}

	
	public void SHOW_SettingsWindow() throws myException {
		new ownPopupWindowFrageNachDaten();
	}

	

	
	private String get_cSQL_UPDATE_STATEMENT_NORMAL() {
		String cQUERY="UPDATE JT_KOSTEN_LIEFERBED_ADR KL "+
						" SET KL.BETRAG_KOSTEN=("+this.get_cSQL_BERECHNUNGSBLOCK()+") WHERE KL.ID_MANDANT=" +bibALL.get_ID_MANDANT()+
						" AND NVL(("+this.get_cSQL_BERECHNUNGSBLOCK()+"),0)<>0 "+
						" AND NVL(KL.LOCK_PRICE,'N')='N' ";
		return cQUERY+this.cADD_ON_QUERY();
	}
	

	//standard-Werte nur fuer sorten mit der standard-abrechnungseinheit (t)
	private String get_cSQL_UPDATE_STATEMENT_STANDARD_WERT(String cVALUE_STANDARD_UF, String cID_EINHEIT_PREIS_BASE) {
		String cQUERY=	"UPDATE JT_KOSTEN_LIEFERBED_ADR KL "+
						" SET KL.BETRAG_KOSTEN=" +cVALUE_STANDARD_UF+ " WHERE KL.ID_MANDANT=" +bibALL.get_ID_MANDANT()+
						" AND NVL(("+this.get_cSQL_BERECHNUNGSBLOCK()+"),0)=0 "+
						" AND NVL(KL.LOCK_PRICE,'N')='N' "+
						" AND KL.ID_ARTIKEL IN (SELECT AR.ID_ARTIKEL FROM JT_ARTIKEL AR  WHERE AR.ID_EINHEIT_PREIS="+cID_EINHEIT_PREIS_BASE+ " )";
		
		return cQUERY+this.cADD_ON_QUERY();
	}

	
	//standard-Werte nur fuer sorten mit der standard-abrechnungseinheit (t)
	private String get_cSQL_UPDATE_STATEMENT_UNDEF_WERT(String cID_EINHEIT_PREIS_BASE) {
		String cQUERY=	"UPDATE JT_KOSTEN_LIEFERBED_ADR KL "+
						" SET KL.BETRAG_KOSTEN=NULL WHERE KL.ID_MANDANT=" +bibALL.get_ID_MANDANT()+
						" AND NVL(("+this.get_cSQL_BERECHNUNGSBLOCK()+"),0)=0 "+
						" AND NVL(KL.LOCK_PRICE,'N')='N' "+
						" AND KL.ID_ARTIKEL NOT IN (SELECT AR.ID_ARTIKEL FROM JT_ARTIKEL AR  WHERE AR.ID_EINHEIT_PREIS="+cID_EINHEIT_PREIS_BASE+ " )";
		
		return cQUERY+this.cADD_ON_QUERY();
	}

	
	private String cADD_ON_QUERY() {
		String cSQL_ADDON = "";
		if (S.isFull(this.cID_ADRESSE_BASE)) {
			cSQL_ADDON+= (" AND  KL."+_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS+"="+this.cID_ADRESSE_BASE);
		}
		if (S.isFull(this.cID_KOSTEN_LIEFERBED_ADR)) {
			cSQL_ADDON+= (" AND KL."+_DB.KOSTEN_LIEFERBED_ADR$ID_KOSTEN_LIEFERBED_ADR+"="+this.cID_KOSTEN_LIEFERBED_ADR);
		}
		
		return cSQL_ADDON;
	}
	
	
	
	private String get_cSQL_BERECHNUNGSBLOCK() {
		String cZUSATZ = this.bSetzeUndefinierteAlsNichtNeutral?"ST.TP_KOST_PRO_ABRECH_EH_UDEF":"0";
		String cQUERY = "SELECT "+
				" SUM((" +cZUSATZ+ "+ST.TP_KOST_PRO_ABRECH_EH_REAL)*(ST.ANTEIL_LADEMENGE_LIEF/ST.MENGENDIVISOR))"+
				" / "+
				" (SUM(ST.ANTEIL_LADEMENGE_LIEF/ST.MENGENDIVISOR)) "+
				" FROM S"+bibALL.get_ID_MANDANT()+"_KOSTEN_AUS_FUHREN ST "+
				" WHERE ST.ID_ADRESSE_LAGER_START=KL.ID_ADRESSE "+ 
				" AND ST.ID_ADRESSE_LAGER_ZIEL =KL.ID_ADRESSE_ZIEL "+ 
				" AND ST.ID_ARTIKEL= KL.ID_ARTIKEL "+ 
				" AND NVL(ST.ANTEIL_LADEMENGE_LIEF,0)>0 "+
				" AND ST.STATUS_BUCHUNG>0"+
				" AND ST.DATUM >='" +this.cDATE_OLDEST_ISO_Format+ "'";
		
		return cQUERY;
	}
	
	
    
	
	
	
	
	/*
	 * wird diese klasse gestartet, dann gibt das system die moeglichkeit, rahmendaten zu erfassen, die aber auch mit dem konstruktor uebergeben werden
	 * @author martin
	 *
	 */
	public class ownPopupWindowFrageNachDaten extends E2_BasicModuleContainer {

		private MyE2_TextField_DatePOPUP_OWN  	oDATE_OLDEST = new MyE2_TextField_DatePOPUP_OWN(S.NN(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.this.cDATE_OLDEST,bibALL.get_cDateNOW()), 100, true, true);
		private MyE2_CheckBox  					oCB_SetzeUndefinierteAlsNichtNeutral = new MyE2_CheckBox(true, false);
		private MyE2_TextField  				oTF_ValueWhenNothingFound = new MyE2_TextField("0",100,12);
		
		
		public ownPopupWindowFrageNachDaten() throws myException {
			super();
			TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis oThis = TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.this;
			
			MyE2_Grid  oGridINNEN = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			this.add(oGridINNEN, E2_INSETS.I(5,5,5,5));
			
			oGridINNEN.add(new MyE2_Label(new MyE2_String("Ermittlung von Kostensätzen aus den erfaßten Transportrechnungen  ..."),MyE2_Label.STYLE_TITEL_BIG_PLAIN()),2,E2_INSETS.I(0,2,0,10));
			
			oGridINNEN.add(new MyE2_Label(new MyE2_String("Das älteste zu berücksichtigende Datum: "),MyE2_Label.STYLE_NORMAL_BOLD()),1,E2_INSETS.I(0,2,5,5));
			oGridINNEN.add(this.oDATE_OLDEST,1,E2_INSETS.I(0,2,0,5));
			
			oGridINNEN.add(new MyE2_Label(new MyE2_String("Fuhren-Kostenpositionen mit undefiniertem Kostentyp einschließen: "),MyE2_Label.STYLE_NORMAL_BOLD()),1,E2_INSETS.I(0,2,5,5));
			oGridINNEN.add(this.oCB_SetzeUndefinierteAlsNichtNeutral,1,E2_INSETS.I(0,2,0,5));
			
			oGridINNEN.add(new MyE2_Label(new MyE2_String("Undefinierte Kosten (nur bei Abrechnung der Einheit: ",true,oThis.recEINHEIT_USED_MODE.get_EINHEITKURZ_cUF_NN("<eh>"),false,")",false),MyE2_Label.STYLE_NORMAL_BOLD()),1,E2_INSETS.I(0,2,5,30));
			oGridINNEN.add(this.oTF_ValueWhenNothingFound,1,E2_INSETS.I(0,2,0,30));
			
			
			oGridINNEN.add(new E2_ComponentGroupHorizontal(0, new ownButtonSave(), new ownButtonCancel(), E2_INSETS.I(0,0,10,0)));
			
			//einstellungen laden
			HashMap<String, String> hmSaveValues = new UserSetter().READ();
			this.oCB_SetzeUndefinierteAlsNichtNeutral.setSelected(false);
			if (hmSaveValues != null) {
				if (S.NN(hmSaveValues.get(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_CB_UNDEF_AS_REAL)).equals("Y") ) {
					this.oCB_SetzeUndefinierteAlsNichtNeutral.setSelected(true);
				}
				if (S.isFull(hmSaveValues.get(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_DATE_MINIMUM)) ) {
					this.oDATE_OLDEST.get_oTextField().setText(hmSaveValues.get(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_DATE_MINIMUM));
				}
				if (S.isFull(hmSaveValues.get(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_BASISWERT_FUER_NULL)) ) {
					this.oTF_ValueWhenNothingFound.setText(hmSaveValues.get(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_BASISWERT_FUER_NULL));
				}
			}
			
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300), new MyE2_String("Preise suchen und ermitteln ..."));
		}
		
		private class ownButtonSave extends MyE2_Button {
			public ownButtonSave() throws myException	{
				super(new MyE2_String("OK"));
				this.add_oActionAgent(new ownActionSave());
			}
			
			private class ownActionSave extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis 	oThis = TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.this;
					ownPopupWindowFrageNachDaten 				ooThis = ownPopupWindowFrageNachDaten.this;	
					
					if (oThis.pruefe_und_setzeDatum(ownPopupWindowFrageNachDaten.this.oDATE_OLDEST.get_oFormatedDateFromTextField())) {
						
						MyBigDecimal  bdWertWennNull = new MyBigDecimal(ooThis.oTF_ValueWhenNothingFound.getText());
						
						if (bdWertWennNull.get_bOK()) {
							HashMap<String, String>  hmWerte = new HashMap<String, String>();
							hmWerte.put(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_CB_UNDEF_AS_REAL, ownPopupWindowFrageNachDaten.this.oCB_SetzeUndefinierteAlsNichtNeutral.isSelected()?"Y":"N");
							hmWerte.put(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_DATE_MINIMUM, S.NN(ownPopupWindowFrageNachDaten.this.oDATE_OLDEST.get_oFormatedDateFromTextField()));
							hmWerte.put(TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_BASISWERT_FUER_NULL, S.NN(bdWertWennNull.get_FormatedRoundedNumber(2)));
							
							new UserSetter().SAVE(hmWerte);
							
							//jetzt die aktuellen objekt-werte fuer die weiterverarbeitung sichern (ausser datum, ist schon hinterlegt)
							oThis.bSetzeUndefinierteAlsNichtNeutral = ownPopupWindowFrageNachDaten.this.oCB_SetzeUndefinierteAlsNichtNeutral.isSelected();
							oThis.bdValueWhenNoPrice = bdWertWennNull.get_bdWert();
							
							
							//jetzt die updates ausfuehren
							Integer iCountNormal = new Integer(0);
							Integer iCountStandard = new Integer(0);
							Integer iCountUndef = new Integer(0);
							
							Vector<Integer>  vCountNormal = new Vector<Integer>();
							Vector<Integer>  vCountStandard = new Vector<Integer>();
							Vector<Integer>  vCountUndef = new Vector<Integer>();
							
							vCountNormal.add(iCountNormal);
							vCountStandard.add(iCountStandard);
							vCountUndef.add(iCountUndef);
							
							String cID_EINHEIT__PREIS = oThis.recEINHEIT_USED_MODE.get_ID_EINHEIT_cUF();
							String cPREIS_STD_UF = MyNumberFormater.formatDez(oThis.bdValueWhenNoPrice, 2, false, '.', ',', true);
							
							
//								//hier die temporaere tabelle ST_KOSTEN_AUS_FUHREN pruefen und ggf aufbauen
//								Prepare_StandardTempTable oPrepStandard = new Prepare_StandardTempTable("KOSTEN_AUS_FUHREN");
//								if (oPrepStandard.CHECK_IF_MUST_BE_EXECUTED()) {
//									bibMSG.add_MESSAGE(oPrepStandard.EXECUTED_BEFORE_REPORT());
//								}
							
							
							if (bibMSG.get_bIsOK()) {
							
								boolean bOK_NORMAL = true;
								boolean bOK_STANDARD = true;
								boolean bOK_UNDEF = true;
								
								bOK_NORMAL=bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(
																oThis.get_cSQL_UPDATE_STATEMENT_NORMAL(), true, vCountNormal);
								
								DEBUG.System_println(oThis.get_cSQL_UPDATE_STATEMENT_NORMAL());
								
								bOK_STANDARD=bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(
																oThis.get_cSQL_UPDATE_STATEMENT_STANDARD_WERT(cPREIS_STD_UF, cID_EINHEIT__PREIS), true, vCountStandard);
								
								DEBUG.System_println(oThis.get_cSQL_UPDATE_STATEMENT_STANDARD_WERT(cPREIS_STD_UF, cID_EINHEIT__PREIS));
								
								bOK_UNDEF=bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(
																oThis.get_cSQL_UPDATE_STATEMENT_UNDEF_WERT(cID_EINHEIT__PREIS), true, vCountUndef);
								
								DEBUG.System_println(oThis.get_cSQL_UPDATE_STATEMENT_UNDEF_WERT(cID_EINHEIT__PREIS));
								
								
								String cSQL_CountLock = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+
													".JT_KOSTEN_LIEFERBED_ADR KL WHERE NVL(KL.LOCK_PRICE,'N')='Y'"+oThis.cADD_ON_QUERY());
								String cSQL_CountAll = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_KOSTEN_LIEFERBED_ADR KL WHERE 1=1 "+oThis.cADD_ON_QUERY());
							
								
								
								if (bOK_NORMAL && bOK_STANDARD && bOK_UNDEF) {
	
									bibMSG.add_MESSAGE(oThis.do_After_UpdateStatements());
									
									if (bibMSG.get_bIsOK()) {
										ownPopupWindowFrageNachDaten.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
										MyE2_String cSTATUS = new MyE2_String("Zahl geprüfter Kostensätze: ",true,cSQL_CountAll,false,
												" //  Aktualisiert (auf Basis Transportkosten): ",true,""+vCountNormal.get(0).intValue(),false,
												" //  Übersprungen (gesperrt): ",true,cSQL_CountLock,false,
												" //  Aktualisiert (mit Standardwert): ",true,""+vCountStandard.get(0).intValue(),false,
												" //  Preis LEER, weil Abrechnungseinheit nicht ",true,"<"+oThis.recEINHEIT_USED_MODE.get_EINHEITKURZ_cF_NN("?")+">: "+vCountUndef.get(0).intValue(),false);
										bibMSG.add_MESSAGE(new MyE2_Info_Message(cSTATUS));
									}
									
								} else {
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Ausführung ...")));
									if (!bOK_NORMAL) {
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oThis.get_cSQL_UPDATE_STATEMENT_NORMAL()));
									}
									if (!bOK_STANDARD) {
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oThis.get_cSQL_UPDATE_STATEMENT_STANDARD_WERT(cPREIS_STD_UF, cID_EINHEIT__PREIS)));
									}
									if (!bOK_UNDEF) {
										bibMSG.add_MESSAGE(new MyE2_Alarm_Message(oThis.get_cSQL_UPDATE_STATEMENT_UNDEF_WERT(cID_EINHEIT__PREIS)));
									}
								}
							
							}
							
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ungültige Zahl ..")));
						}
					} else {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ungültiges Datum ..")));
					}
				}
			}
		}
		
		private class ownButtonCancel extends MyE2_Button {
			public ownButtonCancel()	{
				super(new MyE2_String("Abbruch"));
				this.add_oActionAgent(new ownActionCancel());
			}
			
			private class ownActionCancel extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					ownPopupWindowFrageNachDaten.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			}
		}
	}
	
	
	
	
	
	public String get_cDATE_OLDEST() {
		return cDATE_OLDEST;
	}


	public boolean get_bSetzeUndefinierteAlsNichtNeutral()	{
		return bSetzeUndefinierteAlsNichtNeutral;
	}


	public String get_cDATE_OLDEST_ISO_Format() {
		return cDATE_OLDEST_ISO_Format;
	}


	public String get_FormatedKostenValueWhenNull() {
		return MyNumberFormater.formatDez(this.bdValueWhenNoPrice, 2, true);
	}
	
	
	
	//status-sicherungsklasse
	private class UserSetter extends E2_UserSetting_HashMap {

		public UserSetter() {
			super(ENUM_USER_SAVEKEY.SESSION_HASH_USER_VARIANTEN_KOSTENERMITTLUNG.name(), 
					bibALL.get_Vector(
							TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_CB_UNDEF_AS_REAL, 
							TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_DATE_MINIMUM,
							TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis.SAVEKEY_BASISWERT_FUER_NULL));
		}
		
		public void SAVE(HashMap<String, String> hmValues) throws myException {
			this.STORE(ENUM_USER_SAVEKEY.SESSION_HASH_USER_VARIANTEN_KOSTENERMITTLUNG.name(), hmValues);
		}
		
		@SuppressWarnings("unchecked")
		public HashMap<String, String> READ() throws myException {
			return  (HashMap<String, String>) this.get_Settings(ENUM_USER_SAVEKEY.SESSION_HASH_USER_VARIANTEN_KOSTENERMITTLUNG.name());
		}
		
	}

	

	
}
