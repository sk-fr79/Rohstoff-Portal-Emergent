/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
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
import rohstoff.businesslogic.bewegung.lager.list_saldo.BG_Lager_Saldo_CONST.TRANSLATOR;

/**
 * @author manfred
 * @date 16.01.2018
 *
 */
public class jt_bg_ladung extends REC_Base {
	


	private DOString ID_BG_LADUNG = new DOString("ID_BG_LADUNG",null,"");
	private DOString ID_BG_ATOM = new DOString("ID_BG_ATOM",null,"");
	private DOLong ID_MANDANT = new DOLong("ID_MANDANT");

	private DODate LETZTE_AENDERUNG = new DODate("LETZTE_AENDERUNG");
	private DOString GEAENDERT_VON = new DOString("GEAENDERT_VON");
	private DOString ERZEUGT_VON = new DOString("ERZEUGT_VON");
	private DODate ERZEUGT_AM = new DODate("ERZEUGT_AM");

	private DODate LEISTUNGSDATUM = new DODate("LEISTUNGSDATUM");

	private DOString ANR1 = new DOString("ANR1");
	private DOString ANR2 = new DOString("ANR2");
	private DOString ARTBEZ1 = new DOString("ARTBEZ1");
	private DOString ARTBEZ2 = new DOString("ARTBEZ2");
	private DOBigDecimal MENGE = new DOBigDecimal("MENGE");
	private DOBigDecimal MENGE_NETTO = new DOBigDecimal("MENGE_NETTO");
	private DOBigDecimal ABZUG_MENGE = new DOBigDecimal("ABZUG_MENGE");
	private DOBigDecimal E_PREIS_RESULT_NETTO_MGE = new DOBigDecimal("E_PREIS_RESULT_NETTO_MGE");
	private DOBigDecimal E_PREIS_RESULT_BRUTTO_MGE = new DOBigDecimal("E_PREIS_RESULT_BRUTTO_MGE");
	private DOBigDecimal GESAMTPREIS = new DOBigDecimal("GESAMTPREIS");
	private DOBigDecimal GPREIS_ABZ_MGE = new DOBigDecimal("GPREIS_ABZ_MGE");
	private DOBigDecimal GPREIS_ABZ_AUF_NETTOMGE = new DOBigDecimal("GPREIS_ABZ_AUF_NETTOMGE");
	private DOBigDecimal GPREIS_ABZ_VORAUSZAHLUNG = new DOBigDecimal("GPREIS_ABZ_VORAUSZAHLUNG");
	private DOString KONTRAKTZWANG = new DOString("KONTRAKTZWANG");
	private DOString POSTENNUMMER = new DOString("POSTENNUMMER");
	private DOString EU_STEUER_VERMERK = new DOString("EU_STEUER_VERMERK");
	private DOString BEMERKUNG_EXTERN = new DOString("BEMERKUNG_EXTERN");
	private DOString BEMERKUNG_INTERN = new DOString("BEMERKUNG_INTERN");
	private DOLong MENGENVORZEICHEN = new DOLong("MENGENVORZEICHEN");
	private DOString TIMESTAMP_IN_MASK = new DOString("TIMESTAMP_IN_MASK");
	private DOBigDecimal KOSTEN_PRODUKT = new DOBigDecimal("KOSTEN_PRODUKT");
	private DOString ID_BG_STATION = new DOString("ID_BG_STATION",null,"");
	private DOLong ID_ARTIKEL_BEZ = new DOLong("ID_ARTIKEL_BEZ");
	private DOLong ID_ARTIKEL = new DOLong("ID_ARTIKEL");
	private DOLong ID_ADRESSE_BESITZER = new DOLong("ID_ADRESSE_BESITZER");
	private DOLong ID_BG_PRUEFPROT_MENGE = new DOLong("ID_BG_PRUEFPROT_MENGE");
	private DOLong ID_BG_PRUEFPROT_PREIS = new DOLong("ID_BG_PRUEFPROT_PREIS");
	private DOLong ID_BG_PRUEFPROT_ABSCHLUSS = new DOLong("ID_BG_PRUEFPROT_ABSCHLUSS");
	private DOLong ID_VPOS_KON = new DOLong("ID_VPOS_KON");
	private DOLong ID_VPOS_STD = new DOLong("ID_VPOS_STD");
	private DOLong ID_TAX = new DOLong("ID_TAX");
	private DOString STEUERSATZ = new DOString("STEUERSATZ");
	private DOLong 		ID_LAGER_KONTO = new DOLong("ID_LAGER_KONTO");
	private DOString 	ID_BG_VEKTOR = new DOString("ID_BG_VEKTOR",null,"");

	private DOString ID_BG_STORNO_INFO = new DOString("ID_BG_STORNO_INFO",null,"");
	private DOString ID_BG_DEL_INFO = new DOString("ID_BG_DEL_INFO",null,"");
	
	/**
	 * die Station der Ladung
	 */
	private jt_bg_station o_jt_bg_station = null;
	
	/**
	 * das Atom der Ladung
	 */
	private jt_bg_atom o_jt_bg_atom = null;
	
