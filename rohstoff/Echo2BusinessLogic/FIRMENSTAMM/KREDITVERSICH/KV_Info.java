package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * gibt für einen  Kunden die aktiven Kreditlimits zurück
 * @author manfred
 * @date   26.10.2011
 */
public class KV_Info {
	
	private Vector<KV_Info_Entry> m_Kreditlimits = null;

	public KV_Info() {
		m_Kreditlimits = new Vector<KV_Info_Entry>();
	}
	
	
	public Vector<KV_Info_Entry> getKreditlimitsFor(String sIDAdresse){
	    m_Kreditlimits.clear();
	    
		String sSql = 
			      " SELECT to_char(P.GUELTIG_AB,'yyyy-MM-dd'), to_char(P.GUELTIG_BIS,'yyyy-MM-dd'), P.BETRAG, P.BETRAG_ANFRAGE, K.ID_KREDITVERS_KOPF "
				+ " FROM " + bibE2.cTO()  + ".JT_KREDITVERS_POS P "
				+ " INNER JOIN " + bibE2.cTO()  + ".JT_KREDITVERS_KOPF K "
				+ "    ON P.ID_MANDANT = K.ID_MANDANT "
				+ "    AND P.ID_KREDITVERS_KOPF = K.ID_KREDITVERS_KOPF "
				+ " INNER JOIN " + bibE2.cTO()  + ".JT_KREDITVERS_ADRESSE A "
				+ "    ON A.ID_KREDITVERS_KOPF = K.ID_KREDITVERS_KOPF "
				+ "    AND A.ID_MANDANT = K.ID_MANDANT "
				+ " WHERE P.ID_MANDANT =  " + bibALL.get_ID_MANDANT()  
				+ " AND nvl(K.AKTIV,'N') = 'Y' "
				+ " AND nvl(P.AKTIV,'N') = 'Y' " 
				+ "	AND A.ID_ADRESSE =  " + sIDAdresse 
				+ "	ORDER BY 1 desc , 2, 3 ";
		
		String[][] cKVEntries = bibDB.EinzelAbfrageInArray(sSql, "");
		
		if (cKVEntries != null){
			
			for (int i= 0; i < cKVEntries.length; i++){
				
				String sAb = cKVEntries[i][0];
				String sBis = cKVEntries[i][1];
				String sBetrag = cKVEntries[i][2];
				String sAnfrage = cKVEntries[i][3];
				String sIDKVKopf = cKVEntries[i][4];
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date dtAb  = null;
				Date dtBis = null;
				BigDecimal bdBetrag = null;
				BigDecimal bdAnfrage = null;
				
				try {
					dtAb = df.parse(sAb);
				} catch (ParseException e) {}
				
				try {
					dtBis = df.parse(sBis);
				} catch (ParseException e) {}
				
				bdBetrag = bibALL.convertDBTextToBigDecimal(sBetrag);
				bdAnfrage = bibALL.convertDBTextToBigDecimal(sAnfrage);
				
				KV_Info_Entry oEntry = new KV_Info_Entry(bdBetrag, bdAnfrage, dtAb, dtBis, "",sIDKVKopf);
			
				m_Kreditlimits.add(oEntry);

			}
		}
		
		// internes Kreditlimit noch ermitteln:
		
		try {
			RECORD_FIRMENINFO rFI = new RECORD_FIRMENINFO("ID_ADRESSE = " + sIDAdresse);
			if (rFI != null){
				BigDecimal bdKB = rFI.get_KREDITBETRAG_INTERN_bdValue(null) ;

				if (bdKB != null && bdKB.compareTo(BigDecimal.ZERO) != 0 ){
					Date       dtKB = null;
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String 	   sdt  = rFI.get_KREDITBETRAG_INTERN_VALID_TO_cUF_NN("");
					try {
						dtKB = df.parse(sdt);
					} catch (ParseException e) {}
					
					KV_Info_Entry oEntry = new KV_Info_Entry(bdKB, null , null , dtKB, "Interner Firmenkredit",null);
					m_Kreditlimits.add(oEntry);
				}
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
		
		return m_Kreditlimits;
	}
	
	
	
	/**
	 * ermittel alle AdressIDs die zu einer Kreditversicherung zugeordnet ist
	 * @author manfred
	 * @date 21.08.2018
	 *
	 * @param idAdresse
	 * @return
	 */
	public Vector<String> getAllAddressIDsConnected(String idKreditVersKopf){
		Vector<String> vIdAdressen = new Vector<>();
		
		String sql = "SELECT id_adresse FROM JT_KREDITVERS_ADRESSE WHERE ID_KREDITVERS_KOPF = " + idKreditVersKopf ;
		
		
		String[][] sArrDaten =  bibDB.EinzelAbfrageInArray(sql,(String)null);
		for (int i= 0; i < sArrDaten.length; i++){
			vIdAdressen.add(sArrDaten[i][0]);
		}
		
		return vIdAdressen;
	}
	

}
