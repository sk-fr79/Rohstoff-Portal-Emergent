/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_Lager_Konto;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_OwnAdresses;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOBigDecimal;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.REC_Base;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;


/**
 * @author manfred

 *
 */
public class jt_bg_station extends REC_Base {

	

	private DOString 	ID_BG_STATION = new DOString(BG_STATION.id_bg_station,null,"").setRaw(true);
	private DOLong 		ID_MANDANT = new DOLong(BG_STATION.id_mandant);
	private DOString 	ID_BG_STORNO_INFO = new DOString(BG_STATION.id_bg_storno_info,null,"").setRaw(true);
	private DOString 	ID_BG_DEL_INFO = new DOString(BG_STATION.id_bg_del_info,null,"").setRaw(true);
	private DODate 		LETZTE_AENDERUNG = new DODate(BG_STATION.letzte_aenderung);
	private DOString 	GEAENDERT_VON = new DOString(BG_STATION.geaendert_von);
	private DOString 	ERZEUGT_VON = new DOString(BG_STATION.erzeugt_von);
	private DODate 		ERZEUGT_AM = new DODate(BG_STATION.erzeugt_am);
	private DOString 	ID_BG_VEKTOR = new DOString(BG_STATION.id_bg_vektor,null,"").setRaw(true);
	private DOString 	WIEGEKARTENKENNER = new DOString(BG_STATION.wiegekartenkenner);
	private DOBigDecimal KONTROLLMENGE = new DOBigDecimal(BG_STATION.kontrollmenge);
	private DOString NAME1 = new DOString(BG_STATION.name1);
	private DOString NAME2 = new DOString(BG_STATION.name2);
	private DOString NAME3 = new DOString(BG_STATION.name3);
	private DOString STRASSE = new DOString(BG_STATION.strasse);
	private DOString HAUSNUMMER = new DOString(BG_STATION.hausnummer);
	private DOString PLZ = new DOString(BG_STATION.plz);
	private DOString ORT = new DOString(BG_STATION.ort);
	private DOString ORTZUSATZ = new DOString(BG_STATION.ortzusatz);
	private DOString OEFFNUNGSZEITEN = new DOString(BG_STATION.oeffnungszeiten);
	private DOString TELEFON = new DOString(BG_STATION.telefon);
	private DOString FAX = new DOString(BG_STATION.fax);
	private DOString TIMESTAMP_IN_MASK = new DOString(BG_STATION.timestamp_in_mask);
	private DOString UMSATZSTEUERID = new DOString(BG_STATION.umsatzsteuerid);
	private DOString UMSATZSTEUERLKZ = new DOString(BG_STATION.umsatzsteuerlkz);
	private DOLong ID_LAND = new DOLong(BG_STATION.id_land);
	private DOLong ID_ADRESSE = new DOLong(BG_STATION.id_adresse);
	private DOLong ID_ADRESSE_BASIS = new DOLong(BG_STATION.id_adresse_basis);
	private DOLong ID_ADRESSE_BESITZER_LDG = new DOLong(BG_STATION.id_adresse_besitz_ldg);
	private DOLong ID_WIEGEKARTE = new DOLong(BG_STATION.id_wiegekarte);
	private DOString POS_IN_MAKS = new DOString(BG_STATION.pos_in_mask);
	

//	private jt_bg_atom _jt_bg_atom = null;


	// del und storno-INFOS
	private REC_Base     _jt_bg_del_info = null;
	private REC_Base 	 _jt_bg_storno_info = null;

	
	/**
	 * @author manfred
	 * @date 17.01.2018
	 *
	 */
	public jt_bg_station(conv_helper helper) {
		super(BG_STATION._tab());
		_helper = helper;
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
	public jt_bg_station( Bewegung_Fuhren_Transformation trans,  Rec21_VPOS_TPA_FUHRE_ext fuhre, boolean bIstLadeseite, conv_helper helper) throws myException {
		this(helper);

		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

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
		setTELEFON					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.tel_lieferant)				: fuhre.getUfs(VPOS_TPA_FUHRE.tel_abnehmer));
		setFAX						(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.fax_lieferant)				: fuhre.getUfs(VPOS_TPA_FUHRE.fax_abnehmer));

		setTIMESTAMP_IN_MASK		( fuhre.getUfs(VPOS_TPA_FUHRE.zeitstempel));
		
		setID_WIEGEKARTE			(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_wiegekarte_lief) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_wiegekarte_abn));
		setID_ADRESSE				(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_start) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_lager_ziel));
		setID_ADRESSE_BASIS			(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_start) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_adresse_ziel));

		setKONTROLLMENGE			(bIstLadeseite ? fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn) );
		
		// wird bisher über laendercode gemacht.. jetzt nur noch über ID
		String land = 				(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.l_laendercode) : fuhre.getUfs(VPOS_TPA_FUHRE.a_laendercode));
		Long idLand = trans.getLandIDFromLaendercode(land);
		setID_LAND(idLand);
		