	// del und storno-INFOS
	private REC_Base     _jt_bg_del_info = null;
	private REC_Base 	 _jt_bg_storno_info = null;

	public jt_bg_ladung set_jt_bg_del_info(jt_bg_del_info delInfo){
		this._jt_bg_del_info = delInfo;
		return this;
	}
	
	public jt_bg_del_info get_jt_bg_del_info(){
		return (jt_bg_del_info)_jt_bg_del_info;
	}
	

	public jt_bg_ladung set_jt_bg_storno_info(jt_bg_storno_info stornoInfo){
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
	public jt_bg_ladung() {
		super("JT_BG_LADUNG");
		this.initFieldList();
	} 

	
	
	
	public jt_bg_ladung(BG_FUHREN_Transformation trans,  Rec20_VPOS_TPA_FUHRE_ext fuhre, boolean bIstLadeseite) throws myException{
		this();
//		setID_BG_LADUNG(BG_LADUNG._tab().seq_nextval());
		setID_BG_STATION(BG_STATION._tab().seq_currval());
		setID_BG_ATOM(BG_ATOM._tab().seq_currval());
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant) );
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));

		
		setMENGENVORZEICHEN		(bIstLadeseite ? -1L : +1L) ;
		
		setLEISTUNGSDATUM		(bIstLadeseite ? fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_aufladen) : fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abladen) );
		setANR1					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.anr1_ek): fuhre.getUfs(VPOS_TPA_FUHRE.anr1_vk) );
		setANR2					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.anr2_ek): fuhre.getUfs(VPOS_TPA_FUHRE.anr2_vk) );
		setARTBEZ1				(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.artbez1_ek): fuhre.getUfs(VPOS_TPA_FUHRE.artbez1_vk) ) ;
		setARTBEZ2				(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.artbez2_ek): fuhre.getUfs(VPOS_TPA_FUHRE.artbez2_vk) ) ;

		setID_ARTIKEL_BEZ		(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel_bez_ek): fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel_bez_vk) ) ;
//		setID_ARTIKEL			(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel) ) ;
		setID_ARTIKEL			(trans.getArtikelIDFromArtikelBezID(getID_ARTIKEL_BEZ().ValuePlain()));
		
		
		
		
		// ermitteln der Menge abhängig von Ladeseite und schalter lademenge_gutschrift/ablademenge-rechnung
		String					sIDAdresse = (bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.id_adresse_lager_start) : fuhre.getUfs(VPOS_TPA_FUHRE.id_adresse_lager_ziel) ) ;
		boolean 				bIstLager = trans.m_vLagerOrte.contains(sIDAdresse);

		BigDecimal bdMenge = BigDecimal.ZERO;
		if(bIstLadeseite){
			if (bIstLager){
				// ladeseite und lager, dann immer die Lademenge
				bdMenge = fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief) ;
				if (bdMenge == null || 
						( 
								bdMenge.equals(BigDecimal.ZERO) 
								&& 
								fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_lief,BigDecimal.ZERO).compareTo(bdMenge) > 0 
								) 
				){
					bdMenge = fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_lief) ;
				}
			} else {
				// sonst prüfen...
				if( fuhre.getUfs(VPOS_TPA_FUHRE.lademenge_gutschrift,"N").equalsIgnoreCase("Y")){
					bdMenge = (fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief) ) ;
				} else {
					bdMenge = (fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_lief) ) ;
				}
			}
		} else { 
			// abladeseite
			if (bIstLager){
				bdMenge =(fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn) );
				if (bdMenge == null){
					bdMenge = (fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_abn) );
				}
			} else {
				if (fuhre.getUfs(VPOS_TPA_FUHRE.ablademenge_rechnung,"N").equalsIgnoreCase("Y")){
					bdMenge = (fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn) ) ;
				} else {
					bdMenge = (fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_abn) ) ;
				}
			}
		}
		setMENGE				(bdMenge);
		
