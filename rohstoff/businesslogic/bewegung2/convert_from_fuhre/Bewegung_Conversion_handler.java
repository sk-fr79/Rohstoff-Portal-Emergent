/**
 * rohstoff.businesslogic.bewegung2.convert_from_fuhre
 * @author manfred
 * @date 29.05.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.batch.ICallableTask;
import panter.gmbh.indep.dataTools.MyDBStatement;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_Lager_Konto;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;

/**
 * @author manfred
 * @date 29.05.2019
 *
 */
public class Bewegung_Conversion_handler implements ICallableTask{

	/**
	 *  der Vector mit allen SQL-Statements um die Bewegung im System zu schreiben, mit allen Vectoren und Atomen
	 */

	conv_helper 						m_helper = null;



	BL_Kostenberechnung 				m_BLKostenberechnung = null;
	
	// MIXED-FAELLE
	HashMap<Integer, Integer> 			faelle = new HashMap<Integer, Integer>();
	boolean 							bFuhreOnly = false;
	boolean 							bLagerOnly = false;
	
	
	// Vektor, der alle ID_LAGER_KONTO hält, die im aktuellen Lauf konvertiert wurden, damit keine doppelten Korrekturen vorkommen 
	Vector<String>   					m_vHandbuchungenKonvertiert = new Vector<String>();
	
	
	
	MyDBToolBox         				m_MyDBToolbox = null;
	
	// Kalkuliere die Kosten mit der generierung der Atome
	private boolean 					m_omitKostenCalc = true; 
	
	private Vector<jt_bg_vektor> 		v_Vektor = null;
	
	
	
	
	/**
	 * Per Default werden die Kosten-Sätze bei der Generierung der Atome generiert.
	 * Falls man das nicht möchte, muss man dies ausdrücklich verhindern mit omitKostenCalculation(true);
	 * 
	 * @author manfred
	 * @date   27.07.2012
	 */
	public Bewegung_Conversion_handler() throws myException {
		m_helper = new conv_helper();
		

		m_MyDBToolbox = bibALL.get_myDBToolBox();

	    try
	    {
	    	m_MyDBToolbox = new MyDBToolBox(bibALL.get_oConnectionNormal());
	    	m_MyDBToolbox.setZusatzFelder((String[][]) bibALL.get_DB_ZusatzFelder(false, false,false, "", ""));
	    	m_MyDBToolbox.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
	    	m_MyDBToolbox.set_bErsetzungTableView(true); // standardmässig wird ersetzt tables gegen views bei JT_tables
	        
	    }
	    catch (myException ex)
	    {
	    	m_MyDBToolbox = bibALL.get_myDBToolBox();
	        // im fehlerfall kommt null zurueck
	    }

	    m_helper.setDBToolbox(m_MyDBToolbox);
		
	    
		
//		// Kostneberechnungs-Klasse puffern
//		m_BLKostenberechnung = new  BL_Kostenberechnung();
	
	}

	
	/**
	 * verhindert die erzeugung des Kosten-Satzes
	 * @author manfred
	 * @date 07.10.2016
	 *
	 * @param omitKostenCalc true: verhindert die Kostenkalkulation bei der Erzeugung des Datensatzes
	 * @return
	 */
	public Bewegung_Conversion_handler omitKostenCalculation ( boolean omitKostenCalc ){
		m_omitKostenCalc = omitKostenCalc;
		return this;
	}

	

	
	/**
	 * Testroutine zum konvertieren einzelner Fuhren oder Fuhrenmengen 
	 * @author manfred
	 *
	 * @param DateStart
	 * @param DateEnd
	 * @param bFuhre
	 * @param IDFuhre
	 * @param IDFuhreVon
	 * @param IDFuhreBis
	 * @param bWE
	 * @param bWA
	 * @param bS
	 * @param bMixed
	 * @param bLL
	 * @param bHand
	 * @param IDLager
	 * @param IDLagerVon
	 * @param IDLagerBis
	 */
	public void test(
			String DateStart, 
			String DateEnd, 

			boolean bFuhre,
			String IDFuhre, 
			String IDFuhreVon, 
			String IDFuhreBis,
			boolean bWE, boolean bWA, boolean bS, boolean bMixed, boolean bLL, 
			
			boolean bHand,
			String IDLager, 
			String IDLagerVon, 
			String IDLagerBis
			){
		
		
		GregorianCalendar calBegin = new GregorianCalendar();
		
		String [][] asIds = new String[0][0];
		
		
		String sWhere = " nvl(ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' ";
		String sWhereLager = " BUCHUNG_HAND IS NOT NULL ";
		
		boolean bSingleFuhre = !S.isEmpty(IDFuhre);
		boolean bSingleLager = !S.isAllEmpty(IDLager);
		
		String ssqlIDs = "";
		
		GregorianCalendar calEnd;
		long diff_in_sec = 0L;
		
		
		
		//
		//  Fuhrenbuchungen
		//
		if ( bFuhre ) {
			calBegin = new GregorianCalendar();
			
			if (bSingleFuhre) {
				sWhere += " AND ID_VPOS_TPA_FUHRE = " + IDFuhre;
			} else {
				// FuhreBereich
				String sTyp = "";
				sTyp += bWE ? "1," : "";
				sTyp += bWA ? "2," : "";
				sTyp += bMixed ? "3," : "";
				sTyp += bLL ? "4," : "";
				sTyp += bS ? "0," : "";
				
				if (sTyp.length() > 0){
					sTyp = sTyp.substring(0, sTyp.length() - 1 );
					sTyp = " AND TYP_STRECKE_LAGER_MIXED in (" + sTyp + ") ";
				} else {
					 sTyp = "1 = 2 ";
				}
				
				if (!bibALL.isEmpty(IDFuhreVon) ){
					sWhere += " AND ID_VPOS_TPA_FUHRE >= " + IDFuhreVon;
				}
				
				if (!bibALL.isEmpty(IDFuhreBis) ){
					sWhere += " AND ID_VPOS_TPA_FUHRE <= " + IDFuhreBis;
				}

				
				if (!bibALL.isEmpty(DateStart) ){
					sWhere += " AND to_char(DATUM_AUFLADEN,'yyyy-mm-dd') >= '" + DateStart + "'"; 
				}
				
				if (!bibALL.isEmpty(DateEnd) ){
					sWhere += " AND to_char(DATUM_AUFLADEN,'yyyy-mm-dd') <= '" + DateEnd + "'"; 
				}
				sWhere += sTyp;
			}

			
			// ggf kommen zu viele RECORD-Objekte in der Liste vor, deshalb wird die Verarbeitung in Teile aufgesplittet
			ssqlIDs = "SELECT ID_VPOS_TPA_FUHRE FROM "+ bibE2.cTO() +".JT_VPOS_TPA_FUHRE WHERE " + sWhere + " ORDER BY ID_VPOS_TPA_FUHRE ";
			
			
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					Rec21_VPOS_TPA_FUHRE_ext recFuhre = new Rec21_VPOS_TPA_FUHRE_ext();
					recFuhre._fill_id(asIds[iRun][0]);
					generateBGVektorFromFuhre_Prepared(recFuhre, true);
				} catch (myException e1) {
					e1.printStackTrace();
				} 
			}

