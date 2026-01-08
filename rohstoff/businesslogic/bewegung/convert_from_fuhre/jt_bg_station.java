/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_Lager_Konto;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOBigDecimal;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base;

/**
 * @author manfred
 * @date 16.01.2018
 *
 */
public class jt_bg_station extends REC_Base {

	

	private DOString ID_BG_STATION = new DOString("ID_BG_STATION",null,"");
	private DOLong ID_MANDANT = new DOLong("ID_MANDANT");

	private DOString ID_BG_STORNO_INFO = new DOString("ID_BG_STORNO_INFO",null,"");
	private DOString ID_BG_DEL_INFO = new DOString("ID_BG_DEL_INFO",null,"");
	
	private DODate LETZTE_AENDERUNG = new DODate("LETZTE_AENDERUNG");
	private DOString GEAENDERT_VON = new DOString("GEAENDERT_VON");
	private DOString ERZEUGT_VON = new DOString("ERZEUGT_VON");
	private DODate ERZEUGT_AM = new DODate("ERZEUGT_AM");
	
	private DOString WIEGEKARTENKENNER = new DOString("WIEGEKARTENKENNER");
	private DOBigDecimal KONTROLLMENGE = new DOBigDecimal("KONTROLLMENGE");

	private DOString NAME1 = new DOString("NAME1");
	private DOString NAME2 = new DOString("NAME2");
	private DOString NAME3 = new DOString("NAME3");
	private DOString STRASSE = new DOString("STRASSE");
	private DOString HAUSNUMMER = new DOString("HAUSNUMMER");
	private DOString PLZ = new DOString("PLZ");
	private DOString ORT = new DOString("ORT");
	private DOString ORTZUSATZ = new DOString("ORTZUSATZ");
	private DOString OEFFNUNGSZEITEN = new DOString("OEFFNUNGSZEITEN");
	private DOString BESTELLNUMMER = new DOString("BESTELLNUMMER");
	private DOString TELEFON = new DOString("TELEFON");
	private DOString FAX = new DOString("FAX");
	private DOString LIEFERINFO_TPA = new DOString("LIEFERINFO_TPA");
	private DOString TIMESTAMP_IN_MASK = new DOString("TIMESTAMP_IN_MASK");

	//Long
	private DOLong ID_LAND = new DOLong("ID_LAND");
	private DOLong ID_BG_RECH_BLOCK = new DOLong("ID_BG_RECH_BLOCK");
	private DOLong ID_ADRESSE = new DOLong("ID_ADRESSE");
	private DOLong ID_ADRESSE_BASIS = new DOLong("ID_ADRESSE_BASIS");
	private DOLong ID_WIEGEKARTE = new DOLong("ID_WIEGEKARTE");

	private DOLong ID_ZAHLUNGSBEDINGUNGEN = new DOLong("ID_ZAHLUNGSBEDINGUNGEN");
	private DOString ZAHLUNGSBEDINGUNGEN = new DOString("ZAHLUNGSBEDINGUNGEN");

	private DOString 	ID_BG_VEKTOR = new DOString("ID_BG_VEKTOR",null,"");

	private jt_bg_ladung o_jt_bg_ladung = null;


	// del und storno-INFOS
	private REC_Base     _jt_bg_del_info = null;
	private REC_Base 	 _jt_bg_storno_info = null;

	public jt_bg_station set_jt_bg_del_info(jt_bg_del_info delInfo){
		this._jt_bg_del_info = delInfo;
		return this;
	}
	
	public jt_bg_del_info get_jt_bg_del_info(){
		return (jt_bg_del_info)_jt_bg_del_info;
	}
	

	public jt_bg_station set_jt_bg_storno_info(jt_bg_storno_info stornoInfo){
		this._jt_bg_storno_info = stornoInfo;
		return this;
	}
	
