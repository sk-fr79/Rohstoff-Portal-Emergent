package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import org.apache.xmlrpc.parser.LongParser;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;

public abstract class BG_FUHREN_Transformation {

//	int m_typ_strecke_lager_mixed = -1;
	EN_VEKTOR_TYP m_vektor_typ = EN_VEKTOR_TYP.WE;
	
	Rec21 		m_oFuhre = null;
	RecList21  	m_rlOrt = null;
	
	int fuhre_lager_links = 0;
	int fuhre_lager_rechts = 0;
	int fuhre_kunde_links = 0;
	int fuhre_kunde_rechts = 0;
	
	int lager_links = 0;
	int lager_rechts = 0;
	int kunde_links = 0;
	int kunde_rechts = 0;
	
	int sum_links = 0;
	int sum_rechts = 0;
	

	Vector<String> m_vLagerOrte = null;
	
	// der Hauptsatz
	jt_bg_vektor     m_BG_Vektor = null;

	// Objekt zur Größenumrechnung
	GROESSEN_Umrechnung m_oUmrechnung = null;
	
	HashMap<String, String> m_hmArtikeBezZuordnung = null;
	Vector<String> 			m_vLeergutartikel = null;
	HashMap<String, Long>   m_hmLaenderCode = null;
	
	// eigene Mandanten-Adresse
	Long m_IDAdresseMandant = null;
	Long m_IDAdresseBesitzerFuhre = null;
	Long m_IDAdresseBesitzerOrt = null;
	
	HashMap<String, String> m_hmLieferbedingungen = null;

	
	/**
	 * Default-Konstruktor
	 * @author manfred
	 * @throws myException 
	 * @date   08.08.2012
	 */
	public BG_FUHREN_Transformation(Vector<String>Lagerorte, GROESSEN_Umrechnung objUmrechnung, HashMap<String, String> hmArtikeBezZuordnung, Vector<String> vLeergutartikel , HashMap<String, String> hmLieferbedingungen,HashMap<String, Long> hmLaendercode) throws myException {
		super();
		m_vLagerOrte = Lagerorte;
		m_oUmrechnung = objUmrechnung;
		m_hmArtikeBezZuordnung = hmArtikeBezZuordnung;
		m_vLeergutartikel = vLeergutartikel;
		
		// die eigene AdressID ermitteln
		m_IDAdresseMandant = Long.parseLong( bibALL.get_ID_ADRESS_MANDANT() );
		
		// die Lieferbedingungen
		m_hmLieferbedingungen = hmLieferbedingungen;
		// die Ländercodes
		m_hmLaenderCode = hmLaendercode;
		
	}

	
	

	/**
	 * Hauptroutine 
	 * @author manfred
	 * @date 25.01.2018
	 *
	 * @param oRecFuhre
	 * @return
	 * @throws myException
	 */
	public abstract jt_bg_vektor transformiereFuhre(Rec20_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException;	
	

	
	
	/**
	 * Ermittelt die Land-ID vom gegebenen Laendercode
	 * @author manfred
	 *
	 * @param Laendercode
	 * @return
	 */
	protected Long getLandIDFromLaendercode(String Laendercode){
		Long lID = null;
		
		if (Laendercode != null && m_hmLaenderCode.containsKey(Laendercode.trim())){
			lID = m_hmLaenderCode.get(Laendercode.trim());
		} else {
			// default-Laendercode D wählen
			lID = m_hmLaenderCode.get("D");
		}
		
		return lID;
	}
	
	
	
	
	/**
	 * ermittelt die Artikelid aus der Artikelbez-ID unter Zuhilfenahme des internen Caches
	 * @author manfred
	 * @date   21.08.2012
	 * @param sIDArtikelBez
	 * @return
	 */
	protected Long getArtikelIDFromArtikelBezID(String sIDArtikelBez){
		String sIDArtikel = null;
		if (sIDArtikelBez == null || sIDArtikelBez.equals("-1")) return null;
		
		
		if (m_hmArtikeBezZuordnung.containsKey(sIDArtikelBez)){
			sIDArtikel = m_hmArtikeBezZuordnung.get(sIDArtikelBez);
		} else {
			try {
				Rec21 rArtBez = new Rec21(ARTIKEL_BEZ._tab());
				rArtBez._fill_id(sIDArtikelBez);
				sIDArtikel = rArtBez.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel);
				m_hmArtikeBezZuordnung.put(sIDArtikelBez, sIDArtikel);
			} catch (myException e1) {
				// keinen Artikelbez gefunden
			}
		}
		if (sIDArtikel != null){
			return new Long(sIDArtikel);
		} else 
			return null;
	}
	
	
	
	
	
