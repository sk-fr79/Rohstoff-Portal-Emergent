package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import org.apache.xmlbeans.impl.jam.mutable.MElement;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bibSES_keys4save;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.GROESSEN_UMRECHNUNGEN.GROESSEN_Umrechnung;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;


public abstract class Bewegung_Fuhren_Transformation {

//	int m_typ_strecke_lager_mixed = -1;
//	EN_VEKTOR_TYP m_vektor_typ = EN_VEKTOR_TYP.WE;
	
	MyDBToolBox m_MyDBToolbox = null;
	
	
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
	
	
	// der Hauptsatz
	jt_bg_vektor     m_BG_Vektor = null;

	// cache-Objekte
	conv_helper		m_helper = null;

	
	// eigene Mandanten-Adresse
	Long m_IDAdresseMandant = null;
	Long m_IDAdresseBesitzerFuhre = null;
	Long m_IDAdresseBesitzerOrt = null;
	

	EnTransportTyp m_transportTyp = null;
	
	/**
	 * Default-Konstruktor
	 * @author manfred
	 * @throws myException 
	 * @date   08.08.2012
	 */
	public Bewegung_Fuhren_Transformation( conv_helper helper ) throws myException {
		super();
		m_helper = helper;

		// die eigene AdressID ermitteln
		m_IDAdresseMandant = Long.parseLong( bibALL.get_ID_ADRESS_MANDANT() );
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
	public abstract Vector<jt_bg_vektor> transformiereFuhre(Rec21_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException;	
	

	
	
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
	 * Ermittelt die ZolltarifID aus der Zolltarifnummer
	 * @author manfred
	 * @date 09.07.2019
	 *
	 * @param Zolltarifnummer
	 * @return
	 */
	protected Long getIDZolltarifnummer(String Zolltarifnummer){
		Long lID = null;
		
		if (Zolltarifnummer != null && m_helper.m_hmZolltarifnummern.containsKey(Zolltarifnummer.trim())){
			lID = m_helper.m_hmZolltarifnummern.get(Zolltarifnummer.trim());
		} 
		
		return lID;
	}
	
	
	

	
	
	
	
	
	/**
	 * Aufbereiten der Fuhre und deren Orte um die verschiedenen Fälle zu betrachten
	 * @author manfred
	 * @date   06.08.2012
	 * @throws myException
	 */
	protected void	initFuhre(Rec21_VPOS_TPA_FUHRE_ext oRecFuhre) throws myException{
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
		if (m_helper.m_vEigeneLieferadressen.contains(m_oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_start, "-") ) ){
			lager_links++;
			fuhre_lager_links++;
		} else {
			kunde_links++;
			fuhre_kunde_links++;
		}
		
		if (m_helper.m_vEigeneLieferadressen.contains(m_oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_adresse_lager_ziel, "-")) ){
			lager_rechts++;
			fuhre_lager_rechts++;
		} else {
			kunde_rechts++;
			fuhre_kunde_rechts++;
		}
		
		
		// Fuhrenorte untersuchen
		if (m_rlOrt != null){
			for (Rec20 o: m_rlOrt.values()){
				
				if (m_helper.m_vEigeneLieferadressen.contains(o.get_fs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "-") ) ){
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
	protected jt_bg_vektor generiereVectorAusFuhre_Basisdaten(Rec21_VPOS_TPA_FUHRE_ext oFuhre) throws myException{
	
		// Vektor erzeugen und mit den Basisdaten initialisieren
		jt_bg_vektor oVek = new jt_bg_vektor(this,oFuhre,m_helper);
		
		// vektor-Fuhre verbindung erzeugen
		jt_bg_vektor_konvert oKonvert = new jt_bg_vektor_konvert(oFuhre,m_helper).setBG_VEKTOR(oVek);
		oVek.set_jt_bg_vektor_konvert(oKonvert);
		
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
		
//		oVek.setEN_VEKTOR_WARENKLASSE(Warenklasse);
		
		return oVek;
	}
	
	/**
	 * füllt die Basisdaten, für einen Fuhrenort
	 * @author manfred
	 * @date   01.08.2012
	 * @param oFuhre
	 * @return
	 * @throws myException
	 */
	protected jt_bg_vektor generiereVectorAusFuhre_Basisdaten(Rec21_VPOS_TPA_FUHRE_ext oFuhre, Rec21_VPOS_TPA_FUHRE_ORT_ext oOrt) throws myException{
	
		// Vektor erzeugen und mit den Basisdaten initialisieren
		jt_bg_vektor oVek = new jt_bg_vektor(this,oFuhre,oOrt,m_helper);
		
		// vektor-Fuhre verbindung erzeugen
		jt_bg_vektor_konvert oKonvert = new jt_bg_vektor_konvert(oOrt,m_helper).setBG_VEKTOR(oVek);
		oVek.set_jt_bg_vektor_konvert(oKonvert);
		
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
		
//		oVek.setEN_VEKTOR_WARENKLASSE(Warenklasse);
		
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
	protected void generiere_atom_Hauptfuhre (jt_bg_vektor oVek, Rec21_VPOS_TPA_FUHRE_ext oFuhre) throws myException{

		jt_bg_atom atom1 = new jt_bg_atom(this,oVek,oFuhre, EnTabKeyInMask.A1,m_helper);
		jt_bg_atom atom2 = new jt_bg_atom(this,oVek,oFuhre, EnTabKeyInMask.A2,m_helper);
		
		//
		// atomdaten setzen
		//
		
		// Start-Station
		jt_bg_station station_start = new jt_bg_station(this, oFuhre, true,m_helper);
		station_start.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		atom1.set_jt_bg_station_quelle(station_start);
		
				
		// Ziel-Station 
		jt_bg_station station_ziel = new jt_bg_station(this, oFuhre, false,m_helper);
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
		
//		station_zw.setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE_longValue());
		
		station_zw.setID_ADRESSE_BASIS(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
		station_zw.setID_LAND(getLandIDFromLaendercode(bibSES.get_LAENDERCODE_MANDANT()));
		station_zw.setPLZ("-");
		station_zw.setNAME1(m_transportTyp.name());
		
		atom1.set_jt_bg_station_ziel(station_zw);
		atom2.set_jt_bg_station_quelle(station_zw);
				
		//
		station_start.setPOS_IN_MAKS(EnTabKeyInMask.S1.dbVal());
		station_zw.setPOS_IN_MAKS(EnTabKeyInMask.S2.dbVal());
		station_ziel.setPOS_IN_MAKS(EnTabKeyInMask.S3.dbVal());
		
		
		
//		// Sorte
//		// info über neue Sorte lesen:
		Long idArtikel = m_helper.getArtikelIDFromArtikelBezID(oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_ek,"-1") );
		
		// Besitzverhältnisse
		Long IDAdresseBesitzerFuhreStart = null;
		Long IDAdresseBesitzerFuhreZiel = null;
		
		// EIGENWARE/FREMDWARE/LEERGUT
		if (oFuhre.get_fs_dbVal(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)   != null){
			// bei Fremdauftrag immer der Auftraggeber
			IDAdresseBesitzerFuhreStart = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag);
			IDAdresseBesitzerFuhreZiel = IDAdresseBesitzerFuhreStart;
		} else {
			String sIDArtikel = idArtikel != null ? idArtikel.toString() : "-1";
			if (oFuhre.is_yes_db_val(VPOS_TPA_FUHRE.ohne_abrechnung)  ){
				if (m_helper.m_vLeergut_Artikel.contains(sIDArtikel)){
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
		
		
		
//		//TEST--- Besitzer-übergang bei 2. Atom
		atom1.get_jt_bg_station_quelle().setID_ADRESSE_BESITZER_LDG(IDAdresseBesitzerFuhreStart);
		atom1.get_jt_bg_station_ziel().setID_ADRESSE_BESITZER_LDG(IDAdresseBesitzerFuhreStart);
		
		atom2.get_jt_bg_station_quelle().setID_ADRESSE_BESITZER_LDG(IDAdresseBesitzerFuhreStart);
		atom2.get_jt_bg_station_ziel().setID_ADRESSE_BESITZER_LDG(IDAdresseBesitzerFuhreZiel);
		
		
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
		
		
		// Storno
		
		// sonstiges
//		oAtom.setPreisermittlung(null);
//		oAtom.setSetzkastenKomplett(null);

		
	}

	
	
	
	
	/**
	 *
	 * @author manfred
	 *
	 * @param vektor
	 * @param oFuhre
	 * @param oOrt
	 * @return
	 * @throws myException
	 */
	protected void generiereAtome_FuhrenORT (jt_bg_vektor vektor, Rec21_VPOS_TPA_FUHRE_ext oFuhre, Rec21_VPOS_TPA_FUHRE_ORT_ext oOrt ) throws myException{
		
		boolean bIstLadeseite = oOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"-").equals("EK");
		//
		// Bewegungsatome
		//
		jt_bg_atom atom1 ;
		jt_bg_atom atom2 ;

		jt_bg_station station_start;
		jt_bg_station station_ziel;
		
		if(bIstLadeseite) {
			atom1 = new jt_bg_atom(this,vektor,oOrt,oFuhre, EnTabKeyInMask.A1, m_helper );
			atom2 = new jt_bg_atom(this,vektor,oOrt,oFuhre, EnTabKeyInMask.A2, m_helper);
			// Start-Station
			station_start = new jt_bg_station(this,oOrt,oFuhre, m_helper); 
			station_start.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
			atom1.set_jt_bg_station_quelle(station_start);
			// Ziel-Station 
			station_ziel = new jt_bg_station(this, oFuhre, false, m_helper);
			station_ziel.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
			atom2.set_jt_bg_station_ziel(station_ziel);
		} else {
			atom1 = new jt_bg_atom(this,vektor,oOrt, oFuhre, EnTabKeyInMask.A1, m_helper);
			atom2 = new jt_bg_atom(this,vektor,oOrt, oFuhre, EnTabKeyInMask.A2, m_helper);
			
			// Start-Station
			station_start = new jt_bg_station(this, oFuhre, true, m_helper);
			station_start.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
			atom1.set_jt_bg_station_quelle(station_start);
			
			// Ziel-Station 
			station_ziel = new jt_bg_station(this,oOrt,oFuhre, m_helper); 
			station_ziel.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
			atom2.set_jt_bg_station_ziel(station_ziel);
		}
		
		
		// Zwischenstation 
		jt_bg_station station_zw = new jt_bg_station(m_helper);
		
		station_zw.setID_BG_STATION(BG_STATION._tab().seq_nextval());
		station_zw.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		station_zw.setID_MANDANT(station_start.getID_MANDANT().lValue);
		station_zw.setERZEUGT_AM(station_start.getERZEUGT_AM().dValue);
		station_zw.setERZEUGT_VON(station_start.getERZEUGT_VON().sValue);
		station_zw.setGEAENDERT_VON(station_start.getGEAENDERT_VON().sValue);
		station_zw.setLETZTE_AENDERUNG(station_start.getLETZTE_AENDERUNG().dValue);
		
//		station_zw.setID_ADRESSE(bibSES.get_ID_ADRESSE_LAGER_ZWISCHENLAGER_WE_longValue());
		
		station_zw.setID_ADRESSE_BASIS(Long.parseLong(bibALL.get_ID_ADRESS_MANDANT()));
		station_zw.setID_LAND(getLandIDFromLaendercode(bibSES.get_LAENDERCODE_MANDANT()));
		station_zw.setPLZ("-");
		station_zw.setNAME1(m_transportTyp.name());
		
		atom1.set_jt_bg_station_ziel(station_zw);
		atom2.set_jt_bg_station_quelle(station_zw);
				
		//
		station_start.setPOS_IN_MAKS(EnTabKeyInMask.S1.dbVal());
		station_zw.setPOS_IN_MAKS(EnTabKeyInMask.S2.dbVal());
		station_ziel.setPOS_IN_MAKS(EnTabKeyInMask.S3.dbVal());
		
		
			
		// datum der Ausführung ist das Datum des Orts
//		MyDate d = oOrt.get_myDate_dbVal(VPOS_TPA_FUHRE_ORT.datum_lade_ablade);
//		atom.setDATUM_AUSFUEHRUNG(d!= null ? bibDate.String2Date(d.get_cDBFormatErgebnis()) : null ) ;
//		atom.setDATUM_AUSFUEHRUNG(bibDate.String2Date(oOrt.get_myDate_dbVal(VPOS_TPA_FUHRE_ORT.datum_lade_ablade).get_cDBFormatErgebnis()));
		
		
//		// Sorte ist sorte der Ladeseite, da es nur um die Erkennung der Leergutartikel geht.
//		Long idArtikel = getArtikelIDFromArtikelBezID(oOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_artikel_bez,"-1"));
//
//		// EIGENWARE/FREMDWARE/LEERGUT
//		if (oFuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_adresse_fremdauftrag)  != null){
//			// bei Fremdauftrag immer der Auftraggeber
//			m_IDAdresseBesitzerFuhre = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag);
//			m_IDAdresseBesitzerOrt = m_IDAdresseBesitzerFuhre;
//		} else {
//			if (oOrt.is_yes_db_val(VPOS_TPA_FUHRE_ORT.ohne_abrechnung) ){
//				String sIDArtikel = oOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_artikel, "-1");
//				if (m_helper.m_vLeergut_Artikel.contains(sIDArtikel)){
//					//Leergutstellung
//					m_IDAdresseBesitzerFuhre = m_IDAdresseMandant;
//					m_IDAdresseBesitzerOrt = m_IDAdresseMandant;
//				} else {
//					// Fremdware aufgrund "Fuhre ohne Abrechnung"
//					// Besitzer ist Kunde beim Start der Fuhre
//					m_IDAdresseBesitzerFuhre = oOrt.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse);
//					m_IDAdresseBesitzerOrt = m_IDAdresseBesitzerFuhre;
//				}
//			} else {
//				// Eigenware / nur abrechenbar, wenn der Ort ein Kunde ist....
//				m_IDAdresseBesitzerOrt = oOrt.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse);;
//				
//				if( !m_helper.m_vEigeneLieferadressen.contains(oOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_adresse_lager, "*"))){
//					if (bIstLadeseite){
//						m_IDAdresseBesitzerFuhre = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_ziel);
//					}	else {
//						m_IDAdresseBesitzerFuhre = oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start);
//					}
//				} else {
//					m_IDAdresseBesitzerFuhre = m_IDAdresseMandant;
//				}
//			}
//		}
		
		
		
		// abrechenbar immer, wenn besitzerwechsel stattfindet
//		ladung_fuhre.setID_ADRESSE_BESITZER(m_IDAdresseBesitzerFuhre);
//		ladung_ort.setID_ADRESSE_BESITZER(m_IDAdresseBesitzerOrt);

		//Daten aus der Rechnungsposition zusätzlich noch setzen, falls die Ladeseite kein Lager ist
//		int nLagervorzeichen = m_vLagerOrte.contains(m_oFuhre.get_ID_ADRESSE_LAGER_START_cUF_NN("-")) ? -1: 1 ;
		
		
//		//Daten aus der Rechnungsposition
//		setRGValuesInAtomFromFuhreOrt(atom, oOrt);
		
//		return atom;
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
	protected void setRGValuesInAtomFromFuhre(jt_bg_vektor vektor, Rec21 oFuhre,  EnTabKeyInMask pos) throws myException {
		int 	nLagervorzeichen = -1;
				
		jt_bg_atom atom = null;
		
		if (pos.equals(EnTabKeyInMask.A1) ) {
			atom = vektor.getATOM_1();
			nLagervorzeichen = 1;
		} else if (pos.equals(EnTabKeyInMask.A2)) {
			atom = vektor.getATOM_2();
			nLagervorzeichen = -1;
		} else {
			throw new myException("fehlerhafter Stationstyp");
		};
		
		
		RecList21 lVposRg = null;
		try {
			
			SqlStringExtended s = new SqlStringExtended("select * from " + _TAB.vpos_rg.fullTableName() + " WHERE "
					+ " ID_VPOS_RG_STORNO_VORGAENGER is null " +
					" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
				   	" AND NVL(DELETED,'N') = 'N' " +
				   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = ? "  +    
				   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL " + 
				   	" AND LAGER_VORZEICHEN = ? "   
					);
			
					s.getValuesList().add(new Param_Long(oFuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre) ) );
					s.getValuesList().add(new Param_Long(nLagervorzeichen) );
					
			lVposRg = new RecList21(_TAB.vpos_rg)._fill(s);		
			
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
		
//		// Alternative Lieferbedingung aus dem Fuhrenort setzen
//		String sLieferbedingung =oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.lieferbed_alternativ) ; 
//		
//		// zuerst die Lieferbedingungen im Atom aus der Fuhre setzen 
//		setIDLieferbedingungInAtom(atom, sLieferbedingung);
//		
//
//		RecList21 lVposRg = null;
//		
//		SqlStringExtended sqlExt = new SqlStringExtended("SELECT * FROM  " + VPOS_RG.fullTabName() + " WHERE" +
//				" ID_VPOS_RG_STORNO_VORGAENGER is null " +
//				" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
//			   	" AND NVL(DELETED,'N') = 'N' " +
//			   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = ?" + //oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre) +
//			   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD = ?" //+ oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort)
//			   	
//			   	);
//		sqlExt.getValuesList().add( new Param_Long("ID_FUHRE_ZUGEORDNET", Long.parseLong(oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre)) ));
//		sqlExt.getValuesList().add( new Param_Long("ID_FUHRE_ORT_ZUGEORDNET", Long.parseLong(oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort)) ));
//		
//		
//		try {
////			lVposRg = new RecList21(_TAB.vpos_rg)._fill(  	" ID_VPOS_RG_STORNO_VORGAENGER is null " +
////															" AND ID_VPOS_RG_STORNO_NACHFOLGER is null " +
////														   	" AND NVL(DELETED,'N') = 'N' " +
////														   	" AND ID_VPOS_TPA_FUHRE_ZUGEORD = " + oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre) +
////														   	" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD = " + oFuhreOrt.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort) 
////														,"");
//			
//			lVposRg = new RecList21(_TAB.vpos_rg)._fill(sqlExt);
//
//			// Werte setzen wenn eine RG-Position gefunden wurde
//			if (lVposRg.size() > 0){
//				Rec21 rVposRg = lVposRg.get(0);
//				setRGValuesInAtom( atom, rVposRg );
//			}
//			
//		} catch (myException e) {
//			throw new myException("setRGValuesInAtomFromFuhreOrt::Rechnungsposition konnte nicht ermittelt werden", e);
//		}
//		
		
		
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
				atom.setID_ZAHLUNGSBEDINGUNGEN(oVposRg.get_raw_resultValue_Long(VPOS_RG.id_zahlungsbedingungen ));
				
				// die Lieferbedingung zuerst aus der Fuhre (also aus dem Atom) lesen, wenn nicht vorhanden, dann aus der Rechnungsposition
				String sLieferbedingung = atom.getLIEFERBEDINGUNGEN().sValue;
				if (bibALL.isEmpty(sLieferbedingung) ){
					// falls noch keine Lieferbedingung im Atom eingetragen war, die Lieferbedingung aus der Rechnungsposition eintragen
					sLieferbedingung =  oVposRg.get_ufs_dbVal(VPOS_RG.lieferbedingungen) ; 
					setIDLieferbedingungInAtom(atom, sLieferbedingung);
				}
				
				Long id_waehrung_fremd =oVposRg.get_raw_resultValue_Long(VPOS_RG.id_waehrung_fremd);
				if(id_waehrung_fremd != null) {
					atom.setID_WAEHRUNG(id_waehrung_fremd);
				}
				
				// Basis-Währung
				atom.setE_PREIS_BASISWAEHRUNG(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.epreis_result_netto_mge,null) ) ;
				atom.setE_PREIS_RES_NETTO_MGE_BASIS(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.epreis_result_netto_mge,null) );
					
				atom.setG_PREIS_BASISWAEHRUNG(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gesamtpreis,null)) ;
				atom.setG_PREIS_ABZUG_BASIS(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gpreis_abz_mge,null));

				// FW
				atom.setE_PREIS_FREMDWAEHRUNG(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.epreis_result_netto_mge_fw,null));
				atom.setE_PREIS_RES_NETTO_MGE_FREMD(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.epreis_result_netto_mge_fw,null) );
				
				atom.setG_PREIS_FREMDWAEHRUNG(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gesamtpreis_fw,null)) ;
				atom.setG_PREIS_ABZUG_FREMD(oVposRg.get_raw_resultValue_bigDecimal(VPOS_RG.gpreis_abz_mge_fw,null));
			}
	}
	
	
	
	
	/**
	 * TO BE overwritten!!
	 * Ermittelt für alle Ladungen die Kunden-Preise und versucht diese auf die jeweils gegenüberliegende Lager-seite zu übertragen, wenn dort keine Preise vorhanden sind. 
	 * @author manfred
	 */
	protected void checkAndCompletePriceEntries(Vector<jt_bg_vektor> vVektor){
		return;
	}
	
	
	
	/**
	 * Setzt die Lieferbedingung in die ID um
	 * @param atom
	 * @param sLieferbedingung
	 */
	private void setIDLieferbedingungInAtom(jt_bg_atom atom, String sLieferbedingung){
		// jetzt die ID aus der Lieferbedingung ermitteln
		if (sLieferbedingung != null){
			sLieferbedingung = sLieferbedingung.trim().toUpperCase();

			atom.setLIEFERBEDINGUNGEN(sLieferbedingung);
			atom.setID_LIEFERBEDINGUNGEN(m_helper.getIDLieferbedingungenFrom(sLieferbedingung));
			
		}
	}
	



	/**
	 * 
	 * 
	 *  Methoden fuer die Preisermittlungen der LL-Fuhren Bzw. LL-Bereiche der Mixed-fuhren
	 *  
	 *  
	 *  
	 */

	
	// Preisfindung
	protected BigDecimal bdPreisHauptsorte = BigDecimal.ZERO;
	protected String sIDArtikelHauptsorte = "";
	protected HashMap<String, BigDecimal> hmSortenpreise = new HashMap<String, BigDecimal>();

	
	
	/**
	 * Liest die Lagerpreise einmal für die Fuhre
	 * @author manfred
	 * @date 09.02.2018
	 *
	 * @param atom
	 * @throws myException
	 */
	protected void initLagerpreise(String sIDFuhre) throws myException{
		
		Reclist21_LAGER_KONTO_ext list  = null;
		
		// WA-Preise in der Lager-Konto-Liste lesen, falls es welche gibt, sind diese dort schon richtig ermittelt
		list = new Reclist21_LAGER_KONTO_ext(sIDFuhre,true,false); 
		
		bdPreisHauptsorte = BigDecimal.ZERO;
		
		
		// für die Lager/Lager Preise sind nur die WE-Preise relevant, da diese auch so wieder eingebucht werden müssen 
	    if (list.size() == 1 ){
	    	bdPreisHauptsorte = list.get(0).get_raw_resultValue_bigDecimal(LAGER_KONTO.preis, BigDecimal.ZERO);
	    	sIDArtikelHauptsorte = list.get(0).get_ufs_dbVal(LAGER_KONTO.id_artikel_sorte, "");
	    } 
	    
	    // falls kein Preis für die Hauptsorte ermittelt wurde: 
	    if (list.size() > 1 || bdPreisHauptsorte.equals(BigDecimal.ZERO)) {
	    
	    	//den Preis der Hauptfuhre
			for (Rec21 rec : list.values()){
				
				BigDecimal bdPreisTemp = rec.get_raw_resultValue_bigDecimal(LAGER_KONTO.preis,BigDecimal.ZERO);
				String sIDSorte = rec.get_ufs_dbVal(LAGER_KONTO.id_artikel_sorte, "");
				
				if (bdPreisTemp.equals(BigDecimal.ZERO)) {
					bdPreisTemp = getLagerPreisAvg(rec.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager),
												   rec.get_raw_resultValue_Long(LAGER_KONTO.id_artikel_sorte),
												   rec.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum));
					
				}
				
								
				if (rec.get_ufs_dbVal(LAGER_KONTO.id_vpos_tpa_fuhre_ort) == null){
					bdPreisHauptsorte = bdPreisTemp;
					sIDArtikelHauptsorte = sIDSorte;
				}  
			
				hmSortenpreise.put(sIDSorte, bdPreisTemp);
				
			}
	    }
	}

	/**
	 * ermittelt den Durchschnittspreis von WE-Positionen eines Lagers und Sorte für einen bestimmten Zeitraum
	 * 
	 * @author manfred
	 * @date 05.11.2019
	 *
	 * @param idLager
	 * @param idSorte
	 * @param dateMaxISO
	 * @return
	 */
	protected BigDecimal getLagerPreisAvg(Long idLager, Long idSorte, Date dateMaxISO) {
		
		String sql = "SELECT AVG(PREIS) FROM ( " +
						" SELECT K.PREIS FROM  " +
						" JT_LAGER_KONTO K " +
						" WHERE NVL(K.STORNO ,'N') = 'N' " +
						" AND K.BUCHUNGSTYP='WE' " +
						" AND NVL(K.PREIS,0) > 0 " +
						" AND K.ID_ADRESSE_LAGER = ? " +
						" AND K.ID_ARTIKEL_SORTE = ? " +
						" AND K.BUCHUNGSDATUM <= ? " +
						" ORDER BY K.BUCHUNGSDATUM DESC " +
				" ) WHERE ROWNUM < 10";
				
		SqlStringExtended sqlExt = new SqlStringExtended(sql);
		sqlExt.getValuesList().add(new Param_Long(idLager));
		sqlExt.getValuesList().add(new Param_Long(idSorte));
		sqlExt.getValuesList().add(new Param_Date(dateMaxISO));
		
		String [][] cDaten = new String[0][0];
		cDaten =  bibDB.EinzelAbfrageInArray(sqlExt,(String)null);
		
		BigDecimal bdRet = BigDecimal.ZERO;
		if (cDaten.length> 0 && cDaten[0][0]!= null) {
			bdRet = new BigDecimal(cDaten[0][0]);
		}
		return bdRet;
		
	}
	
	
	
	
	
	
	/**
	 * Bei der Konvertierung muss der LL-Preis noch ermittelt werden (Hier aus der Lagerliste,
	 * da dort schon die Preisermittlung durchgeführt wurde)
	 * @author manfred
	 * @date   
	 * @param bewegung
	 */
	protected void setzeLagerPreise(Vector<jt_bg_vektor> vVektor){
		
		// alle Vektoren durchlaufen um Preise beim Ausgangslager zu finden 
		for (jt_bg_vektor v: vVektor ){
			
			jt_bg_atom atom_start = v.getATOM_1();
			jt_bg_atom atom_ziel = v.getATOM_2();

			// relevantes Atom merken
			jt_bg_atom atom_preis = atom_start;
			
			if(atom_start == null || atom_ziel == null){
				// weiter zum nächsten Vektor
				break;
			}
			
			
			// der Relevante Artikel der auf der Ladeseite
			String sIDArtikel = atom_start.getID_ARTIKEL().ValuePlain();
			boolean bIstOrt = v.get_jt_bg_vektor_konvert().getID_VPOS_TPA_FUHRE_ORT() != null;
			
			if (sIDArtikel != null){
				if (atom_preis.getE_PREIS_BASISWAEHRUNG().bdValue == null || atom_preis.getE_PREIS_BASISWAEHRUNG().bdValue.equals(BigDecimal.ZERO) ){
					
					if (atom_preis.getID_ARTIKEL().ValuePlain().equals(sIDArtikelHauptsorte)){
						atom_start.setE_PREIS_BASISWAEHRUNG(bdPreisHauptsorte);
						atom_start.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreisHauptsorte);
						atom_ziel.setE_PREIS_BASISWAEHRUNG(bdPreisHauptsorte);
						atom_ziel.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreisHauptsorte);
						
					} else {
						BigDecimal bdPreis = BigDecimal.ZERO;
						// prüfen, ob die Sorte noch irgendwo vorhanden ist
						if (hmSortenpreise.containsKey(sIDArtikel)){
							bdPreis =hmSortenpreise.get(sIDArtikel);
						} else {
							// sonst doch den Preis der Hauptsorte nehmen
							bdPreis = bdPreisHauptsorte;
						}
						
						atom_start.setE_PREIS_BASISWAEHRUNG(bdPreis);
						atom_start.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreis);
						atom_ziel.setE_PREIS_BASISWAEHRUNG(bdPreis);
						atom_ziel.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreis);
					}
 
				} else {
					
				}
						BigDecimal bdPreis = atom_preis.getE_PREIS_BASISWAEHRUNG().bdValue;
					
						atom_ziel.setE_PREIS_BASISWAEHRUNG(bdPreis);
						atom_ziel.setE_PREIS_RES_NETTO_MGE_BASIS(bdPreis);
					}
				 
			}
			
			
			
		
	}

	
	
}
