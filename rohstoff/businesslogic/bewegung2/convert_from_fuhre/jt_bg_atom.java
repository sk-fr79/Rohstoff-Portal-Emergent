/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;
import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.ENUM_VEKTOR_POS_TYP;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.__WF_HEAD_LIST_Selector__unused.enumDisplayOptions;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_Lager_Konto;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_Lager_Konto;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;
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
 * @date 16.01.2018
 *
 */
public class jt_bg_atom extends REC_Base{

	private DOString ID_BG_ATOM = new DOString(BG_ATOM.id_bg_atom,null,"").setRaw(true);
	private DOString ID_BG_VEKTOR = new DOString(BG_ATOM.id_bg_vektor,null,"").setRaw(true);
	
	private DOLong ID_MANDANT = new DOLong(BG_ATOM.id_mandant);
	private DOString ERZEUGT_VON = new DOString(BG_ATOM.erzeugt_von);
	private DOString GEAENDERT_VON = new DOString(BG_ATOM.geaendert_von);

	private DOString POS_IN_MASK = new DOString(BG_ATOM.pos_in_mask);

	private DOLong ID_BG_STATION_QUELLE = new DOLong(BG_ATOM.id_bg_station_quelle);
	private DOLong ID_BG_STATION_ZIEL = new DOLong(BG_ATOM.id_bg_station_ziel);
	
	private DOString ID_BG_DEL_INFO = new DOString(BG_ATOM.id_bg_del_info,null,"").setRaw(true);
	private DOString ID_BG_STORNO_INFO = new DOString(BG_ATOM.id_bg_storno_info,null,"").setRaw(true);

	private DOLong ID_ARTIKEL = new DOLong(BG_ATOM.id_artikel);
	private DOLong ID_ARTIKEL_BEZ = new DOLong(BG_ATOM.id_artikel_bez);
	private DOLong ID_BG_PRUEFPROT_ABSCHLUSS = new DOLong(BG_ATOM.id_bg_pruefprot_abschluss);
	private DOLong ID_BG_PRUEFPROT_MENGE = new DOLong(BG_ATOM.id_bg_pruefprot_menge);
	private DOLong ID_BG_PRUEFPROT_PREIS = new DOLong(BG_ATOM.id_bg_pruefprot_preis);
	private DOLong ID_BG_RECH_BLOCK = new DOLong(BG_ATOM.id_bg_rech_block);
	private DOLong ID_EAK_CODE = new DOLong(BG_ATOM.id_eak_code);
	private DOLong ID_LAGER_KONTO = new DOLong(BG_ATOM.id_lager_konto);
	private DOLong ID_LIEFERBEDINGUNGEN = new DOLong(BG_ATOM.id_lieferbedingungen);
	private DOLong ID_TAX = new DOLong(BG_ATOM.id_tax);
	private DOLong ID_VPOS_KON = new DOLong(BG_ATOM.id_vpos_kon);
	private DOLong ID_VPOS_STD = new DOLong(BG_ATOM.id_vpos_std);
	private DOLong ID_WAEHRUNG = new DOLong(BG_ATOM.id_waehrung);
	private DOLong ID_ZAHLUNGSBEDINGUNGEN = new DOLong(BG_ATOM.id_zahlungsbedingungen);
	private DOLong ID_ZOLLTARIFNUMMER = new DOLong(BG_ATOM.id_zolltarifnummer);


	private DOBigDecimal E_PREIS_BASISWAEHRUNG = new DOBigDecimal(BG_ATOM.e_preis_basiswaehrung);
	private DOBigDecimal E_PREIS_FREMDWAEHRUNG = new DOBigDecimal(BG_ATOM.e_preis_fremdwaehrung);
	private DOBigDecimal E_PREIS_RES_NETTO_MGE_BASIS = new DOBigDecimal(BG_ATOM.e_preis_res_netto_mge_basis);
	private DOBigDecimal E_PREIS_RES_NETTO_MGE_FREMD = new DOBigDecimal(BG_ATOM.e_preis_res_netto_mge_fremd);
	private DOBigDecimal G_PREIS_ABZUG_BASIS = new DOBigDecimal(BG_ATOM.g_preis_abzug_basis);
	private DOBigDecimal G_PREIS_ABZUG_FREMD = new DOBigDecimal(BG_ATOM.g_preis_abzug_fremd);
	private DOBigDecimal G_PREIS_BASISWAEHRUNG = new DOBigDecimal(BG_ATOM.g_preis_basiswaehrung);
	private DOBigDecimal G_PREIS_FREMDWAEHRUNG = new DOBigDecimal(BG_ATOM.g_preis_fremdwaehrung);
	private DOBigDecimal KOSTEN_PRODUKT = new DOBigDecimal(BG_ATOM.kosten_produkt);
	private DOBigDecimal MENGE = new DOBigDecimal(BG_ATOM.menge);
	private DOBigDecimal MENGE_ABRECH = new DOBigDecimal(BG_ATOM.menge_abrech);
	private DOBigDecimal MENGE_ABZUG = new DOBigDecimal(BG_ATOM.menge_abzug);
	private DOBigDecimal MENGE_NETTO = new DOBigDecimal(BG_ATOM.menge_netto);
	private DOBigDecimal MENGE_VERTEILUNG = new DOBigDecimal(BG_ATOM.menge_verteilung);
	private DOBigDecimal STEUERSATZ = new DOBigDecimal(BG_ATOM.steuersatz);
	private DOBigDecimal WAEHRUNGSKURS = new DOBigDecimal(BG_ATOM.waehrungskurs);


	private DOString ANR1 = new DOString(BG_ATOM.anr1);
	private DOString ANR2 = new DOString(BG_ATOM.anr2);
	private DOString ARTBEZ1 = new DOString(BG_ATOM.artbez1);
	private DOString ARTBEZ2 = new DOString(BG_ATOM.artbez2);
	private DOString BEMERKUNG_EXTERN = new DOString(BG_ATOM.bemerkung_extern);
	private DOString BEMERKUNG_INTERN = new DOString(BG_ATOM.bemerkung_intern);
	private DOString BESTELLNUMMER = new DOString(BG_ATOM.bestellnummer);
	private DOString EU_STEUER_VERMERK = new DOString(BG_ATOM.eu_steuer_vermerk);
	private DOString INTRASTAT_MELD = new DOString(BG_ATOM.intrastat_meld);
	private DOString LIEFERBEDINGUNGEN = new DOString(BG_ATOM.lieferbedingungen);
	private DOString NATIONALER_ABFALL_CODE = new DOString(BG_ATOM.nationaler_abfall_code);
	private DOString NOTIFIKATION_NR = new DOString(BG_ATOM.notifikation_nr);
	private DOString POSTENNUMMER = new DOString(BG_ATOM.postennummer);
	private DOString TIMESTAMP_IN_MASK = new DOString(BG_ATOM.timestamp_in_mask);
	private DOString ZAHLUNGSBEDINGUNGEN = new DOString(BG_ATOM.zahlungsbedingungen);

