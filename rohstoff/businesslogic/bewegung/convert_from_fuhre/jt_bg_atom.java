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

import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
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
/**
 * @author manfred
 * @date 16.01.2018
 *
 */
public class jt_bg_atom extends REC_Base{


	


	private DOString 	ID_BG_ATOM = new DOString("ID_BG_ATOM",null,"");
	private DOLong 		ID_MANDANT = new DOLong("ID_MANDANT");
	
	private DOString 		ID_BG_STORNO_INFO = new DOString("ID_BG_STORNO_INFO",null,"");
	private DOString 		ID_BG_DEL_INFO = new DOString("ID_BG_DEL_INFO",null,"");

	private DODate 		LETZTE_AENDERUNG = new DODate("LETZTE_AENDERUNG");
	private DOString 	GEAENDERT_VON = new DOString("GEAENDERT_VON");
	private DOString 	ERZEUGT_VON = new DOString("ERZEUGT_VON");
	private DODate 		ERZEUGT_AM = new DODate("ERZEUGT_AM");
	private DOLong 		POSNR = new DOLong("POSNR");
	private DOString 	eN_ATOM_TYP = new DOString("EN_ATOM_TYP");
	private DOString 	eN_ATOM_POS_IN_BG = new DOString("EN_ATOM_POS_IN_BG");
	private DOString 	NOTIFIKATION_NR = new DOString("NOTIFIKATION_NR");
	private DOString 	NATIONALER_ABFALL_CODE = new DOString("NATIONALER_ABFALL_CODE");
	private DOString 	TIMESTAMP_IN_MASK = new DOString("TIMESTAMP_IN_MASK");
	private DOString 	INTRASTAT_MELD = new DOString("INTRASTAT_MELD");
	private DOBigDecimal E_PREIS = new DOBigDecimal("E_PREIS");
	private DOString 	MANUELL_PREIS = new DOString("MANUELL_PREIS");
	private DOBigDecimal KOSTEN_TRANSPORT = new DOBigDecimal("KOSTEN_TRANSPORT");
	private DODate 		DATUM_AUSFUEHRUNG = new DODate("DATUM_AUSFUEHRUNG");
	private DOString 	LIEFERBEDINGUNGEN = new DOString("LIEFERBEDINGUNGEN");
	private DOString 	ZAHLUNGSBEDINGUNGEN = new DOString("ZAHLUNGSBEDINGUNGEN");
	
	private DOString 	ID_BG_VEKTOR = new DOString("ID_BG_VEKTOR",null,"");
	private DOLong 		ID_ADRESSE_LOGI_START = new DOLong("ID_ADRESSE_LOGI_START");
	private DOLong 		ID_ADRESSE_LOGI_ZIEL = new DOLong("ID_ADRESSE_LOGI_ZIEL");
	private DOLong 		ID_VPOS_TPA_FUHRE = new DOLong("ID_VPOS_TPA_FUHRE");
	private DOLong 		ID_VPOS_TPA_FUHRE_ORT = new DOLong("ID_VPOS_TPA_FUHRE_ORT");
	private DOLong 		ID_EAK_CODE = new DOLong("ID_EAK_CODE");
	private DOLong 		ID_ZAHLUNGSBEDINGUNGEN = new DOLong("ID_ZAHLUNGSBEDINGUNGEN");
	private DOLong 		ID_LIEFERBEDINGUNGEN = new DOLong("ID_LIEFERBEDINGUNGEN");

	
	// Ladungen
	private Vector<jt_bg_ladung> jt_bg_ladungs = new Vector<>();
	
	// Vektor
	private jt_bg_vektor jt_bg_vektor = null;

	// del und storno-INFOS
	private REC_Base     _jt_bg_del_info = null;
	private REC_Base 	 _jt_bg_storno_info = null;

	
	public jt_bg_atom() {
		super("JT_BG_ATOM");
		this.initFieldList();
	}
	
	
	
	
	
