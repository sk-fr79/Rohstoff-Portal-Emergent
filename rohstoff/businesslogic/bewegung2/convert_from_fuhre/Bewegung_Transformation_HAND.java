package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

//import com.sun.glass.events.GestureEvent;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_KONTO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_Lager_Konto;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;




public class Bewegung_Transformation_HAND {
	
	Vector<String> m_vLagerOrte = null;
	
	GROESSEN_Umrechnung m_oUmrechnung = null;
	
	HashMap<String, RECORD_ARTIKEL_BEZ> m_hmArtikelBez_zu_Artikel = null;
	HashMap<String, RECORD_ARTIKEL> m_hmArtikel = null;
	RecList20				m_rlArtikel = null;
	
	conv_helper m_helper;
	
	Long m_IDAdresseMandant = null;


	EnTransportTyp m_transportTyp = null;
	
	/**
	 * Default-Konstruktor
	 * @author manfred
	 * @throws myException 
	 * @date   08.08.2012
	 */
	public Bewegung_Transformation_HAND(conv_helper helper) throws myException {
		super();
		m_helper = helper;

		
		// die eigene AdressID ermitteln
		m_IDAdresseMandant = Long.parseLong( bibALL.get_ID_ADRESS_MANDANT() );
	}



	

	/**
	 * Vektor für eine Lagerkorrekturbuchung mit einer Lagerkonto-Buchunng
	 * 
	 * @author manfred
	 * @date 08.11.2019
	 *
	 * @param oLager
	 * @param _transport_typ
	 * @return
	 * @throws myException
	 */
	protected jt_bg_vektor generiere_Vektor_Korrekturbuchung(	Rec21_Lager_Konto oLager/* , EnTransportTyp _transport_typ */) throws myException{
		
		Long posnrInVektor = 1L;
		if (oLager.get_ufs_dbVal(LAGER_KONTO.buchungstyp).equals("WE")) {
			m_transportTyp = EnTransportTyp.EINBUCHUNG;
		} else {
			m_transportTyp = EnTransportTyp.AUSBUCHUNG;
		}
		

		// Buchung_hand darf nur "K" sein:
//		String buchung_hand = oLager.get_ufs_dbVal(LAGER_KONTO.buchung_hand, "*");
//		if (!buchung_hand.equalsIgnoreCase("K")) {
//			throw new myException("Buchung muss Korrekturbuchung sein!!");
//		}
				
		

		//
		// Vektor erzeugen und mit den Basisdaten initialisieren
		//
		//
		jt_bg_vektor oVek = new jt_bg_vektor(oLager,m_helper);

		oVek.setEN_TRANSPORT_TYP(m_transportTyp);
		
		// vektor-Fuhre verbindung erzeugen
		jt_bg_vektor_konvert oKonvert = new jt_bg_vektor_konvert(oLager,m_helper).setBG_VEKTOR(oVek);
		oVek.set_jt_bg_vektor_konvert(oKonvert);
		
		EN_VEKTOR_STATUS enStatus = EN_VEKTOR_STATUS.FINAL;
		oVek.setEN_VEKTOR_STATUS(enStatus);
		
		// ATOME
		jt_bg_atom atom1 = new jt_bg_atom(this, oVek, oLager, EnTabKeyInMask.A1, m_helper);
		jt_bg_atom atom2 = new jt_bg_atom(this, oVek, oLager, EnTabKeyInMask.A2, m_helper);
		
		// planmengen Anpassen
		oVek.setPLANMENGE(BigDecimal.ZERO);
		
		// Stationen
		// Start-Station
		jt_bg_station station_start = new jt_bg_station(this,oLager,m_helper); 
		station_start.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		atom1.set_jt_bg_station_quelle(station_start);
		
				
		// Ziel-Station 
		jt_bg_station station_ziel = new jt_bg_station(this, oLager, m_helper);
		station_ziel.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		atom2.set_jt_bg_station_ziel(station_ziel);
		
		// Zwischenstation 
		jt_bg_station station_zw = new jt_bg_station(m_helper);
		
		station_zw.setID_BG_STATION(BG_STATION._tab().seq_nextval());
		station_zw.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		station_zw.setID_MANDANT(station_start.getID_MANDANT().lValue);
		station_zw.setERZEUGT_AM(station_start.getERZEUGT_AM().dValue);
		station_zw.setERZEUGT_VON(station_start.getERZEUGT_VON().sValue);
		station_zw.setGEAENDERT_VON(station_start.getGEAENDERT_VON().sValue);
		station_zw.setLETZTE_AENDERUNG(station_start.getLETZTE_AENDERUNG().dValue);
		
		
		station_zw.setID_ADRESSE_BASIS(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
		// Adresse der Zwischenstation setzen
		station_zw.setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_KORREKTUR_HAND_longValue());
		station_zw.setID_ADRESSE_BESITZER_LDG(station_start.getID_ADRESSE_BESITZER_LDG().lValue);
		
		station_zw.setID_LAND(station_start.getID_LAND().lValue);
		
		station_zw.setPLZ("-");
		station_zw.setNAME1(m_transportTyp.name());
		
		atom1.set_jt_bg_station_ziel(station_zw);
		atom2.set_jt_bg_station_quelle(station_zw);
				
		//
		station_start.setPOS_IN_MAKS(EnTabKeyInMask.S1.dbVal());
		station_zw.setPOS_IN_MAKS(EnTabKeyInMask.S2.dbVal());
		station_ziel.setPOS_IN_MAKS(EnTabKeyInMask.S3.dbVal());
		
		return oVek;
		
	}

	
	
