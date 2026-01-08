/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_Lager_Konto;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOBigDecimal;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.REC_Base;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

/**
 * @author manfred
 *
 */
public class jt_bg_vektor extends REC_Base {
	
//	Vector<DataObject>    m_vDataObjects = new Vector<>();
	
	private DOString 	ID_BG_VEKTOR = new DOString(BG_VEKTOR.id_bg_vektor,null,"").setRaw(true);
	
	private DOLong 		ID_MANDANT = new DOLong(BG_VEKTOR.id_mandant);
	private DODate 		LETZTE_AENDERUNG = new DODate(BG_VEKTOR.letzte_aenderung);
	private DOString 	GEAENDERT_VON = new DOString(BG_VEKTOR.geaendert_von);
	private DOString 	ERZEUGT_VON = new DOString(BG_VEKTOR.erzeugt_von);
	private DODate 		ERZEUGT_AM = new DODate(BG_VEKTOR.erzeugt_am);
	
	private DOString     VEKTOR_STATUS 	= new DOString(BG_VEKTOR.en_vektor_status,null,"");
	private DOString     VEKTOR_QUELLE 	= new DOString(BG_VEKTOR.en_vektor_quelle,null,"");
	private DOString     TRANSPORT_TYP 	= new DOString(BG_VEKTOR.en_transport_typ,null,"");
	
	private DOBigDecimal PLANMENGE 			= new DOBigDecimal (BG_VEKTOR.planmenge);
	
	private DOLong		POSNR				= new DOLong(BG_VEKTOR.posnr);
	private DOString 	TIMESTAMP_IN_MASK   = new DOString(BG_VEKTOR.timestamp_in_mask);
	
	private DOString ID_BG_STORNO_INFO 		= new DOString(BG_VEKTOR.id_bg_storno_info,null,"").setRaw(true);
	private DOString ID_BG_DEL_INFO 		= new DOString(BG_VEKTOR.id_bg_del_info,null,"").setRaw(true);
		
	private DOLong ID_BG_VEKTOR_BASE = new DOLong(BG_VEKTOR.id_bg_vektor_base);
	private DOLong ID_LAND_TRANSIT1 = new DOLong(BG_VEKTOR.id_land_transit1);
	private DOLong ID_LAND_TRANSIT2 = new DOLong(BG_VEKTOR.id_land_transit2);
	private DOLong ID_LAND_TRANSIT3 = new DOLong(BG_VEKTOR.id_land_transit3);
	private DOBigDecimal KOSTEN_TRANSPORT = new DOBigDecimal(BG_VEKTOR.kosten_transport);
	private DOLong ID_HANDELSDEF = new DOLong(BG_VEKTOR.id_handelsdef);
	private DOLong ID_ADRESSE_LOGI_START = new DOLong(BG_VEKTOR.id_adresse_logi_start);
	private DOLong ID_ADRESSE_LOGI_ZIEL = new DOLong(BG_VEKTOR.id_adresse_logi_ziel);
	private DOBigDecimal EU_BLATT_MENGE = new DOBigDecimal(BG_VEKTOR.ah7_menge);
	private DOBigDecimal EU_BLATT_VOLUMEN = new DOBigDecimal(BG_VEKTOR.ah7_volumen);
	
	private DOLong ID_ADRESSE_SPEDITION = new DOLong(BG_VEKTOR.id_adresse_spedition);
	private DOLong ID_UMA_KONTRAKT = new DOLong(BG_VEKTOR.id_uma_kontrakt);
	private DOLong ID_TRANSPORTMITTEL = new DOLong(BG_VEKTOR.id_transportmittel);
	//private DOLong ID_BG_PRUEFPORT_GELANGENSBEST = new DOLong(BG_VEKTOR.id_bg_pruefport_gelangensbest);
	private DOLong ID_BG_PRUEFPROT_ABSCHLUSS = new DOLong(BG_VEKTOR.id_bg_pruefprot_abschluss);
	private DOLong ID_VERPACKUNGSART = new DOLong(BG_VEKTOR.id_verpackungsart);
	private DOLong ID_ADRESSE_FREMDWARE = new DOLong(BG_VEKTOR.id_adresse_fremdware);
	private DOLong ID_BG_VEKTOR_SUB = new DOLong(BG_VEKTOR.id_bg_vektor_sub);


	private DOString LIEFERINFO_TPA = new DOString(BG_VEKTOR.lieferinfo_tpa);
	private DOString ORDNUNGSNUMMER_CMR = new DOString(BG_VEKTOR.ordnungsnummer_cmr);
	private DOString TRANSPORTMITTEL = new DOString(BG_VEKTOR.transportmittel);
	private DOString TRANSPORTKENNZEICHEN = new DOString(BG_VEKTOR.transportkennzeichen);
	private DOString ANHAENGERKENNZEICHEN = new DOString(BG_VEKTOR.anhaengerkennzeichen);
	private DOString BUCHUNGSNUMMER = new DOString(BG_VEKTOR.buchungsnummer);
	private DOString PRINT_ANHANG7 = new DOString(BG_VEKTOR.print_anhang7);
	private DOString BEMERKUNG = new DOString(BG_VEKTOR.bemerkung);
	private DOString BEMERKUNG_FUER_KUNDE = new DOString(BG_VEKTOR.bemerkung_fuer_kunde);
	private DOString BEMERKUNG_SACHBEARBEITER = new DOString(BG_VEKTOR.bemerkung_sachbearbeiter);
	private DOString TRANSPORTVERANTWORTUNG = new DOString(BG_VEKTOR.transportverantwortung);