			calEnd = new  GregorianCalendar();
			diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
			DEBUG.System_println("Es wurden " + asIds.length + " Fuhren konvertiert in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);
			
		}  
		
		if (bHand) {
			calBegin = new GregorianCalendar();
			// handbuchungen
			if (bSingleLager){
				sWhereLager += " AND ID_LAGER_KONTO = " + IDLager;
			} else {
				
				if (!bibALL.isEmpty(IDLagerVon) ){
					sWhereLager += " AND ID_LAGER_KONTO >= " + IDLagerVon;
				}
				
				if (!bibALL.isEmpty(IDLagerBis) ){
					sWhereLager += " AND ID_LAGER_KONTO <= " + IDLagerBis;
				}

				
				if (!bibALL.isEmpty(DateStart) ){
					sWhereLager += " AND to_char(BUCHUNGSDATUM,'yyyy-mm-dd') >= '" + DateStart + "'"; 
				}
				
				if (!bibALL.isEmpty(DateEnd) ){
					sWhereLager += " AND to_char(BUCHUNGSDATUM,'yyyy-mm-dd') <= '" + DateEnd + "'"; 
				}
			}

			m_vHandbuchungenKonvertiert.clear();
			
			// ggf kommen zu viele RECORD-Objekte in der Liste vor, deshalb wird die Verarbeitung in Teile aufgesplittet
			ssqlIDs = "SELECT ID_LAGER_KONTO FROM "+ bibE2.cTO() +".JT_LAGER_KONTO WHERE "+  sWhereLager + " ORDER BY ID_LAGER_KONTO ";
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					Rec21_Lager_Konto oKonto = new Rec21_Lager_Konto();
					oKonto._fill_id(asIds[iRun][0]);
					generateVektorFromHandbuchung(oKonto,true);
				} catch (myException e1) {
					e1.printStackTrace();
				} 
			}

			calEnd = new  GregorianCalendar();
			diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
			DEBUG.System_println("Es wurden " + asIds.length + " Handbuchungen konvertiert in " + diff_in_sec + " sec." , DEBUG.DEBUG_FLAG_DIVERS1);
		} 


		// Anzahl der Fälle ausdrucken
		for (Integer oKey: faelle.keySet()){
			DEBUG.System_println("Fall " + oKey.toString() + " : " + faelle.get(oKey) + " mal!" , DEBUG.DEBUG_FLAG_DIVERS1);
		}
		
		
	}

	
	/**
	 * schneidet eine Tranche aus dem Array raus und erzeugt eine IN-Clause
	 * @author manfred
	 * @date   01.10.2012
	 * @param asIds
	 * @param iBlock
	 * @param iBlockSize
	 * @return
	 */
	private String getInClause(String [][] asIds, int iBlock, int iBlockSize){
		String sRet = "";
		
		int iStart = iBlock*iBlockSize;
		int iSize = asIds.length;
		
		if (iSize <= 0 || iStart  > iSize) return null;
		
		StringBuilder sIDs = new StringBuilder();
		sIDs.append("(");
		
		for (int iCount = iStart; iCount < iSize; iCount++){
			sIDs.append(asIds[iCount][0]);
			sIDs.append(",");
		}
		
		sIDs.deleteCharAt(sIDs.lastIndexOf(","));
		
		sIDs.append(")");
		
		return sIDs.toString();
	}
	
	

	
	/**
	 * 
	 * @author manfred
	 * @param oRecFuhre
	 * @throws myException 
	 * @throws SQLException 
	 */
	public void generateBGVektorFromFuhre_Prepared(Rec21_VPOS_TPA_FUHRE_ext oRecFuhre, boolean bDeleteOldEntry) throws myException{
		if (oRecFuhre == null) return;
		
		Long lIDVposTpaFuhre = oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre);
		String idFuhre = oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre).toString();
		
		if (idFuhre == null) return;
		
		
		int iFuhrentyp = oRecFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.typ_strecke_lager_mixed).intValue();
		
		switch (iFuhrentyp) {
		case 1: // WE
			Bewegung_FUHREN_Transformation_WE oTransWE = new Bewegung_FUHREN_Transformation_WE(m_helper);
			v_Vektor = oTransWE.transformiereFuhre(oRecFuhre);
			break;
			
		case 2: // WA
			Bewegung_FUHREN_Transformation_WA oTransWA = new Bewegung_FUHREN_Transformation_WA(m_helper);
			v_Vektor = oTransWA.transformiereFuhre(oRecFuhre);
			break;
			
		case 0: // STRECKE
			Bewegung_FUHREN_Transformation_STRECKE oTransSTRECKE = new Bewegung_FUHREN_Transformation_STRECKE(m_helper);
			v_Vektor = oTransSTRECKE.transformiereFuhre(oRecFuhre);
			break;
		case 3: // MIXED
			Bewegung_FUHREN_Transformation_MIXED oTransMIXED = new Bewegung_FUHREN_Transformation_MIXED(m_helper);
		    v_Vektor = oTransMIXED.transformiereFuhre(oRecFuhre);

		    Integer iRet = new Integer(oTransMIXED.Fallunterscheidung());
		    if (faelle.containsKey(iRet)){
		    	Integer o = faelle.get(iRet);
		    	o++;
		    	faelle.put(iRet, o);
		    } else {
		    	faelle.put(iRet, 1);
		    }
			
			break;
			
		case 4: // LAGER-LAGER
			Bewegung_FUHREN_Transformation_LL oTransLL = new Bewegung_FUHREN_Transformation_LL(m_helper);
			v_Vektor = oTransLL.transformiereFuhre(oRecFuhre);
			break;

		default:
			DEBUG.System_println("Default: Nich implementiert: FuhrenID = " + idFuhre + " Fuhrentyp: " + iFuhrentyp , DEBUG.DEBUG_FLAG_DIVERS1);
			break;
		}

		boolean bOK = true;
		// 
		// alte Einträge löschen
		//
		if (bDeleteOldEntry){
			Vector<MyDBStatementPrepared> vPrepSTMT = new  Vector<MyDBStatementPrepared>();
			vPrepSTMT.addAll(generateDeleteStmts_BGVektorPrepared(lIDVposTpaFuhre));
			bOK = true;
			for (MyDBStatementPrepared prepStmt: vPrepSTMT ){
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(prepStmt, false);
			}
		}

		// mit batch
