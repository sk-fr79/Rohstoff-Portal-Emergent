/**
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGER_INVENTUR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.exceptions.myException;

/**
 * Klasse zum Verarbeiten der LagerKonto-Daten in den "Setzkasten", 
 * d.h. die Zuordnung von Lager-Ausgang zu Lager-Eingangs-Mengen und Preisen
 * 
 * @author manfred
 *
 */
public class LAG_LagerBewegungHandler {

	
	private int    			iCount = 0;
	private MyE2_Column   	colLoopInfo = null;
	
	
	
	/**
	 * @param IdMandant
	 */
	public LAG_LagerBewegungHandler(MyE2_Column   	ColLoopInfo ) {
		
		this.colLoopInfo = ColLoopInfo;
	}
	
	/**
	 * Alle Lagereinträge im Lager werden automatisch reorganisiert
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @return
	 */
	public void ReorganiseLagerEntries()	{
		
		RECLIST_MANDANT lMandant = null;
		try {
			 lMandant = new RECLIST_MANDANT (
				       "SELECT * FROM "+bibE2.cTO()+".JD_MANDANT");
		} catch (myException e) {
			// TODO Auto-generated catch block
			lMandant = null;
		}
		
		// jetzt für alle Mandanten die Lager organisieren
		String idMandant = "";
		for(RECORD_MANDANT oMandant: lMandant.values()){
			try {
				idMandant = oMandant.get_ID_MANDANT_cUF();
				ReorganiseLagerEntries(idMandant);
			} catch (myException e) {
				// TODO Auto-generated catch block
				continue;
			}
		}

	}
	