	private DODate AVV_AUSSTELLUNG_DATUM = new DODate(BG_VEKTOR.ah7_ausstellung_datum);
	private DODate DATUM_PLANUNG_VON = new DODate(BG_VEKTOR.datum_planung_von);
	private DODate DATUM_PLANUNG_BIS = new DODate(BG_VEKTOR.datum_planung_bis);

	private DOString EK_VK_ZUORD_ZWANG = new DOString(BG_VEKTOR.ek_vk_zuord_zwang);
	
	
	
	/**
	 *  die Atome des Vektors
	 */
	
	// atome in einem 2er array ablegen
	private jt_bg_atom[] atoms = new jt_bg_atom[2];
	
	// für umbuchungen braucht man 2 Konvert-Vektoren
	private jt_bg_vektor_konvert _jt_bg_vektor_konvert = null;
	private jt_bg_vektor_konvert _jt_bg_vektor_konvert2 = null;
	
	
	// del und storno-INFOS
	private REC_Base     _jt_bg_del_info = null;
	private REC_Base 	 _jt_bg_storno_info = null;
	


	

	/**
	 * @author manfred
	 * @date 16.01.2018
	 *
	 */
	public jt_bg_vektor( conv_helper helper) {
		super(_TAB.bg_vektor);
		this._helper = helper;
		this.initFieldList();
		
		
	}
	
	
	/**
	 * @author manfred
	 * @date 16.01.2018
	 *
	 * @param iD_BG_VEKTOR
	 * @param iD_MANDANT
	 */
	public jt_bg_vektor(String iD_BG_VEKTOR, Long iD_MANDANT, conv_helper helper) {
		this(helper);
		setID_BG_VEKTOR( iD_BG_VEKTOR);
		setID_MANDANT( iD_MANDANT);
	}
	
	
	
	/**
	 * Initialisiern des Vektors mit den Daten der Fuhre
	 * @author manfred
	 *
	 * @param fuhre
	 * @throws myException
	 */
	public jt_bg_vektor( Bewegung_Fuhren_Transformation trans, Rec21_VPOS_TPA_FUHRE_ext fuhre, conv_helper helper) throws myException {
		this(helper);
		
		// Sequenzer setzen
		this.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_nextval() );
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant) );
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));

		setPOSNR(1L);
		setTIMESTAMP_IN_MASK(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.zeitstempel));
		
		
		
		this.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.AKTIV);
		this.setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE.FUHRE);
		
		// wird in den subklassen definiert
//		TRANSPORT_TYP
		
		
//		ID_BG_VEKTOR_BASE
		setID_LAND_TRANSIT1(trans.getLandIDFromLaendercode(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.laendercode_transit1))) ;
		setID_LAND_TRANSIT2(trans.getLandIDFromLaendercode(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.laendercode_transit2))) ;
		setID_LAND_TRANSIT3(trans.getLandIDFromLaendercode(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.laendercode_transit3))) ;
//		ID_BG_STORNO_INFO 
//		ID_BG_DEL_INFO 
//		POSNR 
//		KOSTEN_TRANSPORT 
		
		
		setID_HANDELSDEF( fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_handelsdef));
		setID_ADRESSE_LOGI_START(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_start)); 
		setID_ADRESSE_LOGI_ZIEL(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_ziel));
		setID_ADRESSE_FREMDWARE(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_fremdauftrag));


		setEU_BLATT_MENGE(fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.eu_blatt_menge)); 
		setEU_BLATT_VOLUMEN (fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.eu_blatt_volumen)); 
		setPLANMENGE(fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_planmenge_abn));  

		setID_ADRESSE_SPEDITION(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
		setID_UMA_KONTRAKT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_uma_kontrakt));
		
		
//		setID_TRANSPORTMITTEL(trans.getIDTransportmittelfuhreFromTransportmittel(get_myLong_dbVal(VPOS_TPA_FUHRE.transportmittel)));
//		 
//		ID_BG_PRUEFPORT_GELANGENSBEST 
//		ID_BG_PRUEFPROT_ABSCHLUSS 
		setID_VERPACKUNGSART(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_verpackungsart));

//		 
//		ID_BG_VEKTOR_SUB
		
//		TIMESTAMP_IN_MASK 
		setLIEFERINFO_TPA(fuhre.getUfs(VPOS_TPA_FUHRE.lieferinfo_tpa));
