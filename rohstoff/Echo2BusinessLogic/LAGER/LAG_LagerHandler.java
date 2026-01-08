/**
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LagerPreisHandler.CLagerPreisResult;
import rohstoff.utils.bibROHSTOFF;

/**
 * @author manfred
 * Verwaltung der Lager-Tabelle.
 * einlagern, auslagern, umlagern
 * 
 * Alle Fehler/Hinweise werden über bibMSG abgehandelt
 * 2009-06-09: ALT_WIRD_NICHT_GEBUCHT wird berücksichtigt und es wird abgebrochen.
 * 
 */
public class LAG_LagerHandler  
{
	// Member-Variablen
	//boolean m_bUseOwnTransaction = false;

	// Vector, der die abzuarbeitenden SQL-Statements beinhaltet
	Vector<String>  m_vSQLStatements = null;
	
	// die aktuellen Saldi einer Lager-Material-Kombi, da ja evtl. bei einer Änderung mehrere materialien / Läger betroffen
	// sein können.
	Hashtable<String, BigDecimal> m_Saldo = new Hashtable<String, BigDecimal>();

	boolean m_bWA_GEWICHT_NETTO = false;
	
	/**
	 * Initialisiert den Lager-Handler
	 * @param IdMandant
	 * @param bUseOwnTransaction : 	wenn true, wird die Aktion mit commit/rollback abgeschlossen.
	 * 								wenn false, muss die aufrufende Komponente die Transaktion verwalten
	 */
	public LAG_LagerHandler( ) {
		m_vSQLStatements = new Vector<String>();
		
		// Mandanten-Parameter lesen
		String s_LagerWAGewichtNetto = __RECORD_MANDANT_ZUSATZ.get___Value("LAGER_WA_GEWICHT_NETTO", "N");
		m_bWA_GEWICHT_NETTO  	= s_LagerWAGewichtNetto.equalsIgnoreCase("Y") ? true : false;

		
	}

	
	/*
	 * Lagerbuchungen der Fuhren
	 */


