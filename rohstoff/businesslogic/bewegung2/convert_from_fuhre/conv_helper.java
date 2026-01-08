/**
 * rohstoff.businesslogic.bewegung2.convert_from_fuhre
 * @author manfred
 * @date 09.07.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.utils.bibROHSTOFF;

/**
 * @author manfred
 * @date 09.07.2019
 *
 */
public class conv_helper {

	// die eigenen Lieferadressen
	protected Vector<String>        				m_vEigeneLieferadressen = new Vector<String>();
	
	// Cache für die Artikelzuordnung. Wird langsam gefüllt und verhindert mehrfachaufrufe des Records für den gleichen Artikel
	// Sollte DB-Zugriffe deutlich verringern
	protected HashMap<String, String> 			m_hmArtikeBezZuordnung = new HashMap<String, String>();
	protected HashMap<String, Rec21_artikel_bez> m_hmArtikeBezZuordnungObjekte = new HashMap<String, Rec21_artikel_bez>();
	
	
		
	// Cache für die Lieferbedingungen
	protected HashMap<String,String> 			m_hmLieferbedingungen = new HashMap<String, String>();
	
	// Puffer zur Umrechnung der Größen
	protected GROESSEN_Umrechnung     			m_UmrechnungDerGroessen = null;
	
	//Cache für den Ländercode
	protected HashMap<String, Long>             m_hmLaenderCode = new HashMap<>();

	// Cache für die Zolltarifnummern
	protected HashMap<String,Long> 				m_hmZolltarifnummern = new HashMap<>();

	// cache für die User
	protected HashMap<String,Long>				m_hmUser = new HashMap<>();
	
	// leergutartikel
	protected Vector<String>   					m_vLeergut_Artikel = new Vector<String>();
	
	protected RecList21 						m_rlArtikel = null;
	
	protected RecList21							m_rlWaehrung = null;
	protected Long								m_idWaehrungStd= null;
	
	
	MyDBToolBox									m_MyDBToolbox = null;
	/**
	 * @author manfred
	 * @throws myException 
	 * @date 17.09.2019
	 *
	 */
	public conv_helper() throws myException {
		
		// toolbox für die Conversion aufbauen, ohne Standard-Zusatzfelder da alles von den alten Sätzen kommt
		m_MyDBToolbox = bibALL.get_myDBToolBox();

		// eigene Lieferadressen puffern, da diese immer frisch gelesen werden.
		try {
			
			this.m_vEigeneLieferadressen.addAll( bibROHSTOFF.get_vEigeneLieferadressen() );
		} catch (myException e) {
			throw new myException("Bewegung_Conversion_Handler::eigene Lieferadressen können nicht ermittelt werden.");
		}
		
		// Objekt zur Umrechnung der Größen initialisieren
		try {
			this.m_UmrechnungDerGroessen = new GROESSEN_Umrechnung();
		} catch (myException e) {
			throw new myException("Bewegung_Conversion_Handler::Umrechnungsfaktoren können nicht ermittelt werden.");
		}
		
		// Leergutartikel ermitteln und in den Vektor schreiben
		try {
			SEL sel = new SEL("*").FROM(_TAB.artikel).WHERE(new vgl(ARTIKEL.ist_leergut,COMP.EQ, "Y"));
			RecList21 rlArtikel = new RecList21(_TAB.artikel)._fill(sel.s());
			for (Rec21 r: rlArtikel){
				this.m_vLeergut_Artikel.add(r.get_ufs_dbVal(ARTIKEL.id_artikel));
			}
		} catch (myException e) {
			throw new myException("Bewegung_Conversion_Handler::Leergutartikel können nicht ermittelt werden.",e);
		}

		
		//
		// User
		//
		try {
			SEL sel = new SEL("*").FROM(_TAB.user);
			RecList21 rl = new RecList21(_TAB.user)._fill(sel.s());
			for (Rec21 o: rl){
				this.m_hmUser.put(o.getUfs(USER.kuerzel), o.get_raw_resultValue_Long(USER.id_user));
			}
		} catch (Exception e) {
			throw new myException("Bewegung_Conversion_Handler::User können nicht geladen werden.");
		}
		
		//
		// Währung
		//
		try {
			SEL sel = new SEL("*").FROM(_TAB.waehrung);
			this.m_rlWaehrung = new RecList21(_TAB.waehrung)._fill(sel.s());
			for (Rec21 o: this.m_rlWaehrung){
				if (o.getUfs(WAEHRUNG.ist_standard,"N").equals("Y")){
					this.m_idWaehrungStd = o.get_raw_resultValue_Long(WAEHRUNG.id_waehrung);
				}
			}
		} catch (Exception e) {
			throw new myException("Bewegung_Conversion_Handler::Währung können nicht geladen werden.");
		}
		
		//
		// Alle Artikel
		//
		try {
			SEL sel = new SEL("*").FROM(_TAB.artikel);
			this.m_rlArtikel = new RecList21(_TAB.artikel)._fill(sel.s());
		} catch (Exception e) {
			throw new myException("Bewegung_Conversion_Handler::Artikel können nicht geladen werden.");
		}
		
		// Hashmap für die Lieferbedingungen erstellen und füllen
		// alle Lieferbedingungen in UpperCase
		// Key: Bezeichnung, Value: ID_LIEFERBEDINGUNGEN
		try {
			SEL sel = new SEL("*").FROM(_TAB.lieferbedingungen);
			RecList21 rl = new RecList21(_TAB.lieferbedingungen)._fill(sel.s());
			for (Rec21 r: rl){
			
				this.m_hmLieferbedingungen.put(r.get_ufs_dbVal(LIEFERBEDINGUNGEN.bezeichnung, "").trim().toUpperCase(), r.get_ufs_dbVal(LIEFERBEDINGUNGEN.id_lieferbedingungen,"-1"));
			}
		} catch (Exception e) {
			throw new myException("Bewegung_Conversion_Handler::Lieferbedingungen können nicht ermittelt werden.");
		}
		
		
		// Ländercode cachen
		try {
			RecList21 rl = new RecList21(_TAB.land)._fill("","");
			for (Rec21 r: rl){
				this.m_hmLaenderCode.put(r.get_ufs_dbVal(LAND.laendercode), r.get_raw_resultValue_Long(LAND.id_land, -1L)  );
			}
		} catch (Exception e) {
			throw new myException("Bewegung_Conversion_Handler::Ländercodes können nicht ermittelt werden.");
		}
		
		
		// Zolltarifnummern cachen
		try {
			RecList21 rl = new RecList21(_TAB.zolltarifnummer)._fill("","");
			for (Rec21 r: rl){
				this.m_hmZolltarifnummern.put(r.get_ufs_dbVal(ZOLLTARIFNUMMER.nummer), r.get_raw_resultValue_Long(ZOLLTARIFNUMMER.id_zolltarifnummer, -1L)  );
			}
		} catch (Exception e) {
			throw new myException("Bewegung_Conversion_Handler::Zolltarifnummern können nicht ermittelt werden.");
		}
		
		
	}
	