/*
		set (fuhre.getUfs(VPOS_TPA_FUHRE.));
*/		
		setORDNUNGSNUMMER_CMR (fuhre.getUfs(VPOS_TPA_FUHRE.ordnungsnummer_cmr));
		setTRANSPORTMITTEL (fuhre.getUfs(VPOS_TPA_FUHRE.transportmittel));
		setTRANSPORTKENNZEICHEN (fuhre.getUfs(VPOS_TPA_FUHRE.transportkennzeichen));
		setANHAENGERKENNZEICHEN  (fuhre.getUfs(VPOS_TPA_FUHRE.anhaengerkennzeichen));
		setEK_VK_ZUORD_ZWANG (fuhre.getUfs(VPOS_TPA_FUHRE.ek_vk_zuord_zwang));
		setBUCHUNGSNUMMER (fuhre.getUfs(VPOS_TPA_FUHRE.buchungsnr_fuhre));
//		setPRINT_ANHANG7 (fuhre.getUfs(VPOS_TPA_FUHRE.?));
		setBEMERKUNG (fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung));
		setBEMERKUNG_FUER_KUNDE (fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung_fuer_kunde));
		setBEMERKUNG_SACHBEARBEITER (fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung_sachbearbeiter));
		setTRANSPORTVERANTWORTUNG (fuhre.getUfs(VPOS_TPA_FUHRE.tp_verantwortung));
		
		setAVV_AUSSTELLUNG_DATUM (fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.avv_ausstellung_datum));
		setDATUM_PLANUNG_VON (fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.dat_vorgemerkt_fp));
		setDATUM_PLANUNG_BIS (fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.dat_vorgemerkt_ende_fp));


		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre, _helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( fuhre,_helper);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
			
		}
	}


	/**
	 * Initialisiern des Vektors mit den Daten der Fuhre
	 * @author manfred
	 *
	 * @param fuhre
	 * @throws myException
	 */
	public jt_bg_vektor( Bewegung_Fuhren_Transformation trans, Rec21_VPOS_TPA_FUHRE_ext fuhre, Rec21_VPOS_TPA_FUHRE_ORT_ext ort, conv_helper helper) throws myException {
		this(trans,fuhre,helper);

		boolean bIsLadeseite = ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"").equals("EK");
		
		// Sequenzer setzen
		setPOSNR(1L);
		setTIMESTAMP_IN_MASK(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.zeitstempel));
		
		
		
		this.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.AKTIV);
		this.setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE.FUHRE);
		
		setID_LAND_TRANSIT1(trans.getLandIDFromLaendercode(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.laendercode_transit1))) ;
		setID_LAND_TRANSIT2(trans.getLandIDFromLaendercode(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.laendercode_transit2))) ;
		setID_LAND_TRANSIT3(trans.getLandIDFromLaendercode(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.laendercode_transit3))) ;
 
		
		
		setID_HANDELSDEF( ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_handelsdef));
		if (bIsLadeseite) {
			setID_ADRESSE_LOGI_START(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse_lager)); 
			setID_ADRESSE_LOGI_ZIEL(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_ziel));
		} else {
			setID_ADRESSE_LOGI_START(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_start)); 
			setID_ADRESSE_LOGI_ZIEL(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse_lager));
		}
		


		setEU_BLATT_MENGE(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.eu_blatt_menge)); 
		setEU_BLATT_VOLUMEN (ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.eu_blatt_volumen)); 
		setPLANMENGE(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_planmenge));  

		

		setBEMERKUNG (ort.getUfs(VPOS_TPA_FUHRE_ORT.bemerkung));
		setAVV_AUSSTELLUNG_DATUM (ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.avv_ausstellung_datum));
		
		
		if (ort.getUfs(VPOS_TPA_FUHRE_ORT.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( ort,_helper);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
			
		}
	}

	
	
	/**
	 * 
	 * @author manfred
	 * @date 06.11.2019
	 *
	 * @param oLager
	 * @param helper
	 * @throws myException
	*/
	public jt_bg_vektor(Rec21_Lager_Konto oLager, conv_helper helper) throws myException {
		this(helper);
		
		// Sequenzer setzen
		this.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_nextval() );

		setID_MANDANT(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_mandant) );
		setLETZTE_AENDERUNG(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(oLager.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(oLager.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));

		setPOSNR(1L);
		setTIMESTAMP_IN_MASK(oLager.get_ufs_dbVal(LAGER_KONTO.letzte_aenderung));
		setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.AKTIV);
		setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE.LAGER);
//		setBEMERKUNG(oLager.get_ufs_dbVal(LAGER_KONTO.bemerkung));

		setID_ADRESSE_LOGI_START(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager)); 
		setID_ADRESSE_LOGI_ZIEL(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager));

		setPLANMENGE(oLager.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge, new BigDecimal(0L).abs()));

//		setDATUM_PLANUNG_VON (oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.));
//		setDATUM_PLANUNG_BIS (oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.));

		//		ID_ADRESSE_FREMDWARE 
