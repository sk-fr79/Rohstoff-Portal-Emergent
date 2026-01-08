package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

//import com.sun.glass.events.GestureEvent;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
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
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_Lager_Konto;



public class BG_HAND_Transformation {
	
	Vector<String> m_vLagerOrte = null;
	
	GROESSEN_Umrechnung m_oUmrechnung = null;
	
	HashMap<String, RECORD_ARTIKEL_BEZ> m_hmArtikelBez_zu_Artikel = null;
	HashMap<String, RECORD_ARTIKEL> m_hmArtikel = null;
	RecList20				m_rlArtikel = null;
	
	
	/**
	 * Default-Konstruktor
	 * @author manfred
	 * @throws myException 
	 * @date   08.08.2012
	 */
	public BG_HAND_Transformation(Vector<String>Lagerorte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, RECORD_ARTIKEL_BEZ> hmArtikeBezZuordnung, RecList20 rlArtikel) throws myException {
		super();
		m_vLagerOrte = Lagerorte;
		m_oUmrechnung = objUmrechnung;
		m_hmArtikelBez_zu_Artikel = hmArtikeBezZuordnung;
		m_rlArtikel = rlArtikel;
	}



	

	/**
	 * Generieren eines Vektors mit Atom für eine einfache Korrekturbuchung
	 * @author manfred
	 * @date   24.09.2012
	 * @param oLager
	 * @param posnrAtomInVector
	 * @return
	 * @throws myException
	 */
	protected jt_bg_vektor generiere_Vektor_Korrekturbuchung (Rec20_Lager_Konto oLager) throws myException{
		
		Long posnrInVektor = 1L;
		boolean bIstLadeseite = oLager.get_ufs_dbVal(LAGER_KONTO.buchungstyp, "*").equals("WA");
		
		// Vektor
		jt_bg_vektor oVek = new jt_bg_vektor(oLager);
		oVek.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.FINAL);
		oVek.setEN_VEKTOR_WARENKLASSE(EN_LADUNG_WARENKLASSE.EIGENWARE);
		oVek.setEN_VEKTOR_TYP(EN_VEKTOR_TYP.KORR.name());
	
		
		// Atom 
		jt_bg_atom atom = new jt_bg_atom(oLager);
		atom.setPOSNR(posnrInVektor);
		atom.setEN_ATOM_TYP(EN_ATOM_TYP.HAUPTATOM);
		atom.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.MID);

		// vektor-atom-Verbindung
		atom.set_jt_bg_vektor(oVek);
		atom.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		oVek.add_bgAtom(atom);
		
		
		// Ladung der Korrektur
		jt_bg_ladung ladungKonto = new jt_bg_ladung(atom, oLager);
		
		// Besitzverhältnisse
		ladungKonto.setID_ADRESSE_BESITZER(bibSES.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null));

		// Sorten vervollständigen
		setArtikelBezeichnungen(ladungKonto);
		
		ladungKonto.setID_ARTIKEL_BEZ(getArtikelBezID_For_ArtikelID(ladungKonto.getID_ARTIKEL().Value()));
		ladungKonto.setARTBEZ1(getARTBEZ1(ladungKonto.getID_ARTIKEL().Value()));

		
		ladungKonto.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		ladungKonto.set_jt_bg_atom(atom);
		
		// Lager-Station
		jt_bg_station station_konto = new jt_bg_station(this, ladungKonto, oLager,  false);
		station_konto.set_jt_bg_ladung(ladungKonto);
		ladungKonto.set_jt_bg_station(station_konto);
		station_konto.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

		// Ladung ans Atom hängen
		atom.add_jt_bg_ladung(ladungKonto);
		
		
		// Korrektur-Ladung
		jt_bg_ladung ladungKorr = new jt_bg_ladung(ladungKonto);
		ladungKorr.setMENGENVORZEICHEN(ladungKorr.getMENGENVORZEICHEN().lValue * -1L);
		
		// Korrektur-Station
		jt_bg_station station_korr = new jt_bg_station(this, ladungKorr, oLager, true);
		station_korr.set_jt_bg_ladung(ladungKorr);
		ladungKorr.set_jt_bg_station(station_korr);
		station_korr.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		
		if(bIstLadeseite){
			// Ausbuchung, d.h. das Korrektur-Lager ist Zieladresse
			atom.get_jt_bg_ladungs().add(ladungKorr);
		} else {
			// Einbuchung, d.h. das Korrektur-Lager ist Startadresse
			atom.get_jt_bg_ladungs().add(0,ladungKorr);
		}
		
		atom.setDATUM_AUSFUEHRUNG(ladungKonto.getLEISTUNGSDATUM().dValue);
		
		return oVek;
		
	}

	
	
	protected jt_bg_vektor generiere_Vektor_Umbuchung (Rec20_Lager_Konto oLager1, Rec20_Lager_Konto oLager2) throws myException{
		
		Long posnrInVektor = 1L;
		boolean bIstLadeseite = oLager1.get_ufs_dbVal(LAGER_KONTO.buchungstyp, "*") .equals("WA");
		
		// Vektor
		jt_bg_vektor oVek = new jt_bg_vektor(oLager1);
		oVek.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.FINAL);
		oVek.setEN_VEKTOR_WARENKLASSE(EN_LADUNG_WARENKLASSE.EIGENWARE);
		oVek.setEN_VEKTOR_TYP(EN_VEKTOR_TYP.UMB.name());
	
		
		jt_bg_atom atom = new jt_bg_atom(oLager1);
		atom.setPOSNR(posnrInVektor);
		atom.setEN_ATOM_TYP(EN_ATOM_TYP.HAUPTATOM);
		atom.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.MID);

		// vektor-atom-Verbindung
		atom.set_jt_bg_vektor(oVek);
		atom.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		oVek.add_bgAtom(atom);
				
		// AUSBUCHUNG 
		jt_bg_ladung ladungKonto1 = new jt_bg_ladung(atom, oLager1);
		
		// Besitzverhältnisse
		ladungKonto1.setID_ADRESSE_BESITZER(bibSES.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null));
		
		// Sorten vervollständigen
		
		setArtikelBezeichnungen(ladungKonto1);
				
		ladungKonto1.setID_ARTIKEL_BEZ(getArtikelBezID_For_ArtikelID(ladungKonto1.getID_ARTIKEL().Value()));
		ladungKonto1.setARTBEZ1(getARTBEZ1(ladungKonto1.getID_ARTIKEL().Value()));
		ladungKonto1.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		ladungKonto1.set_jt_bg_atom(atom);
		
		// Lager-Station
		jt_bg_station station_konto1 = new jt_bg_station(this, ladungKonto1, oLager1,  false);
		station_konto1.set_jt_bg_ladung(ladungKonto1);
		ladungKonto1.set_jt_bg_station(station_konto1);
		station_konto1.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

		// Ladung ans Atom hängen
		atom.add_jt_bg_ladung(ladungKonto1);
		
		// EINBUCHUNG
		jt_bg_ladung ladungKonto2 = new jt_bg_ladung(atom, oLager2);

		// Besitzverhältnisse
		ladungKonto2.setID_ADRESSE_BESITZER(bibSES.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null));

		// Sorten vervollständigen
		setArtikelBezeichnungen(ladungKonto2);
				
		ladungKonto2.setID_ARTIKEL_BEZ(getArtikelBezID_For_ArtikelID(ladungKonto2.getID_ARTIKEL().Value()));
		ladungKonto2.setARTBEZ1(getARTBEZ1(ladungKonto2.getID_ARTIKEL().Value()));
		ladungKonto2.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		ladungKonto2.set_jt_bg_atom(atom);
		
		// Lager-Station
		jt_bg_station station_konto2 = new jt_bg_station(this, ladungKonto2, oLager2,  false);
		station_konto2.set_jt_bg_ladung(ladungKonto2);
		ladungKonto2.set_jt_bg_station(station_konto2);
		station_konto2.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

		atom.setDATUM_AUSFUEHRUNG(ladungKonto1.getLEISTUNGSDATUM().dValue);

		// Ladung ans Atom hängen
		atom.add_jt_bg_ladung(ladungKonto2);
		
		
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
	 * setzt anr1, artbez1 und artbez2 in der ladung anhand der artikel-ID
	 * @author manfred
	 * @date 15.02.2018
	 *
	 * @param ladung
	 */
	protected void setArtikelBezeichnungen(jt_bg_ladung ladung){
		Rec20 art = null;
		if (ladung.getID_ARTIKEL().Value() != null){
			if (m_rlArtikel.containsKey(ladung.getID_ARTIKEL().Value())){
				try {
					art = m_rlArtikel.get(ladung.getID_ARTIKEL().Value());
					ladung.setANR1(art.get_ufs_dbVal(ARTIKEL.anr1));
					ladung.setARTBEZ1(art.get_ufs_dbVal(ARTIKEL.artbez1));
					ladung.setARTBEZ2(art.get_ufs_dbVal(ARTIKEL.artbez2));
				} catch (myException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ermittelt die Artikelid aus der Artikelbez-ID unter zuhilfenahme des internen Caches
	 * @author manfred
	 * @date   21.08.2012
	 * @param sIDArtikelBez
	 * @return
	 * @throws myException 
	 */
	protected Long getArtikelBezID_For_ArtikelID(String sIDArtikel) {
		Long lIDArtikelBez = null;
		RECORD_ARTIKEL_BEZ oRecArtbez = null;
		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
		
		
		if (m_hmArtikelBez_zu_Artikel.containsKey(sIDArtikel)){
			oRecArtbez = m_hmArtikelBez_zu_Artikel.get(sIDArtikel);
			try {
				lIDArtikelBez = oRecArtbez.get_ID_ARTIKEL_BEZ_lValue(null);
			} catch (myException e) {
				e.printStackTrace();
			}
		} else {
			RECLIST_ARTIKEL_BEZ oArtBez = null;
			try {
				oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
				RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
				lIDArtikelBez = o.get_ID_ARTIKEL_BEZ_lValue(null);
				m_hmArtikelBez_zu_Artikel.put(sIDArtikel, o);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		return lIDArtikelBez;
	}

	
	/**
	 * Ermittelt die ARTBEZ1 zu einem Artikel
	 * @author manfred
	 * @date   27.09.2012
	 * @param sIDArtikel
	 * @return
	 */
	protected String getARTBEZ1(String sIDArtikel){
		String sARTBEZ1 = null;
		Long lIDArtikelBez = null;
		RECORD_ARTIKEL_BEZ oRecArtbez = null;
		if (sIDArtikel == null || sIDArtikel.equals("-1")) return null;
		
		
		if (m_hmArtikelBez_zu_Artikel.containsKey(sIDArtikel)){
			oRecArtbez = m_hmArtikelBez_zu_Artikel.get(sIDArtikel);
			try {
				sARTBEZ1 = oRecArtbez.get_ARTBEZ1_cUF();
			} catch (myException e) {
				e.printStackTrace();
			}
		} else {
			RECLIST_ARTIKEL_BEZ oArtBez = null;
			try {
				oArtBez = new RECLIST_ARTIKEL_BEZ("ID_ARTIKEL = " + sIDArtikel," ANR2 ASC ");
				RECORD_ARTIKEL_BEZ o = oArtBez.get(0);
				sARTBEZ1 = oRecArtbez.get_ARTBEZ1_cUF();
				m_hmArtikelBez_zu_Artikel.put(sIDArtikel, o);
			} catch (myException e) {
				e.printStackTrace();
			}
		}

		return sARTBEZ1;
	}
	
	

	
	
	public Vector<String> getSQLTransformation(){
		return new Vector<String>();
	}
	
	
}