//		--> debug temp
		setID_ADRESSE_BESITZER_LDG  (getID_ADRESSE().Value());
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre,_helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (fuhre.getUfs(VPOS_TPA_FUHRE.deleted,"N").equals("Y")  ){
			jt_bg_del_info i = new jt_bg_del_info( fuhre,_helper);
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
	public jt_bg_station( Bewegung_Fuhren_Transformation trans, Rec21_VPOS_TPA_FUHRE_ORT_ext ort, Rec21_VPOS_TPA_FUHRE_ext fuhre , conv_helper helper) throws myException {
		this(helper);

		boolean bIstLadeseite = ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"-").equals("EK");
		
		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

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
		setTELEFON					(ort.getUfs(VPOS_TPA_FUHRE_ORT.tel ));
		setFAX						(ort.getUfs(VPOS_TPA_FUHRE_ORT.fax ));
		
		setID_ADRESSE				(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse_lager ));
		setID_ADRESSE_BASIS			(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_adresse ));
		
//		--> debug temp
		setID_ADRESSE_BESITZER_LDG  (getID_ADRESSE().Value());
		
		setTIMESTAMP_IN_MASK		( ort.getUfs(VPOS_TPA_FUHRE_ORT.zeitstempel) );
		
		// wird bisher über laendercode gemacht.. jetzt nur noch über ID
		String land = 			(ort.getUfs(VPOS_TPA_FUHRE_ORT.laendercode));
		Long idLand = trans.getLandIDFromLaendercode(land);
		setID_LAND(idLand);

	
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre,_helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (ort.getUfs(VPOS_TPA_FUHRE_ORT.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( ort,_helper);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		}
		
		
	}



	


	

	/**
	 * 
	 * @author manfred
	 * @date 08.11.2019
	 *
	 * @param bg_HAND_Transformation
	 * @param oKonto
	 * @param helper
	 * @throws myException
	 */
	public jt_bg_station(Bewegung_Transformation_HAND bg_HAND_Transformation, Rec21_Lager_Konto	oKonto, conv_helper helper) throws myException {
		this(helper);

		boolean bIstLadeseite = oKonto.get_ufs_dbVal(LAGER_KONTO.buchungstyp,"-").equals("WA");
		
		setPOS_IN_MAKS(EnTabKeyInMask.S1.dbVal());
		
		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

		setID_MANDANT(oKonto.get_raw_resultValue_Long(LAGER_KONTO.id_mandant) );
		setLETZTE_AENDERUNG(oKonto.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(oKonto.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(oKonto.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(oKonto.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));
		
		setID_ADRESSE				(oKonto.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager ));
		setID_ADRESSE_BASIS			(oKonto.get_raw_resultValue_Long(LAGER_KONTO.id_adresse_lager ));
		
		Rec21_adresse 	rAdresse = new Rec21_adresse()._fill_id(getID_ADRESSE().lValue);
		setNAME1					(rAdresse.get_ufs_dbVal(ADRESSE.name1));
		setNAME2					(rAdresse.get_ufs_dbVal(ADRESSE.name2));
		setNAME3					(rAdresse.get_ufs_dbVal(ADRESSE.name3));
		setSTRASSE					(rAdresse.get_ufs_dbVal(ADRESSE.strasse));
		setHAUSNUMMER				(rAdresse.get_ufs_dbVal(ADRESSE.hausnummer));
		setPLZ						(rAdresse.get_ufs_dbVal(ADRESSE.plz));
		setORT						(rAdresse.get_ufs_dbVal(ADRESSE.ort));
		
		setKONTROLLMENGE			(oKonto.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge));
		
//		--> debug temp
		setID_ADRESSE_BESITZER_LDG  (getID_ADRESSE().Value());
		setTIMESTAMP_IN_MASK		( oKonto.getUfs(LAGER_KONTO.buchungsdatum) );
		
		Long idLand= 			(rAdresse.get_raw_resultValue_Long(ADRESSE.id_land));
		setID_LAND(idLand);
	
		if (oKonto.getUfs(LAGER_KONTO.storno,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( oKonto,_helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
	}
	
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 08.11.2019
	 *
	 * @param bg_HAND_Transformation
	 * @param oKonto
	 * @param rAdresse
	 * @param helper
	 * @throws myException
	 */
	public jt_bg_station(Bewegung_Transformation_HAND bg_HAND_Transformation, Rec21_Lager_Konto oKonto, Rec21_adresse rAdresse, conv_helper helper) throws myException {
		this(helper);
		
		
		setID_BG_STATION( BG_STATION._tab().seq_nextval());
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

		setID_MANDANT(oKonto.get_raw_resultValue_Long(LAGER_KONTO.id_mandant) );
		setLETZTE_AENDERUNG(oKonto.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(oKonto.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(oKonto.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(oKonto.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));
		
		setID_ADRESSE				(rAdresse.get_raw_resultValue_Long(ADRESSE.id_adresse));

		// Hauptadresse suchen	
		RecList21 rl2 = new RecList21(_TAB.lieferadresse);
		SEL sel 	= new SEL(LIEFERADRESSE.id_adresse_basis).FROM(_TAB.lieferadresse).WHERE(new vglParam(LIEFERADRESSE.id_adresse_liefer));
		SqlStringExtended  sql = new SqlStringExtended(sel.s())
										._addParameters(new VEK<ParamDataObject>()
										._a(new Param_Long("",rAdresse.get_raw_resultValue_Long(ADRESSE.id_adresse))));
		rl2._fill(sql);
		if (rl2.size() > 0) {
			setID_ADRESSE_BASIS			(rl2.get(0).get_raw_resultValue_Long(LIEFERADRESSE.id_adresse_basis));
		} else {
			setID_ADRESSE_BASIS			(rAdresse.get_raw_resultValue_Long(ADRESSE.id_adresse));
		}
			
		setNAME1					(rAdresse.get_ufs_dbVal(ADRESSE.name1));
		setNAME2					(rAdresse.get_ufs_dbVal(ADRESSE.name2));
		setNAME3					(rAdresse.get_ufs_dbVal(ADRESSE.name3));
		setSTRASSE					(rAdresse.get_ufs_dbVal(ADRESSE.strasse));
		setHAUSNUMMER				(rAdresse.get_ufs_dbVal(ADRESSE.hausnummer));
		setPLZ						(rAdresse.get_ufs_dbVal(ADRESSE.plz));
		setORT						(rAdresse.get_ufs_dbVal(ADRESSE.ort));
		
		setKONTROLLMENGE			(oKonto.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge));
		
//		--> debug temp
		setID_ADRESSE_BESITZER_LDG  (getID_ADRESSE_BASIS().Value());
		setTIMESTAMP_IN_MASK		( oKonto.getUfs(LAGER_KONTO.buchungsdatum) );
		
	
		if (oKonto.getUfs(LAGER_KONTO.storno,"N").equals("Y")   ){
			jt_bg_storno_info i = new jt_bg_storno_info( oKonto,_helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
	}
	
	
	
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
	

	public DOString getID_BG_STATION() {
	    return ID_BG_STATION;
	}
	

	public jt_bg_station setID_BG_STATION(String pID_BG_STATION) {
	    ID_BG_STATION.setValue(pID_BG_STATION);
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


	public DOString getTIMESTAMP_IN_MASK() {
	    return TIMESTAMP_IN_MASK;
	}

	public jt_bg_station setTIMESTAMP_IN_MASK(String pTIMESTAMP_IN_MASK) {
	    TIMESTAMP_IN_MASK.setValue(pTIMESTAMP_IN_MASK);
	    return this;
	}

	
	public DOString getUMSATZSTEUERID() {
		return UMSATZSTEUERID;
	}

	public jt_bg_station setUMSATZSTEUERID(String uMSATZSTEUERID) {
		UMSATZSTEUERID.setValue(uMSATZSTEUERID);
		return this;
	}

	public DOString getUMSATZSTEUERLKZ() {
		return UMSATZSTEUERLKZ;
	}

	public jt_bg_station setUMSATZSTEUERLKZ(String uMSATZSTEUERLKZ) {
		UMSATZSTEUERLKZ.setValue( uMSATZSTEUERLKZ );
		return this;
	}

	public DOLong getID_ADRESSE_BESITZER_LDG() {
		return ID_ADRESSE_BESITZER_LDG;
	}

	public jt_bg_station setID_ADRESSE_BESITZER_LDG(Long iD_ADRESSE_BESITZER_LDG) {
		ID_ADRESSE_BESITZER_LDG.setValue(iD_ADRESSE_BESITZER_LDG);
		return this;
	}

	public DOString getPOS_IN_MAKS() {
		return POS_IN_MAKS;
	}

	public jt_bg_station setPOS_IN_MAKS(String pOS_IN_MAKS) {
		POS_IN_MAKS.setValue(pOS_IN_MAKS);
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
		m_vDataObjects.addElement ( TELEFON );
		m_vDataObjects.addElement ( FAX );
		m_vDataObjects.addElement ( TIMESTAMP_IN_MASK );
		m_vDataObjects.addElement ( ID_LAND );
		m_vDataObjects.addElement ( ID_ADRESSE );
		m_vDataObjects.addElement ( ID_ADRESSE_BASIS );
		m_vDataObjects.addElement ( ID_WIEGEKARTE );
		m_vDataObjects.addElement ( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement ( ID_BG_DEL_INFO );
		m_vDataObjects.addElement ( ID_BG_VEKTOR );
		m_vDataObjects.addElement ( UMSATZSTEUERID );
		m_vDataObjects.addElement ( UMSATZSTEUERLKZ );
		m_vDataObjects.addElement ( POS_IN_MAKS );
		m_vDataObjects.addElement(  ID_ADRESSE_BESITZER_LDG );
		
	}






	
}
