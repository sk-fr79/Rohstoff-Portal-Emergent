/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_Lager_Konto;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOBigDecimal;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base;
import rohstoff.businesslogic.bewegung2.global.EN_VEKTOR_QUELLE;
/**
 * @author manfred
 * @date 16.01.2018
 *
 */
public class jt_bg_vektor extends REC_Base {
	
//	private static String m_tableName = "JT_BG_VEKTOR";

//	Vector<DataObject>    m_vDataObjects = new Vector<>();
	

	
	private DOString 	ID_BG_VEKTOR = new DOString("ID_BG_VEKTOR",null,"");
	private DOLong 		ID_MANDANT = new DOLong("ID_MANDANT");
	private DODate 		LETZTE_AENDERUNG = new DODate("LETZTE_AENDERUNG");
	private DOString 	GEAENDERT_VON = new DOString("GEAENDERT_VON");
	private DOString 	ERZEUGT_VON = new DOString("ERZEUGT_VON");
	private DODate 		ERZEUGT_AM = new DODate("ERZEUGT_AM");
	private DOLong 		POSNR = new DOLong("POSNR");
	private DOString 	TIMESTAMP_IN_MASK = new DOString("TIMESTAMP_IN_MASK");
	private DOString 	enum_EN_VEKTOR_STATUS = new DOString("EN_VEKTOR_STATUS");
	private DOString 	enum_EN_VEKTOR_TYP = new DOString("EN_VEKTOR_TYP");
	private DOString 	enum_EN_VEKTOR_QUELLE = new DOString("EN_VEKTOR_QUELLE");
	
	private DOString 	LAENDERCODE_TRANSIT1 = new DOString("LAENDERCODE_TRANSIT1");
	private DOString 	LAENDERCODE_TRANSIT2 = new DOString("LAENDERCODE_TRANSIT2");
	private DOString 	LAENDERCODE_TRANSIT3 = new DOString("LAENDERCODE_TRANSIT3");

	private DOString 	ORDNUNGSNUMMER_CMR = new DOString("ORDNUNGSNUMMER_CMR");
	private DOString 	TRANSPORTMITTEL = new DOString("TRANSPORTMITTEL");
	private DOString 	TRANSPORTKENNZEICHEN = new DOString("TRANSPORTKENNZEICHEN");
	private DOString 	ANHAENGERKENNZEICHEN = new DOString("ANHAENGERKENNZEICHEN");
	private DOBigDecimal EU_BLATT_MENGE= new DOBigDecimal("EU_BLATT_MENGE");
	private DOBigDecimal EU_BLATT_VOLUMEN= new DOBigDecimal("EU_BLATT_VOLUMEN");
	private DODate 		AVV_AUSSTELLUNG_DATUM = new DODate("AVV_AUSSTELLUNG_DATUM");
	
	private DOString 	BEMERKUNG = new DOString("BEMERKUNG");
	private DOString 	BEMERKUNG_FUER_KUNDE = new DOString("BEMERKUNG_FUER_KUNDE");
	private DOString 	BEMERKUNG_SACHBEARBEITER = new DOString("BEMERKUNG_SACHBEARBEITER");
	