	private DODate 	 LETZTE_AENDERUNG = new DODate(BG_ATOM.letzte_aenderung);
	private DODate 	 ERZEUGT_AM = new DODate(BG_ATOM.erzeugt_am);
	private DODate 	 DATUM_AUSFUEHRUNG = new DODate(BG_ATOM.datum_ausfuehrung);
	
	private DOString KONTRAKTZWANG = new DOString(BG_ATOM.kontraktzwang);
	private DOString MANUELL_PREIS = new DOString(BG_ATOM.manuell_preis);
	private DOString TRANSIT_MELD = new DOString(BG_ATOM.transit_meld);


	
	

	// Vektor
//	private jt_bg_vektor jt_bg_vektor = null;

	// del und storno-INFOS
	private REC_Base     _jt_bg_del_info = null;
	private REC_Base 	 _jt_bg_storno_info = null;

	private jt_bg_station _jt_bg_station_quelle = null;
	private jt_bg_station _jt_bg_station_ziel = null;
	
	
	
	
	public jt_bg_atom(conv_helper helper) {
		super(_TAB.bg_atom);
		_helper = helper;
		this.initFieldList();
	}
	
	
	
	
	
	/**
	 * @author manfred
	 * @date 19.01.2018
	 *
	 * @param iD_BG_ATOM
	 * @param iD_MANDANT
	 */
	public jt_bg_atom(String iD_BG_ATOM, String iD_MANDANT, conv_helper helper) {
		this(helper);
		ID_BG_ATOM.setValue(iD_BG_ATOM);
		ID_MANDANT.setValue(iD_MANDANT);
	}


	
	
	
	
	/**
	
	 * 
	 * @author manfred
	 * @date 19.01.2018
	 * @param fuhre
	 *
	 * @throws myException
	 */
	public jt_bg_atom(Bewegung_Fuhren_Transformation trans,jt_bg_vektor vektor,Rec21_VPOS_TPA_FUHRE_ext fuhre, EnTabKeyInMask pos, conv_helper helper) throws myException{
		this(helper);

		// ladeseite == A1 / abladeseite == A2
		boolean ladeseite = (pos.equals(EnTabKeyInMask.A1) ); 
		
		// ID Atom 
		setID_BG_ATOM(BG_ATOM._tab().seq_nextval());
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
//		vektor.setATOM(this, index);
//		set_jt_bg_vektor(vektor);

		
		// EGAL ob Lade- oder Abladeseite 
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant));
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));

		setPOS_IN_MASK(pos.dbVal());
		
		setNOTIFIKATION_NR(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.notifikation_nr));
		setNATIONALER_ABFALL_CODE(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.nationaler_abfall_code));
		setTIMESTAMP_IN_MASK(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.zeitstempel));
		
		setID_ZOLLTARIFNUMMER( trans.getIDZolltarifnummer(fuhre.getUfs(VPOS_TPA_FUHRE.zolltarifnr) ));
		setTIMESTAMP_IN_MASK	(fuhre.getUfs(VPOS_TPA_FUHRE.zeitstempel) );

		setBEMERKUNG_EXTERN		(fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung_fuer_kunde));
		setBEMERKUNG_INTERN		(fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung_sachbearbeiter));
		
		
		// abhänging ob linke seite 1 oder rechte Seite 2 gelesen wird 
		if (ladeseite ){
			vektor.setATOM_1(this);
		} else { 
			vektor.setATOM_2(this);
		}
		
				
		// beide Seiten gleich
		
//		setID_ARTIKEL			(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel));
		setID_EAK_CODE			(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_eak_code));
		
		// Seiten unterschiedlich
		setDATUM_AUSFUEHRUNG    		( ladeseite ?   fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_aufladen)  : fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abladen) );
		
		setE_PREIS_BASISWAEHRUNG		( ladeseite ?   fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.einzelpreis_ek) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.einzelpreis_vk) );
		setE_PREIS_RES_NETTO_MGE_BASIS  ( ladeseite ?   fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.epreis_result_netto_mge_ek) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.epreis_result_netto_mge_vk));
		setID_ARTIKEL_BEZ				( ladeseite ?   fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel_bez_ek) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel_bez_vk));
		setID_TAX						( ladeseite ?   fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_tax_ek) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_tax_vk)  );
		setID_VPOS_KON					( ladeseite ?   fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_kon_ek) :fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_kon_vk) );
		setID_VPOS_STD					( ladeseite ?   fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_std_ek) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_std_vk) );

		// Artikel von der Artikelbez ermittelt
		setID_ARTIKEL					( ladeseite ?   _helper.getArtikelIDFromArtikelBezID(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_ek)):_helper.getArtikelIDFromArtikelBezID(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.id_artikel_bez_vk)) );

		setMENGE						( ladeseite ?   fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn) );
//		setMENGE_ABRECH					( ladeseite ?   fuhre.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.).get_bdWert() : fuhre.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.).get_bdWert() );
		setMENGE_ABZUG					( ladeseite ?   fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.abzug_lademenge_lief) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.abzug_ablademenge_abn) );
		
		BigDecimal	 bdMengeNettoLade 	= fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_lademenge_lief,BigDecimal.ZERO ).subtract(fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.abzug_lademenge_lief,BigDecimal.ZERO ));
		BigDecimal	 bdMengeNettoAblade = fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.anteil_ablademenge_abn,BigDecimal.ZERO).subtract(fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.abzug_ablademenge_abn,BigDecimal.ZERO ));
		setMENGE_NETTO					( ladeseite ?   bdMengeNettoLade : bdMengeNettoAblade );
		setSTEUERSATZ					( ladeseite ?   fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.steuersatz_ek) : fuhre.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE.steuersatz_vk) );
		setANR1							( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.anr1_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.anr1_vk));
		setANR2							( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.anr2_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.anr2_vk));

		setARTBEZ1						( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.artbez1_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.artbez1_vk) );
		setARTBEZ2						( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.artbez2_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.artbez2_vk) );
		
		setBESTELLNUMMER				( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.bestellnummer_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.bestellnummer_vk) );
		setEU_STEUER_VERMERK			( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.eu_steuer_vermerk_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.eu_steuer_vermerk_vk) );
		setINTRASTAT_MELD				( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.intrastat_meld_in) : fuhre.getUfs(VPOS_TPA_FUHRE.intrastat_meld_out) );
		
		setMANUELL_PREIS				( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.manuell_preis_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.manuell_preis_vk)  );
		setNATIONALER_ABFALL_CODE		( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.nationaler_abfall_code) : fuhre.getUfs(VPOS_TPA_FUHRE.nationaler_abfall_code)  );
		
		setNOTIFIKATION_NR				( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.notifikation_nr_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.notifikation_nr)  );
		