//		Vector<String> vSQL = new  Vector<String>();
//		bOK &= generateDeleteStmts_BGVektor(lIDVposTpaFuhre);
		
		
		
		
		//
		// Erzeugung der neuen Vektor-Einträge
		//
//		boolean bOK = true;
		if (v_Vektor != null) {
			for (jt_bg_vektor v: v_Vektor){
				bOK &= writeVektorToDB(v);
			}
		}
		
		
		// Committen
		if (bOK){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
		}
		
		
	}
	

	

	/**
	 * Handbuchungen erzeugen für BG-Struktur
	 * @author manfred
	 * @date 14.02.2018
	 *
	 * @param kto
	 * @throws myException
	 */
	public void generateVektorFromHandbuchung(Rec21_Lager_Konto kto,boolean bDeleteOldEntries) throws myException {
		if (kto == null) return;
		String id_lager_konto = "";
		v_Vektor = new Vector<jt_bg_vektor>();
		
		try {	
			id_lager_konto = kto.get_ufs_dbVal(LAGER_KONTO.id_lager_konto,"-1");
		} catch (myException e1) {
			throw new myException(S.ms("LagerID kann nicht gefunden werden").CTrans(), e1);
		}
		
		
		// falls der Lagereintrag schon verarbeitet wurde, dann wieder zurück. (Umbuchungen verarbeiten beide Einträge auf einmal) 
		if (m_vHandbuchungenKonvertiert.contains(id_lager_konto) ) return;
		
		
		// löschen der Statements...
		String sID = null;
		Long lID = null; 
		Vector<MyDBStatementPrepared> _vPrepSTMT = new Vector<>();
		MyDBStatementPrepared sp ;
		
		String sSql = "";
		jt_bg_vektor vek = null;
		boolean bOK = true;
		
		try {
			Bewegung_Transformation_HAND oHandTranform = new Bewegung_Transformation_HAND(m_helper) ;
			
			
			if (kto.get_ufs_dbVal(LAGER_KONTO.buchung_hand, "-").equalsIgnoreCase("U") ){
				// Umbuchung

				RecList21 listKorr;
				Rec21_Lager_Konto rec1 = null ;
				Rec21_Lager_Konto rec2 = null ;

				String s = "SELECT * FROM JT_LAGER_KONTO WHERE BUCHUNG_HAND IS NOT NULL AND ERZEUGT_AM = (SELECT K2.ERZEUGT_AM FROM JT_LAGER_KONTO K2 WHERE K2.ID_LAGER_KONTO = ? ) ORDER BY ID_LAGER_KONTO";
				SqlStringExtended  sql = new SqlStringExtended(s)
												._addParameters(new VEK<ParamDataObject>()
												._a(new Param_Long("",kto.get_raw_resultValue_Long(LAGER_KONTO.id_lager_konto))));
				
				listKorr = new RecList21(_TAB.lager_konto)._fill(sql );
				
				// listKorr darf nur max 2 Einträge haben!
				if (listKorr.values().size() > 0){
					rec1 = new Rec21_Lager_Konto(listKorr.get(0));
					if (listKorr.values().size() >1) {
						rec2 = new Rec21_Lager_Konto(listKorr.get(1));
					} else rec2 = null;
				}

				if (rec1 != null && rec2 != null) {
					vek = oHandTranform.generiere_Vektor_Umbuchung(rec1, rec2);
				} else {
					Rec21_Lager_Konto r ;
					if (rec1 != null) {
						r = rec1;
					} else {
						r = rec2;
					}
					
					vek = oHandTranform.generiere_Vektor_Korrekturbuchung(r);
				}
				
				
				v_Vektor.add(vek);
			
				if ( rec1 != null ) m_vHandbuchungenKonvertiert.add(rec1.get_ufs_dbVal(LAGER_KONTO.id_lager_konto));
				if ( rec2 != null ) m_vHandbuchungenKonvertiert.add(rec2.get_ufs_dbVal(LAGER_KONTO.id_lager_konto));
				
				
			} else {
				// Korrekturbuchung
				vek = oHandTranform.generiere_Vektor_Korrekturbuchung(kto);
				m_vHandbuchungenKonvertiert.add(kto.get_ufs_dbVal(LAGER_KONTO.id_lager_konto));
				
				v_Vektor.add(vek);
			}
			
			
			
			// 
			// alte Einträge löschen
			//
			if (bDeleteOldEntries){
				Vector<MyDBStatementPrepared> vPrepSTMT = new  Vector<MyDBStatementPrepared>();
				vPrepSTMT.addAll(generateDeleteStmtsKONTO_BGVektorPrepared(Long.parseLong(id_lager_konto)) );
				bOK = true;
				for (MyDBStatementPrepared prepStmt: vPrepSTMT ){
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(prepStmt, false);
				}
			}

			
			
			//
			// Erzeugung der neuen Vektor-Einträge
			//
			if (v_Vektor != null) {
				for (jt_bg_vektor v: v_Vektor){
					bOK &= writeVektorToDB(v);
				}
			}
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bOK = false;
		}

		// Committen
		if (bOK){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
		}
		
	}

	
	

	
	
	/**
	 * baut die prepared-Statements für das löschen des Vektors und der abhängigen Datensätze
	 * @author manfred
	 * @date 20.02.2018
	 *
	 * @param lID
	 * @return
	 * @throws myException
	 */
	private boolean  generateDeleteStmts_BGVektor(Long IDVposTPAFuhre) throws myException{
		
		Vector<String> vStmt = new Vector<String>();
		
		if (IDVposTPAFuhre != null){
			String id = IDVposTPAFuhre.toString();
			
			vStmt.add("DELETE FROM JT_BG_DEL_INFO WHERE JT_BG_DEL_INFO.ID_BG_DEL_INFO  IN "
						+ "(SELECT ID_BG_DEL_INFO FROM JT_BG_ATOM WHERE JT_BG_ATOM.ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = " + id +")) " );
	
			
			vStmt.add( "DELETE FROM JT_BG_STORNO_INFO WHERE ID_BG_STORNO_INFO IN "
					+ "(SELECT ID_BG_STORNO_INFO FROM JT_BG_ATOM WHERE JT_BG_ATOM.ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = " + id  + " )) " );
			
			
			vStmt.add( "DELETE FROM JT_BG_ATOM WHERE ID_BG_VEKTOR IN "
					+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = " + id  + " ) " );
			
			vStmt.add( "DELETE FROM JT_BG_DEL_INFO      WHERE ID_BG_DEL_INFO            "
					+ "IN (SELECT ID_BG_DEL_INFO FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE =  " + id  + " )) " );
			
			vStmt.add( "DELETE FROM JT_BG_STORNO_INFO   WHERE ID_BG_STORNO_INFO         "
					+ "IN (SELECT ID_BG_STORNO_INFO FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE =  " + id  + " )) " );
			
			vStmt.add( "DELETE FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN "
					+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE =  " + id  + " ) " );
			
			vStmt.add( "DELETE FROM JT_BG_DEL_INFO      WHERE ID_BG_DEL_INFO            "
					+ "IN (SELECT ID_BG_DEL_INFO FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE =  " + id  + " )) " );
			
			vStmt.add( "DELETE FROM JT_BG_STORNO_INFO   WHERE ID_BG_STORNO_INFO         "
					+ "IN (SELECT ID_BG_STORNO_INFO FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE =  " + id  + " )) " );
			
			vStmt.add( "DELETE FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN "
					+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE =  " + id  + " ) " );
							

				}
			
		
	
		return m_MyDBToolbox.ExecSQL_in_Batch(vStmt, false);
		
	}
	
	

	
	

	/**
	 * baut die prepared-Statements für das löschen des Vektors und der abhängigen Datensätze
	 * @author manfred
	 * @date 20.02.2018
	 *
	 * @param lID
	 * @return
	 * @throws myException
	 */
	private Vector<MyDBStatementPrepared>  generateDeleteStmts_BGVektorPrepared(Long lIDVposTPAFuhre) throws myException{
		
		Vector<MyDBStatementPrepared> _vPrepSTMT = new Vector<>();
		MyDBStatementPrepared sp;
		
		if (lIDVposTPAFuhre != null){
			int IDVposTPAFuhre = lIDVposTPAFuhre.intValue();
			try {
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_DEL_INFO WHERE JT_BG_DEL_INFO.ID_BG_DEL_INFO  IN "
							+ "(SELECT ID_BG_DEL_INFO FROM JT_BG_ATOM WHERE JT_BG_ATOM.ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?)) " );
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STORNO_INFO WHERE ID_BG_STORNO_INFO IN "
						+ "(SELECT ID_BG_STORNO_INFO FROM JT_BG_ATOM WHERE JT_BG_ATOM.ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?) )");
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_ATOM WHERE ID_BG_VEKTOR IN "
						+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?)");
				
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_DEL_INFO      WHERE ID_BG_DEL_INFO            "
						+ "IN (SELECT ID_BG_DEL_INFO FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?) )");
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STORNO_INFO   WHERE ID_BG_STORNO_INFO         "
						+ "IN (SELECT ID_BG_STORNO_INFO FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?) )");
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);

				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN "
						+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?)");
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_DEL_INFO      WHERE ID_BG_DEL_INFO            "
						+ "IN (SELECT ID_BG_DEL_INFO FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?) )");
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);

				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STORNO_INFO   WHERE ID_BG_STORNO_INFO         "
						+ "IN (SELECT ID_BG_STORNO_INFO FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?) )");
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);

				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN "
						+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_VPOS_TPA_FUHRE = ?)");
				sp.STMT.setInt(1, IDVposTPAFuhre);
				_vPrepSTMT.addElement(sp);

				
			} catch (SQLException e) {
				throw new myException("loescheBGVektorPrepared(::Fehler beim erzeugen der Lösch-Statements", e);
			}
		}
		return _vPrepSTMT;
	}
		
	/**
	 * baut die prepared-Statements für das löschen des Vektors und der abhängigen Datensätze
	 * @author manfred
	 * @date 20.02.2018
	 *
	 * @param lID
	 * @return
	 * @throws myException
	 */
	private Vector<MyDBStatementPrepared>  generateDeleteStmtsKONTO_BGVektorPrepared(Long IDLagerKonto) throws myException{
		
		Vector<MyDBStatementPrepared> _vPrepSTMT = new Vector<>();
		MyDBStatementPrepared sp;
		if (IDLagerKonto != null){
			try {
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_DEL_INFO WHERE JT_BG_DEL_INFO.ID_BG_DEL_INFO  IN "
							+ "(SELECT ID_BG_DEL_INFO FROM JT_BG_ATOM WHERE JT_BG_ATOM.ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?)) " );
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STORNO_INFO WHERE ID_BG_STORNO_INFO IN "
						+ "(SELECT ID_BG_STORNO_INFO FROM JT_BG_ATOM WHERE JT_BG_ATOM.ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?) )");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_ATOM WHERE ID_BG_VEKTOR IN "
						+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?)");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_DEL_INFO      WHERE ID_BG_DEL_INFO            "
						+ "IN (SELECT ID_BG_DEL_INFO FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?) )");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STORNO_INFO   WHERE ID_BG_STORNO_INFO         "
						+ "IN (SELECT ID_BG_STORNO_INFO FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?) )");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);

				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STATION WHERE ID_BG_VEKTOR IN "
						+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?)");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);
				
				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_DEL_INFO      WHERE ID_BG_DEL_INFO            "
						+ "IN (SELECT ID_BG_DEL_INFO FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?) )");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);

				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_STORNO_INFO   WHERE ID_BG_STORNO_INFO         "
						+ "IN (SELECT ID_BG_STORNO_INFO FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN (SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?) )");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);

				sp = new MyDBStatementPrepared( "DELETE FROM JT_BG_VEKTOR WHERE ID_BG_VEKTOR IN "
						+ "(SELECT ID_BG_VEKTOR FROM JT_BG_VEKTOR_KONVERT WHERE ID_LAGER_KONTO = ?)");
				sp.STMT.setLong(1, IDLagerKonto);
				_vPrepSTMT.addElement(sp);
				
			} catch (SQLException e) {
				throw new myException("loescheBGVektorPrepared(::Fehler beim erzeugen der Lösch-Statements", e);
			}
		}
		return _vPrepSTMT;
	}
		


	
	/**
	 * Hilfsklasse für das Aufräumen der Tabellen
	 * @author manfred
	 * @date 05.10.2016
	 *
	 */
	class sT {
		public String key;
		public String value;
		
		sT(String s_key, String s_value){
			key = s_key;
			value = s_value;
		}
	}

	
	/**
	 * Löscht Bewegungssätze, die aus der alten Welt kommen, abhängig von den Schaltern:
	 * @author manfred
	 * @date 05.10.2016
	 *
	 * @param bCleanFuhren 	Löscht alle Bewegungen die aus Fuhren resultieren
	 * @param bCleanHandbuchungLager	löscht alle Bewegungen, die aus Lagerbuchungen Hand/Umbuchung resultieren
	 */
	public MyE2_MessageVector cleanBewegungssaetze(boolean bCleanFuhren, boolean bCleanHandbuchungLager){
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
//		Vector<sT> vCleanFuhren = new Vector<>();
//		
//		if (bCleanFuhren){
//			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG_ATOM.fullTabName(),	"DELETE FROM " + BEWEGUNG_ATOM.fullTabName() + " WHERE " + BEWEGUNG_ATOM.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null )" ));
//			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG_VEKTOR.fullTabName(),	"DELETE FROM " + BEWEGUNG_VEKTOR.fullTabName() + " WHERE " + BEWEGUNG_VEKTOR.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null )" ));
//			 vCleanFuhren.add(new sT("Lösche Fuhrenbuchungen " + BEWEGUNG.fullTabName(),		"DELETE FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.id_vpos_tpa_fuhre.tnfn() + " is not null " ) );
//		}
//		 
//		if (bCleanHandbuchungLager){
//			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG_ATOM.fullTabName() ,	"DELETE FROM " + BEWEGUNG_ATOM.fullTabName() + " WHERE " + BEWEGUNG_ATOM.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y')" ));
//			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG_VEKTOR.fullTabName(),	"DELETE FROM " + BEWEGUNG_VEKTOR.fullTabName() + " WHERE " + BEWEGUNG_VEKTOR.id_bewegung.tnfn() + " IN ( SELECT " + BEWEGUNG.id_bewegung.tnfn() + " FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y')" ));
//			 vCleanFuhren.add(new sT("Lösche Lagerbuchungen " + BEWEGUNG.fullTabName(),			"DELETE FROM " + BEWEGUNG.fullTabName() + " WHERE " + BEWEGUNG.ist_lagerbuchung_alt.tnfn() + " = 'Y' " ) );
//		}
//		 
//		 
//		for (sT tupel: vCleanFuhren){
//			 boolean bOK = bibDB.ExecSQL(tupel.value, true);
//			 mv.add( new MyE2_Info_Message(tupel.key + (bOK ? "OK" : "Error!!!") + "\n" ) );
//		}
//		
//		 
//		// die Sequencer neu aufbauen
//		Vector<String> vNewSequencer = new Vector<>();
//		vNewSequencer.add(BEWEGUNG.fullTabName());
//		vNewSequencer.add(BEWEGUNG_VEKTOR.fullTabName());
//		vNewSequencer.add(BEWEGUNG_VEKTOR_POS.fullTabName());
//		vNewSequencer.add(BEWEGUNG_ATOM.fullTabName());
//		vNewSequencer.add(BEWEGUNG_STATION.fullTabName());
//		vNewSequencer.add(BEWEGUNG_ATOM_ABZUG.fullTabName());
//		vNewSequencer.add(BEWEGUNG_ATOM_KOSTEN.fullTabName());
//		 
//		for (String sTable: vNewSequencer) {
//				Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(sTable, null);
//				mv._add(oSeq.Build_New_SequenceBased_on_DatabaseQuery() ) ;
//		}
//
				 
		 
		return mv;
		 
	}
	
	
	
	
	/**
	 * löscht die Setzkaesten   
	 * @author manfred
	 * @date 05.10.2016
	 *
	 */
	public MyE2_MessageVector cleanSetzkasten(){
		MyE2_MessageVector mv = new MyE2_MessageVector();

//		Vector<sT> vCleanFuhren = new Vector<>();
//
//		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_ATOM_VERBUCHT.fullTabName(),			"DELETE FROM " + BEWEGUNG_ATOM_VERBUCHT.fullTabName()));
//		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_SETZKASTEN.fullTabName(),				"DELETE FROM " + BEWEGUNG_SETZKASTEN.fullTabName()));
//		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_ATOM_VERBUCHT_K.fullTabName(),			"DELETE FROM " + BEWEGUNG_ATOM_VERBUCHT_K.fullTabName()));
//		vCleanFuhren.add(new sT("Lösche " + BEWEGUNG_SETZKASTEN_K.fullTabName(),			"DELETE FROM " + BEWEGUNG_SETZKASTEN_K.fullTabName()));
//
//		for (sT tupel : vCleanFuhren) {
//			boolean bOK = bibDB.ExecSQL(tupel.value, true);
//			mv.add(new MyE2_Info_Message(tupel.key + (bOK ? "OK" : "Error!!!") + "\n"));
//		}
//
//		// die Sequencer neu aufbauen
//		Vector<String> vNewSequencer = new Vector<>();
//		vNewSequencer.add(BEWEGUNG_ATOM_VERBUCHT.fullTabName());
//		vNewSequencer.add(BEWEGUNG_ATOM_VERBUCHT_K.fullTabName());
//		vNewSequencer.add(BEWEGUNG_SETZKASTEN.fullTabName());
//		vNewSequencer.add(BEWEGUNG_SETZKASTEN_K.fullTabName());
//
//		for (String sTable : vNewSequencer) {
//			Project_TableSequenceBuilder oSeq = new Project_TableSequenceBuilder(sTable, null);
//			mv._add(oSeq.Build_New_SequenceBased_on_DatabaseQuery());
//		}
//
		return mv;
	}

	
	public boolean writeVektorToDB(jt_bg_vektor vek){
		
		
		boolean bOK = true;
		SqlStringExtended _sql_ext = null; 
		
		try{
			// zuerst den Vektor...
			// die SQL-Kommandos generieren und in die Queue einbauen
			if (vek != null){
				
				DEBUG.System_println("Konvertierte Fuhre: " + 
						vek.get_jt_bg_vektor_konvert().getID_VPOS_TPA_FUHRE().ValuePlain("-") + 
						"/" + vek.get_jt_bg_vektor_konvert().getID_VPOS_TPA_FUHRE_ORT().ValuePlain("-"), DEBUG.DEBUG_FLAG_SQL_EXEC);
				
				if (vek.get_jt_bg_storno_info()!= null){
					_sql_ext = vek.get_jt_bg_storno_info().getInsertStatement();
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				}
				
				if (vek.get_jt_bg_del_info()!= null){
					_sql_ext = vek.get_jt_bg_del_info().getInsertStatement();
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				}
				
				// jetzt der Vektor
//				vek.debugParamData();
				
				_sql_ext = vek.getInsertStatement();
				
//				DEBUG.System_println(_sql_ext.getSqlString() );
//				for (ParamDataObject o : _sql_ext.getValuesList()) {
//					DEBUG.System_println( o.Value() );
//					
//				}
				
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				
				// jetzt der Vektor-Fuhre Connector
				_sql_ext = vek.get_jt_bg_vektor_konvert().getInsertStatement();
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);

				// bei umbuchungen gibt es einen 2. Eintrag
				if (vek.get_jt_bg_vektor_konvert2() != null) {
					_sql_ext = vek.get_jt_bg_vektor_konvert2().getInsertStatement();
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				}

				
				//
				// jetzt die 3 Stationen
				//
				// S1
				_sql_ext = vek.getATOM_1().get_jt_bg_station_quelle().getInsertStatement();
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				Long id_station_1 = Long.parseLong(this.m_MyDBToolbox.EinzelAbfrage(BG_STATION._tab().sql_currval()));
				
				// S2
				_sql_ext = vek.getATOM_1().get_jt_bg_station_ziel().getInsertStatement();
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				Long id_station_2 = Long.parseLong(this.m_MyDBToolbox.EinzelAbfrage(BG_STATION._tab().sql_currval()));
				
				// S3
				_sql_ext = vek.getATOM_2().get_jt_bg_station_ziel().getInsertStatement();
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				Long id_station_3 = Long.parseLong(this.m_MyDBToolbox.EinzelAbfrage(BG_STATION._tab().sql_currval()));
				
				
				
				// Stationen den Atomen zuweisen
				vek.getATOM_1().setID_BG_STATION_QUELLE(id_station_1);
				vek.getATOM_1().setID_BG_STATION_ZIEL(id_station_2);
				
				vek.getATOM_2().setID_BG_STATION_QUELLE(id_station_2);
				vek.getATOM_2().setID_BG_STATION_ZIEL(id_station_3);
				
				//
				// ATOM 1
				//
				// stornoinfo
				if (vek.getATOM_1().get_jt_bg_storno_info() != null){
					_sql_ext = vek.getATOM_1().get_jt_bg_storno_info().getInsertStatement();
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				}
				
				// delinfo
				if (vek.getATOM_1().get_jt_bg_del_info() != null){
					_sql_ext = vek.getATOM_1().get_jt_bg_del_info().getInsertStatement();
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				}
				
				// das atom
				_sql_ext = vek.getATOM_1().getInsertStatement();
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				

				//
				// ATOM 2
				//
				// stornoinfo
				if (vek.getATOM_1().get_jt_bg_storno_info() != null){
					_sql_ext = vek.getATOM_2().get_jt_bg_storno_info().getInsertStatement();
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				}
				
				// delinfo
				if (vek.getATOM_1().get_jt_bg_del_info() != null){
					_sql_ext = vek.getATOM_2().get_jt_bg_del_info().getInsertStatement();
					bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				}
				
				// das atom
				_sql_ext = vek.getATOM_2().getInsertStatement();
				bOK &= m_MyDBToolbox.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(_sql_ext, false);
				

			}
			
		} catch (myException ex){
			// rollback
			bOK = false;
		}
				
		
		return bOK;
	}

	
	
	
	
	/***********
	 * 
	 *
	 * 
	 * Bereich für die Automatisierte Umsetzung der Fuhren in den neuen Bewegungssatz
	 * 
	 * 
	 * 
	 *********/

	// Automator-
	private HashMap<String, String> m_hmParameters = null;
	private Vector<String> m_TaskMessages = new Vector<String>();

	

	/**
	 * 
	 * Hauptroutine zum automatischen ausführen des Aufbaus der neuen Datensätze
	 * - es werden immer alle Fuhren generiert, die noch nicht in den Atomen vorhanden sind
	 * zusätzlich: 
	 * 		- wenn parameter days=x angegeben ist, werden alle Fuhren neu generiert, die in den letzten x-Tagen geändert wurden
	 *        damit werden auch geänderte Fuhren erfasst. -> wenn die Batch jeden tag läuft, sollten immer alle Fuhren aktuell sein
	 *     
	 *  curl "http://localhost:8080/rohstoff_app/batch?user=batchuser&pw=xxxx&task=convert2bgatom"
	 *  Eintrag in die Tabelle jd_batch_task:
	 *  
	 * insert into jd_batch_task (id_batch_task, id_mandant, taskname, classname, description,geaendert_von,letzte_aenderung,erzeugt_von,erzeugt_am)
	 * values(seq_batch_task.nextval,1,'convert2bgatom','rohstoff.businesslogic.bewegung.convert_from_fuhre.bg_bewegung_handler','Konvertiert die Handbuchungen und Fuhren in die BG-Atom-Strutkur','SYSTEM',sysdate,'SYSTEM',sysdate);
	 * 
	 */
	@Override
	public boolean runTask() {

		
		GregorianCalendar calBegin ;
		GregorianCalendar calEnd;
		long diff_in_sec ;
		
		String sSql1 = "";
		String sSql2 = "";
		String sSql2a = "";
		String sSql3 = "";
		String sSql4 = "";
		String ssqlIDs = "";
		String [][] asIds;
		String sID = "";
		
		boolean bConvertHandbuchungen = false;
		try {
			bConvertHandbuchungen = ENUM_MANDANT_DECISION.CONVERT_HANDBUCHUNG_LAGER_TO_ATOM.is_YES();
		} catch (myException e) {
			e.printStackTrace();
		}
		
		boolean bConvertLagerbestandserfassungen = false;
		try {
			bConvertLagerbestandserfassungen = ENUM_MANDANT_DECISION.CONVERT_LAGERBESTANDSERFASSUNG_TO_ATOM.is_YES();
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		// zuerst alle Handbuchungen
		if (bConvertHandbuchungen || bConvertLagerbestandserfassungen){
			//
			// Handbuchungen korrigieren
			//
			calBegin = new GregorianCalendar();

			Vector<String> vSql = new Vector<>();
			
			if (bConvertHandbuchungen){
				//		-- alle HANDBUCHUNGEN ,die möglicherweise im aktuellen Tag geändert wurden
				//		-- muss gemacht werden, da per Programm keine Zeitangaben beim Timestamp übernommen wird :-(
				vSql.add( 	" SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" WHERE trunc(L.LETZTE_AENDERUNG,'DD') >  trunc(sysdate  - 1 ,'DD') AND L.BUCHUNG_HAND IS NOT NULL ") ;
				
				//     -- alle HANDBUCHUNGEN, die geändert wurden, und ein Gap des änderungsdatums > 1 ist
				vSql.add(" SELECT L.ID_LAGER_KONTO FROM  " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						"					INNER JOIN  " + bibE2.cTO() +".JT_BG_VEKTOR_KONVERT V " +
						" 					ON V.ID_LAGER_KONTO = L.ID_LAGER_KONTO AND V.ID_MANDANT = L.ID_MANDANT " +
						" WHERE trunc(L.LETZTE_AENDERUNG,'DD') - trunc(V.LETZTE_AENDERUNG,'DD') >= 1 "	+ 
						" AND L.BUCHUNG_HAND IS NOT NULL " );
				
				// 		--  alle HANDBUCHUNGEN, die noch nicht verarbeitet wurden
				vSql.add(" SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						"					INNER JOIN  " + bibE2.cTO() +".JT_BG_VEKTOR_KONVERT V " +
						" 					ON V.ID_LAGER_KONTO = L.ID_LAGER_KONTO AND V.ID_MANDANT = L.ID_MANDANT " +
						" 				WHERE V.ID_LAGER_KONTO IS NULL and L.BUCHUNG_HAND IS NOT NULL ") ;
			} 
			
			if (bConvertLagerbestandserfassungen){
			
				vSql.add(" SELECT L.ID_LAGER_KONTO FROM " + bibE2.cTO() +".JT_LAGER_KONTO L " +
						" 				LEFT OUTER JOIN " + bibE2.cTO() +".JT_BG_VEKTOR_KONVERT V ON L.ID_LAGER_KONTO = V.ID_LAGER_KONTO AND L.ID_MANDANT = V.ID_MANDANT " +
						" 				WHERE V.ID_LAGER_KONTO IS NULL and L.BUCHUNG_HAND IS NOT NULL AND  " +
						"				UPPER( L.BEMERKUNG ) = UPPER('LAGERBESTANDSERFASSUNG') ") ;
			}

			
			if (vSql.size()>0){
				try {
					ssqlIDs = bibALL.Concatenate(vSql, " UNION ", "") + " order by 1 ";
				} catch (myException e) {
					ssqlIDs = "";	
				}
			}
			
			
			asIds = new String[0][0];
			asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
			Rec21_Lager_Konto oLag = null;
			sID = "";
			for (int iRun = 0; iRun < asIds.length; iRun++){
				try {
					oLag = new Rec21_Lager_Konto ();
					oLag._fill_id(asIds[iRun][0]);
					
					sID = oLag.get_ufs_dbVal(LAGER_KONTO.id_lager_konto, "N/A");
					generateVektorFromHandbuchung(oLag,true);
					
				} catch (myException e1) {
					e1.printStackTrace();
					
					m_TaskMessages.add("Fehler beim Konvertieren der Handbuchung " + sID);
				} catch (Exception e_default){
					e_default.printStackTrace();
				}
			}
			
			calEnd = new  GregorianCalendar();
			diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
			
			m_TaskMessages.add("Es wurden " + asIds.length + " Handbuchungen konvertiert in " + diff_in_sec + " sec.");

		}
		
		
		

		// Fuhrenbuchungen Konvertieren
		calBegin = new GregorianCalendar();
		
		//		-- alle fuhren ,die möglicherweise im aktuellen Tag geändert wurden
		//		-- muss gemacht werden, da per Programm keine Zeitangaben beim Timestamp übernommen wird :-(
		sSql1 = 	" SELECT F.id_vpos_tpa_fuhre FROM "+ bibE2.cTO() +".jt_vpos_tpa_fuhre F " +
						" WHERE trunc(F.LETZTE_AENDERUNG,'DD') >  trunc(sysdate  - 1 ,'DD') " ;
		
		//     -- alle Fuhren, die geändert wurden, und ein Gap des änderungsdatums > 1 ist
		sSql2 = 	" SELECT F.id_vpos_tpa_fuhre FROM "+ bibE2.cTO() +".jt_vpos_tpa_fuhre F " +
						" inner join "+ bibE2.cTO() +".JT_BG_VEKTOR_KONVERT V " +
						"      on F.ID_MANDANT = V.ID_MANDANT and F.ID_VPOS_TPA_FUHRE = V.ID_VPOS_TPA_FUHRE and V.ID_VPOS_TPA_FUHRE_ORT is null " +
						" WHERE trunc(F.LETZTE_AENDERUNG,'DD') - trunc(V.LETZTE_AENDERUNG,'DD') >= 1 " ;
		
		//     -- alle Fuhren, die geändert wurden, und ein Gap des änderungsdatums > 1 ist
		sSql2a = 	" SELECT F.id_vpos_tpa_fuhre FROM "+ bibE2.cTO() +".jt_vpos_tpa_fuhre_ort F " +
						" inner join "+ bibE2.cTO() +".JT_BG_VEKTOR_KONVERT V " +
						"      on F.ID_MANDANT = V.ID_MANDANT and F.ID_VPOS_TPA_FUHRE_ort = V.ID_VPOS_TPA_FUHRE_ort " +
						" WHERE trunc(F.LETZTE_AENDERUNG,'DD') - trunc(V.LETZTE_AENDERUNG,'DD') >= 1 " ;
		

		
		// 		--  alle fuhren, die noch nicht verarbeitet wurden
		
		// Nutzen des Outer-Joins mit null-IDs geht deutlich schneller als das "not in" statement
		sSql3 =  " SELECT F.id_vpos_tpa_fuhre FROM  "+ bibE2.cTO() +".jt_vpos_tpa_fuhre F " +
				" left outer join  "+ bibE2.cTO() +".JT_BG_VEKTOR_KONVERT V on F.id_vpos_tpa_fuhre = V.ID_VPOS_TPA_FUHRE and F.id_mandant = V.ID_MANDANT " +
		" WHERE V.ID_VPOS_TPA_FUHRE is null " +
		" and NVL(F.ALT_WIRD_NICHT_GEBUCHT,'N') = 'N' " 
		;
		
		

		ssqlIDs = sSql1 + " UNION " + sSql2 + " UNION " + sSql2a + " UNION " + sSql3 + " order by 1 ";
		
		asIds = new String[0][0];
		asIds = bibDB.EinzelAbfrageInArray(ssqlIDs); 
		Rec21_VPOS_TPA_FUHRE_ext oFuhre = null;
		sID = "";

		for (int iRun = 0; iRun < asIds.length; iRun++){
			try {
				sID = asIds[iRun][0];
				oFuhre = new Rec21_VPOS_TPA_FUHRE_ext();
				oFuhre._fill_id(sID);
				
				generateBGVektorFromFuhre_Prepared(oFuhre,true);
			} catch (myException e1) {
				e1.printStackTrace();
				
				m_TaskMessages.add("Fehler beim Konvertieren der Fuhre " + sID);
			} catch (Exception e_default){
				e_default.printStackTrace();
			}
		}
		
		
		
		calEnd = new  GregorianCalendar();
		diff_in_sec = (calEnd.getTimeInMillis() - calBegin.getTimeInMillis()) / 1000;
		
		// anzahl der Faelle ausdrucken
		for (Integer oKey: faelle.keySet()){
			m_TaskMessages.add("Fall " + oKey.toString() + " : " + faelle.get(oKey) + " mal!");
		}
		
		m_TaskMessages.add("Es wurden " + asIds.length + " Fuhren konvertiert in " + diff_in_sec + " sec.");
		
		
		
		
		return true;
	}




	@Override
	public void setTaskParameters(HashMap<String, String> hmParameters) {
		m_hmParameters  = hmParameters;		
	}




	@Override
	public Vector<String> getTaskMessages() {
		return m_TaskMessages;
	}
	

}