//		ID_BG_VEKTOR_SUB
		
//		TIMESTAMP_IN_MASK 
/*
		set (fuhre.getUfs(VPOS_TPA_FUHRE.));
*/		


		if (oLager.getUfs(LAGER_KONTO.storno,"N").equals("Y")){
			jt_bg_storno_info i = new jt_bg_storno_info( oLager, _helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
	}

	
	
	public jt_bg_vektor setATOM(jt_bg_atom atom, int index){
		if (index >=0  && index <= 1){
			atoms[index] = atom;
		}
		return this;
	}
	
	
	public Vector<jt_bg_atom> getAtoms(){
		return new Vector(Arrays.asList(atoms));
	}
	

	public jt_bg_atom getAtom(int index){
		if (index >=0  && index <= 1){
			return atoms[index];
		}
		return null;
	}
	
	
	
	public jt_bg_atom getATOM_1() {
		return atoms[0];
	}


	public jt_bg_vektor setATOM_1(jt_bg_atom aTOM_1) {
		atoms[0] = aTOM_1;
		return this;
	}

	public jt_bg_atom getATOM_2() {
		return atoms[1];
	}


	public jt_bg_vektor setATOM_2(jt_bg_atom aTOM_2) {
		atoms[1] = aTOM_2;
		return this;
	}
	
	public jt_bg_station getStation1() {
		if (atoms[0] != null) {
			return atoms[0].get_jt_bg_station_quelle();
		} else {
			return null;
		}
	}
	
	public jt_bg_station getStation2() {
		if (atoms[0] != null) {
			return atoms[0].get_jt_bg_station_ziel();
		} else {
			return null;
		}
	}

	public jt_bg_station getStation3() {
		if (atoms[1] != null) {
			return atoms[1].get_jt_bg_station_ziel();
		} else {
			return null;
		}
	}

	public void setStation1(jt_bg_station station) {
		if (atoms[0] != null) {
			atoms[0].set_jt_bg_station_quelle(station);
		}
	}
	
	public void setStation2(jt_bg_station station) {
		if (atoms[0] != null) {
			atoms[0].set_jt_bg_station_ziel(station);
		}
		if (atoms[1] != null) {
			atoms[1].set_jt_bg_station_quelle(station);
		}
	}
	
	public void setStation3(jt_bg_station station) {
		if (atoms[1] != null) {
			atoms[1].set_jt_bg_station_ziel(station);
		}
	}
	
	
	
	public jt_bg_vektor_konvert get_jt_bg_vektor_konvert() {
		return _jt_bg_vektor_konvert;
	}


	public jt_bg_vektor set_jt_bg_vektor_konvert(jt_bg_vektor_konvert _jt_bg_vektor_konvert) {
		this._jt_bg_vektor_konvert = _jt_bg_vektor_konvert;
		return this;

	}

	public jt_bg_vektor_konvert get_jt_bg_vektor_konvert2() {
		return _jt_bg_vektor_konvert2;
	}


	public jt_bg_vektor set_jt_bg_vektor_konvert2(jt_bg_vektor_konvert _jt_bg_vektor_konvert) {
		this._jt_bg_vektor_konvert2 = _jt_bg_vektor_konvert;
		return this;
	}	
	
	
	public DOString getID_BG_VEKTOR() {
		return ID_BG_VEKTOR;
	}
	public jt_bg_vektor setID_BG_VEKTOR(String iD_BG_VEKTOR) {
		ID_BG_VEKTOR.setValue(iD_BG_VEKTOR);
		return this;
	}
	
	
	public jt_bg_vektor set_jt_bg_del_info(jt_bg_del_info delInfo){
		this._jt_bg_del_info = delInfo;
		return this;
	}
	
	public jt_bg_del_info get_jt_bg_del_info(){
		return (jt_bg_del_info)_jt_bg_del_info;
	}
	

	public jt_bg_vektor set_jt_bg_storno_info(jt_bg_storno_info stornoInfo){
		this._jt_bg_storno_info = stornoInfo;
		return this;
	}
	
	public jt_bg_storno_info get_jt_bg_storno_info(){
		return (jt_bg_storno_info)_jt_bg_storno_info;
	}
	
	
	
	public DOLong getID_MANDANT() {
		return ID_MANDANT;
	}
	public jt_bg_vektor setID_MANDANT(Long iD_MANDANT) {
		ID_MANDANT.setValue(iD_MANDANT);
		return this;
	}
	
	public DODate getLETZTE_AENDERUNG() {
		return LETZTE_AENDERUNG;
	}
	public jt_bg_vektor setLETZTE_AENDERUNG(Date lETZTE_AENDERUNG) {
		LETZTE_AENDERUNG.setValue(lETZTE_AENDERUNG);
		return this;
	}
	
	public DOString getGEAENDERT_VON() {
		return GEAENDERT_VON;
	}
	public jt_bg_vektor setGEAENDERT_VON(String gEAENDERT_VON) {
		GEAENDERT_VON.setValue(gEAENDERT_VON);
		return this;
	}
	
	public DOString getERZEUGT_VON() {
		return ERZEUGT_VON;
	}
	public jt_bg_vektor setERZEUGT_VON(String eRZEUGT_VON) {
		ERZEUGT_VON.setValue(eRZEUGT_VON);
		return this;
	}
	
	public DODate getERZEUGT_AM() {
		return ERZEUGT_AM;
	}
	public jt_bg_vektor setERZEUGT_AM(Date eRZEUGT_AM) {
		ERZEUGT_AM.setValue(eRZEUGT_AM);
		return this;
	}
	
	public DOString getTIMESTAMP_IN_MASK() {
		return TIMESTAMP_IN_MASK;
	}
	public jt_bg_vektor setTIMESTAMP_IN_MASK(String tIMESTAMP_IN_MASK) {
		TIMESTAMP_IN_MASK.setValue( tIMESTAMP_IN_MASK);
		return this;
	}
	
	public DOLong getPOSNR() {
		return POSNR;
	}
	public jt_bg_vektor setPOSNR(Long pOSNR) {
		POSNR.setValue( pOSNR);
		return this;
	}
	
	
	public DOString getEN_VEKTOR_QUELLE() {
		return VEKTOR_QUELLE;
	}
	public jt_bg_vektor setEN_VEKTOR_QUELLE(String eN_VEKTOR_QUELLE) {
		VEKTOR_QUELLE.setValue( eN_VEKTOR_QUELLE);
		return this;
	}
	public jt_bg_vektor setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE eN_VEKTOR_QUELLE){
		VEKTOR_QUELLE.setValue(eN_VEKTOR_QUELLE.dbVal());
		return this;
	}
	
	
	
	public DOString getEN_VEKTOR_STATUS() {
		return VEKTOR_STATUS;
	}
	public jt_bg_vektor setEN_VEKTOR_STATUS(String eN_VEKTOR_STATUS) {
		VEKTOR_STATUS.setValue(eN_VEKTOR_STATUS);
		return this;
	}
	
	public jt_bg_vektor setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS eN_VEKTOR_STATUS) {
		VEKTOR_STATUS.setValue(eN_VEKTOR_STATUS.dbVal());
		return this;
	}
	
	
	public DOString getEN_TRANSPORT_TYP() {
		return TRANSPORT_TYP;
	}
	public jt_bg_vektor setEN_TRANSPORT_TYP(String eN_TRANSPORT_TYP) {
		TRANSPORT_TYP.setValue(eN_TRANSPORT_TYP);
		return this;
	}
	public jt_bg_vektor setEN_TRANSPORT_TYP(EnTransportTyp eN_TRANSPORT_TYP) {
		TRANSPORT_TYP.setValue(eN_TRANSPORT_TYP.dbVal());
		return this;
	}


	
	public DOBigDecimal getPLANMENGE() {
		return PLANMENGE;
	}
	public jt_bg_vektor setPLANMENGE(BigDecimal pLANMENGE) {
		BigDecimal bPM = pLANMENGE;
		if (bPM== null) {
			bPM = BigDecimal.ZERO;
		}
		PLANMENGE .setValue( bPM);
		return this;
	}

	public DOLong getID_BG_VEKTOR_BASE(){
		return ID_BG_VEKTOR_BASE;
	}
	public jt_bg_vektor setID_BG_VEKTOR_BASE (Long _ID_BG_VEKTOR_BASE){
		ID_BG_VEKTOR_BASE.setValue(_ID_BG_VEKTOR_BASE);
		return this;
	}

	public DOLong getID_LAND_TRANSIT1(){
		return ID_LAND_TRANSIT1;
	}
	public jt_bg_vektor setID_LAND_TRANSIT1 (Long _ID_LAND_TRANSIT1){
		ID_LAND_TRANSIT1.setValue(_ID_LAND_TRANSIT1);
		return this;
	}

	public DOLong getID_LAND_TRANSIT2(){
		return ID_LAND_TRANSIT2;
	}
	public jt_bg_vektor setID_LAND_TRANSIT2 (Long _ID_LAND_TRANSIT2){
		ID_LAND_TRANSIT2.setValue(_ID_LAND_TRANSIT2);
		return this;
	}

	public DOLong getID_LAND_TRANSIT3(){
		return ID_LAND_TRANSIT3;
	}
	public jt_bg_vektor setID_LAND_TRANSIT3 (Long _ID_LAND_TRANSIT3){
		ID_LAND_TRANSIT3.setValue(_ID_LAND_TRANSIT3);
		return this;
	}

	public DOString getID_BG_STORNO_INFO(){
		return ID_BG_STORNO_INFO;
	}
	public jt_bg_vektor setID_BG_STORNO_INFO (Long _ID_BG_STORNO_INFO){
		ID_BG_STORNO_INFO.setValue(Long.toString(_ID_BG_STORNO_INFO));
		return this;
	}
	public jt_bg_vektor setID_BG_STORNO_INFO (String _ID_BG_STORNO_INFO){
		ID_BG_STORNO_INFO.setValue(_ID_BG_STORNO_INFO);
		return this;
	}
	
	public DOString getID_BG_DEL_INFO(){
		return ID_BG_DEL_INFO;
	}
	
	public jt_bg_vektor setID_BG_DEL_INFO (Long _ID_BG_DEL_INFO){
		ID_BG_DEL_INFO.setValue(Long.toString(_ID_BG_DEL_INFO)) ;
		return this;
	}

	
	public jt_bg_vektor setID_BG_DEL_INFO (String _ID_BG_DEL_INFO){
		ID_BG_DEL_INFO.setValue(_ID_BG_DEL_INFO);
		return this;
	}


	public DOBigDecimal getKOSTEN_TRANSPORT(){
		return KOSTEN_TRANSPORT;
	}
	public jt_bg_vektor setKOSTEN_TRANSPORT (BigDecimal _KOSTEN_TRANSPORT){
		KOSTEN_TRANSPORT.setValue(_KOSTEN_TRANSPORT);
		return this;
	}

	public DOLong getID_HANDELSDEF(){
		return ID_HANDELSDEF;
	}
	public jt_bg_vektor setID_HANDELSDEF (Long _ID_HANDELSDEF){
		ID_HANDELSDEF.setValue(_ID_HANDELSDEF);
		return this;
	}

	public DOLong getID_ADRESSE_LOGI_START(){
		return ID_ADRESSE_LOGI_START;
	}
	public jt_bg_vektor setID_ADRESSE_LOGI_START (Long _ID_ADRESSE_LOGI_START){
		ID_ADRESSE_LOGI_START.setValue(_ID_ADRESSE_LOGI_START);
		return this;
	}

	public DOLong getID_ADRESSE_LOGI_ZIEL(){
		return ID_ADRESSE_LOGI_ZIEL;
	}
	public jt_bg_vektor setID_ADRESSE_LOGI_ZIEL (Long _ID_ADRESSE_LOGI_ZIEL){
		ID_ADRESSE_LOGI_ZIEL.setValue(_ID_ADRESSE_LOGI_ZIEL);
		return this;
	}

	public DOBigDecimal getEU_BLATT_MENGE(){
		return EU_BLATT_MENGE;
	}
	public jt_bg_vektor setEU_BLATT_MENGE (BigDecimal bigDecimal){
		EU_BLATT_MENGE.setValue(bigDecimal);
		return this;
	}

	public DOBigDecimal getEU_BLATT_VOLUMEN(){
		return EU_BLATT_VOLUMEN;
	}
	public jt_bg_vektor setEU_BLATT_VOLUMEN (BigDecimal _EU_BLATT_VOLUMEN){
		EU_BLATT_VOLUMEN.setValue(_EU_BLATT_VOLUMEN);
		return this;
	}


	public DOLong getID_ADRESSE_SPEDITION(){
		return ID_ADRESSE_SPEDITION;
	}
	public jt_bg_vektor setID_ADRESSE_SPEDITION (Long _ID_ADRESSE_SPEDITION){
		ID_ADRESSE_SPEDITION.setValue(_ID_ADRESSE_SPEDITION);
		return this;
	}

	public DOLong getID_UMA_KONTRAKT(){
		return ID_UMA_KONTRAKT;
	}
	public jt_bg_vektor setID_UMA_KONTRAKT (Long _ID_UMA_KONTRAKT){
		ID_UMA_KONTRAKT.setValue(_ID_UMA_KONTRAKT);
		return this;
	}

	public DOLong getID_TRANSPORTMITTEL(){
		return ID_TRANSPORTMITTEL;
	}
	public jt_bg_vektor setID_TRANSPORTMITTEL (Long _ID_TRANSPORTMITTEL){
		ID_TRANSPORTMITTEL.setValue(_ID_TRANSPORTMITTEL);
		return this;
	}