//		setMENGE				(bIstLadeseite ? fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn) ) ;
		setABZUG_MENGE			(bIstLadeseite ? fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.abzug_lademenge_lief): fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.abzug_ablademenge_abn) ) ;
		
		if (MENGE.bdValue != null) {
				setMENGE_NETTO	(MENGE.bdValue.subtract(ABZUG_MENGE.bdValue != null ? ABZUG_MENGE.bdValue : BigDecimal.ZERO) );
		}
		
		setE_PREIS_RESULT_NETTO_MGE	(bIstLadeseite ? fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.epreis_result_netto_mge_ek) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.epreis_result_netto_mge_vk) ) ;
		
		setPOSTENNUMMER					(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.postennummer_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.postennummer_vk) ) ;
		setTIMESTAMP_IN_MASK			(fuhre.getUfs(VPOS_TPA_FUHRE.zeitstempel) );
		setID_VPOS_KON					(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_kon_ek): fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_kon_vk) ) ;
		setID_VPOS_STD					(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_std_ek): fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_std_vk)) ;
		
		setID_TAX						(bIstLadeseite ? fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_tax_ek): fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_tax_vk) ) ;
		setEU_STEUER_VERMERK			(bIstLadeseite ? fuhre.getUfs(VPOS_TPA_FUHRE.eu_steuer_vermerk_ek): fuhre.getUfs(VPOS_TPA_FUHRE.eu_steuer_vermerk_vk));
		
		setBEMERKUNG_INTERN				(fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung) ) ;
		
		
		//setE_PREIS_RESULT_BRUTTO_MGE(pE_PREIS_RESULT_BRUTTO_MGE)
		//setGESAMTPREIS					(bIsLadeseite ? : ) ;
		//setGPREIS_ABZ_MGE				(bIsLadeseite ? : ) ;
		//setGPREIS_ABZ_AUF_NETTOMGE		(bIsLadeseite ? : ) ;
		//setGPREIS_ABZ_VORAUSZAHLUNG		(bIsLadeseite ? : ) ;
		//setBEMERKUNG_EXTERN				(bIsLadeseite ? fuhre.getbeme: ) ;
		//setKOSTEN_PRODUKT				(bIsLadeseite ? : ) ;
		
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
	
	
	public jt_bg_ladung(BG_FUHREN_Transformation trans, jt_bg_atom atom, Rec20_VPOS_TPA_FUHRE_ORT_ext ort, Rec20_VPOS_TPA_FUHRE_ext fuhre) throws myException{
		this();
		
		boolean bIstLadeseite = ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"-").equals("EK");
		
		
//		setID_BG_LADUNG(BG_LADUNG._tab().seq_nextval());
		setID_BG_STATION(BG_STATION._tab().seq_currval());
		setID_BG_ATOM(BG_ATOM._tab().seq_currval());
		
		setID_MANDANT(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_mandant) );
		setLETZTE_AENDERUNG(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.letzte_aenderung) )  ;
		setGEAENDERT_VON(ort.get_ufs_dbVal( VPOS_TPA_FUHRE_ORT.geaendert_von));
		setERZEUGT_AM(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.erzeugt_am));
		setERZEUGT_VON(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.erzeugt_von));
	
			
		setMENGENVORZEICHEN		(bIstLadeseite ? -1L : +1L) ;
		
		setLEISTUNGSDATUM		(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.datum_lade_ablade) ) ;
		setANR1					(ort.getUfs(VPOS_TPA_FUHRE_ORT.anr1) );
		setANR2					(ort.getUfs(VPOS_TPA_FUHRE_ORT.anr2));
		setARTBEZ1				(ort.getUfs(VPOS_TPA_FUHRE_ORT.artbez1)) ;
		setARTBEZ2				(ort.getUfs(VPOS_TPA_FUHRE_ORT.artbez2)) ;

		
		setID_ARTIKEL_BEZ		(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_artikel_bez) ) ;