//		setPOS_IN_MASK					( ladeseite ? : );
		setPOSTENNUMMER					( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.postennummer_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.postennummer_vk)  );
		setTRANSIT_MELD					( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.transit_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.transit_vk)  );
		setLIEFERBEDINGUNGEN 			( ladeseite ?   fuhre.getUfs(VPOS_TPA_FUHRE.lieferbed_alternativ_ek) : fuhre.getUfs(VPOS_TPA_FUHRE.lieferbed_alternativ_vk)  );
		setID_LIEFERBEDINGUNGEN			( helper.getIDLieferbedingungenFrom(getLIEFERBEDINGUNGEN().ValuePlain()  ) );
		
		setKONTRAKTZWANG				( ladeseite ?  "N" : (fuhre.getUfs(VPOS_TPA_FUHRE.kein_kontrakt_noetig, "N").equalsIgnoreCase("N") ? "Y" : "N" ));
		setID_WAEHRUNG					( trans.m_helper.m_idWaehrungStd);
		
//		setID_BG_PRUEFPROT_ABSCHLUSS( );
//		setID_BG_PRUEFPROT_MENGE( );
//		setID_BG_PRUEFPROT_PREIS( );
//		setID_BG_RECH_BLOCK( );
//		setID_BG_STATION_QUELLE( );
//		setID_BG_STATION_ZIEL( );
//		setID_BG_VEKTOR( );
//		setID_LAGER_KONTO( );
		
		
//		setID_LIEFERBEDINGUNGEN( );
//		setID_ZAHLUNGSBEDINGUNGEN( );
//		setZAHLUNGSBEDINGUNGEN( );
//		setID_WAEHRUNG( );

		
//		setKOSTEN_PRODUKT( );
//		setMENGE_VERTEILUNG( );
//		setWAEHRUNGSKURS( );
		
		
		
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")  ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre,_helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (fuhre.getUfs(VPOS_TPA_FUHRE.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( fuhre, _helper);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		}
		
	}


	
	
	
	/**
	 * ORT ist LADESEITE
	 * FUHRE ist HAUPTFUHRE (ABLADESEITE)
	 * 
	 * @author manfred
	 * @date 08.07.2019
	 *
	 * @param vektor
	 * @param ort
	 * @param fuhre
	 * @param index ATOM ist 1: ladeseite 2:abladeseite
	 * @throws myException
	 */
	public jt_bg_atom(Bewegung_Fuhren_Transformation trans, jt_bg_vektor vektor, Rec21_VPOS_TPA_FUHRE_ORT_ext ort, Rec21_VPOS_TPA_FUHRE_ext fuhre,  EnTabKeyInMask index, conv_helper helper) throws myException{
		this(helper);

		// lade  / abladeseite
		boolean OrtIstLadeseite = (ort.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel).equalsIgnoreCase("EK")); 
		
		// ID Atom 
		setID_BG_ATOM(BG_ATOM._tab().seq_nextval());
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());

		// Ortsdaten unabhängig ob EK/VK
		setID_MANDANT(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_mandant));
		setLETZTE_AENDERUNG(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.letzte_aenderung) )  ;
		setGEAENDERT_VON(ort.get_ufs_dbVal( VPOS_TPA_FUHRE_ORT.geaendert_von));
		setERZEUGT_AM(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.erzeugt_am));
		setERZEUGT_VON(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.erzeugt_von));
		
		
		if (index.equals(EnTabKeyInMask.A1) ){
			// A1
			this.setPOS_IN_MASK(EnTabKeyInMask.A1.dbVal());
			vektor.setATOM_1(this); 

			
			// lade ORT
			setTIMESTAMP_IN_MASK			( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.zeitstempel)    				: fuhre.getUfs(VPOS_TPA_FUHRE.zeitstempel));
			setID_ARTIKEL					( OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_artikel) 	: helper.getArtikelIDFromArtikelBezID(fuhre.getUfs(VPOS_TPA_FUHRE.id_artikel_bez_ek)) );
				
			setBEMERKUNG_EXTERN				( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.bemerkung) 						: fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung_fuer_kunde));
			setBEMERKUNG_INTERN				( OrtIstLadeseite ?   ""															: fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung));
				
			setID_EAK_CODE					( OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_eak_code) 	: null);
			setDATUM_AUSFUEHRUNG    		( OrtIstLadeseite ?   ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.datum_lade_ablade)  : fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_aufladen) );
				
			setE_PREIS_BASISWAEHRUNG		( OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.einzelpreis) : null );
			setE_PREIS_RES_NETTO_MGE_BASIS  ( OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.epreis_result_netto_mge) : null);
			setID_ARTIKEL_BEZ				( OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_artikel_bez) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel_bez_ek));
			setID_TAX						( OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_tax) 		: null );
			setID_VPOS_KON					( OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_kon) 	: null );
			setID_VPOS_STD					( OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_std) 	: null );

			setMENGE						( OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_lademenge) 	: BigDecimal.ZERO );
			setMENGE_ABZUG					( OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.abzug_menge) 		: BigDecimal.ZERO  ) ;
			BigDecimal	 bdMengeNetto 	= ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_lademenge,BigDecimal.ZERO ).subtract(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.abzug_menge,BigDecimal.ZERO ));
			setMENGE_NETTO					( OrtIstLadeseite ?   bdMengeNetto : BigDecimal.ZERO );
			setSTEUERSATZ					( OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.steuersatz) : null );
				

			setANR1							( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.anr1) 								: fuhre.getUfs(VPOS_TPA_FUHRE.anr1_ek));
			setANR2							( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.anr2) 								: fuhre.getUfs(VPOS_TPA_FUHRE.anr2_ek));

			setARTBEZ1						( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.artbez1) 							: fuhre.getUfs(VPOS_TPA_FUHRE.artbez1_ek) );
			setARTBEZ2						( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.artbez2) 							: fuhre.getUfs(VPOS_TPA_FUHRE.artbez2_ek) );
				
			setBESTELLNUMMER				( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.bestellnummer) 						: fuhre.getUfs(VPOS_TPA_FUHRE.bestellnummer_ek) );
			setEU_STEUER_VERMERK			( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.eu_steuer_vermerk) 					: null );
			setINTRASTAT_MELD				( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.intrastat_meld) 					: null );
				
			setMANUELL_PREIS				( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.manuell_preis) 						: null  );
			setNATIONALER_ABFALL_CODE		( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.nationaler_abfall_code) 			: null  );
				
			setNOTIFIKATION_NR				( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.notifikation_nr) 					: fuhre.getUfs(VPOS_TPA_FUHRE.notifikation_nr)  );
				
			setPOSTENNUMMER					( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.postennummer) 						: fuhre.getUfs(VPOS_TPA_FUHRE.postennummer_ek)  );
			setTRANSIT_MELD					( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.transit) 							: fuhre.getUfs(VPOS_TPA_FUHRE.transit_ek)  );
			setLIEFERBEDINGUNGEN 			( OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.lieferbed_alternativ) 				: fuhre.getUfs(VPOS_TPA_FUHRE.lieferbed_alternativ_ek)  );
			setID_LIEFERBEDINGUNGEN			( helper.getIDLieferbedingungenFrom(getLIEFERBEDINGUNGEN().ValuePlain()));
				
			setKONTRAKTZWANG				( OrtIstLadeseite ?  "N" 																: (fuhre.getUfs(VPOS_TPA_FUHRE.kein_kontrakt_noetig, "N").equalsIgnoreCase("N") ? "Y" : "N" ));
			
			setID_WAEHRUNG					( trans.m_helper.m_idWaehrungStd);
		} else {
			// A2
			this.setPOS_IN_MASK(EnTabKeyInMask.A2.dbVal());
			vektor.setATOM_2(this) ;

			
			// lade ORT
			setTIMESTAMP_IN_MASK			( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.zeitstempel)    					: fuhre.getUfs(VPOS_TPA_FUHRE.zeitstempel));
			setID_ARTIKEL					( !OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_artikel) 		: helper.getArtikelIDFromArtikelBezID(fuhre.getUfs(VPOS_TPA_FUHRE.id_artikel_bez_vk)) );
				
			setBEMERKUNG_EXTERN				( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.bemerkung) 						: fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung_fuer_kunde));
			setBEMERKUNG_INTERN				( !OrtIstLadeseite ?   ""																: fuhre.getUfs(VPOS_TPA_FUHRE.bemerkung));
				
			setID_EAK_CODE					( !OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_eak_code) 	: null);
			setDATUM_AUSFUEHRUNG    		( !OrtIstLadeseite ?   ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.datum_lade_ablade)  : fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.datum_abladen) );
				
			setE_PREIS_BASISWAEHRUNG		( !OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.einzelpreis) : null );
			setE_PREIS_RES_NETTO_MGE_BASIS  ( !OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.epreis_result_netto_mge) : null);
			setID_ARTIKEL_BEZ				( !OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_artikel_bez) : fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_artikel_bez_vk));
			setID_TAX						( !OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_tax) 			: null );
			setID_VPOS_KON					( !OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_kon) 	: null );
			setID_VPOS_STD					( !OrtIstLadeseite ?   ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_std) 	: null );

			setMENGE						( !OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge) 	: BigDecimal.ZERO );
			setMENGE_ABZUG					( !OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.abzug_menge) 			: BigDecimal.ZERO  ) ;
			
			BigDecimal	 bdMengeNetto 	= ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.anteil_ablademenge,BigDecimal.ZERO ).subtract(ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.abzug_menge,BigDecimal.ZERO ));
			setMENGE_NETTO					( !OrtIstLadeseite ?   bdMengeNetto : BigDecimal.ZERO );
			setSTEUERSATZ					( !OrtIstLadeseite ?   ort.get_raw_resultValue_bigDecimal(VPOS_TPA_FUHRE_ORT.steuersatz) : null );
				

			setANR1							( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.anr1) 								: fuhre.getUfs(VPOS_TPA_FUHRE.anr1_vk));
			setANR2							( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.anr2) 								: fuhre.getUfs(VPOS_TPA_FUHRE.anr2_vk));

			setARTBEZ1						( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.artbez1) 							: fuhre.getUfs(VPOS_TPA_FUHRE.artbez1_vk) );
			setARTBEZ2						( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.artbez2) 							: fuhre.getUfs(VPOS_TPA_FUHRE.artbez2_vk) );
				
			setBESTELLNUMMER				( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.bestellnummer) 					: fuhre.getUfs(VPOS_TPA_FUHRE.bestellnummer_vk) );
			setEU_STEUER_VERMERK			( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.eu_steuer_vermerk) 				: null );
			setINTRASTAT_MELD				( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.intrastat_meld) 					: null );
				
			setMANUELL_PREIS				( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.manuell_preis) 					: null  );
			setNATIONALER_ABFALL_CODE		( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.nationaler_abfall_code) 			: null  );
				
			setNOTIFIKATION_NR				( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.notifikation_nr) 					: fuhre.getUfs(VPOS_TPA_FUHRE.notifikation_nr)  );
				
			setPOSTENNUMMER					( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.postennummer) 						: fuhre.getUfs(VPOS_TPA_FUHRE.postennummer_vk)  );
			setTRANSIT_MELD					( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.transit) 							: fuhre.getUfs(VPOS_TPA_FUHRE.transit_vk)  );
			setLIEFERBEDINGUNGEN 			( !OrtIstLadeseite ?   ort.getUfs(VPOS_TPA_FUHRE_ORT.lieferbed_alternativ) 				: fuhre.getUfs(VPOS_TPA_FUHRE.lieferbed_alternativ_vk)  );
			setID_LIEFERBEDINGUNGEN			( helper.getIDLieferbedingungenFrom(getLIEFERBEDINGUNGEN().ValuePlain()));
				
			setKONTRAKTZWANG				( !OrtIstLadeseite ?  "N" 																: (fuhre.getUfs(VPOS_TPA_FUHRE.kein_kontrakt_noetig, "N").equalsIgnoreCase("N") ? "Y" : "N" ));
			
			setID_WAEHRUNG					( trans.m_helper.m_idWaehrungStd);
			
		}
		
				
		
		
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")  ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre,_helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}

		if (fuhre.getUfs(VPOS_TPA_FUHRE.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( fuhre,_helper);
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		} else if (ort.getUfs(VPOS_TPA_FUHRE_ORT.deleted,"N").equals("Y")) {
			jt_bg_del_info i = new jt_bg_del_info( ort, _helper );
			this.setID_BG_DEL_INFO(BG_DEL_INFO._tab().seq_currval());
			this._jt_bg_del_info = i;
		}
		
	}

	



	
	/**
	 * @author manfred
	 * @date 13.02.2018
	 *
	 * @param oLager
	 * @throws myException 
	 */
	public jt_bg_atom(Bewegung_Transformation_HAND trans, jt_bg_vektor vektor, Rec21_Lager_Konto oLager, EnTabKeyInMask pos, conv_helper helper) throws myException {
		this(helper);
		
		// Einbuchung oder Ausbuchung
		boolean isEinbuchung = oLager.getUfs(LAGER_KONTO.buchungstyp,"*").equalsIgnoreCase("WE");
		
		// ladeseite == A1 / abladeseite == A2
		boolean ladeseite = (pos.equals(EnTabKeyInMask.A1) ); 
		
		// Gewichts- und Preisdaten setzen wenn...
		boolean bSetData = (isEinbuchung && !ladeseite) || (!isEinbuchung && ladeseite) ;
		
		
		// ID Atom 
		setID_BG_ATOM(BG_ATOM._tab().seq_nextval());
	
		setID_MANDANT(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_mandant) );
		setLETZTE_AENDERUNG(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(oLager.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(oLager.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));
		
		
	
		// ID Atom 
		setID_BG_ATOM(BG_ATOM._tab().seq_nextval());
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		
		if (ladeseite ){
			vektor.setATOM_1(this);
		} else { 
			vektor.setATOM_2(this);
		}

		
		// EGAL ob Lade- oder Abladeseite 
		setID_MANDANT(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_mandant));
		setLETZTE_AENDERUNG(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(oLager.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(oLager.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));

		setPOS_IN_MASK(pos.dbVal());
		setTIMESTAMP_IN_MASK(oLager.get_ufs_dbVal(LAGER_KONTO.erzeugt_am));

		
//		setBEMERKUNG_EXTERN		(oLager.getUfs(LAGER_KONTO.bemerkung));
		setBEMERKUNG_INTERN		(oLager.getUfs(LAGER_KONTO.bemerkung));
		
		// beide Seiten gleich
		setID_ARTIKEL			(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_artikel_sorte));
		Rec21_artikel art = new Rec21_artikel()._fill_id(getID_ARTIKEL().lValue);
		setID_ARTIKEL_BEZ( art.get_artikel_bez_default().getIdLong());
		
