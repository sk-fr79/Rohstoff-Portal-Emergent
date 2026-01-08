package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibNUM;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerHandler;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE.WK_LIST_BT_PRINT.ENUM_DruckTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_DRUCKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMapCollector;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_Drucktyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfEtiketten;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintBase;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintBefundung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintEingangLieferschein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintEtikett;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintHofschein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintWiegekarteEingangsschein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print.WK_RB_PrintWiegekarte_STORNO;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class WK_RB_MC_PrintHandling extends RB_MaskController {

	public WK_RB_MC_PrintHandling(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	public WK_RB_MC_PrintHandling(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}

	public WK_RB_MC_PrintHandling(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	public WK_RB_MC_PrintHandling(RB_DataobjectsCollector p_dataObjectsCollector) throws myException {
		super(p_dataObjectsCollector);
	}

	public WK_RB_MC_PrintHandling(IF_RB_Component p_component, MyE2_MessageVector mvForErrors) {
		super(p_component, mvForErrors);
	}

	public WK_RB_MC_PrintHandling(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
		super(p_componentMap, mvForErrors);
	}

	public WK_RB_MC_PrintHandling(RB_ComponentMapCollector p_componentMapCollector, MyE2_MessageVector mvForErrors) {
		super(p_componentMapCollector, mvForErrors);
	}

	public WK_RB_MC_PrintHandling(RB_DataobjectsCollector p_dataObjectsCollector, MyE2_MessageVector mvForErrors) {
		super(p_dataObjectsCollector, mvForErrors);
	}

	public WK_RB_MC_PrintHandling(IF_RB_Component p_component, boolean immediateBuild) throws myException {
		super(p_component, immediateBuild);
	}

	public WK_RB_MC_PrintHandling(RB_ComponentMap p_componentMap, boolean immediateBuild) throws myException {
		super(p_componentMap, immediateBuild);
	}

	public WK_RB_MC_PrintHandling(RB_ComponentMapCollector p_componentMapCollector, boolean immediateBuild)
			throws myException {
		super(p_componentMapCollector, immediateBuild);
	}

	public WK_RB_MC_PrintHandling(RB_DataobjectsCollector p_dataObjectsCollector, boolean immediateBuild)
			throws myException {
		super(p_dataObjectsCollector, immediateBuild);
	}


	//
	//  Printhandler mit der JasperHash
	//
	WK_RB_PrintBase wkPrint = null;
	WK_RB_PrintEtikett wkPrintEtikett = null;
	WK_RB_PrintBefundung wkPrintBefundung = null;
	WK_RB_PrintHofschein wkPrintHofschein = null;

	/**
	 * Druck der Wiegekarte 
	 * @author manfred
	 * @date 07.10.2020
	 *
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_PrintHandling printWiegekarte() throws myException  {
		MyE2_MessageVector _mv = bibMSG.getNewMV();
		
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		WK_RB_SelField_Drucktyp selDrucktyp = (WK_RB_SelField_Drucktyp) getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key(), _mv);
		 
		
		
		WK_RB_MC_ValidateOnSave validateOnSave = new WK_RB_MC_ValidateOnSave(compMap, _mv);
		
		if(rec._is_Storno()) {
			_mv._addAlarm("Die Wiegekarte wurde storniert. Es ist kein Druck mehr möglich!");
		}
		
		if (rec.get_raw_resultValue_bigDecimal(WIEGEKARTE.gewicht_nach_abzug_fuhre, BigDecimal.ZERO).compareTo(BigDecimal.ZERO) < 0 ){
			_mv._addAlarm("Das Netto-Gewicht nach allen Abzügen ist negativ! Bitte korrigieren Sie die Daten. Es ist kein Druck möglich!");
		}
		
		if (_mv.isOK()) {
			
			
			WK_RB_ENUM_DRUCKTYP drucktyp = selDrucktyp._getCurrentSelectedTyp();
			
			switch (drucktyp) {
			case WIEGESCHEIN:
				wkPrint = new WK_RB_PrintWiegekarte(rec);
				
				break;
			case EINGANGSCHEIN:
				wkPrint = new WK_RB_PrintEingangLieferschein(rec);
				break;
				
			case WIEGESCHEIN_EINGANGSSCHEIN:
				wkPrint = new WK_RB_PrintWiegekarteEingangsschein(rec);
				break;
				
			default:
				_mv._addAlarm("Drucktyp nicht bekannt!");
				break;
			}	
			
			if (wkPrint != null) {
				try {
					// zuerst die WK abspeichern, um zu prüfen, ob es überhaupt geht
					WK_RB_MASK_ComponentMapCollector compMapColl =  (WK_RB_MASK_ComponentMapCollector) this.get_ComponentMapCollector();
					compMapColl._DoCompleteSaveCycle();
					
					// Prüfung vor Druck	
					validateOnSave._validateBeforePrint(bibMSG.MV());
					
					// drucken...
					if (bibMSG.get_bIsOK()) {
						wkPrint.Print();

						compMapColl.do_CompleteMaskReload();
						
						// Gewicht in der Fuhre speichern...
						setWK_Data_In_Fuhre(_mv);
						
						// navigation List aktualisieren, damit die Liste neu aufgebaut wird
						compMap.getParams().getNavigationList()._REBUILD_COMPLETE_LIST("");
						
					}
				} catch (myException e) {
					_mv._addAlarm("Wiegekarte konnte nicht gedruckt werden!");
				}
				
				
				//
				// Nach dem Druck der Wiegekarte die Etiketten automatisch drucken
				//
				if ( bibMSG.get_bIsOK() &&drucktyp.equals(WK_RB_ENUM_DRUCKTYP.WIEGESCHEIN) || drucktyp.equals(WK_RB_ENUM_DRUCKTYP.WIEGESCHEIN_EINGANGSSCHEIN)) {
					try {
						printEtikettenAutomatik();
					} catch (Exception e) {	
						// keine etiketten drucken	
						
					}
				}
				
				
			}
			
			
			
		}
		
		bibMSG.MV()._add(_mv);
		
		return this;
	}
	
	
	/**
	 * Druck der Etiketten 
	 * @author manfred
	 * @date 07.10.2020
	 *
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_PrintHandling printEtiketten() throws myException  {
		MyE2_MessageVector _mv = bibMSG.getNewMV();
		
		int ncopies = -1;
		String sCopies = "";
		
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		if (rec._is_Storno()) {
			_mv._addAlarm("Die Wiegekarte wurde storniert. Es ist kein Druck mehr möglich!");
		}

	
		if (_mv.isOK()) {
			
			WK_RB_tfEtiketten tf = (WK_RB_tfEtiketten) getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key(),  _mv);
			
			if (!tf.getText().equalsIgnoreCase("") && !bibNUM.isAllNumber(tf.getText()) ) {
				_mv._addAlarm("Wert in Etiketten muss leer oder eine Ganzzahl sein");
				tf._col_back_alarm();
			} else {
				tf._col_back_white();
				sCopies = tf.getText().trim();
				if (S.isFull(sCopies)) {
					ncopies = Integer.parseInt(sCopies);
				}
			}
			
			
			if (_mv.isOK()) {
				
				wkPrintEtikett = new WK_RB_PrintEtikett(rec,ncopies);
				if (wkPrintEtikett != null) {
					wkPrintEtikett.Print();
				}
			}
		}
		
		
		bibMSG.MV()._add(_mv);
		return this;
	}

	
	
	

	/**
	 * Automatischer Druck der Etiketten wenn die 1. Wiegekarte gedruckt wird.
	 * @author manfred
	 * @date 25.06.2021
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_MC_PrintHandling printEtikettenAutomatik() throws myException{
		MyE2_MessageVector _mv = bibMSG.getNewMV();
		
		int ncopies = -1;
		String sCopies = "";
		
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
	
		if (ENUM_MANDANT_DECISION.WIEGEKARTE_DRUCKE_ETIKETT_AUTO.is_YES()){
			if (!rec._is_Storno() && rec.get_myLong_dbVal(WIEGEKARTE.druckzaehler, new MyLong(1L)).get_lValue() == 1)  {
				
				WK_RB_tfEtiketten tf = (WK_RB_tfEtiketten) getRbComp(RecDOWiegekarte.key,WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key(),  _mv);
				
				if (!tf.getText().equalsIgnoreCase("") && !bibNUM.isAllNumber(tf.getText()) ) {
					_mv._addAlarm("Wert in Etiketten muss leer oder eine Ganzzahl sein");
					tf._col_back_alarm();
				} else {
					tf._col_back_white();
					sCopies = tf.getText().trim();
					if (S.isFull(sCopies)) {
						ncopies = Integer.parseInt(sCopies);
					}
				}
				
				if (_mv.isOK()) {
					
					wkPrintEtikett = new WK_RB_PrintEtikett(rec,ncopies);
					if (wkPrintEtikett != null) {
						wkPrintEtikett.Print();
					}
				}
				
			}
		}
				
		bibMSG.MV()._add(_mv);
		return this;
	}
	
	/** 
	 * Drucken des Befundungsauftrags
	 * @author manfred
	 * @throws myException 
	 * @date 22.01.2021
	 *
	 */
	public WK_RB_MC_PrintHandling printBefundung() throws myException {
		
		MyE2_MessageVector _mv = bibMSG.getNewMV();
		
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
			
		if (rec._is_Storno()) {
			_mv._addAlarm("Die Wiegekarte wurde storniert. Es ist kein Druck mehr möglich!");
		}
		
		// die Wiegekarte prüfen und speichern...
		WK_RB_MC_ValidateOnSave validateOnSave = new WK_RB_MC_ValidateOnSave(compMap, _mv);
		if (_mv.isOK()) {

			try {
				// zuerst die WK abspeichern, um zu prüfen, ob es überhaupt geht
				WK_RB_MASK_ComponentMapCollector compMapColl =  (WK_RB_MASK_ComponentMapCollector) this.get_ComponentMapCollector();
				compMapColl._DoCompleteSaveCycle();
				

				// Prüfung vor Druck	
				validateOnSave._validateBeforePrint(bibMSG.MV());
				
				// drucken...
				if (bibMSG.get_bIsOK()) {
					// Befundung drucken	
					wkPrintBefundung = new WK_RB_PrintBefundung(rec);
					if (wkPrintBefundung != null) {
						wkPrintBefundung.Print();
						compMapColl.do_CompleteMaskReload();
					}
					
				}
			} catch (myException e) {
				_mv._addAlarm("Befundung konnte nicht gedruckt werden!");
			}
		}
		
		bibMSG.MV()._add(_mv);
		return this;
	}
	
	/**
	 * Druck des Hofscheins von Hand initiert
	 * @author manfred
	 * @date 07.10.2020
	 *
	 * @return
	 * @throws myException 
	 */
	public WK_RB_MC_PrintHandling printHofschein() throws myException  {

		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
			
		wkPrintHofschein = new WK_RB_PrintHofschein(rec);
		if (wkPrintHofschein != null) {
				wkPrintHofschein.Print();
		}
		

		return this;
	}
	
		

	/**
	 * Druck des Hofscheins, falls automatisch aufgerufen
	 * @author manfred
	 * @date 08.10.2020
	 *
	 * @return
	 * @throws myException
	 */
	public WK_RB_MC_PrintHandling printHofscheinAUTOMATIK() throws myException  {

		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
	
		// Automatische Hofschein-Druck nur bei Erstwägung
		if (rec._get_Waegung1() != null && rec._get_Waegung2() == null) {
			String sIstLieferant 	= rec.get_ufs_dbVal(WIEGEKARTE.ist_lieferant, "N");
			boolean bIstLieferant 	= sIstLieferant.equalsIgnoreCase("Y");
			
			boolean bDruckeWE = bib_Settigs_Mandant.IS__Value("WAAGE_HOFSCHEIN_DRUCKEN_WE", "N","N");
			boolean bDruckeWA = bib_Settigs_Mandant.IS__Value("WAAGE_HOFSCHEIN_DRUCKEN_WA", "N","N");
			boolean bDrucke = false;
			
			// festlegen ob gedruckt werden soll
			bDrucke = (bDruckeWE && bIstLieferant);
			bDrucke |= (bDruckeWA && !bIstLieferant);
			
			if (bDrucke) {
				wkPrintHofschein = new WK_RB_PrintHofschein(rec);
				if (wkPrintHofschein != null) {
					wkPrintHofschein.Print();
				}
			}
		}
		
		return this;
	}
	
	
	/**
	 * Druck der Wiegekarte bei Stornierung, falls automatisch aufgerufen
	 * @author manfred
	 * @date 19.05.2021
	 *
	 * @return
	 * @throws myException
	 */
//	public WK_RB_MC_PrintHandling printWiegekarteSTORNO_AUTOMATIK() throws myException  {
//
//		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
//		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
//	
//		
//		WK_RB_PrintWiegekarte_STORNO wkPrint = new WK_RB_PrintWiegekarte_STORNO(rec);
//		if (wkPrint != null) {
//			wkPrint.Print();
//		}
//
//		return this;
//	}
	
	
	
	
	/**
	 * Fügt das Nettogewicht der Wiegekarte in die Fuhre ein
	 * @throws myException 
	 */
	public WK_RB_MC_PrintHandling setWK_Data_In_Fuhre (MyE2_MessageVector _mv ) throws myException{
	
		WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
		RecDOWiegekarte rec 	= (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
		
		WK_RB_SelField_Drucktyp selDrucktyp = (WK_RB_SelField_Drucktyp) getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key(), _mv);
		if (!selDrucktyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_DRUCKTYP.WIEGESCHEIN) && 
			!selDrucktyp._getCurrentSelectedTyp().equals(WK_RB_ENUM_DRUCKTYP.WIEGESCHEIN_EINGANGSSCHEIN) ) {
			_mv._addInfo("Daten werden nur beim Druck der Wiegekarte in die Fuhre übernommen.");
			return this;
		}
		;
		
		
		String idFuhre = rec.get_ufs_lastVal(WIEGEKARTE.id_vpos_tpa_fuhre);
		if ( idFuhre == null ){
			return this;
		}

		if (rec._get_Waegung2() == null) {
			_mv.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Die Wiegekarte ist nicht vollständig. Das Gewicht kann nicht in die Fuhre übertragen werden.")));
			return this;
		}

		
		// 
		//  Gewicht schreiben... 
		//
		_mv._add(	setGewichtInFuhre(rec)	);
		
		
		
		
		// 2017-06-26 Manfred
		// Erzeugung des Lager-Konto-Eintrags für die Fuhre: Muss gemacht werden, nachdem das Gewicht in der Fuhre eingetragen wurde,
		// sonst kann das Gewicht nicht ermittelt werden
		Vector<String> m_vSQLStatements = new Vector<String>();

		LAG_LagerHandler handler = new LAG_LagerHandler(); 
		handler.LagerBuchung( idFuhre );
		m_vSQLStatements.addAll(handler.getSqlStatements() );

		if (!bibMSG.get_bHasAlarms() && m_vSQLStatements.size() > 0 ){
			boolean bRet = bibDB.ExecSQL(m_vSQLStatements, true);
			if (bRet){
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Der Lager-Datensatz wurde erzeugt")));
			}
		}
		
		
		//2012-05-07/Martin: die kontrollmengen aufbauen und in die fuhre reinschreiben (wenn moeglich)
		this._schreibe_kontroll_mengen(rec);
		//2012-05-07//
		

		m_vSQLStatements.clear();
		return this;
		
	}
	
	
	/**
	 * Gibt true zurück, wenn die Gewichte in den Fuhren als Lade- und Abladegewicht Eingetragen werden soll,
	 * je nach dem wie der Schalter in der Fuhre steht
	 * Folgende Bedingungen müssen erfüllt sein:
	 *  -* LAGER_UMBUCHUNGEN_ERLAUBEN muss im Mandanten-Zusatz gesetzt sein
	 * @author manfred
	 * @date   30.03.2012
	 * @return
	 */
	private boolean bIstWiegegewichteBeidseitigEintragen(){
		
		// nur wenn der Schalter im Mandantenzusatz gesetzt ist
		boolean bErlaubt = __RECORD_MANDANT_ZUSATZ.IS__Value("WIEGEGEWICHTE_BEIDSEITIG_EINTRAGEN", "N", "N");
		
		return bErlaubt;
	}
	
	/**
	 * schreibt das nettogewicht und die Wiegekarten-ID bei Fertigstellung der Wiegekarte in die Fuhre rein.
	 *  
	 * @param oWK_Buchungssatz
	 * @param bdGewichtNetto
	 * @throws myException 
	 */
	private MyE2_MessageVector setGewichtInFuhre(RecDOWiegekarte recWK) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		boolean bIstAblademenge = false;
		
		boolean bWiegegewichtBeidseitigeintragen = bIstWiegegewichteBeidseitigEintragen();
		
		boolean bFuelleLadegewicht = false;
		boolean bFuelleAbladegewicht = false;
		
		
		if (recWK.get_ufs_dbVal(WIEGEKARTE.ist_lieferant).equalsIgnoreCase("Y")){
			bIstAblademenge = true;
		}
		
		// Gewicht ermitteln
		BigDecimal bdGewichtNetto = recWK.getBigDecimalDbValue(WIEGEKARTE.gewicht_nach_abzug);
		if (bdGewichtNetto == null){
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Die Wiegekarte hat kein Nettogewicht. Es wurde kein Eintrag in der Fuhre geschrieben.").CTrans()));
			return mv;
		}
		

		String sGewicht = bibALL.convertBigDecimalToString(bdGewichtNetto,3);
		
		String sIDFuhre 	= recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre);
		String sIDFuhreOrt 	= recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre_ort);
		
		
		if (S.isEmpty(sIDFuhre)) {
			return mv;
		}
		
		
		
		Rec22 recFuhre = new Rec22(_TAB.vpos_tpa_fuhre)._fill_id(sIDFuhre);
		Rec22 recFuhreOrt = null;
		
		
		if (sIDFuhreOrt != null ) {
			recFuhreOrt = new Rec22(_TAB.vpos_tpa_fuhre_ort)._fill_id(sIDFuhreOrt);
			
			if (recFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.pruefung_menge,"N").equals("Y")){
				mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Das Gewicht wurde schon geprüft und kann nicht mehr gesetzt werden.").CTrans()));
				return mv;
			}
			
			// Festlegen, welche Felder beschrieben werden sollen (Abhängig von Ladeseite/Abladeseite)
			if (bIstAblademenge){
				bFuelleAbladegewicht = true;
				bFuelleLadegewicht = recFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.ablademenge_rechnung,"N").equals("N");
			} else {
				bFuelleAbladegewicht = recFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.lademenge_gutschrift,"N").equals("N"); 
				bFuelleLadegewicht = true;
			}
			
			if (bFuelleAbladegewicht){
				// prüfen, ob da schon der gleiche wert drin steht...
				BigDecimal bdAnteilAblade = recFuhreOrt.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge);
				if (bdAnteilAblade != null) {
					if(!bdAnteilAblade.equals(bdGewichtNetto)) {
						mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
					}
				} else {
					recFuhreOrt._setNewValue(VPOS_TPA_FUHRE_ORT.anteil_ablademenge, bdGewichtNetto, mv);
				}
			}
			
			if (bFuelleLadegewicht) {
				
				// prüfen, ob da schon der gleiche wert drin steht...
				BigDecimal bdAnteilLade = recFuhreOrt.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_lademenge);
				if (bdAnteilLade != null) {
					if(!bdAnteilLade.equals(bdGewichtNetto)) {
						mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
					}
				} else {
					recFuhreOrt._setNewValue(VPOS_TPA_FUHRE_ORT.anteil_lademenge, bdGewichtNetto, mv);
				}
			}			

			if (recFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.datum_lade_ablade) == null) {
				recFuhreOrt._setNewValue(VPOS_TPA_FUHRE_ORT.datum_lade_ablade, recWK.getDateDbValue(WIEGEKARTE.erzeugt_am), mv);
			} else {
				mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon ein Lade-/Abladedatum.").CTrans()));
			}
			
			
			// speichern
			recFuhreOrt._SAVE(true, mv);
			if (!mv.get_bHasAlarms()) {
				mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Das Gewicht wurde in die Fuhre übertragen.").CTrans()));
			}
			return mv;
			
			
		} else if (sIDFuhre != null) {
			
			if (bIstAblademenge || bWiegegewichtBeidseitigeintragen){
				
				bFuelleAbladegewicht = true;
				bFuelleLadegewicht = recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.ablademenge_rechnung,"N").equals("N");
				
				if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.pruefung_ablademenge,"N").equals("Y")){
					mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Das Gewicht wurde schon geprüft und kann nicht mehr gesetzt werden.").CTrans()));
					return mv;
				}

				//
				// Gewichte setzen
				//
				if (bFuelleAbladegewicht) {
					// prüfen, ob da schon irgend ein Wert drin steht...
					if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn)  != null){
						// prüfen, ob da schon der gleiche wert drin steht...
						if (recFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn,BigDecimal.ZERO).compareTo(bdGewichtNetto) != 0 ){
								mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Ablademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						recFuhre._setNewValue(VPOS_TPA_FUHRE.anteil_ablademenge_abn, bdGewichtNetto, mv);
					}
				}
				

				
				if (bFuelleLadegewicht){
					// prüfen, ob da schon irgend ein Wert drin steht...
					if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_abn)  != null){
						if (recFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_abn,BigDecimal.ZERO).compareTo(bdGewichtNetto) != 0 ){
							mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Lademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						recFuhre._setNewValue(VPOS_TPA_FUHRE.anteil_lademenge_abn, bdGewichtNetto, mv);
					}
				}


				
				
				if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.datum_abladen) == null) {
					recFuhre._setNewValue(VPOS_TPA_FUHRE.datum_abladen, recWK.getDateDbValue(WIEGEKARTE.erzeugt_am), mv);
				} else {
					mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon ein Abladedatum.").CTrans()));
				}

			}
			
			
			/**
			 * Ladeseite
			 */
			if (!bIstAblademenge || bWiegegewichtBeidseitigeintragen){

				bFuelleAbladegewicht = recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.lademenge_gutschrift,"N").equals("N"); 
				bFuelleLadegewicht = true;
				
				
				if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.pruefung_lademenge,"N").equals("Y")){
					mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Das Gewicht wurde schon geprüft und kann nicht mehr gesetzt werden.").CTrans()));
					return mv;
				}
								
				//
				// Gewichte setzen
				//
				if (bFuelleAbladegewicht) {

					// prüfen, ob da schon irgend ein Wert drin steht...
					if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_lief) != null){
						// prüfen, ob da schon der gleiche wert drin steht...
						if (recFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_lief,BigDecimal.ZERO).compareTo(bdGewichtNetto) != 0 ){
								mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Ablademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						recFuhre._setNewValue(VPOS_TPA_FUHRE.anteil_ablademenge_lief, bdGewichtNetto, mv);
					}
				}
				
				if (bFuelleLadegewicht){
					// prüfen, ob da schon irgend ein Wert drin steht...
					if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief) != null){
						// prüfen, ob da schon der gleiche wert drin steht...
						if (recFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief,BigDecimal.ZERO).compareTo(bdGewichtNetto) != 0 ){
								mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon einen abweichenden Gewichtswert in der Lademenge eingetragen. Der Wert wurde nicht aktualisiert.").CTrans()));
						}
					} else {
						recFuhre._setNewValue(VPOS_TPA_FUHRE.anteil_lademenge_lief, bdGewichtNetto, mv);
					}
					
				}
				
				
				// LADEDATUM
				if (recFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.datum_aufladen) == null) {
					recFuhre._setNewValue(VPOS_TPA_FUHRE.datum_aufladen, recWK.getDateDbValue(WIEGEKARTE.erzeugt_am), mv);
				} else {
					mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Die Fuhre hat schon ein Ladedatum.").CTrans()));
				}
			}
		
		}
		
		
		// Speichern des Wertes...	
		recFuhre._SAVE(true, mv);