	/**
	 * Aufbereiten der Fuhre und deren Orte um die verschiedenen Fälle zu betrachten
	 * @author manfred
	 * @date   06.08.2012
	 * @throws myException
	 */
	protected void	initFuhre(Rec20_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException{
		// initialisieren
		m_rlOrt = null;
		
		fuhre_lager_links 	= 0;
		fuhre_lager_rechts 	= 0;
		fuhre_kunde_links 	= 0;
		fuhre_kunde_rechts 	= 0;
		
		lager_links 		= 0;
		lager_rechts 		= 0;
		kunde_links 		= 0;
		kunde_rechts	 	= 0;
		
		m_BG_Vektor 		= null;
		
		//
		// Füllen der Daten
		//
		m_oFuhre = oRecFuhre;
		
		
		// lesen der Fuhrenorte
		try {
			m_rlOrt = m_oFuhre.get_down_reclist21(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, "NVL(DELETED,'N') = 'N' ", " DEF_QUELLE_ZIEL, ID_ARTIKEL  " ,true)  ;
		} catch (myException e) {
			m_rlOrt = null;
		}
		
		// Hauptfuhre untersuchten
		if (m_vLagerOrte.contains(m_oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start, "-") ) ){
			lager_links++;
			fuhre_lager_links++;
		} else {
			kunde_links++;
			fuhre_kunde_links++;
		}
		
		if (m_vLagerOrte.contains(m_oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel, "-")) ){
			lager_rechts++;
			fuhre_lager_rechts++;
		} else {
			kunde_rechts++;
			fuhre_kunde_rechts++;
		}
		
		
		// Fuhrenorte untersuchen
		if (m_rlOrt != null){
			for (Rec20 o: m_rlOrt.values()){
				
				if (m_vLagerOrte.contains(o.get_fs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "-") ) ){
					if (o.get_fs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager).equals("EK")){
						lager_links++;
					} else {
						lager_rechts++;
					}
				} else {
					if (o.get_fs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager).equals("EK")){
						kunde_links++;
					} else {
						kunde_rechts++;
					}
				}
			}
		}
		
		// summen...
		sum_links = lager_links + kunde_links;
		sum_rechts = lager_rechts + kunde_rechts;
		
	}
	
	
	
		
	/**
	 * füllt die Basisdaten, unabhängig ob Strecke, WE-WA-Fuhre oder ähnlichem
	 * @author manfred
	 * @date   01.08.2012
	 * @param oFuhre
	 * @return
	 * @throws myException
	 */
	protected jt_bg_vektor generiereVectorAusFuhre_Basisdaten(Rec20_VPOS_TPA_FUHRE_ext oFuhre) throws myException{
	
		// Vektor erzeugen und mit den Basisdaten initialisieren
		jt_bg_vektor oVek = new jt_bg_vektor(this,oFuhre);
		
		
		/**
		 * STATUS
		 * "Kann folgende Einträge haben: 
		 * GEPLANT
		 * STORNIERT (protokollieren: STORNIERT_VON,_AM) 
		 * AKTIV  (noetig, damit irgend ein Schein o.ä. erzeugt werden kann) 
		 * ABGESCHLOSSEN  (protokollieren: ABGESCHLOSSEN_VON,_AM)" 
		 */
		EN_VEKTOR_STATUS enStatus = EN_VEKTOR_STATUS.GEPLANT;
		
		if (oFuhre.is_yes_db_val(VPOS_TPA_FUHRE.ist_storniert)){
			enStatus = EN_VEKTOR_STATUS.STORNIERT;
		} else if (oFuhre.is_yes_db_val(VPOS_TPA_FUHRE.abgeschlossen)){
			enStatus = EN_VEKTOR_STATUS.FINAL;
		} else if (oFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief,null)  != null || oFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn,null) != null ){
			enStatus = EN_VEKTOR_STATUS.AKTIV;
		} 
		oVek.setEN_VEKTOR_STATUS(enStatus);
		
		
		
//		EIGENWARE/FREMDWARE/LEERGUT
		EN_LADUNG_WARENKLASSE Warenklasse = EN_LADUNG_WARENKLASSE.EIGENWARE;
		if (oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_adresse_fremdauftrag) != null || oFuhre.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung) ){
			Warenklasse = EN_LADUNG_WARENKLASSE.FREMDWARE;
		} 
		oVek.setEN_VEKTOR_WARENKLASSE(Warenklasse);
		
		return oVek;
	}
	

	
	


	/**
	 * Erzeugen eines Bewegungsatoms aus einer HauptFuhre für einen Wareneingang
	 * LAGER -> KD
	 * LAGER -> MIX  / MIX -> KUNDE
	 * 
	 * Eine Mengenkorrektur / Sortenkorrektur findet aktuell implizit im MIXed-Lager statt
	 * 
	 * @author manfred
	 * @date   01.08.2012
	 * @param oFuhre
	 * @return
	 * @throws myException 
	 */
	protected jt_bg_atom generiere_atom_Hauptfuhre (jt_bg_vektor oVek, Rec20_VPOS_TPA_FUHRE_ext oFuhre, long posnrInVektor) throws myException{

		
		jt_bg_atom atom = new jt_bg_atom(oFuhre);
		atom.setEN_ATOM_TYP(EN_ATOM_TYP.HAUPTATOM);
		atom.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.MID);

		// vektor-atom-Verbindung
		atom.set_jt_bg_vektor(oVek);
		atom.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		oVek.add_bgAtom(atom);
		
		
		//
		// atomdaten setzen
		//
		atom.setPOSNR(posnrInVektor);

		// Start-Ladung
		jt_bg_ladung ladung_start = new jt_bg_ladung(this, oFuhre, true);
		
		ladung_start.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		ladung_start.set_jt_bg_atom(atom);
		atom.add_jt_bg_ladung(ladung_start);
		
		// Start-Station
		jt_bg_station station_start = new jt_bg_station(this, oFuhre, true);
		station_start.set_jt_bg_ladung(ladung_start);
		ladung_start.set_jt_bg_station(station_start);
		station_start.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		// Ziel-Ladung
		jt_bg_ladung ladung_ziel = new jt_bg_ladung(this, oFuhre, false);
		ladung_ziel.set_jt_bg_atom(atom);
		atom.add_jt_bg_ladung(ladung_ziel);
		ladung_ziel.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
				
		// Ziel-Station 
		jt_bg_station station_ziel = new jt_bg_station(this, oFuhre, false);
		station_ziel.set_jt_bg_ladung(ladung_ziel);
		ladung_ziel.set_jt_bg_station(station_ziel);
		station_ziel.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		
		// datum der Ausführung ist das Datum an dem das ganze beginnt??
		MyDate d = oFuhre.get_myDate_dbVal(VPOS_TPA_FUHRE.datum_aufladen);
		atom.setDATUM_AUSFUEHRUNG(d!= null ? bibDate.String2Date(d.get_cDBFormatErgebnis()) : null ) ;
		
		
		