//	public DOLong getID_BG_PRUEFPORT_GELANGENSBEST(){
//		return ID_BG_PRUEFPORT_GELANGENSBEST;
//	}
//	public jt_bg_vektor setID_BG_PRUEFPORT_GELANGENSBEST (Long _ID_BG_PRUEFPORT_GELANGENSBEST){
//		ID_BG_PRUEFPORT_GELANGENSBEST.setValue(_ID_BG_PRUEFPORT_GELANGENSBEST);
//		return this;
//	}

	public DOLong getID_BG_PRUEFPROT_ABSCHLUSS(){
		return ID_BG_PRUEFPROT_ABSCHLUSS;
	}
	public jt_bg_vektor setID_BG_PRUEFPROT_ABSCHLUSS (Long _ID_BG_PRUEFPROT_ABSCHLUSS){
		ID_BG_PRUEFPROT_ABSCHLUSS.setValue(_ID_BG_PRUEFPROT_ABSCHLUSS);
		return this;
	}

	public DOLong getID_VERPACKUNGSART(){
		return ID_VERPACKUNGSART;
	}
	public jt_bg_vektor setID_VERPACKUNGSART (Long _ID_VERPACKUNGSART){
		ID_VERPACKUNGSART.setValue(_ID_VERPACKUNGSART);
		return this;
	}

	public DOLong getID_ADRESSE_FREMDWARE(){
		return ID_ADRESSE_FREMDWARE;
	}
	public jt_bg_vektor setID_ADRESSE_FREMDWARE (Long _ID_ADRESSE_FREMDWARE){
		ID_ADRESSE_FREMDWARE.setValue(_ID_ADRESSE_FREMDWARE);
		return this;
	}

	public DOLong getID_BG_VEKTOR_SUB(){
		return ID_BG_VEKTOR_SUB;
	}
	public jt_bg_vektor setID_BG_VEKTOR_SUB (Long _ID_BG_VEKTOR_SUB){
		ID_BG_VEKTOR_SUB.setValue(_ID_BG_VEKTOR_SUB);
		return this;
	}



	public DOString getLIEFERINFO_TPA(){
		return LIEFERINFO_TPA;
	}
	public jt_bg_vektor setLIEFERINFO_TPA (String _LIEFERINFO_TPA){
		LIEFERINFO_TPA.setValue(_LIEFERINFO_TPA);
		return this;
	}

	public DOString getORDNUNGSNUMMER_CMR(){
		return ORDNUNGSNUMMER_CMR;
	}
	public jt_bg_vektor setORDNUNGSNUMMER_CMR (String _ORDNUNGSNUMMER_CMR){
		ORDNUNGSNUMMER_CMR.setValue(_ORDNUNGSNUMMER_CMR);
		return this;
	}

	public DOString getTRANSPORTMITTEL(){
		return TRANSPORTMITTEL;
	}
	public jt_bg_vektor setTRANSPORTMITTEL (String _TRANSPORTMITTEL){
		TRANSPORTMITTEL.setValue(_TRANSPORTMITTEL);
		return this;
	}

	public DOString getTRANSPORTKENNZEICHEN(){
		return TRANSPORTKENNZEICHEN;
	}
	public jt_bg_vektor setTRANSPORTKENNZEICHEN (String _TRANSPORTKENNZEICHEN){
		TRANSPORTKENNZEICHEN.setValue(_TRANSPORTKENNZEICHEN);
		return this;
	}

	public DOString getANHAENGERKENNZEICHEN(){
		return ANHAENGERKENNZEICHEN;
	}
	public jt_bg_vektor setANHAENGERKENNZEICHEN (String _ANHAENGERKENNZEICHEN){
		ANHAENGERKENNZEICHEN.setValue(_ANHAENGERKENNZEICHEN);
		return this;
	}

	public DOString getEK_VK_ZUORD_ZWANG(){
		return EK_VK_ZUORD_ZWANG;
	}
	public jt_bg_vektor setEK_VK_ZUORD_ZWANG (String _EK_VK_ZUORD_ZWANG){
		EK_VK_ZUORD_ZWANG.setValue(_EK_VK_ZUORD_ZWANG);
		return this;
	}

	public DOString getBUCHUNGSNUMMER(){
		return BUCHUNGSNUMMER;
	}
	public jt_bg_vektor setBUCHUNGSNUMMER (String _BUCHUNGSNUMMER){
		BUCHUNGSNUMMER.setValue(_BUCHUNGSNUMMER);
		return this;
	}

	public DOString getPRINT_ANHANG7(){
		return PRINT_ANHANG7;
	}
	public jt_bg_vektor setPRINT_ANHANG7 (String _PRINT_ANHANG7){
		PRINT_ANHANG7.setValue(_PRINT_ANHANG7);
		return this;
	}

	public DOString getBEMERKUNG(){
		return BEMERKUNG;
	}
	public jt_bg_vektor setBEMERKUNG (String _BEMERKUNG){
		BEMERKUNG.setValue(_BEMERKUNG);
		return this;
	}

	public DOString getBEMERKUNG_FUER_KUNDE(){
		return BEMERKUNG_FUER_KUNDE;
	}
	public jt_bg_vektor setBEMERKUNG_FUER_KUNDE (String _BEMERKUNG_FUER_KUNDE){
		BEMERKUNG_FUER_KUNDE.setValue(_BEMERKUNG_FUER_KUNDE);
		return this;
	}

	public DOString getBEMERKUNG_SACHBEARBEITER(){
		return BEMERKUNG_SACHBEARBEITER;
	}
	public jt_bg_vektor setBEMERKUNG_SACHBEARBEITER (String _BEMERKUNG_SACHBEARBEITER){
		BEMERKUNG_SACHBEARBEITER.setValue(_BEMERKUNG_SACHBEARBEITER);
		return this;
	}

	public DOString getTRANSPORTVERANTWORTUNG(){
		return TRANSPORTVERANTWORTUNG;
	}
	public jt_bg_vektor setTRANSPORTVERANTWORTUNG (String _TRANSPORTVERANTWORTUNG){
		TRANSPORTVERANTWORTUNG.setValue(_TRANSPORTVERANTWORTUNG);
		return this;
	}

	public DODate getAVV_AUSSTELLUNG_DATUM(){
		return AVV_AUSSTELLUNG_DATUM;
	}
	public jt_bg_vektor setAVV_AUSSTELLUNG_DATUM(Date _AVV_AUSSTELLUNG_DATUM){
		AVV_AUSSTELLUNG_DATUM.setValue(_AVV_AUSSTELLUNG_DATUM);
		return this;
	}

	public DODate getDATUM_PLANUNG_VON(){
		return DATUM_PLANUNG_VON;
	}
	public jt_bg_vektor setDATUM_PLANUNG_VON(Date _DATUM_PLANUNG_VON){
		DATUM_PLANUNG_VON.setValue(_DATUM_PLANUNG_VON);
		return this;
	}

	public DODate getDATUM_PLANUNG_BIS(){
		return DATUM_PLANUNG_BIS;
	}
	public jt_bg_vektor setDATUM_PLANUNG_BIS(Date _DATUM_PLANUNG_BIS){
		DATUM_PLANUNG_BIS.setValue(_DATUM_PLANUNG_BIS);
		return this;
	}
	

	
	
	
	
	
	/**
	 * Vektor aller Felder aufbauen
	 * @author manfred
	 * @date 16.02.2018
	 *
	 */
	protected void initFieldList(){
		
		m_vDataObjects.addElement( ID_BG_VEKTOR );
		m_vDataObjects.addElement( ID_MANDANT );
		m_vDataObjects.addElement( ID_BG_VEKTOR_BASE );
		m_vDataObjects.addElement( ID_LAND_TRANSIT1 );
		m_vDataObjects.addElement( ID_LAND_TRANSIT2 );
		m_vDataObjects.addElement( ID_LAND_TRANSIT3 );
		m_vDataObjects.addElement( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement( ID_BG_DEL_INFO );
		m_vDataObjects.addElement( POSNR );
		m_vDataObjects.addElement( KOSTEN_TRANSPORT );
		m_vDataObjects.addElement( ID_HANDELSDEF );
		m_vDataObjects.addElement( ID_ADRESSE_LOGI_START );
		m_vDataObjects.addElement( ID_ADRESSE_LOGI_ZIEL );
		m_vDataObjects.addElement( EU_BLATT_MENGE );
		m_vDataObjects.addElement( EU_BLATT_VOLUMEN );
		m_vDataObjects.addElement( PLANMENGE );
		m_vDataObjects.addElement( ID_ADRESSE_SPEDITION );
		m_vDataObjects.addElement( ID_UMA_KONTRAKT );
		m_vDataObjects.addElement( ID_TRANSPORTMITTEL );
//		m_vDataObjects.addElement( ID_BG_PRUEFPORT_GELANGENSBEST );
		m_vDataObjects.addElement( ID_BG_PRUEFPROT_ABSCHLUSS );
		m_vDataObjects.addElement( ID_VERPACKUNGSART );
		m_vDataObjects.addElement( ID_ADRESSE_FREMDWARE );
		m_vDataObjects.addElement( ID_BG_VEKTOR_SUB );
		m_vDataObjects.addElement( GEAENDERT_VON );
		m_vDataObjects.addElement( ERZEUGT_VON );
		m_vDataObjects.addElement( TIMESTAMP_IN_MASK );
		m_vDataObjects.addElement( VEKTOR_STATUS );
		m_vDataObjects.addElement( VEKTOR_QUELLE );
		m_vDataObjects.addElement( TRANSPORT_TYP );
		m_vDataObjects.addElement( LIEFERINFO_TPA );
		m_vDataObjects.addElement( ORDNUNGSNUMMER_CMR );
		m_vDataObjects.addElement( TRANSPORTMITTEL );
		m_vDataObjects.addElement( TRANSPORTKENNZEICHEN );
		m_vDataObjects.addElement( ANHAENGERKENNZEICHEN );
		m_vDataObjects.addElement( EK_VK_ZUORD_ZWANG );
		m_vDataObjects.addElement( BUCHUNGSNUMMER );
		m_vDataObjects.addElement( PRINT_ANHANG7 );
		m_vDataObjects.addElement( BEMERKUNG );
		m_vDataObjects.addElement( BEMERKUNG_FUER_KUNDE );
		m_vDataObjects.addElement( BEMERKUNG_SACHBEARBEITER );
		m_vDataObjects.addElement( TRANSPORTVERANTWORTUNG );
		m_vDataObjects.addElement( LETZTE_AENDERUNG );
		m_vDataObjects.addElement( ERZEUGT_AM );
		m_vDataObjects.addElement( AVV_AUSSTELLUNG_DATUM );
		m_vDataObjects.addElement( DATUM_PLANUNG_VON );
		m_vDataObjects.addElement( DATUM_PLANUNG_BIS );
	}

	
	
	

	
	
}