	/**
	 * Vektor für eine Lagerumbuchung durch 2 Lagerkonto-Einträge
	 * @author manfred
	 * @date 08.11.2019
	 *
	 * @param oLager1
	 * @param oLager2
	 * @return
	 * @throws myException
	 */
	protected jt_bg_vektor generiere_Vektor_Umbuchung (Rec21_Lager_Konto oLager1, Rec21_Lager_Konto oLager2) throws myException{
		Long posnrInVektor = 1L;
		m_transportTyp = EnTransportTyp.UMBUCHUNG;
		
		String buchung_hand = oLager1.get_ufs_dbVal(LAGER_KONTO.buchung_hand, "*");
		if (!buchung_hand.equalsIgnoreCase("U")) {
			throw new myException("Buchung muss Korrekturbuchung sein!!");
		}
		
		
		//
		// Vektor erzeugen und mit den Basisdaten initialisieren
		//
		//
		jt_bg_vektor oVek = new jt_bg_vektor(oLager1,m_helper);
		
		// vektor-Fuhre verbindung erzeugen
		jt_bg_vektor_konvert oKonvert = new jt_bg_vektor_konvert(oLager1,m_helper).setBG_VEKTOR(oVek);
		oVek.set_jt_bg_vektor_konvert(oKonvert);

		jt_bg_vektor_konvert oKonvert2 = new jt_bg_vektor_konvert(oLager2,m_helper).setBG_VEKTOR(oVek);
		oVek.set_jt_bg_vektor_konvert2(oKonvert2);
		
		oVek.setEN_TRANSPORT_TYP(m_transportTyp);
		oVek.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.FINAL);
		
		
		// ATOME
		jt_bg_atom atom1 = new jt_bg_atom(this, oVek, oLager1, EnTabKeyInMask.A1, m_helper);
		jt_bg_atom atom2 = new jt_bg_atom(this, oVek, oLager2, EnTabKeyInMask.A2, m_helper);
		
		// planmengen Anpassen
		oVek.setPLANMENGE(BigDecimal.ZERO);
		
		// Stationen
		// Start-Station
		jt_bg_station station_start = new jt_bg_station(this,oLager1,m_helper); 
		station_start.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		atom1.set_jt_bg_station_quelle(station_start);
		
				
		// Ziel-Station 
		jt_bg_station station_ziel = new jt_bg_station(this, oLager2, m_helper);
		station_ziel.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		atom2.set_jt_bg_station_ziel(station_ziel);
		
		// Zwischenstation 
		jt_bg_station station_zw = new jt_bg_station(m_helper);
		
		station_zw.setID_BG_STATION(BG_STATION._tab().seq_nextval());
		station_zw.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		station_zw.setID_MANDANT(station_start.getID_MANDANT().lValue);
		station_zw.setERZEUGT_AM(station_start.getERZEUGT_AM().dValue);
		station_zw.setERZEUGT_VON(station_start.getERZEUGT_VON().sValue);
		station_zw.setGEAENDERT_VON(station_start.getGEAENDERT_VON().sValue);
		station_zw.setLETZTE_AENDERUNG(station_start.getLETZTE_AENDERUNG().dValue);
		station_zw.setID_LAND(station_start.getID_LAND().lValue);
		
		station_zw.setID_ADRESSE_BASIS(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
		station_zw.setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_UMBUCHUNG_HAND_longValue());
		station_zw.setID_ADRESSE_BESITZER_LDG(station_start.getID_ADRESSE_BESITZER_LDG().lValue);

		station_zw.setPLZ("-");
		station_zw.setNAME1(m_transportTyp.name());
		
		atom1.set_jt_bg_station_ziel(station_zw);
		atom2.set_jt_bg_station_quelle(station_zw);
				