	/**
	 * Erzeugt die Lagerbuchungen wenn nur die Fuhren-ID bekannt ist.
	 * Es wird der Fuhrensatz gelesen und die Daten zum Einbuchen ins Lager ermittelt
	 * 
	 * Author: manfred
     * 07.04.2009
     * 
     * @param idFuhre
 	 */
	public void LagerBuchung (String idFuhre){
	    this.LagerBuchung(idFuhre, null);	
	}
	
	
	/**
	 * Erzeugt die Lagerbuchungen wenn nur die FuhrenOrt-ID bekannt ist.
	 * Es wird der FuhrenOrt-Satz gelesen und die Daten zum Einbuchen ins Lager ermittelt
	 * 
	 * Author: manfred
     * 07.04.2009
     * 
     * @param idFuhrenOrt
 	 */
	public void LagerbuchungOrt (String idFuhrenOrt){
	   this.LagerBuchung(null, idFuhrenOrt);	
	}
	
		
    /***
     * Verbuchung von Fuhren/Fuhrenorten im Lager
     * Es werden nur solche Fuhren berücksichtigt, die eine Lade- oder Ablademenge haben. 
     * Fuhren nur mit Planmengen werden ignoriert.
     * Es werden nur Fuhren berücksichtigt, die auch ein Lade-/Abladedatum haben.
     * 
     * Author: manfred
     * 07.04.2009
     *
     * @param idFuhre
     * @param idFuhrenOrt
     * @return
     */
	private void LagerBuchung (String idFuhre, String idFuhrenOrt){
		// wenn eine Fuhre oder Fuhrenposition übergeben wird, müssen die Daten aus der DB gelesen werden.
		RECORD_VPOS_TPA_FUHRE oFuhre = null;
		RECORD_VPOS_TPA_FUHRE_ORT oFuhrenOrt = null;
		
						
		// der verwendete Lagerbuchungssatz, wird später gefüllt und dann in die Verarbeitung gegeben
		Vector<LAG_LagerBuchungsSatz> vLagerbuchungen = new Vector<LAG_LagerBuchungsSatz>() ;
		
		//
		//  Es ist eine Fuhre...
		//
		if (idFuhrenOrt == null || idFuhrenOrt.trim().equals("")){
			if (idFuhre == null){
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Bei einer Lagerbuchung wurde keine Fuhren-ID angegeben.")));
				return;
			}

			// Hier können es theoretisch 2 Buchungen bei einer Mandanteninternen Lagerumbuchung geben
			try {
				oFuhre = new RECORD_VPOS_TPA_FUHRE(idFuhre);
				if (oFuhre == null || oFuhre.get_ID_VPOS_TPA_FUHRE_cUF() == null){
					// dann ist die Fuhrenid zwar da, aber die fuhre konnte nicht gelesen werden...
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Fuhre " + idFuhre + " konnte nicht gelesen werden.")));
					return;
				}
				Vector<LAG_LagerBuchungsSatz> vBS = generateLagerbuchungssatz(oFuhre);
				// wenn die Fuhre den Status ALT_WIRD_NICHT_GEBUCHT hat, wird einfach storniert
				if (oFuhre.get_ALT_WIRD_NICHT_GEBUCHT_cF_NN("").equals("Y"))
				{
					for (LAG_LagerBuchungsSatz o: vBS){
						o.set_storno("Y");
					}
				}
				vLagerbuchungen.addAll( vBS);	
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es ist ein Fehler bei der Generierung eines Lagerbuchungssatzes der Fuhre " + idFuhre + " aufgetreten.")));
				return;
			}
		} 
		else
		{
			//
			//  Es ist ein FuhrenOrt...
			//
			try {
				oFuhrenOrt = new RECORD_VPOS_TPA_FUHRE_ORT(idFuhrenOrt);
				
				// die dazugehörige Fuhre lesen und prüfen, ob man überhaupt was machen muss..
				oFuhre = oFuhrenOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
				
				LAG_LagerBuchungsSatz b = generateLagerbuchungssatz(oFuhrenOrt);
				// wenn der Satz ein "ALT_WIRD_NICHT_GEBUCHT" hat, dann wird er storniert, falls er vorhanden war!!
				if (oFuhre.get_ALT_WIRD_NICHT_GEBUCHT_cF_NN("").equals("Y"))
				{
					b.set_storno("Y");
					//return;
				}
				
				if (b != null){
					vLagerbuchungen = new Vector<LAG_LagerBuchungsSatz>();
					vLagerbuchungen.add(b);
				}
				 
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es ist ein Fehler bei der Generierung eines Lagerbuchungssatzes des Fuhrenortes " + idFuhrenOrt + " aufgetreten.")));
				return;
			}
		}

		//
		// Jetzt die notwendigen Lagerbuchungen durchführen...
		//
		for (LAG_LagerBuchungsSatz b:vLagerbuchungen){
			this.verbucheLagerBuchungsSatz(b);
		}
	}
	
	
	
	
	/**
	 * Generiert einen Vektor von LAG_LagerbuchungsSatz-Objekten, die aus einer Buchung einer Fuhre hervorgehen können.
	 * -> Eine Fuhre von einem Firmen-Lager in ein anderes kann 2 Lagerbuchungen generieren.
	 * Im allgemeinen ist es aber nur eine Lagerbuchung
	 * 
	 * @author manfred
	 * @date 08.04.2009
	 * @param oFuhre
	 * @return  Vektor mit den Lagerbuchungssätzen
	 * @throws myException
	 */
	private Vector<LAG_LagerBuchungsSatz> generateLagerbuchungssatz ( RECORD_VPOS_TPA_FUHRE oFuhre ) throws myException{
		Vector<LAG_LagerBuchungsSatz> vRet = new Vector<LAG_LagerBuchungsSatz>();

		if (oFuhre == null ){
			// leeren Vector zurückgeben
			return vRet;
		}
		
		boolean    bStorno = false;
		
		BigDecimal dAblademengeAbn = null;
		BigDecimal dLademengeAbn = null;
		BigDecimal dMengeAbn = null;
		BigDecimal dMengeKorrAbn = null;
		
		
		BigDecimal dAblademengeLief = null;
		BigDecimal dLademengeLief = null;
		BigDecimal dMengeLief = null;
		BigDecimal dMengeKorrLief = null;
		
		String idLagerAbn = null;
		String idLagerLief = null;
		
		String	   sLadedatum = null;
		String 	   sLadezeit = null;
		String	   sAbladedatum = null;
		String 	   sAbladezeit = null;

		
		// der Artikel der Fuhre wird über die Felder ID_ARTIKEL_BEZ_VK bzw. ID_ARTIKEL_BEZ_EK bestimmt
		String idArtikelAbn = "";
		String idArtikelLief = "";
				
		// LägerIDs
		idLagerLief = oFuhre.get_ID_ADRESSE_LAGER_START_cUF();
		idLagerAbn = oFuhre.get_ID_ADRESSE_LAGER_ZIEL_cUF();

		// prüfen, ob der Satz ein Stornosatz ist
		bStorno = oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") || oFuhre.get_DELETED_cUF_NN("N").equalsIgnoreCase("Y");
		
		// ArtikelIDs ermitteln
		RECORD_ARTIKEL_BEZ recBezEK = oFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
		RECORD_ARTIKEL_BEZ recBezVK = oFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
		if (recBezEK != null) idArtikelLief = recBezEK.get_ID_ARTIKEL_cUF_NN("");
		if (recBezVK != null) idArtikelAbn  = recBezVK.get_ID_ARTIKEL_cUF_NN("");
				
		// WE ermitteln: hier die Ablademenge bevorzugen
		dAblademengeAbn = new BigDecimal (oFuhre.get_ANTEIL_ABLADEMENGE_ABN_cUF_NN("0"));
		dLademengeAbn = new BigDecimal(oFuhre.get_ANTEIL_LADEMENGE_ABN_cUF_NN("0"));
		
		if (dAblademengeAbn.compareTo(BigDecimal.ZERO) > 0){
			dMengeAbn = dAblademengeAbn;
		} else {
			dMengeAbn = dLademengeAbn;
		}

		sAbladedatum = oFuhre.get_DATUM_ABLADEN_cUF();
		sAbladezeit = "12:00";
		
		// WA ermitteln: hier die Lademenge bevorzugen
		dAblademengeLief = new BigDecimal (oFuhre.get_ANTEIL_ABLADEMENGE_LIEF_cUF_NN("0"));
		dLademengeLief = new BigDecimal(oFuhre.get_ANTEIL_LADEMENGE_LIEF_cUF_NN("0"));
		
		if (dLademengeLief.compareTo(BigDecimal.ZERO) > 0){
			dMengeLief = dLademengeLief;
		} else {
			dMengeLief = dAblademengeLief;
		}

		sLadedatum = oFuhre.get_DATUM_AUFLADEN_cUF();
		sLadezeit = "12:00";
		
		
		// die Lagerorte des Mandanten ermitteln
		Vector<String> vLagerOrte = bibSES.get__EigeneLieferadressenOhneSonderlager(false) ; //bibROHSTOFF.get_vEigeneLieferadressen();


		// neu: auch eventuell andere Lagerorte berücksichtigen
		vLagerOrte.addAll(bibROHSTOFF.get_vFremdeLieferadressen());

		
		
		LAG_LagerBuchungsSatz b;

		/*
		 * Vorgehen der Buchung:
		 * 
		 * 0. Prüfen, ob die Vorrausetzungen gegeben sind
		 * 	  - Artikel ist da
		 * 	  - Lager ist da
		 *    
		 * 
		 * 1. Es wird für jede Buchung ein Lagerbuchungssatz erzeugt, auch wenn die Zieladresse kein Lager ist
		 * 	  GRUND: es könnte sein, dass es ein Änderungssatz ist, und die Zieladresse vorher ein Lager war. Dann muss ein Storno-Satz geschrieben werden.
		 * 2. Prüfen, ob es eine Lagerbuchung ist.
		 *    Wenn JA: normales Vorgehen
		 *    Wenn NEIN: Storno-Satz schreiben, dass ein eventuell alter Buchungssatz dieser Fuhre storniert wird, d.h. die Menge rausgenommen wird.
		 * */ 

		// den Preis der Fuhre ermitteln über den LagerPreis-Handler
		LAG_LagerPreisHandler oPreisHandler = new LAG_LagerPreisHandler(null);
		BigDecimal dPreis = null;
		CLagerPreisResult oResult = null;
		
		// 1. WarenEingang
		if (idLagerAbn != null && !idLagerAbn.equals("")
				&& idArtikelAbn != null && !idArtikelAbn.equals("")){

			
			// Bei Wareneingangs-Fuhren noch prüfen, ob die Fuhre Abzüge hat:
			if ( dMengeAbn != null && dMengeAbn.compareTo(BigDecimal.ZERO) == 1){
				BigDecimal dMengeAbzug = get_WE_Abzug(oFuhre);
				if (dMengeAbzug != null){
					dMengeAbn = dMengeAbn.subtract(dMengeAbzug);
				}
			}
			
			oResult= oPreisHandler.getPreisFor_Lagerbuchung( oFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),oFuhre, null,idLagerAbn, idArtikelAbn, "WE"); 
			dPreis = (oResult != null ? oResult.get_Preis() : null) ;   
			
			b = new LAG_LagerBuchungsSatz(	idLagerAbn,
					idArtikelAbn,
					dPreis,
					dMengeAbn,
					sAbladedatum,
					sAbladezeit,
					"WE",
					oFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),
					null, null, null, oResult.get_Status());
			