	private DOString 	EK_VK_ZUORD_ZWANG = new DOString("EK_VK_ZUORD_ZWANG");
	private DOBigDecimal PLANMENGE = new DOBigDecimal("PLANMENGE");
	private DOString 	BUCHUNGSNUMMER = new DOString("BUCHUNGSNUMMER");
	private DOString 	PRINT_ANHANG7 = new DOString("PRINT_ANHANG7");
	private DODate 		DATUM_START_VON= new DODate("DATUM_START_VON");
	private DODate 		DATUM_START_BIS= new DODate("DATUM_START_BIS");
	private DODate 		DATUM_ZIEL_VON= new DODate("DATUM_ZIEL_VON");
	private DODate 		DATUM_ZIEL_BIS= new DODate("DATUM_ZIEL_BIS");
	private DOLong 		ID_ADRESSE_SPEDITION = new DOLong("ID_ADRESSE_SPEDITION");
	private DOLong 		ID_BG_PRUEFPORT_GELANGENSBEST = new DOLong("ID_BG_PRUEFPORT_GELANGENSBEST");
	private DOLong 		ID_BG_PRUEFPROT_ABSCHLUSS = new DOLong("ID_BG_PRUEFPROT_ABSCHLUSS");
	private DOLong	 	ID_VERPACKUNGSART = new DOLong("ID_VERPACKUNGSART");
	private DOLong 		ID_UMA_KONTRAKT = new DOLong("ID_UMA_KONTRAKT");
	private DOLong 		ID_TRANSPORTMITTEL = new DOLong("ID_TRANSPORTMITTEL");
	private DOLong 		ID_HANDELSDEF = new DOLong("ID_HANDELSDEF");
	private DOString 	enum_EN_VEKTOR_WARENKLASSE = new DOString("EN_VEKTOR_WARENKLASSE");
	
	private DOString 		ID_BG_STORNO_INFO = new DOString("ID_BG_STORNO_INFO",null,"");
	private DOString 		ID_BG_DEL_INFO = new DOString("ID_BG_DEL_INFO",null,"");

	/**
	 *  die Atome des Vektors
	 */
	private Vector<jt_bg_atom> jt_bg_atoms = new Vector<>();

	// del und storno-INFOS
	private REC_Base     _jt_bg_del_info = null;
	private REC_Base 	 _jt_bg_storno_info = null;


	
	
	/**
	 * @author manfred
	 * @date 16.01.2018
	 *
	 */
	public jt_bg_vektor() {
		super("JT_BG_VEKTOR");
		this.initFieldList();
		
	}
	
	
	/**
	 * @author manfred
	 * @date 16.01.2018
	 *
	 * @param iD_BG_VEKTOR
	 * @param iD_MANDANT
	 */
	public jt_bg_vektor(String iD_BG_VEKTOR, Long iD_MANDANT) {
		this();
		setID_BG_VEKTOR( iD_BG_VEKTOR);
		setID_MANDANT( iD_MANDANT);
	}
	
	
	