		//
		station_start.setPOS_IN_MAKS(EnTabKeyInMask.S1.dbVal());
		station_zw.setPOS_IN_MAKS(EnTabKeyInMask.S2.dbVal());
		station_ziel.setPOS_IN_MAKS(EnTabKeyInMask.S3.dbVal());
			
			
		return oVek;

	}

	

	
	/**
	 * Ermittelt die ID-Adresse des Besitzer eines Lagers
	 * @param sIDAdresseLager
	 * @return
	 */
	protected Long getIdAdresseBesitzer(String sIDAdresseLager ){

		String sSQL = " SELECT DISTINCT NVL(V.ID_ADRESSE_FREMDWARE,V.ID_ADRESSE_BASIS) FROM " + bibE2.cTO() +".V_FIRMENADRESSEN_FLACH V "
					+ " WHERE V.ID_MANDANT = " + bibALL.get_ID_MANDANT()
					+ " AND V.ID_ADRESSE_LIEFER = " + sIDAdresseLager ;
				
		Long lIDRet = null;
		
		String sIDRet = bibDB.EinzelAbfrage(sSQL);
		if ( !bibALL.isEmpty(sIDRet) ){
			try {
				lIDRet = Long.parseLong(sIDRet);
			} catch (Exception e) {
				lIDRet = null;
			}
		}
		return lIDRet;
	}
	

	
	
	/**
	 * Ermittelt die Land-ID vom gegebenen Laendercode
	 * @author manfred
	 *
	 * @param Laendercode
	 * @return
	 */
	protected Long getLandIDFromLaendercode(String Laendercode){
		Long lID = null;
		
		if (Laendercode != null && m_helper.m_hmLaenderCode.containsKey(Laendercode.trim())){
			lID = m_helper.m_hmLaenderCode.get(Laendercode.trim());
		} else {
			// default-Laendercode D wählen
			lID = m_helper.m_hmLaenderCode.get("D");
		}
		
		return lID;
	}
	
	
	
	/**
	 * setzt anr1, artbez1 und artbez2 in der ladung anhand der artikel-ID
	 * @author manfred
	 * @date 15.02.2018
	 *
	 * @param ladung
	 */
//	protected void setArtikelBezeichnungen(jt_bg_ladung ladung){
//		Rec20 art = null;
//		if (ladung.getID_ARTIKEL().Value() != null){
//			if (m_rlArtikel.containsKey(ladung.getID_ARTIKEL().Value())){
//				try {
//					art = m_rlArtikel.get(ladung.getID_ARTIKEL().Value());
//					ladung.setANR1(art.get_ufs_dbVal(ARTIKEL.anr1));
//					ladung.setARTBEZ1(art.get_ufs_dbVal(ARTIKEL.artbez1));
//					ladung.setARTBEZ2(art.get_ufs_dbVal(ARTIKEL.artbez2));
//				} catch (myException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	/**
	 * ermittelt die Artikelid aus der Artikelbez-ID unter zuhilfenahme des internen Caches
	 * @author manfred
	 * @date   21.08.2012
	 * @param sIDArtikelBez
	 * @return
	 * @throws myException 
	 */
//	protected Long getArtikelBezID_For_ArtikelID(String sIDArtikel) {
//		Long lIDArtikelBez = null;
//		RECORD_ARTIKEL_BEZ oRecArtbez = null;
//		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
//		
//		
//		if (m_hmArtikelBez_zu_Artikel.containsKey(sIDArtikel)){
//			oRecArtbez = m_hmArtikelBez_zu_Artikel.get(sIDArtikel);
//			try {
//				lIDArtikelBez = oRecArtbez.get_ID_ARTIKEL_BEZ_lValue(null);
//			} catch (myException e) {
//				e.printStackTrace();
//			}
//		} else {
//			RECLIST_ARTIKEL_BEZ oArtBez = null;
//			try {
//				oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
//				RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
//				lIDArtikelBez = o.get_ID_ARTIKEL_BEZ_lValue(null);
//				m_hmArtikelBez_zu_Artikel.put(sIDArtikel, o);
//			} catch (myException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return lIDArtikelBez;
//	}

	
	/**
	 * Ermittelt die ARTBEZ1 zu einem Artikel
	 * @author manfred
	 * @date   27.09.2012
	 * @param sIDArtikel
	 * @return
	 */
//	protected String getARTBEZ1(String sIDArtikel){
//		String sARTBEZ1 = null;
//		Long lIDArtikelBez = null;
//		RECORD_ARTIKEL_BEZ oRecArtbez = null;
//		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
//		
//		
//		if (m_hmArtikelBez_zu_Artikel.containsKey(sIDArtikel)){
//			oRecArtbez = m_hmArtikelBez_zu_Artikel.get(sIDArtikel);
//			try {
//				sARTBEZ1 = oRecArtbez.get_ARTBEZ1_cUF();
//			} catch (myException e) {
//				e.printStackTrace();
//			}
//		} else {
//			RECLIST_ARTIKEL_BEZ oArtBez = null;
//			try {
//				oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
//				RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
//				sARTBEZ1 = oRecArtbez.get_ARTBEZ1_cUF();
//				m_hmArtikelBez_zu_Artikel.put(sIDArtikel, o);
//			} catch (myException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return sARTBEZ1;
//	}
	
	

	
	
	public Vector<String> getSQLTransformation(){
		return new Vector<String>();
	}
	
	
}