			b.set_storno(bStorno ? "Y" : null);
	
			// 2. prüfen ob Lagerbuchung
			if (!vLagerOrte.contains(idLagerAbn)){
				b.set_storno("Y");
			} 
			
			vRet.add(b);
		}
		
		
		// 2. Teil der Fuhre
		
		// 1. WarenAusgang
		if (idLagerLief != null && !idLagerLief.equals("")
				&& idArtikelLief != null && !idArtikelLief.equals("")){
			
			// wenn im Mandant gesetzt ist, dass der WA auch die Abzüge des Kunden im Lager erscheinen sollen, 
			// dann wird hier der Abzug abgezogen
			if (m_bWA_GEWICHT_NETTO){
				// Bei Warenausgangs-Fuhren noch prüfen, ob die Fuhre Abzüge hat:
				if ( dMengeLief != null && dMengeLief.compareTo(BigDecimal.ZERO) == 1){
					BigDecimal dMengeAbzug = get_WE_Abzug(oFuhre);
					if (dMengeAbzug != null){
						dMengeLief = dMengeLief.subtract(dMengeAbzug);
					}
				}
			}
			
			
			
			dPreis = null;
			oResult =  oPreisHandler.getPreisFor_Lagerbuchung( oFuhre.get_ID_VPOS_TPA_FUHRE_cUF(), oFuhre, null,idLagerLief, idArtikelLief, "WA");
			dPreis = (oResult != null ? oResult.get_Preis() : null) ;   

			b = new LAG_LagerBuchungsSatz( 	idLagerLief,
					idArtikelLief,
					dPreis, 
					dMengeLief.negate(),
					sLadedatum,
					sLadezeit,
					"WA",
					oFuhre.get_ID_VPOS_TPA_FUHRE_cUF(),
					null, 
					null,
					null,
					oResult.get_Status());
			
			b.set_storno(bStorno ? "Y" : null);
			// 2. prüfen ob Lagerbuchung
			if (!vLagerOrte.contains(idLagerLief)){
				b.set_storno("Y");
			}				
			vRet.add(b);
		}
		
		return vRet;
	
	}
	
	
	
	
	/**
	 * Berechnet den Abzug der durch FBAM-Tabelle gegeben ist.
	 * @param oFuhre
	 * @return
	 * @throws myException
	 */
	private BigDecimal get_WE_Abzug(RECORD_VPOS_TPA_FUHRE oFuhre) throws myException {

		BigDecimal bdReturn = oFuhre.get_ABZUG_ABLADEMENGE_ABN_bdValue(BigDecimal.ZERO);
		
		//Manfred: 2018-08-28: wenn der Schalter gesetzt wurde, dann wird der Abzug von der Ladeseite genommen, falls auf der Abladeseite kein Abzug definiert wurde.
		if (ENUM_MANDANT_DECISION.LAGER_ABZUG_VON_WA_NACH_WE_UEBERNEHMEN.is_YES_FromSession()){
			if (bdReturn == null || bdReturn.equals(BigDecimal.ZERO)){
				
				SqlStringExtended s = new SqlStringExtended("select * from " + _TAB.vpos_tpa_fuhre_abzug_vk.fullTableName() + " WHERE ID_VPOS_TPA_FUHRE = ? " );
				s.getValuesList().add(new Param_Long(oFuhre.get_longValue(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fieldName()) ) ) ;

				RecList21 rlAbzuege = new RecList21(_TAB.vpos_tpa_fuhre_abzug_vk)._fill(s);
				
				if (rlAbzuege.size() == 0){
					bdReturn = oFuhre.get_ABZUG_LADEMENGE_LIEF_bdValue(BigDecimal.ZERO);
				}
			}
		}
		
		return bdReturn;
	}

	
	
	
	/**
	 * Generiert ein LAG_LagerbuchungsSatz-Objekt, der aus einer Buchung eines Fuhrenortes hervor geht.
	 * 
	 * @author manfred
	 * @date 08.04.2009
	 * @param oFuhrenOrt
	 * @return
	 * @throws myException
	 */
	private LAG_LagerBuchungsSatz generateLagerbuchungssatz(RECORD_VPOS_TPA_FUHRE_ORT oFuhrenOrt) throws myException{
		
		if (oFuhrenOrt == null ){
			// leeren Vector zurückgeben
			return null;
		}
		
		// die Lagerorte des Mandanten ermitteln
		Vector<String> vLagerOrte = bibSES.get__EigeneLieferadressenOhneSonderlager(false);	//  bibROHSTOFF.get_vEigeneLieferadressen();
		// neu: auch eventuell andere Lagerorte berücksichtigen
		vLagerOrte.addAll(bibROHSTOFF.get_vFremdeLieferadressen());
		
		
		// LagerIDs
		String idLager  = oFuhrenOrt.get_ID_ADRESSE_LAGER_cUF();
		
		// prüfen, ob der Satz ein Stornosatz ist
		boolean    bStorno = oFuhrenOrt.get_DELETED_cUF_NN("N").equalsIgnoreCase("Y");
		
		BigDecimal dAblademenge = new BigDecimal (oFuhrenOrt.get_ANTEIL_ABLADEMENGE_cUF_NN("0"));
		BigDecimal dLademenge = new BigDecimal(oFuhrenOrt.get_ANTEIL_LADEMENGE_cUF_NN("0"));
		BigDecimal dMenge = BigDecimal.ZERO;
		
		String	   sLadedatum = oFuhrenOrt.get_DATUM_LADE_ABLADE_cUF();
		String 	   sLadezeit = "12:00";

		
		String sQuelleZiel = oFuhrenOrt.get_DEF_QUELLE_ZIEL_cUF();
		String sBuchungstyp = "";

		if (sQuelleZiel.equalsIgnoreCase("EK")){
			// Aufladeort: WA
			sBuchungstyp = "WA";
			if (dLademenge.compareTo(BigDecimal.ZERO) > 0){
				dMenge = dLademenge;
			} else {
				dMenge = dAblademenge;
			}
			dMenge = dMenge.negate();
			
		}else{
			// Abladeort: WE
			sBuchungstyp = "WE";
			if (dAblademenge.compareTo(BigDecimal.ZERO) > 0){
				dMenge = dAblademenge;
			} else {
				dMenge = dLademenge;
			}
		
			// Abzug beim WE berücksichtigen
			BigDecimal bdAbzug = oFuhrenOrt.get_ABZUG_MENGE_bdValue(BigDecimal.ZERO);
			dMenge = dMenge.subtract(bdAbzug);
		}

		// den Preis der Fuhre ermitteln über den LagerPreis-Handler
		LAG_LagerPreisHandler oPreisHandler = new LAG_LagerPreisHandler(null);
		
		CLagerPreisResult oResult = oPreisHandler.getPreisFor_Lagerbuchung(
				oFuhrenOrt.get_ID_VPOS_TPA_FUHRE_cUF(), null,
				oFuhrenOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), 
				idLager, oFuhrenOrt.get_ID_ARTIKEL_cUF(), sBuchungstyp);
		
		BigDecimal 	dPreis = (oResult != null ? oResult.get_Preis() : null) ;   
		String  	sStatus = (oResult != null ? oResult.get_Status() : null) ;   
		
		// 1. Lagerbuchungssatz erzeugen
		LAG_LagerBuchungsSatz b = 
			new LAG_LagerBuchungsSatz(	idLager,
										oFuhrenOrt.get_ID_ARTIKEL_cUF(), 
//										new BigDecimal(0),
										dPreis,
										dMenge,
										sLadedatum,
										sLadezeit,
										sBuchungstyp,
										oFuhrenOrt.get_ID_VPOS_TPA_FUHRE_cUF(),
										oFuhrenOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), null,null, sStatus);
		b.set_storno(bStorno ? "Y" : null);
			
		// 2. prüfen auf Lagerbuchung
		if (!vLagerOrte.contains(idLager)){
			b.set_storno("Y");
		}
			
		return b;
	}

	
	/**
	 * Gibt einen Vector mit allen erzeugten SQL-Statements zurück
	 * 
	 * @author manfred
	 * @date 08.04.2009
	 * @return
	 */
	public Vector<String> getSqlStatements(){
		return m_vSQLStatements;
	}
	
	
	/**
	 * Führt alle ermittelten Sql-Statements aus und setzt ein Commit, wenn gewünscht
	 * @author manfred
	 * @date 08.04.2009
	 * @param UseOwnTransaction
	 * @return
	 */
	public boolean executeSqlStatements(boolean UseOwnTransaction){
		boolean bRet = true;
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(m_vSQLStatements, UseOwnTransaction);
		bRet &= mv.get_bIsOK();
		
		if ( UseOwnTransaction){
			if (bRet){
				bibDB.Commit();
			} else {
				bibDB.Rollback();
			}
		}
			
		return bRet;
	}
	

	
	
	/***
	 * Buchungsvorgang mit einem einzigen Buchungssatz
	 * @param pBuchung
	 * @return Boolean(Erfolgreich)
	 * @throws myException 
	 */
	public boolean verbucheLagerBuchungsSatz (LAG_LagerBuchungsSatz pBuchung) 
	{
		boolean bOk = true;  // zwingend true für die folgende logik!!		
		
		boolean bStorno = (pBuchung.get_storno() != null && pBuchung.get_storno().equalsIgnoreCase("Y"));
		RECORD_LAGER_KONTO recLager = GetOldEntryForFuhre(pBuchung.get_id_vpos_tpa_fuhre(), pBuchung.get_id_vpos_tpa_fuhre_ort(), pBuchung.get_buchungstyp());

		// wenn es ein Storno ist und kein alter Lagerbestand da ist, dann einfach zurück zum Aufrufer mit false...
		if (recLager == null && bStorno){
			return false;
		}

		
		boolean bSindGleich = compareBuchungMenge(recLager, pBuchung); 
		boolean bPreisIstGeaendert = checkBuchungPreisGeaendert(recLager,pBuchung);
		boolean bDatumIstGeaendert = checkBuchungDatumGeaendert(recLager,pBuchung);
		
		// wenn das Datum zurückgesetzt wird, muss die Buchung aus dem Lager rausgenommen werden.
		boolean bDatumIstNull = (pBuchung.get_buchungsdatum()== null || pBuchung.get_buchungsdatum().trim().equals("")); 
		
		
		// wenn der vorhandene Satz da ist, und die Sätze sind verschieden oder es ist ein Storno, dann den negativsatz schreiben
		// es wird kein Negativsatz geschrieben, wenn kein alter Satz vorhanden ist. Dann ist es ein anderer Fehler!!
		if (recLager != null && ( !bSindGleich || bStorno  || bDatumIstNull ) ){
			
			// NEGATIVBUCHUNG 
			GenerateNegativeBuchung(recLager);
			if (!bibMSG.get_bIsOK())
			{
				bOk = false;
			}
		} 

		
		
			
		boolean bMengeIstNull = pBuchung.get_menge().equals(BigDecimal.ZERO);
		
		// wenn es Unterschiede gbit und wenn es kein Storno-Satz ist, muss es auch einen positiven Satz dazu geben!!
		// 2009-10-15: Wenn die Menge == 0 ist, dann kein Buchungssatz schreiben!!
		// 2010-08-09: Wenn das Datum leer ist, dann kein Buchungssatz schreiben!!
		if (bOk && !bSindGleich && !bStorno && !bMengeIstNull && !bDatumIstNull){            
			GenerateKontoBuchung(pBuchung);
		}	
		else {
			// wenn beide gleich sind, der Preis sich aber geändert hat, dann einen Update auf den Preis machen...
			if (bOk && !bStorno && !bMengeIstNull && bSindGleich && bPreisIstGeaendert && !bDatumIstNull){
				GeneratePreiskorrekturBuchung(recLager, pBuchung);
			}
			// wenn beide gleich sind, das Datum sich aber geändert hat, dann einen Update auf das Datum machen...
			if (bOk && !bStorno && !bMengeIstNull && bSindGleich && bDatumIstGeaendert && !bDatumIstNull){
				GenerateDatumskorrekturBuchung(recLager, pBuchung);
			}
		}
		
		
		return bOk;
	}
	
	
	/**
	 * Storniert eine Lagerbuchung durch eine negative Gegenbuchung
	 * und setzt das Storno-Kennzeichen
	 * @param idLagerKonto
	 * @param bUseOwnTransaction 
	 * 			true: es wird gleich geschrieben
	 * 			false: die SqlStatements werden nur erzeugt und müssen vom aufrufenden Programm ausgeführt werden.
	 * @return
	 */
	public boolean storniereLagerbuchung(String idLagerKonto, boolean bUseOwnTransaction){
		boolean bRet = false;
		try {
			RECLIST_LAGER_KONTO reclist = null;
			
			RECORD_LAGER_KONTO rec = new RECORD_LAGER_KONTO(idLagerKonto);
			
			if (rec.get_ID_VPOS_TPA_FUHRE_cUF() != null){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fuhren-Buchungen können nicht storniert werden!")));
				return false;
			}
			
			boolean bIstUmbuchung = rec.get_BUCHUNG_HAND_cF_NN("").equalsIgnoreCase("U");
			
			if (bIstUmbuchung) {
				// wenn es eine Umbuchung ist, dann finden der Gegenbuchung
				// select von Hand durchführen, weil records keine Sekunden beherrschen!!
				String sqlWhere = " BUCHUNG_HAND = 'U' AND ID_ADRESSE_LAGER = "+ rec.get_ID_ADRESSE_LAGER_cUF() + 
								  " and to_char(erzeugt_am, 'YYYY-MM-DD-HH24:MI:SS') = (select to_char(erzeugt_am, 'YYYY-MM-DD-HH24:MI:SS') from jt_lager_konto where id_lager_konto = " + rec.get_ID_LAGER_KONTO_cUF() + ")"	;
				
				reclist = new RECLIST_LAGER_KONTO(sqlWhere,"");
				
				if (reclist.size() > 0){
					for (RECORD_LAGER_KONTO o: reclist.values()){
						if (o.is_STORNO_NO()){
							GenerateNegativeBuchung(o);
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Info: Der Lagersatz " + o.get_ID_LAGER_KONTO_cUF_NN("?") + " wurde storniert.")));
						} else {
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Info: Der Lagersatz " + o.get_ID_LAGER_KONTO_cUF_NN("?") + " war schon storniert.")));
						}
					}

					if (bUseOwnTransaction){
						if (bibMSG.get_bIsOK()){
							bRet = this.executeSqlStatements(true);
						}
					}
				}
			} else {
				// Einzelsatz stornieren
				if (rec.is_STORNO_NO()){
					GenerateNegativeBuchung(rec);
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Info: Der Lagersatz " + rec.get_ID_LAGER_KONTO_cUF_NN("?") + " wurde storniert.")));
					if (bUseOwnTransaction){
						if (bibMSG.get_bIsOK()){
							bRet = this.executeSqlStatements(true);
						}
					}
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler: Der Lagersatz " + rec.get_ID_LAGER_KONTO_cUF_NN("?") + " war schon storniert.")));
				}
			}
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Stornobuchung konnte nicht durchgeführt werden. ")));
		}
		
		return bRet;
	}
	
	
	
	public boolean storniereLagerbuchung_old(String idLagerKonto, boolean bUseOwnTransaction){
		boolean bRet = false;
		try {
			RECORD_LAGER_KONTO rec = new RECORD_LAGER_KONTO(idLagerKonto);
			
			if (rec.get_ID_VPOS_TPA_FUHRE_cUF() != null){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fuhren-Buchungen können nicht storniert werden!")));
				return false;
			}
			
			
			if (rec.is_STORNO_NO()){
				
				GenerateNegativeBuchung(rec);
				
				if (bUseOwnTransaction){
					
					if (bibMSG.get_bIsOK()){
						bRet = this.executeSqlStatements(true);
					}
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler: Der Lagersatz war schon storniert.")));
			}
			
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Stornobuchung konnte nicht durchgeführt werden. ")));
		}
		
		return bRet;
	}
	
	
	
	
	
	/***
	 * Generiert einen neue Konto-Buchung und eine WA-Buchung erzeugt einen leeren Kontobewegungssatz.
	 * @param oNew
	 * @throws myException
	 */
	private void GenerateKontoBuchung(LAG_LagerBuchungsSatz oNew){
		
		try
		{
			m_vSQLStatements.add(SQLInsertNewLagerPos(oNew));
	
		} catch (myException e)	{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler beim erzeugen des Lagersatze-SQL : ") + oNew.get_id_adresse_lager() + 
					new MyE2_String(" mit Material ") + oNew.get_id_artikel_sorte()));
		}
	}

	
	
	/**
	 * Generieren einer negativen Buchung, 
	 * die Transaktion wird nicht geschlossen (kein Commit)
	 * Die negativ-Buchung bekommt den gleichen Buchungstyp wie die Ursprungsbuchung
	 * Der/die Lagerbewegungs-Sätze werden korrigiert.
	 * @author manfred
	 * @date 12.03.2009
	 * @param oLager : der Lagereintrag der storniert werden soll, d.h. für den eine Gegenbuchung eingefügt wird
	 * @return
	 * @throws myException 
	 * @throws myException
	 */
	private void GenerateNegativeBuchung(RECORD_LAGER_KONTO oLager)   {
		
		// das aktuelle Saldo ermitteln, das auf dem Lager vorhanden ist
		//BigDecimal dOldSaldo = GetLastSaldo( oLager.get_ID_ADRESSE_LAGER_cUF(), oLager.get_ID_ARTIKEL_SORTE_cUF());
		

		// einen negativen Buchungssatz schreiben. Das geht über einen Lagerbuchungssatz...
		LAG_LagerBuchungsSatz oNew;
		try
		{
		
			
			
			oNew = new LAG_LagerBuchungsSatz(
					oLager.get_ID_ADRESSE_LAGER_cUF(),
					oLager.get_ID_ARTIKEL_SORTE_cUF(),
					oLager.get_PREIS_bdValue(null),
					new BigDecimal( oLager.get_MENGE_dValue(0.0) * -1.0 ),
					oLager.get_BUCHUNGSDATUM_cUF(),
					oLager.get_BUCHUNGSZEIT_cUF(),
					oLager.get_BUCHUNGSTYP_cUF(),
					oLager.get_ID_VPOS_TPA_FUHRE_cUF(),
					oLager.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), 
					oLager.get_BEMERKUNG_cUF(),
					oLager.get_BUCHUNG_HAND_cUF(),
					oLager.get_STATUS_PREIS_cUF());
			oNew.set_storno("Y");
			oNew.set_ist_komplett("Y");
			oNew.set_id_lager_konto_parent(oLager.get_ID_LAGER_KONTO_cUF());
			
			if (oLager.get_MENGE_BUCH_bdValue(null) != null){
				oNew.set_menge_buchhalterisch( oLager.get_MENGE_BUCH_bdValue(BigDecimal.ZERO).negate() );
			}
			
			m_vSQLStatements.add( SQLInsertNewLagerPos(oNew));
			
		} catch (myException e)	{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler einfügen des Korrektur-Lagersatzes!")));			
		}
		
		
		
		// update des alten Lagersatzes / OHNE COMMIT
		try
		{
			oLager.set_NEW_VALUE_STORNO("Y");
			oLager.set_NEW_VALUE_IST_KOMPLETT("Y");
			oLager.set_NEW_VALUE_IST_GEAENDERT("Y");
			
			Vector<String> vDoNotUpdate = DB_STATICS.get_AutomaticWrittenFields_STD();
			vDoNotUpdate.add("BUCHUNGSDATUM");
			vDoNotUpdate.add("BUCHUNGSZEIT");
			
			m_vSQLStatements.add(oLager.get_SQL_UPDATE_STATEMENT(vDoNotUpdate,true));

		} catch (myException e)	{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler beim ändern des zu stornierenden Lagersatzes!")));
		}
	
		// mögliche Lagerbewegungssätze anpassen, die schon mal geschrieben wurden
		updateLagerBewegungsSatz(oLager);
		
	}

	
	/**
	 * Generieren einer Preiskorrektur-Buchung, 
	 * die Transaktion wird nicht geschlossen (kein Commit)
	 * Die negativ-Buchung bekommt den gleichen Buchungstyp wie die Ursprungsbuchung
	 * Der/die Lagerbewegungs-Sätze werden korrigiert.
	 * @author manfred
	 * @date 12.03.2010
	 * @param oLager : der Lagereintrag der storniert werden soll, d.h. für den eine Gegenbuchung eingefügt wird
	 * @return
	 * @throws myException 
	 * @throws myException
	 */
	private void GeneratePreiskorrekturBuchung(RECORD_LAGER_KONTO oLager,LAG_LagerBuchungsSatz pBuchung)   {
		
		// update des alten Lagersatzes / OHNE COMMIT
		String sPreis = null;
		BigDecimal bdPreis = null;
		try
		{
			bdPreis =pBuchung.get_preis();
			
			if (bdPreis != null){
				sPreis = MyNumberFormater.formatDez(bdPreis.toPlainString(), 2, true);
			}
			
			oLager.set_NEW_VALUE_PREIS( sPreis );
			oLager.set_NEW_VALUE_STATUS_PREIS(pBuchung.get_status_preis());
			oLager.set_NEW_VALUE_IST_GEAENDERT("Y");
			
			Vector<String> vDoNotUpdate = DB_STATICS.get_AutomaticWrittenFields_STD();
			vDoNotUpdate.add("BUCHUNGSDATUM");
			vDoNotUpdate.add("BUCHUNGSZEIT");
			m_vSQLStatements.add(oLager.get_SQL_UPDATE_STATEMENT(vDoNotUpdate, true));
			
		} catch (myException e)	{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler beim ändern des Preises des Lagersatzes!")));
		}
		
		
		// mögliche Lagerbewegungssätze anpassen, die schon mal geschrieben wurden
		String sUpdateLagerBewegungssätze = LAG_LagerPreisHandler.getUpdateLagerBewegungsSatzPreise(oLager, bdPreis);
		if (sUpdateLagerBewegungssätze != null){
			m_vSQLStatements.add(sUpdateLagerBewegungssätze);
		}

	}

	
	/**
	 * Generieren einer Datums-Korrektur-Buchung, 
	 * die Transaktion wird nicht geschlossen (kein Commit)
	 * @author manfred
	 * @date 12.03.2010
	 * @return
	 * @throws myException 
	 * @throws myException
	 */
	private void GenerateDatumskorrekturBuchung(RECORD_LAGER_KONTO oLager,LAG_LagerBuchungsSatz pBuchung)   {
		
		// update des alten Lagersatzes / OHNE COMMIT
		try
		{
			String sDatum = null;
			String sZeit = null;
			
			
			sDatum = pBuchung.get_buchungsdatum_formatted();
			sZeit = pBuchung.get_buchungszeit();
			
			
			oLager.set_NEW_VALUE_BUCHUNGSDATUM(sDatum);
			oLager.set_NEW_VALUE_BUCHUNGSZEIT(sZeit);
			oLager.set_NEW_VALUE_IST_GEAENDERT("Y");
			
			Vector<String> vDoNotUpdate = DB_STATICS.get_AutomaticWrittenFields_STD();
			m_vSQLStatements.add(oLager.get_SQL_UPDATE_STATEMENT(vDoNotUpdate, true));
			
		} catch (myException e)	{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler beim ändern der Buchungszeit des Lagersatzes!")));
		}

	}


	/**
	 * Anpassen der Lagerbewegungs-Sätze, die evtl. schon mal geschrieben wurden
	 * Author: manfred
	 * 22.05.2009
	 *
	 * @param oLager
	 */
	private void updateLagerBewegungsSatz(RECORD_LAGER_KONTO oLager) {
		// jetzt die Einträge der Lagerbewegungen erneuern bzw. schreiben
		String sSqlKto = "";
		String sSqlBewegung = "";
		
		try
		{
			// falls ein WA storniert wird: Alle zugeordneten Wareneingänge dürfen dann nicht mehr komplett sein.
			if (oLager.get_BUCHUNGSTYP_cUF().equalsIgnoreCase("WA"))
			{
				sSqlKto = " UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO " +
						" SET JT_LAGER_KONTO.IST_KOMPLETT = null " +
						" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO IN ( " +
						" SELECT JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG FROM  " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
						" WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_AUSGANG = " + oLager.get_ID_LAGER_KONTO_cUF() + 
						")";
				sSqlBewegung = "DELETE FROM " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
						" WHERE ID_LAGER_KONTO_AUSGANG = " + oLager.get_ID_LAGER_KONTO_cUF();
				

			}
			else // falls ein WE storniert wird muss für die WA-Konto-Sätze auf jeden fall das IstKomplett gelöscht werden
			{
				sSqlKto = "UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO " +
				" SET JT_LAGER_KONTO.IST_KOMPLETT = null " +
				" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO IN ( " +
				" SELECT JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_AUSGANG FROM  " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
				" WHERE JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = " + oLager.get_ID_LAGER_KONTO_cUF() + 
				")";

				// und dann der Lagerbewegungs-Satz geputzt werden
				sSqlBewegung = "DELETE FROM " + bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
				" WHERE ID_LAGER_KONTO_EINGANG = " + oLager.get_ID_LAGER_KONTO_cUF();
			}
		} catch (myException e)
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Fehler beim anpassen des Lagerkonto-  und Bewegungssatzes!")));
		}

		
		
		m_vSQLStatements.add( sSqlKto);
		m_vSQLStatements.add( sSqlBewegung);
	}
	
	
	/***
	 * Vergleicht Lagerbuchungen, ob sich was an der Buchung/Buchungen geändert hat.
	 * - Anzahl der Buchungen
	 * - Materialien
	 * - Preise
	 * - Mengen
	 * @author manfred
	 * @date 12.03.2009
	 * @param old
	 * @param pBuchung
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private boolean CompareBuchung(RECLIST_LAGER_KONTO old, ArrayList<LAG_LagerBuchungsSatz> pBuchung ){
		boolean bRet = false;
		
		String key = "";
		
		// gleiche Anzahl der Buchungssätze
		if (old.size() == pBuchung.size()){
			// 2 Sortierte Liste aufbauen, mit den Schlüsseln/Werten
			//			Lager:Fuhre:FuhrenOrt:Material:Menge:Preis
			// Wenn die gleich sind, dann hat sich in der Lager-Buchung eigentlich nix geändert.
			TreeMap<String,String> lOld = new TreeMap<String, String>();
			for (int i=0;i<old.size();i++)
			{
				RECORD_LAGER_KONTO oREC;
				try
				{
					oREC = old.get(i);
					key = oREC.get_ID_ADRESSE_LAGER_cF_NN("") + ":" + 
					oREC.get_ID_VPOS_TPA_FUHRE_cF_NN("") + ":" +
					oREC.get_ID_VPOS_TPA_FUHRE_ORT_cF_NN("") + ":" +
					oREC.get_ID_ARTIKEL_SORTE_cF_NN("") +":" +
					oREC.get_MENGE_cUF_NN("") +":" +
					oREC.get_PREIS_cUF_NN("");

					lOld.put(key, key);
				} 
				catch (myException e)
				{
					e.printStackTrace();
				}
			}
			
			
			TreeMap<String,String> lNew = new TreeMap<String, String>();
			for (LAG_LagerBuchungsSatz oL : pBuchung)
			{
				key = oL.get_id_adresse_lager() +  ":" + 
				oL.get_id_vpos_tpa_fuhre() + ":" + 
				oL.get_id_vpos_tpa_fuhre_ort() +  ":" + 
				oL.get_id_artikel_sorte() +  ":" + 
				oL.get_menge().toPlainString() +  ":" + 
				oL.get_preis().toPlainString();
			
				lNew.put(key, key);
			}
			
			// jetzt den einen durchlaufen, und schauen, ob es den key auch im neuen gibt. Wenn ja für alle, ist alles gleich!
			for (String k: lOld.keySet())
			{
				bRet &= lNew.containsKey(k);
			}
		
			// ein wenig aufräumen
			lNew.clear();
			lOld.clear();
		}
		
		return bRet;
	}
	
	
	/**
	 * Vergleicht eine neue Buchung mit einer alten Buchung. wenn sich was geändert hat, 
	 * dann wird false zurückgeliefert.
	 * Der Schwerpunkt liegt hier auf der Menge.
	 * @author manfred
	 * @date 19.03.2009
	 * @param old
	 * @param pBuchung
	 * @return
	 */
	private boolean compareBuchungMenge(RECORD_LAGER_KONTO old, LAG_LagerBuchungsSatz pBuchung ){
		String key_old = "**";
		String key_new = "";
		
		try
		{
			if (old != null){
				key_old =  	old.get_ID_ADRESSE_LAGER_cUF_NN("") + ":" + 
							old.get_ID_VPOS_TPA_FUHRE_cUF_NN("null") + ":" +
							old.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("null") + ":" +
							old.get_ID_ARTIKEL_SORTE_cUF_NN("") +":" +
							old.get_MENGE_cUF_NN("") ;
			}
			
		} catch (myException e)
		{
			key_old = "**";
		}
					
		key_new = 	pBuchung.get_id_adresse_lager() +  ":" + 
					pBuchung.get_id_vpos_tpa_fuhre() + ":" + 
					pBuchung.get_id_vpos_tpa_fuhre_ort() +  ":" + 
					pBuchung.get_id_artikel_sorte() +  ":" + 
					pBuchung.get_menge().toPlainString() ;
		
		return key_old.equalsIgnoreCase(key_new);
	}
	
	
	/**
	 * Vergleicht eine neue Buchung mit einer alten Buchung. Wenn sich was geändert hat, wird true
	 * zurückgeliefert.
	 * Wenn kein alter Preis vorhanden ist, wird false zurückgeliefert, da der Preis sich nicht geändert hat.
	 * D.h. es wird nur true zurückgeliefert, wenn es einen Preis gab, der sich auch geändert hat!!
	 * 
	 * Author: manfred
	 * 10.02.2010
	 *
	 * @param old
	 * @param pBuchung
	 * @return
	 */
	private boolean checkBuchungPreisGeaendert(RECORD_LAGER_KONTO old, LAG_LagerBuchungsSatz pBuchung){
		String key_old = "**";
		String key_new = "";
		
		// nur wenn es einen alten Preis gab, wird auch geprüft, sonst liegt keine Preisänderung vor
		if (old == null) return false;
		
		try
		{
			if (old != null){
				key_old =  	old.get_ID_ADRESSE_LAGER_cUF_NN("") + ":" + 
							old.get_ID_VPOS_TPA_FUHRE_cUF_NN("null") + ":" +
							old.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("null") + ":" +
							old.get_ID_ARTIKEL_SORTE_cUF_NN("") +":" +
							old.get_PREIS_cF_NN("");
			}
		} catch (myException e)	{
			key_old = "**";
		}
		
					
		try {
			key_new = 	pBuchung.get_id_adresse_lager() +  ":" + 
						pBuchung.get_id_vpos_tpa_fuhre() + ":" + 
						pBuchung.get_id_vpos_tpa_fuhre_ort() +  ":" + 
						pBuchung.get_id_artikel_sorte() +  ":" +
						(pBuchung.get_preis() == null ? "" :	MyNumberFormater.formatDez( pBuchung.get_preis().toPlainString(),2,true) );
		} catch (myException e) {
			key_new = "";
		}
		//MyNumberFormater.formatDez(pBuchung.get_preis().toPlainString(), 2, true);
		return !key_old.equalsIgnoreCase(key_new);
	}
	
	
	/**
	 * Vergleicht eine neue Buchung mit einer alten Buchung. Wenn sich was geändert hat, wird true
	 * zurückgeliefert.
	 * Wenn kein alter Preis vorhanden ist, wird false zurückgeliefert, da der Preis sich nicht geändert hat.
	 * D.h. es wird nur true zurückgeliefert, wenn es einen Preis gab, der sich auch geändert hat!!
	 * 
	 * Author: manfred
	 * 10.02.2010
	 *
	 * @param old
	 * @param pBuchung
	 * @return
	 */
	private boolean checkBuchungDatumGeaendert(RECORD_LAGER_KONTO old, LAG_LagerBuchungsSatz pBuchung){
		String key_old = "**";
		String key_new = "";
		
		// nur wenn es einen alten Eintrag gab, wird auch geprüft
		if (old == null) return false;
		
		try
		{
			if (old != null){
				key_old =  	old.get_ID_ADRESSE_LAGER_cUF_NN("") + ":" + 
							old.get_ID_VPOS_TPA_FUHRE_cUF_NN("null") + ":" +
							old.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("null") + ":" +
							old.get_ID_ARTIKEL_SORTE_cUF_NN("") + ":" +
							old.get_BUCHUNGSDATUM_cUF_NN("") + ":" +
							old.get_BUCHUNGSZEIT_cUF_NN("");
				
			}
		} catch (myException e)	{
			key_old = "**";
		}
		
					
		try {
			key_new = 	pBuchung.get_id_adresse_lager() +  ":" + 
						pBuchung.get_id_vpos_tpa_fuhre() + ":" + 
						pBuchung.get_id_vpos_tpa_fuhre_ort() +  ":" + 
						pBuchung.get_id_artikel_sorte() +  ":" +
						(pBuchung.get_buchungsdatum() == null ? "" : pBuchung.get_buchungsdatum()) +  ":" +
						(pBuchung.get_buchungszeit() == null ? "" : pBuchung.get_buchungszeit());
		} catch (Exception e) {
			key_new = "";
		}
		
		return !key_old.equalsIgnoreCase(key_new);
	}

	
	
	/***
	 * Erzeugt ein Insert-SQL-Statement für einen neuen Lagersatz.
	 * Zusätzlich muss das vorher ermittelte neue Saldo 
	 * @param lagersatz
	 * @param saldo (BigDecimal)
	 * @return
	 * @throws myException
	 */
	private String SQLInsertNewLagerPos(LAG_LagerBuchungsSatz lagersatz) throws myException{
		String sSql = "";
		
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
		
//		BigDecimal dSaldo = BigDecimal.ZERO;
//		dSaldo = getSaldoFromCache(lagersatz.get_id_adresse_lager(), lagersatz.get_id_artikel_sorte());
//		dSaldo = dSaldo.add(lagersatz.get_menge());
//		setSaldoCache(lagersatz.get_id_adresse_lager(), lagersatz.get_id_artikel_sorte(), dSaldo);
		
		oSql.addSQL_Paar("ID_LAGER_KONTO", "SEQ_LAGER_KONTO.NEXTVAL", false);

	    oSql.addSQL_Paar("ID_ADRESSE_LAGER", lagersatz.get_id_adresse_lager(), false);
	    oSql.addSQL_Paar("ID_ARTIKEL_SORTE", lagersatz.get_id_artikel_sorte(), false);
	    oSql.addSQL_Paar("PREIS", lagersatz.get_preis() == null ? null : lagersatz.get_preis().toPlainString() , false);
	    oSql.addSQL_Paar("MENGE", lagersatz.get_menge().toPlainString() , false);
	    oSql.addSQL_Paar("BUCHUNGSDATUM",lagersatz.get_buchungsdatum() , true);
	    oSql.addSQL_Paar("BUCHUNGSZEIT", lagersatz.get_buchungszeit(),true );
	    oSql.addSQL_Paar("BUCHUNGSTYP", lagersatz.get_buchungstyp() , true);
	    oSql.addSQL_Paar("ID_VPOS_TPA_FUHRE", lagersatz.get_id_vpos_tpa_fuhre() , false);
	    oSql.addSQL_Paar("ID_VPOS_TPA_FUHRE_ORT", lagersatz.get_id_vpos_tpa_fuhre_ort(), false);
	    oSql.addSQL_Paar("STORNO", lagersatz.get_storno() , true);
	    oSql.addSQL_Paar("ID_LAGER_KONTO_PARENT", lagersatz.get_id_lager_konto_parent() , false);
	    oSql.addSQL_Paar("IST_KOMPLETT", lagersatz.get_ist_komplett() , true);
	    oSql.addSQL_Paar("BEMERKUNG", lagersatz.get_bemerkung(), true);
	    oSql.addSQL_Paar("BUCHUNG_HAND", lagersatz.get_buchung_hand(),true);
	    oSql.addSQL_Paar("STATUS_PREIS", lagersatz.get_status_preis(),true);
	    oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
	    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
	    oSql.addSQL_Paar("IST_GEAENDERT","Y",true);

	    //2016-02-04 anforderung wegen L01
	    oSql.addSQL_Paar("MENGE_BUCH", lagersatz.get_menge_buchhalterisch() == null ? null : lagersatz.get_menge_buchhalterisch().toPlainString(),false);
	    
	    sSql = oSql.get_CompleteInsertString("JT_LAGER_KONTO", bibE2.cTO());

		return sSql;
	}

		
	
	/**
	 * Ermittelt den alten Eintrag einer Fuhre bzw. eines Fuhrenortes für ein Material!
	 * @author manfred
	 * @date 19.03.2009
	 * @param idVposTpaFuhre
	 * @param idVposTpaFuhrenOrt
	 * @param buchungsTyp TODO
	 * @return
	 * @throws myException
	 */
	private RECORD_LAGER_KONTO GetOldEntryForFuhre (String idVposTpaFuhre, String idVposTpaFuhrenOrt, String buchungsTyp) {

		
		RECORD_LAGER_KONTO kto = null;
		
		// wenn keine fuhre / ort, dann nix machen
		if (idVposTpaFuhre == null && idVposTpaFuhrenOrt == null){
			//bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde keine Fuhre oder Fuhrenort zur Lagerermittlung angegeben!") ));
			return null;
		}

		
		
		String sWhere = " AND BUCHUNGSTYP = '" + buchungsTyp + "'";
		if (idVposTpaFuhrenOrt == null){
			sWhere +=  " AND ID_VPOS_TPA_FUHRE_ORT IS NULL AND ID_VPOS_TPA_FUHRE = " + idVposTpaFuhre;
		} else
		{
			sWhere +=  " AND ID_VPOS_TPA_FUHRE_ORT = " + idVposTpaFuhrenOrt;
		}
		
		
		
		
		
		RECLIST_LAGER_KONTO listLager 	= null;
		try {
			listLager = new RECLIST_LAGER_KONTO("SELECT * FROM "
			+ bibE2.cTO()+".JT_LAGER_KONTO WHERE STORNO IS NULL "
			+ sWhere
			);
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// es sollte eigentlich immer nur ein satz aktiv sein: Also-> Fehler
		if (listLager!= null && listLager.size() > 1)
		{
			String s = idVposTpaFuhre == null ? "" : "Fuhre: " + idVposTpaFuhre;
			s += idVposTpaFuhrenOrt == null ? "" : "Fuhren-Ort: " + idVposTpaFuhrenOrt;
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es ist mehr als eine aktive Buchung im Konto vorhanden! ") + s));
			
		}
		
		// wenn ein Satz gefunden wurde, dann den zurückgeben!!
		if (listLager != null && listLager.size() == 1){
			try
			{
				kto = listLager.get(0);
			} catch (myException e)
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der bestehende Lagersatz für Fuhre/Ort " + idVposTpaFuhre + "/" + idVposTpaFuhrenOrt+ " konnte nicht gelesen werden! ") ));
			}
		}
		
		return kto;
	}
	

	



}






