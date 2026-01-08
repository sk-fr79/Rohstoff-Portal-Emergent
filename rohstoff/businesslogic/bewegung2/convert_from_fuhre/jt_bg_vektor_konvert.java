/**
 * rohstoff.businesslogic.bewegung.convert_from_fuhre
 * @author manfred
 * @date 16.01.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;
import java.util.Date;

import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR_KONVERT;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_KONTO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_Lager_Konto;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DODate;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOLong;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.DOString;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.REC_Base;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ORT_ext;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.types.Rec21_VPOS_TPA_FUHRE_ext;


/**
 * @author manfred
 * @date 16.01.2018
 *
 */
public class jt_bg_vektor_konvert extends REC_Base{


	


	private DOString 	ID_BG_VEKTOR_KONVERT = new DOString(BG_VEKTOR_KONVERT.id_bg_vektor,null,"").setRaw(true);
	private DOLong 		ID_MANDANT = new DOLong(BG_VEKTOR_KONVERT.id_mandant);
	
	private DODate 		LETZTE_AENDERUNG = new DODate(BG_VEKTOR_KONVERT.letzte_aenderung);
	private DOString 	GEAENDERT_VON = new DOString(BG_VEKTOR_KONVERT.geaendert_von);
	private DOString 	ERZEUGT_VON = new DOString(BG_VEKTOR_KONVERT.erzeugt_von);
	private DODate 		ERZEUGT_AM = new DODate(BG_VEKTOR_KONVERT.erzeugt_am);
	
	private DOString	ID_BG_VEKTOR = new DOString(BG_VEKTOR_KONVERT.id_bg_vektor,null,"").setRaw(true);
	private DOLong		ID_VPOS_TPA_FUHRE = new DOLong(BG_VEKTOR_KONVERT.id_vpos_tpa_fuhre);
	private DOLong		ID_VPOS_TPA_FUHRE_ORT = new DOLong(BG_VEKTOR_KONVERT.id_vpos_tpa_fuhre_ort);
	private DOLong		ID_LAGER_KONTO = new DOLong(BG_VEKTOR_KONVERT.id_lager_konto);
	
	private jt_bg_vektor _BG_VEKTOR = null;
	