	/**
	 * @author manfred
	 * @date 19.01.2018
	 *
	 * @param iD_BG_ATOM
	 * @param iD_MANDANT
	 */
	public jt_bg_atom(String iD_BG_ATOM, String iD_MANDANT) {
		this();
		ID_BG_ATOM.setValue(iD_BG_ATOM);
		ID_MANDANT.setValue(iD_MANDANT);
	}

	
	/**
	 * to be set:
	 * 
	 * posnr 
	 * en_atom_typ
	 * timestamp_in_mask
	 * intrastat_meld
	 * e_preis
	 * manuell_preis
	 * datum_ausfuehrung
	 * 
	 * @author manfred
	 * @date 19.01.2018
	 * @param fuhre
	 *
	 * @throws myException
	 */
	public jt_bg_atom(Rec20_VPOS_TPA_FUHRE_ext fuhre) throws myException{
		this();
		// ID Atom 
		setID_BG_ATOM(BG_ATOM._tab().seq_nextval());
		
//		// vektor-atom-Verbindung
//		set_jt_bg_vektor(vektor);
//		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
//		vektor.add_bgAtom(this);
	
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant));
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));
		
		setID_VPOS_TPA_FUHRE(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre));
		
		setNOTIFIKATION_NR(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.notifikation_nr));
		setNATIONALER_ABFALL_CODE(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.nationaler_abfall_code));
		setTIMESTAMP_IN_MASK(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.zeitstempel));
		
		// intrastat ??