//		// Sorte
//		// info über neue Sorte lesen:
		Long idArtikel = getArtikelIDFromArtikelBezID(oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_ek,"-1") );

		
		// Besitzverhältnisse
		Long IDAdresseBesitzerFuhreStart = null;
		Long IDAdresseBesitzerFuhreZiel = null;
		
		// EIGENWARE/FREMDWARE/LEERGUT
		if (oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)   != null){
			// bei Fremdauftrag immer der Auftraggeber
			IDAdresseBesitzerFuhreStart = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag);
			IDAdresseBesitzerFuhreZiel = m_IDAdresseBesitzerFuhre;
		} else {
			String sIDArtikel = idArtikel != null ? idArtikel.toString() : "-1";
			if (oFuhre.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung)  ){
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					IDAdresseBesitzerFuhreStart = m_IDAdresseMandant;
					IDAdresseBesitzerFuhreZiel = m_IDAdresseMandant;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					IDAdresseBesitzerFuhreStart = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start);
					IDAdresseBesitzerFuhreZiel = IDAdresseBesitzerFuhreStart;
				}
			} else {
				// Eigenware
				IDAdresseBesitzerFuhreStart = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start);
				IDAdresseBesitzerFuhreZiel =  oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_ziel);
			}
		}
		
		
		
		// abrechenbar immer, wenn besitzerwechsel stattfindet
//		atom.setAbrechenbar(m_IDAdresseBesitzerStart.equals(m_IDAdresseBesitzerZiel) ? "N" : "Y");
		ladung_start.setID_ADRESSE_BESITZER(IDAdresseBesitzerFuhreStart);
		ladung_ziel.setID_ADRESSE_BESITZER(IDAdresseBesitzerFuhreZiel);
		
		
		// Planmenge des Vektors ist die Planmenge der Ladeseite....
		BigDecimal bdPlan = oFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_lief,null);
		if (bdPlan == null){
			bdPlan = oFuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_abn,null);
		}
		if (bdPlan == null){
			bdPlan = BigDecimal.ZERO;
		}
		
		oVek.setPLANMENGE(bdPlan);
		

		
//		//Daten aus der Rechnungsposition zusätzlich noch setzen, falls die Ladeseite kein Lager ist
//		int nLagervorzeichen = m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-")) ? -1: 1 ;
//		setRGValuesInAtomFromFuhre(atom, oFuhre, nLagervorzeichen);
		
		
		// Bestellnr
		String sBestellnr = oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.bestellnummer_ek);
		if (sBestellnr == null) {
			// manchmal steht die Bestellnummer auch auf der falschen Seite
			sBestellnr = oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.bestellnummer_vk);
		}

	
		//Löschinformationen
//		oAtom.setDelDate(bibDate.String2Date(oFuhre.get_DEL_DATE_cUF())) ;
//		oAtom.setDeleted(oFuhre.get_DELETED_cUF());
//		oAtom.setDelGrund(oFuhre.get_DEL_GRUND_cUF());
//		oAtom.setDelKuerzel(oFuhre.get_DEL_KUERZEL_cUF());
		
		
		// Storno
//		oAtom.setStorniert(oFuhre.get_IST_STORNIERT_cUF());
//		oAtom.setStorniertAm(oFuhre.get_IST_STORNIERT_cUF_NN("N").equalsIgnoreCase("Y") ? bibDate.String2Date("2012-01-01"): null);
//		oAtom.setStorniertGrund(oFuhre.get_STORNO_GRUND_cUF());
//		oAtom.setStorniertVon(oFuhre.get_STORNO_KUERZEL_cUF());
		
		
		// sonstiges