	public jt_bg_vektor_konvert( conv_helper helper) {
		super(_TAB.bg_vektor_konvert);
		this._helper = helper;
		this.initFieldList();
	}
	
	
	public jt_bg_vektor_konvert(Rec21_VPOS_TPA_FUHRE_ext fuhre, conv_helper helper) throws myException{
		this(helper);
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant));
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));
		setID_VPOS_TPA_FUHRE(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre));
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
	}

	
	
	public jt_bg_vektor_konvert(Rec21_VPOS_TPA_FUHRE_ORT_ext ort, conv_helper helper) throws myException{
		this(helper);

		setID_MANDANT(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_mandant));
		setLETZTE_AENDERUNG(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.letzte_aenderung) )  ;
		setGEAENDERT_VON(ort.get_ufs_dbVal( VPOS_TPA_FUHRE_ORT.geaendert_von));
		setERZEUGT_AM(ort.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE_ORT.erzeugt_am));
		setERZEUGT_VON(ort.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.erzeugt_von));
		setID_VPOS_TPA_FUHRE(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre));
		setID_VPOS_TPA_FUHRE_ORT(ort.get_raw_resultValue_Long(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort));
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
	}
	
	
	public jt_bg_vektor_konvert(Rec21_Lager_Konto lager,conv_helper helper) throws myException{
		this(helper);
		
		setID_MANDANT(lager.get_raw_resultValue_Long(LAGER_KONTO.id_mandant));
		setLETZTE_AENDERUNG(lager.get_raw_resultValue_timeStamp(LAGER_KONTO.letzte_aenderung) )  ;
		setGEAENDERT_VON(lager.get_ufs_dbVal( LAGER_KONTO.geaendert_von));
		setERZEUGT_AM(lager.get_raw_resultValue_timeStamp(LAGER_KONTO.erzeugt_am));
		setERZEUGT_VON(lager.get_ufs_dbVal(LAGER_KONTO.erzeugt_von));
		setID_LAGER_KONTO(lager.get_raw_resultValue_Long(LAGER_KONTO.id_lager_konto));
		setID_BG_VEKTOR(BG_VEKTOR._tab().seq_currval());
	}
	
	
	
	public DODate getLETZTE_AENDERUNG() {
	    return LETZTE_AENDERUNG;
	}

	public jt_bg_vektor_konvert setLETZTE_AENDERUNG(Date pLETZTE_AENDERUNG) {
	    LETZTE_AENDERUNG.setValue(pLETZTE_AENDERUNG);
	    return this;
	}


	public DODate getERZEUGT_AM() {
	    return ERZEUGT_AM;
	}

	public jt_bg_vektor_konvert setERZEUGT_AM(Date pERZEUGT_AM) {
	    ERZEUGT_AM.setValue(pERZEUGT_AM);
	    return this;
	}




	public DOLong getID_MANDANT() {
	    return ID_MANDANT;
	}

	public jt_bg_vektor_konvert setID_MANDANT(Long pID_MANDANT) {
	    ID_MANDANT.setValue(pID_MANDANT);
	    return this;
	}

	

	public DOString getGEAENDERT_VON() {
	    return GEAENDERT_VON;
	}

	public jt_bg_vektor_konvert setGEAENDERT_VON(String pGEAENDERT_VON) {
	    GEAENDERT_VON.setValue(pGEAENDERT_VON);
	    return this;
	}


	public DOString getERZEUGT_VON() {
	    return ERZEUGT_VON;
	}

	public jt_bg_vektor_konvert setERZEUGT_VON(String pERZEUGT_VON) {
	    ERZEUGT_VON.setValue(pERZEUGT_VON);
	    return this;
	}


	
	public jt_bg_vektor_konvert setBG_VEKTOR(jt_bg_vektor vektor){
		this._BG_VEKTOR = vektor;
		return this;
	}





	public DOString getID_BG_VEKTOR() {
		return ID_BG_VEKTOR;
	}


	public jt_bg_vektor_konvert setID_BG_VEKTOR(String iD_BG_VEKTOR) {
		ID_BG_VEKTOR.setValue(iD_BG_VEKTOR);
		return this;
	}


	public DOLong getID_VPOS_TPA_FUHRE() {
		return ID_VPOS_TPA_FUHRE;
	}


	public jt_bg_vektor_konvert setID_VPOS_TPA_FUHRE(Long iD_VPOS_TPA_FUHRE) {
		ID_VPOS_TPA_FUHRE.setValue( iD_VPOS_TPA_FUHRE );
		return this;
	}


	public DOLong getID_VPOS_TPA_FUHRE_ORT() {
		return ID_VPOS_TPA_FUHRE_ORT;
	}


	public jt_bg_vektor_konvert setID_VPOS_TPA_FUHRE_ORT(Long iD_VPOS_TPA_FUHRE_ORT) {
		ID_VPOS_TPA_FUHRE_ORT.setValue(iD_VPOS_TPA_FUHRE_ORT);
		return this;
	}


	public DOLong getID_LAGER_KONTO() {
		return ID_LAGER_KONTO;
	}


	public jt_bg_vektor_konvert setID_LAGER_KONTO(Long iD_LAGER_KONTO) {
		ID_LAGER_KONTO.setValue(iD_LAGER_KONTO);
		return this;
	}





	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {

		m_vDataObjects.addElement ( ID_BG_VEKTOR_KONVERT );
		m_vDataObjects.addElement ( ID_MANDANT );
		m_vDataObjects.addElement ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement ( GEAENDERT_VON );
		m_vDataObjects.addElement ( ERZEUGT_VON );
		m_vDataObjects.addElement ( ERZEUGT_AM );
		
		m_vDataObjects.addElement ( ID_BG_VEKTOR );
		m_vDataObjects.addElement ( ID_LAGER_KONTO );
		m_vDataObjects.addElement ( ID_VPOS_TPA_FUHRE );
		m_vDataObjects.addElement ( ID_VPOS_TPA_FUHRE_ORT );
		
	}
	
}