//		if (!mv.get_bHasAlarms()) {
//			mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String( "Das Gewicht wurde in die Fuhre übertragen.").CTrans()));
//		}
					
	    return mv;
			
		
	}
	


	
	/*
	 * 2012-05-07/Martin: Die gewichtsfelder fuer die kontrolle muessen hier manuell korrigiert werden, da der fuhrenspeicherdaemon nicht aufgerufen wird
	 *             konservativ abgesichert, da noch von manfred zu pruefen
	 */
	private void _schreibe_kontroll_mengen(RecDOWiegekarte recWK)
	{
		try
		{
			PRUEF_RECORD_VPOS_TPA_FUHRE  oRecFuhreKorr = null;
			
			if (S.isFull(recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre_ort) ) )
			{
				RECORD_VPOS_TPA_FUHRE_ORT  recOrt = new RECORD_VPOS_TPA_FUHRE_ORT(recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre_ort));
				oRecFuhreKorr = new PRUEF_RECORD_VPOS_TPA_FUHRE(recOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre(),false);
			}
			else if (S.isFull(recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre)))
			{
				oRecFuhreKorr = new PRUEF_RECORD_VPOS_TPA_FUHRE(recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre), false);
			}
			
			if (oRecFuhreKorr != null)
			{
				oRecFuhreKorr.__writeSQLStatemtents_MengenSituation_undFuhrenStatus(true);
			}
			
		}
		catch(Exception ex)
		{
			
		}
		
	}


	

	
	
	
	
	
	
	
}