//		if (fuhre.is_yes_db_val(VPOS_TPA_FUHRE.intrastat_meld_in) ||fuhre.is_yes_db_val(VPOS_TPA_FUHRE.intrastat_meld_out) ){
//			setINTRASTAT_MELD("Y");
//		}
		
		
		//setINTRASTAT_MELD()
		//setE_PREIS()
		//setMANUELL_PREIS()
		//setKOSTEN_TRANSPORT()
		//setDATUM_AUSFUEHRUNG()
		//setID_ADRESSE_LOGI_START()
		//setID_ADRESSE_LOGI_ZIEL()
		setID_EAK_CODE(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_eak_code));
		//setID_ZAHLUNGSBEDINGUNGEN(o)
		//setID_ZAHLUNGSBEDINGUNGEN()
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")  ){
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

	
	
	public jt_bg_atom(jt_bg_vektor vektor, Rec20_VPOS_TPA_FUHRE_ORT_ext o, Rec20_VPOS_TPA_FUHRE_ext fuhre) throws myException{
		this();
		// ID Atom 
		setID_BG_ATOM(BG_ATOM._tab().seq_nextval());
		
		// vektor-atom-Verbindung
		set_jt_bg_vektor(vektor);
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
		vektor.add_bgAtom(this);
		
		setID_MANDANT(o.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_mandant));
		setLETZTE_AENDERUNG(o.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.letzte_aenderung) )  ;
		setGEAENDERT_VON(o.get_ufs_dbVal( VPOS_TPA_FUHRE_ORT.geaendert_von));
		setERZEUGT_AM(o.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.erzeugt_am));
		setERZEUGT_VON(o.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.erzeugt_von));
		
		setID_VPOS_TPA_FUHRE(o.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre) );
		setID_VPOS_TPA_FUHRE_ORT(o.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort) );
		
		setNOTIFIKATION_NR(o.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.notifikation_nr));
		setNATIONALER_ABFALL_CODE(o.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.nationaler_abfall_code));
		
		setTIMESTAMP_IN_MASK(o.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.zeitstempel));
		setINTRASTAT_MELD(o.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.intrastat_meld));
		//setE_PREIS()
		//setMANUELL_PREIS()
		//setKOSTEN_TRANSPORT()
		//setDATUM_AUSFUEHRUNG()
		//setID_ADRESSE_LOGI_START()
		//setID_ADRESSE_LOGI_ZIEL()
		setID_EAK_CODE(o.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_eak_code) );
		//setID_ZAHLUNGSBEDINGUNGEN(o)
		//setID_ZAHLUNGSBEDINGUNGEN()
		
		if (fuhre.getUfs(VPOS_TPA_FUHRE.ist_storniert,"N").equals("Y")  ){
			jt_bg_storno_info i = new jt_bg_storno_info( fuhre);
			this.setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_currval());
			this._jt_bg_storno_info = i;
		}
		
		

		if (o.getUfs(VPOS_TPA_FUHRE_ORT.deleted,"N").equals("Y")   ){
			jt_bg_del_info i = new jt_bg_del_info( o);
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
	public jt_bg_atom(Rec20_Lager_Konto oLager) throws myException {
		this();
		// ID Atom 
		setID_BG_ATOM(BG_ATOM._tab().seq_nextval());
	
		setEN_ATOM_TYP(EN_ATOM_TYP.HAUPTATOM);

		setID_MANDANT(oLager.get_raw_resultValue_Long(LAGER_KONTO.id_mandant) );
		setLETZTE_AENDERUNG(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(oLager.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(oLager.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(oLager.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));
		
		setE_PREIS(oLager.get_raw_resultValue_bigDecimal(LAGER_KONTO.preis));

	}





	/**
	 * die Ladungen des Atoms
	 */

	public Vector<jt_bg_ladung> get_jt_bg_ladungs(){
		return jt_bg_ladungs;
	}
	
	public jt_bg_atom add_jt_bg_ladung(jt_bg_ladung ladung){
		jt_bg_ladungs.add(ladung);
		return this;
	}
	
	public jt_bg_atom set_jt_bg_ladungs(Vector<jt_bg_ladung> ladungs){
		jt_bg_ladungs = ladungs;
		return this;
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




	public DOBigDecimal getE_PREIS() {
	    return E_PREIS;
	}

	public jt_bg_atom setE_PREIS(BigDecimal pE_PREIS) {
	    E_PREIS.setValue(pE_PREIS);
	    return this;
	}


	public DOBigDecimal getKOSTEN_TRANSPORT() {
	    return KOSTEN_TRANSPORT;
	}

	public jt_bg_atom setKOSTEN_TRANSPORT(BigDecimal pKOSTEN_TRANSPORT) {
	    KOSTEN_TRANSPORT.setValue(pKOSTEN_TRANSPORT);
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


	public DOLong getPOSNR() {
	    return POSNR;
	}

	public jt_bg_atom setPOSNR(Long pPOSNR) {
	    POSNR.setValue(pPOSNR);
	    return this;
	}


	public DOString getID_BG_VEKTOR() {
	    return ID_BG_VEKTOR;
	}

	public jt_bg_atom setID_BG_VEKTOR(String pID_BG_VEKTOR) {
	    ID_BG_VEKTOR.setValue(pID_BG_VEKTOR);
	    return this;
	}


	public DOLong getID_ADRESSE_LOGI_START() {
	    return ID_ADRESSE_LOGI_START;
	}

	public jt_bg_atom setID_ADRESSE_LOGI_START(Long pID_ADRESSE_LOGI_START) {
	    ID_ADRESSE_LOGI_START.setValue(pID_ADRESSE_LOGI_START);
	    return this;
	}


	public DOLong getID_ADRESSE_LOGI_ZIEL() {
	    return ID_ADRESSE_LOGI_ZIEL;
	}

	public jt_bg_atom setID_ADRESSE_LOGI_ZIEL(Long pID_ADRESSE_LOGI_ZIEL) {
	    ID_ADRESSE_LOGI_ZIEL.setValue(pID_ADRESSE_LOGI_ZIEL);
	    return this;
	}


	public DOLong getID_VPOS_TPA_FUHRE() {
	    return ID_VPOS_TPA_FUHRE;
	}

	public jt_bg_atom setID_VPOS_TPA_FUHRE(Long pID_VPOS_TPA_FUHRE) {
	    ID_VPOS_TPA_FUHRE.setValue(pID_VPOS_TPA_FUHRE);
	    return this;
	}


	public DOLong getID_VPOS_TPA_FUHRE_ORT() {
	    return ID_VPOS_TPA_FUHRE_ORT;
	}

	public jt_bg_atom setID_VPOS_TPA_FUHRE_ORT(Long pID_VPOS_TPA_FUHRE_ORT) {
	    ID_VPOS_TPA_FUHRE_ORT.setValue(pID_VPOS_TPA_FUHRE_ORT);
	    return this;
	}


	public DOLong getID_EAK_CODE() {
	    return ID_EAK_CODE;
	}

	public jt_bg_atom setID_EAK_CODE(Long pID_EAK_CODE) {
	    ID_EAK_CODE.setValue(pID_EAK_CODE);
	    return this;
	}


	public DOString getID_BG_STORNO_INFO() {
	    return ID_BG_STORNO_INFO;
	}

	public jt_bg_atom setID_BG_STORNO_INFO(String pID_BG_STORNO_INFO) {
	    ID_BG_STORNO_INFO.setValue(pID_BG_STORNO_INFO);
	    return this;
	}


	public DOString getID_BG_DEL_INFO() {
	    return ID_BG_DEL_INFO;
	}

	public jt_bg_atom setID_BG_DEL_INFO(String pID_BG_DEL_INFO) {
	    ID_BG_DEL_INFO.setValue(pID_BG_DEL_INFO);
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


	
	

	public DOString getGEAENDERT_VON() {
	    return GEAENDERT_VON;
	}

	public jt_bg_atom setGEAENDERT_VON(String pGEAENDERT_VON) {
	    GEAENDERT_VON.setValue(pGEAENDERT_VON);
	    return this;
	}


	public DOString getERZEUGT_VON() {
	    return ERZEUGT_VON;
	}

	public jt_bg_atom setERZEUGT_VON(String pERZEUGT_VON) {
	    ERZEUGT_VON.setValue(pERZEUGT_VON);
	    return this;
	}


	public DOString getEN_ATOM_TYP() {
	    return eN_ATOM_TYP;
	}

	public jt_bg_atom setEN_ATOM_TYP(String pEN_ATOM_TYP) {
	    eN_ATOM_TYP.setValue(pEN_ATOM_TYP);
	    return this;
	}
	
	public jt_bg_atom setEN_ATOM_TYP(EN_ATOM_TYP pEN_ATOM_TYP) {
	    eN_ATOM_TYP.setValue(pEN_ATOM_TYP.dbVal());
	    return this;
	}
	


	public DOString getNOTIFIKATION_NR() {
	    return NOTIFIKATION_NR;
	}

	public jt_bg_atom setNOTIFIKATION_NR(String pNOTIFIKATION_NR) {
	    NOTIFIKATION_NR.setValue(pNOTIFIKATION_NR);
	    return this;
	}


	public DOString getNATIONALER_ABFALL_CODE() {
	    return NATIONALER_ABFALL_CODE;
	}

	public jt_bg_atom setNATIONALER_ABFALL_CODE(String pNATIONALER_ABFALL_CODE) {
	    NATIONALER_ABFALL_CODE.setValue(pNATIONALER_ABFALL_CODE);
	    return this;
	}


	public DOString getTIMESTAMP_IN_MASK() {
	    return TIMESTAMP_IN_MASK;
	}

	public jt_bg_atom setTIMESTAMP_IN_MASK(String pTIMESTAMP_IN_MASK) {
	    TIMESTAMP_IN_MASK.setValue(pTIMESTAMP_IN_MASK);
	    return this;
	}


	public DOString getINTRASTAT_MELD() {
	    return INTRASTAT_MELD;
	}

	public jt_bg_atom setINTRASTAT_MELD(String pINTRASTAT_MELD) {
	    INTRASTAT_MELD.setValue(pINTRASTAT_MELD);
	    return this;
	}


	public DOString getMANUELL_PREIS() {
	    return MANUELL_PREIS;
	}

	public jt_bg_atom setMANUELL_PREIS(String pMANUELL_PREIS) {
	    MANUELL_PREIS.setValue(pMANUELL_PREIS);
	    return this;
	}


	public DOString getLIEFERBEDINGUNGEN() {
	    return LIEFERBEDINGUNGEN;
	}

	public jt_bg_atom setLIEFERBEDINGUNGEN(String pLIEFERBEDINGUNGEN) {
	    LIEFERBEDINGUNGEN.setValue(pLIEFERBEDINGUNGEN);
	    return this;
	}

	public DOString getZAHLUNGSBEDINGUNGEN() {
	    return ZAHLUNGSBEDINGUNGEN;
	}

	public jt_bg_atom setZAHLUNGSBEDINGUNGEN(String pZAHLUNGSBEDINGUNGEN) {
		ZAHLUNGSBEDINGUNGEN.setValue(pZAHLUNGSBEDINGUNGEN);
	    return this;
	}

	
	
	public DOString getEN_ATOM_POS_IN_BG() {
		return eN_ATOM_POS_IN_BG;
	}

	public jt_bg_atom setEN_ATOM_POS_IN_BG(String pEN_ATOM_POS_IN_BG) {
		eN_ATOM_POS_IN_BG.setValue(pEN_ATOM_POS_IN_BG);
		return this;
	}

	public jt_bg_atom setEN_ATOM_POS_IN_BG(EN_ATOM_POS_IN_BG pEN_ATOM_POS_IN_BG) {
		eN_ATOM_POS_IN_BG.setValue(pEN_ATOM_POS_IN_BG.dbVal());
		return this;
	}



	public jt_bg_vektor get_jt_bg_vektor() {
		return jt_bg_vektor;
	}


	public jt_bg_atom set_jt_bg_vektor(jt_bg_vektor jt_bg_vektor) {
		this.jt_bg_vektor = jt_bg_vektor;
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
	
	





	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {

		m_vDataObjects.addElement ( ID_BG_ATOM );
		m_vDataObjects.addElement ( ID_MANDANT );
		m_vDataObjects.addElement ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement ( GEAENDERT_VON );
		m_vDataObjects.addElement ( ERZEUGT_VON );
		m_vDataObjects.addElement ( ERZEUGT_AM );
		m_vDataObjects.addElement ( POSNR );
		m_vDataObjects.addElement ( eN_ATOM_TYP );
		m_vDataObjects.addElement ( NOTIFIKATION_NR );
		m_vDataObjects.addElement ( NATIONALER_ABFALL_CODE );
		m_vDataObjects.addElement ( TIMESTAMP_IN_MASK );
		m_vDataObjects.addElement ( INTRASTAT_MELD );
		m_vDataObjects.addElement ( E_PREIS );
		m_vDataObjects.addElement ( MANUELL_PREIS );
		m_vDataObjects.addElement ( KOSTEN_TRANSPORT );
		m_vDataObjects.addElement ( DATUM_AUSFUEHRUNG );
		m_vDataObjects.addElement ( LIEFERBEDINGUNGEN );
		m_vDataObjects.addElement ( ID_BG_VEKTOR );
		m_vDataObjects.addElement ( ID_ADRESSE_LOGI_START );
		m_vDataObjects.addElement ( ID_ADRESSE_LOGI_ZIEL );
		m_vDataObjects.addElement ( ID_VPOS_TPA_FUHRE );
		m_vDataObjects.addElement ( ID_VPOS_TPA_FUHRE_ORT );
		m_vDataObjects.addElement ( ID_EAK_CODE );
		m_vDataObjects.addElement ( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement ( ID_BG_DEL_INFO );
		m_vDataObjects.addElement ( ID_ZAHLUNGSBEDINGUNGEN );
		m_vDataObjects.addElement ( ID_LIEFERBEDINGUNGEN );
		m_vDataObjects.addElement ( eN_ATOM_POS_IN_BG );
		m_vDataObjects.addElement ( ZAHLUNGSBEDINGUNGEN );
		
	}









	
}
