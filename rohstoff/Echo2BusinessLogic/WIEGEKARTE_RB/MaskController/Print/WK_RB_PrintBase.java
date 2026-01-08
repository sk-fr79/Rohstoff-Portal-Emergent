package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.Print;

import java.sql.Timestamp;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public abstract class WK_RB_PrintBase {

	// Druckroutine, abhängig vom Druckaufrag (WK, ES/LS,BUERO,ETIKETT)
	public abstract void Print() throws myException;
	
	// Jasper-Hash-Vector, sammelt alle Jasperhashes zum Drucken
	protected V_JasperHASH m_vecJasperHash = new V_JasperHASH();
	
	
	protected enum ENUM_PRINT_PARAMETERS {
		// Ablaufkontrolle
		DRUCKE_EINGANGSSCHEIN_LIEFERSCHEIN("DRUCKE_EINGANGSSCHEIN_LIEFERSCHEIN"),
		DRUCKE_WIEGEKARTE("DRUCKE_WIEGEKARTE"),
		DRUCKE_AUSGANG_BUERO("DRUCKE_AUSGANG_BUERO"),
		NUM_COPY("NUM_COPY"),
		NUM_COPY_GESAMT("NUM_COPY_GESAMT"),
		
		// WK-Daten
		ID_WIEGEKARTE("ID_WIEGEKARTE"),
		ID_WIEGEKARTE_PARENT("ID_WIEGEKARTE_PARENT"),

		ID_ADRESSE("ID_ADRESSE"),
		ID_ADRESSE_LAGER("ID_ADRESSE_LAGER"),

		EXTRA("EXTRA"),	// WE,WA
		WE_WA("WE_WA"),	// WE,WA redundant zu EXTRA...
		
		ID_FUHRE("ID_FUHRE"),
		HAT_FUHRE("HAT_FUHRE"),  // 0,1
		
		
		ID_WAAGE_STANDORT("ID_WAAGE_STANDORT"), 
		ID_ADRESSE_KUNDE("ID_ADRESSE_KUNDE"),
		ID_ADRESSE_SPEDITION("ID_ADRESSE_SPEDITION"),
		ID_ADRESSE_ABN_STRECKE("ID_ADRESSE_ABN_STRECKE"),
		ID_ARTIKEL("ID_ARTIKEL"),
		ID_ARTIKEL_BEZ("ID_ARTIKEL_BEZ"),
		IST_GESAMTVERWIEGUNG("IST_GESAMTVERWIEGUNG"),
		TYP_WIEGEKARTE("TYP_WIEGEKARTE"),
		GUETERKATEGORIE("GUETERKATEGORIE"),
		KENNZEICHEN("KENNZEICHEN"),
		TRAILER("TRAILER"), 
		ID_EINGANGSSCHEIN("ID_EINGANGSSCHEIN"), 
		ID_ABZUG_GEBINDE("ID_ABZUG_GEBINDE"), 
		NUM_COPY_GEBINDE("NUM_COPY_GEBINDE")		, 
		NUM_COPY_GEBINDE_GESAMT("NUM_COPY_GEBINDE_GESAMT"),
		MODULNAME("MODULNAME"),
		
		STORNO("STORNO"),
		ID_WIEGEKARTE_STORNO("ID_WIEGEKARTE_STORNO"),
		ID_CONTAINER_EIGEN("ID_CONTAINER_EIGEN")
		;
		
	    private String     _Name = null;

	    ENUM_PRINT_PARAMETERS(String name) {
	       this._Name = name;
	    }
		
		public String Name(){
			return this._Name;
		}
	}
	
	// Basis-Vektor für alle Drucke...
	V_JasperHASH m_v_jasper = new V_JasperHASH();
	
	
	// die Wiegekarte
	RecDOWiegekarte _recWK = null;
	
	// die ES-Nr
	String _esNr = "";
	
	/**
	 * Standard-Konstruktor
	 * @author manfred
	 * @date 01.10.2020
	 *
	 */
	public WK_RB_PrintBase(RecDOWiegekarte recWK) {
		_recWK = recWK;
	}
	
	/**
	 * Hilfskonstruktor, falls der Druck von aussen angetriggert werden soll.
	 * RecWiegekarte wird im Status MASK_STATUS.EDIT erzeugt.
	 * 
	 * @author manfred
	 * @date 05.10.2020
	 *
	 * @param idWiegekarte
	 * @throws myException
	 */
	public WK_RB_PrintBase(String idWiegekarte) throws myException {
		RecDOWiegekarte wk = new RecDOWiegekarte(MASK_STATUS.EDIT)._fill_id(idWiegekarte);
		_recWK = wk;
		
	}
	

	
	
	
	/**
	 * Setzt alle möglichen Extra-Werte in das übergebene JasperHash
	 * @author manfred
	 * @date 01.10.2020
	 *
	 * @param jasperHash
	 */
	protected WK_RB_PrintBase setJasper_BaseData ( E2_JasperHASH jasperHash) {
		
		if (_recWK != null && jasperHash != null) {
			try {
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_WIEGEKARTE.Name(), 			_recWK.get_ufs_dbVal(WIEGEKARTE.id_wiegekarte, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_EINGANGSSCHEIN.Name(), 		S.isFull(_esNr) ? _esNr :  _recWK.get_ufs_dbVal(WIEGEKARTE.es_nr, ""));
				
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_WIEGEKARTE_PARENT.Name(), 	_recWK.get_ufs_dbVal(WIEGEKARTE.id_wiegekarte_parent, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ADRESSE.Name(), 			_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ADRESSE_KUNDE.Name(), 		_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ADRESSE_LAGER.Name(), 		_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lager, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_FUHRE.Name(), 				_recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.HAT_FUHRE.Name(), 				S.isEmpty(_recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre, "")) ? "0" : "1");
				jasperHash.put(ENUM_PRINT_PARAMETERS.EXTRA.Name(), 					_recWK.get_ufs_dbVal(WIEGEKARTE.ist_lieferant, "N").equalsIgnoreCase("Y") ? "WE" : "WA");
				jasperHash.put(ENUM_PRINT_PARAMETERS.WE_WA.Name(), 					_recWK.get_ufs_dbVal(WIEGEKARTE.ist_lieferant, "N").equalsIgnoreCase("Y") ? "WE" : "WA");
				
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_WAAGE_STANDORT.Name(), 		_recWK.get_ufs_dbVal(WIEGEKARTE.id_waage_standort, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ADRESSE_SPEDITION.Name(), 	_recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_spedition, ""));
				
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ADRESSE_ABN_STRECKE.Name(), _recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_abn_strecke, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ARTIKEL.Name(), 			_recWK.get_ufs_dbVal(WIEGEKARTE.id_artikel_sorte, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ARTIKEL_BEZ.Name(), 		_recWK.get_ufs_dbVal(WIEGEKARTE.id_artikel_bez, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.IST_GESAMTVERWIEGUNG.Name(), 	_recWK.get_ufs_dbVal(WIEGEKARTE.ist_gesamtverwiegung, "N"));
				
				jasperHash.put(ENUM_PRINT_PARAMETERS.TYP_WIEGEKARTE.Name(), 		_recWK.get_ufs_dbVal(WIEGEKARTE.typ_wiegekarte, ""));
				
				jasperHash.put(ENUM_PRINT_PARAMETERS.GUETERKATEGORIE.Name(), 		_recWK.get_ufs_dbVal(WIEGEKARTE.gueterkategorie, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.KENNZEICHEN.Name(), 			_recWK.get_ufs_dbVal(WIEGEKARTE.kennzeichen, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.TRAILER.Name(), 				_recWK.get_ufs_dbVal(WIEGEKARTE.trailer, ""));
				
				jasperHash.put(ENUM_PRINT_PARAMETERS.STORNO.Name()								, _recWK.get_ufs_dbVal(WIEGEKARTE.storno, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_WIEGEKARTE_STORNO.Name()				, _recWK.get_ufs_dbVal(WIEGEKARTE.id_wiegekarte_storno, ""));
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_CONTAINER_EIGEN.Name()					, _recWK.get_ufs_dbVal(WIEGEKARTE.id_container_eigen, ""));
				
				
				// STANDARD-DRUCKVORGANG setzen
				jasperHash.put(ENUM_PRINT_PARAMETERS.DRUCKE_EINGANGSSCHEIN_LIEFERSCHEIN.Name()	, "N" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.DRUCKE_WIEGEKARTE.Name()					, "N" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.DRUCKE_AUSGANG_BUERO.Name()				, "N" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY.Name()							, "0" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GESAMT.Name()						, "0" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.ID_ABZUG_GEBINDE.Name()					, "" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GEBINDE.Name()					, "" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.NUM_COPY_GEBINDE_GESAMT.Name()				, "" );
				jasperHash.put(ENUM_PRINT_PARAMETERS.MODULNAME.Name()							, "WK_RB");
				
			} catch (myException e) {}
		}
		
		return this;
		
	}
	

	/**
	 * Wiegekarte wird nur geschrieben, wenn noch kein Druckdatum gesetzt wurde und das Nettogewicht gesetzt ist, also wenn die WK 
	 * vollständig ist.
	 * @author manfred
	 * @date 02.10.2020
	 *
	 * @return
	 */
	public WK_RB_PrintBase updateWK_SetDruckdatum() {
		
		SqlStringExtended  sql = new SqlStringExtended( " UPDATE JT_WIEGEKARTE WK SET WK.GEDRUCKT_AM = SYSDATE WHERE  WK.ID_WIEGEKARTE = ? " +  
									 " AND GEDRUCKT_AM IS NULL AND NETTOGEWICHT IS NOT NULL ")
									 ._addParameter(new Param_Long(getIDWiegekarte()));
		
		if (!bibDB.ExecSQL(sql, true)) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim aktualisieren der Wiegekarte.")));
		}		
		
		return this;
	}


	/**
	 * incrementiert den Druckzähler der Wiegekarte
	 * @author manfred
	 * @date 05.10.2020
	 *
	 * @param bCommit
	 * @return
	 * @throws myException
	 */
	public WK_RB_PrintBase incWKDruckzaehler(boolean bCommit) throws myException{
		SqlStringExtended sSqlExt = new SqlStringExtended(" UPDATE JT_WIEGEKARTE WK SET WK.DRUCKZAEHLER = nvl(WK.DRUCKZAEHLER,0) + 1  WHERE WK.ID_WIEGEKARTE = ?" )
										._addParameter(new Param_Long(getIDWiegekarte()));
		
		if (!bibDB.ExecSQL(sSqlExt, bCommit)) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim aktualisieren des Druckzählers der Wiegekarte.")));
		}		
		return this;
	}


	/**
	 * incrementiert den Druckzähler des Eingangsscheins.
	 * @author manfred
	 * @date 05.10.2020
	 *
	 * @param bCommit
	 * @return
	 * @throws myException
	 */
	public WK_RB_PrintBase incWKESZaehler(boolean bCommit) throws myException{
		SqlStringExtended sSqlExt = new SqlStringExtended(" UPDATE JT_WIEGEKARTE WK SET DRUCKZAEHLER_EINGANGSSCHEIN = nvl(WK.DRUCKZAEHLER_EINGANGSSCHEIN,0) + 1  WHERE WK.ES_NR = ? ")
										._addParameter(new Param_String("",_esNr));
		
		if (!bibDB.ExecSQL(sSqlExt, bCommit)) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim aktualisieren des ES-Zählers der Wiegekarte.")));
		}		
		return this;

	}

	

	
	
	
	/**
	 * Eingangsschein-Schreiben
	 * @author manfred
	 * @date 05.10.2020
	 *
	 * @return
	 * @throws myException 
	 */
	public WK_RB_PrintBase updateWK_SetEingangsscheinNr() throws myException {
		
		_esNr = _recWK.get_ufs_dbVal(WIEGEKARTE.es_nr);
		
		// wenn noch kein ES-Nr geschrieben wurde, dann eine neue erstellen
		if (S.isEmpty(_esNr)) {
			
			String sIstLieferant 	= _recWK.get_ufs_dbVal(WIEGEKARTE.ist_lieferant, "N");
			boolean bIstLieferant 	= sIstLieferant.equalsIgnoreCase("Y");
			
			Timestamp 	tErzeugtAm 		= _recWK.get_raw_resultValue_timeStamp(WIEGEKARTE.erzeugt_am);
			String 		sErzeugtAm 		= tErzeugtAm.toString().substring(0, 10);
			
			
			
			Long lIDNew = -1L;
			
			String sSql = "";
			if ( bIstLieferant ) {
				sSql = " SELECT SEQ_" + bibALL.get_ID_MANDANT() + "_WK_EINGANGSSCHEIN.NEXTVAL  FROM DUAL "  ;
			} else {
				sSql = " SELECT SEQ_" + bibALL.get_ID_MANDANT() + "_WK_LIEFERSCHEIN.NEXTVAL  FROM DUAL "  ;
			}
			SqlStringExtended sqlNewID = new SqlStringExtended(sSql);
			String [][] arrID = bibDB.EinzelAbfrageInArray(sSql, "-1");
			
			_esNr = arrID[0][0] ;
			lIDNew = Long.parseLong(_esNr );
			
			
			// Parameter
			Vector<ParamDataObject> vecParamWhere = new Vector<ParamDataObject>();
			
			
				
			String sIDLieferant = _recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant);  // recWK.get_ID_ADRESSE_LIEFERANT_cUF() ;
			String sKennzeichen = _recWK.get_ufs_dbVal(WIEGEKARTE.kennzeichen); 		 //recWK.get_KENNZEICHEN_cUF();
				
				// immer die eigene WK-ID
				String sWhereES = " WHERE ID_WIEGEKARTE = ?" ;//+ m_sIDWiegekarte ;
				vecParamWhere.add(new Param_Long(_recWK.getActualID()));
				
				String sWhereLieferant = "";
				
				
				// und die des dazugehörigen Eingangsscheins 
				if (sKennzeichen != null){
					// oder alle noch nicht zu einem ES gehörenden WK eines Kunden, die schon eine Wiegekarte haben.
					sWhereES += " OR ( "
							+ " (SELECT typ_wiegekarte FROM jt_wiegekarte  WHERE id_wiegekarte = ? ) != 'S' " // " + m_sIDWiegekarte + " 
							+ " AND TYP_WIEGEKARTE != 'S' "
							+ " AND KENNZEICHEN = ? "  			// + sKennzeichen + "' " 
							+ " AND trunc(ERZEUGT_AM,'DD') = ? " 	//+ m_recWK.get_ERZEUGT_AM_VALUE_FOR_SQLSTATEMENT()
							+ " AND ES_NR IS NULL AND NVL(DRUCKZAEHLER,0) > 0 " 
							+ " AND NVL(IST_LIEFERANT,'N') = ? ";
							;
						vecParamWhere.add(new Param_Long(_recWK.getActualID()));
						vecParamWhere.add(new Param_String("",sKennzeichen));
						vecParamWhere.add(new Param_String("",sErzeugtAm));
						vecParamWhere.add(new Param_String("",sIstLieferant));
						
						if (sIDLieferant != null){
							sWhereLieferant = " AND ID_ADRESSE_LIEFERANT = ?"; // + sIDLieferant ;
							sWhereES +=  sWhereLieferant;
							vecParamWhere.add(new Param_Long(_recWK.get_raw_resultValue_Long(WIEGEKARTE.id_adresse_lieferant) ) );
						} else {
							sWhereLieferant = " AND ID_ADRESSE_LIEFERANT is null"; // + sIDLieferant ;
							sWhereES +=  sWhereLieferant;
						}
					sWhereES += " )"; 
					
					// für den Streckenschein kann es auch sein, dass 2 Einträge ohne Wiegekarte auf einen Streckenschein gedruckt werden sollen. 
					sWhereES += " OR ( "
							+ " TYP_WIEGEKARTE = 'S' "
							+ " AND TYP_WIEGEKARTE = (Select typ_wiegekarte from jt_wiegekarte where id_wiegekarte = ? )  "  // + m_sIDWiegekarte + ") " 
							+ " AND KENNZEICHEN = ? "     // '" + sKennzeichen + "' " 
							+ " AND trunc(ERZEUGT_AM,'DD') = ? " // + m_recWK.get_ERZEUGT_AM_VALUE_FOR_SQLSTATEMENT()
							+ " AND ES_NR IS NULL AND NVL(DRUCKZAEHLER,0) = 0  "
							+ " AND NVL(IST_LIEFERANT,'N') = ? " 
							;
					
					vecParamWhere.add(new Param_Long(_recWK.getActualID()));
					vecParamWhere.add(new Param_String("",sKennzeichen));
					vecParamWhere.add(new Param_String("",sErzeugtAm));
					vecParamWhere.add(new Param_String("",sIstLieferant));
					
					if (sIDLieferant != null){
						sWhereLieferant = " AND ID_ADRESSE_LIEFERANT = ?"; // + sIDLieferant ;
						sWhereES +=  sWhereLieferant;
						vecParamWhere.add(new Param_Long(_recWK.get_raw_resultValue_Long(WIEGEKARTE.id_adresse_lieferant) ) );
					} else {
						sWhereLieferant = " AND ID_ADRESSE_LIEFERANT is null"; // + sIDLieferant ;
						sWhereES +=  sWhereLieferant;
					}
					
					sWhereES += " )";
					
				}

				String sSQLUpdate = " UPDATE " + bibE2.cTO() + ".JT_WIEGEKARTE WK SET WK.ES_NR = ? " + sWhereES;

				SqlStringExtended sqlUpdate = new SqlStringExtended(sSQLUpdate);
				sqlUpdate._addParameter(new Param_String("",_esNr));
				sqlUpdate._addParameters(vecParamWhere);
			
				if (!bibDB.ExecSQL(sqlUpdate, true)) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim schreiben der ES-Nr.")));
				}
		}
		
		return this;
	}

	
	
	
	/**
	 * Anhängen der Wiegekarte an übergeordnete Datensätze (Fuhre, / Adresse)
	 * @author manfred
	 * @date 05.10.2020
	 *
	 */
	protected void connectWKArchivToAdditionalRecords() {
		//suchen, ob bei der Wiegekarte schon eine Archivdatei besteht.
		RECLIST_ARCHIVMEDIEN_ext  	rlMedien = null;
		RECORD_ARCHIVMEDIEN_ext  	oMedien  = null;
		Vector<String> vSql = new Vector<String>();
		String 	sIDMsg = "";
	
		boolean bConnectToAddress = bib_Settigs_Mandant.get_Wiegekarte_ArchivAnAdresseAnbinden();
		boolean bConnectToFuhre   = bib_Settigs_Mandant.get_Wiegekarte_ArchivAnFuhreAnbinden();

		if (!bConnectToAddress && !bConnectToFuhre) return;
		
		
		try {
			String sIDAdresse 		= _recWK.get_ufs_dbVal(WIEGEKARTE.id_adresse_lieferant);    
			String sIDFuhre			= _recWK.get_ufs_dbVal(WIEGEKARTE.id_vpos_tpa_fuhre);
			String sIDWiegekarte  	= _recWK.getActualID().toString();
			
			// prüfen, ob dem Wiegekaten-DS schon ein Archiv-Dolument zugeordent ist (Medienkenner == PRINT)
			rlMedien = new RECLIST_ARCHIVMEDIEN_ext(_DB.WIEGEKARTE, sIDWiegekarte, Archiver_CONST.MEDIENKENNER.PRINT);
			
			if (rlMedien.size() > 0){
				// 1. Mediensatz nehmen
				oMedien = new RECORD_ARCHIVMEDIEN_ext(rlMedien.get(0));;
				
				// mit dem Ziel-Records verknüpfen
				String sTables = "";
				if (!bibALL.isEmpty(sIDAdresse) && bConnectToAddress){
					vSql.add(oMedien.connectToAdditionalEntry(_DB.ADRESSE.substring(3), sIDAdresse, Archiver_CONST.MEDIENKENNER.WIEGEKARTE) );
					sTables += "Adresse";
				}
				if (!bibALL.isEmpty(sIDFuhre) && bConnectToFuhre){
					vSql.add(oMedien.connectToAdditionalEntry(_DB.VPOS_TPA_FUHRE.substring(3), sIDFuhre, Archiver_CONST.MEDIENKENNER.WIEGEKARTE ) );
					sTables += (sTables.length() >0 ? " und " : "") + "Fuhre";
				}
				
				
				if (vSql.size() > 0) {
					// die Einträge in der DB eintragen
					bibMSG.add_MESSAGE( bibDB.ExecMultiSQLVector(vSql, true) );
					
					if (bibMSG.get_bIsOK()){
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Wiegekarte wurde an " + sTables + " gekoppelt." ));
					}
				}
			}
			
		} catch (myException e) {
			rlMedien = null;
		}
	}
	
	
	
	/**
	 * 
	 * Druckt die JasperHashes, die im Vektor stehen alle aus und startet den Download
	 * @author manfred
	 * @date 05.10.2020
	 *
	 * @return
	 */
	protected boolean doPrint() {
		boolean bRet = false;
		try {
			
			// alle Jasper-Hashes sollen nur eine Warning schmeissen, wenn die Pipeline leer ist.
			for (E2_JasperHASH j: m_v_jasper) {
				j.set_bWarningOnEmptyPipelineResult(true);
			}
			
			
			bRet = m_v_jasper.CREATE_TEMP_FILES_FOR_ALL_HASHMAPS(null, false, true);
			
			m_v_jasper.DownloadFiles(null);
			
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Fehler beim Drucken des Dokuments..."))) ;
			bRet = false;
		}
		return bRet;
	}
	
	
	
	/** 
	 * die aktuelle WK-ID
	 * @author manfred
	 * @date 02.10.2020
	 *
	 * @return
	 */
	protected Long getIDWiegekarte() {
		Long id = null;
		if (_recWK != null) {
			try {
				id = _recWK.getActualID();
			} catch (myException e) {	
				id = null;
			}
		}
		return id;
	}
	
	protected Long getIDWiegekarteBefund() {
		Long id =null;
		if (_recWK != null ) {
			RecDOWiegekarteBefund recBefund = _recWK._get_Befundung();
			if (recBefund != null) {
				try {
					id = recBefund.getActualID();
				} catch (myException e) {
					id = null;
				};
			}
		}
		return id;
	}
	
	
	/**
	 * Wiegekarte wird nur geschrieben, wenn noch kein Druckdatum gesetzt wurde und das Nettogewicht gesetzt ist, also wenn die WK 
	 * vollständig ist.
	 * @author manfred
	 * @date 02.10.2020
	 *
	 * @return
	 */
	public WK_RB_PrintBase updateWK_SetBefundungDruckdatum() {
		Long idWKBefund = getIDWiegekarteBefund();
		if (idWKBefund != null) {
			SqlStringExtended  sql = new SqlStringExtended( " UPDATE JT_WIEGEKARTE_BEFUND WK SET WK.GEDRUCKT_AM = SYSDATE WHERE  WK.ID_WIEGEKARTE_BEFUND = ? " +  
					"  ")
					._addParameter(new Param_Long(idWKBefund));
			
			if (!bibDB.ExecSQL(sql, true)) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim aktualisieren der Wiegekarte.")));
			}		
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim aktualisieren der Wiegekarte. Kein Befundungs-Satz vorhanden.")));
		}
		
		return this;
	}
	
	
	
}