//		setID_ARTIKEL			(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_artikel) ) ;
		setID_ARTIKEL			(trans.getArtikelIDFromArtikelBezID(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.id_artikel_bez)));
		
		
		BigDecimal bdMenge = null;
		if (bIstLadeseite){
			if (ort.getUfs(VPOS_TPA_FUHRE_ORT.ablademenge_rechnung,"N").equalsIgnoreCase("Y")){
				bdMenge = ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge);
			} else {
				bdMenge = ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_lademenge);
			}
		} else {
			if (ort.getUfs(VPOS_TPA_FUHRE_ORT.lademenge_gutschrift,"N").equalsIgnoreCase("Y")){
				bdMenge = ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_lademenge);
			} else {
				bdMenge = ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge);
			}
		}
		setMENGE				( bdMenge)  ;
		

		
		
		setMENGE				( bIstLadeseite ? ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_lademenge)  : ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge) )  ;
		setABZUG_MENGE			( ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.abzug_menge) ) ;
		
		if (MENGE.bdValue != null) {
				setMENGE_NETTO	(MENGE.bdValue.subtract(ABZUG_MENGE.bdValue != null ? ABZUG_MENGE.bdValue : BigDecimal.ZERO) );
		}
		
		setE_PREIS_RESULT_NETTO_MGE	( ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.epreis_result_netto_mge)) ;
		
		
		setPOSTENNUMMER					(ort.getUfs(VPOS_TPA_FUHRE_ORT.postennummer)) ;
		setTIMESTAMP_IN_MASK			(ort.getUfs(VPOS_TPA_FUHRE_ORT.zeitstempel) );
		
		setID_VPOS_KON					(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_kon) ) ;
		setID_VPOS_STD					(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_std) ) ;
		setID_TAX						(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_tax) ) ;
		
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y") ){
			jt_bg_storno_info i = new jt_bg_storno_info(fuhre);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (ort.getUfs(VPOS_TPA_FUHRE_ORT.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info(ort);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		}

		setBEMERKUNG_INTERN				( ort.getUfs(VPOS_TPA_FUHRE_ORT.bemerkung) );
		
		
		//setE_PREIS_RESULT_BRUTTO_MGE(pE_PREIS_RESULT_BRUTTO_MGE)
		//setGESAMTPREIS					(bIsLadeseite ? : ) ;
//		setGPREIS_ABZ_MGE				(bIsLadeseite ? : ) ;
//		setGPREIS_ABZ_AUF_NETTOMGE		(bIsLadeseite ? : ) ;
//		setGPREIS_ABZ_VORAUSZAHLUNG		(bIsLadeseite ? : ) ;
//		setKONTRAKTZWANG				(bIsLadeseite ? : ) ;
//		setBEMERKUNG_EXTERN				(bIsLadeseite ? fuhre.getbeme: ) ;
//		setKOSTEN_PRODUKT				(bIsLadeseite ? : ) ;
	}
	

	
	
	
	
	

	/**
	 * Erzeugt eine Basis Ladung für eine Lager-Hand-Buchung
	 * @author manfred
	 * @date 13.02.2018
	 *
	 * @param atom
	 * @param lager
	 * @throws myException
	 */
	public jt_bg_ladung( jt_bg_atom atom, Rec20_Lager_Konto lager) throws myException{
		this();
		String sTyp = lager.get_ufs_dbVal(LAGER_KONTO.buchung_hand,"*");
		
		if (!sTyp.equals("U") && !sTyp.equals("K")){
			throw new myException("Buchung ist keine Handbuchung: " + lager.getUfs(LAGER_KONTO.id_lager_konto) );
		}
			
		boolean bIstLadeseite = lager.get_ufs_dbVal(LAGER_KONTO.buchungstyp,"*").equals("WA");
		
//		setID_BG_LADUNG		(BG_LADUNG._tab().seq_nextval());
		setID_BG_STATION	(BG_STATION._tab().seq_currval());
		setID_BG_ATOM		(BG_ATOM._tab().seq_currval());
		
		setID_MANDANT(lager.get_raw_resultValue_Long(LAGER_KONTO.id_mandant) );
		setLETZTE_AENDERUNG(lager.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(lager.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(lager.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(lager.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));

		// Menge wird ohne Vorzeichen abgelegt
		setMENGENVORZEICHEN	(bIstLadeseite ? -1L : +1L) ;
		
		setMENGE			(lager.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge, BigDecimal.ZERO ).abs() );
		setMENGE_NETTO		(lager.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge, BigDecimal.ZERO ).abs() );
		
		setLEISTUNGSDATUM	(lager.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum) ) ;
		
		setID_ARTIKEL		(lager.get_raw_resultValue_Long(LAGER_KONTO.id_artikel_sorte)) ;
		setID_LAGER_KONTO	(lager.get_raw_resultValue_Long(LAGER_KONTO.id_lager_konto) );
		setBEMERKUNG_INTERN (lager.getUfs(LAGER_KONTO.bemerkung) );
		
		setE_PREIS_RESULT_NETTO_MGE  (lager.get_raw_resultValue_bigDecimal(LAGER_KONTO.preis, BigDecimal.ZERO ) );
		setE_PREIS_RESULT_BRUTTO_MGE (lager.get_raw_resultValue_bigDecimal(LAGER_KONTO.preis, BigDecimal.ZERO ) );
	}

	
	
	
	
	
	

	/**
	 * Clone-Konstruktor
	 * @author manfred
	 * @date 07.02.2018
	 *
	 */
	public jt_bg_ladung(jt_bg_ladung ori){
		this();
		
		ID_BG_LADUNG.CopyFrom(ori.ID_BG_LADUNG);
		ID_BG_ATOM.CopyFrom(ori.ID_BG_ATOM);
		ID_MANDANT.CopyFrom(ori.ID_MANDANT);
		LETZTE_AENDERUNG.CopyFrom(ori.LETZTE_AENDERUNG); 
		GEAENDERT_VON.CopyFrom(ori.GEAENDERT_VON); 
		ERZEUGT_VON.CopyFrom(ori.ERZEUGT_VON); 
		ERZEUGT_AM.CopyFrom(ori.ERZEUGT_AM); 
		LEISTUNGSDATUM.CopyFrom(ori.LEISTUNGSDATUM); 
		ANR1.CopyFrom(ori.ANR1); 
		ANR2.CopyFrom(ori.ANR2); 
		ARTBEZ1.CopyFrom(ori.ARTBEZ1); 
		ARTBEZ2.CopyFrom(ori.ARTBEZ2); 
		MENGE.CopyFrom(ori.MENGE); 
		MENGE_NETTO.CopyFrom(ori.MENGE_NETTO); 
		ABZUG_MENGE.CopyFrom(ori.ABZUG_MENGE); 
		E_PREIS_RESULT_NETTO_MGE.CopyFrom(ori.E_PREIS_RESULT_NETTO_MGE); 
		E_PREIS_RESULT_BRUTTO_MGE.CopyFrom(ori.E_PREIS_RESULT_BRUTTO_MGE); 
		GESAMTPREIS.CopyFrom(ori.GESAMTPREIS); 
		GPREIS_ABZ_MGE.CopyFrom(ori.GPREIS_ABZ_MGE); 
		GPREIS_ABZ_AUF_NETTOMGE.CopyFrom(ori.GPREIS_ABZ_AUF_NETTOMGE);
		GPREIS_ABZ_VORAUSZAHLUNG.CopyFrom(ori.GPREIS_ABZ_VORAUSZAHLUNG); 
		KONTRAKTZWANG.CopyFrom(ori.KONTRAKTZWANG); 
		POSTENNUMMER.CopyFrom(ori.POSTENNUMMER); 
		EU_STEUER_VERMERK.CopyFrom(ori.EU_STEUER_VERMERK); 
		BEMERKUNG_EXTERN.CopyFrom(ori.BEMERKUNG_EXTERN);
		BEMERKUNG_INTERN.CopyFrom(ori.BEMERKUNG_INTERN); 
		MENGENVORZEICHEN.CopyFrom(ori.MENGENVORZEICHEN); 
		TIMESTAMP_IN_MASK.CopyFrom(ori.TIMESTAMP_IN_MASK); 
		KOSTEN_PRODUKT.CopyFrom(ori.KOSTEN_PRODUKT);
		ID_BG_STATION.CopyFrom(ori.ID_BG_STATION); 
		ID_ARTIKEL_BEZ.CopyFrom(ori.ID_ARTIKEL_BEZ); 
		ID_ARTIKEL.CopyFrom(ori.ID_ARTIKEL); 
		ID_ADRESSE_BESITZER.CopyFrom(ori.ID_ADRESSE_BESITZER); 
		ID_BG_PRUEFPROT_MENGE.CopyFrom(ori.ID_BG_PRUEFPROT_MENGE); 
		ID_BG_PRUEFPROT_PREIS.CopyFrom(ori.ID_BG_PRUEFPROT_PREIS); 
		ID_BG_PRUEFPROT_ABSCHLUSS.CopyFrom(ori.ID_BG_PRUEFPROT_ABSCHLUSS); 
		ID_BG_STORNO_INFO.CopyFrom(ori.ID_BG_STORNO_INFO); 
		ID_BG_DEL_INFO.CopyFrom(ori.ID_BG_DEL_INFO); 
		ID_VPOS_KON.CopyFrom(ori.ID_VPOS_KON); 
		ID_VPOS_STD.CopyFrom(ori.ID_VPOS_STD); 
		ID_TAX.CopyFrom(ori.ID_TAX); 
		STEUERSATZ.CopyFrom(ori.STEUERSATZ);
		ID_BG_VEKTOR.CopyFrom(ori.ID_BG_VEKTOR);
		ID_LAGER_KONTO.CopyFrom(ori.ID_LAGER_KONTO);
		o_jt_bg_station = ori.o_jt_bg_station;
		o_jt_bg_atom = ori.o_jt_bg_atom;
	}

	
	public DOString getID_BG_VEKTOR() {
	    return ID_BG_VEKTOR;
	}

	public jt_bg_ladung setID_BG_VEKTOR(String pID_BG_VEKTOR) {
	    ID_BG_VEKTOR.setValue(pID_BG_VEKTOR);
	    return this;
	}


	//date
	public DODate getLETZTE_AENDERUNG() {
	    return LETZTE_AENDERUNG;
	}

	public jt_bg_ladung setLETZTE_AENDERUNG(Date pLETZTE_AENDERUNG) {
	    LETZTE_AENDERUNG.setValue(pLETZTE_AENDERUNG);
	    return this;
	}


	public DODate getERZEUGT_AM() {
	    return ERZEUGT_AM;
	}

	public jt_bg_ladung setERZEUGT_AM(Date pERZEUGT_AM) {
	    ERZEUGT_AM.setValue(pERZEUGT_AM);
	    return this;
	}


	public DODate getLEISTUNGSDATUM() {
	    return LEISTUNGSDATUM;
	}

	public jt_bg_ladung setLEISTUNGSDATUM(Date pLEISTUNGSDATUM) {
	    LEISTUNGSDATUM.setValue(pLEISTUNGSDATUM);
	    return this;
	}




	public DOLong getMENGENVORZEICHEN() {
	    return MENGENVORZEICHEN;
	}

	public jt_bg_ladung setMENGENVORZEICHEN(Long pMENGENVORZEICHEN) {
	    MENGENVORZEICHEN.setValue(pMENGENVORZEICHEN);
	    return this;
	}


	public DOString getID_BG_LADUNG() {
	    return ID_BG_LADUNG;
	}

	public jt_bg_ladung setID_BG_LADUNG(String string) {
	    ID_BG_LADUNG.setValue(string);
	    return this;
	}


	public DOLong getID_MANDANT() {
	    return ID_MANDANT;
	}

	public jt_bg_ladung setID_MANDANT(Long pID_MANDANT) {
	    ID_MANDANT.setValue(pID_MANDANT);
	    return this;
	}


	public DOString getID_BG_STATION() {
	    return ID_BG_STATION;
	}

	public jt_bg_ladung setID_BG_STATION(String pID_BG_STATION) {
	    ID_BG_STATION.setValue(pID_BG_STATION);
	    return this;
	}


	public DOString getID_BG_ATOM() {
	    return ID_BG_ATOM;
	}

	public jt_bg_ladung setID_BG_ATOM(String pID_BG_ATOM) {
	    ID_BG_ATOM.setValue(pID_BG_ATOM);
	    return this;
	}
	
	
	public DOLong getID_ARTIKEL_BEZ() {
	    return ID_ARTIKEL_BEZ;
	}

	public jt_bg_ladung setID_ARTIKEL_BEZ(Long pID_ARTIKEL_BEZ) {
	    ID_ARTIKEL_BEZ.setValue(pID_ARTIKEL_BEZ);
	    return this;
	}


	public DOLong getID_ARTIKEL() {
	    return ID_ARTIKEL;
	}

	public jt_bg_ladung setID_ARTIKEL(Long pID_ARTIKEL) {
	    ID_ARTIKEL.setValue(pID_ARTIKEL);
	    return this;
	}


	public DOLong getID_ADRESSE_BESITZER() {
	    return ID_ADRESSE_BESITZER;
	}

	public jt_bg_ladung setID_ADRESSE_BESITZER(Long pID_ADRESSE_BESITZER) {
	    ID_ADRESSE_BESITZER.setValue(pID_ADRESSE_BESITZER);
	    return this;
	}


	public DOLong getID_BG_PRUEFPROT_MENGE() {
	    return ID_BG_PRUEFPROT_MENGE;
	}

	public jt_bg_ladung setID_BG_PRUEFPROT_MENGE(Long pID_BG_PRUEFPROT_MENGE) {
	    ID_BG_PRUEFPROT_MENGE.setValue(pID_BG_PRUEFPROT_MENGE);
	    return this;
	}


	public DOLong getID_BG_PRUEFPROT_PREIS() {
	    return ID_BG_PRUEFPROT_PREIS;
	}

	public jt_bg_ladung setID_BG_PRUEFPROT_PREIS(Long pID_BG_PRUEFPROT_PREIS) {
	    ID_BG_PRUEFPROT_PREIS.setValue(pID_BG_PRUEFPROT_PREIS);
	    return this;
	}


	public DOLong getID_BG_PRUEFPROT_ABSCHLUSS() {
	    return ID_BG_PRUEFPROT_ABSCHLUSS;
	}

	public jt_bg_ladung setID_BG_PRUEFPROT_ABSCHLUSS(Long pID_BG_PRUEFPROT_ABSCHLUSS) {
	    ID_BG_PRUEFPROT_ABSCHLUSS.setValue(pID_BG_PRUEFPROT_ABSCHLUSS);
	    return this;
	}


	public DOString getID_BG_STORNO_INFO() {
	    return ID_BG_STORNO_INFO;
	}

	public jt_bg_ladung setID_BG_STORNO_INFO(String pID_BG_STORNO_INFO) {
	    ID_BG_STORNO_INFO.setValue(pID_BG_STORNO_INFO);
	    return this;
	}


	public DOString getID_BG_DEL_INFO() {
	    return ID_BG_DEL_INFO;
	}

	public jt_bg_ladung setID_BG_DEL_INFO(String pID_BG_DEL_INFO) {
	    ID_BG_DEL_INFO.setValue(pID_BG_DEL_INFO);
	    return this;
	}


	public DOLong getID_VPOS_KON() {
	    return ID_VPOS_KON;
	}

	public jt_bg_ladung setID_VPOS_KON(Long pID_VPOS_KON) {
	    ID_VPOS_KON.setValue(pID_VPOS_KON);
	    return this;
	}


	public DOLong getID_VPOS_STD() {
	    return ID_VPOS_STD;
	}

	public jt_bg_ladung setID_VPOS_STD(Long pID_VPOS_STD) {
	    ID_VPOS_STD.setValue(pID_VPOS_STD);
	    return this;
	}


	public DOLong getID_TAX() {
	    return ID_TAX;
	}

	public jt_bg_ladung setID_TAX(Long pID_TAX) {
	    ID_TAX.setValue(pID_TAX);
	    return this;
	}



	//bigdecimal
	public DOBigDecimal getE_PREIS_RESULT_NETTO_MGE() {
	    return E_PREIS_RESULT_NETTO_MGE;
	}

	public jt_bg_ladung setE_PREIS_RESULT_NETTO_MGE(BigDecimal pE_PREIS_RESULT_NETTO_MGE) {
	    E_PREIS_RESULT_NETTO_MGE.setValue(pE_PREIS_RESULT_NETTO_MGE);
	    return this;
	}


	public DOBigDecimal getE_PREIS_RESULT_BRUTTO_MGE() {
	    return E_PREIS_RESULT_BRUTTO_MGE;
	}

	public jt_bg_ladung setE_PREIS_RESULT_BRUTTO_MGE(BigDecimal pE_PREIS_RESULT_BRUTTO_MGE) {
	    E_PREIS_RESULT_BRUTTO_MGE.setValue(pE_PREIS_RESULT_BRUTTO_MGE);
	    return this;
	}


	public DOBigDecimal getGESAMTPREIS() {
	    return GESAMTPREIS;
	}

	public jt_bg_ladung setGESAMTPREIS(BigDecimal pGESAMTPREIS) {
	    GESAMTPREIS.setValue(pGESAMTPREIS);
	    return this;
	}


	public DOBigDecimal getGPREIS_ABZ_MGE() {
	    return GPREIS_ABZ_MGE;
	}

	public jt_bg_ladung setGPREIS_ABZ_MGE(BigDecimal pGPREIS_ABZ_MGE) {
	    GPREIS_ABZ_MGE.setValue(pGPREIS_ABZ_MGE);
	    return this;
	}


	public DOBigDecimal getGPREIS_ABZ_AUF_NETTOMGE() {
	    return GPREIS_ABZ_AUF_NETTOMGE;
	}

	public jt_bg_ladung setGPREIS_ABZ_AUF_NETTOMGE(BigDecimal pGPREIS_ABZ_AUF_NETTOMGE) {
	    GPREIS_ABZ_AUF_NETTOMGE.setValue(pGPREIS_ABZ_AUF_NETTOMGE);
	    return this;
	}


	public DOBigDecimal getGPREIS_ABZ_VORAUSZAHLUNG() {
	    return GPREIS_ABZ_VORAUSZAHLUNG;
	}

	public jt_bg_ladung setGPREIS_ABZ_VORAUSZAHLUNG(BigDecimal pGPREIS_ABZ_VORAUSZAHLUNG) {
	    GPREIS_ABZ_VORAUSZAHLUNG.setValue(pGPREIS_ABZ_VORAUSZAHLUNG);
	    return this;
	}


	public DOBigDecimal getKOSTEN_PRODUKT() {
	    return KOSTEN_PRODUKT;
	}

	public jt_bg_ladung setKOSTEN_PRODUKT(BigDecimal pKOSTEN_PRODUKT) {
	    KOSTEN_PRODUKT.setValue(pKOSTEN_PRODUKT);
	    return this;
	}


	public DOBigDecimal getMENGE() {
	    return MENGE;
	}

	public jt_bg_ladung setMENGE(BigDecimal pMENGE) {
	    MENGE.setValue(pMENGE);
	    return this;
	}


	public DOBigDecimal getMENGE_NETTO() {
	    return MENGE_NETTO;
	}

	public jt_bg_ladung setMENGE_NETTO(BigDecimal pMENGE_NETTO) {
	    MENGE_NETTO.setValue(pMENGE_NETTO);
	    return this;
	}


	public DOBigDecimal getABZUG_MENGE() {
	    return ABZUG_MENGE;
	}

	public jt_bg_ladung setABZUG_MENGE(BigDecimal pABZUG_MENGE) {
	    ABZUG_MENGE.setValue(pABZUG_MENGE);
	    return this;
	}



	//string
	public DOString getGEAENDERT_VON() {
	    return GEAENDERT_VON;
	}

	public jt_bg_ladung setGEAENDERT_VON(String pGEAENDERT_VON) {
	    GEAENDERT_VON.setValue(pGEAENDERT_VON);
	    return this;
	}


	public DOString getERZEUGT_VON() {
	    return ERZEUGT_VON;
	}

	public jt_bg_ladung setERZEUGT_VON(String pERZEUGT_VON) {
	    ERZEUGT_VON.setValue(pERZEUGT_VON);
	    return this;
	}


	public DOString getANR1() {
	    return ANR1;
	}

	public jt_bg_ladung setANR1(String pANR1) {
	    ANR1.setValue(pANR1);
	    return this;
	}


	public DOString getANR2() {
	    return ANR2;
	}

	public jt_bg_ladung setANR2(String pANR2) {
	    ANR2.setValue(pANR2);
	    return this;
	}


	public DOString getARTBEZ1() {
	    return ARTBEZ1;
	}

	public jt_bg_ladung setARTBEZ1(String pARTBEZ1) {
	    ARTBEZ1.setValue(pARTBEZ1);
	    return this;
	}


	public DOString getARTBEZ2() {
	    return ARTBEZ2;
	}

	public jt_bg_ladung setARTBEZ2(String pARTBEZ2) {
	    ARTBEZ2.setValue(pARTBEZ2);
	    return this;
	}



	public DOString getKONTRAKTZWANG() {
	    return KONTRAKTZWANG;
	}

	public jt_bg_ladung setKONTRAKTZWANG(String pKONTRAKTZWANG) {
	    KONTRAKTZWANG.setValue(pKONTRAKTZWANG);
	    return this;
	}


	public DOString getPOSTENNUMMER() {
	    return POSTENNUMMER;
	}

	public jt_bg_ladung setPOSTENNUMMER(String pPOSTENNUMMER) {
	    POSTENNUMMER.setValue(pPOSTENNUMMER);
	    return this;
	}


	public DOString getEU_STEUER_VERMERK() {
	    return EU_STEUER_VERMERK;
	}

	public jt_bg_ladung setEU_STEUER_VERMERK(String pEU_STEUER_VERMERK) {
	    EU_STEUER_VERMERK.setValue(pEU_STEUER_VERMERK);
	    return this;
	}


	public DOString getBEMERKUNG_EXTERN() {
	    return BEMERKUNG_EXTERN;
	}

	public jt_bg_ladung setBEMERKUNG_EXTERN(String pBEMERKUNG_EXTERN) {
	    BEMERKUNG_EXTERN.setValue(pBEMERKUNG_EXTERN);
	    return this;
	}


	public DOString getBEMERKUNG_INTERN() {
	    return BEMERKUNG_INTERN;
	}

	public jt_bg_ladung setBEMERKUNG_INTERN(String pBEMERKUNG_INTERN) {
	    BEMERKUNG_INTERN.setValue(pBEMERKUNG_INTERN);
	    return this;
	}


	public DOString getTIMESTAMP_IN_MASK() {
	    return TIMESTAMP_IN_MASK;
	}

	public jt_bg_ladung setTIMESTAMP_IN_MASK(String pTIMESTAMP_IN_MASK) {
	    TIMESTAMP_IN_MASK.setValue(pTIMESTAMP_IN_MASK);
	    return this;
	}


	public DOString getSTEUERSATZ() {
		return STEUERSATZ;
	}


	public jt_bg_ladung setSTEUERSATZ(String sTEUERSATZ) {
		STEUERSATZ.setValue(sTEUERSATZ);
		return this;
	}
	
	public DOLong getID_LAGER_KONTO() {
	    return ID_LAGER_KONTO;
	}

	public jt_bg_ladung setID_LAGER_KONTO(Long pID_LAGER_KONTO) {
	    ID_LAGER_KONTO.setValue(pID_LAGER_KONTO);
	    return this;
	}	

	/**
	 * die Station der Ladung
	 * @author manfred
	 * @date 17.01.2018
	 *
	 * @return
	 */
	
	public jt_bg_station get_jt_bg_station() {
		return o_jt_bg_station;
	}
	
	
	public jt_bg_ladung set_jt_bg_station(jt_bg_station p_jt_bg_stations) {
		o_jt_bg_station = p_jt_bg_stations;
		return this;
	}
	
	

	public jt_bg_atom get_jt_bg_atom() {
		return o_jt_bg_atom;
	}




	public jt_bg_ladung set_jt_bg_atom(jt_bg_atom o_jt_bg_atom) {
		this.o_jt_bg_atom = o_jt_bg_atom;
		return this;
	}


	
	


	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {
		m_vDataObjects.addElement  ( ID_BG_LADUNG );
		m_vDataObjects.addElement  ( ID_BG_STATION );
		m_vDataObjects.addElement  ( ID_BG_ATOM );
		
		m_vDataObjects.addElement  ( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement  ( ID_BG_DEL_INFO );

		m_vDataObjects.addElement  ( ID_MANDANT );
		m_vDataObjects.addElement  ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement  ( GEAENDERT_VON );
		m_vDataObjects.addElement  ( ERZEUGT_VON );
		m_vDataObjects.addElement  ( ERZEUGT_AM );

		m_vDataObjects.addElement  ( LEISTUNGSDATUM );
		m_vDataObjects.addElement  ( ANR1 );
		m_vDataObjects.addElement  ( ANR2 );
		m_vDataObjects.addElement  ( ARTBEZ1 );
		m_vDataObjects.addElement  ( ARTBEZ2 );

		m_vDataObjects.addElement  ( MENGE );
		m_vDataObjects.addElement  ( MENGE_NETTO );
		m_vDataObjects.addElement  ( ABZUG_MENGE );
		m_vDataObjects.addElement  ( E_PREIS_RESULT_NETTO_MGE );
		m_vDataObjects.addElement  ( E_PREIS_RESULT_BRUTTO_MGE );
		m_vDataObjects.addElement  ( GESAMTPREIS );
		m_vDataObjects.addElement  ( GPREIS_ABZ_MGE );
		m_vDataObjects.addElement  ( GPREIS_ABZ_AUF_NETTOMGE );
		m_vDataObjects.addElement  ( GPREIS_ABZ_VORAUSZAHLUNG );
		m_vDataObjects.addElement  ( KONTRAKTZWANG );
		m_vDataObjects.addElement  ( POSTENNUMMER );
		m_vDataObjects.addElement  ( EU_STEUER_VERMERK );
		m_vDataObjects.addElement  ( BEMERKUNG_EXTERN );
		m_vDataObjects.addElement  ( BEMERKUNG_INTERN );
		m_vDataObjects.addElement  ( MENGENVORZEICHEN );
		m_vDataObjects.addElement  ( TIMESTAMP_IN_MASK );
		m_vDataObjects.addElement  ( KOSTEN_PRODUKT );
		m_vDataObjects.addElement  ( ID_ARTIKEL_BEZ );
		m_vDataObjects.addElement  ( ID_ARTIKEL );
		m_vDataObjects.addElement  ( ID_ADRESSE_BESITZER );
		m_vDataObjects.addElement  ( ID_BG_PRUEFPROT_MENGE );
		m_vDataObjects.addElement  ( ID_BG_PRUEFPROT_PREIS );
		m_vDataObjects.addElement  ( ID_BG_PRUEFPROT_ABSCHLUSS );
		m_vDataObjects.addElement  ( ID_VPOS_KON );
		m_vDataObjects.addElement  ( ID_VPOS_STD );
		m_vDataObjects.addElement  ( ID_TAX );
		m_vDataObjects.addElement  ( STEUERSATZ);
		m_vDataObjects.addElement  ( ID_BG_VEKTOR );
		m_vDataObjects.addElement  ( ID_LAGER_KONTO );
	}











}
