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
public class jt_bg_storno_info extends REC_Base{


	


	private DOString 	ID_BG_STORNO_INFO = new DOString("ID_BG_STORNO_INFO",null,"");
	private DOLong 		ID_MANDANT = new DOLong("ID_MANDANT");
	
	private DODate 		LETZTE_AENDERUNG = new DODate("LETZTE_AENDERUNG");
	private DOString 	GEAENDERT_VON = new DOString("GEAENDERT_VON");
	private DOString 	ERZEUGT_VON = new DOString("ERZEUGT_VON");
	private DODate 		ERZEUGT_AM = new DODate("ERZEUGT_AM");
	
	private DODate 		STORNO_DATUM = new DODate("STORNO_DATUM");
	private DOString 	STORNO_GRUND = new DOString("STORNO_GRUND");

	private DOLong 		ID_USER = new DOLong("ID_USER");
	
	// Vektor
	private jt_bg_vektor jt_bg_vektor = null;


	public jt_bg_storno_info() {
		super("JT_BG_STORNO_INFO");
		this.initFieldList();
	}
	
	


	
	public jt_bg_storno_info(Rec20_VPOS_TPA_FUHRE_ext fuhre) throws myException{
		this();

		setID_BG_STORNO_INFO(BG_STORNO_INFO._tab().seq_nextval());
		setSTORNO_DATUM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung));
		setSTORNO_GRUND(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.storno_grund));
//		setID_USER(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.storno_kuerzel));
		
		setID_MANDANT(fuhre.get_raw_resultValue_Long(VPOS_TPA_FUHRE.id_mandant));
		setLETZTE_AENDERUNG(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.letzte_aenderung) )  ;
		setGEAENDERT_VON(fuhre.get_ufs_dbVal( VPOS_TPA_FUHRE.geaendert_von));
		setERZEUGT_AM(fuhre.get_raw_resultValue_timeStamp(VPOS_TPA_FUHRE.erzeugt_am));
		setERZEUGT_VON(fuhre.get_ufs_dbVal(VPOS_TPA_FUHRE.erzeugt_von));

	}
	
	public DODate getLETZTE_AENDERUNG() {
	    return LETZTE_AENDERUNG;
	}

	public jt_bg_storno_info setLETZTE_AENDERUNG(Date pLETZTE_AENDERUNG) {
	    LETZTE_AENDERUNG.setValue(pLETZTE_AENDERUNG);
	    return this;
	}


	public DODate getERZEUGT_AM() {
	    return ERZEUGT_AM;
	}

	public jt_bg_storno_info setERZEUGT_AM(Date pERZEUGT_AM) {
	    ERZEUGT_AM.setValue(pERZEUGT_AM);
	    return this;
	}


	public DODate getSTORNO_DATUM() {
	    return STORNO_DATUM;
	}

	public jt_bg_storno_info setSTORNO_DATUM(Date pSTORNO_DATUM) {
	    STORNO_DATUM.setValue(pSTORNO_DATUM);
	    return this;
	}


	public DOString getSTORNO_GRUND() {
	    return STORNO_GRUND;
	}

	public jt_bg_storno_info setSTORNO_GRUND(String pSTORNO_GRUND) {
	    STORNO_GRUND.setValue(pSTORNO_GRUND);
	    return this;
	}


	public DOLong getID_USER() {
	    return ID_USER;
	}

	public jt_bg_storno_info setID_USER(Long pID_USER) {
	    ID_USER.setValue(pID_USER);
	    return this;
	}




	public DOString getID_BG_STORNO_INFO() {
	    return ID_BG_STORNO_INFO;
	}

	public jt_bg_storno_info setID_BG_STORNO_INFO(String pID_BG_DEL_INFO) {
	    ID_BG_STORNO_INFO.setValue(pID_BG_DEL_INFO);
	    return this;
	}


	public DOLong getID_MANDANT() {
	    return ID_MANDANT;
	}

	public jt_bg_storno_info setID_MANDANT(Long pID_MANDANT) {
	    ID_MANDANT.setValue(pID_MANDANT);
	    return this;
	}




	
	

	public DOString getGEAENDERT_VON() {
	    return GEAENDERT_VON;
	}

	public jt_bg_storno_info setGEAENDERT_VON(String pGEAENDERT_VON) {
	    GEAENDERT_VON.setValue(pGEAENDERT_VON);
	    return this;
	}


	public DOString getERZEUGT_VON() {
	    return ERZEUGT_VON;
	}

	public jt_bg_storno_info setERZEUGT_VON(String pERZEUGT_VON) {
	    ERZEUGT_VON.setValue(pERZEUGT_VON);
	    return this;
	}




	public jt_bg_vektor get_jt_bg_vektor() {
		return jt_bg_vektor;
	}


	public jt_bg_storno_info set_jt_bg_vektor(jt_bg_vektor jt_bg_vektor) {
		this.jt_bg_vektor = jt_bg_vektor;
		return this;
	}
	
	

	
	





	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.REC_Base#initFieldList()
	 */
	@Override
	protected void initFieldList() {

		m_vDataObjects.addElement ( ID_BG_STORNO_INFO );
		m_vDataObjects.addElement ( ID_MANDANT );
		m_vDataObjects.addElement ( LETZTE_AENDERUNG );
		m_vDataObjects.addElement ( GEAENDERT_VON );
		m_vDataObjects.addElement ( ERZEUGT_VON );
		m_vDataObjects.addElement ( ERZEUGT_AM );
		m_vDataObjects.addElement ( STORNO_DATUM );
		m_vDataObjects.addElement ( STORNO_GRUND );
	}




	
}