//		oAtom.setPreisermittlung(null);
//		oAtom.setSetzkastenKomplett(null);
		


		
		return atom;
		
	}

	
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 25.01.2018
	 *
	 * @param vektor
	 * @param oFuhre
	 * @param oOrt
	 * @param posnrAtomPos
	 * @param enumSonderlager
	 * @param generateDummyEntry
	 * @return
	 * @throws myException
	 */
	protected jt_bg_atom generiereAtom_FuhrenORT (jt_bg_vektor vektor, Rec20_VPOS_TPA_FUHRE_ext oFuhre, Rec20_VPOS_TPA_FUHRE_ORT_ext oOrt, long posnrAtomPos ) throws myException{
		
		//
		// Bewegungsatom
		//
		jt_bg_atom atom = new jt_bg_atom(vektor,oOrt,oFuhre);
		
		atom.setPOSNR(posnrAtomPos);
		atom.setEN_ATOM_TYP(EN_ATOM_TYP.HAUPTATOM);
		atom.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.MID);
		
		
		// Start-Ladung
		jt_bg_ladung ladung_ort = new jt_bg_ladung(this, atom, oOrt, oFuhre);
		ladung_ort.set_jt_bg_atom(atom);
		ladung_ort.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		// Start-Station 
		jt_bg_station station_ort = new jt_bg_station(this, oOrt,oFuhre);
		station_ort.set_jt_bg_ladung(ladung_ort);
		ladung_ort.set_jt_bg_station(station_ort);
		station_ort.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		boolean bLadeseite = ladung_ort.getMENGENVORZEICHEN().lValue.equals(-1L);
		
		// Ziel-Ladung
		jt_bg_ladung ladung_fuhre = new jt_bg_ladung(this, oFuhre, !bLadeseite );
		ladung_fuhre.set_jt_bg_atom(atom);
		ladung_fuhre.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		// Fuhrenseite der Ladung auf 0-Gewicht setzen
		ladung_fuhre.setABZUG_MENGE(BigDecimal.ZERO);
		ladung_fuhre.setMENGE(BigDecimal.ZERO);
		ladung_fuhre.setMENGE_NETTO(BigDecimal.ZERO);
		
		
				
		// Ziel-Station 
		jt_bg_station station_fuhre = new jt_bg_station(this, oFuhre, !bLadeseite);
		station_fuhre.set_jt_bg_ladung(ladung_fuhre);
		ladung_fuhre.set_jt_bg_station(station_fuhre);
		station_fuhre.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		station_fuhre.setKONTROLLMENGE(BigDecimal.ZERO);
		station_fuhre.setWIEGEKARTENKENNER(null);
		station_fuhre.setID_WIEGEKARTE(null);
		
		
		// ladungen der Reihe nach an die Fuhre hängen...spaßeshalber
		if (bLadeseite){
			atom.add_jt_bg_ladung(ladung_ort);
			atom.add_jt_bg_ladung(ladung_fuhre);
		} else {
			atom.add_jt_bg_ladung(ladung_fuhre);
			atom.add_jt_bg_ladung(ladung_ort);
		}
			
		
		// datum der Ausführung ist das Datum des Orts
		MyDate d = oOrt.get_myDate_dbVal(VPOS_TPA_FUHRE_ORT.datum_lade_ablade);
		atom.setDATUM_AUSFUEHRUNG(d!= null ? bibDate.String2Date(d.get_cDBFormatErgebnis()) : null ) ;
//		atom.setDATUM_AUSFUEHRUNG(bibDate.String2Date(oOrt.get_myDate_dbVal(VPOS_TPA_FUHRE_ORT.datum_lade_ablade).get_cDBFormatErgebnis()));
		
		
		// Sorte ist sorte der Ladeseite, da es nur um die Erkennung der Leergutartikel geht.
		Long idArtikel = getArtikelIDFromArtikelBezID(oOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_artikel_bez,"-1"));

		// EIGENWARE/FREMDWARE/LEERGUT
		if (oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)  != null){
			// bei Fremdauftrag immer der Auftraggeber
			m_IDAdresseBesitzerFuhre = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag);
			m_IDAdresseBesitzerOrt = m_IDAdresseBesitzerFuhre;
		} else {
			if (oOrt.is_yes_db_val(VPOS_TPA_FUHRE_ORT.ohne_abrechnung) ){
				String sIDArtikel = oOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_artikel, "-1");
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					m_IDAdresseBesitzerFuhre = m_IDAdresseMandant;
					m_IDAdresseBesitzerOrt = m_IDAdresseMandant;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					m_IDAdresseBesitzerFuhre = oOrt.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse);
					m_IDAdresseBesitzerOrt = m_IDAdresseBesitzerFuhre;
				}
			} else {
				// Eigenware / nur abrechenbar, wenn der Ort ein Kunde ist....
				m_IDAdresseBesitzerOrt = oOrt.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse);;
				
				if( !m_vLagerOrte.contains(oOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "*"))){
					if (bLadeseite){
						m_IDAdresseBesitzerFuhre = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_ziel);
					}	else {
						m_IDAdresseBesitzerFuhre = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start);
					}
				} else {
					m_IDAdresseBesitzerFuhre = m_IDAdresseMandant;
				}
			}
		}
		
		
		
		// abrechenbar immer, wenn besitzerwechsel stattfindet
		ladung_fuhre.setID_ADRESSE_BESITZER(m_IDAdresseBesitzerFuhre);
		ladung_ort.setID_ADRESSE_BESITZER(m_IDAdresseBesitzerOrt);

		//Daten aus der Rechnungsposition zusätzlich noch setzen, falls die Ladeseite kein Lager ist