//		setID_EAK_CODE			(fuhre.get_raw_resultValue_Long(LAGER_KONTO.id_eak_code));
		
		// Seiten unterschiedlich
		setDATUM_AUSFUEHRUNG    		( oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.buchungsdatum));
		
		
		setE_PREIS_BASISWAEHRUNG		( bSetData ? oLager.get_raw_resultValue_bigDecimal(LAGER_KONTO.preis) : BigDecimal.ZERO);
		setE_PREIS_RES_NETTO_MGE_BASIS  ( bSetData ? oLager.get_raw_resultValue_bigDecimal(LAGER_KONTO.preis) : BigDecimal.ZERO);
		setMENGE						( bSetData ? oLager.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge).abs() : BigDecimal.ZERO);
		setMENGE_NETTO					( bSetData ? oLager.get_raw_resultValue_bigDecimal(LAGER_KONTO.menge).abs() : BigDecimal.ZERO);
		
		setMENGE_ABZUG					( BigDecimal.ZERO );

		if (_helper.m_rlArtikel.containsKey(oLager.getUfs(LAGER_KONTO.id_artikel_sorte))) {
			Rec21 recArtikel = _helper.m_rlArtikel.get(oLager.getUfs(LAGER_KONTO.id_artikel_sorte));
			setANR1						( recArtikel.get_ufs_dbVal(ARTIKEL.anr1));
			setARTBEZ1					( recArtikel.get_ufs_dbVal(ARTIKEL.artbez1));
			setARTBEZ2					( recArtikel.get_ufs_dbVal(ARTIKEL.artbez2));
			
		}
		setID_WAEHRUNG					( trans.m_helper.m_idWaehrungStd);
		
		if (oLager.getUfs(LAGER_KONTO.storno,"N").equals("Y")){
			jt_bg_storno_info i = new jt_bg_storno_info( oLager, _helper);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
	}



	
	
	
	public DODate getLETZTE_AENDERUNG() {
	    return LETZTE_AENDERUNG;
	}

	public jt_bg_atom setLETZTE_AENDERUNG(Date pLETZTE_AENDERUNG) {
	    LETZTE_AENDERUNG.setValue(pLETZTE_AENDERUNG);
	    return this;
	}


	public DODate getERZEUGT_AM() {
	    return ERZEUGT_AM;
	}

	public jt_bg_atom setERZEUGT_AM(Date pERZEUGT_AM) {
	    ERZEUGT_AM.setValue(pERZEUGT_AM);
	    return this;
	}


	public DODate getDATUM_AUSFUEHRUNG() {
	    return DATUM_AUSFUEHRUNG;
	}

	public jt_bg_atom setDATUM_AUSFUEHRUNG(Date pDATUM_AUSFUEHRUNG) {
	    DATUM_AUSFUEHRUNG.setValue(pDATUM_AUSFUEHRUNG);
	    return this;
	}







	public DOString getID_BG_ATOM() {
	    return ID_BG_ATOM;
	}

	public jt_bg_atom setID_BG_ATOM(String pID_BG_ATOM) {
	    ID_BG_ATOM.setValue(pID_BG_ATOM);
	    return this;
	}


	public DOLong getID_MANDANT() {
	    return ID_MANDANT;
	}

	public jt_bg_atom setID_MANDANT(Long pID_MANDANT) {
	    ID_MANDANT.setValue(pID_MANDANT);
	    return this;
	}




	public DOLong getID_EAK_CODE() {
	    return ID_EAK_CODE;
	}

	public jt_bg_atom setID_EAK_CODE(Long pID_EAK_CODE) {
	    ID_EAK_CODE.setValue(pID_EAK_CODE);
	    return this;
	}






	public DOLong getID_ZAHLUNGSBEDINGUNGEN() {
	    return ID_ZAHLUNGSBEDINGUNGEN;
	}

	public jt_bg_atom setID_ZAHLUNGSBEDINGUNGEN(Long pID_ZAHLUNGSBEDINGUNGEN) {
	    ID_ZAHLUNGSBEDINGUNGEN.setValue(pID_ZAHLUNGSBEDINGUNGEN);
	    return this;
	}


	public DOLong getID_LIEFERBEDINGUNGEN() {
	    return ID_LIEFERBEDINGUNGEN;
	}

	public jt_bg_atom setID_LIEFERBEDINGUNGEN(Long pID_LIEFERBEDINGUNGEN) {
	    ID_LIEFERBEDINGUNGEN.setValue(pID_LIEFERBEDINGUNGEN);
	    return this;
	}


	
	public jt_bg_atom set_jt_bg_del_info(jt_bg_del_info delInfo){
		this._jt_bg_del_info = delInfo;
		return this;
	}
	
	public jt_bg_del_info get_jt_bg_del_info(){
		return (jt_bg_del_info)_jt_bg_del_info;
	}
	

	public jt_bg_atom set_jt_bg_storno_info(jt_bg_storno_info stornoInfo){
		this._jt_bg_storno_info = stornoInfo;
		return this;
	}
	
	public jt_bg_storno_info get_jt_bg_storno_info(){
		return (jt_bg_storno_info)_jt_bg_storno_info;
	}
	
	







	public  DOString getID_BG_VEKTOR() {
		return ID_BG_VEKTOR;
	}


	public jt_bg_atom setID_BG_VEKTOR(String iD_BG_VEKTOR) {
		ID_BG_VEKTOR.setValue(iD_BG_VEKTOR);
		return this;
	}


	public jt_bg_atom set_jt_bg_station_quelle(jt_bg_station station){
		this._jt_bg_station_quelle = station;
		return this;
	}
	
	public jt_bg_station get_jt_bg_station_quelle(){
		return _jt_bg_station_quelle;
	}
	


	public DOLong getID_BG_STATION_QUELLE(){
		return ID_BG_STATION_QUELLE;
	}
	public jt_bg_atom setID_BG_STATION_QUELLE (Long _ID_BG_STATION_QUELLE){
		ID_BG_STATION_QUELLE.setValue(_ID_BG_STATION_QUELLE);
		return this;
	}

	

	public jt_bg_atom set_jt_bg_station_ziel(jt_bg_station station){
		this._jt_bg_station_ziel = station;
		return this;
	}
	
	public jt_bg_station get_jt_bg_station_ziel(){
		return _jt_bg_station_ziel;
	}
	
	
	public DOLong getID_BG_STATION_ZIEL(){
		return ID_BG_STATION_ZIEL;
	}
	public jt_bg_atom setID_BG_STATION_ZIEL (Long _ID_BG_STATION_ZIEL){
		ID_BG_STATION_ZIEL.setValue(_ID_BG_STATION_ZIEL);
		return this;
	}

	public DOLong getID_BG_RECH_BLOCK(){
		return ID_BG_RECH_BLOCK;
	}
	public jt_bg_atom setID_BG_RECH_BLOCK (Long _ID_BG_RECH_BLOCK){
		ID_BG_RECH_BLOCK.setValue(_ID_BG_RECH_BLOCK);
		return this;
	}


	public DOLong getID_ARTIKEL(){
		return ID_ARTIKEL;
	}
	public jt_bg_atom setID_ARTIKEL (Long _ID_ARTIKEL){
		ID_ARTIKEL.setValue(_ID_ARTIKEL);
		return this;
	}

	public DOLong getID_ARTIKEL_BEZ(){
		return ID_ARTIKEL_BEZ;
	}
	public jt_bg_atom setID_ARTIKEL_BEZ (Long _ID_ARTIKEL_BEZ){
		ID_ARTIKEL_BEZ.setValue(_ID_ARTIKEL_BEZ);
		return this;
	}

	public DOLong getID_VPOS_KON(){
		return ID_VPOS_KON;
	}
	public jt_bg_atom setID_VPOS_KON (Long _ID_VPOS_KON){
		ID_VPOS_KON.setValue(_ID_VPOS_KON);
		return this;
	}

	public DOLong getID_VPOS_STD(){
		return ID_VPOS_STD;
	}
	public jt_bg_atom setID_VPOS_STD (Long _ID_VPOS_STD){
		ID_VPOS_STD.setValue(_ID_VPOS_STD);
		return this;
	}

	public DOLong getID_TAX(){
		return ID_TAX;
	}
	public jt_bg_atom setID_TAX (Long _ID_TAX){
		ID_TAX.setValue(_ID_TAX);
		return this;
	}

	public DOLong getID_WAEHRUNG(){
		return ID_WAEHRUNG;
	}
	public jt_bg_atom setID_WAEHRUNG (Long _ID_WAEHRUNG){
		ID_WAEHRUNG.setValue(_ID_WAEHRUNG);
		return this;
	}

	public DOLong getID_BG_PRUEFPROT_MENGE(){
		return ID_BG_PRUEFPROT_MENGE;
	}
	public jt_bg_atom setID_BG_PRUEFPROT_MENGE (Long _ID_BG_PRUEFPROT_MENGE){
		ID_BG_PRUEFPROT_MENGE.setValue(_ID_BG_PRUEFPROT_MENGE);
		return this;
	}

	public DOLong getID_BG_PRUEFPROT_PREIS(){
		return ID_BG_PRUEFPROT_PREIS;
	}
	public jt_bg_atom setID_BG_PRUEFPROT_PREIS (Long _ID_BG_PRUEFPROT_PREIS){
		ID_BG_PRUEFPROT_PREIS.setValue(_ID_BG_PRUEFPROT_PREIS);
		return this;
	}

	public DOLong getID_BG_PRUEFPROT_ABSCHLUSS(){
		return ID_BG_PRUEFPROT_ABSCHLUSS;
	}
	public jt_bg_atom setID_BG_PRUEFPROT_ABSCHLUSS (Long _ID_BG_PRUEFPROT_ABSCHLUSS){
		ID_BG_PRUEFPROT_ABSCHLUSS.setValue(_ID_BG_PRUEFPROT_ABSCHLUSS);
		return this;
	}

	public DOString getID_BG_STORNO_INFO(){
		return ID_BG_STORNO_INFO;
	}

	public jt_bg_atom setID_BG_STORNO_INFO(String pID_BG_STORNO_INFO) {
	    ID_BG_STORNO_INFO.setValue(pID_BG_STORNO_INFO);
	    return this;
	}
	
	public DOString getID_BG_DEL_INFO(){
		return ID_BG_DEL_INFO;
	}
	

	public jt_bg_atom setID_BG_DEL_INFO(String pID_BG_DEL_INFO) {
	    ID_BG_DEL_INFO.setValue(pID_BG_DEL_INFO);
	    return this;
	}
	

	public DOLong getID_LAGER_KONTO(){
		return ID_LAGER_KONTO;
	}
	public jt_bg_atom setID_LAGER_KONTO (Long _ID_LAGER_KONTO){
		ID_LAGER_KONTO.setValue(_ID_LAGER_KONTO);
		return this;
	}

	public DOLong getID_ZOLLTARIFNUMMER(){
		return ID_ZOLLTARIFNUMMER;
	}
	public jt_bg_atom setID_ZOLLTARIFNUMMER (Long _ID_ZOLLTARIFNUMMER){
		ID_ZOLLTARIFNUMMER.setValue(_ID_ZOLLTARIFNUMMER);
		return this;
	}






	public DOBigDecimal getE_PREIS_BASISWAEHRUNG() {
		return E_PREIS_BASISWAEHRUNG;
	}


	public jt_bg_atom setE_PREIS_BASISWAEHRUNG(BigDecimal e_PREIS_BASISWAEHRUNG) {
		E_PREIS_BASISWAEHRUNG.setValue(e_PREIS_BASISWAEHRUNG);
		return this;
	}




	public DOBigDecimal getE_PREIS_FREMDWAEHRUNG() {
		return E_PREIS_FREMDWAEHRUNG;
	}
	public jt_bg_atom setE_PREIS_FREMDWAEHRUNG(BigDecimal e_PREIS_FREMDWAEHRUNG) {
		E_PREIS_FREMDWAEHRUNG.setValue( e_PREIS_FREMDWAEHRUNG);
		return this;
	}

	public DOBigDecimal getE_PREIS_RES_NETTO_MGE_BASIS() {
		return E_PREIS_RES_NETTO_MGE_BASIS;
	}


	public jt_bg_atom setE_PREIS_RES_NETTO_MGE_BASIS(BigDecimal e_PREIS_RES_NETTO_MGE_BASIS) {
		E_PREIS_RES_NETTO_MGE_BASIS.setValue(e_PREIS_RES_NETTO_MGE_BASIS);
		return this;
	}




	public DOBigDecimal getE_PREIS_RES_NETTO_MGE_FREMD() {
		return E_PREIS_RES_NETTO_MGE_FREMD;
	}


	public jt_bg_atom setE_PREIS_RES_NETTO_MGE_FREMD(BigDecimal e_PREIS_RES_NETTO_MGE_FREMD) {
		E_PREIS_RES_NETTO_MGE_FREMD.setValue(e_PREIS_RES_NETTO_MGE_FREMD);
		return this;
	}




	public DOBigDecimal getG_PREIS_ABZUG_BASIS() {
		return G_PREIS_ABZUG_BASIS;
	}

	public jt_bg_atom setG_PREIS_ABZUG_BASIS(BigDecimal g_PREIS_ABZUG_BASIS) {
		G_PREIS_ABZUG_BASIS .setValue(g_PREIS_ABZUG_BASIS);
		return this;
	}




	public DOBigDecimal getG_PREIS_ABZUG_FREMD() {
		return G_PREIS_ABZUG_FREMD;
	}

	public jt_bg_atom setG_PREIS_ABZUG_FREMD(BigDecimal g_PREIS_ABZUG_FREMD) {
		G_PREIS_ABZUG_FREMD .setValue(g_PREIS_ABZUG_FREMD);
		return this;
	}





	public DOBigDecimal getG_PREIS_BASISWAEHRUNG() {
		return G_PREIS_BASISWAEHRUNG;
	}

	public jt_bg_atom setG_PREIS_BASISWAEHRUNG(BigDecimal g_PREIS_BASISWAEHRUNG) {
		G_PREIS_BASISWAEHRUNG .setValue(g_PREIS_BASISWAEHRUNG);
		return this;
	}




	public DOBigDecimal getG_PREIS_FREMDWAEHRUNG() {
		return G_PREIS_FREMDWAEHRUNG;
	}

	public jt_bg_atom setG_PREIS_FREMDWAEHRUNG(BigDecimal g_PREIS_FREMDWAEHRUNG) {
		G_PREIS_FREMDWAEHRUNG .setValue(g_PREIS_FREMDWAEHRUNG);
		return this;
	}




	public DOBigDecimal getKOSTEN_PRODUKT() {
		return KOSTEN_PRODUKT;
	}

	public jt_bg_atom setKOSTEN_PRODUKT(BigDecimal kOSTEN_PRODUKT) {
		KOSTEN_PRODUKT .setValue(kOSTEN_PRODUKT);
		return this;
	}





	public DOBigDecimal getMENGE() {
		return MENGE;
	}

	public jt_bg_atom setMENGE(BigDecimal mENGE) {
		MENGE.setValue(mENGE);
		return this;
	}





	public DOBigDecimal getMENGE_ABRECH() {
		return MENGE_ABRECH;
	}


	public jt_bg_atom setMENGE_ABRECH(BigDecimal mENGE_ABRECH) {
		MENGE_ABRECH .setValue(mENGE_ABRECH);
		return this;
	}




	public DOBigDecimal getMENGE_ABZUG() {
		return MENGE_ABZUG;
	}

	public jt_bg_atom setMENGE_ABZUG(BigDecimal mENGE_ABZUG) {
		MENGE_ABZUG .setValue(mENGE_ABZUG);
		return this;
	}





	public DOBigDecimal getMENGE_NETTO() {
		return MENGE_NETTO;
	}


	public jt_bg_atom setMENGE_NETTO(BigDecimal mENGE_NETTO) {
		MENGE_NETTO .setValue( mENGE_NETTO);
		return this;
	}




	public DOBigDecimal getMENGE_VERTEILUNG() {
		return MENGE_VERTEILUNG;
	}

	public jt_bg_atom setMENGE_VERTEILUNG(BigDecimal mENGE_VERTEILUNG) {
		MENGE_VERTEILUNG .setValue(mENGE_VERTEILUNG);
		return this;
	}



	public DOBigDecimal getSTEUERSATZ() {
		return STEUERSATZ;
	}

	public jt_bg_atom setSTEUERSATZ(BigDecimal sTEUERSATZ) {
		STEUERSATZ .setValue(sTEUERSATZ);
		return this;
	}




	public DOBigDecimal getWAEHRUNGSKURS() {
		return WAEHRUNGSKURS;
	}

	public jt_bg_atom setWAEHRUNGSKURS(BigDecimal wAEHRUNGSKURS) {
		WAEHRUNGSKURS.setValue(wAEHRUNGSKURS);
		return this;
	}

	public DOString getGEAENDERT_VON(){
		return GEAENDERT_VON;
	}
	public jt_bg_atom setGEAENDERT_VON (String _GEAENDERT_VON){
		GEAENDERT_VON.setValue(_GEAENDERT_VON);
		return this;
	}

	public DOString getERZEUGT_VON(){
		return ERZEUGT_VON;
	}
	public jt_bg_atom setERZEUGT_VON (String _ERZEUGT_VON){
		ERZEUGT_VON.setValue(_ERZEUGT_VON);
		return this;
	}

	public DOString getMANUELL_PREIS(){
		return MANUELL_PREIS;
	}
	public jt_bg_atom setMANUELL_PREIS (String _MANUELL_PREIS){
		MANUELL_PREIS.setValue(_MANUELL_PREIS);
		return this;
	}

	public DOString getLIEFERBEDINGUNGEN(){
		return LIEFERBEDINGUNGEN;
	}
	public jt_bg_atom setLIEFERBEDINGUNGEN (String _LIEFERBEDINGUNGEN){
		LIEFERBEDINGUNGEN.setValue(_LIEFERBEDINGUNGEN);
		return this;
	}

	public DOString getZAHLUNGSBEDINGUNGEN(){
		return ZAHLUNGSBEDINGUNGEN;
	}
	public jt_bg_atom setZAHLUNGSBEDINGUNGEN (String _ZAHLUNGSBEDINGUNGEN){
		ZAHLUNGSBEDINGUNGEN.setValue(_ZAHLUNGSBEDINGUNGEN);
		return this;
	}

	public DOString getANR1(){
		return ANR1;
	}
	public jt_bg_atom setANR1 (String _ANR1){
		ANR1.setValue(_ANR1);
		return this;
	}

	public DOString getANR2(){
		return ANR2;
	}
	public jt_bg_atom setANR2 (String _ANR2){
		ANR2.setValue(_ANR2);
		return this;
	}

	public DOString getARTBEZ1(){
		return ARTBEZ1;
	}
	public jt_bg_atom setARTBEZ1 (String _ARTBEZ1){
		ARTBEZ1.setValue(_ARTBEZ1);
		return this;
	}

	public DOString getARTBEZ2(){
		return ARTBEZ2;
	}
	public jt_bg_atom setARTBEZ2 (String _ARTBEZ2){
		ARTBEZ2.setValue(_ARTBEZ2);
		return this;
	}

	public DOString getKONTRAKTZWANG(){
		return KONTRAKTZWANG;
	}
	public jt_bg_atom setKONTRAKTZWANG (String _KONTRAKTZWANG){
		KONTRAKTZWANG.setValue(_KONTRAKTZWANG);
		return this;
	}

	public DOString getPOSTENNUMMER(){
		return POSTENNUMMER;
	}
	public jt_bg_atom setPOSTENNUMMER (String _POSTENNUMMER){
		POSTENNUMMER.setValue(_POSTENNUMMER);
		return this;
	}

	public DOString getEU_STEUER_VERMERK(){
		return EU_STEUER_VERMERK;
	}
	public jt_bg_atom setEU_STEUER_VERMERK (String _EU_STEUER_VERMERK){
		EU_STEUER_VERMERK.setValue(_EU_STEUER_VERMERK);
		return this;
	}

	public DOString getBEMERKUNG_EXTERN(){
		return BEMERKUNG_EXTERN;
	}
	public jt_bg_atom setBEMERKUNG_EXTERN (String _BEMERKUNG_EXTERN){
		BEMERKUNG_EXTERN.setValue(_BEMERKUNG_EXTERN);
		return this;
	}

	public DOString getBEMERKUNG_INTERN(){
		return BEMERKUNG_INTERN;
	}
	public jt_bg_atom setBEMERKUNG_INTERN (String _BEMERKUNG_INTERN){
		BEMERKUNG_INTERN.setValue(_BEMERKUNG_INTERN);
		return this;
	}

	public DOString getTIMESTAMP_IN_MASK(){
		return TIMESTAMP_IN_MASK;
	}
	public jt_bg_atom setTIMESTAMP_IN_MASK (String _TIMESTAMP_IN_MASK){
		TIMESTAMP_IN_MASK.setValue(_TIMESTAMP_IN_MASK);
		return this;
	}

	public DOString getNOTIFIKATION_NR(){
		return NOTIFIKATION_NR;
	}
	public jt_bg_atom setNOTIFIKATION_NR (String _NOTIFIKATION_NR){
		NOTIFIKATION_NR.setValue(_NOTIFIKATION_NR);
		return this;
	}

	public DOString getNATIONALER_ABFALL_CODE(){
		return NATIONALER_ABFALL_CODE;
	}
	public jt_bg_atom setNATIONALER_ABFALL_CODE (String _NATIONALER_ABFALL_CODE){
		NATIONALER_ABFALL_CODE.setValue(_NATIONALER_ABFALL_CODE);
		return this;
	}

	public DOString getBESTELLNUMMER(){
		return BESTELLNUMMER;
	}
	public jt_bg_atom setBESTELLNUMMER (String _BESTELLNUMMER){
		BESTELLNUMMER.setValue(_BESTELLNUMMER);
		return this;
	}

	public DOString getPOS_IN_MASK(){
		return POS_IN_MASK;
	}
	public jt_bg_atom setPOS_IN_MASK (String _POS_IN_MASK){
		POS_IN_MASK.setValue(_POS_IN_MASK);
		return this;
	}

	public DOString getINTRASTAT_MELD(){
		return INTRASTAT_MELD;
	}
	public jt_bg_atom setINTRASTAT_MELD (String _INTRASTAT_MELD){
		INTRASTAT_MELD.setValue(_INTRASTAT_MELD);
		return this;
	}

	public DOString getTRANSIT_MELD(){
		return TRANSIT_MELD;
	}
	public jt_bg_atom setTRANSIT_MELD (String _TRANSIT_MELD){
		TRANSIT_MELD.setValue(_TRANSIT_MELD);
		return this;
	}




	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {

		m_vDataObjects.addElement( DATUM_AUSFUEHRUNG );
		m_vDataObjects.addElement( ERZEUGT_AM );
		m_vDataObjects.addElement( LETZTE_AENDERUNG );
		m_vDataObjects.addElement( E_PREIS_BASISWAEHRUNG );
		m_vDataObjects.addElement( E_PREIS_FREMDWAEHRUNG );
		m_vDataObjects.addElement( E_PREIS_RES_NETTO_MGE_BASIS );
		m_vDataObjects.addElement( E_PREIS_RES_NETTO_MGE_FREMD );
		m_vDataObjects.addElement( G_PREIS_ABZUG_BASIS );
		m_vDataObjects.addElement( G_PREIS_ABZUG_FREMD );
		m_vDataObjects.addElement( G_PREIS_BASISWAEHRUNG );
		m_vDataObjects.addElement( G_PREIS_FREMDWAEHRUNG );
		m_vDataObjects.addElement( ID_ARTIKEL );
		m_vDataObjects.addElement( ID_ARTIKEL_BEZ );
		m_vDataObjects.addElement( ID_BG_ATOM );
		m_vDataObjects.addElement( ID_BG_DEL_INFO );
		m_vDataObjects.addElement( ID_BG_PRUEFPROT_ABSCHLUSS );
		m_vDataObjects.addElement( ID_BG_PRUEFPROT_MENGE );
		m_vDataObjects.addElement( ID_BG_PRUEFPROT_PREIS );
		m_vDataObjects.addElement( ID_BG_RECH_BLOCK );
		m_vDataObjects.addElement( ID_BG_STATION_QUELLE );
		m_vDataObjects.addElement( ID_BG_STATION_ZIEL );
		m_vDataObjects.addElement( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement( ID_BG_VEKTOR );
		m_vDataObjects.addElement( ID_EAK_CODE );
		m_vDataObjects.addElement( ID_LAGER_KONTO );
		m_vDataObjects.addElement( ID_LIEFERBEDINGUNGEN );
		m_vDataObjects.addElement( ID_MANDANT );
		m_vDataObjects.addElement( ID_TAX );
		m_vDataObjects.addElement( ID_VPOS_KON );
		m_vDataObjects.addElement( ID_VPOS_STD );
		m_vDataObjects.addElement( ID_WAEHRUNG );
		m_vDataObjects.addElement( ID_ZAHLUNGSBEDINGUNGEN );
		m_vDataObjects.addElement( ID_ZOLLTARIFNUMMER );
		m_vDataObjects.addElement( KOSTEN_PRODUKT );
		m_vDataObjects.addElement( MENGE );
		m_vDataObjects.addElement( MENGE_ABRECH );
		m_vDataObjects.addElement( MENGE_ABZUG );
		m_vDataObjects.addElement( MENGE_NETTO );
		m_vDataObjects.addElement( MENGE_VERTEILUNG );
		m_vDataObjects.addElement( STEUERSATZ );
		m_vDataObjects.addElement( WAEHRUNGSKURS );
		m_vDataObjects.addElement( ANR1 );
		m_vDataObjects.addElement( ANR2 );
		m_vDataObjects.addElement( ARTBEZ1 );
		m_vDataObjects.addElement( ARTBEZ2 );
		m_vDataObjects.addElement( BEMERKUNG_EXTERN );
		m_vDataObjects.addElement( BEMERKUNG_INTERN );
		m_vDataObjects.addElement( BESTELLNUMMER );
		m_vDataObjects.addElement( ERZEUGT_VON );
		m_vDataObjects.addElement( EU_STEUER_VERMERK );
		m_vDataObjects.addElement( GEAENDERT_VON );
		m_vDataObjects.addElement( INTRASTAT_MELD );
		m_vDataObjects.addElement( KONTRAKTZWANG );
		m_vDataObjects.addElement( LIEFERBEDINGUNGEN );
		m_vDataObjects.addElement( MANUELL_PREIS );
		m_vDataObjects.addElement( NATIONALER_ABFALL_CODE );
		m_vDataObjects.addElement( NOTIFIKATION_NR );
		m_vDataObjects.addElement( POS_IN_MASK );
		m_vDataObjects.addElement( POSTENNUMMER );
		m_vDataObjects.addElement( TIMESTAMP_IN_MASK );
		m_vDataObjects.addElement( TRANSIT_MELD );
		m_vDataObjects.addElement( ZAHLUNGSBEDINGUNGEN );
		
	}








	
}