	public jt_bg_storno_info get_jt_bg_storno_info(){
		return (jt_bg_storno_info)_jt_bg_storno_info;
	}
	
	
	/**
	 * @author manfred
	 * @date 17.01.2018
	 *
	 */
	public jt_bg_station() {
		super("JT_BG_STATION");
		this.initFieldList();
	}
	
	
	/**
	 * @author manfred
	 * @date 17.01.2018
	 *
	 * @param iD_BG_STATION
	 * @param iD_MANDANT
	 * @throws myException 
	 */
	public jt_bg_station( BG_FUHREN_Transformation trans,  Rec20_VPOS_TPA_FUHRE_ext fuhre, boolean bIstLadeseite) throws myException {
		this();

		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant) );
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));

		
		setWIEGEKARTENKENNER		(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.wiegekartenkenner_laden) 	: fuhre.getUfs(VPOS_TPA_FUHRE.wiegekartenkenner_abladen));
		setNAME1					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_name1)					: fuhre.getUfs(VPOS_TPA_FUHRE.a_name1));
		setNAME2					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_name2)					: fuhre.getUfs(VPOS_TPA_FUHRE.a_name2));
		setNAME3					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_name3)					: fuhre.getUfs(VPOS_TPA_FUHRE.a_name3));
		setSTRASSE					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_strasse)					: fuhre.getUfs(VPOS_TPA_FUHRE.a_strasse));
		setHAUSNUMMER				(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_hausnummer)				: fuhre.getUfs(VPOS_TPA_FUHRE.a_hausnummer));
		setPLZ						(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_plz,"0")					: fuhre.getUfs(VPOS_TPA_FUHRE.a_plz,"0"));
		setORT						(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_ort)						: fuhre.getUfs(VPOS_TPA_FUHRE.a_ort));
		setORTZUSATZ				(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_ortzusatz)				: fuhre.getUfs(VPOS_TPA_FUHRE.a_ortzusatz));
		setOEFFNUNGSZEITEN			(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.oeffnungszeiten_lief) 		: fuhre.getUfs(VPOS_TPA_FUHRE.oeffnungszeiten_abn));
		setBESTELLNUMMER			(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.bestellnummer_ek)			: fuhre.getUfs(VPOS_TPA_FUHRE.bestellnummer_vk) );
		setTELEFON					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.tel_lieferant)				: fuhre.getUfs(VPOS_TPA_FUHRE.tel_abnehmer));
		setFAX						(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.fax_lieferant)				: fuhre.getUfs(VPOS_TPA_FUHRE.fax_abnehmer));

		setLIEFERINFO_TPA			( fuhre.getUfs(VPOS_TPA_FUHRE.lieferinfo_tpa)); 
		setTIMESTAMP_IN_MASK		( fuhre.getUfs(VPOS_TPA_FUHRE.zeitstempel));
		
		setID_WIEGEKARTE			(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_wiegekarte_lief) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_wiegekarte_abn));
		setID_ADRESSE				(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_start) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_ziel));
		setID_ADRESSE_BASIS			(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_ziel));

		setKONTROLLMENGE			(bIstLadeseite ? fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn) );
		
		// wird bisher über laendercode gemacht.. jetzt nur noch über ID
		String land = 				(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_laendercode) : fuhre.getUfs(VPOS_TPA_FUHRE.a_laendercode));
		Long idLand = trans.getLandIDFromLaendercode(land);
		setID_LAND(idLand);
		
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (fuhre.getUfs(VPOS_TPA_FUHRE.deleted,"N").equals("Y")  ){
			jt_bg_del_info i = new jt_bg_del_info( fuhre);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		}
		
	}

	
	/**
	 * @author manfred
	 * @date 17.01.2018
	 *
	 * @param iD_BG_STATION
	 * @param iD_MANDANT
	 * @throws myException 
	 */
	public jt_bg_station( BG_FUHREN_Transformation trans, Rec20_VPOS_TPA_FUHRE_ORT_ext ort, Rec20_VPOS_TPA_FUHRE_ext fuhre ) throws myException {
		this();

		boolean bIstLadeseite = ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"-").equals("EK");
		
		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		
		setID_MANDANT(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_mandant) );
		setLETZTE_AENDERUNG(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.letzte_aenderung) )  ;
		setGEAENDERT_VON(ort.get_ufs_dbVal( VPOS_TPA_FUHRE_ORT.geaendert_von));
		setERZEUGT_AM(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.erzeugt_am));
		setERZEUGT_VON(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.erzeugt_von));
		
		
		setWIEGEKARTENKENNER		(ort.getUfs(VPOS_TPA_FUHRE_ORT.wiegekartenkenner)) ;
		setID_WIEGEKARTE			(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_wiegekarte) );
		setKONTROLLMENGE			(bIstLadeseite ? ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_lademenge): ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge) );
		setNAME1					(ort.getUfs(VPOS_TPA_FUHRE_ORT.name1 ));
		setNAME2					(ort.getUfs(VPOS_TPA_FUHRE_ORT.name2));
		setNAME3					(ort.getUfs(VPOS_TPA_FUHRE_ORT.name3));
		setSTRASSE					(ort.getUfs(VPOS_TPA_FUHRE_ORT.strasse) );
		setHAUSNUMMER				(ort.getUfs(VPOS_TPA_FUHRE_ORT.hausnummer) );
		setPLZ						(ort.getUfs(VPOS_TPA_FUHRE_ORT.plz,"0" ));
		setORT						(ort.getUfs(VPOS_TPA_FUHRE_ORT.ort ));
		setORTZUSATZ				(ort.getUfs(VPOS_TPA_FUHRE_ORT.ortzusatz));
		setOEFFNUNGSZEITEN			(ort.getUfs(VPOS_TPA_FUHRE_ORT.oeffnungszeiten) );
		setBESTELLNUMMER			(ort.getUfs(VPOS_TPA_FUHRE_ORT.bestellnummer));
		setTELEFON					(ort.getUfs(VPOS_TPA_FUHRE_ORT.tel ));
		setFAX						(ort.getUfs(VPOS_TPA_FUHRE_ORT.fax ));
		
		setID_ADRESSE				(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse_lager ));
		setID_ADRESSE_BASIS			(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse ));
		
		setTIMESTAMP_IN_MASK		( ort.getUfs(VPOS_TPA_FUHRE_ORT.zeitstempel) );
		
		// wird bisher über laendercode gemacht.. jetzt nur noch über ID
		String land = 			(ort.getUfs(VPOS_TPA_FUHRE_ORT.laendercode));
		Long idLand = trans.getLandIDFromLaendercode(land);
		setID_LAND(idLand);

	
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (ort.getUfs(VPOS_TPA_FUHRE_ORT.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( ort);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		}
		
		
	}



	
	/**
	 * Erzeugt eine Strecken-Station für die übergebene Ladung
	 * @author manfred
	 * @date 17.01.2018
	 *
	 * @param iD_BG_STATION
	 * @param iD_MANDANT
	 * @throws myException 
	 */
	public jt_bg_station( BG_FUHREN_Transformation trans, jt_bg_ladung ladung) throws myException {
		this();

		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		ladung.set_jt_bg_station(this);
		this.set_jt_bg_ladung(ladung);
		this.setID_BG_VEKTOR(ladung.getID_BG_VEKTOR().ValuePlain());
		
		
		setID_MANDANT(ladung.getID_MANDANT().lValue);

		setERZEUGT_AM(ladung.getERZEUGT_AM().dValue);
		setERZEUGT_VON(ladung.getERZEUGT_VON().ValuePlain());
		
		setLETZTE_AENDERUNG(ladung.getLETZTE_AENDERUNG().dValue);
		setGEAENDERT_VON(ladung.getGEAENDERT_VON().ValuePlain());
		
		setNAME1					(EN_VEKTOR_TYP.ST.name() );
		setPLZ						("-");
		setID_ADRESSE				(bibSES.get_ID_ADRESSE_LAGER_STRECKE_longValue() );
		setID_ADRESSE_BASIS			(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null) );
		
		// land aus der originalen Station übernehmen 
		//setID_LAND				(ladung.get_jt_bg_station().getID_LAND().lValue);
		setID_LAND					(bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(null));
		
		
		setTIMESTAMP_IN_MASK		( ladung.getTIMESTAMP_IN_MASK().ValuePlain());
		
		
		
	}


	
	/**
	 * Erzeugt eine Korrekturbuchungs-Station oder die Ladungs-Station der Korrektur
	 * @author manfred
	 * @date 13.02.2018
	 *
	 * @param bg_HAND_Transformation
	 * @param oKonto
	 * @param b
	 * @throws myException 
	 */
	public jt_bg_station(BG_HAND_Transformation bg_HAND_Transformation, jt_bg_ladung ladung, Rec20_Lager_Konto oKonto, boolean bErzeugeKorrekturStation) throws myException {
		this();

		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		ladung.set_jt_bg_station(this);
		this.set_jt_bg_ladung(ladung);
		this.setID_BG_VEKTOR(ladung.getID_BG_VEKTOR().ValuePlain());
		
		
		setID_MANDANT(ladung.getID_MANDANT().lValue);
		setERZEUGT_AM(ladung.getERZEUGT_AM().dValue);
		setERZEUGT_VON(ladung.getERZEUGT_VON().ValuePlain());
		setLETZTE_AENDERUNG(ladung.getLETZTE_AENDERUNG().dValue);
		setGEAENDERT_VON(ladung.getGEAENDERT_VON().ValuePlain());
		
		if (bErzeugeKorrekturStation){
			setNAME1					(EN_VEKTOR_TYP.KORR.name() );
			setPLZ						("-");
			setID_ADRESSE				(bibSES.get_ID_ADRESSE_LAGER_KORREKTUR_HAND_longValue());
			setID_ADRESSE_BASIS			(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null) );
			setID_LAND					(bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(null));
		} else {
			setID_ADRESSE( oKonto.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager));

			// die tatsächliche Lager-Station
			RECORD_ADRESSE rec = new RECORD_ADRESSE(oKonto.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager));
			
			setNAME1	(rec.get_NAME1_cUF_NN(null));
			setNAME2	(rec.get_NAME2_cUF_NN(null));
			setNAME3	(rec.get_NAME3_cUF_NN(null));
			setPLZ		(rec.get_PLZ_cUF_NN(null));
			setORT		(rec.get_ORT_cUF());
			setSTRASSE	(rec.get_STRASSE_cUF());
			setID_LAND	(rec.get_ID_LAND_lValue(bibALL.get_RECORD_MANDANT().get_ID_LAND_lValue(null) ) );
			setID_ADRESSE_BASIS			(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(null) );
			
		}

		
	}
	




	public DOString getID_BG_STATION() {
	    return ID_BG_STATION;
	}
	

	public jt_bg_station setID_BG_STATION(String pID_BG_STATION) {
	    ID_BG_STATION.setValue(pID_BG_STATION);
	    return this;
	}

	public jt_bg_ladung get_jt_bg_ladung() {
		return o_jt_bg_ladung;
	}


	public jt_bg_station set_jt_bg_ladung(jt_bg_ladung o_jt_bg_ladung) {
		this.o_jt_bg_ladung = o_jt_bg_ladung;
		return this;
	}
	
	public DOString getID_BG_VEKTOR() {
	    return ID_BG_VEKTOR;
	}

	public jt_bg_station setID_BG_VEKTOR(String pID_BG_VEKTOR) {
	    ID_BG_VEKTOR.setValue(pID_BG_VEKTOR);
	    return this;
	}

 
	//date
	public DODate getLETZTE_AENDERUNG() {
	    return LETZTE_AENDERUNG;
	}

	public jt_bg_station setLETZTE_AENDERUNG(Date pLETZTE_AENDERUNG) {
	    LETZTE_AENDERUNG.setValue(pLETZTE_AENDERUNG);
	    return this;
	}


	public DODate getERZEUGT_AM() {
	    return ERZEUGT_AM;
	}

	public jt_bg_station setERZEUGT_AM(Date pERZEUGT_AM) {
	    ERZEUGT_AM.setValue(pERZEUGT_AM);
	    return this;
	}





	public DOLong getID_MANDANT() {
	    return ID_MANDANT;
	}

	public jt_bg_station setID_MANDANT(Long pID_MANDANT) {
	    ID_MANDANT.setValue(pID_MANDANT);
	    return this;
	}


	public DOLong getID_LAND() {
	    return ID_LAND;
	}

	public jt_bg_station setID_LAND(Long pID_LAND) {
	    ID_LAND.setValue(pID_LAND);
	    return this;
	}


	public DOLong getID_BG_RECH_BLOCK() {
	    return ID_BG_RECH_BLOCK;
	}

	public jt_bg_station setID_BG_RECH_BLOCK(Long pID_BG_RECH_BLOCK) {
	    ID_BG_RECH_BLOCK.setValue(pID_BG_RECH_BLOCK);
	    return this;
	}


	public DOLong getID_ADRESSE() {
	    return ID_ADRESSE;
	}

	public jt_bg_station setID_ADRESSE(Long pID_ADRESSE) {
	    ID_ADRESSE.setValue(pID_ADRESSE);
	    return this;
	}


	public DOLong getID_ADRESSE_BASIS() {
	    return ID_ADRESSE_BASIS;
	}

	public jt_bg_station setID_ADRESSE_BASIS(Long pID_ADRESSE_BASIS) {
	    ID_ADRESSE_BASIS.setValue(pID_ADRESSE_BASIS);
	    return this;
	}


	public DOLong getID_WIEGEKARTE() {
	    return ID_WIEGEKARTE;
	}

	public jt_bg_station setID_WIEGEKARTE(Long pID_WIEGEKARTE) {
	    ID_WIEGEKARTE.setValue(pID_WIEGEKARTE);
	    return this;
	}


	public DOString getID_BG_STORNO_INFO() {
	    return ID_BG_STORNO_INFO;
	}

	public jt_bg_station setID_BG_STORNO_INFO(String pID_BG_STORNO_INFO) {
	    ID_BG_STORNO_INFO.setValue(pID_BG_STORNO_INFO);
	    return this;
	}


	public DOString getID_BG_DEL_INFO() {
	    return ID_BG_DEL_INFO;
	}

	public jt_bg_station setID_BG_DEL_INFO(String pID_BG_DEL_INFO) {
	    ID_BG_DEL_INFO.setValue(pID_BG_DEL_INFO);
	    return this;
	}


	public DOLong getID_ZAHLUNGSBEDINGUNGEN() {
	    return ID_ZAHLUNGSBEDINGUNGEN;
	}

	public jt_bg_station setID_ZAHLUNGSBEDINGUNGEN(Long pID_ZAHLUNGSBEDINGUNGEN) {
	    ID_ZAHLUNGSBEDINGUNGEN.setValue(pID_ZAHLUNGSBEDINGUNGEN);
	    return this;
	}



	//bigDecimal
	public DOBigDecimal getKONTROLLMENGE() {
	    return KONTROLLMENGE;
	}

	public jt_bg_station setKONTROLLMENGE(BigDecimal pKONTROLLMENGE) {
	    KONTROLLMENGE.setValue(pKONTROLLMENGE);
	    return this;
	}



	//String
	public DOString getGEAENDERT_VON() {
	    return GEAENDERT_VON;
	}

	public jt_bg_station setGEAENDERT_VON(String pGEAENDERT_VON) {
	    GEAENDERT_VON.setValue(pGEAENDERT_VON);
	    return this;
	}


	public DOString getERZEUGT_VON() {
	    return ERZEUGT_VON;
	}

	public jt_bg_station setERZEUGT_VON(String pERZEUGT_VON) {
	    ERZEUGT_VON.setValue(pERZEUGT_VON);
	    return this;
	}


	public DOString getWIEGEKARTENKENNER() {
	    return WIEGEKARTENKENNER;
	}

	public jt_bg_station setWIEGEKARTENKENNER(String pWIEGEKARTENKENNER) {
	    WIEGEKARTENKENNER.setValue(pWIEGEKARTENKENNER);
	    return this;
	}


	public DOString getNAME1() {
	    return NAME1;
	}

	public jt_bg_station setNAME1(String pNAME1) {
	    NAME1.setValue(pNAME1);
	    return this;
	}


	public DOString getNAME2() {
	    return NAME2;
	}

	public jt_bg_station setNAME2(String pNAME2) {
	    NAME2.setValue(pNAME2);
	    return this;
	}


	public DOString getNAME3() {
	    return NAME3;
	}

	public jt_bg_station setNAME3(String pNAME3) {
	    NAME3.setValue(pNAME3);
	    return this;
	}


	public DOString getSTRASSE() {
	    return STRASSE;
	}

	public jt_bg_station setSTRASSE(String pSTRASSE) {
	    STRASSE.setValue(pSTRASSE);
	    return this;
	}


	public DOString getHAUSNUMMER() {
	    return HAUSNUMMER;
	}

	public jt_bg_station setHAUSNUMMER(String pHAUSNUMMER) {
	    HAUSNUMMER.setValue(pHAUSNUMMER);
	    return this;
	}


	public DOString getPLZ() {
	    return PLZ;
	}

	public jt_bg_station setPLZ(String pPLZ) {
	    PLZ.setValue(pPLZ);
	    return this;
	}


	public DOString getORT() {
	    return ORT;
	}

	public jt_bg_station setORT(String pORT) {
	    ORT.setValue(pORT);
	    return this;
	}


	public DOString getORTZUSATZ() {
	    return ORTZUSATZ;
	}

	public jt_bg_station setORTZUSATZ(String pORTZUSATZ) {
	    ORTZUSATZ.setValue(pORTZUSATZ);
	    return this;
	}


	public DOString getOEFFNUNGSZEITEN() {
	    return OEFFNUNGSZEITEN;
	}

	public jt_bg_station setOEFFNUNGSZEITEN(String pOEFFNUNGSZEITEN) {
	    OEFFNUNGSZEITEN.setValue(pOEFFNUNGSZEITEN);
	    return this;
	}


	public DOString getBESTELLNUMMER() {
	    return BESTELLNUMMER;
	}

	public jt_bg_station setBESTELLNUMMER(String pBESTELLNUMMER) {
	    BESTELLNUMMER.setValue(pBESTELLNUMMER);
	    return this;
	}


	public DOString getTELEFON() {
	    return TELEFON;
	}

	public jt_bg_station setTELEFON(String pTELEFON) {
	    TELEFON.setValue(pTELEFON);
	    return this;
	}


	public DOString getFAX() {
	    return FAX;
	}

	public jt_bg_station setFAX(String pFAX) {
	    FAX.setValue(pFAX);
	    return this;
	}


	public DOString getLIEFERINFO_TPA() {
	    return LIEFERINFO_TPA;
	}

	public jt_bg_station setLIEFERINFO_TPA(String pLIEFERINFO_TPA) {
	    LIEFERINFO_TPA.setValue(pLIEFERINFO_TPA);
	    return this;
	}


	public DOString getTIMESTAMP_IN_MASK() {
	    return TIMESTAMP_IN_MASK;
	}

	public jt_bg_station setTIMESTAMP_IN_MASK(String pTIMESTAMP_IN_MASK) {
	    TIMESTAMP_IN_MASK.setValue(pTIMESTAMP_IN_MASK);
	    return this;
	}


	public DOString getZAHLUNGSBEDINGUNGEN() {
	    return ZAHLUNGSBEDINGUNGEN;
	}

	public jt_bg_station setZAHLUNGSBEDINGUNGEN(String pZAHLUNGSBEDINGUNGENN) {
	    ZAHLUNGSBEDINGUNGEN.setValue(pZAHLUNGSBEDINGUNGENN);
	    return this;
	}


	

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {
		
		m_vDataObjects.addElement ( ID_BG_STATION );
		m_vDataObjects.addElement ( ID_MANDANT );
		m_vDataObjects.addElement ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement ( GEAENDERT_VON );
		m_vDataObjects.addElement ( ERZEUGT_VON );
		m_vDataObjects.addElement ( ERZEUGT_AM );
		m_vDataObjects.addElement ( WIEGEKARTENKENNER );
		m_vDataObjects.addElement ( KONTROLLMENGE );
		m_vDataObjects.addElement ( NAME1 );
		m_vDataObjects.addElement ( NAME2 );
		m_vDataObjects.addElement ( NAME3 );
		m_vDataObjects.addElement ( STRASSE );
		m_vDataObjects.addElement ( HAUSNUMMER );
		m_vDataObjects.addElement ( PLZ );
		m_vDataObjects.addElement ( ORT );
		m_vDataObjects.addElement ( ORTZUSATZ );
		m_vDataObjects.addElement ( OEFFNUNGSZEITEN );
		m_vDataObjects.addElement ( BESTELLNUMMER );
		m_vDataObjects.addElement ( TELEFON );
		m_vDataObjects.addElement ( FAX );
		m_vDataObjects.addElement ( LIEFERINFO_TPA );
		m_vDataObjects.addElement ( TIMESTAMP_IN_MASK );
		m_vDataObjects.addElement ( ID_LAND );
		m_vDataObjects.addElement ( ID_BG_RECH_BLOCK );
		m_vDataObjects.addElement ( ID_ADRESSE );
		m_vDataObjects.addElement ( ID_ADRESSE_BASIS );
		m_vDataObjects.addElement ( ID_WIEGEKARTE );
		m_vDataObjects.addElement ( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement ( ID_BG_DEL_INFO );
		m_vDataObjects.addElement ( ID_ZAHLUNGSBEDINGUNGEN );
		m_vDataObjects.addElement ( ZAHLUNGSBEDINGUNGEN );
		m_vDataObjects.addElement ( ID_BG_VEKTOR );
	}




	
}