//		int nLagervorzeichen = m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-")) ? -1: 1 ;
		
		
//		//Daten aus der Rechnungsposition
//		setRGValuesInAtomFromFuhreOrt(atom, oOrt);
		
		return atom;
	}

	
	
	
	
	
	
	
	

	
	/**
	 * Setzt relevante Rechnungspositions-Werte im Atom bezogen auf die Fuhre
	 * @author manfred
	 * @date   12.09.2013
	 * @param atom
	 * @param oFuhre
	 * @param Lagervorzeichen
	 * @throws myException
	 */
	protected void setRGValuesInAtomFromFuhre(jt_bg_atom atom, Rec21 oFuhre, int Lagervorzeichen) throws myException {
		
		// Alternative Lieferbedingung aus der Fuhre setzen
		String sLieferbedingung = "";
		if(Lagervorzeichen>0){
			sLieferbedingung = oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.lieferbed_alternativ_ek);
		} else {
			sLieferbedingung = oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.lieferbed_alternativ_vk);
		}
		
		// zuerst die Lieferbedingung aus der Fuhre im Atom eintragen 
		setIDLieferbedingungInAtom(atom, sLieferbedingung);
		
		RecList21 lVposRg = null;
		try {
			
			SqlStringExtended s = new SqlStringExtended("select * from " + _TAB.vpos_rg.fullTableName() + " WHERE "
					+ " ID_VPOS_RG_STORNO_VORGAENGER is null " +
					" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
				   	" AND NVL(DELETED,'N') = 'N' " +
				   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = ? "  +    //+ oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre) +
				   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL " + 
				   	" AND LAGER_VORZEICHEN = ? "   // + Integer.toString(Lagervorzeichen)
					);
			
					s.getValuesList().add(new Param_Long(oFuhre.get_myLong_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre).get_lValue() ) );
					s.getValuesList().add(new Param_Long(Lagervorzeichen) );
					
			lVposRg = new RecList21(_TAB.vpos_rg)._fill(s);		