	public void setDBToolbox(MyDBToolBox tb) {
		m_MyDBToolbox = tb;
	}
	
	public MyDBToolBox getDBToolbox() {
		return m_MyDBToolbox;
	}
	
	
	/**
	 * ermittelt die Userid vom Kuerzel,
	 * wenn keiner gefunden wird, wird versucht "batch" zu nehmen.
	 * 
	 * @author manfred
	 * @date 12.07.2019
	 *
	 * @param user
	 * @return
	 */
	public Long getIDUserFromUser(String user){
		Long idUser = null;
		if (this.m_hmUser.containsKey(user)){
			idUser = this.m_hmUser.get(user);
		} else {
			// user batch...
			if (this.m_hmUser.containsKey("batch")){
				idUser = this.m_hmUser.get("batch");
			}
		}
		return idUser;
	}
	
	
	/**
	 * ermittelt die LieferbedingungsID aus den Lieferbedingungen
	 * 
	 * @author manfred
	 * @date 01.10.2019
	 *
	 * @param sLieferbedingung
	 * @return
	 */
	public Long getIDLieferbedingungenFrom(String sLieferbedingung) {
		Long idLieferbed = null;
		if (!S.isEmpty(sLieferbedingung)) {
			if (m_hmLieferbedingungen.containsKey(sLieferbedingung)){
				idLieferbed = Long.parseLong(m_hmLieferbedingungen.get(sLieferbedingung));
			} else if (sLieferbedingung.length()>=3 &&  m_hmLieferbedingungen.containsKey(sLieferbedingung.substring(0, 3) )){
				// falls die Lieferbedingungen nicht gefunden werden, dann die ersten drei Buchstaben vergleichen
				idLieferbed = Long.parseLong(m_hmLieferbedingungen.get(sLieferbedingung.substring(0, 3) ));
			}
		}
		return idLieferbed;
	}
	
	

	
	
	/**
	 * ermittelt die Artikelid aus der Artikelbez-ID unter Zuhilfenahme des internen Caches
	 * @author manfred
	 * @date   21.08.2012
	 * @param sIDArtikelBez
	 * @return
	 */
	public Long getArtikelIDFromArtikelBezID(String sIDArtikelBez){
				
		String sIDArtikel = null;
		if (sIDArtikelBez == null || sIDArtikelBez.equals("-1")) return null;
		
		
		if (m_hmArtikeBezZuordnung.containsKey(sIDArtikelBez)){
			sIDArtikel = m_hmArtikeBezZuordnung.get(sIDArtikelBez);
		} else {
			try {
				Rec21_artikel_bez rArtBez = new Rec21_artikel_bez()._fill_id(sIDArtikelBez);
				
				sIDArtikel = rArtBez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel);
				m_hmArtikeBezZuordnung.put(sIDArtikelBez, sIDArtikel);
				m_hmArtikeBezZuordnungObjekte.put(sIDArtikelBez, rArtBez);
			} catch (myException e1) {
				// keinen Artikelbez gefunden
			}
		}
		if (sIDArtikel != null){
			return new Long(sIDArtikel);
		} else 
			return null;
	}
	
}