	/**
	 * Initialisiern des Vektors mit den Daten der Fuhre
	 * @author manfred
	 * @date 17.01.2018
	 *
	 * @param fuhre
	 * @throws myException
	 */
	public jt_bg_vektor(BG_FUHREN_Transformation trans,  Rec20_VPOS_TPA_FUHRE_ext fuhre) throws myException {
		this();
		
		// Sequenzer setzen
		this.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_nextval() );
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant) );
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));

		this.setPOSNR(1L);
		this.setTIMESTAMP_IN_MASK(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.zeitstempel));
		
		this.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.AKTIV);

		this.setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE.FUHRE);
		
		this.setLAENDERCODE_TRANSIT1(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.laendercode_transit1));
		this.setLAENDERCODE_TRANSIT2(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.laendercode_transit2));
		this.setLAENDERCODE_TRANSIT3(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.laendercode_transit3));
		this.setORDNUNGSNUMMER_CMR(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.ordnungsnummer_cmr));
		this.setTRANSPORTMITTEL(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportmittel));
		this.setTRANSPORTKENNZEICHEN(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.transportkennzeichen));
		this.setANHAENGERKENNZEICHEN(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.anhaengerkennzeichen));
		
		
		this.setEU_BLATT_MENGE(fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.eu_blatt_menge));
		this.setEU_BLATT_VOLUMEN(fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.eu_blatt_volumen));
		
		this.setAVV_AUSSTELLUNG_DATUM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.avv_ausstellung_datum) ) ;
		this.setBEMERKUNG_FUER_KUNDE(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.bemerkung_fuer_kunde) );
		this.setBEMERKUNG(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.bemerkung) );
		this.setBEMERKUNG_SACHBEARBEITER(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.bemerkung_sachbearbeiter) );
		this.setEK_VK_ZUORD_ZWANG(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.ek_vk_zuord_zwang));
		//this.setPLANMENGE(pLANMENGE);
		this.setBUCHUNGSNUMMER(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.buchungsnr_fuhre ));
		this.setPRINT_ANHANG7(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.print_eu_amtsblatt));
		
		this.setDATUM_START_VON(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung));
		this.setDATUM_START_BIS(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abholung_ende));
		this.setDATUM_ZIEL_VON(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_anlieferung));
		this.setDATUM_ZIEL_BIS(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_anlieferung_ende));
		
		this.setID_ADRESSE_SPEDITION(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_spedition));
		//this.setID_BG_PRUEFPORT_GELANGENSBEST(iD_BG_PRUEFPORT_GELANGENSBEST);
		//this.setID_BG_PRUEFPROT_ABSCHLUSS(iD_BG_PRUEFPROT_ABSCHLUSS);
		this.setID_VERPACKUNGSART(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_verpackungsart));
		//this.setID_BG_STORNO_INFO(iD_BG_STORNO_INFO);
		//this.setID_BG_DEL_INFO(iD_BG_DEL_INFO);
		this.setID_UMA_KONTRAKT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_uma_kontrakt));
		//this.setID_TRANSPORTMITTEL(..));
		//this.setID_HANDELSDEF(iD_HANDELSDEF);
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (fuhre.getUfs(VPOS_TPA_FUHRE.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( fuhre);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		}
	}


	/**
	 * @author manfred
	 * @date 13.02.2018
	 *
	 * @param oRec
	 * @throws myException 
	 */
	public jt_bg_vektor(Rec20_Lager_Konto oLager) throws myException {
		this();
		
		// Sequenzer setzen
		this.setID_BG_VEKTOR(BG_VEKTOR._tab().seq_nextval() );

		setID_MANDANT(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_mandant) );
		setLETZTE_AENDERUNG(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(oLager.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(oLager.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));

		this.setPOSNR(1L);
		
		this.setDATUM_START_VON(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum));
		this.setDATUM_START_BIS(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum));
		this.setDATUM_ZIEL_VON(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum));
		this.setDATUM_ZIEL_BIS(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum));

		
		this.setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS.AKTIV);

		this.setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE.LAGER);
		this.setBEMERKUNG(oLager.get_ufs_dbVal(LAGER_KONTO.bemerkung));
		
		this.setPLANMENGE(oLager.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge, new BigDecimal(0L)));
		
	}

	

	public jt_bg_vektor add_bgAtom(jt_bg_atom a){
		this.jt_bg_atoms.addElement(a);
		return this;
	}
	
	public Vector<jt_bg_atom> get_jt_bg_atoms(){
		return jt_bg_atoms;
	}
	
	public jt_bg_vektor set_jt_bg_atoms( Vector<jt_bg_atom> atoms){
		jt_bg_atoms = atoms;
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
		return enum_EN_VEKTOR_QUELLE;
	}
	public jt_bg_vektor setEN_VEKTOR_QUELLE(String eN_VEKTOR_QUELLE) {
		enum_EN_VEKTOR_QUELLE.setValue( eN_VEKTOR_QUELLE);
		return this;
	}
	public jt_bg_vektor setEN_VEKTOR_QUELLE(EN_VEKTOR_QUELLE eN_VEKTOR_QUELLE){
		enum_EN_VEKTOR_QUELLE.setValue(eN_VEKTOR_QUELLE.dbVal());
		return this;
	}
	
	
	
	public DOString getEN_VEKTOR_STATUS() {
		return enum_EN_VEKTOR_STATUS;
	}
	public jt_bg_vektor setEN_VEKTOR_STATUS(String eN_VEKTOR_STATUS) {
		enum_EN_VEKTOR_STATUS.setValue(eN_VEKTOR_STATUS);
		return this;
	}
	
	public jt_bg_vektor setEN_VEKTOR_STATUS(EN_VEKTOR_STATUS eN_VEKTOR_STATUS) {
		enum_EN_VEKTOR_STATUS.setValue(eN_VEKTOR_STATUS.dbVal());
		return this;
	}
	
	
	public DOString getEN_VEKTOR_TYP() {
		return enum_EN_VEKTOR_TYP;
	}
	public jt_bg_vektor setEN_VEKTOR_TYP(String eN_VEKTOR_TYP) {
		enum_EN_VEKTOR_TYP.setValue(eN_VEKTOR_TYP);
		return this;
	}
	public jt_bg_vektor setEN_VEKTOR_TYP(EN_VEKTOR_TYP eN_VEKTOR_TYP) {
		enum_EN_VEKTOR_TYP.setValue(eN_VEKTOR_TYP.dbVal());
		return this;
	}

	
	public DOString getEN_VEKTOR_WARENKLASSE(){
		return enum_EN_VEKTOR_WARENKLASSE;
	}
	
	public jt_bg_vektor setEN_VEKTOR_WARENKLASSE(String pEN_VEKTOR_WARENKLASSE) {
		enum_EN_VEKTOR_WARENKLASSE.setValue( pEN_VEKTOR_WARENKLASSE );
		return this;
	}
	
	public jt_bg_vektor setEN_VEKTOR_WARENKLASSE(EN_LADUNG_WARENKLASSE pEN_VEKTOR_WARENKLASSE) {
		enum_EN_VEKTOR_WARENKLASSE.setValue( pEN_VEKTOR_WARENKLASSE.dbVal() );
		return this;
	}
	
	
	
	public DOString getLAENDERCODE_TRANSIT1() {
		return LAENDERCODE_TRANSIT1;
	}
	public jt_bg_vektor setLAENDERCODE_TRANSIT1(String lAENDERCODE_TRANSIT1) {
		LAENDERCODE_TRANSIT1 .setValue(lAENDERCODE_TRANSIT1);
		return this;
	}
	
	public DOString getLAENDERCODE_TRANSIT2() {
		return LAENDERCODE_TRANSIT2;
	}
	public jt_bg_vektor setLAENDERCODE_TRANSIT2(String lAENDERCODE_TRANSIT2) {
		LAENDERCODE_TRANSIT2 .setValue(lAENDERCODE_TRANSIT2);
		return this;
	}
	
	public DOString getLAENDERCODE_TRANSIT3() {
		return LAENDERCODE_TRANSIT3;
	}
	public jt_bg_vektor setLAENDERCODE_TRANSIT3(String lAENDERCODE_TRANSIT3) {
		LAENDERCODE_TRANSIT3.setValue( lAENDERCODE_TRANSIT3);
		return this;
	}
	
	public DOString getORDNUNGSNUMMER_CMR() {
		return ORDNUNGSNUMMER_CMR;
	}
	public jt_bg_vektor setORDNUNGSNUMMER_CMR(String oRDNUNGSNUMMER_CMR) {
		ORDNUNGSNUMMER_CMR .setValue(oRDNUNGSNUMMER_CMR);
		return this;
	}
	
	public DOString getTRANSPORTMITTEL() {
		return TRANSPORTMITTEL;
	}
	public jt_bg_vektor setTRANSPORTMITTEL(String tRANSPORTMITTEL) {
		TRANSPORTMITTEL .setValue(tRANSPORTMITTEL);
		return this;
	}
	
	public DOString getTRANSPORTKENNZEICHEN() {
		return TRANSPORTKENNZEICHEN;
	}
	public jt_bg_vektor setTRANSPORTKENNZEICHEN(String tRANSPORTKENNZEICHEN) {
		TRANSPORTKENNZEICHEN .setValue( tRANSPORTKENNZEICHEN);
		return this;
	}
	public DOString getANHAENGERKENNZEICHEN() {
		return ANHAENGERKENNZEICHEN;
	}
	public jt_bg_vektor setANHAENGERKENNZEICHEN(String aNHAENGERKENNZEICHEN) {
		ANHAENGERKENNZEICHEN .setValue( aNHAENGERKENNZEICHEN);
		return this;
	}
	public DOBigDecimal getEU_BLATT_MENGE() {
		return EU_BLATT_MENGE;
	}
	public jt_bg_vektor setEU_BLATT_MENGE(BigDecimal eU_BLATT_MENGE) {
		EU_BLATT_MENGE .setValue( eU_BLATT_MENGE);
		return this;
	}
	public DOBigDecimal getEU_BLATT_VOLUMEN() {
		return EU_BLATT_VOLUMEN;
	}
	public jt_bg_vektor setEU_BLATT_VOLUMEN(BigDecimal eU_BLATT_VOLUMEN) {
		EU_BLATT_VOLUMEN .setValue( eU_BLATT_VOLUMEN);
		return this;
	}
	public DODate getAVV_AUSSTELLUNG_DATUM() {
		return AVV_AUSSTELLUNG_DATUM;
	}
	public jt_bg_vektor setAVV_AUSSTELLUNG_DATUM(Date aVV_AUSSTELLUNG_DATUM) {
		AVV_AUSSTELLUNG_DATUM .setValue( aVV_AUSSTELLUNG_DATUM);
		return this;
	}
	public DOString getBEMERKUNG() {
		return BEMERKUNG;
	}
	public jt_bg_vektor setBEMERKUNG(String bEMERKUNG) {
		BEMERKUNG .setValue( bEMERKUNG);
		return this;
	}
	public DOString getBEMERKUNG_FUER_KUNDE() {
		return BEMERKUNG_FUER_KUNDE;
	}
	public jt_bg_vektor setBEMERKUNG_FUER_KUNDE (String pBEMERKUNG_FUER_KUNDE) {
		BEMERKUNG_FUER_KUNDE .setValue( pBEMERKUNG_FUER_KUNDE);
		return this;
	}
	public DOString getBEMERKUNG_SACHBEARBEITER() {
		return BEMERKUNG_SACHBEARBEITER;
	}
	public jt_bg_vektor setBEMERKUNG_SACHBEARBEITER (String pBEMERKUNG_SACHBEARBEITER) {
		BEMERKUNG_SACHBEARBEITER .setValue( pBEMERKUNG_SACHBEARBEITER);
		return this;
	}

	
	public DOString getEK_VK_ZUORD_ZWANG() {
		return EK_VK_ZUORD_ZWANG;
	}
	public jt_bg_vektor setEK_VK_ZUORD_ZWANG(String eK_VK_ZUORD_ZWANG) {
		EK_VK_ZUORD_ZWANG .setValue( eK_VK_ZUORD_ZWANG);
		return this;
	}
	public DOBigDecimal getPLANMENGE() {
		return PLANMENGE;
	}
	public jt_bg_vektor setPLANMENGE(BigDecimal pLANMENGE) {
		PLANMENGE .setValue( pLANMENGE);
		return this;
	}
	public DOString getBUCHUNGSNUMMER() {
		return BUCHUNGSNUMMER;
	}
	public jt_bg_vektor setBUCHUNGSNUMMER(String bUCHUNGSNUMMER) {
		BUCHUNGSNUMMER .setValue( bUCHUNGSNUMMER);
		return this;
	}
	public DOString getPRINT_ANHANG7() {
		return PRINT_ANHANG7;
	}
	public jt_bg_vektor setPRINT_ANHANG7(String pRINT_ANHANG7) {
		PRINT_ANHANG7 .setValue( pRINT_ANHANG7);
		return this;
	}
	public DODate getDATUM_START_VON() {
		return DATUM_START_VON;
	}
	public jt_bg_vektor setDATUM_START_VON(Date dATUM_START_VON) {
		DATUM_START_VON .setValue( dATUM_START_VON);
		return this;
	}
	public DODate getDATUM_START_BIS() {
		return DATUM_START_BIS;
	}
	public jt_bg_vektor setDATUM_START_BIS(Date dATUM_START_BIS) {
		DATUM_START_BIS .setValue( dATUM_START_BIS);
		return this;
	}
	public DODate getDATUM_ZIEL_VON() {
		return DATUM_ZIEL_VON;
	}
	public jt_bg_vektor setDATUM_ZIEL_VON(Date dATUM_ZIEL_VON) {
		DATUM_ZIEL_VON .setValue( dATUM_ZIEL_VON);
		return this;
	}
	public DODate getDATUM_ZIEL_BIS() {
		return DATUM_ZIEL_BIS;
	}
	public jt_bg_vektor setDATUM_ZIEL_BIS(Date dATUM_ZIEL_BIS) {
		DATUM_ZIEL_BIS .setValue( dATUM_ZIEL_BIS);
		return this;
	}
	public DOLong getID_ADRESSE_SPEDITION() {
		return ID_ADRESSE_SPEDITION;
	}
	public jt_bg_vektor setID_ADRESSE_SPEDITION(Long iD_ADRESSE_SPEDITION) {
		ID_ADRESSE_SPEDITION .setValue( iD_ADRESSE_SPEDITION);
		return this;
	}
	public DOLong getID_BG_PRUEFPORT_GELANGENSBEST() {
		return ID_BG_PRUEFPORT_GELANGENSBEST;
	}
	public jt_bg_vektor setID_BG_PRUEFPORT_GELANGENSBEST(Long iD_BG_PRUEFPORT_GELANGENSBEST) {
		ID_BG_PRUEFPORT_GELANGENSBEST .setValue( iD_BG_PRUEFPORT_GELANGENSBEST);
		return this;
	}
	public DOLong getID_BG_PRUEFPROT_ABSCHLUSS() {
		return ID_BG_PRUEFPROT_ABSCHLUSS;
	}
	public jt_bg_vektor setID_BG_PRUEFPROT_ABSCHLUSS(Long iD_BG_PRUEFPROT_ABSCHLUSS) {
		ID_BG_PRUEFPROT_ABSCHLUSS .setValue( iD_BG_PRUEFPROT_ABSCHLUSS);
		return this;
	}
	public DOLong getID_VERPACKUNGSART() {
		return ID_VERPACKUNGSART;
	}
	public jt_bg_vektor setID_VERPACKUNGSART(Long iD_VERPACKUNGSART) {
		ID_VERPACKUNGSART .setValue( iD_VERPACKUNGSART);
		return this;
	}
	public DOString getID_BG_STORNO_INFO() {
		return ID_BG_STORNO_INFO;
	}
	public jt_bg_vektor setID_BG_STORNO_INFO(String iD_BG_STORNO_INFO) {
		ID_BG_STORNO_INFO .setValue( iD_BG_STORNO_INFO);
		return this;
	}
	public DOString getID_BG_DEL_INFO() {
		return ID_BG_DEL_INFO;
	}
	public jt_bg_vektor setID_BG_DEL_INFO(String iD_BG_DEL_INFO) {
		ID_BG_DEL_INFO .setValue( iD_BG_DEL_INFO);
		return this;
	}
	public DOLong getID_UMA_KONTRAKT() {
		return ID_UMA_KONTRAKT;
	}
	public jt_bg_vektor setID_UMA_KONTRAKT(Long iD_UMA_KONTRAKT) {
		ID_UMA_KONTRAKT .setValue( iD_UMA_KONTRAKT);
		return this;
	}
	public DOLong getID_TRANSPORTMITTEL() {
		return ID_TRANSPORTMITTEL;
	}
	public jt_bg_vektor setID_TRANSPORTMITTEL(Long iD_TRANSPORTMITTEL) {
		ID_TRANSPORTMITTEL .setValue( iD_TRANSPORTMITTEL);
		return this;
	}
	public DOLong getID_HANDELSDEF() {
		return ID_HANDELSDEF;
	}
	public jt_bg_vektor setID_HANDELSDEF(Long iD_HANDELSDEF) {
		ID_HANDELSDEF .setValue( iD_HANDELSDEF);
		return this;
	}
	
	
	
	/**
	 * Vektor aller Felder aufbauen
	 * @author manfred
	 * @date 16.02.2018
	 *
	 */
	protected void initFieldList(){
		m_vDataObjects.addElement ( ID_BG_VEKTOR );
		m_vDataObjects.addElement ( ID_MANDANT );                                  
		m_vDataObjects.addElement ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement ( GEAENDERT_VON );
		m_vDataObjects.addElement ( ERZEUGT_VON );
		m_vDataObjects.addElement ( ERZEUGT_AM );
		m_vDataObjects.addElement ( POSNR );
		m_vDataObjects.addElement ( TIMESTAMP_IN_MASK );
		m_vDataObjects.addElement ( enum_EN_VEKTOR_STATUS );  
		m_vDataObjects.addElement ( enum_EN_VEKTOR_TYP );                      
		m_vDataObjects.addElement ( enum_EN_VEKTOR_QUELLE );
		m_vDataObjects.addElement ( LAENDERCODE_TRANSIT1 );
		m_vDataObjects.addElement ( LAENDERCODE_TRANSIT2 );
		m_vDataObjects.addElement ( LAENDERCODE_TRANSIT3 );
		m_vDataObjects.addElement ( ORDNUNGSNUMMER_CMR );
		m_vDataObjects.addElement ( TRANSPORTMITTEL );
		m_vDataObjects.addElement ( TRANSPORTKENNZEICHEN );
		m_vDataObjects.addElement ( ANHAENGERKENNZEICHEN );
		m_vDataObjects.addElement ( EU_BLATT_MENGE );
		m_vDataObjects.addElement ( EU_BLATT_VOLUMEN );
		m_vDataObjects.addElement ( AVV_AUSSTELLUNG_DATUM );
		m_vDataObjects.addElement ( BEMERKUNG );
		m_vDataObjects.addElement ( BEMERKUNG_FUER_KUNDE );
		m_vDataObjects.addElement ( BEMERKUNG_SACHBEARBEITER );
		m_vDataObjects.addElement ( EK_VK_ZUORD_ZWANG );
		m_vDataObjects.addElement ( PLANMENGE );
		m_vDataObjects.addElement ( BUCHUNGSNUMMER );
		m_vDataObjects.addElement ( PRINT_ANHANG7 );
		m_vDataObjects.addElement ( DATUM_START_VON );
		m_vDataObjects.addElement ( DATUM_START_BIS );
		m_vDataObjects.addElement ( DATUM_ZIEL_VON );
		m_vDataObjects.addElement ( DATUM_ZIEL_BIS );
		m_vDataObjects.addElement ( ID_ADRESSE_SPEDITION );
		m_vDataObjects.addElement ( ID_BG_PRUEFPORT_GELANGENSBEST );
		m_vDataObjects.addElement ( ID_BG_PRUEFPROT_ABSCHLUSS );
		m_vDataObjects.addElement ( ID_VERPACKUNGSART );
		m_vDataObjects.addElement ( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement ( ID_BG_DEL_INFO );
		m_vDataObjects.addElement ( ID_UMA_KONTRAKT );
		m_vDataObjects.addElement ( ID_TRANSPORTMITTEL );
		m_vDataObjects.addElement ( ID_HANDELSDEF );          
		m_vDataObjects.addElement ( enum_EN_VEKTOR_WARENKLASSE);
	}
	
	
	

	
	
}