//			lVposRg = new RecList21(_TAB.vpos_rg)._fill(" ID_VPOS_RG_STORNO_VORGAENGER is null " +
//															" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
//														   	" AND NVL(DELETED,'N') = 'N' " +
//														   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = " + oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre) +
//														   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL " + 
//														   	" AND LAGER_VORZEICHEN = " + Integer.toString(Lagervorzeichen), "");
			
			if (lVposRg.size() > 0){
				Rec21 rVposRg = lVposRg.get(0);
				// Werte setzen wenn eine RG-Position gefunden wurde
				setRGValuesInAtom( atom, rVposRg );
			}
		} catch (myException e) {
			throw new myException("setRGValuesInAtomFromFuhre::Rechnungsposition konnte nicht ermittelt werden", e);
		}
		
		
	}
	
	
	
	
	
	/**
	 * Setzt relevante Rechnungspositions-Werte im Atom bezogen auf den Fuhrenort
	 * @author manfred
	 * @date   12.09.2013
	 * @param atom
	 * @param oFuhreOrt
	 * @throws myException
	 */
	protected void setRGValuesInAtomFromFuhreOrt (jt_bg_atom atom, Rec21 oFuhreOrt) throws myException{
		
		// Alternative Lieferbedingung aus dem Fuhrenort setzen
		String sLieferbedingung =oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.lieferbed_alternativ) ; 
		
		// zuerst die Lieferbedingungen im Atom aus der Fuhre setzen 
		setIDLieferbedingungInAtom(atom, sLieferbedingung);
		

		RecList21 lVposRg = null;
		
		SqlStringExtended sqlExt = new SqlStringExtended("SELECT * FROM  " + VPOS_RG.fullTabName() + " WHERE" +
				" ID_VPOS_RG_STORNO_VORGAENGER is null " +
				" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
			   	" AND NVL(DELETED,'N') = 'N' " +
			   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = ?" + //oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre) +
			   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD = ?" //+ oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort)
			   	
			   	);
		sqlExt.getValuesList().add( new Param_Long("ID_FUHRE_ZUGEORDNET", Long.parseLong(oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre)) ));
		sqlExt.getValuesList().add( new Param_Long("ID_FUHRE_ORT_ZUGEORDNET", Long.parseLong(oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort)) ));
		
		
		try {
//			lVposRg = new RecList21(_TAB.vpos_rg)._fill(  	" ID_VPOS_RG_STORNO_VORGAENGER is null " +
//															" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
//														   	" AND NVL(DELETED,'N') = 'N' " +
//														   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = " + oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre) +
//														   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD = " + oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort) 
//														,"");
			
			lVposRg = new RecList21(_TAB.vpos_rg)._fill(sqlExt);

			// Werte setzen wenn eine RG-Position gefunden wurde
			if (lVposRg.size() > 0){
				Rec21 rVposRg = lVposRg.get(0);
				setRGValuesInAtom( atom, rVposRg );
			}
			
		} catch (myException e) {
			throw new myException("setRGValuesInAtomFromFuhreOrt::Rechnungsposition konnte nicht ermittelt werden", e);
		}
		
		
		
	}
	
	
	/**
	 * Setzt die Werte aus der Rechnungsposition im Atom
	 * @author manfred
	 * @date   12.09.2013
	 * @param atom
	 * @param oVposRg
	 * @throws myException
	 */
	private void setRGValuesInAtom(jt_bg_atom atom, Rec21 oVposRg ) throws myException{
			
			if (oVposRg != null){
				
				// falls die Rechnungsposition vorhanden ist, dann die Menge aus der Rechnungsposition nehmen
				atom.setE_PREIS(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.einzelpreis,null));
				atom.setID_ZAHLUNGSBEDINGUNGEN(oVposRg.get_raw_resultValue_Long(VPOS_RG.id_zahlungsbedingungen ));
				
				// die Lieferbedingung zuerst aus der Fuhre (also aus dem Atom) lesen, wenn nicht vorhanden, dann aus der Rechnungsposition
				String sLieferbedingung = atom.getLIEFERBEDINGUNGEN().sValue;
				if (bibALL.isEmpty(sLieferbedingung) ){
					// falls noch keine Lieferbedingung im Atom eingetragen war, die Lieferbedingung aus der Rechnungsposition eintragen
					sLieferbedingung =  oVposRg.get_ufs_dbVal(VPOS_RG.lieferbedingungen) ; 
					setIDLieferbedingungInAtom(atom, sLieferbedingung);
				}
				
				// resultierende Preise sind in der Ladung abgelegt
				for (jt_bg_ladung ladung : atom.get_jt_bg_ladungs()){
					ladung.setE_PREIS_RESULT_NETTO_MGE(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.epreis_result_netto_mge,null) );
					ladung.setE_PREIS_RESULT_BRUTTO_MGE(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.einzelpreis_result,null) ) ;
					
					ladung.setGESAMTPREIS(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gesamtpreis,null)) ;
					ladung.setGPREIS_ABZ_MGE(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gpreis_abz_mge,null));
					ladung.setGPREIS_ABZ_VORAUSZAHLUNG(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gpreis_abz_vorauszahlung,null)) ;
					ladung.setGPREIS_ABZ_AUF_NETTOMGE(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gpreis_abz_auf_nettomge,null) );
				}

				
			}
	}
	
	
	/**
	 * Ermittelt für alle Ladungen die Kunden-Preise und versucht diese auf die jeweils gegenüberliegende Lager-seite zu übertragen, wenn dort keine Preise vorhanden sind. 
	 * @author manfred
	 * @date 12.02.2018
	 *
	 */
	protected void checkAndCompletePriceEntries(){
		HashMap<Long, BigDecimal> hmKundenPreise = new HashMap<>();
		// erst die Kundenpreise ermitteln
		for (jt_bg_atom atom: m_BG_Vektor.get_jt_bg_atoms()){
			for (jt_bg_ladung ladung: atom.get_jt_bg_ladungs()){
				if (ladung.get_jt_bg_station() != null){
					String idAdresse = ladung.get_jt_bg_station().getID_ADRESSE().Value();
					if (!this.m_vLagerOrte.contains(idAdresse)){
						hmKundenPreise.put(ladung.getID_ARTIKEL().lValue, ladung.getE_PREIS_RESULT_NETTO_MGE().bdValue);
					}
				}
			}
		}

		// dann die fehlenden Lagerpreise aktualisieren
		for (jt_bg_atom atom: m_BG_Vektor.get_jt_bg_atoms()){
			for (jt_bg_ladung ladung: atom.get_jt_bg_ladungs()){
				if(ladung.get_jt_bg_station() != null){
					String idAdresse = ladung.get_jt_bg_station().getID_ADRESSE().Value();
					if (this.m_vLagerOrte.contains(idAdresse)){
						if (ladung.getE_PREIS_RESULT_NETTO_MGE()== null 
								|| ladung.getE_PREIS_RESULT_NETTO_MGE().bdValue  == null 
								|| ladung.getE_PREIS_RESULT_NETTO_MGE().bdValue.equals(BigDecimal.ZERO)){
							if(hmKundenPreise.containsKey(ladung.getID_ARTIKEL().lValue)){
								BigDecimal bdPreis = hmKundenPreise.get(ladung.getID_ARTIKEL().lValue);
								ladung.setE_PREIS_RESULT_NETTO_MGE(bdPreis);
								ladung.setE_PREIS_RESULT_BRUTTO_MGE(bdPreis);
							}
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Setzt die Lieferbedingung in die ID um
	 * @param atom
	 * @param sLieferbedingung
	 */
	private void setIDLieferbedingungInAtom(jt_bg_atom atom, String sLieferbedingung){
		// jetzt die ID aus der Lieferbedingung ermitteln
		if (sLieferbedingung != null){
			atom.setLIEFERBEDINGUNGEN(sLieferbedingung);

			sLieferbedingung = sLieferbedingung.trim().toUpperCase();
			
			if (sLieferbedingung != null){
				if (m_hmLieferbedingungen.containsKey(sLieferbedingung)){
					atom.setID_LIEFERBEDINGUNGEN(Long.parseLong(m_hmLieferbedingungen.get(sLieferbedingung)));
				} else if (m_hmLieferbedingungen.containsKey(sLieferbedingung.substring(0, 3) )){
					// falls die Lieferbedingungen nicht gefunden werden, dann die ersten drei Buchstaben vergleichen
					atom.setID_LIEFERBEDINGUNGEN(Long.parseLong(m_hmLieferbedingungen.get(sLieferbedingung.substring(0, 3) )));
				}
			}
		}
	}
	


	
	/**
	 * nimmt die beiden generierten Atome einer Fuhre und verbindet sie zur Strecke
	 * KD1 ---> KD2
	 * nach
	 * KD1 ---> STRECKE ---> KD2
	 * die Verbindung wird über die Ladungen und Stationen hergestellt
	 * @author manfred
	 * @param oFuhre 
	 * @date 07.02.2018
	 *
	 * @param vektor
	 * @param atom_lade
	 * @param atom_ablade
	 * @throws myException 
	 */
	public void fuhre_to_strecke(Rec20 oFuhre, jt_bg_vektor vektor, jt_bg_atom atom_lade, jt_bg_atom atom_ablade ) throws myException{
		
		// linkes und rechtes atom sind identisch generiert, und jetzt müssen die Ladungen getauscht werden, und die Streckenstation als Bindeglied erzeugt werden
		// Linke Struktur: 		S1a-L1a-A1-L1b-S1b
		// Rechte Struktur: 	S2a-L2a-A2-L2b-S2b
		// wird zu
		// S1a-L1a-A1-L2a-Sstrecke
		// SStrecke-L1b-A2-L2b-S2b
		// d.h. die Informationen des Kunden1 werden in die Strecke als WE übernommen(Sorte,Menge)
		// und die Informationen des Kunden2 werden in die Strecke als WA übernommen. Somit sind we und wa in der Strecke gespiegelt.
		
		atom_lade.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.LEFT);
		atom_ablade.setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG.RIGHT);
		
		// L1b und L2a ermitteln, die dann getauscht werden
		jt_bg_ladung l1b =  atom_lade.get_jt_bg_ladungs().lastElement();
		jt_bg_ladung l2a =  atom_ablade.get_jt_bg_ladungs().firstElement();

		
		
		// Ladungen tauschen: 
		// A2-LD1 --> A1-LD2  
		atom_lade.get_jt_bg_ladungs().remove(1);
		atom_lade.get_jt_bg_ladungs().add(1, l2a);
		l2a.set_jt_bg_atom(atom_lade);
		// Mengenvorzeichen umkehren
		l2a.setMENGENVORZEICHEN(l2a.getMENGENVORZEICHEN().lValue * -1L);

		// A1-LD2--> A2-->LD1
		atom_ablade.get_jt_bg_ladungs().remove(0);
		atom_ablade.get_jt_bg_ladungs().add(0,l1b);
		l1b.set_jt_bg_atom(atom_ablade);
		// Mengenvorzeichen umkehren
		l1b.setMENGENVORZEICHEN(l1b.getMENGENVORZEICHEN().lValue * -1L);

		
		// neuer Strecken-Stations-Satz generieren, nur im 1. Satz, da dieser dann automatisch mit dem 2. Satz gelinkt wird über SEQ-CURRVAL
		l2a.set_jt_bg_station(new jt_bg_station(this, l2a));
		l1b.set_jt_bg_station(null);
		
		
		
		// Besitzverhältnisse
		Long idAdresseBesitzerStart1;
		Long idAdresseBesitzerZiel1;
		Long idAdresseBesitzerStart2;
		Long idAdresseBesitzerZiel2;
		
		// info über Lade-Sorte lesen:
		Long idArtikel = getArtikelIDFromArtikelBezID(oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel_bez_ek,-1L).toString());
		
		// EIGENWARE/FREMDWARE/LEERGUT
		if (oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag) != null){
					
			// bei Fremdauftrag immer der Auftraggeber
			idAdresseBesitzerStart1 = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag);
			idAdresseBesitzerZiel1 	= idAdresseBesitzerStart1;
			idAdresseBesitzerStart2 = idAdresseBesitzerStart1;
			idAdresseBesitzerZiel2 	= idAdresseBesitzerStart1;
			
		} else {
			String sIDArtikel = idArtikel != null ? idArtikel.toString() : "-1";
			if (oFuhre.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung) ){
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					idAdresseBesitzerStart1 = m_IDAdresseMandant;
					idAdresseBesitzerZiel1 	= idAdresseBesitzerStart1;
					idAdresseBesitzerStart2 = idAdresseBesitzerStart1;
					idAdresseBesitzerZiel2 	= idAdresseBesitzerStart1;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					idAdresseBesitzerStart1 = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start)  ;
					idAdresseBesitzerZiel1 	= idAdresseBesitzerStart1;
					idAdresseBesitzerStart2 = idAdresseBesitzerStart1;
					idAdresseBesitzerZiel2 	= idAdresseBesitzerStart1;
				}
			} else {
				// Eigenware
				idAdresseBesitzerStart1 = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start)  ;
				idAdresseBesitzerZiel1 	= m_IDAdresseMandant;
				idAdresseBesitzerStart2 = m_IDAdresseMandant;
				idAdresseBesitzerZiel2 	= oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_ziel)  ;
			}
		}
		
		
		// Standard-Besitzer der Strecke definieren. (Besitzer im Streckenlager ist der Mandant)
		atom_lade.get_jt_bg_ladungs().firstElement().setID_ADRESSE_BESITZER(idAdresseBesitzerStart1);
		atom_lade.get_jt_bg_ladungs().lastElement().setID_ADRESSE_BESITZER(idAdresseBesitzerZiel1);
		
		atom_ablade.get_jt_bg_ladungs().firstElement().setID_ADRESSE_BESITZER(idAdresseBesitzerStart2);
		atom_ablade.get_jt_bg_ladungs().lastElement().setID_ADRESSE_BESITZER(idAdresseBesitzerZiel2);

	}
	

	public void fuhrenort_to_strecke(Rec20 oFuhre, Rec20 ort, jt_bg_vektor vektor, jt_bg_atom atom_ort ) throws myException{
		
		// Struktur: 		S1-L1-A-L2-S2
		// wird zu (links)	S1-L1-A-L1-Sstrecke
		// oder	  (rechts)	Sstrecke-L2-A-L2-S2
		//
		boolean blinks = atom_ort.getEN_ATOM_POS_IN_BG().ValuePlain().equals(EN_ATOM_POS_IN_BG.LEFT.dbVal());
		jt_bg_ladung ldStrecke;
		jt_bg_ladung ldOrt;
		
		if (blinks){
			// die linke Ladung kopieren und umhängen
			ldStrecke = new jt_bg_ladung(atom_ort.get_jt_bg_ladungs().get(0));
			ldOrt = atom_ort.get_jt_bg_ladungs().get(0);
			atom_ort.get_jt_bg_ladungs().remove(1);
			atom_ort.get_jt_bg_ladungs().add(1, ldStrecke);
		} else {
			// die rechte Ladung kopieren und umhängen
			ldStrecke = new jt_bg_ladung(atom_ort.get_jt_bg_ladungs().get(1));
			ldOrt = atom_ort.get_jt_bg_ladungs().get(1);
			atom_ort.get_jt_bg_ladungs().remove(0);
			atom_ort.get_jt_bg_ladungs().add(0, ldStrecke);
		}

		ldStrecke.set_jt_bg_atom(atom_ort);
		ldStrecke.setMENGENVORZEICHEN(ldStrecke.getMENGENVORZEICHEN().lValue*-1L);
		
		jt_bg_station stStrecke = new jt_bg_station(this, ldStrecke);
		ldStrecke.set_jt_bg_station(stStrecke);

		
		// Besitzverhältnisse
		Long idAdresseBesitzerOrt;
		Long idAdresseBesitzerStrecke;

		
		// info über Lade-Sorte lesen:
		Long idArtikel = getArtikelIDFromArtikelBezID(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_artikel_bez,"-1") );
		
		// EIGENWARE/FREMDWARE/LEERGUT
		if (oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag) != null){
					
			// bei Fremdauftrag immer der Auftraggeber
			idAdresseBesitzerOrt = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag);
			idAdresseBesitzerStrecke 	= idAdresseBesitzerOrt;
			
		} else {
			String sIDArtikel = idArtikel != null ? idArtikel.toString() : "-1";
			if (oFuhre.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung)  ){
				if (m_vLeergutartikel.contains(sIDArtikel)){
					//Leergutstellung
					idAdresseBesitzerOrt = m_IDAdresseMandant;
					idAdresseBesitzerStrecke 	= idAdresseBesitzerOrt;
				} else {
					// Fremdware aufgrund "Fuhre ohne Abrechnung"
					// Besitzer ist Kunde beim Start der Fuhre
					idAdresseBesitzerOrt = ort.get_raw_resultValue_Long( VPOS_TPA_FUHRE_ORT.id_adresse);
					idAdresseBesitzerStrecke 	= idAdresseBesitzerOrt;
				}
			} else {
				// Eigenware
				idAdresseBesitzerOrt = ort.get_raw_resultValue_Long( VPOS_TPA_FUHRE_ORT.id_adresse);
				idAdresseBesitzerStrecke 	= m_IDAdresseMandant;
			}
		}
		
		
		// Standard-Besitzer der Strecke definieren. (Besitzer im Streckenlager ist der Mandant)
		ldStrecke.setID_ADRESSE_BESITZER(idAdresseBesitzerStrecke);
		ldOrt.setID_ADRESSE_BESITZER(idAdresseBesitzerOrt);
		
	}

	
	
}