	/**
	 * Nur die Lager des Mandanten werden automatisch reorganisiert zum aktuellen Datum
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @param idMandant
	 * @return
	 */
	public void ReorganiseLagerEntries(String idMandant){
		String sDatum = myDateHelper.ChangeNormalString2DBFormatString(bibALL.get_cDateNOW()) ;
		try {
			ReorganiseLagerEntries(idMandant,sDatum);
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	/**
	 * Nur die Lager des Mandanten werden automatisch reorganisiert zum vorgegebenen Datum
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @param idMandant
	 * @return
	 * @throws myException 
	 */
	public void ReorganiseLagerEntries(String idMandant , String sDateISO) throws myException{

		// ermitteln aller Lageradressen eines Mandanten:
		//ermitteln aller Läger eines Mandanten, die verwaltet werden
		String [][] asIds = new String[0][0];
		String sSql = "SELECT DISTINCT  JT_LAGER_KONTO.ID_ADRESSE_LAGER, JT_LAGER_KONTO.ID_ARTIKEL_SORTE " +
		" FROM "+ bibE2.cTO()+".JT_LAGER_KONTO " +
		" WHERE NVL(JT_LAGER_KONTO.STORNO,'N') ='N' " +
		" AND   NVL(JT_LAGER_KONTO.IST_KOMPLETT,'N') = 'N' " +
		" AND  JT_LAGER_KONTO.ID_MANDANT = ?" + //idMandant +
		" AND  JT_LAGER_KONTO.BUCHUNGSDATUM <= to_date(? ,'yyyy-MM-dd') " + //to_date('" + sDateISO + "','yyyy-MM-dd') " +
		" ORDER BY 1,2 ";

		SqlStringExtended sqlext = new SqlStringExtended(sSql);
		sqlext.getValuesList().add(new Param_Long(Long.parseLong(idMandant)));
		sqlext.getValuesList().add(new Param_String("",sDateISO));
		
		asIds = bibDB.EinzelAbfrageInArray(sqlext); 
		
		for (int i=0;i<asIds.length;i++)
		{
			ReorganiseLagerEntries(idMandant, asIds[i][0],asIds[i][1],sDateISO);
		}
	}

	
	/**
	 * Es wird ein bestimmtes Lager eines Mandanten reorganisiert
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @param idMandant
	 * @param IdAdresseLager
	 * @param sDateISO Letzer Tag für den der Setzkasten reorganisiert werden soll im ISO-Foramt
	 * @return
	 */
	public void ReorganiseLagerEntries(String idMandant, String IdAdresseLager, String sDateISO){
		
		// ermitteln aller Artikel, die in dem Lager geführt werden
		String [][] asIds = new String[0][0];

		String sSql = " SELECT DISTINCT JT_LAGER_KONTO.ID_ARTIKEL_SORTE " +
		" FROM "+ bibE2.cTO()+".JT_LAGER_KONTO " +
		" WHERE JT_LAGER_KONTO.ID_MANDANT = " + idMandant + 
		" AND JT_LAGER_KONTO.ID_ADRESSE_LAGER = " + IdAdresseLager +
		" AND  JT_LAGER_KONTO.BUCHUNGSDATUM <= to_date('" + sDateISO + "','yyyy-MM-dd') ";
		
		asIds = bibDB.EinzelAbfrageInArray(sSql);

		for (int i=0;i<asIds.length;i++)
		{
			try {
				ReorganiseLagerEntries(idMandant, IdAdresseLager, asIds[i][0], sDateISO );
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	
	/**
	 * Es wird ein bestimmter Artikel in einem Lager Reorganisiert
	 * Author: manfred
	 * 26.03.2009
	 *
	 * @param idMandant
	 * @param IdAdresseLager
	 * @param IdArtikel
	 * @param DatumBis  - Datum bis zu dem die Verbuchung stattfinden soll im ISO-Format yyyy-MM-dd
	 * @return
	 * @throws myException 
	 */
	public void ReorganiseLagerEntries(String idMandant, String IdAdresseLager, String IdArtikel, String sDatumBisISO) throws myException{
		
		ArrayList<String> alDatumsgrenzen = new ArrayList<String>();
		
		String sDatum = null;
		sDatum = sDatumBisISO + " 23:59";
		
		// alle einzelnen Lagerinventur-Abschnitte ermitteln
		RECLIST_LAGER_INVENTUR listLagerInventur = new RECLIST_LAGER_INVENTUR("SELECT * FROM " + bibE2.cTO()  + ".JT_LAGER_INVENTUR WHERE ID_MANDANT = " + idMandant +
				" AND ID_ADRESSE_LAGER = " + IdAdresseLager + " AND ID_ARTIKEL_SORTE = " + IdArtikel + 
				" AND  to_date( to_char(BUCHUNGSDATUM,'yyyy-mm-dd') || ' ' || BUCHUNGSZEIT, 'yyyy-mm-dd HH24:mi')  <= to_date('" + sDatumBisISO + "', 'yyyy-MM-dd HH24:mi') " +
				" ORDER BY BUCHUNGSDATUM " );
		
		// für jeden Abschnitt die Verbuchung durchführen
		for (int i=0; i <listLagerInventur.size(); i++){
			sDatum = listLagerInventur.get(i).get_BUCHUNGSDATUM_cUF() + " " + listLagerInventur.get(i).get_BUCHUNGSZEIT_cUF_NN("23:59");
			alDatumsgrenzen.add(sDatum);
		}
		// letzten Eintrag noch dazufügen
		sDatum = sDatumBisISO + " 23:59";
		alDatumsgrenzen.add(sDatum);
		
		// Alle Datumsbereiche verbuchen
		for (int j = 0; j < alDatumsgrenzen.size(); j ++){
		
			Vector<LagerDaten> vLagerEingang = new Vector<LagerDaten>();
			Vector<LagerDaten> vLagerAusgang = new Vector<LagerDaten>();
			
			vLagerAusgang = FillLagerDaten(idMandant, IdAdresseLager, IdArtikel, EnumBuchungstyp.WA, alDatumsgrenzen.get(j) );
			if (vLagerAusgang.size() == 0){
				// wenn schon keine neuen Ausgangssätze da sind, dann braucht man nicht weiter machen...
				continue;
			}
			
			vLagerEingang = FillLagerDaten(idMandant, IdAdresseLager, IdArtikel, EnumBuchungstyp.WE, alDatumsgrenzen.get(j) );
			
//			String sSqlBewegung = "";
			// Update auf Konto mit Prepared-Statements
			String sSqlKto = "";
			
			SqlStringExtended sqlExtBewegung = null;
			SqlStringExtended sqlExtKonto = null;
			
			
			
			
			
			BigDecimal dNewWEMenge = BigDecimal.ZERO;
			BigDecimal dNewWAMenge = BigDecimal.ZERO;
			
			if (vLagerAusgang.size() == 0 || vLagerEingang.size()== 0){
				continue;
			}
			
			// jetzt alle Warenausgänge durchlaufen, und versuchen Setzkasten-Einträge zu schreiben
			for(LagerDaten oAusgang: vLagerAusgang){
				
				BigDecimal dAusgangOffen = oAusgang.getOffeneMenge();
				dNewWAMenge = dAusgangOffen;
				
				// wenn nichts offen ist, weiter
				if (dAusgangOffen.compareTo(BigDecimal.ZERO) == 0){
					continue;
				}
				
				if (vLagerEingang.size() == 0){
					break;
				}
				
				// jetzt über alle gelesenen Lagereingänge gehen und den Lagerausgang mit Einträgen "füllen"
				for (LagerDaten oEingang: vLagerEingang){
					// die verbleibende offene Menge lesen
					BigDecimal dEingangOffen = oEingang.getOffeneMenge();
					dNewWEMenge = dEingangOffen;
					
					// die Ausgangsmenge anpassen, da sie unten ja geändert werden kann
					dAusgangOffen = oAusgang.getOffeneMenge();
					dNewWAMenge = dAusgangOffen;
					
					// wenn nichts offen ist, weiter
					if (dEingangOffen.compareTo(BigDecimal.ZERO) == 0){
						continue;
					}
					
					// prüfen, wer in wen passt 
					int comp = dEingangOffen.compareTo(dAusgangOffen) ;
					if (comp < 0 ){
						// WE < WA
						sqlExtBewegung = SQLInsertNewLagerBewegung(	oAusgang.getId_lager_konto(), 
								oEingang.getId_lager_konto(), 
								dEingangOffen, 
								oEingang.getPreis(), 
								oAusgang.getPreis(), 
								null);
						// Update auf den WE-Satz, er wird als Komplett gekennzeichnet!
						sSqlKto = "UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO " +
						" SET JT_LAGER_KONTO.IST_KOMPLETT = 'Y' " +
						" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO  = ? " ;//+ oEingang.getId_lager_konto();
				
						sqlExtKonto = new SqlStringExtended(sSqlKto);
						sqlExtKonto.getValuesList().add(new Param_Long(Long.parseLong(oEingang.getId_lager_konto())));
						
						dNewWEMenge = BigDecimal.ZERO;
						dNewWAMenge = dAusgangOffen.subtract(dEingangOffen);
						
					} else if (comp > 0){
						// WE > WA
						sqlExtBewegung = SQLInsertNewLagerBewegung(	oAusgang.getId_lager_konto(), 
								oEingang.getId_lager_konto(), 
								dAusgangOffen.abs(), 
								oEingang.getPreis(), 
								oAusgang.getPreis(), 
						"Y");
						
						// Update auf den WE-Satz, er wird als Komplett gekennzeichnet!
						sSqlKto = "UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO " +
						" SET JT_LAGER_KONTO.IST_KOMPLETT = 'Y' " +
						" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO  = ?"; // + oAusgang.getId_lager_konto();
						
						sqlExtKonto = new SqlStringExtended(sSqlKto);
						sqlExtKonto.getValuesList().add(new Param_Long(Long.parseLong(oAusgang.getId_lager_konto())));
						
						
						dNewWEMenge = dEingangOffen.subtract(dAusgangOffen);
						dNewWAMenge = BigDecimal.ZERO;
						
						
					} else {
						// WE == WA 
						sqlExtBewegung = SQLInsertNewLagerBewegung(	oAusgang.getId_lager_konto(), 
								oEingang.getId_lager_konto(), 
								dAusgangOffen, 
								oEingang.getPreis(), 
								oAusgang.getPreis(), 
						"Y");
						
						// Update auf den WA und WE-Satz, er wird als Komplett gekennzeichnet!
						sSqlKto = "UPDATE " + bibE2.cTO()+".JT_LAGER_KONTO " +
						" SET JT_LAGER_KONTO.IST_KOMPLETT = 'Y' " +
						" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO  = ? " + //oEingang.getId_lager_konto() +
						" OR JT_LAGER_KONTO.ID_LAGER_KONTO = ?" ; //+ oAusgang.getId_lager_konto();
						
						sqlExtKonto = new SqlStringExtended(sSqlKto);
						sqlExtKonto.getValuesList().add(new Param_Long(Long.parseLong(oEingang.getId_lager_konto())));
						sqlExtKonto.getValuesList().add(new Param_Long(Long.parseLong(oAusgang.getId_lager_konto())));
								
						dNewWAMenge = BigDecimal.ZERO;
						dNewWEMenge = BigDecimal.ZERO;
					}
					
//					Vector<String> sSQLVector = new Vector<String>();
//					sSQLVector.add(sSqlBewegung);
//					sSQLVector.add(sSqlKto);
//					boolean bRet =  bibDB.ExecSQL(sSQLVector, true);
//					if (bRet){
//						oEingang.setOffeneMenge(dNewWEMenge);
//						oAusgang.setOffeneMenge(dNewWAMenge);
//					}
					
					if (bibDB.ExecSQL(sqlExtBewegung, false) ){
						if (bibDB.ExecSQL(sqlExtKonto, true)){

							oEingang.setOffeneMenge(dNewWEMenge);
							oAusgang.setOffeneMenge(dNewWAMenge);
							
						} else {
							bibDB.Rollback();
						}
					} else {
						bibDB.Rollback();
					}
					
					
					
					
					// wenn der Warenausgang auf die Eingänge verteilt ist..
					if (oAusgang.getOffeneMenge().compareTo(BigDecimal.ZERO)==0){
						break;  // ..weitermachen beim nächste Warenausgang!!
					}
					
					
					//loop-kontrolle von aussen
					if (this.colLoopInfo != null)
					{
						this.colLoopInfo.removeAll();
						this.colLoopInfo.add(new MyE2_Label(new MyE2_String("Lager verbuchen .... "+(this.iCount++))));
					}
					
					
				}
				
				
				// jetzt die verbuchten Eingangsposten aus dem Vector rausschmeissen
				int size = vLagerEingang.size();
				for (int i = size -1; i >= 0; i--){
					LagerDaten oEin = vLagerEingang.get(i);
					// wenn nichts offen ist, weiter
					if (oEin.getOffeneMenge().compareTo(BigDecimal.ZERO) == 0){
						vLagerEingang.remove(i);
					}
				}
				
				
				
			}
		}
		
		return ;
		
		
	}
	
	
	/**
	 * Ermitteln der Lagerbestände, die noch nicht verteilt sind!
	 * Author: manfred
	 * 01.04.2009
	 *
	 * @param idMandant
	 * @param idLager
	 * @param idArtikel
	 * @param bt EnumBuchungstyp.WA/WE
	 * @return
	 */
	private Vector<LagerDaten> FillLagerDaten(	String idMandant, 
												String idLager, 
												String idArtikel,
												EnumBuchungstyp bt,
												String sDatumBis){
		
		Vector<LagerDaten> vReturn = new Vector<LagerDaten>();
		
		String [][] cLagerDaten = new String[0][0];
		String sSql = "";
		SqlStringExtended sqlExt = null;
		
		String sBuchungsTyp = bt.toString().toUpperCase(); 
		
		
		if (bt == EnumBuchungstyp.WA){
			sSql = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO, " +
					" max(JT_LAGER_KONTO.PREIS) ," + 
					" max(JT_LAGER_KONTO.MENGE), " +
					" max(JT_LAGER_KONTO.BUCHUNGSDATUM), " +
					" nvl( SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0  )),0.0) as verbucht " +
					" FROM "+ bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
					" RIGHT OUTER JOIN "+ bibE2.cTO()+".JT_LAGER_KONTO on JT_LAGER_BEWEGUNG.ID_MANDANT = JT_LAGER_KONTO.ID_MANDANT " +
					" and JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_AUSGANG = JT_LAGER_KONTO.ID_LAGER_KONTO " +
					" WHERE JT_LAGER_KONTO.ID_MANDANT = ? " + //idMandant +  
					" AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = ? " + //idArtikel + 
					" and JT_LAGER_KONTO.ID_ADRESSE_LAGER = ? " + //idLager +
					" AND JT_LAGER_KONTO.BUCHUNGSTYP = ? " +  //'WA' " +
					" AND NVL(JT_LAGER_KONTO.STORNO, 'N') = 'N' " +
					" AND NVL(JT_LAGER_KONTO.IST_KOMPLETT, 'N') = 'N' " +
					" AND  to_char(JT_LAGER_KONTO.BUCHUNGSDATUM, 'YYYY-MM-DD') || ' ' || JT_LAGER_KONTO.BUCHUNGSZEIT  <= ? " + //'" + sDatumBis + "' " +
					" GROUP BY JT_LAGER_KONTO.ID_LAGER_KONTO " +
					" ORDER BY 4,2  " 
					;
			
			sqlExt = new SqlStringExtended(sSql);
			sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idMandant)));
			sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idArtikel)));
			sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idLager)));
			sqlExt.getValuesList().add(new Param_String("",sBuchungsTyp));
			sqlExt.getValuesList().add(new Param_String("",sDatumBis));
			

			
			
		}else{
				sSql = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO, " +
					" max(JT_LAGER_KONTO.PREIS) ," + 
					" max(JT_LAGER_KONTO.MENGE), " +
					" max(JT_LAGER_KONTO.BUCHUNGSDATUM), " +
					" nvl( SUM(nvl2(JT_LAGER_BEWEGUNG.MENGE,JT_LAGER_BEWEGUNG.MENGE,0  )),0.0) as verbucht " +
					" FROM "+ bibE2.cTO()+".JT_LAGER_BEWEGUNG " +
					" RIGHT OUTER JOIN "+ bibE2.cTO()+".JT_LAGER_KONTO on JT_LAGER_BEWEGUNG.ID_MANDANT = JT_LAGER_KONTO.ID_MANDANT " +
					" and JT_LAGER_BEWEGUNG.ID_LAGER_KONTO_EINGANG = JT_LAGER_KONTO.ID_LAGER_KONTO " +
					" WHERE JT_LAGER_KONTO.ID_MANDANT = ? " + //idMandant +  
					" AND JT_LAGER_KONTO.ID_ARTIKEL_SORTE = ?" + //idArtikel + 
					" and JT_LAGER_KONTO.ID_ADRESSE_LAGER = ?" + //idLager +
					" AND JT_LAGER_KONTO.BUCHUNGSTYP = ? " + //'WE' " +
					" AND NVL(JT_LAGER_KONTO.STORNO ,'N') = 'N' " +
					" AND NVL(JT_LAGER_KONTO.IST_KOMPLETT,'N')= 'N' " +
					" AND  to_char(JT_LAGER_KONTO.BUCHUNGSDATUM, 'YYYY-MM-DD') || ' ' || JT_LAGER_KONTO.BUCHUNGSZEIT  <= ? " + //'" + sDatumBis + "' " +
					" GROUP BY JT_LAGER_KONTO.ID_LAGER_KONTO " +
					" ORDER BY 2,4 " 
					;
				
				sqlExt = new SqlStringExtended(sSql);
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idMandant)));
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idArtikel)));
				sqlExt.getValuesList().add(new Param_Long(Long.parseLong(idLager)));
				sqlExt.getValuesList().add(new Param_String("",sBuchungsTyp));
				sqlExt.getValuesList().add(new Param_String("",sDatumBis));

		}

		
		cLagerDaten =  bibDB.EinzelAbfrageInArray(sqlExt,(String)null);
		
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cLagerDaten == null || cLagerDaten.length == 0){
			return vReturn;
		}
		
		// sonst den Vektor füllen
		for (int i = 0; i < cLagerDaten.length; i++){
			String 		Lager = 			cLagerDaten[i][0];
			String		sPreis = cLagerDaten[i][1];
			BigDecimal 	dPreis = 			sPreis != null ? new BigDecimal(cLagerDaten[i][1]) : null ;
			BigDecimal 	dMenge = 			new BigDecimal(cLagerDaten[i][2]) ;
			String 		Buchungsdatum = 	cLagerDaten[i][3];
			BigDecimal 	dVerbuchteMenge = 	new BigDecimal(cLagerDaten[i][4]) ;
			BigDecimal 	dOffeneMenge  = 	new BigDecimal(0);
			
			// Warenausgänge sind im Konto negativ, aber die Bewegungsliste hat immer positive Werte
			if (bt == EnumBuchungstyp.WA){
				dOffeneMenge =  dMenge.add(dVerbuchteMenge).abs();
			}else{
				dOffeneMenge = dMenge.subtract(dVerbuchteMenge).abs();
			}
				
			LagerDaten l = new LagerDaten(	Lager,
											dPreis,
											dMenge,
											Buchungsdatum,
											dVerbuchteMenge,
											dOffeneMenge);
			// an den Vector dranhängen!
			vReturn.add(l);
			
		}
		return vReturn;
		
	}
	
	
	/**
	 * SQLString zur erzeugung eines neuen Lager-Bewegungssatz
	 * @author manfred
	 * @date 12.03.2009
	 * 
	 * @param id_lager_kto_ausgang
	 * @param id_lager_kto_eingang
	 * @param menge
	 * @param preis_eingang
	 * @param preis_ausgang
	 * @param komplett
	 * @return
	 * @throws myException 
	 */
	private String SQLInsertNewLagerBewegung_old(	String id_lager_kto_ausgang, 
												String id_lager_kto_eingang,
												BigDecimal menge,
												BigDecimal preis_eingang,
												BigDecimal preis_ausgang,
												String komplett) throws myException{
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

		oSql.addSQL_Paar("ID_LAGER_BEWEGUNG", "SEQ_LAGER_BEWEGUNG.NEXTVAL", false);

	    oSql.addSQL_Paar("ID_LAGER_KONTO_EINGANG", id_lager_kto_eingang, false);
	    oSql.addSQL_Paar("ID_LAGER_KONTO_AUSGANG", id_lager_kto_ausgang, false);
	    oSql.addSQL_Paar("MENGE", menge == null ? null : menge.toPlainString() , false);
	    oSql.addSQL_Paar("PREIS_EINGANG", preis_eingang == null ? null : preis_eingang.toPlainString() , false);
	    oSql.addSQL_Paar("PREIS_AUSGANG",preis_ausgang == null ? null : preis_ausgang.toPlainString() , false);
	    oSql.addSQL_Paar("KOMPLETT", komplett , true);

	    oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
	    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
	    
	    String sSql = oSql.get_CompleteInsertString("JT_LAGER_BEWEGUNG", bibE2.cTO());

		return sSql;
		
	}

	
	
	/**
	 * SQLString zur erzeugung eines neuen Lager-Bewegungssatz
	 * @author manfred
	 * @date 12.03.2009
	 * 
	 * @param id_lager_kto_ausgang
	 * @param id_lager_kto_eingang
	 * @param menge
	 * @param preis_eingang
	 * @param preis_ausgang
	 * @param komplett
	 * @return
	 * @throws myException 
	 */
	private SqlStringExtended SQLInsertNewLagerBewegung(	String id_lager_kto_ausgang, 
												String id_lager_kto_eingang,
												BigDecimal menge,
												BigDecimal preis_eingang,
												BigDecimal preis_ausgang,
												String komplett) throws myException{
//		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
//
//		oSql.addSQL_Paar("ID_LAGER_BEWEGUNG", "SEQ_LAGER_BEWEGUNG.NEXTVAL", false);

//	    oSql.addSQL_Paar("ID_LAGER_KONTO_EINGANG", id_lager_kto_eingang, false);
//	    oSql.addSQL_Paar("ID_LAGER_KONTO_AUSGANG", id_lager_kto_ausgang, false);
//	    oSql.addSQL_Paar("MENGE", menge == null ? null : menge.toPlainString() , false);
//	    oSql.addSQL_Paar("PREIS_EINGANG", preis_eingang == null ? null : preis_eingang.toPlainString() , false);
//	    oSql.addSQL_Paar("PREIS_AUSGANG",preis_ausgang == null ? null : preis_ausgang.toPlainString() , false);
//	    oSql.addSQL_Paar("KOMPLETT", komplett , true);
//
//	    oSql.addSQL_Paar("ERZEUGT_VON", bibALL.get_KUERZEL(), true);
//	    oSql.addSQL_Paar("ERZEUGT_AM", "SYSDATE", false);
//	    
//	    String sSql = oSql.get_CompleteInsertString("JT_LAGER_BEWEGUNG", bibE2.cTO());
//
//		return sSql;
		
		
		Rec21 rec = new Rec21(_TAB.lager_bewegung);
		rec._setNewValueInDatabaseTerminus(LAGER_BEWEGUNG.id_lager_bewegung.fn(), "SEQ_LAGER_BEWEGUNG.NEXTVAL");
		rec.set_NewValueForDatabase(LAGER_BEWEGUNG.id_lager_konto_eingang.fn(), id_lager_kto_eingang);
		rec.set_NewValueForDatabase(LAGER_BEWEGUNG.id_lager_konto_ausgang.fn(), id_lager_kto_ausgang);
		rec.set_NewValueForDatabase(LAGER_BEWEGUNG.menge.fn(), menge);
		rec.set_NewValueForDatabase(LAGER_BEWEGUNG.preis_eingang.fn(), preis_eingang);
		rec.set_NewValueForDatabase(LAGER_BEWEGUNG.preis_ausgang.fn(), preis_ausgang);
		
		rec.set_NewValueForDatabase(LAGER_BEWEGUNG.komplett.fn(), komplett);
		rec._setNewValueInDatabaseTerminus(LAGER_BEWEGUNG.erzeugt_am.fn(), "SYSDATE");
		rec.set_NewValueForDatabase(LAGER_BEWEGUNG.erzeugt_von.fn(), bibALL.get_KUERZEL());
		
		return rec.createSaveString(bibALL.get_myDBToolBox());
	}
	
	/**
	 * 
	 * @author manfred
	 *
	 */
	private enum EnumBuchungstyp {
		WE,
		WA
	}
	
	

	
	
	

}
